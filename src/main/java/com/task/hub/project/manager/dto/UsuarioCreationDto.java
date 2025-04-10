package com.task.hub.project.manager.dto;

import com.task.hub.project.manager.entity.Tarefa;
import com.task.hub.project.manager.entity.Time;
import com.task.hub.project.manager.entity.Usuario;
import java.util.List;

public record UsuarioCreationDto(
    String nome,
    String email,
    String password,
    List<Time> time,
    List<Tarefa> tarefas,
    String role,
    String username
) {
  public Usuario toEntity() {
    return new Usuario(nome, email, password, time, tarefas, role, username);
  }
}
