package com.br.airsystem.service;

import com.br.airsystem.model.User;
import com.br.airsystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.stereotype.Service;

@Deprecated(forRemoval = true)
@Service
@RequiredArgsConstructor
public class DemoService extends DefaultService{

    private final UserRepository userRepository;

    public void createUser(){
        User user = User.builder()
                .name("Evandro C")
                .cpf("935.024.450-01")
                .admin(true)
                .email("evandroc@gmail.com")
                .password(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("123456789"))
                .district("João Lemes Sei lá")
                .street("Rua Taltal")
                .build();

        userRepository.save(user);
    }
}
