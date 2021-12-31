package com.example.crud.demo.execptions;

import com.example.crud.demo.domain.DTO.FieldErrorsDTO;
import com.example.crud.demo.domain.DTO.ResponseErrorApiDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;


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

    @ExceptionHandler(FieldErrorsExceptions.class)
    public ResponseEntity<ResponseErrorApiDTO> handlerEntityNotFoundException(FieldErrorsExceptions msg) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        ResponseErrorApiDTO errorApiDTO = ResponseErrorApiDTO.builder()
                .error(status.value())
                .timestamp(LocalDateTime.now())
                .status(status)
                .errorsDTOS(mountErrorMessage(msg))
                .build();
        return new ResponseEntity<>(errorApiDTO, status);
    }

    private List<FieldErrorsDTO> mountErrorMessage(FieldErrorsExceptions msg) {
        String removeStr = msg.getLocalizedMessage().replaceAll("[{}]","");
        List<String> errors = stream(removeStr.split(", ")).collect(Collectors.toList());
        List<FieldErrorsDTO> fieldErrorsDTOS = new ArrayList<>();
        for (String error : errors) {
            int indexEqual = error.indexOf("=");
            String field = error.substring(0,indexEqual);
            String message = error.substring(indexEqual+1, error.length());
            fieldErrorsDTOS.add(new FieldErrorsDTO(field, message));
        }
        return fieldErrorsDTOS;
    }

}
