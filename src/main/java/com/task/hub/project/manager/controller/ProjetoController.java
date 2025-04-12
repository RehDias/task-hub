package com.task.hub.project.manager.controller;

import com.task.hub.project.manager.dto.ProjetoCreationDto;
import com.task.hub.project.manager.dto.ProjetoDto;
import com.task.hub.project.manager.service.ProjetoService;
import com.task.hub.project.manager.service.exceptions.ProjetoNotFoundException;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projetos")
public class ProjetoController {

  private final ProjetoService projetoService;

  @Autowired
  public ProjetoController(ProjetoService projetoService) {
    this.projetoService = projetoService;
  }

  @GetMapping
  @PreAuthorize("hasAuthority('ADMIN', 'MANAGER', 'DEV')")
  @SecurityRequirement(name = "bearerAuth")
  public List<ProjetoDto> listarProjetos() {
    return projetoService.buscarProjetos().stream().map(ProjetoDto::fromEntity).toList();
  }

  @PostMapping
  @PreAuthorize("hasAuthority('ADMIN', 'MANAGER')")
  @SecurityRequirement(name = "bearerAuth")
  public ProjetoDto criarProjeto(@RequestBody ProjetoCreationDto projetoCreationDto) {
    return ProjetoDto.fromEntity(projetoService.criarProjeto(projetoCreationDto.toEntity()));
  }

  @GetMapping("/{id}")
  @PreAuthorize("hasAuthority('ADMIN', 'MANAGER')")
  @SecurityRequirement(name = "bearerAuth")
  public ProjetoDto buscarProjetoPorId(@PathVariable Long id) throws ProjetoNotFoundException {
    return ProjetoDto.fromEntity(projetoService.buscarPorId(id));
  }

  @PatchMapping("/{id}")
  @PreAuthorize("hasAuthority('ADMIN', 'MANAGER')")
  @SecurityRequirement(name = "bearerAuth")
  public ProjetoDto atualizarProjeto(@PathVariable Long id, @RequestBody ProjetoCreationDto projetoCreationDto)
      throws ProjetoNotFoundException {
    return ProjetoDto.fromEntity(projetoService.atualizarProjeto(id, projetoCreationDto.toEntity()));
  }

  @DeleteMapping("/{id}")
  @PreAuthorize("hasAuthority('ADMIN')")
  @SecurityRequirement(name = "bearerAuth")
  public ProjetoDto excluirProjeto(@PathVariable Long id) throws ProjetoNotFoundException {
    return ProjetoDto.fromEntity(projetoService.excluirProjeto(id));
  }


}
