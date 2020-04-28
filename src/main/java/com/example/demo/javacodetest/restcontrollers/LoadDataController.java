package com.example.demo.javacodetest.restcontrollers;



import com.example.demo.javacodetest.entities.ProviderDataEntity;
import com.example.demo.javacodetest.entities.SpecificationEntity;
import com.example.demo.javacodetest.services.ProviderDataEntityService;
import com.example.demo.javacodetest.services.SpecificationEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class LoadDataController {

    @Autowired
    private ProviderDataEntityService providerDataEntityService ;

    @Autowired
    private SpecificationEntityService specificationEntityService;

    @RequestMapping(value = "/LoadProviderData" , method = RequestMethod.POST)
    public ResponseEntity<Object> loadProviderData(@RequestBody final Map< String , Object >  data) throws Exception
    {

        try {

            Set<String> set = data.keySet();

            if  ( set.contains("providerId") && set.contains("data")) {

                ProviderDataEntity providerDataEntity = new ProviderDataEntity();

                if (data.get("data")  instanceof  ArrayList<?> ) {

                    Optional < SpecificationEntity > specificationEntity = specificationEntityService.findSpecificationEntityByProviderId((Integer) data.get("providerId"));

                    if ( specificationEntity.isPresent() ) {

                        ArrayList<Map<String , Object>> dataVal = (ArrayList<Map<String , Object>>) data.get("data");

                        for (Map<String, Object> stringObjectMap : dataVal) {

                            if (specificationEntity.get().getSpecificationFields().containsAll( stringObjectMap.keySet() ) )  {

                                continue;

                            }

                            else {

                                return ResponseEntity.of(Optional.of("Specification keys not match for one or all of the values in the data"));

                            }

                        }

                        providerDataEntity.setProviderId( (Integer) data.get("providerId") );

                        providerDataEntity.setData(dataVal);

                        providerDataEntityService.saveData(providerDataEntity);

                        return ResponseEntity.of(Optional.of("Data loaded"));

                    }else {

                        return ResponseEntity.of(Optional.of("Specification for the data does not exist. please create a Specification before loading data"));

                    }

                }else {

                   return ResponseEntity.of(Optional.of("fields has to be strings data type"));

                }

            }else {

                return ResponseEntity.of(Optional.of("Request body received does not have providerId or data key"));

            }

        } catch (Exception e) {

            return ResponseEntity.of(Optional.of("Error occured " + e.getMessage() ) );

        }
    }


    @GetMapping(value = "/GetAllProviderDataWithLimit/{limit}")
    public  ResponseEntity<List<ProviderDataEntity>> getAllData(@PathVariable(name = "limit") Integer limit){
        return ResponseEntity.ok(providerDataEntityService.getAllData(limit));
    }

}
