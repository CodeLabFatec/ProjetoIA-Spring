package br.gov.sp.fatec.projetoia.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.sp.fatec.projetoia.entity.SaidaRedZoneEntity;
import br.gov.sp.fatec.projetoia.repository.SaidaRedZoneRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class SaidaRedZoneService {

    @Autowired
    private SaidaRedZoneRepository repo;
    

    public List<SaidaRedZoneEntity> getAllWithTrueStatus(){
        return repo.findByRedZoneStatus(true);
    }

    public Optional<SaidaRedZoneEntity> getById(Long id){
        return repo.findById(id);
    }

    public SaidaRedZoneEntity insert(SaidaRedZoneEntity entity){
        entity.setData(LocalDateTime.now());
        return repo.save(entity);
    }

    public void delete(SaidaRedZoneEntity entity){
        repo.delete(entity);
    }

    public SaidaRedZoneEntity update(Long id, SaidaRedZoneEntity updatedEntity) {
        Optional<SaidaRedZoneEntity> existingEntityOptional = repo.findById(id);

        if (existingEntityOptional.isPresent()) {
            SaidaRedZoneEntity existingEntity = existingEntityOptional.get();
            existingEntity.setData(updatedEntity.getData());
            return repo.save(existingEntity);
        } else {
            throw new EntityNotFoundException("Entidade de saida com o ID " + id + " não encontrada");
        }
    }

    public Long countPeopleInRedZone(Long redZoneId) {
        return repo.countPeopleInRedZone(redZoneId);
    }

}
