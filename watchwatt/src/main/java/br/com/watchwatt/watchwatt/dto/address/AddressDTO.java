package br.com.watchwatt.watchwatt.dto.address;

import br.com.watchwatt.watchwatt.domain.address.Address;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record AddressDTO(
        @Pattern(regexp = ZIP_CODE_REGEX, message = "zipcode invalid")
        String zipCode,
        @NotNull(message = "Street not null")
        String street,
        @NotNull
        Integer number,
        @NotNull
        String neighborhood,
        @NotNull
        String city,
        @NotNull
        String state
) {

  private static final String ZIP_CODE_REGEX = "\\d{5}(-\\d{3})?";

  public Address getAddress() {
    return Address.builder()
            .zipCode(zipCode)
            .street(street)
            .number(number)
            .neighborhood(neighborhood)
            .city(city)
            .state(state)
            // .user(new User(userId))
            .build();
  }
}
