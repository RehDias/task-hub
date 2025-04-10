package com.task.hub.project.manager.dto;

import com.task.hub.project.manager.entity.Tarefa;
import com.task.hub.project.manager.utils.Status;
import java.time.LocalDate;

public record TarefaDto(
    Long id,
    String titulo,
    String descricao,
    Status status,
    LocalDate dataInicio,
    LocalDate dataFim,
    ProjetoDto projeto,
    UsuarioDto responsavel
) {
  public static TarefaDto fromEntity(Tarefa tarefa) {
    ProjetoDto projetoDto = tarefa.getProjeto() != null ?
        ProjetoDto.fromEntity(tarefa.getProjeto()) : null;

    UsuarioDto usuarioDto = tarefa.getResponsavel() != null ?
        UsuarioDto.fromEntity(tarefa.getResponsavel()) : null;

    return new TarefaDto(
        tarefa.getId(),
        tarefa.getTitulo(),
        tarefa.getDescricao(),
        tarefa.getStatus(),
        tarefa.getDataInicio(),
        tarefa.getDataFim(),
        projetoDto,
        usuarioDto
    );
  }
}
