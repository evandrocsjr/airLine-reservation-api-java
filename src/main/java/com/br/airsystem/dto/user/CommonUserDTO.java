package com.br.airsystem.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommonUserDTO {
    private Long id;
    private String name;
    private String email;
    private String password;
    private String cpf;
    private String street;
    private String district;
}
