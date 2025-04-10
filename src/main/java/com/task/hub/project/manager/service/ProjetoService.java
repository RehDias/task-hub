package com.task.hub.project.manager.service;

import com.task.hub.project.manager.entity.Projeto;
import com.task.hub.project.manager.repository.ProjetoRepository;
import com.task.hub.project.manager.service.exceptions.ProjetoNotFoundException;
import jakarta.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class ProjetoService {

  private final ProjetoRepository projetoRepository;

  @Autowired
  public ProjetoService(ProjetoRepository projetoRepository) {
    this.projetoRepository = projetoRepository;
  }

  public Projeto criarProjeto(@Valid Projeto projeto) {
    projeto.setDataInicio(LocalDate.now());
    return projetoRepository.save(projeto);
  }

  public List<Projeto> buscarProjetos() {
    return projetoRepository.findAll();
  }

  public Projeto buscarPorId(Long id) throws ProjetoNotFoundException {
    return projetoRepository.findById(id).orElseThrow(ProjetoNotFoundException::new);
  }

public Projeto atualizarProjeto(Long id, @Valid Projeto projeto) throws ProjetoNotFoundException {
    Projeto projetoDb = buscarPorId(id);

    projetoDb.setNome(projeto.getNome());
    projetoDb.setDescricao(projeto.getDescricao());
    projetoDb.getTimes().clear();
    projetoDb.getTimes().addAll(projeto.getTimes());
    projetoDb.getTarefas().clear();
    projetoDb.getTarefas().addAll(projeto.getTarefas());
    projetoDb.setDataFim(projeto.getDataFim());

    return projetoRepository.save(projetoDb);
  }

  public Projeto excluirProjeto(Long id) throws ProjetoNotFoundException {
    Projeto projeto = buscarPorId(id);

    projetoRepository.deleteById(id);

    return projeto;
  }
}
