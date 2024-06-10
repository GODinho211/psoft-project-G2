package com.example.projetopsoft2024.models.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthorDTO {
    private Long idAuthor;
    private String name;
    private String bio;
    private Long lendCount;
}
