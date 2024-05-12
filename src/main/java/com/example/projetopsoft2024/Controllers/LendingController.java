package com.example.projetopsoft2024.Controllers;


import com.example.projetopsoft2024.Service.LendingService;
import com.example.projetopsoft2024.models.Lending;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/lendings")
public class LendingController {

    @Autowired
    private LendingService lendingService;


    @PostMapping("/{bookId}lend{userId}")
    public ResponseEntity<String> lendBooks(@PathVariable Long userId, @PathVariable Long bookId) {

        try {
            boolean success = lendingService.lendBooks(userId, bookId);
            if (success) {
                return ResponseEntity.ok("Livro emprestado com sucesso.");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Falha ao emprestar o livro.");
            }
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/getAll")
    public List<Lending> getLendings(){
        return lendingService.getAll();
    }

    @GetMapping("/bookId/{bookId}")
    public ResponseEntity<List<Lending>> findLendingsByBookId(@PathVariable Long bookId) {
        List<Lending> lendings = lendingService.findLendingByBookId(bookId);
        if (lendings.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(lendings);
        }
    }

    @GetMapping("/userId/{userId}")
    public ResponseEntity<List<Lending>> findLendingByUserId(@PathVariable Long userId) {
        List<Lending> lendings = lendingService.findLendingByUserId(userId);
        if (lendings.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(lendings);
        }
    }
    @PostMapping("/returnBook")
    public ResponseEntity<String> returnBook(@RequestParam Long lendingId) {
        try {
            lendingService.returnBook(lendingId);
            return ResponseEntity.ok("Livro devolvido com sucesso.");
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
