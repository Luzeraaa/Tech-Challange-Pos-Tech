package br.com.watchwatt.watchwatt.dto.appliance;

import br.com.watchwatt.watchwatt.domain.appliance.Appliance;

public record ListApplianceDTO(

        Long id,

        String name,

        String model,

        Integer power

) {

    public ListApplianceDTO(Appliance appliance){
        this(appliance.getId(), appliance.getName(), appliance.getModel(), appliance.getPower());
    }
}
