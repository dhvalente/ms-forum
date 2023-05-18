package br.com.forum.model;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "profiles")
public class Profile {

    @Id
    private String id;

    private String gender;

    private String adress;

    private String college;
}
