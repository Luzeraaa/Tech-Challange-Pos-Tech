package br.com.watchwatt.watchwatt.dto.user;

import br.com.watchwatt.watchwatt.domain.user.Gender;
import br.com.watchwatt.watchwatt.exception.BadRequestException;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

public record UserDTO(

        @Pattern(regexp = NUMBER_REGEX, message = CPF_NUMBER_MESSAGE)
        @CPF(message = CPF_MESSAGE)
        String cpf,

        @NotNull(message = NAME_MESSAGE)
        String name,

        @Past(message = BIRTHDAY_PAST_MESSAGE)
        @NotNull(message = BIRTHDAY_MESSAGE)
        LocalDate birthday,

        @NotNull(message = GENDER_MESSAGE)
        Gender gender,

        @Email(message = EMAIL_MESSAGE)
        String email,

        @Size(min = PASSWORD_MIN_SIZE, message = PASSWORD_SIZE_MESSAGE)
        @Pattern(regexp = PASSWORD_REGEX, message = PASSWORD_MESSAGE)
        String password
) {
  private static final String CPF_MESSAGE = "CPF number is invalid, Please use a valid cpf";
  private static final String CPF_NUMBER_MESSAGE = "CPF is invalid, only number";
  private static final String CPF_NON_NUMERIC_MESSAGE = "CPF contains non-numeric characters";
  private static final String EMAIL_MESSAGE = "Email is invalid, Please use a valid email";
  private static final String PASSWORD_REGEX = "[A-Za-z0-9]+";
  private static final String PASSWORD_MESSAGE = "Password must contain letters and numbers";
  private static final String PASSWORD_SIZE_MESSAGE = "The password must be in the minimum 6 characters";
  private static final String NAME_MESSAGE = "Name cannot be null or empty";
  private static final String BIRTHDAY_MESSAGE = "Birthday cannot be null or empty";
  private static final String BIRTHDAY_PAST_MESSAGE = "must be a past date";
  private static final String GENDER_MESSAGE = "Gender should not be null";
  private static final int PASSWORD_MIN_SIZE = 6;
  private static final String NUMBER_REGEX = "^[0-9]*$";

  public Long getCPF() {
    try {
      return Long.parseLong(this.cpf);
    } catch (NumberFormatException ex) {
      throw new BadRequestException(CPF_NON_NUMERIC_MESSAGE);
    }
  }
}
