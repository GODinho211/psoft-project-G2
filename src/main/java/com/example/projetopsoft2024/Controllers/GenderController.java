package com.example.projetopsoft2024.Controllers;

import com.example.projetopsoft2024.Service.GenderService;
import com.example.projetopsoft2024.models.Gender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;



@RestController
@RequestMapping("api/gender")
public class GenderController {

    @Autowired
    private GenderService genderService;

    // existing endpoints...

    @PostMapping()
    public Gender createGender(@RequestBody Gender gender){
        return genderService.createGender(gender);
    }

    @GetMapping()
    public List<Gender> getGenders(){
        return genderService.getAllGenders();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Gender> updateGender(@PathVariable long id, @RequestBody Gender gender) {
        try {
            Gender updatedGender = genderService.updateGender(id, gender);
            return new ResponseEntity<>(updatedGender, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}