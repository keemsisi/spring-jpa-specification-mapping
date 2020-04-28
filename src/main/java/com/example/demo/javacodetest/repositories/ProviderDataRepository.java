package com.example.demo.javacodetest.repositories;

import com.example.demo.javacodetest.entities.ProviderDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProviderDataRepository extends JpaRepository<ProviderDataEntity, Integer> {
}
