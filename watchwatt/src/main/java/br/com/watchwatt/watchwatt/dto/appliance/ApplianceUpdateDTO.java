package br.com.watchwatt.watchwatt.dto.appliance;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record ApplianceUpdateDTO(
		
		@Size(min = 1)
		String name,
		
		String model,
		
		@Positive
		int power
		
		) {

}
