package com.HotelRating.ApiGateway.Exception;

public class ApiException extends RuntimeException{
    public ApiException(String message) {
        super(message);
    }
}
