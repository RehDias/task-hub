package com.task.hub.project.manager.dto;

import com.task.hub.project.manager.entity.Usuario;
import java.util.List;

public record UsuarioDto(
    Long id,
    String nome,
    String email,
    List<TimeDto> time,
    List<TarefaDto> tarefas,
    String username,
    String role
) {
  public static UsuarioDto fromEntity(Usuario usuario) {
    return new UsuarioDto(
        usuario.getId(),
        usuario.getNome(),
        usuario.getEmail(),
        usuario.getTime().stream().map(TimeDto::fromEntity).toList(),
        usuario.getTarefas().stream().map(TarefaDto::fromEntity).toList(),
        usuario.getUsername(),
        usuario.getRole()
    );
  }
}
