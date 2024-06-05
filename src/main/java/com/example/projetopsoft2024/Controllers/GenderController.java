package com.example.projetopsoft2024.Controllers;

import com.example.projetopsoft2024.Service.GenderService;
import com.example.projetopsoft2024.models.Gender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;


@RestController
@RequestMapping("api/gender")
public class GenderController {

    @Autowired
    private GenderService genderService;


    @PostMapping()
    public ResponseEntity<String> createGender(@RequestBody Gender gender) {
        Gender createdGender = genderService.createGender(gender);
        if (createdGender != null) {
            return new ResponseEntity<>("Gender created successfully", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Failed to create gender", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping()
    public List<Gender> getGenders() {
        return genderService.getAllGenders();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteGender(@PathVariable Long id) {
        String response = genderService.deleteGender(id);
        if (response.equals("Gender deleted")) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<Gender>> findByDescription(@RequestParam String description) {
        List<Gender> genders = genderService.findByDescription(description);
        return ResponseEntity.ok(genders);
    }
}