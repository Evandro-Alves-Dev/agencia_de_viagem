package br.com.velg.agencia_de_viagem.repository;

import br.com.velg.agencia_de_viagem.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAllByUsername(String name);

    Optional<User> findByUsername(String name);

}
