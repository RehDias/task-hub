package com.task.hub.project.manager.controller;

import com.task.hub.project.manager.dto.TimeCreationDto;
import com.task.hub.project.manager.dto.TimeDto;
import com.task.hub.project.manager.service.TimeService;
import com.task.hub.project.manager.service.exceptions.ProjetoNotFoundException;
import com.task.hub.project.manager.service.exceptions.TimeNotFoundException;
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
@RequestMapping("/times")
public class TimeController {

  private final TimeService timeService;

  @Autowired
  public TimeController(TimeService timeService) {
    this.timeService = timeService;
  }

  @GetMapping
  @PreAuthorize("hasAuthority('ADMIN', 'MANAGER', 'DEV')")
  @SecurityRequirement(name = "bearerAuth")
  public List<TimeDto> listarTimes() {
    return timeService.buscarTimes().stream().map(TimeDto::fromEntity).toList();
  }

  @PostMapping
  @PreAuthorize("hasAuthority('ADMIN')")
  @SecurityRequirement(name = "bearerAuth")
  public TimeDto criarTime(@RequestBody TimeCreationDto timeCreationDto) {
    return TimeDto.fromEntity(timeService.salvarTime(timeCreationDto.toEntity()));
  }

  @GetMapping("/{id}")
  @PreAuthorize("hasAuthority('ADMIN', 'MANAGER')")
  @SecurityRequirement(name = "bearerAuth")
  public TimeDto buscarTimePorId(@PathVariable Long id) throws TimeNotFoundException {
    return TimeDto.fromEntity(timeService.buscarPorId(id));
  }

  @PatchMapping("/{id}")
  @PreAuthorize("hasAuthority('ADMIN', 'MANAGER')")
  @SecurityRequirement(name = "bearerAuth")
  public TimeDto atualizarTime(
      @PathVariable Long id,
      @RequestBody TimeCreationDto timeCreationDto)
      throws TimeNotFoundException {
    return TimeDto.fromEntity(timeService.atualizarTime(id, timeCreationDto.toEntity()));
  }

  @DeleteMapping("/{id}")
  @PreAuthorize("hasAuthority('ADMIN')")
  @SecurityRequirement(name = "bearerAuth")
  public TimeDto excluirTime(@PathVariable Long id) throws TimeNotFoundException {
    return TimeDto.fromEntity(timeService.excluirTime(id));
  }

  @PutMapping("/{timeId}/projetos/{projetoId}")
  @PreAuthorize("hasAuthority('ADMIN', 'MANAGER')")
  @SecurityRequirement(name = "bearerAuth")
  public TimeDto adicionarProjetoAoTime(
      @PathVariable Long timeId, @PathVariable Long projetoId)
      throws TimeNotFoundException, ProjetoNotFoundException {
    return TimeDto.fromEntity(timeService.adicionarProjetoAoTime(timeId, projetoId));
  }

  @DeleteMapping("/{timeId}/projetos")
  @PreAuthorize("hasAuthority('ADMIN', 'MANAGER')")
  @SecurityRequirement(name = "bearerAuth")
  public TimeDto removerProjetoDoTime(@PathVariable Long timeId) throws TimeNotFoundException {
    return TimeDto.fromEntity(timeService.removerProjetoDoTime(timeId));
  }
}
