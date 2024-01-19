package com.hotelrating.userservice.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User extends BaseEntity{

    @Column(name = "User_Name", length = 50, nullable = false)
    private String Name;
    @Column(unique = true, nullable = false)
    private String Email;
    private String Password;
    private String About;

    @Transient
    private List<Rating> rating= new ArrayList<>();
}
