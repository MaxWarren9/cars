package com.example.cars.model.dto.response;

import com.example.cars.model.dto.request.CarInfoRequest;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CarInfoResponse extends CarInfoRequest {
    Long id;
}
