package com.task.hub.project.manager.controller;

import com.task.hub.project.manager.dto.SenhaDto;
import com.task.hub.project.manager.dto.UsuarioCreationDto;
import com.task.hub.project.manager.dto.UsuarioDto;
import com.task.hub.project.manager.service.UsuarioService;
import com.task.hub.project.manager.service.exceptions.TimeNotFoundException;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import java.util.List;

import com.task.hub.project.manager.service.exceptions.UsuarioNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

  private final UsuarioService usuarioService;

  @Autowired
  public UsuarioController(UsuarioService usuarioService) {
    this.usuarioService = usuarioService;
  }

  @GetMapping
  @PreAuthorize("hasAuthority('ADMIN', 'MANAGER')")
  @SecurityRequirement(name = "bearerAuth")
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
  @SecurityRequirement(name = "bearerAuth")
  public UsuarioDto buscarUsuarioPorId(@PathVariable Long id) throws UsuarioNotFoundException {
    return UsuarioDto.fromEntity(usuarioService.buscarPorId(id));
  }

  @PatchMapping("/{id}")
  @PreAuthorize("hasAuthority('ADMIN', 'MANAGER', 'DEV')")
  @SecurityRequirement(name = "bearerAuth")
  public UsuarioDto atualizarUsuario(@PathVariable Long id, UsuarioCreationDto usuarioCreationDto)
          throws UsuarioNotFoundException {
    return UsuarioDto.fromEntity(usuarioService.atualizarUsuario(id, usuarioCreationDto.toEntity()));
  }

  @DeleteMapping("/{id}")
  @PreAuthorize("hasAuthority('ADMIN')")
  @SecurityRequirement(name = "bearerAuth")
  public UsuarioDto excluirUsuario(@PathVariable Long id) throws UsuarioNotFoundException {
    return UsuarioDto.fromEntity(usuarioService.excluirUsuario(id));
  }

  @PatchMapping("/{id}/senha")
  @PreAuthorize("hasAuthority('ADMIN', 'MANAGER', 'DEV')")
  @SecurityRequirement(name = "bearerAuth")
  public String alterarSenhaUsuario(@PathVariable Long id, @RequestBody SenhaDto senhaDto)
          throws UsuarioNotFoundException {
    usuarioService.alterarSenha(id, senhaDto.senhaAtual(), senhaDto.novaSenha());
    return "Senha alterada com sucesso!";
  }

  @PutMapping("/{usuarioId}/times/{timeId}")
  @PreAuthorize("hasAuthority('ADMIN', 'MANAGER')")
  @SecurityRequirement(name = "bearerAuth")
  public UsuarioDto adicionarMembroNoTime(
      @PathVariable Long usuarioId, @PathVariable Long timeId)
      throws TimeNotFoundException, UsuarioNotFoundException {
    return UsuarioDto.fromEntity(usuarioService.adicionarMembroNoTime(usuarioId, timeId));
  }

  @DeleteMapping("/{usuarioId}/times/{timeId}")
  @PreAuthorize("hasAuthority('ADMIN', 'MANAGER')")
  @SecurityRequirement(name = "bearerAuth")
  public UsuarioDto removerMembroDoTime(@PathVariable Long usuarioId, @PathVariable Long timeId)
      throws TimeNotFoundException, UsuarioNotFoundException {
    return UsuarioDto.fromEntity(usuarioService.removerMembroDoTime(usuarioId, timeId));
  }
}
