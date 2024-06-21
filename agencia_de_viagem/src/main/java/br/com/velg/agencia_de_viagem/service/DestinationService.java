package br.com.velg.agencia_de_viagem.service;

import br.com.velg.agencia_de_viagem.dtos.DestinationDTO;
import br.com.velg.agencia_de_viagem.entities.Destination;
import br.com.velg.agencia_de_viagem.repository.DestinationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class DestinationService {

    private final DestinationRepository destinationRepository;

    DestinationService(DestinationRepository destinationRepository) {
        this.destinationRepository = destinationRepository;
    }

    public DestinationDTO insert(DestinationDTO destinationDTO) {
        Destination destination = new Destination();
        copyDtoToEntityDestination(destinationDTO, destination);
        destination = destinationRepository.save(destination);
        return new DestinationDTO(destination);
    }

    public List<DestinationDTO> findAll() {
        var list = destinationRepository.findAll();
        return list.stream().map(destination ->
                new DestinationDTO(destination)).toList();
    }


    public List<DestinationDTO> searchByNameOrLocation(String nameDestination, String location) throws Exception {
        if (nameDestination != null && location != null) {
            throw new Exception("É necessário informar apenas um nome OU uma localização");
        } else if (nameDestination == null) {
            return destinationRepository.findByLocationContainingIgnoreCase(location).stream()
                    .map(locations ->
                            new DestinationDTO(locations)).toList();
        } else if (location == null) {
            return destinationRepository.findByNameDestinationContainingIgnoreCase(nameDestination).stream()
                    .map(name ->
                            new DestinationDTO(name)).toList();
        } else {
            throw new Exception("É necessário informar ao mesno um nome OU uma localização");
        }
    }

    public DestinationDTO findById(Long id) {
       Destination destination = destinationRepository.findById(id).
               orElseThrow(() -> new NoSuchElementException("Destino não encontrado"));
       return new DestinationDTO(destination);
    }

    public void delete(Long id) {
        destinationRepository.deleteById(id);
    }

    public DestinationDTO check(Long id, float evaluation) {
        delimiterMaxEvaluation(evaluation);
        Destination destination = destinationRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Destino não encontrado"));

        float newAverage = ((evaluation + destination.getEvaluation()) / countEvaluationByuser(id));
        newAverage = (float) (Math.round(newAverage * Math.pow(10, 2)) / Math.pow(10, 2));
        destination.setEvaluation(newAverage);
        destinationRepository.save(destination);
        return new DestinationDTO(destination);
    }

    private void copyDtoToEntityDestination(DestinationDTO destinationDTO, Destination destination) {
        destination.setNameDestination(destinationDTO.getNameDestination());
        destination.setCountry(destinationDTO.getCountry());
        destination.setState(destinationDTO.getState());
        destination.setLocation(destinationDTO.getLocation());
        destination.setEvaluation(delimiterMaxEvaluation(destinationDTO.getEvaluation()));
        destination.setCountEvaluation(1);
    }

    private float delimiterMaxEvaluation(float evaluation) {
        if (evaluation < 1.0f || evaluation > 10.0f) {
           throw new IllegalArgumentException("A avaliação mínima é 1.0 e máxima é 10.0");
        }
        return evaluation;
    }

    private int countEvaluationByuser(Long id) {
        Optional<Destination> destination = destinationRepository.findById(id);
        destination.get().setCountEvaluation(destination.get().getCountEvaluation() + 1);
        return destination.get().getCountEvaluation();
    }
}
