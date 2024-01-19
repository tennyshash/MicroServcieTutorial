package com.hotelrating.userservice.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.UUID;

@Getter
@MappedSuperclass
public class BaseEntity {
    @Id
    @Column(nullable = false, name = "ID")
    private String ID;

    private Date createdAt;

    @PrePersist
    public void generateID(){
        if( ID == null){
            ID= UUID.randomUUID().toString();
            createdAt=new Date();
        }
    }

}
