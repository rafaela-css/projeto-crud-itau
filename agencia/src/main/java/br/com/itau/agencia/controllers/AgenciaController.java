package br.com.itau.agencia.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.itau.agencia.dtos.AgenciaRecordDTO;
import br.com.itau.agencia.models.AgenciaModel;
import br.com.itau.agencia.repositories.AgenciaRepository;
import jakarta.validation.Valid;

@RestController
public class AgenciaController {

    @Autowired
    AgenciaRepository agenciaRepository;

    @PostMapping("/api/agencias")
    public ResponseEntity<AgenciaModel> saveAgencia(@RequestBody @Valid AgenciaRecordDTO agenciaRecordDTO) {
        var agenciaModel = new AgenciaModel();
        BeanUtils.copyProperties(agenciaRecordDTO, agenciaModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(agenciaRepository.save(agenciaModel));
    }

    @GetMapping("/api/agencias")
    public ResponseEntity<List<AgenciaModel>> getAllAgencias() {
        return ResponseEntity.status(HttpStatus.OK).body(agenciaRepository.findAll());
    }

    @GetMapping("/api/agencias/{id}")
    public ResponseEntity<Object> getOneProduct(@PathVariable(value = "id") UUID id){
        Optional<AgenciaModel> agencia = agenciaRepository.findById(id);

        if(agencia.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Agencia Não Encontrada - 404");
        }
        return ResponseEntity.status(HttpStatus.OK).body(agencia.get());
    }

    @PutMapping("/api/agencias/{id}")
    public ResponseEntity<Object> updateAgencia(@PathVariable(value = "id") UUID id, @RequestBody @Valid AgenciaRecordDTO agenciaRecordDTO) {
        
        Optional<AgenciaModel> agencia = agenciaRepository.findById(id);
        
        if (agencia.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Agencia Não Encontrada - 404");
        }

        var agenciaModel = agencia.get();
        BeanUtils.copyProperties(agenciaRecordDTO, agenciaModel);
        return ResponseEntity.status(HttpStatus.OK).body(agenciaRepository.save(agenciaModel));
    }

    @DeleteMapping("/api/agencias/{id}")
    public ResponseEntity<Object> deleteAgencia(@PathVariable(value = "id") UUID id) {
        Optional<AgenciaModel> agencia = agenciaRepository.findById(id);
        
        if (agencia.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Agencia Não Encontrada - 404");
        }

        agenciaRepository.delete(agencia.get());
        return ResponseEntity.status(HttpStatus.OK).body("Agencia deletada com sucesso - 200");
    }

}
