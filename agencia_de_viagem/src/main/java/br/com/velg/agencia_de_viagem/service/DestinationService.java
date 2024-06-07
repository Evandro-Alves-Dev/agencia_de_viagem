package br.com.velg.agencia_de_viagem.service;

import br.com.velg.agencia_de_viagem.dtos.DestinationDTO;
import br.com.velg.agencia_de_viagem.entities.Destination;
import br.com.velg.agencia_de_viagem.repository.DestinationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DestinationService {

    private DestinationRepository destinationRepository;

    DestinationService() {
        this.destinationRepository = destinationRepository;
    }

    public DestinationDTO insert(DestinationDTO destinationDTO) {
        Destination destination = new Destination();
        copyDtoToEntity(destinationDTO, destination);
        destination = destinationRepository.save(destination);
        return new DestinationDTO(destination);
    }

    public List<DestinationDTO> findAll() {
        var list = destinationRepository.findAll();
        return list.stream().map(destination ->
                new DestinationDTO(destination)).toList();
    }


    public List<DestinationDTO> searchByNameOrLocation(String nameDestination, String location) throws Exception {
        if (!nameDestination.isBlank() && !location.isBlank()) {
            throw new Exception("É necessário informar um nome OU uma localização");
        } else if (nameDestination.isBlank()) {
            return destinationRepository.findByLocationContainingIgnoreCase(location).stream()
                    .map(locations ->
                            new DestinationDTO(locations)).toList();
        } else if (location.isBlank()) {
            return destinationRepository.findByNameDestinationContainingIgnoreCase(nameDestination).stream()
                    .map(name ->
                            new DestinationDTO(name)).toList();
        } else {
            throw new Exception("É necessário informar um nome ou localização");
        }
    }

//
//    public Optional<Destino> buscarPorId(Long id) {
//        return destinationRepository.findById(id);
//    }
//
//    public void excluir(Long id) {
//        destinationRepository.deleteById(id);
//    }
//
//    public Destino avaliar(Long id, int nota) {
//        Destino destino = destinationRepository.findById(id).orElseThrow(() -> new RuntimeException("Destino não encontrado"));
//        double novaMedia = ((destino.getAvaliacaoMedia() * destino.getNumeroAvaliacoes()) + nota) / (destino.getNumeroAvaliacoes() + 1);
//        destino.setAvaliacaoMedia(novaMedia);
//        destino.setNumeroAvaliacoes(destino.getNumeroAvaliacoes() + 1);
//        return destinationRepository.save(destino);
//    }

    private void copyDtoToEntity(DestinationDTO destinationDTO, Destination destination) {
        destination.setNameDestination(destinationDTO.getNameDestination());
        destination.setCountry(destinationDTO.getCountry());
        destination.setState(destinationDTO.getState());
        destination.setLocation(destinationDTO.getLocation());
    }
}
