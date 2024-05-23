package com.example.projetopsoft2024.models.Requests;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class LendingRequest {

    private Long userId;
    private List<Long> bookIds;
    private LocalDate startDate;
    private LocalDate returnDate;

}