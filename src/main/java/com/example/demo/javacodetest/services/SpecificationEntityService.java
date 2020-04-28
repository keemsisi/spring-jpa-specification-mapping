package com.example.demo.javacodetest.services;


import com.sun.istack.NotNull;
import com.example.demo.javacodetest.entities.SpecificationEntity;
import com.example.demo.javacodetest.repositories.SpecificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SpecificationEntityService {
    @Autowired
    private SpecificationRepository specificationRepository ;

    /**
     *
     * @param providerId The specification provider Id
     * @return
     */
    public Optional<SpecificationEntity> findSpecificationEntityByProviderId(@NonNull final Integer providerId){
        return specificationRepository.findById(providerId);
    }

    public SpecificationEntity createNewSpecificationProvider(@NotNull final SpecificationEntity specificationEntity){
        return specificationRepository.save(specificationEntity);
    }

}
