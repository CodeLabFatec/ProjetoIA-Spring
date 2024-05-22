package br.gov.sp.fatec.projetoia.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.sp.fatec.projetoia.dtos.RedZoneDTO;
import br.gov.sp.fatec.projetoia.entity.AreaEntity;
import br.gov.sp.fatec.projetoia.entity.RedZoneEntity;
import br.gov.sp.fatec.projetoia.repository.AreaRepository;
import br.gov.sp.fatec.projetoia.repository.RedZoneRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class RedZoneService {
    @Autowired
    private RedZoneRepository repo;

    @Autowired
    private AreaRepository areaRepo;

    public List<RedZoneEntity> getAll() {
        return repo.findAll();
    }

    public Optional<RedZoneEntity> getById(Long id) {
        return repo.findById(id);
    }

    public RedZoneEntity insert(RedZoneDTO entity) {
        RedZoneEntity redZone = new RedZoneEntity();
        AreaEntity areaEntity = areaRepo.findById(entity.getAreaId()).get();
        redZone.setData(LocalDateTime.now());
        redZone.setStatus(true);
        redZone.setNome(entity.getNome());
        redZone.setDescricao(entity.getDescricao());
        redZone.setArea(areaEntity);
        return repo.save(redZone);
    }

    public void delete(RedZoneEntity entity) {
        entity.setStatus(false);
        repo.save(entity);
    }

    public RedZoneEntity update(Long id, RedZoneDTO updatedEntity) {

        Optional<RedZoneEntity> existingEntityOptional = repo.findById(id);

        if (existingEntityOptional.isPresent()) {
            AreaEntity areaEntity = areaRepo.findById(updatedEntity.getAreaId()).get();
            RedZoneEntity existingEntity = existingEntityOptional.get();
            existingEntity.setNome(updatedEntity.getNome());
            existingEntity.setDescricao(updatedEntity.getDescricao());
            existingEntity.setArea(areaEntity);
            existingEntity.setData(LocalDateTime.now());
            existingEntity.setStatus(true);
            return repo.save(existingEntity);
        } else {
            throw new EntityNotFoundException("Entidade com o ID " + id + " não encontrada");
        }
    }

    public void activate(RedZoneEntity entity){
        entity.setStatus(true);
        repo.save(entity);
    }
}