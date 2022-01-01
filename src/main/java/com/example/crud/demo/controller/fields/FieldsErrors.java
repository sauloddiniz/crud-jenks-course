package com.example.crud.demo.controller.fields;

import static java.util.Arrays.stream;

import com.example.crud.demo.domain.DTO.errors.FieldErrorsDTO;
import com.example.crud.demo.execptions.FieldErrorsExceptions;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

public class FieldsErrors {

  public static void fieldsHasErrors(BindingResult result) {
    if (result.hasErrors()) {
      HashMap<String, String> errors = new HashMap<>();
      for (FieldError error : result.getFieldErrors()) {
        errors.put(
            error.getField(),
            result.getFieldErrors(error.getField()).stream()
                .map(e -> e.getDefaultMessage())
                .collect(Collectors.joining(",")));
      }
      throw new FieldErrorsExceptions(errors.toString());
    }
  }

  public static List<FieldErrorsDTO> mountErrorMessage(FieldErrorsExceptions msg) {
    String removeStr = msg.getLocalizedMessage().replaceAll("[{}]", "");
    List<String> errors = stream(removeStr.split(", ")).collect(Collectors.toList());
    List<FieldErrorsDTO> fieldErrorsDTOS = new ArrayList<>();
    for (String error : errors) {
      int indexEqual = error.indexOf("=");
      String field = error.substring(0, indexEqual);
      String message = error.substring(indexEqual + 1, error.length());
      fieldErrorsDTOS.add(new FieldErrorsDTO(field, message));
    }
    return fieldErrorsDTOS;
  }
}
