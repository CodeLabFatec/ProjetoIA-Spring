package br.gov.sp.fatec.projetoia.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.sp.fatec.projetoia.entity.ExampleEntity;
import br.gov.sp.fatec.projetoia.repository.ExampleEntityRepository;

@Service
public class ExampleEntityService{
    @Autowired
    private ExampleEntityRepository exampleEntityRepository;

    public List<ExampleEntity> getAll(){
        return exampleEntityRepository.findAll();
    }
    public Optional<ExampleEntity> getById(Long id){
        return exampleEntityRepository.findById(id);
    }
    public ExampleEntity insert(ExampleEntity entity){
        return exampleEntityRepository.save(entity);
    }
    public void delete(ExampleEntity entity){
        exampleEntityRepository.delete(entity);
    }
}