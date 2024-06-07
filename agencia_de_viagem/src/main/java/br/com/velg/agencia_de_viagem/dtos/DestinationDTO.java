package br.com.velg.agencia_de_viagem.dtos;

import br.com.velg.agencia_de_viagem.entities.Destination;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DestinationDTO {

    private String country;
    private String state;
    private String nameDestination;
    private String location;

    public DestinationDTO(Destination destination) {
        this.country = destination.getCountry();
        this.state = destination.getState();
        this.nameDestination = destination.getNameDestination();
        this.location = destination.getLocation();
    }
}
