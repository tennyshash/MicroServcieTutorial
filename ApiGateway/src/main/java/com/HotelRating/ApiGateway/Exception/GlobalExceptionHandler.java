package com.HotelRating.ApiGateway.Exception;


import com.HotelRating.ApiGateway.models.ApiResponseDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.FileNotFoundException;
import java.io.IOException;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ApiResponseDto> handleApiException(ApiException apiException){
        String message= apiException.getMessage();

        ApiResponseDto responseDto= new ApiResponseDto("FAILURE", message);

        return new ResponseEntity<ApiResponseDto>(responseDto, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        return new ResponseEntity<>("Http Request Method in Valid , Pls Change" , HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiResponseDto> handleUnAuthoriseException(AccessDeniedException accessDeniedException ){
        String message=accessDeniedException.getMessage();
        ApiResponseDto responseDto= new ApiResponseDto("FAILURE" , message);
        return new ResponseEntity<ApiResponseDto>(responseDto,HttpStatus.UNAUTHORIZED);
    }
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ApiResponseDto> handleBadCredentialException(BadCredentialsException exception){
        String message= exception.getMessage();
        return new ResponseEntity<>(new ApiResponseDto("FAILURE" , message),HttpStatus.UNAUTHORIZED);
    }
}
