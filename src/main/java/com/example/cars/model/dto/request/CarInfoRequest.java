package com.example.cars.model.dto.request;
import com.example.cars.model.Color;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CarInfoRequest {
    String modelName;
    String modelBrand;
    String modelYear;
    Long price;
    Float weight;
    Float engineCapacity;
    Color color;
}
