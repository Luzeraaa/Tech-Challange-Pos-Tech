package br.com.watchwatt.watchwatt.controller;

import br.com.watchwatt.watchwatt.domain.kinship.Kinship;
import br.com.watchwatt.watchwatt.dto.kinship.KinshipDTO;
import br.com.watchwatt.watchwatt.service.kinship.KinshipService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    private static final String CPF = "cpf";
    private static final String USER_ID = "userId";
    private static final String KINSHIP_ID = "kinshipId";
    private static final String KINSHIP_ID_PATH = "/kinship/{idAddress}";
    private static final String KINSHIP_ADDRESS_ID = "/kinship/address/id";
    private static final String ADDRESS_ID = "addressId";


//    @GetMapping(headers = X_API_VERSION_1, params = {CPF})
//    public ResponseEntity<List<Kinship>> getKinshipByCpf(String cpf) {
//        var kinship = kinshipService.getKinshipByCpf(cpf);
//
//        return ResponseEntity.ok(kinship);
//    }

    @PostMapping(headers = X_API_VERSION_1, path = KINSHIP_ADDRESS_ID, params = ADDRESS_ID)
    public ResponseEntity<List<Kinship>> addKinshipByCpf(
            @RequestBody @Valid List<KinshipDTO> kinshipDTO,
            long idAddress,
            UriComponentsBuilder uriBuilder
    ) {
        var kinship = kinshipService.addKinshipByAddress(kinshipDTO, idAddress);

        return ResponseEntity
                .created(uriBuilder.path(KINSHIP_ID_PATH).buildAndExpand(kinship).toUri())
                .body(kinship);
    }

    @PutMapping(headers = X_API_VERSION_1, params = {USER_ID, KINSHIP_ID})
    public ResponseEntity<Kinship> updateByUserIdAndKinshipId(@RequestBody @Valid KinshipDTO kinshipDTO, Long userId, Long kinshipId) {
        var kinship = kinshipService.updateByUserIdAndKinshipId(kinshipDTO, userId, kinshipId);

        return ResponseEntity.ok(kinship);
    }

    @DeleteMapping(headers = X_API_VERSION_1, params = {USER_ID, KINSHIP_ID})
    public ResponseEntity<Void> deleteKinshipByCpfAndId(Long userId, Long kinshipId) {
        kinshipService.deleteKinshipByCpfAndId(userId, kinshipId);

        return ResponseEntity.noContent().build();
    }
}
