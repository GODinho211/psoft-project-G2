package com.example.projetopsoft2024.Service;


import com.example.projetopsoft2024.Repositories.BookRepository;
import com.example.projetopsoft2024.Repositories.GenderRepository;
import com.example.projetopsoft2024.Repositories.LendingRepository;
import com.example.projetopsoft2024.Repositories.UserRepository;
import com.example.projetopsoft2024.models.Book;
import com.example.projetopsoft2024.models.DTO.Top5UsersDto;
import com.example.projetopsoft2024.models.Entitys.Lending;
import com.example.projetopsoft2024.models.Gender;
import com.example.projetopsoft2024.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.util.*;

import java.util.stream.Collectors;

@Service
public class LendingService {

    private final LendingRepository lendingRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final GenderRepository genderRepository;

    @Autowired
    public LendingService(LendingRepository lendingRepository, UserRepository userRepository, BookRepository bookRepository, GenderRepository genderRepository) {
        this.lendingRepository = lendingRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
        this.genderRepository = genderRepository;
    }

    public Lending findLendingByLendId(long lendingId){
        return lendingRepository.findById(lendingId).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User findUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public List<Book> findBooksByIds(List<Long> bookIds) {
        return bookRepository.findAllById(bookIds);
    }

    public void saveLending(Lending lending) {
        lendingRepository.save(lending);
    }
    public List<Lending> getAll() {
        return lendingRepository.findAll();
    }

    public List<Lending> findLendingByBookId(Long bookId) {
        return lendingRepository.findLendingByBookId(bookId);
    }

    public List<Lending> findLendingByUserId(Long userId) {
        return lendingRepository.findLendingByUserId(userId);
    }


    public List<Lending> findOverdueLendings() {
        return lendingRepository.findAll().stream()
                .filter(lending -> lending.getReturnDate() != null)
                .filter(lending -> lending.getReturnDate().isAfter(lending.getStartDate().plusDays(Lending.MAX_LOAN_PERIOD)))
                .sorted(Comparator.comparingLong(Lending::getDaysOverdue).reversed())
                .collect(Collectors.toList());
    }

    public List<Map<String, Object>> getAvgLendingPerGenrePerDay(int month, int year) {
        List<Object[]> results = lendingRepository.countLendingsPerGenre(month, year);
        List<Map<String, Object>> response = new ArrayList<>();

        int daysInMonth = YearMonth.of(year, month).lengthOfMonth();

        for (Object[] result : results) {
            Map<String, Object> map = new HashMap<>();
            map.put("description", result[0]);

            double avgLendings = ((Number) result[1]).doubleValue() / daysInMonth;// calcula a media dividindo o numero de emprestimos do genero pelo numero de dias no mes
            String formattedAvgLendings = String.format("%.2f", avgLendings); // apenas 2 casas decimias, mas passou para string

            map.put("avg",formattedAvgLendings);
            response.add(map);
        }

        return response;
    }

    public BigDecimal getAverageLendingDuration() {
        List<Lending> lendings = lendingRepository.findAll();
        long totalDays = lendings.stream()
                .mapToLong(Lending::getDaysOverdue)
                .sum();
        long totalLendings = lendings.size();

        if (totalLendings == 0) {
            return BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
        }

        BigDecimal averageDuration = BigDecimal.valueOf(totalDays)
                .divide(BigDecimal.valueOf(totalLendings), 2, RoundingMode.HALF_UP);
        return averageDuration;
    }



    public List<Top5UsersDto> findTopReaders() {
        // Group lendings by user and count the number of lendings for each user
        Map<User, Long> lendingCounts = lendingRepository.findAll().stream()
                .collect(Collectors.groupingBy(Lending::getUser, Collectors.counting()));

        // Sort users based on the number of lendings (books borrowed) in descending order
        List<Top5UsersDto> topReaders = lendingCounts.entrySet().stream()
                .sorted(Map.Entry.<User, Long>comparingByValue().reversed())
                .limit(5) // Get top 5 readers
                .map(Map.Entry::getKey)
                .map(User::toTop5UsersDto) // Convert User entity to Top5UsersDto
                .collect(Collectors.toList());

        return topReaders;
    }

    public List<Map<String, Object>> getLendingsPerMonthAndGenreLast12Months() {
        LocalDate oneYearAgo = LocalDate.now().minusYears(1);
        List<Lending> lendings = lendingRepository.findAllLendingsSince(oneYearAgo);
        Map<String, Map<String, Long>> genreMonthCountMap = new HashMap<>();

        for (Lending lending : lendings) {
            LocalDate startDate = lending.getStartDate();
            String monthYear = startDate.getMonth() + "-" + startDate.getYear();
            for (Book book : lending.getBooks()) {
                for (Gender genre : book.getGender()) {
                    genreMonthCountMap
                            .computeIfAbsent(genre.getDescription(), k -> new HashMap<>())
                            .merge(monthYear, 1L, Long::sum);
                }
            }
        }

        List<Map<String, Object>> response = new ArrayList<>();
        for (Map.Entry<String, Map<String, Long>> genreEntry : genreMonthCountMap.entrySet()) {
            String genre = genreEntry.getKey();
            for (Map.Entry<String, Long> monthEntry : genreEntry.getValue().entrySet()) {
                String[] monthYear = monthEntry.getKey().split("-");
                Month month = Month.valueOf(monthYear[0]);
                int year = Integer.parseInt(monthYear[1]);

                Map<String, Object> map = new HashMap<>();
                map.put("genre", genre);
                map.put("month", month.getValue());
                map.put("year", year);
                map.put("count", monthEntry.getValue());
                response.add(map);
            }
        }

        return response.stream()
                .sorted((m1, m2) -> {
                    int yearCompare = ((Integer) m1.get("year")).compareTo((Integer) m2.get("year"));
                    if (yearCompare != 0) {
                        return yearCompare;
                    }
                    return ((Integer) m1.get("month")).compareTo((Integer) m2.get("month"));
                })
                .collect(Collectors.toList());
    }


    public Lending findLendingById(Long lendingId) {
        return lendingRepository.findById(lendingId).orElse(null);
    }
}