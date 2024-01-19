package com.hotelrating.userservice.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Rating {
    private int ratingID;
    private int userID;
    private int hotelID;
    private int rating;
    private String remark;
}
