package br.gov.sp.fatec.projetoia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import br.gov.sp.fatec.projetoia.entity.EntradaRedZoneEntity;
import br.gov.sp.fatec.projetoia.service.EntradaRedZoneService;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/entrada-redzone")
@CrossOrigin
public class EntradaRedZoneController {

    @Autowired
    private EntradaRedZoneService redZoneserv;

    @GetMapping
    public List<EntradaRedZoneService> getAll() {
        return redZoneserv.
    }

    @PostMapping
    public EntradaRedZoneService insert(@RequestBody EntradaRedZoneService data) {
        return redZoneserv.insert(data);
    }

    @GetMapping(value = "/{id}")
    public Optional<EntradaRedZoneService> getById(@PathVariable("id") Long id) {
        return redZoneserv.getById(id);
    }

    @DeleteMapping(value = "/{id}")
    public Optional<EntradaRedZoneService> deleteById(@PathVariable("id") Long id) {
        Optional<EntradaRedZoneService> data = redZoneserv.getById(id);
        if (data.isEmpty())
            return null;

        redZoneserv.delete(data.get());

        return data;
    }

    @PutMapping(value = "/{id}")
    public EntradaRedZoneService update(@PathVariable("id") Long id, @RequestBody EntradaRedZoneService updatedEntity) {
        Optional<EntradaRedZoneService> existingEntity = redZoneserv.getById(id);
        if (existingEntity.isEmpty()) {
            return null;
        }
        updatedEntity.setId(id);
        return redZoneserv.update(id, updatedEntity);
    }
}
