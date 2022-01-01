package com.example.crud.demo.domain.DTO.errors;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseErrorApiDTO implements Serializable {

  private int error;
  private HttpStatus status;
  private LocalDateTime timestamp;
  private String message;
  private List<FieldErrorsDTO> errorsFields;
}
