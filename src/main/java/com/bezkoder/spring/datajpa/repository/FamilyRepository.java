package com.bezkoder.spring.datajpa.repository;

import com.bezkoder.spring.datajpa.dto.FamilyDto;
import com.bezkoder.spring.datajpa.model.Family;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FamilyRepository extends JpaRepository<Family, Long> {
    Optional<Family> findByFamilyName(String familyName);

    Optional<Family> findByFamilyId(Long familyId);
}
