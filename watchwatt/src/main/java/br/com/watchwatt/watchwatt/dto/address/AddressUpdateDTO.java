package br.com.watchwatt.watchwatt.dto.address;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;

public record AddressUpdateDTO(
    @Pattern(regexp = ZIP_CODE_REGEX, message = ZIP_CODE_INVALID_MESSAGE)
    String zipCode,

    @PositiveOrZero(message = FIELD_ONLY_NUMBER_MESSAGE)
    Integer number,

    String reference
) {
    private static final String ZIP_CODE_REGEX = "\\d{5}(-\\d{3})?";
    private static final String ZIP_CODE_INVALID_MESSAGE = "Zipcode is invalid, example: 00000-000";
    private static final String FIELD_ONLY_NUMBER_MESSAGE = "Invalid field, enter only positive numbers";
}
