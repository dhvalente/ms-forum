package br.com.forum.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class PostDTO {

    @Size(max = 90 , message = "Maximum allowed characters is 90")
    @NotBlank(message = "Title is required!")
    private String title;

    @NotBlank(message = "Content is required!")
    private String content;

    @NotBlank(message = "Author is required!")
    private String authorId;

    @NotBlank(message = "Topic is required!")
    private String topic;
}
