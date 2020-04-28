package com.example.demo.javacodetest.restcontrollers;



import com.example.demo.javacodetest.entities.ProviderDataEntity;
import com.example.demo.javacodetest.services.ProviderDataEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class LoadDataController {
    @Autowired
    private ProviderDataEntityService providerDataEntityService ;

    @RequestMapping(value = "/LoadProviderData" , method = RequestMethod.POST)
    public ResponseEntity<Boolean> loadProviderData(@RequestBody ProviderDataEntity providerDataEntity) throws Exception
    {
       providerDataEntityService.saveData(providerDataEntity);
       return ResponseEntity.ok(true);
    }


    @GetMapping(value = "/GetAllProviderDataWithLimit/{limit}")
    public  ResponseEntity<List<ProviderDataEntity>> getAllData(@PathVariable(name = "limit") Integer limit){
        return ResponseEntity.ok(providerDataEntityService.getAllData(limit));
    }

}
