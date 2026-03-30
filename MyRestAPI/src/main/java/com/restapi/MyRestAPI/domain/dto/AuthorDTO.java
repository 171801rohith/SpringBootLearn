package com.restapi.MyRestAPI.domain.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthorDTO {
    private Integer id;

    @NotBlank(message = "Author name is required and cannot be blank.")
    private String name;

    @Min(value = 0, message = "Age cannot be less than 0")
    private Integer age;
}
