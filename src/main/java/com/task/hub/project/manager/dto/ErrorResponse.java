package com.task.hub.project.manager.dto;

import java.util.Map;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse {
  private Map<String, String> errors;

  public ErrorResponse(Map<String, String> errors) {
    this.errors = errors;
  }
}
