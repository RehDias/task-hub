package com.task.hub.project.manager.service.exceptions;

public class TarefaNotFoundException extends NotFoundException {

  public TarefaNotFoundException() {
    super("Tarefa não encontrada!");
  }
}
