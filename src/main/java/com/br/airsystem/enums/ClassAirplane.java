package com.br.airsystem.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum ClassAirplane {

    ECONOMIC_CLASS(1, "Classe Econômica", "Economic Class"),
    EXECUTIVE_CLASS(2, "Classe Executiva", "Executive Class"),
    FIRST_CLASS(3, "Primeira Classe", "First Class");

    final int value;
    final String descriptionPT;
    final String descriptionEN;

    public static ClassAirplane getByValue(int value) {
        return Arrays.stream(values()).filter(classAir -> classAir.value == value).findFirst().orElseThrow();
    }

    public static ClassAirplane getByName(String name) {
        return Arrays.stream(values()).filter(classAir -> classAir.name().contains(name)).findFirst().orElseThrow();
    }
}
