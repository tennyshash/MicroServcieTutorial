package com.hotelrating.hotelservice.Payload;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiResponse {
    private boolean requestStatus;
    private String message;
    private HttpStatus httpStatus;
}
