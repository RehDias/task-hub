package com.task.hub.project.manager.entity;

import com.task.hub.project.manager.utils.Roles;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
@NoArgsConstructor
public class Usuario {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  @NotBlank(message = "O nome é obrigatório")
  private String nome;

  @Column(nullable = false, unique = true)
  @Email(message = "O email deve ser válido")
  @NotBlank(message = "O email é obrigatório")
  private String email;

  @Column(nullable = false)
  @Size(min = 8, message = "A senha deve ter no mínimo 8 caracteres")
  @NotBlank(message = "A senha é obrigatória")
  private String senha;

  @ManyToMany(mappedBy = "membros", cascade = CascadeType.ALL)
  private List<Time> time;

  @OneToMany(mappedBy = "responsavel", cascade = CascadeType.ALL)
  private List<Tarefa> tarefas;

  @Enumerated(EnumType.STRING)
  private Roles role;
}
