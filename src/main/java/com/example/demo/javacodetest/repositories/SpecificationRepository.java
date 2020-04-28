package com.example.demo.javacodetest.repositories;

import com.example.demo.javacodetest.entities.SpecificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpecificationRepository  extends JpaRepository<SpecificationEntity, Integer> {
}
