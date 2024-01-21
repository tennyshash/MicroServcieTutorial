package com.hotelrating.userservice.Entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Rating {
    private String id;
    private String userID;
    private String hotelID;
    private int rating;
    private String remark;

    private Hotel hotel;
}
