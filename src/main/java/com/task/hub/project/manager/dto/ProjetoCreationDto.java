package com.task.hub.project.manager.dto;

import com.task.hub.project.manager.entity.Projeto;
import java.time.LocalDate;

public record ProjetoCreationDto(
    String nome,
    String descricao,
    LocalDate dataInicio,
    LocalDate dataFim
) {
  public Projeto toEntity() {
    return new Projeto(nome, descricao, dataInicio, dataFim);
  }
}
