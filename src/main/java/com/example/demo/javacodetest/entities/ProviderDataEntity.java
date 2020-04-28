package com.example.demo.javacodetest.entities;

import org.springframework.lang.NonNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.Map;

@Entity
public class ProviderDataEntity {

    @Id
    private Integer providerId ;

    @NonNull
    @Column(length = 1025)
    private ArrayList < Map < String , Object > > data ;

    public Integer getProviderId() {
        return providerId;
    }

    public void setProviderId(Integer providerId) {
        this.providerId = providerId;
    }

    public ArrayList< Map<String, Object> > getData() {
        return data;
    }

    public void setData(ArrayList < Map<String, Object> > data) {
        this.data = data;
    }
}