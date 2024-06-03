package com.example.projetopsoft2024.models.Requests;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;



@Getter
@Setter
public class ReturnRequest {

    private Long lendingId;
    private LocalDate returnDate;
}
