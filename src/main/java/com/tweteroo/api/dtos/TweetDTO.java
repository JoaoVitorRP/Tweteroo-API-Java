package com.tweteroo.api.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class TweetDTO {
    @NotNull
    private Long userId;

    @NotBlank(message = "Text cannot be blank!")
    @Size(max = 280, message = "Maximum length for text is 280 characters!")
    private String text;
}
