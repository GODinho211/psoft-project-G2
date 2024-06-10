package com.example.projetopsoft2024.Controllers;


import com.example.projetopsoft2024.Service.GenderService;
import com.example.projetopsoft2024.Service.LendingService;
import com.example.projetopsoft2024.models.Book;
import com.example.projetopsoft2024.models.DTO.LendingDTO;
import com.example.projetopsoft2024.models.DTO.LendingDateDTO;
import com.example.projetopsoft2024.models.DTO.ReturnDTO;
import com.example.projetopsoft2024.models.DTO.Top5UsersDto;
import com.example.projetopsoft2024.models.Entitys.Lending;
import com.example.projetopsoft2024.models.Requests.LendingRequest;
import com.example.projetopsoft2024.models.Requests.ReturnRequest;
import com.example.projetopsoft2024.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("api/lendings")
public class LendingController {

    @Autowired
    private LendingService lendingService;

    @Autowired
    private GenderService genderService;

    @PostMapping("/lend")
    public LendingDTO createLending(@RequestBody LendingRequest request) {

        User user = lendingService.findUserById(request.getUserId());
        List<Book> books = lendingService.findBooksByIds(request.getBookIds());

        Lending lending = new Lending(user,books, request.getStartDate(), request.getReturnDate());
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

    @PutMapping("/returnBook")
    public ReturnDTO returnBooks(@RequestBody ReturnRequest returnRequest) {
        Lending lending = lendingService.findLendingByLendId(returnRequest.getLendingId());// find the lending by the id the user provides
        lending.fineCalc(lending.getStartDate(),returnRequest.getReturnDate()); //sets the fine value
        lending.setReturnDate(returnRequest.getReturnDate());
        lendingService.saveLending(lending);
        return  new ReturnDTO(lending.getLendingId(),lending.getUser(),lending.getBooks(),lending.getStartDate(),returnRequest.getReturnDate(),lending.getFine()); //creates the response body for user to see
    }
    @GetMapping("/overdueLendings")
    public ResponseEntity<List<Lending>> getOverdueLendings() {
        List<Lending> overdueLendings = lendingService.findOverdueLendings();
        return new ResponseEntity<>(overdueLendings, HttpStatus.OK);
    }
    @GetMapping("/AvgLendingPerGender")
    public List<Map<String, Object>> getAvgLendingPerGenrePerDay(@RequestBody LendingDateDTO lendingDateDTO) {
        return lendingService.getAvgLendingPerGenrePerDay(lendingDateDTO.getMonth(), lendingDateDTO.getYear());
    }
    @GetMapping("/top5readers")
    public ResponseEntity<List<Top5UsersDto>> getTopReaders() {
        List<Top5UsersDto> topReaders = lendingService.findTopReaders();
        return new ResponseEntity<>(topReaders, HttpStatus.OK);
    }

    @GetMapping("/average-lending-duration")
    public String getAverageLendingDuration() {
        BigDecimal averageDuration = lendingService.getAverageLendingDuration();
        String message = "Média de Dias por Empréstimo: " + averageDuration.setScale(2, BigDecimal.ROUND_HALF_UP);
        return message;
    }

    }
