package br.com.jbdevsystem.api.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Data
@Entity
@Table(name = "tarefas")
public class Tarefas implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_tarefa",nullable = false)
	private Long idTarefa;
	
	@Column(length = 70,nullable = false)
	private String titulo;
	
	@Column(length = 150,nullable = false)
	private String descricao;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-mm-dd")
	@Column(name="data_inicio",nullable = true)
	private Date dataInicio;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-mm-dd")
	@Column(name="data_termino",nullable = true)
	private Date dataTermino;
	
	@Column(name="nivel_prioridade",nullable = false)
	private Integer nivelPrioridade;
	
	@Column(nullable = false)
	private Integer status;
	
	@DateTimeFormat(pattern = "yyyy-mm-dd HH:mm:ss")
	@Column(name="data_cadastro")
	private Date dataCadastro = new Date(System.currentTimeMillis());
}
