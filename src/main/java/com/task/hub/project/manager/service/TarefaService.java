package com.task.hub.project.manager.service;

import com.task.hub.project.manager.entity.Tarefa;
import com.task.hub.project.manager.repository.TarefaRepository;
import com.task.hub.project.manager.service.exceptions.TarefaNotFoundException;
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

  @Autowired
  public TarefaService(TarefaRepository tarefaRepository) {
    this.tarefaRepository = tarefaRepository;
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
}
