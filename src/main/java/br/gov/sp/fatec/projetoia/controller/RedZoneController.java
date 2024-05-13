package br.gov.sp.fatec.projetoia.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.sp.fatec.projetoia.dtos.RedZoneDTO;
import br.gov.sp.fatec.projetoia.entity.RedZoneEntity;
import br.gov.sp.fatec.projetoia.service.RedZoneService;

@RestController
@RequestMapping(value = "/redzone")
@CrossOrigin
public class RedZoneController {

    @Autowired
    private RedZoneService serv;

    @GetMapping
    public List<RedZoneEntity> getAll() {
        return serv.getAll();
    }

    @PostMapping
    public RedZoneEntity insert(@RequestBody RedZoneDTO data) {
        return serv.insert(data);
    }

    @GetMapping(value = "/{id}")
    public Optional<RedZoneEntity> getById(@PathVariable("id") Long id) {
        return serv.getById(id);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) {
        Optional<RedZoneEntity> data = serv.getById(id);
        if (data.isEmpty()) {
            return ResponseEntity.notFound().build(); // Retorna status 404 Not Found se a entidade não for encontrada
        }

        serv.delete(data.get());

        return ResponseEntity.noContent().build(); // Retorna status 204 No Content indicando que a solicitação foi processada com sucesso
    }

    @PutMapping(value = "/{id}")
    public RedZoneEntity update(@PathVariable("id") Long id, @RequestBody RedZoneDTO updatedEntity) {
        Optional<RedZoneEntity> existingEntity = serv.getById(id);
        if (existingEntity.isEmpty()) {
            return null;
        }
        return serv.update(id, updatedEntity);
    }

}