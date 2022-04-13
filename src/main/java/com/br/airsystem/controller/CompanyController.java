package com.br.airsystem.controller;

import com.br.airsystem.dto.company.CompanyDTO;
import com.br.airsystem.exception.NotFoundException;
import com.br.airsystem.exception.UnprocessableException;
import com.br.airsystem.service.CompanyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "Empresas")
@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/v1/companies")
public class CompanyController {

    private final CompanyService companyService;

    @PostMapping()
    @ApiOperation("Cadastra uma nova Empresa")
    public CompanyDTO createCompany(@RequestBody CompanyDTO companyDTO) throws UnprocessableException {
        return companyService.create(companyDTO);
    }

    @GetMapping
    @ApiOperation("Retorna todos as Empresas cadastradas")
    public List<CompanyDTO> getAll() {
        return companyService.findAll();
    }

    @GetMapping(value = "/{id}")
    @ApiOperation("Retora uma Empresa através de seu id")
    public CompanyDTO getId(@PathVariable("id") Long id) throws NotFoundException {
        return companyService.findById(id);
    }

    @PutMapping(value = "/{id}")
    @ApiOperation("Atualiza uma Empresa através de seu id")
    public CompanyDTO updateCompany(@PathVariable("id") Long id, @RequestBody CompanyDTO companyDto) throws UnprocessableException, NotFoundException {
        return companyService.update(id, companyDto);
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation("Exclui uma Empresa através de seu id")
    public void deleteCompany(@PathVariable("id") Long id) throws NotFoundException {
        companyService.deleteCompany(id);
    }
}
