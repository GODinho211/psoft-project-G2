package com.example.projetopsoft2024.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

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

    @Version
    private long version;
    @Column(name="author_name",nullable = false)
    private String name;

    @Column(name = "bio",columnDefinition = "TEXT",length = 4096)
    private String bio;

    public Author(String name, String bio) {
        this.name = name;
        this.bio = bio;
    }
    public String getName() {
        return name;
    }
    public Long getVersion() {
        return version;
    }
    public String getBio(){
        return bio;
    }
    private void setName(final String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("'name' is a mandatory attribute of Author");
        }
        if (!name.matches("^[a-zA-Z0-9_-]+$")) {
            throw new IllegalArgumentException("Invalid chracter(s) in 'name', i.e., only alphanumeric are valid");
        }
        this.name = name;
    }
    public void setBio(final String bio) {
        this.bio = bio;
    }
    public void updateName(String newName) {
        this.name = newName;
    }
}
