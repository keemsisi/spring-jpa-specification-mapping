package com.example.demo.javacodetest.restcontrollers;


import com.example.demo.javacodetest.entities.SpecificationEntity;
import com.example.demo.javacodetest.services.SpecificationEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@RestController
public class CreateSpecificationController {
    //timestamp , integer and string
    @Autowired
    private SpecificationEntityService specificationEntityService;

    @RequestMapping(value = "/CreateProviderSpecification" , method = RequestMethod.POST , produces = "application/json")
    public ResponseEntity<String> create(@RequestBody final Map<String , Object> providerDataSpecification) throws IllegalArgumentException {

        try {

            Set<String> set = providerDataSpecification.keySet();

           if  ( set.contains("providerId") && set.contains("fields")) {

               SpecificationEntity specificationEntity = new SpecificationEntity();

               specificationEntity.setProviderId( (Integer) providerDataSpecification.get("providerId"));

               if (providerDataSpecification.get("fields")  instanceof  ArrayList<?> ) {

                   ArrayList<String> fields = (ArrayList<String>) providerDataSpecification.get("fields");

                   specificationEntity.setSpecificationFields(fields);

                   specificationEntityService.createNewSpecificationProvider(specificationEntity);

               }else {
                   ResponseEntity.of(Optional.of("fields has to be strings data type"));
               }

           }else {

               ResponseEntity.of(Optional.of("Request body received does not have providerId or fields id"));

           }

        } catch (Exception e) {

            ResponseEntity.status(HttpStatus.BAD_REQUEST);

            return ResponseEntity.of(Optional.of("failed to create your specfication ::: " + e.getMessage()));

        }

        return ResponseEntity.ok("Specification created successfully");

    }

}
