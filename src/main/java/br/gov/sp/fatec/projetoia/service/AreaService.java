package br.gov.sp.fatec.projetoia.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.sp.fatec.projetoia.entity.AreaEntity;
import br.gov.sp.fatec.projetoia.entity.RedZoneEntity;
import br.gov.sp.fatec.projetoia.repository.AreaRepository;
import br.gov.sp.fatec.projetoia.repository.RedZoneRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class AreaService {
    @Autowired
    private AreaRepository repo;

    @Autowired
    private RedZoneRepository repoRedZone;

    public List<AreaEntity> getAll() {
        return repo.findAll();
    }

    public Optional<AreaEntity> getById(Long id) {
        return repo.findById(id);
    }

    public AreaEntity insert(AreaEntity entity) {
        entity.setStatus(true); 
        return repo.save(entity);
    }

    public void delete(AreaEntity entity) {
        List<RedZoneEntity> redZoneList = repoRedZone.findByAreaId(entity.getId());
        entity.setStatus(false);
        for(RedZoneEntity redZone : redZoneList){
            redZone.setStatus(false);
            repoRedZone.save(redZone);
        }
        repo.save(entity);
    }

    public void activate(AreaEntity entity){
        entity.setStatus(true);
        repo.save(entity);
    }

    public AreaEntity update(Long id, AreaEntity updatedEntity) {

        Optional<AreaEntity> existingEntityOptional = repo.findById(id);

        if (existingEntityOptional.isPresent()) {
            AreaEntity existingEntity = existingEntityOptional.get();
            existingEntity.setNome(updatedEntity.getNome());
            existingEntity.setDescricao(updatedEntity.getDescricao());
            existingEntity.setStatus(true);
            return repo.save(existingEntity);
        } else {
            throw new EntityNotFoundException("Area com o ID " + id + " não encontrada");
        }
    }
}
