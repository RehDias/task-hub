package com.task.hub.project.manager.controller;

import com.task.hub.project.manager.dto.TarefaCreationDto;
import com.task.hub.project.manager.dto.TarefaDto;
import com.task.hub.project.manager.service.TarefaService;
import com.task.hub.project.manager.service.exceptions.ProjetoNotFoundException;
import com.task.hub.project.manager.service.exceptions.TarefaNotFoundException;
import com.task.hub.project.manager.service.exceptions.UsuarioNotFoundException;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tarefas")
public class TarefaController {

  private final TarefaService tarefaService;

  @Autowired
  public TarefaController(TarefaService tarefaService) {
    this.tarefaService = tarefaService;
  }

  @GetMapping
  @PreAuthorize("hasAuthority('ADMIN', 'MANAGER', 'DEV')")
  @SecurityRequirement(name = "bearerAuth")
  public List<TarefaDto> listarTarefas() {
    return tarefaService.buscarTarefas().stream().map(TarefaDto::fromEntity).toList();
  }

  @PostMapping
  @PreAuthorize("hasAuthority('ADMIN', 'MANAGER')")
  @SecurityRequirement(name = "bearerAuth")
  public TarefaDto criarTarefa(@RequestBody TarefaCreationDto tarefaCreationDto) {
    return TarefaDto.fromEntity(tarefaService.criarTarefa(tarefaCreationDto.toEntity()));
  }

  @GetMapping("/{id}")
  @PreAuthorize("hasAuthority('ADMIN', 'MANAGER', 'DEV')")
  @SecurityRequirement(name = "bearerAuth")
  public TarefaDto buscarTarefaPorId(@PathVariable Long id) throws TarefaNotFoundException {
    return TarefaDto.fromEntity(tarefaService.buscarPorId(id));
  }

  @PatchMapping("/{id}")
  @PreAuthorize("hasAuthority('ADMIN', 'MANAGER')")
  @SecurityRequirement(name = "bearerAuth")
  public TarefaDto atualizarTarefa(
      @PathVariable Long id,
      @RequestBody TarefaCreationDto tarefaCreationDto)
      throws TarefaNotFoundException {
    return TarefaDto.fromEntity(tarefaService.atualizarTarefa(id, tarefaCreationDto.toEntity()));
  }

  @DeleteMapping("/{id}")
  @PreAuthorize("hasAuthority('ADMIN', 'MANAGER')")
  @SecurityRequirement(name = "bearerAuth")
  public TarefaDto excluirTarefa(@PathVariable Long id) throws TarefaNotFoundException {
    return TarefaDto.fromEntity(tarefaService.excluirTarefa(id));
  }

  @PutMapping("/{tarefaId}/projetos/{projetoId}")
  @PreAuthorize("hasAuthority('ADMIN', 'MANAGER')")
  @SecurityRequirement(name = "bearerAuth")
  public TarefaDto adicionarProjetoATarefa(
      @PathVariable Long tarefaId, @PathVariable Long projetoId)
      throws ProjetoNotFoundException, TarefaNotFoundException {
    return TarefaDto.fromEntity(tarefaService.adicionarProjeto(tarefaId, projetoId));
  }

  @DeleteMapping("/{tarefaId}/projetos")
  @PreAuthorize("hasAuthority('ADMIN', 'MANAGER')")
  @SecurityRequirement(name = "bearerAuth")
  public TarefaDto removerProjetoDaTarefa(@PathVariable Long tarefaId)
      throws TarefaNotFoundException {
    return TarefaDto.fromEntity(tarefaService.removerProjeto(tarefaId));
  }

  @PutMapping("/{tarefaId}/usuarios/{usuarioId}")
  @PreAuthorize("hasAuthority('ADMIN', 'MANAGER')")
  @SecurityRequirement(name = "bearerAuth")
  public TarefaDto adicionarResponsavelATarefa(
      @PathVariable Long tarefaId, @PathVariable Long usuarioId)
      throws TarefaNotFoundException, UsuarioNotFoundException {
    return TarefaDto.fromEntity(tarefaService.adicionarResponsavel(tarefaId, usuarioId));
  }

  @DeleteMapping("/{tarefaId}/usuarios")
  @PreAuthorize("hasAuthority('ADMIN', 'MANAGER')")
  @SecurityRequirement(name = "bearerAuth")
  public TarefaDto removerResponsavelDaTarefa(@PathVariable Long tarefaId)
      throws TarefaNotFoundException {
    return TarefaDto.fromEntity(tarefaService.removerResponsavel(tarefaId));
  }
}
