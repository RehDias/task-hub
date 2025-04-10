package com.task.hub.project.manager.service.exceptions;

public class UsuarioNotFoundException extends NotFoundException {

  public UsuarioNotFoundException() {
    super("Usuário não encontrado!");
  }
}
