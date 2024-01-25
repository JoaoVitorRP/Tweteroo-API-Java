package com.tweteroo.api.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserDTO {
    @NotBlank(message = "Avatar cannot be blank!")
    private String avatar;

    @NotBlank(message = "Username cannot be blank!")
    @Size(max = 100, message = "Maximum length for username is 100 characters!")
    private String username;
}
