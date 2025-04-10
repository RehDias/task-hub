package com.task.hub.project.manager.service;

import com.task.hub.project.manager.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService implements UserDetailsService {
  private final UsuarioRepository usuarioRepository;

  @Autowired
  public UsuarioService(UsuarioRepository usuarioRepository) {
    this.usuarioRepository = usuarioRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return usuarioRepository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException(username));
  }
}
