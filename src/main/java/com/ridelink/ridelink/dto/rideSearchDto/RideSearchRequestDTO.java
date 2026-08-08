package com.ridelink.ridelink.dto.rideSearchDto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RideSearchRequestDTO {

    @NotBlank
    private String source;

    @NotBlank
    private String destination;

    @NotNull
    private LocalDate travelDate;

    @Min(1)
    private Integer requiredSeats;
}
