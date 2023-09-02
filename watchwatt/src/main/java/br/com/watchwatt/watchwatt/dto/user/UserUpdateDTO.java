package br.com.watchwatt.watchwatt.dto.user;

import br.com.watchwatt.watchwatt.domain.user.Gender;

import java.time.LocalDate;

public record UserUpdateDTO(

        String name,
        String cpf,
        String email,
        String password,
        LocalDate birthday,
        Gender gender
) {
}
