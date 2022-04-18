package com.br.airsystem.service;

import com.br.airsystem.dto.company.CompanyDTO;
import com.br.airsystem.exception.ForbiddenException;
import com.br.airsystem.exception.NotFoundException;
import com.br.airsystem.exception.UnprocessableException;
import com.br.airsystem.model.Company;
import com.br.airsystem.model.User;
import com.br.airsystem.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompanyService extends DefaultService{

    private final CompanyRepository companyRepository;
    private final String INVALID_COMPANY_NAME = "Nome da empresa inválido.";
    private final String COMPANY_NOT_FOUND = "Empresa inválida.";

    private void validateCompany(CompanyDTO companyDto) throws UnprocessableException {
        if (StringUtils.isBlank(companyDto.getName())) {
            throw new UnprocessableException(INVALID_COMPANY_NAME);
        }
    }

    public CompanyDTO create(User loggedUser, CompanyDTO companyDTO) throws UnprocessableException, ForbiddenException {
        super.checkPermission(loggedUser);
        validateCompany(companyDTO);

        ModelMapper mapper = new ModelMapper();
        Company object = mapper.map(companyDTO, Company.class);

        companyRepository.save(object);

        return mapper.map(object, CompanyDTO.class);
    }

    public List<CompanyDTO> findAll() {
        ModelMapper mapper = new ModelMapper();
        return companyRepository.findAll().stream().map(item -> mapper.map(item, CompanyDTO.class)).collect(Collectors.toList());
    }

    public CompanyDTO findById(Long id) throws NotFoundException {
        ModelMapper mapper = new ModelMapper();

        return companyRepository.findById(id).map(c -> mapper.map(c, CompanyDTO.class))
                .orElseThrow(() -> new NotFoundException(COMPANY_NOT_FOUND));
    }

    public void deleteCompany(Long id) throws NotFoundException {
        Company company = companyRepository.findById(id).orElseThrow(() -> new NotFoundException(COMPANY_NOT_FOUND));
        companyRepository.deleteById(company.getId());
    }

    public CompanyDTO update(Long id, CompanyDTO companyDto) throws NotFoundException, UnprocessableException {
        ModelMapper mapper = new ModelMapper();
        Company company = companyRepository.findById(id).orElseThrow(() -> new NotFoundException(COMPANY_NOT_FOUND));
        validateCompany(companyDto);

        company.setCnpj(companyDto.getCnpj());
        company.setName(companyDto.getName());

        Company updateComp = companyRepository.save(company);

        return mapper.map(updateComp, CompanyDTO.class);
    }
}
