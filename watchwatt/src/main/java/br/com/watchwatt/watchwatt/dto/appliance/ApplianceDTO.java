package br.com.watchwatt.watchwatt.dto.appliance;

import br.com.watchwatt.watchwatt.domain.appliance.Appliance;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NoArgsConstructor;

public record ApplianceDTO(
        @NotNull
        String name,
        
        @NotNull
        String model,
        
        @NotNull
        @Positive
        Integer power
        
) {
	
	public ApplianceDTO(Appliance appliance) {
		this(appliance.getName(), appliance.getModel(), appliance.getPower());
	}
}
