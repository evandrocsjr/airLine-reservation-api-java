package com.br.airsystem.controller;

import com.br.airsystem.dto.login.LoginRequestDTO;
import com.br.airsystem.exception.UnauthorizedException;
import com.br.airsystem.model.User;
import com.br.airsystem.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@Api("Login")
@RequestMapping("/v1/login")
public class LoginController extends DefaultController {

    private final UserService userService;
    private final String USER_INVALID = "Email ou senha incorretos.";


    @PostMapping
    @ApiOperation("Retorna o token de acesso.")
    public String loginUser(@RequestBody LoginRequestDTO loginRequestDto) throws UnauthorizedException {
        User user = userService.findByEmail(loginRequestDto.getEmail());

        if (user != null) {
            if (PasswordEncoderFactories.createDelegatingPasswordEncoder().matches(loginRequestDto.getPassword(), user.getPassword())) {
                return super.generateToken(user.getId());
            }
            throw new UnauthorizedException(USER_INVALID);
        }
        throw new UnauthorizedException(USER_INVALID);
    }
}
