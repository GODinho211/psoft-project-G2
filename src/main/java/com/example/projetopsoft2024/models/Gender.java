package com.example.projetopsoft2024.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;

@Entity
@Table(name = "gender")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Gender {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( name = "gender_id")
    private Long genderId;
    //@Column( name = "book_description")
    @Column( name = "gender_description",nullable = false, unique = false, updatable = false)
    private String description;

    @ManyToMany(mappedBy = "gender",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonIgnore()
    private List<Book> books= new ArrayList<>();

    public Gender(String description) {
        this.description= description;
    }



    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
