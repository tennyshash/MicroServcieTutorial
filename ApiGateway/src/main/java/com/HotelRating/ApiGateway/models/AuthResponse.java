package com.HotelRating.ApiGateway.models;

import lombok.*;

import java.util.Collection;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor

public class AuthResponse {
    private String userID;
    private String accessToken;
    private String refreshToken;
    private long expiredAt;
    private Collection<String> authorities;
}
