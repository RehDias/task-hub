package com.task.hub.project.manager.dto;

import com.task.hub.project.manager.entity.Projeto;
import com.task.hub.project.manager.entity.Tarefa;
import com.task.hub.project.manager.entity.Time;
import java.time.LocalDate;
import java.util.List;

public record ProjetoCreationDto(
    String nome,
    String descricao,
    LocalDate dataInicio,
    LocalDate dataFim,
    List<Tarefa> tarefas,
    List<Time> times
) {
  public Projeto toEntity() {
    return new Projeto(nome, descricao, dataInicio, dataFim, tarefas, times);
  }
}
