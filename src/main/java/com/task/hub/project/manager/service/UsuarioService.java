package com.task.hub.project.manager.service;

import com.task.hub.project.manager.dto.UsuarioCreationDto;
import com.task.hub.project.manager.entity.Time;
import com.task.hub.project.manager.entity.Usuario;
import com.task.hub.project.manager.repository.UsuarioRepository;
import com.task.hub.project.manager.service.exceptions.SenhaInvalidaException;
import com.task.hub.project.manager.service.exceptions.TimeNotFoundException;
import com.task.hub.project.manager.service.exceptions.UsuarioNotFoundException;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class UsuarioService implements UserDetailsService {

  private final UsuarioRepository usuarioRepository;
  private final PasswordEncoder passwordEncoder;
  private final TimeService timeService;

  @Autowired
  public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder,
      TimeService timeService) {
    this.usuarioRepository = usuarioRepository;
    this.passwordEncoder = passwordEncoder;
    this.timeService = timeService;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return usuarioRepository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException(username));
  }

  public Usuario criarUsuario(@Valid Usuario usuario) {
    String hashedPassword = new BCryptPasswordEncoder()
        .encode(usuario.getPassword());

    usuario.setPassword(hashedPassword);

    return usuarioRepository.save(usuario);
  }

  public List<Usuario> buscarUsuarios() {
    return usuarioRepository.findAll();
  }

  public Usuario buscarPorId(Long id) throws UsuarioNotFoundException {
    return usuarioRepository.findById(id).orElseThrow(UsuarioNotFoundException::new);
  }

  public Usuario atualizarUsuario(Long id, @Valid Usuario usuario)
      throws UsuarioNotFoundException {
    Usuario usuarioDb = buscarPorId(id);

    usuarioDb.setNome(usuario.getNome());
    usuarioDb.setUsername(usuario.getUsername());
    usuarioDb.setEmail(usuario.getEmail());

    return usuarioRepository.save(usuarioDb);
  }

  public Usuario excluirUsuario(Long id) throws UsuarioNotFoundException {
    Usuario usuario = buscarPorId(id);

    usuarioRepository.deleteById(id);

    return usuario;
  }

  public void alterarSenha(Long id, @Valid String senhaAtual, @Valid String novaSenha)
      throws UsuarioNotFoundException, SenhaInvalidaException {
    Usuario usuario = buscarPorId(id);

    if (!passwordEncoder.matches(senhaAtual, usuario.getPassword())) {
      throw new SenhaInvalidaException("Senha atual incorreta");
    }

    if (passwordEncoder.matches(novaSenha, usuario.getPassword())) {
      throw new SenhaInvalidaException("A nova senha deve ser diferente da atual");
    }

    usuario.setPassword(passwordEncoder.encode(novaSenha));
    usuarioRepository.save(usuario);
  }

  public Usuario adicionarMembroNoTime(Long usuarioId, Long timeId)
      throws UsuarioNotFoundException, TimeNotFoundException {
    Usuario usuario = buscarPorId(usuarioId);
    Time time = timeService.buscarPorId(timeId);

    usuario.getTime().add(time);
    time.getMembros().add(usuario);

    timeService.salvarTime(time);
    return usuarioRepository.save(usuario);
  }

  public Usuario removerMembroDoTime(Long usuarioId, Long timeId)
      throws UsuarioNotFoundException, TimeNotFoundException {
    Usuario usuario = buscarPorId(usuarioId);
    Time time = timeService.buscarPorId(timeId);

    usuario.getTime().remove(time);

    return usuarioRepository.save(usuario);
  }

}
