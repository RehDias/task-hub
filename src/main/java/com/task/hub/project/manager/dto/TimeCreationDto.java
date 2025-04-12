package com.task.hub.project.manager.dto;

import com.task.hub.project.manager.entity.Time;

public record TimeCreationDto(String nome) {
  public Time toEntity() {
    return new Time(nome);
  }
}
