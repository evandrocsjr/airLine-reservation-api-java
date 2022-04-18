package com.br.airsystem.service;

import com.br.airsystem.dto.user.CommonUserDTO;
import com.br.airsystem.dto.user.UserDTO;
import com.br.airsystem.exception.UnprocessableException;
import com.br.airsystem.model.User;
import com.br.airsystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final String INVALID_USER_NAME = "Nome de usuário inválido.";
    private final String INVALID_PASSWORD = "Senha deve possuir no mínimo 8 caracteres.";

    private void validateUser(CommonUserDTO user) throws UnprocessableException {
        if (StringUtils.isBlank(user.getName())) {
            throw new UnprocessableException(INVALID_USER_NAME);
        }
        if(user.getPassword().length() < 8){
            throw new UnprocessableException(INVALID_PASSWORD);
        }
    }

    public void createUser(CommonUserDTO userDto) throws UnprocessableException {
        validateUser(userDto);

        ModelMapper mapper = new ModelMapper();
        User object = mapper.map(userDto, User.class);
        object.setPassword(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(object.getPassword()));
        userRepository.save(object);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
