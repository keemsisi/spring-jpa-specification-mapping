package com.example.demo.javacodetest.entities;

import org.springframework.lang.NonNull;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.Map;

@Entity
public class ProviderDataEntity {

    @Id
    private String id ;

    @NonNull
    private ArrayList <Map<String, Object>> data ;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList< Map<String, Object> > getData() {
        return data;
    }

    public void setData(ArrayList < Map<String, Object> > data) {
        this.data = data;
    }
}