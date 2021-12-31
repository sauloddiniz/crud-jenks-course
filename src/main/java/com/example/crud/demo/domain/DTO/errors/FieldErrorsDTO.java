package com.example.crud.demo.domain.DTO.errors;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FieldErrorsDTO implements Serializable {
  private String field;
  private String message;
}
