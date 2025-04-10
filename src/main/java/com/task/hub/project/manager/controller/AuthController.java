package com.task.hub.project.manager.controller;

import com.task.hub.project.manager.dto.AuthDto;
import com.task.hub.project.manager.dto.TokenDto;
import com.task.hub.project.manager.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
  private final TokenService tokenService;

  private final AuthenticationManager authenticationManager;

  @Autowired
  public AuthController(TokenService tokenService, AuthenticationManager authenticationManager) {
    this.tokenService = tokenService;
    this.authenticationManager = authenticationManager;
  }

  @PostMapping("/login")
  public TokenDto login(@RequestBody AuthDto authDto){
    UsernamePasswordAuthenticationToken usernamePassword =
        new UsernamePasswordAuthenticationToken(authDto.username(), authDto.password());

    Authentication auth = authenticationManager.authenticate(usernamePassword);
    String token = tokenService.generateToken(auth.getName());

    return new TokenDto(token);
  }
}
