package br.com.watchwatt.watchwatt.dto.appliance;

import br.com.watchwatt.watchwatt.domain.appliance.Status;
import jakarta.validation.constraints.NotNull;

public record ApplianceStatusDTO(
        @NotNull
        Long id,

        @NotNull
        Status status
) {
}
