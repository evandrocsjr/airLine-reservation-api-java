package com.br.airsystem.service;

import com.br.airsystem.dto.license.LicenseDTO;
import com.br.airsystem.model.License;
import com.br.airsystem.repository.LicenseRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LicenseService extends DefaultService{

    private final LicenseRepository licenseRepository;

    @Transactional
    public LicenseDTO createLicense(LicenseDTO licenseDto) {
        ModelMapper mapper = new ModelMapper();
        License newLicense = mapper.map(licenseDto, License.class);

        return mapper.map(licenseRepository.save(newLicense), LicenseDTO.class);
    }
}
