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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Lend", description = "Endpoints for managing leding's.")
public class LendingController {

    @Autowired
    private LendingService lendingService;

    @Autowired
    private GenderService genderService;

    @Operation(summary = "Get all users")
    @PostMapping("/lend")
    public LendingDTO createLending(@RequestBody LendingRequest request) {

        User user = lendingService.findUserById(request.getUserId());

        List<Book> books = lendingService.findBooksByIds(request.getBookIds());

        Lending lending = new Lending(user,books, request.getStartDate(), request.getReturnDate());
        lendingService.saveLending(lending);
        return lending.toLendingDTO();
    }

    @Operation(summary = "Get all the Lendings")
    @GetMapping("/getAll")
    public List<Lending> getLendings() {
        return lendingService.getAll();
    }
    @Operation(summary = "Get all users")
    @GetMapping("/bookId/{bookId}")
    public ResponseEntity<List<Lending>> findLendingByBookId(@PathVariable Long bookId) {
        List<Lending> lending = lendingService.findLendingByBookId(bookId);
        if (lending.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(lending);
        }
    }
    @Operation(summary = "Get lend by User Id")
    @GetMapping("/userId/{userId}")
    public ResponseEntity<List<Lending>> findLendingByUserId(@PathVariable Long userId) {
        List<Lending> lending = lendingService.findLendingByUserId(userId);
        if (lending.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(lending);
        }
    }

    @Operation(summary = "Return a Book, a fine could be applied")
    @PutMapping("/returnBook")
    public ReturnDTO returnBooks(@RequestBody ReturnRequest returnRequest) {
        Lending lending = lendingService.findLendingByLendId(returnRequest.getLendingId());// find the lending by the id the user provides
        lending.fineCalc(lending.getStartDate(),returnRequest.getReturnDate()); //sets the fine value
        lending.setReturnDate(returnRequest.getReturnDate());
        lendingService.saveLending(lending);
        return  new ReturnDTO(lending.getLendingId(),lending.getUser(),lending.getBooks(),lending.getStartDate(),returnRequest.getReturnDate(),lending.getFine()); //creates the response body for user to see
    }
    @Operation(summary = "Get Overduo Lends")
    @GetMapping("/overdueLendings")
    public ResponseEntity<List<Lending>> getOverdueLendings() {
        List<Lending> overdueLendings = lendingService.findOverdueLendings();
        return new ResponseEntity<>(overdueLendings, HttpStatus.OK);
    }

    @Operation(summary = "Get Average Lends Per Gender on given date")
    @GetMapping("/AvgLendingPerGender")
    public List<Map<String, Object>> getAvgLendingPerGenrePerDay(@RequestBody LendingDateDTO lendingDateDTO) {
        return lendingService.getAvgLendingPerGenrePerDay(lendingDateDTO.getMonth(), lendingDateDTO.getYear());
    }

    @Operation(summary = "Get Top 5 Readers")
    @GetMapping("/top5readers")
    public ResponseEntity<List<Top5UsersDto>> getTopReaders() {
        List<Top5UsersDto> topReaders = lendingService.findTopReaders();
        return new ResponseEntity<>(topReaders, HttpStatus.OK);
    }

    @Operation(summary = "Get Average Lend Duration")
    @GetMapping("/AvgLendDuration")
    public String getAverageLendingDuration() {
        BigDecimal averageDuration = lendingService.getAverageLendingDuration();
        String message = "Média de Dias por Empréstimo: " + averageDuration.setScale(2, BigDecimal.ROUND_HALF_UP);
        return message;
    }

    @Operation(summary = "Get Number Of Lendings Per gender in the last 12 month's")
    @GetMapping("/lendings-per-month-genre")
    public List<Map<String, Object>> getLendingsPerMonthAndGenreLast12Months() {
        return lendingService.getLendingsPerMonthAndGenreLast12Months();
    }

    }
