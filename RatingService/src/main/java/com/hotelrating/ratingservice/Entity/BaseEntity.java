package com.hotelrating.ratingservice.Entity;


import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import lombok.Getter;

import java.util.UUID;

@Getter
@MappedSuperclass
public class BaseEntity {

    @Id
    @Column(name = "ID",nullable = false)
    private String id;

    @PrePersist
    public void generateID(){
        if(id==null){
            id= UUID.randomUUID().toString();
        }
    }
}
