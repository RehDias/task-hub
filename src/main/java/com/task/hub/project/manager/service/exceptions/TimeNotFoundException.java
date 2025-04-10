package com.task.hub.project.manager.service.exceptions;

public class TimeNotFoundException extends NotFoundException {

  public TimeNotFoundException() {
    super("O time não foi encontrado!");
  }
}
