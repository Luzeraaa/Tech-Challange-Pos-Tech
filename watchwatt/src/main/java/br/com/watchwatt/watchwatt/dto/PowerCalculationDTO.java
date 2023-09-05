package br.com.watchwatt.watchwatt.dto;

public record PowerCalculationDTO(
        Double powerCalculation
) {
    public PowerCalculationDTO(Double powerCalculation) {
        this.powerCalculation = powerCalculation;
    }

}
