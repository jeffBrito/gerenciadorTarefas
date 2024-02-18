package br.com.jbdevsystem.api.dto;

import java.util.Date;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TarefasDto(@NotNull @NotBlank String titulo,@NotNull @NotBlank String descricao,Date dataInicio,Date dataTermino,Integer nivelPrioridade,@NotNull Integer status) {

}
