package com.br.airsystem.service;

import com.br.airsystem.dto.user.UserDTO;
import com.br.airsystem.exception.UnprocessableException;
import com.br.airsystem.model.User;
import com.br.airsystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final String INVALID_USER_NAME = "Nome de usuário inválido.";

    private void validateUser(UserDTO user) throws UnprocessableException {
        if (StringUtils.isBlank(user.getName())) {
            throw new UnprocessableException(INVALID_USER_NAME);
        }
    }

    public UserDTO createUser(UserDTO userDto) throws UnprocessableException {
        validateUser(userDto);

        ModelMapper mapper = new ModelMapper();
        User object = mapper.map(userDto, User.class);


        userRepository.save(object);
        return mapper.map(object, UserDTO.class);
    }
}
