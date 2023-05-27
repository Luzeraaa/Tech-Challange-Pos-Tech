package br.com.watchwatt.watchwatt.controller;

import br.com.watchwatt.watchwatt.domain.appliance.Appliance;
import br.com.watchwatt.watchwatt.dto.appliance.ApplianceDTO;
import br.com.watchwatt.watchwatt.service.appliance.ApplianceService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import static br.com.watchwatt.watchwatt.controller.Constant.X_API_VERSION_1;

@RestController
@RequestMapping(path = "/appliances")
public record AppliancesController(
        ApplianceService service
) {

  private static final String APPLIANCE_ID_PATH = "/appliances";

  @PostMapping(headers = X_API_VERSION_1)
  public ResponseEntity<Appliance> registerAppliance(@RequestBody @Valid ApplianceDTO applianceDTO, UriComponentsBuilder uriBuilder) {
    var appliance = service.registerAppliance(applianceDTO);

    return ResponseEntity
            .created(uriBuilder.path(APPLIANCE_ID_PATH).buildAndExpand(appliance.getId()).toUri())
            .body(appliance);
  }
}
