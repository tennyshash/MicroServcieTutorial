package com.hotelrating.hotelservice.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Hotels")
public class Hotel extends BaseEntity{

    @Column(name = "Hotel_Name", length = 50, nullable = false)
    private String name;
    private String location;
    private String about;
}
