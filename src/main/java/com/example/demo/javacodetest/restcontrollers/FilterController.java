package com.example.demo.javacodetest.restcontrollers;


import com.example.demo.javacodetest.entities.ProviderDataEntity;
import com.example.demo.javacodetest.entities.SpecificationEntity;
import com.example.demo.javacodetest.services.ProviderDataEntityService;
import com.example.demo.javacodetest.services.SpecificationEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;


public class FilterController {


    @Autowired
    private SpecificationEntityService specificationService;
    @Autowired
    private ProviderDataEntityService providerDataEntityService;


    @RequestMapping(value = "/filter/{providerId}", method = RequestMethod.GET)
    public ResponseEntity<List<Map<String, Object>>> filterProviderData(@RequestParam final String  providerId , @RequestParam Map<String , String > queryParams) throws Exception {
        return ResponseEntity.ok(filterProviderDataHandler(providerId, queryParams ));
    }


    private List<Map<String, Object>> filterProviderDataHandler(

            @NonNull String providerId,

            @NonNull Map<String, String> queryMultiValueMap
            ) throws Exception {


        Optional<SpecificationEntity> specificationEntityOptional = specificationService.findSpecificationEntityByProviderId(providerId);

        if (!specificationEntityOptional.isPresent()) {

            ResponseEntity.status(HttpStatus.NO_CONTENT);

            throw new Exception("The providerId provided does not exists!");

        } else {

            Optional<ProviderDataEntity> providerDataEntity = providerDataEntityService.findProviderDataById(providerId);

            List<Map<String, Object>> data = providerDataEntity.get().getData();

            String[] specificationFieldsFromQueryStrings = (String[]) queryMultiValueMap.keySet().stream().toArray(); // specification fields

            List<String> specificationValues = Arrays.asList((String[]) queryMultiValueMap.values().toArray()); // specification filter query

            Map<String, String[]> queryMapping = new HashMap<>();

            int count = 0;

            for (String s : specificationValues) {

                String[] str = s.split(":");

                queryMapping.put( specificationFieldsFromQueryStrings [count++] , new String[] {str [0] , str [1] } );

            }

            return filterWithQueries( queryMapping, data );

        }

    }


    private List<Map<String, Object>> filterWithQueries(Map<String, String[]> queryMapping, List<Map<String, Object>> providerEntityDataValues ) throws NumberFormatException {


        String condition, value, specValue;

        String[] queryParamValue;

        Boolean provDataIndexStillValid = true ;

        Long specLongValue = 0L ;


        List<Map<String, Object>> filteredResults = new ArrayList<>();

        for ( Map<String, Object> providerEntityDataValue : providerEntityDataValues ) { // iterate through the provider entity data attribute value

            for ( String specField : queryMapping.keySet() ) { // iterate through the fields in the query string

                queryParamValue = queryMapping.get(specField);

                condition = queryParamValue[0];

                value = queryParamValue[1];

                switch (condition) {
                    // string only then convert to lower case
                    case "eqc": {

                        specValue = (String) providerEntityDataValue.get(specField);

                        if (specValue.toLowerCase().contains(value)) {

                            provDataIndexStillValid = true; // continue running filtering;

                            break;

                        }else provDataIndexStillValid = false ;

                    }
                    // integer and timestamp only
                    case "gt": {

                        try {

                            specValue = (String) providerEntityDataValue.get(specField);

                            specLongValue = Long.parseLong(specValue);

                            if ( Long.parseLong(value) > specLongValue ) {

                                provDataIndexStillValid = true; // continue running filterin;

                                break;

                            }else provDataIndexStillValid = false ;

                        } catch (NumberFormatException e) {

                            ResponseEntity.status(HttpStatus.BAD_REQUEST);

                            throw new NumberFormatException("gt:value query passed is not an integer nor a timestamp value. please check the query and retry. Operation terminated!");

                        }
                    }
                    case "lt": {

                        try {

                            specValue = (String) providerEntityDataValue.get(specField);

                            specLongValue = Long.parseLong(specValue);

                            if ( Long.parseLong(value) < specLongValue ) {

                                provDataIndexStillValid = true; // continue running filterin;

                                break;

                            }

                        } catch (NumberFormatException e) {

                            ResponseEntity.status(HttpStatus.BAD_REQUEST);

                            throw new NumberFormatException("lt:value query passed is not an integer nor timestamp value. please check the query and retry. Operation terminated!");

                        }
                    }
                    case "eq": {

                        try {

                            specValue = (String) providerEntityDataValue.get(specField);

                            specLongValue = Long.parseLong(specValue);

                            if ( Long.parseLong(value) == specLongValue ) {

                                provDataIndexStillValid = true; // continue running filterin;

                                break;

                            }else provDataIndexStillValid = false ;

                        } catch (NumberFormatException e) {

                            ResponseEntity.status(HttpStatus.BAD_REQUEST);

                            throw new NumberFormatException("eq:value query passed is not an integer nor timestamp value. please check the query and retry. Operation terminated!");

                        }
                    }

                }

                if ( provDataIndexStillValid == false )  break;

            }

            if (provDataIndexStillValid) filteredResults.add(providerEntityDataValue); // add providerDataValue if it passes all the query params

        }

        return filteredResults;

    }
}
