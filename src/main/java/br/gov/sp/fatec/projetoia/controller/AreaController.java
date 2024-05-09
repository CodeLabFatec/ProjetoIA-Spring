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

import br.gov.sp.fatec.projetoia.entity.AreaEntity;
import br.gov.sp.fatec.projetoia.service.AreaService;

@RestController
@RequestMapping(value = "/area")
@CrossOrigin
public class AreaController {

    @Autowired
    private AreaService serv;

    @GetMapping
    public List<AreaEntity> getAll() {
        return serv.getAll();
    }

    @PostMapping
    public AreaEntity insert(@RequestBody AreaEntity data) {
        return serv.insert(data);
    }

    @GetMapping(value = "/{id}")
    public Optional<AreaEntity> getById(@PathVariable("id") Long id) {
        return serv.getById(id);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) {
        Optional<AreaEntity> data = serv.getById(id);
        if (data.isEmpty()) {
            return ResponseEntity.notFound().build(); 
        }

        serv.delete(data.get());

        return ResponseEntity.noContent().build(); 
    }

    @PutMapping(value = "/{id}")
    public AreaEntity update(@PathVariable("id") Long id, @RequestBody AreaEntity updatedEntity) {
        Optional<AreaEntity> existingEntity = serv.getById(id);
        if (existingEntity.isEmpty()) {
            return null;
        }
        updatedEntity.setId(id);
        return serv.update(id, updatedEntity);
    }
}
