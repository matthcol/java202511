package org.example.commune.entity;

import jakarta.persistence.*;
import lombok.*;

// lombok
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
// JPA (Java Persistence Api) : 1 feature Java/Jakarta EE
@Entity
public class Commune {
    @Id // Primary Key
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long idCommune;

    private String nomStandard;
    private String nomSansPronom;
    private String codeDepartement;
    private int population;
}
