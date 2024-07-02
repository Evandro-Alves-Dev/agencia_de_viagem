package br.com.velg.agencia_de_viagem.dtos;

import br.com.velg.agencia_de_viagem.entities.User;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO implements Serializable {

    private Long idUser;

    @NotBlank(message = "Campo obrigatório")
    private String username;

    @NotBlank(message = "Campo obrigatório")
    private String password;

    @NotBlank(message = "Campo obrigatório")
    private String roleType;

    public UserDTO(User entity) {
        idUser = entity.getIdUser();
        username = entity.getUsername();
        password = entity.getPassword();
        roleType = entity.getRoleType();
    }
}

