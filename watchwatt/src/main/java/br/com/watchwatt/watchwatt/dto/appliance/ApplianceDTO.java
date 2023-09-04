package br.com.watchwatt.watchwatt.dto.appliance;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ApplianceDTO(
        @NotNull
        String name,

        @NotNull
        String model,

        @NotNull
        @Positive
        Integer power
) {
}
