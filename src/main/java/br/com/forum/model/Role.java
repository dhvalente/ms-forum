package br.com.forum.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.security.core.GrantedAuthority;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Role implements GrantedAuthority {

    @Id
    private String id;

    private String nameRole;


    public Role(String nameRole){
        this.nameRole = nameRole;
    }

    @Override
    public String getAuthority() {
        return nameRole;
    }


}