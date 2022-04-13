package com.br.airsystem.service;

import com.br.airsystem.dto.company.CompanyDTO;
import com.br.airsystem.exception.NotFoundException;
import com.br.airsystem.exception.UnprocessableException;
import com.br.airsystem.model.Company;
import com.br.airsystem.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final String INVALID_COMPANY_NAME = "Nome da empresa inválido.";
    private final String COMPANY_NOT_FOUND = "Empresa inválida.";

    private void validateCompany(CompanyDTO companyDTO) throws UnprocessableException {
        if (StringUtils.isBlank(companyDTO.getName())) {
            throw new UnprocessableException(INVALID_COMPANY_NAME);
        }
    }

    public CompanyDTO create(CompanyDTO companyDTO) throws UnprocessableException {
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
}
