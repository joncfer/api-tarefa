package br.uninter.apitafefas.repository;

import br.uninter.apitafefas.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

// Esta interface herda todos os métodos básicos (save, findById, findAll, delete)
public interface TarefaRepository extends JpaRepository<Tarefa, Long> { // 
}