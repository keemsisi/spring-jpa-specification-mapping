package com.example.demo.javacodetest.services;

import com.example.demo.javacodetest.entities.ProviderDataEntity;
import com.example.demo.javacodetest.repositories.ProviderDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProviderDataEntityService {
    @Autowired
    private ProviderDataRepository providerDataRepository ;

    public Optional<ProviderDataEntity> findProviderDataById(@NonNull final Integer providerId)  {
        Optional<ProviderDataEntity> entityOptional = providerDataRepository.findById(providerId);
        return entityOptional;
    }


    public Boolean saveData(@NonNull ProviderDataEntity providerDataEntity) {
        providerDataRepository.save(providerDataEntity);
        return true ;
    }


    public List<ProviderDataEntity> getAllData(Integer limit)  {
        List<ProviderDataEntity> entityOptional = providerDataRepository.findAll();
        return entityOptional.stream().limit(limit).collect(Collectors.toCollection(ArrayList::new));
    }

}
