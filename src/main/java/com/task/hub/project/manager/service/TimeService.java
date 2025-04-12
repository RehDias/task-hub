package com.task.hub.project.manager.service;

import com.task.hub.project.manager.entity.Projeto;
import com.task.hub.project.manager.entity.Time;
import com.task.hub.project.manager.repository.TimeRepository;
import com.task.hub.project.manager.service.exceptions.ProjetoNotFoundException;
import com.task.hub.project.manager.service.exceptions.TimeNotFoundException;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class TimeService {

  private final TimeRepository timeRepository;
  private final ProjetoService projetoService;

  @Autowired
  public TimeService(TimeRepository timeRepository, ProjetoService projetoService) {
    this.timeRepository = timeRepository;
    this.projetoService = projetoService;
  }

  public Time salvarTime(@Valid Time time) {
    return timeRepository.save(time);
  }

  public List<Time> buscarTimes() {
    return timeRepository.findAll();
  }

  public Time buscarPorId(Long id) throws TimeNotFoundException {
    return timeRepository.findById(id).orElseThrow(TimeNotFoundException::new);
  }

  public Time atualizarTime(Long id, @Valid Time time) throws TimeNotFoundException {
    Time timeDb = buscarPorId(id);

    timeDb.setNome(time.getNome());
    timeDb.getMembros().clear();

    return timeRepository.save(timeDb);
  }

  public Time excluirTime(Long id) throws TimeNotFoundException {
    Time time = buscarPorId(id);

    timeRepository.deleteById(id);

    return time;
  }

  public Time adicionarProjetoAoTime(Long timeId, Long projetoId)
      throws TimeNotFoundException, ProjetoNotFoundException {
    Time time = buscarPorId(timeId);
    Projeto projeto = projetoService.buscarPorId(projetoId);

    time.setProjeto(projeto);

    return timeRepository.save(time);
  }

  public Time removerProjetoDoTime(Long timeId) throws TimeNotFoundException {
    Time time = buscarPorId(timeId);

    time.setProjeto(null);

    return timeRepository.save(time);
  }
}
