package br.com.forum.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommentDTO {

    @NotBlank(message = "Content is required!")
    private String content;

    @NotBlank(message = "Author is required!")
    private String authorId;

    @NotBlank(message = "postId is required!")
    private String postId;
}
