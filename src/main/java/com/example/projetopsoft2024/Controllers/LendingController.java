package com.example.projetopsoft2024.Controllers;


import com.example.projetopsoft2024.Service.LendingService;
import com.example.projetopsoft2024.models.DTO.LendingDTO;
import com.example.projetopsoft2024.models.DTO.ReturnDTO;
import com.example.projetopsoft2024.models.Entitys.Book;
import com.example.projetopsoft2024.models.Entitys.Lending;
import com.example.projetopsoft2024.models.Entitys.User;
import com.example.projetopsoft2024.models.Requests.LendingRequest;
import com.example.projetopsoft2024.models.Requests.ReturnRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api/lendings")
public class LendingController {

    @Autowired
    private LendingService lendingService;


    @PostMapping("/lend")
    public LendingDTO createLending(@RequestBody LendingRequest request) {

        User user = lendingService.findUserById(request.getUserId());
        List<Book> books = lendingService.findBooksByIds(request.getBookIds());

        Lending lending = new Lending(user, books, request.getStartDate(), request.getReturnDate());
        lendingService.saveLending(lending);
        return lending.toLendingDTO();
    }

    @GetMapping("/getAll")
    public List<Lending> getLendings() {
        return lendingService.getAll();
    }

    @GetMapping("/bookId/{bookId}")
    public ResponseEntity<List<Lending>> findLendingByBookId(@PathVariable Long bookId) {
        List<Lending> lending = lendingService.findLendingByBookId(bookId);
        if (lending.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(lending);
        }
    }

    @GetMapping("/userId/{userId}")
    public ResponseEntity<List<Lending>> findLendingByUserId(@PathVariable Long userId) {
        List<Lending> lending = lendingService.findLendingByUserId(userId);
        if (lending.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(lending);
        }
    }


    @PutMapping("/return/{lendingId}")
    public ReturnDTO returnBooks(@PathVariable Long lendingId,@RequestBody ReturnRequest returnRequest) {
        Lending lending = lendingService.findLendingById(lendingId);

        if (lending.getReturnDate() != null) {
            throw new RuntimeException("This book has already been returned.");
        }

        LocalDate returnDate = returnRequest.getReturnDate() != null ? returnRequest.getReturnDate() : LocalDate.now();
        lending.setReturnDate(returnDate);

        lending.fineCalc(lending.getStartDate(), lending.getReturnDate());
        lendingService.saveLending(lending);

        ReturnDTO returnDTO = new ReturnDTO();
        returnDTO.setLendingId(lending.getLendingId());
        returnDTO.setUser(lending.getUser());
        returnDTO.setBooks(lending.getBooks());
        returnDTO.setStartDate(lending.getStartDate());
        returnDTO.setReturnDate(lending.getReturnDate());
        returnDTO.setFine(lending.getFine());


        return returnDTO;

    }
}
