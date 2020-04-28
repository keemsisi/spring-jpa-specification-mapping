package com.example.demo.javacodetest.services;


import com.example.demo.javacodetest.commons.exceptions.NoProviderDataEntityFoundException;
import com.example.demo.javacodetest.entities.ProviderDataEntity;
import com.example.demo.javacodetest.repositories.ProviderDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.security.NoSuchProviderException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProviderDataEntityService {
    @Autowired
    private ProviderDataRepository providerDataRepository ;

    /**
     * ○ eqc: equalsIgnoreCase (string)
     * ○ eq: equalsTo (timestamp and integer)
     * ○ lt: lessThan timestamp and integer)
     * ○ gt: greaterThan )any field timestamp and integer)
     */

    public Optional<ProviderDataEntity> findProviderDataById(@NonNull final Integer providerId) throws NoProviderDataEntityFoundException {
        Optional<ProviderDataEntity> entityOptional = providerDataRepository.findById(providerId);
        return entityOptional;
    }


    public ProviderDataEntity saveData(@NonNull ProviderDataEntity providerDataEntity) throws NoProviderDataEntityFoundException {
        ProviderDataEntity entity = providerDataRepository.save(providerDataEntity);
        return entity ;
    }


    public List<ProviderDataEntity> getAllData(Integer limit)  {
        List<ProviderDataEntity> entityOptional = providerDataRepository.findAll();
        return entityOptional.stream().limit(limit).collect(Collectors.toCollection(ArrayList::new));
    }


    public ProviderDataEntity getProviderDataLessThan(@NonNull final Integer providerId) throws NoProviderDataEntityFoundException {
        Optional<ProviderDataEntity> entityOptional = providerDataRepository.findById(providerId);
        if (entityOptional.isPresent()) return entityOptional.get();
        else throw new NoProviderDataEntityFoundException(String.format("The providerId %s does not exits!", providerId));
    }



    //greater than
    public ProviderDataEntity getProviderDataGreaterThan(@NonNull final Integer providerId) throws NoSuchProviderException {
        Optional<ProviderDataEntity> entityOptional = providerDataRepository.findById(providerId);
        if (entityOptional.isPresent()) return entityOptional.get();
        else throw new NullPointerException(String.format("The providerId %s does not exits!", providerId));
    }

}
