package br.gov.sp.fatec.projetoia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import br.gov.sp.fatec.projetoia.entity.EntradaRedZoneEntity;
import br.gov.sp.fatec.projetoia.service.EntradaRedZoneService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/entrada-redzone")
@CrossOrigin
public class EntradaRedZoneController {

    @Autowired
    private EntradaRedZoneService serv;

    @GetMapping
    public List<EntradaRedZoneEntity> getAll() {
        return serv.getAll();
    }

    @PostMapping
    public EntradaRedZoneEntity insert(@RequestBody EntradaRedZoneEntity data) {
        return serv.insert(data);
    }

    @GetMapping(value = "/{id}")
    public Optional<EntradaRedZoneEntity> getById(@PathVariable("id") Long id) {
        return serv.getById(id);
    }

    @DeleteMapping(value = "/{id}")
    public Optional<EntradaRedZoneEntity> deleteById(@PathVariable("id") Long id) {
        Optional<EntradaRedZoneEntity> data = serv.getById(id);
        if (data.isEmpty())
            return null;
        serv.delete(data.get());
        return data;
    }

    @PutMapping(value = "/{id}")
    public EntradaRedZoneEntity update(@PathVariable("id") Long id, @RequestBody EntradaRedZoneEntity updatedEntity) {
        Optional<EntradaRedZoneEntity> existingEntity = serv.getById(id);
        if (existingEntity.isEmpty()) {
            return null;
        }
        updatedEntity.setId(id);
        return serv.update(id, updatedEntity);
    }

    @GetMapping(value = "/count-by-date")
    public long countPeopleEnteredRedZoneByDate(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return serv.countPeopleEnteredRedZoneByDate(startDate, endDate);
    }

}
