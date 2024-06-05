package com.example.projetopsoft2024.models.DTO;


import com.example.projetopsoft2024.models.Book;
import com.example.projetopsoft2024.models.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReturnDTO {

    private Long lendingId;
    private User user;
    private List<Book> books;
    private LocalDate startDate;
    private LocalDate returnDate;
    private float fine;
}
