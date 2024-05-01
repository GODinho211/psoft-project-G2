package com.example.projetopsoft2024.Service;

import com.example.projetopsoft2024.Repositories.GenderRepository;
import com.example.projetopsoft2024.models.Book;
import com.example.projetopsoft2024.models.Gender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GenderService {

    @Autowired
    private GenderRepository genderRepository;

    public Gender createGender(Gender gender) {
        return genderRepository.save(gender);
    }

    public List<Gender> getAllGenders() {
    return genderRepository.findAll();
    }
}

