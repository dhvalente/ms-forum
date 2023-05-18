package br.com.forum.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "comments")
public class Comment {

    @Id
    private String id;

    private String content;

    private LocalDate createdAt = LocalDate.now();

    private String authorId;

    private String postId;

    private int numberOfLikes;


}
