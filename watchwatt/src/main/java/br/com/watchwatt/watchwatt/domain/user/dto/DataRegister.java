package br.com.watchwatt.watchwatt.domain.user.dto;

import java.time.ZonedDateTime;

import org.hibernate.validator.constraints.br.CPF;

import br.com.watchwatt.watchwatt.domain.user.Gender;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DataRegister(
		
		@CPF
		String cpf,
		
		@NotBlank
		String name,
		
		@NotNull
		ZonedDateTime birthday,
		
		@NotNull
		Gender gender	
		
		) {

}
