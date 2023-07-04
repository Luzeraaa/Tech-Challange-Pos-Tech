package br.com.watchwatt.watchwatt.dto.kinship;

import br.com.watchwatt.watchwatt.domain.kinship.DegreeKinship;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;


public record KinshipDTO(
        @Pattern(regexp = NO_NUMERIC_REGEX, message = NAME_NON_NUMERIC_MESSAGE)
        @NotBlank(message = FIELD_CANNOT_BE_NULL_EMPTY_BLANK_MESSAGE)
        String name,

        @NotNull(message = FIELD_CANNOT_BE_NULL_EMPTY_BLANK_MESSAGE)
        DegreeKinship degreeKinship
) {
    private static final String NO_NUMERIC_REGEX = "[A-Za-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ\\s]+$";
    private static final String FIELD_CANNOT_BE_NULL_EMPTY_BLANK_MESSAGE = "Field cannot be null, empty or blank";
    private static final String NAME_NON_NUMERIC_MESSAGE = "Name must not contain number and cannot be empty";
}
