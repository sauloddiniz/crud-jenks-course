package com.example.crud.demo.execptions;

import com.example.crud.demo.domain.DTO.ResponseErrorApiDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;


@RestControllerAdvice
public class CustomRestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ResponseErrorApiDTO> handlerEntityNotFoundException(EntityNotFoundException entity) {
        HttpStatus status = HttpStatus.NOT_FOUND;

        ResponseErrorApiDTO errorApiDTO = ResponseErrorApiDTO.builder()
                .error(status.value())
                .timestamp(LocalDateTime.now())
                .status(status)
                .message(entity.getLocalizedMessage())
                .build();
        return new ResponseEntity<>(errorApiDTO, status);
    }

}
