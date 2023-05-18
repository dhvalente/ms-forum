package br.com.forum.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "posts")
public class Post {
    @Id
    private String id;

    @Size(max = 90 , message = "Maximum allowed characters is 90")
    @NotBlank(message = "Title is required!")
    private String title;

    @NotBlank(message = "Content is required!")
    private String content;

    private LocalDate dateCreated =  LocalDate.now();

    @NotBlank(message = "Author is required!")
    private String authorId;

    @NotBlank(message = "Topic is required!")
    private String topic;
}
