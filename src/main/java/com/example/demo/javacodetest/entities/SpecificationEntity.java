package com.example.demo.javacodetest.entities;

import org.springframework.lang.NonNull;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.ArrayList;

@Entity
public class SpecificationEntity {
    @Id
    private Integer providerId ;

    @NonNull
    private ArrayList<String> specificationFields ;

    public Integer getProviderId() {
        return providerId;
    }

    public void setProviderId(Integer providerId) {
        this.providerId = providerId;
    }

    public ArrayList<String> getSpecificationFields() {
        return specificationFields;
    }

    public void setSpecificationFields(ArrayList<String> specificationFields) {
        this.specificationFields = specificationFields;
    }
}
