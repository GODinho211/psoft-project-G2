package com.example.projetopsoft2024.models.Requests;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;



@Getter
@Setter
public class ReturnRequest {


    private LocalDate returnDate;
}
// have to add the lending id so we can post wihtout the url