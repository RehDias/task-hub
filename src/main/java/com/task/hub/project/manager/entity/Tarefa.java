package com.task.hub.project.manager.entity;

import com.task.hub.project.manager.utils.Status;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tarefas")
@Getter
@Setter
@NoArgsConstructor
public class Tarefa {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  @NotBlank(message = "O titulo é obrigatório")
  private String titulo;

  private String descricao;

  @Enumerated(EnumType.STRING)
  private Status status;

  private LocalDate dataInicio;
  private LocalDate dataFim;

  @ManyToOne
  @JoinColumn(name = "projeto_id")
  private Projeto projeto;

  @ManyToOne
  @JoinColumn(name = "usuario_id")
  private Usuario responsavel;

  public Tarefa(String titulo, String descricao, Status status, LocalDate dataFim, Projeto projeto,
      Usuario responsavel) {
    this.titulo = titulo;
    this.descricao = descricao;
    this.status = status;
    this.dataFim = dataFim;
    this.projeto = projeto;
    this.responsavel = responsavel;
  }
}
