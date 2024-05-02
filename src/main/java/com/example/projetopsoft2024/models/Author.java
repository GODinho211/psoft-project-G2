package com.example.projetopsoft2024.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="author")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_author")
    private Long idAuthor;

    @Column(name="author_name")
    private String name;

    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

    public Author(String name, LocalDate dataNascimento) {
        this.name = name;
        this.dataNascimento = dataNascimento;
    }
}
