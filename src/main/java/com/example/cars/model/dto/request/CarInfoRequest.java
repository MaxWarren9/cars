package com.example.cars.model.dto.request;
import com.example.cars.model.Color;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CarInfoRequest {
    @NotEmpty
    String email;
    String password;
    String modelName;
    String modelYear;
    Color color;
    @NotNull
    Long price;
    Float engineCapacity;
}
