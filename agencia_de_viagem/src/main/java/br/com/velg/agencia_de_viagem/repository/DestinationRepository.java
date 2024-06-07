package br.com.velg.agencia_de_viagem.repository;

import br.com.velg.agencia_de_viagem.entities.Destination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DestinationRepository extends JpaRepository<Destination, Long> {
    List<Destination> findByNameDestinationContainingIgnoreCase(String nameDestination);

    List<Destination> findByLocationContainingIgnoreCase(String location);
}
