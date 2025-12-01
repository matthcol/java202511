package org.example.commune.repository;


import jakarta.persistence.EntityManager;
import org.example.commune.entity.Commune;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.util.List;

@DataJpaTest
public class DemoRepository {

    @Autowired
    CommuneRepository communeRepository;

    @Autowired
    EntityManager entityManager;

    @Test
    void demoSave(){
        Commune commune = Commune.builder()
                .nomStandard("Toulouse")
                .codeDepartement("31")
                .population(500_000)
                .build();
        communeRepository.saveAndFlush(commune);
        System.out.println(commune);
    }

    @Test
    void demoRead(){
        // hypotheses
        var data = List.of(
                Commune.builder()
                        .nomStandard("Toulouse")
                        .codeDepartement("31")
                        .population(500_000)
                        .build(),
                Commune.builder()
                        .nomStandard("Montauban")
                        .codeDepartement("82")
                        .population(50_000)
                        .build(),
                Commune.builder()
                        .nomStandard("Pau")
                        .codeDepartement("64")
                        .population(77_000)
                        .build()
        );
        communeRepository.saveAllAndFlush(data);

        // vider le cache
        entityManager.clear();

        // lecture
        var optCommune = communeRepository.findById(1L);
        optCommune.ifPresent(
                commune -> System.out.println(commune.getNomStandard())
        );
    }

    @Test
    void demoReadByDept(){
        // hypotheses
        var data = List.of(
                Commune.builder()
                        .nomStandard("Toulouse")
                        .codeDepartement("31")
                        .population(500_000)
                        .build(),
                Commune.builder()
                        .nomStandard("Montauban")
                        .codeDepartement("82")
                        .population(50_000)
                        .build(),
                Commune.builder()
                        .nomStandard("Pau")
                        .codeDepartement("64")
                        .population(77_000)
                        .build(),
                Commune.builder()
                        .nomStandard("Moissac")
                        .codeDepartement("82")
                        .population(12_000)
                        .build()
        );
        communeRepository.saveAllAndFlush(data);

        // vider le cache
        entityManager.clear();
        var communes = communeRepository.findByCodeDepartement("82");
        communes.forEach(System.out::println);
    }
}
