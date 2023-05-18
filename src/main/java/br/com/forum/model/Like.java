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
@Document(collection = "likes")
public class Like {
    @Id
    private String id;
    private int idAuthor;
    private int comment;
    private int post;

}
