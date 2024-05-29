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
                new Gender("Ficção"),
                new Gender("Não Ficção"),
                new Gender("Romance")
                // Adicione mais gêneros conforme necessário
        );

        // Salvar os gêneros no banco de dados
        genderRepository.saveAll(genders);
    }
}
