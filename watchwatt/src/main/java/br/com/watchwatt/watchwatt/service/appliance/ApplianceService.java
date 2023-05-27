package br.com.watchwatt.watchwatt.service.appliance;

import br.com.watchwatt.watchwatt.dao.ApplianceRepository;
import br.com.watchwatt.watchwatt.domain.appliance.Appliance;
import br.com.watchwatt.watchwatt.dto.appliance.ApplianceDTO;
import br.com.watchwatt.watchwatt.exception.BadRequestException;
import org.springframework.stereotype.Service;

@Service
public record ApplianceService(
        ApplianceRepository repository
) {

  public Appliance registerAppliance(ApplianceDTO applianceDTO) {
    var appliance = repository.findByName(applianceDTO.name());

    appliance.ifPresent(it -> {
      throw new BadRequestException("appliance already registered");
    });

    return repository.save(new Appliance(applianceDTO));
  }
}
