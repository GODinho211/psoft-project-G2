package com.example.projetopsoft2024.Bootstrap;

import com.example.projetopsoft2024.Repositories.GenderRepository;
import com.example.projetopsoft2024.models.Gender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class GenderDataLoader implements CommandLineRunner {
    private final GenderRepository genderRepository;

    @Autowired
    public GenderDataLoader(GenderRepository genderRepository) {
        this.genderRepository = genderRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Inicializar gêneros
        List<Gender> genders = Arrays.asList(
                new Gender("Fiction"),
                new Gender("Non-Fiction"),
                new Gender("Romance"),
                new Gender("Horror"),
                new Gender("Science Fiction"),
                new Gender("Fantasy"),
                new Gender("Mystery"),
                new Gender("Thriller"),
                new Gender("Historical Fiction"),
                new Gender("Biography & Memoir")

                // Adicione mais gêneros conforme necessário
        );

        // Salvar os gêneros no banco de dados
        genderRepository.saveAll(genders);
    }
}
