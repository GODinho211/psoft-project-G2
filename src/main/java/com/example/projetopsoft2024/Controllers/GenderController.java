package com.example.projetopsoft2024.Controllers;

import com.example.projetopsoft2024.Service.GenderService;
import com.example.projetopsoft2024.models.Gender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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

    //@PostMapping()
    //public Gender createGender(@RequestBody Gender gender){
    //    return genderService.createGender(gender);
    //}

    @PostMapping()
    public ResponseEntity<String> createGender(@RequestBody Gender gender){
        Gender createdGender = genderService.createGender(gender);
        if (createdGender != null) {
            return new ResponseEntity<>("Gender created successfully", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Failed to create gender", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping()
    public List<Gender> getGenders(){
        return genderService.getAllGenders();
    }

    //@PutMapping("/{id}")
    //public ResponseEntity<Gender> updateGender(@PathVariable long id, @RequestBody Gender gender) {
    //    try {
    //        Gender updatedGender = genderService.updateGender(id, gender);
    //        return new ResponseEntity<>(updatedGender, HttpStatus.OK);
    //    } catch (RuntimeException e) {
    //        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    //    }
    //}

    //@PutMapping("/{id}")
    //public ResponseEntity<Gender> updateGender(@PathVariable Long id, @RequestBody Gender updatedGender) {
    //    try {
    //        Gender gender = genderService.updateGender(id, updatedGender);
    //        return new ResponseEntity<>(gender, HttpStatus.OK);
    //    } catch (RuntimeException e) {
    //        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    //    }
    //}

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteGender(@PathVariable Long id) {
        String response = genderService.deleteGender(id);
        if (response.equals("Gender deleted")) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }


}