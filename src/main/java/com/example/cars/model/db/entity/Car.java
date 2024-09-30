package com.example.cars.model.db.entity;
import com.example.cars.model.CarStatus;
import com.example.cars.model.Color;
import com.example.cars.model.Gender;
import com.example.cars.model.UserStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;


@Getter
@Setter
@Entity
@Table(name = "cars")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "created_at")
    LocalDateTime createdAt;
    @Column(name = "updated_at")
    LocalDateTime updatedAt;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    CarStatus status;
    @Column(name = "model_name")
    String modelName;
    @Column(name = "year")
    Integer year;
    @Column(name = "color")
    @Enumerated(EnumType.STRING)
    Color color;
    @Column(name = "price")
    BigDecimal price;
    @Column(name = "engine_capacity")
    Float engineCapacity;
    @Column(name = "is_new")
    Boolean isNew;

    @ManyToOne
    User user;
}
