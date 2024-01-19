package com.hotelrating.hotelservice.Entity;

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
    @Column(name = "ID", nullable = false)
    private String ID;

    @PrePersist
    public void generateID(){
        if(ID==null){
            ID= UUID.randomUUID().toString();
        }
    }

}
