package com.task.hub.project.manager.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "projetos")
@Getter
@Setter
@NoArgsConstructor
public class Projeto {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  @NotBlank(message = "O nome é obrigatório")
  private String nome;

  private String descricao;
  private LocalDate dataInicio;
  private LocalDate dataFim;

  @OneToMany(mappedBy = "projeto")
  private List<Tarefa> tarefas;

  @OneToMany(mappedBy = "projeto")
  private List<Time> times;

  public Projeto(String nome, String descricao, LocalDate dataInicio, LocalDate dataFim,
      List<Tarefa> tarefas, List<Time> times) {
    this.nome = nome;
    this.descricao = descricao;
    this.dataInicio = dataInicio;
    this.dataFim = dataFim;
    this.tarefas = tarefas;
    this.times = times;
  }
}
