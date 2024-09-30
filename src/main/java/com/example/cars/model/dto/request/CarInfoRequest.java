package com.example.cars.model.dto.request;
import com.example.cars.model.Color;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

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
    Integer year;
    Color color;
    Boolean isNew;
    @NotNull
    BigDecimal price;
    Float engineCapacity;
}
