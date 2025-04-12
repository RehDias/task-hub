package com.task.hub.project.manager.dto;

import com.task.hub.project.manager.entity.Usuario;

public record UsuarioCreationDto(
    String nome,
    String email,
    String password,
    String role,
    String username
) {
  public Usuario toEntity() {
    return new Usuario(nome, email, password, role, username);
  }
}
