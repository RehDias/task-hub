package com.task.hub.project.manager.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "times")
@Getter
@Setter
@NoArgsConstructor
public class Time {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  @NotBlank(message = "O nome é obrigatório")
  private String nome;

  @ManyToMany
  @JoinTable(
      name = "usuario_time",
      joinColumns = @JoinColumn(name = "time_id"),
      inverseJoinColumns = @JoinColumn(name = "usuario_id")
  )
  private List<Usuario> membros;

  @ManyToOne
  @JoinColumn(name = "projeto_id")
  private Projeto projeto;
}
