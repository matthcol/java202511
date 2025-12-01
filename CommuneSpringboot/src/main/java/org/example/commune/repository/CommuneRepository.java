package org.example.commune.repository;


import org.example.commune.entity.Commune;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface CommuneRepository extends JpaRepository<Commune, Long> {

    @Query("""
    SELECT c 
    FROM Commune c 
    WHERE c.codeDepartement = :departement
    ORDER BY c.nomSansPronom
    """)
    List<Commune> findByCodeDepartement(String departement);
}
