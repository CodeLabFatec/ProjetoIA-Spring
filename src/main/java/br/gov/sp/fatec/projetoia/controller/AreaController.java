package br.gov.sp.fatec.projetoia.controller;

import java.util.Optional;
import java.util.List;


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
import org.springframework.http.HttpStatus;


import br.gov.sp.fatec.projetoia.entity.AreaEntity;
import br.gov.sp.fatec.projetoia.service.AreaService;

@RestController
@RequestMapping(value = "/area")
@CrossOrigin
public class AreaController {

    @Autowired
    private AreaService areaService;

    @GetMapping
    public List<AreaEntity> getAll() {
        return areaService.getAll();
    }

    @PostMapping
    public ResponseEntity<AreaEntity> insert(@RequestBody AreaEntity area) {
        AreaEntity createdArea = areaService.insert(area);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdArea);
    }

    @GetMapping(value = "/{id}")
    public Optional<AreaEntity> getById(@PathVariable("id") Long id) {
        return areaService.getById(id);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) {
        Optional<AreaEntity> data = areaService.getById(id);
        if (data.isEmpty()) {
            return ResponseEntity.notFound().build(); 
        }
        areaService.delete(data.get());
        return ResponseEntity.noContent().build(); 
    }

    @PutMapping(value = "/activate/{id}")
    public ResponseEntity<Void> activateById(@PathVariable("id") Long id){
        Optional<AreaEntity> data = areaService.getById(id);
        if (data.isEmpty()) {
            return ResponseEntity.notFound().build(); 
        }
        areaService.activate(data.get());
        return ResponseEntity.noContent().build(); 
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<AreaEntity> update(@PathVariable("id") Long id, @RequestBody AreaEntity updatedArea) {
        Optional<AreaEntity> existingArea = areaService.getById(id);
        if (existingArea.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        updatedArea.setId(id);
        AreaEntity updated = areaService.update(id, updatedArea);
        return ResponseEntity.ok(updated);
    }

}

