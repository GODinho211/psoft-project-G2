package com.example.projetopsoft2024.models.Requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthorRequest {
    private String name;
    private String bio;
    private MultipartFile photo;
}
