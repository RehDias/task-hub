package com.task.hub.project.manager.service.exceptions;

public class ProjetoNotFoundException extends NotFoundException {

  public ProjetoNotFoundException() {
    super("Projeto não encontrado!");
  }
}
