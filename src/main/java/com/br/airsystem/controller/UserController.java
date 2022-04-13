package com.br.airsystem.controller;

import com.br.airsystem.dto.user.UserDTO;
import com.br.airsystem.exception.UnprocessableException;
import com.br.airsystem.model.User;
import com.br.airsystem.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = "Usuários")
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/v1/users")
public class UserController extends DefaultController{

    private final UserService userService;

    @PostMapping
    @ApiOperation(value = "Cria um novo usuário")
    public UserDTO createUser(@RequestHeader("Authorization") String token, @RequestBody UserDTO user) throws UnprocessableException {
        return userService.createUser(user);
    }
}
