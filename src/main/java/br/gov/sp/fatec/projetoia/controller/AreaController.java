package br.gov.sp.fatec.projetoia.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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

    @PostMapping
    public ResponseEntity<AreaEntity> insert(@RequestBody AreaEntity area) {
        AreaEntity createdArea = areaService.insert(area);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdArea);
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
