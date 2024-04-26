package com.example.projetopsoft2024.Controllers;

import com.example.projetopsoft2024.Service.GenderService;
import com.example.projetopsoft2024.models.Gender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



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
}