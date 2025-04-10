package com.task.hub.project.manager.dto;

import com.task.hub.project.manager.entity.Projeto;
import com.task.hub.project.manager.entity.Tarefa;
import com.task.hub.project.manager.entity.Usuario;
import com.task.hub.project.manager.utils.Status;
import java.time.LocalDate;

public record TarefaCreationDto(
    String titulo,
    String descricao,
    Status status,
    LocalDate dataFim,
    Projeto projeto,
    Usuario responsavel
) {
  public Tarefa toEntity() {
    return new Tarefa(titulo, descricao, status, dataFim, projeto, responsavel);
  }
}
