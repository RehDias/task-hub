package com.task.hub.project.manager.controller;

import com.task.hub.project.manager.dto.UsuarioDto;
import com.task.hub.project.manager.service.UsuarioService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

  private final UsuarioService usuarioService;

  @Autowired
  public UsuarioController(UsuarioService usuarioService) {
    this.usuarioService = usuarioService;
  }

  @GetMapping
  @PreAuthorize("hasAuthority('ADMIN', 'MANAGER')")
  public List<UsuarioDto> buscarTodosUsuarios() {
    return usuarioService.buscarUsuarios().stream().map(UsuarioDto::fromEntity).toList();
  }


}
