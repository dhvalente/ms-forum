package br.com.forum.dtos;

import br.com.forum.model.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class UserDTO {

    @NotEmpty(message = "Campo nome não pode ser vazio.")
    private String name;

    @NotEmpty(message = "{email.obrigatorio}")
    @Email(message = "{email.invalido}")
    private String email;

    @NotEmpty(message = "{senha.obrigatorio}")
    private String password;

    @NotEmpty(message = "{role.obrigatorio}")
    private String role;
}
