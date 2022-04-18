package com.br.airsystem.controller;

import com.br.airsystem.dto.license.LicenseDTO;
import com.br.airsystem.service.LicenseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@CrossOrigin
@Api(tags = "Licença")
@RequestMapping("/v1/license")
public class LicenseController extends DefaultController{

    private final LicenseService licenseService;

    @PostMapping
    @ApiOperation("Cria uma nova Licença")
    public LicenseDTO createLicense(@RequestBody LicenseDTO licenseDto){
        return licenseService.createLicense(licenseDto);
    }
}
