package com.task.hub.project.manager.controller;

import com.task.hub.project.manager.dto.AuthDto;
import com.task.hub.project.manager.dto.SenhaDto;
import com.task.hub.project.manager.dto.UsuarioCreationDto;
import com.task.hub.project.manager.dto.UsuarioDto;
import com.task.hub.project.manager.service.UsuarioService;
import java.util.List;

import com.task.hub.project.manager.service.exceptions.UsuarioNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public UsuarioDto criarUsuario(@RequestBody UsuarioCreationDto usuarioCreationDto) {
    return UsuarioDto.fromEntity(usuarioService.criarUsuario(usuarioCreationDto.toEntity()));
  }

  @GetMapping("/{id}")
  @PreAuthorize("hasAuthority('ADMIN', 'MANAGER')")
  public UsuarioDto buscarUsuarioPorId(@PathVariable Long id) throws UsuarioNotFoundException {
    return UsuarioDto.fromEntity(usuarioService.buscarPorId(id));
  }

  @PatchMapping("/{id}")
  @PreAuthorize("hasAuthority('ADMIN', 'MANAGER', 'DEV')")
  public UsuarioDto atualizarUsuario(@PathVariable Long id, UsuarioCreationDto usuarioCreationDto)
          throws UsuarioNotFoundException {
    return UsuarioDto.fromEntity(usuarioService.atualizarUsuario(id, usuarioCreationDto.toEntity()));
  }

  @DeleteMapping("/{id}")
  @PreAuthorize("hasAuthority('ADMIN')")
  public UsuarioDto excluirUsuario(@PathVariable Long id) throws UsuarioNotFoundException {
    return UsuarioDto.fromEntity(usuarioService.excluirUsuario(id));
  }

  @PatchMapping("/{id}/senha")
  @PreAuthorize("hasAuthority('ADMIN', 'MANAGER', 'DEV')")
  public String alterarSenhaUsuario(@PathVariable Long id, @RequestBody SenhaDto senhaDto)
          throws UsuarioNotFoundException {
    usuarioService.alterarSenha(id, senhaDto.senhaAtual(), senhaDto.novaSenha());
    return "Senha alterada com sucesso!";
  }
}
