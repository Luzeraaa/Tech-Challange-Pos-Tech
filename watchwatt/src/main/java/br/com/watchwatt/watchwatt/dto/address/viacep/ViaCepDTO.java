package br.com.watchwatt.watchwatt.dto.address.viacep;

import com.fasterxml.jackson.annotation.JsonAlias;

public record ViaCepDTO(
        @JsonAlias(CEP)
        String zipCode,
        @JsonAlias(LOGRADOURO)
        String street,
        @JsonAlias(BAIRRO)
        String neighborhood,
        @JsonAlias(LOCALIDADE)
        String city,
        @JsonAlias(UF)
        String state
) {
  private static final String CEP = "cep";
  private static final String LOGRADOURO = "logradouro";
  private static final String BAIRRO = "bairro";
  private static final String LOCALIDADE = "localidade";
  private static final String UF = "uf";
}
