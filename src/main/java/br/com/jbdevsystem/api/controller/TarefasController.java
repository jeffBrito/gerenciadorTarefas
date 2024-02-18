package br.com.jbdevsystem.api.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.jbdevsystem.api.dto.TarefasDto;
import br.com.jbdevsystem.api.model.Tarefas;
import br.com.jbdevsystem.api.repository.TarefasRepository;
import br.com.jbdevsystem.api.utils.Utils;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/tarefas")
public class TarefasController {

	@Autowired
	private TarefasRepository tarefasRepository;
	
	@PostMapping("/salvar")
	public ResponseEntity<Object> salvar(@RequestBody @Valid TarefasDto tarefasDto){
		var tarefa = new Tarefas();
		
		Utils.copyNonNullProperties(tarefasDto, tarefa);
		
		tarefa.setTitulo(tarefa.getTitulo().toUpperCase());
		tarefa.setDescricao(tarefa.getDescricao().toUpperCase());
		
		var verTitulo = tarefasRepository.findByTitulo(tarefa.getTitulo());
		
		if(verTitulo != null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Título já cadastrado do sistema !");
		}else {
			return ResponseEntity.status(HttpStatus.CREATED).body(tarefasRepository.save(tarefa));
		}
	}
	
	@GetMapping("/")
	public ResponseEntity<List<Tarefas>> buscaGeral(){
		return ResponseEntity.status(HttpStatus.OK).body(tarefasRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Tarefas>> buscaId(@PathVariable("id") Long idTarefa){
		return ResponseEntity.status(HttpStatus.OK).body(tarefasRepository.findById(idTarefa));
	}

	@PatchMapping("/atualizar/{id}")
	public ResponseEntity<Object> atualizar(@PathVariable("id") Long idTarefa, @RequestBody @Valid TarefasDto tarefasDto){
		var pesTarefa = tarefasRepository.findById(idTarefa).orElse(null);
		
		if(pesTarefa == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tarefa não encontrada em nosso sistema");
		}else {
			Utils.copyNonNullProperties(tarefasDto, pesTarefa);
			
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(tarefasRepository.save(pesTarefa));
		}
	}
	
	@GetMapping("/status/{status}")
	public ResponseEntity<List<Tarefas>> tarefasPendentes(@PathVariable("status") Integer status){
		return ResponseEntity.status(HttpStatus.OK).body(tarefasRepository.findByStatus(status));
	}

}

