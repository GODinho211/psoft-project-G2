package com.example.projetopsoft2024.models;


import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.ArrayList;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name="book")
public class Book {

    @Id
    @Column(name = "book_id", nullable = false, unique = true, updatable = false)
    private long isbn;

    @Column(name = "title", nullable = false, unique = false, updatable = true)
    private String title;

    @Column(name = "book_description", nullable = true, unique = false, updatable = true)
    private String description;

    @Column(name = "picture")
    private byte[] picture;

    //@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    //@JoinTable(
    //        name = "book_gender",
    //        joinColumns = @JoinColumn(name = "book_id"),
    //        inverseJoinColumns = @JoinColumn(name = "gender_id")
    //)
    //private List<Gender> gender= new ArrayList<>();


    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinTable(
            name = "book_gender",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "gender_id")
    )
    private List<Gender> gender= new ArrayList<>();


    //NAO TENHO A CERTEZA SE É ASSIM
    //@ManyToOne(cascade = CascadeType.ALL)
    //@JoinColumn(name = "gender_id")
    //private Gender gender;



    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "autor_id")
    private Author author;

 //   public Book(String title, String description, List<Gender> gender, Author authorOfBook) {
 //       this.title = title;
 //       this.description = description;
 //       this.gender = gender;
 //       this.author = authorOfBook;
 //   }

    public Book(String title, String description, byte[] picture) {
        this.title = title;
        this.description = description;
        this.picture = picture;
    }

    public void setDescription(String description) {
        if (description.length() > 4096) {
            throw new IllegalArgumentException("Description cannot exceed 4096 characters");
        }
        this.description = description;
    }

    public void setTitle(String title) {
        if (title.length() > 128) {
            throw new IllegalArgumentException("Title cannot exceed 128 characters");
        }
        this.title = title;

    }

    //public void setIsbn(long isbn) {
    //    String isbnStr = Long.toString(isbn);
    //    if (!isValidISBN10(isbnStr) && !isValidISBN13(isbnStr)) {
    //        throw new IllegalArgumentException("ISBN must be in ISBN-10 or ISBN-13 format");
    //    }
    //    this.isbn = isbn;
    //}


    public void setIsbn(String isbn) {
        String isbnStr = isbn.replace("-", "");
        if (!isValidISBN10(isbnStr) && !isValidISBN13(isbnStr)) {
            throw new IllegalArgumentException("ISBN must be in ISBN-10 or ISBN-13 format");
        }
        this.isbn = Long.parseLong(isbnStr);
    }


    private boolean isValidISBN10(String isbn) {
        if (isbn == null || isbn.length() != 10) {
            return false;
        }

        try {
            int total = 0;
            for (int i = 0; i < 9; i++) {
                int digit = Integer.parseInt(isbn.substring(i, i + 1));
                total += ((i + 1) * digit);
            }

            String checksum = Integer.toString(total % 11);
            if ("10".equals(checksum)) {
                checksum = "X";
            }

            return checksum.equals(isbn.substring(9));
        } catch (NumberFormatException nfe) {
            // ISBN is not numeric
            return false;
        }
    }

    private boolean isValidISBN13(String isbn) {
        if (isbn == null || isbn.length() != 13) {
            return false;
        }

        try {
            int total = 0;
            for (int i = 0; i < 12; i += 2) {
                total += Integer.parseInt(isbn.substring(i, i + 1));
            }
            for (int i = 1; i < 12; i += 2) {
                total += Integer.parseInt(isbn.substring(i, i + 1)) * 3;
            }

            int checksum = 10 - (total % 10);
            if (checksum == 10) {
                checksum = 0;
            }

            return checksum == Integer.parseInt(isbn.substring(12));
        } catch (NumberFormatException nfe) {
            // ISBN is not numeric
            return false;
        }
    }

}

