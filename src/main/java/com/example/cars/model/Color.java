package com.example.cars.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Color {
    WHITE("Белый"),
    BLACK("Черный"),
    BLUE("Синий"),
    RED("Красный"),
    GREEN("Зеленый"),
    YELLOW("Желтый");
    private final String description;
}
