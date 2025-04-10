package com.task.hub.project.manager.dto;

import com.task.hub.project.manager.entity.Projeto;
import java.time.LocalDate;
import java.util.List;

public record ProjetoDto(
    Long id,
    String nome,
    String descricao,
    LocalDate dataInicio,
    LocalDate dataFim,
    List<TarefaDto> tarefas,
    List<TimeDto> times
) {
  public static ProjetoDto fromEntity(Projeto projeto) {
    return new ProjetoDto(
        projeto.getId(),
        projeto.getNome(),
        projeto.getDescricao(),
        projeto.getDataInicio(),
        projeto.getDataFim(),
        projeto.getTarefas().stream().map(TarefaDto::fromEntity).toList(),
        projeto.getTimes().stream().map(TimeDto::fromEntity).toList()
    );
  }
}
