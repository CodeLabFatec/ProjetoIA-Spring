package br.gov.sp.fatec.projetoia.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.sp.fatec.projetoia.entity.RedZoneEntity;
import br.gov.sp.fatec.projetoia.service.RedZoneService;

@RestController
@RequestMapping(value = "/redzone")
@CrossOrigin
public class RedZoneController {

    @Autowired
    private RedZoneService redZoneserv;

    @GetMapping
    public List<RedZoneEntity> getAll() {
        return redZoneserv.getAll();
    }

    @PostMapping
    public RedZoneEntity insert(@RequestBody RedZoneEntity data) {
        return redZoneserv.insert(data);
    }

    @GetMapping(value = "/{id}")
    public Optional<RedZoneEntity> getById(@PathVariable("id") Long id) {
        return redZoneserv.getById(id);
    }

    @DeleteMapping(value = "/{id}")
    public Optional<RedZoneEntity> deleteById(@PathVariable("id") Long id) {
        Optional<RedZoneEntity> data = redZoneserv.getById(id);
        if (data.isEmpty())
            return null;

        redZoneserv.delete(data.get());

        return data;
    }

    @PutMapping(value = "/{id}")
    public RedZoneEntity update(@PathVariable("id") Long id, @RequestBody RedZoneEntity updatedEntity) {
        Optional<RedZoneEntity> existingEntity = redZoneserv.getById(id);
        if (existingEntity.isEmpty()) {
            return null;
        }
        updatedEntity.setId(id);
        return redZoneserv.update(id, updatedEntity);
    }
}