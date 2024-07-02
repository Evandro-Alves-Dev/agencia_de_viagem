package br.com.velg.agencia_de_viagem.service;

import br.com.velg.agencia_de_viagem.dtos.UserDTO;
import br.com.velg.agencia_de_viagem.entities.User;
import br.com.velg.agencia_de_viagem.exceptions.ResourceNotFoundException;
import br.com.velg.agencia_de_viagem.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //TODO: Implementar o método findAll
    @Transactional(readOnly = true)
    public List<UserDTO> findAll() {
        List<User> users = userRepository.findAll();
        return users.stream().map(UserDTO::new).collect(Collectors.toList());
    }

    //TODO: Implementar o método findById
    @Transactional(readOnly = true)
    public UserDTO findById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Id " + id + " não encontrado"));
        return new UserDTO(user);
    }

    @Transactional
    public UserDTO insert(UserDTO userDTO) {
        User user = new User();
        copyDtoToEntity(userDTO, user);
        user = userRepository.save(user);
        return new UserDTO(user);
    }

    @Transactional
    public UserDTO update(Long id, UserDTO userDTO) {
        try {
            var entity = userRepository.getOne(id);
            copyDtoToEntity(userDTO, entity);
            entity = userRepository.save(entity);
            return new UserDTO(entity);
        } catch (EntityNotFoundException e ) {
            throw new ResourceNotFoundException("Id " + id + " não encontrado");
        }
    }

    //TODO: Implementar o método delete
    @Transactional
    public void delete(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("Id " + id + " não encontrado");
        }
        userRepository.deleteById(id);
    }

    private void copyDtoToEntity(UserDTO userDTO, User user) {
        user.setUsername(userDTO.getUsername());
        user.setPassword(new BCryptPasswordEncoder().encode(userDTO.getPassword()));
        user.setRoleType(userDTO.getRoleType());
    }

    public Optional<User> getUserByUsername(String username) {
        Optional<User> users = userRepository.findByUsername(username);
        return users;
    }
}

