package com.example.crud.demo.domain.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseErrorApiDTO implements Serializable {

    private int error;
    private HttpStatus status;
    private LocalDateTime timestamp;
    private String message;
}
