package com.bezkoder.spring.datajpa.repository;

import com.bezkoder.spring.datajpa.model.Asset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssetRepository extends JpaRepository<Asset, Long> {
}
