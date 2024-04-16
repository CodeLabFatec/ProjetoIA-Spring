package br.gov.sp.fatec.projetoia.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.sp.fatec.projetoia.entity.ExampleEntity;
import br.gov.sp.fatec.projetoia.service.ExampleEntityService;

@RestController
@RequestMapping(value = "/example")
@CrossOrigin
public class ExampleEntityController{

    @Autowired
    private ExampleEntityService service;

    @GetMapping
    public List<ExampleEntity> getAll(){
        return service.getAll();
    }
    @PostMapping
    public ExampleEntity insert(@RequestBody ExampleEntity data){
        return service.insert(data);
    }
    @GetMapping(value = "/{id}")
    public Optional<ExampleEntity> getById(@PathVariable("id") Long id){
        return service.getById(id);
    }
    @DeleteMapping(value = "/{id}")
    public Optional<ExampleEntity> deleteById(@PathVariable("id") Long id){
        Optional<ExampleEntity> data = service.getById(id);
        if(data.isEmpty()) return null;

        service.delete(data.get());

        return data;
    }
}