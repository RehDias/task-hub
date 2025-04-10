package com.task.hub.project.manager.dto;

import com.task.hub.project.manager.entity.Time;
import java.util.List;

public record TimeDto(
    Long id,
    String nome,
    List<UsuarioDto> membros,
    ProjetoDto projeto
) {
  public static TimeDto fromEntity(Time time) {
    ProjetoDto projetoDto = time.getProjeto() != null ?
        ProjetoDto.fromEntity(time.getProjeto()) : null;

    return new TimeDto(
        time.getId(),
        time.getNome(),
        time.getMembros().stream().map(UsuarioDto::fromEntity).toList(),
        projetoDto
    );
  }
}
