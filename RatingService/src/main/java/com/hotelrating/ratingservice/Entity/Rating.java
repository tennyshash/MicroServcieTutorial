package com.hotelrating.ratingservice.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "User_rating")
public class Rating extends BaseEntity{

    private String userID;
    private String hotelID;
    private int rating;
    private String remark;

}
