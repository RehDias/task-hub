package com.task.hub.project.manager.service;

import com.task.hub.project.manager.entity.Projeto;
import com.task.hub.project.manager.entity.Tarefa;
import com.task.hub.project.manager.entity.Usuario;
import com.task.hub.project.manager.repository.TarefaRepository;
import com.task.hub.project.manager.service.exceptions.ProjetoNotFoundException;
import com.task.hub.project.manager.service.exceptions.TarefaNotFoundException;
import com.task.hub.project.manager.service.exceptions.UsuarioNotFoundException;
import jakarta.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class TarefaService {

  private final TarefaRepository tarefaRepository;
  private final UsuarioService usuarioService;
  private final ProjetoService projetoService;

  @Autowired
  public TarefaService(TarefaRepository tarefaRepository, UsuarioService usuarioService,
      ProjetoService projetoService) {
    this.tarefaRepository = tarefaRepository;
    this.usuarioService = usuarioService;
    this.projetoService = projetoService;
  }

  public Tarefa criarTarefa(@Valid Tarefa tarefa) {
    tarefa.setDataInicio(LocalDate.now());
    return tarefaRepository.save(tarefa);
  }

  public List<Tarefa> buscarTarefas() {
    return tarefaRepository.findAll();
  }

  public Tarefa buscarPorId(Long id) throws TarefaNotFoundException {
    return tarefaRepository.findById(id).orElseThrow(TarefaNotFoundException::new);
  }

  public Tarefa atualizarTarefa(Long id, @Valid Tarefa tarefa) throws TarefaNotFoundException {
    Tarefa tarefaDb = buscarPorId(id);

    tarefaDb.setTitulo(tarefa.getTitulo());
    tarefaDb.setDescricao(tarefa.getDescricao());
    tarefaDb.setDataFim(tarefa.getDataFim());
    tarefaDb.setStatus(tarefa.getStatus());

    return tarefaRepository.save(tarefaDb);
  }

  public Tarefa excluirTarefa(Long id) throws TarefaNotFoundException {
    Tarefa tarefa = buscarPorId(id);

    tarefaRepository.deleteById(id);

    return tarefa;
  }

  public Tarefa adicionarResponsavel(Long tarefaId, Long usuarioId)
      throws TarefaNotFoundException, UsuarioNotFoundException {
    Tarefa tarefa = buscarPorId(tarefaId);
    Usuario usuario = usuarioService.buscarPorId(usuarioId);

    tarefa.setResponsavel(usuario);

    return tarefaRepository.save(tarefa);
  }

  public Tarefa removerResponsavel(Long tarefaId) throws TarefaNotFoundException {
    Tarefa tarefa = buscarPorId(tarefaId);

    tarefa.setResponsavel(null);

    return tarefaRepository.save(tarefa);
  }

  public Tarefa adicionarProjeto(Long tarefaId, Long projetoId)
      throws TarefaNotFoundException, ProjetoNotFoundException {
    Tarefa tarefa = buscarPorId(tarefaId);
    Projeto projeto = projetoService.buscarPorId(projetoId);

    tarefa.setProjeto(projeto);

    return tarefaRepository.save(tarefa);
  }

  public Tarefa removerProjeto(Long tarefaId) throws TarefaNotFoundException {
    Tarefa tarefa = buscarPorId(tarefaId);

    tarefa.setProjeto(null);

    return tarefaRepository.save(tarefa);
  }

}
