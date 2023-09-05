package br.com.watchwatt.watchwatt.controller;

import br.com.watchwatt.watchwatt.domain.kinship.Kinship;
import br.com.watchwatt.watchwatt.dto.kinship.KinshipDTO;
import br.com.watchwatt.watchwatt.service.kinship.KinshipService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping(path = "/kinship")
public record KinshipController(
        KinshipService kinshipService

) implements Controller {

    private static final String USER_ID = "userId";
    private static final String KINSHIP_ID = "kinshipId";
    private static final String KINSHIP_ID_PATH = "/kinship/{idAddress}";
    private static final String KINSHIP_ADDRESS_PATH = "/address";
    private static final String ADDRESS_ID = "addressId";

    @PostMapping(headers = X_API_VERSION_1, path = KINSHIP_ADDRESS_PATH, params = ADDRESS_ID)
    public ResponseEntity<List<Kinship>> addKinshipByCpf(
            @RequestBody @Valid List<KinshipDTO> kinshipDTO,
            Long addressId,
            UriComponentsBuilder uriBuilder
    ) {
        var kinship = kinshipService.addKinshipByAddress(kinshipDTO, addressId);

        return ResponseEntity
                .created(uriBuilder.path(KINSHIP_ID_PATH).buildAndExpand(kinship).toUri())
                .body(kinship);
    }

    @DeleteMapping(headers = X_API_VERSION_1, params = {KINSHIP_ID})
    public ResponseEntity<Void> deleteKinshipById(Long kinshipId) {
        kinshipService.deleteKinshipByCpfAndId(kinshipId);

        return ResponseEntity.noContent().build();
    }
}
