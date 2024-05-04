package br.gov.sp.fatec.projetoia.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.gov.sp.fatec.projetoia.entity.SaidaRedZoneEntity;
import br.gov.sp.fatec.projetoia.responses.PeopleCountResponse;
import br.gov.sp.fatec.projetoia.service.SaidaRedZoneService;

@RestController
@RequestMapping(value = "/saida-redzone")
@CrossOrigin
public class SaidaRedZoneController {

    @Autowired
    private SaidaRedZoneService serv;

    @GetMapping
    public List<SaidaRedZoneEntity> getAll() {
        return serv.getAll();
    }

    @GetMapping(value = { "/id" })
    public Optional<SaidaRedZoneEntity> getById(@PathVariable("id") Long id) {
        return serv.getById(id);
    }

    @GetMapping(value = "/people-by-redzone-filter")
    public ResponseEntity<PeopleCountResponse> countPeopleInRedZoneByFilter(
            @RequestParam(required = false) Long redZoneId) {
        Long numberOfPeople = serv.countPeopleInRedZone(redZoneId);
        PeopleCountResponse countResponse = new PeopleCountResponse(numberOfPeople.intValue());
        return ResponseEntity.ok(countResponse);
    }

}
