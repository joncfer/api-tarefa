package br.uninter.apitafefas.controller;

import br.uninter.apitafefas.model.Tarefa;
import br.uninter.apitafefas.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController // Define que esta classe é um Controller REST
@RequestMapping("/tarefas") // Todos os endpoints começarão com /tarefas
public class TarefaController {

    @Autowired // Injeta o repositório para termos acesso ao banco
    private TarefaRepository tarefaRepository;

    // Requisito Funcional: Criar uma tarefa [cite: 12]
    @PostMapping
    public Tarefa criarTarefa(@RequestBody Tarefa tarefa) {
        return tarefaRepository.save(tarefa);
    }

    // Requisito Funcional: Consultar todas as tarefas [cite: 16]
    @GetMapping
    public List<Tarefa> listarTarefas() {
        return tarefaRepository.findAll();
    }

    // Requisito Funcional: Consultar uma tarefa específica pelo ID [cite: 17]
    @GetMapping("/{id}")
    public ResponseEntity<Tarefa> buscarPorId(@PathVariable Long id) {
        Optional<Tarefa> tarefa = tarefaRepository.findById(id);
        if (tarefa.isPresent()) {
            return ResponseEntity.ok(tarefa.get());
        }
        return ResponseEntity.notFound().build();
    }

    // Requisito Funcional: Atualizar uma tarefa existente [cite: 18]
    @PutMapping("/{id}")
    public ResponseEntity<Tarefa> atualizarTarefa(@PathVariable Long id, @RequestBody Tarefa tarefaDetails) {
        Optional<Tarefa> tarefaOptional = tarefaRepository.findById(id);

        if (tarefaOptional.isPresent()) {
            Tarefa tarefa = tarefaOptional.get();
            // Atualiza os campos [cite: 13, 14, 15]
            tarefa.setNome(tarefaDetails.getNome());
            tarefa.setDataEntrega(tarefaDetails.getDataEntrega());
            tarefa.setResponsavel(tarefaDetails.getResponsavel());
            
            Tarefa tarefaAtualizada = tarefaRepository.save(tarefa);
            return ResponseEntity.ok(tarefaAtualizada);
        }
        return ResponseEntity.notFound().build();
    }

    // Requisito Funcional: Remover uma tarefa [cite: 20]
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerTarefa(@PathVariable Long id) {
        if (tarefaRepository.existsById(id)) {
            tarefaRepository.deleteById(id);
            return ResponseEntity.noContent().build(); // Retorna 204 No Content (sucesso sem corpo)
        }
        return ResponseEntity.notFound().build(); // Retorna 404 Not Found
    }
}