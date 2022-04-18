package com.br.airsystem.model;

import com.br.airsystem.enums.SystemModule;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class License {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

//    @Lob
//    @Convert(converter = SystemModulesConverter.class)
//    private List<SystemModule> modules;
}
