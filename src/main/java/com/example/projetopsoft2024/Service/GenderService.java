package com.example.projetopsoft2024.Service;

import com.example.projetopsoft2024.Repositories.GenderRepository;
import com.example.projetopsoft2024.models.Book;
import com.example.projetopsoft2024.models.Gender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    //@Transactional
    //public Gender updateGender(long genderId, Gender updatedGender) {
    //    Optional<Gender> genderOptional = genderRepository.findById(genderId);
    //    if (!genderOptional.isPresent()) {
    //        throw new RuntimeException("Gender not found with id " + genderId);
    //    }

    //    Gender existingGender = genderOptional.get();
    //    existingGender.setDescription(updatedGender.getDescription());
    //    genderRepository.save(existingGender);
    //    return existingGender;
    //}


    //public Gender updateGender(Long id, Gender updatedGender) {
    //    Gender existingGender = genderRepository.findById(id)
    //            .orElseThrow(() -> new RuntimeException("Gender not found with id " + id));

    //    existingGender.setDescription(updatedGender.getDescription());
    //    return genderRepository.save(existingGender);
    //}

    public String deleteGender(Long id) {
        Optional<Gender> optionalGender = genderRepository.findById(id);
        if (optionalGender.isPresent()) {
            genderRepository.deleteById(id);
            return "Gender deleted";
        } else {
            return "Gender not found";
        }
    }
  
    public List<Gender> findByDescription(String description) {
        return genderRepository.findByDescription(description);
    }
}

