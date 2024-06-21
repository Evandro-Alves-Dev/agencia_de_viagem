package br.com.velg.agencia_de_viagem.dtos;

import br.com.velg.agencia_de_viagem.entities.Destination;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DestinationDTO {

    private Long id;
    private String country;
    private String state;
    private String nameDestination;
    private String location;
    private float evaluation;

    public DestinationDTO(Destination destination) {
        this.id = destination.getId();
        this.country = destination.getCountry();
        this.state = destination.getState();
        this.nameDestination = destination.getNameDestination();
        this.location = destination.getLocation();
        this.evaluation = destination.getEvaluation();
    }
}
