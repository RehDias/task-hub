package com.task.hub.project.manager.dto;

import com.task.hub.project.manager.entity.Projeto;
import com.task.hub.project.manager.entity.Time;
import com.task.hub.project.manager.entity.Usuario;
import java.util.List;

public record TimeCreationDto(
    String nome,
    List<Usuario> membros,
    Projeto projeto
) {
  public Time toEntity() {
    return new Time(nome, membros, projeto);
  }
}
