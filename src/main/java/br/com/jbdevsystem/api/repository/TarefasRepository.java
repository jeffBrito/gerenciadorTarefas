package br.com.jbdevsystem.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.jbdevsystem.api.model.Tarefas;
import java.util.List;

@Repository
public interface TarefasRepository extends JpaRepository<Tarefas, Long>{
	
	Tarefas findByTitulo(String titulo);
	
	List<Tarefas> findByStatus(Integer status);	

}
