package com.task.hub.project.manager.service;

import com.task.hub.project.manager.entity.Time;
import com.task.hub.project.manager.repository.TimeRepository;
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

  @Autowired
  public TimeService(TimeRepository timeRepository) {
    this.timeRepository = timeRepository;
  }

  public Time criarTime(@Valid Time time) {
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
    timeDb.getMembros().addAll(time.getMembros());

    return timeRepository.save(timeDb);
  }

  public Time excluirTime(Long id) throws TimeNotFoundException {
    Time time = buscarPorId(id);

    timeRepository.deleteById(id);

    return time;
  }
}
