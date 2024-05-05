package br.gov.sp.fatec.projetoia.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.sp.fatec.projetoia.entity.RedZoneEntity;
import br.gov.sp.fatec.projetoia.repository.RedZoneRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class RedZoneService {
    @Autowired
    private RedZoneRepository repo;

    public List<RedZoneEntity> getAll() {
        return repo.findAllByStatusTrue();
    }

    public Optional<RedZoneEntity> getById(Long id) {
        return repo.findById(id);
    }

    public RedZoneEntity insert(RedZoneEntity entity) {
        entity.setData(LocalDateTime.now());
        entity.setStatus(true);
        return repo.save(entity);
    }

    public void delete(RedZoneEntity entity) {
        entity.setStatus(false);
        repo.save(entity);
    }

    public RedZoneEntity update(Long id, RedZoneEntity updatedEntity) {

        Optional<RedZoneEntity> existingEntityOptional = repo.findById(id);

        if (existingEntityOptional.isPresent()) {
            RedZoneEntity existingEntity = existingEntityOptional.get();
            existingEntity.setNome(updatedEntity.getNome());
            existingEntity.setDescricao(updatedEntity.getDescricao());
            return repo.save(existingEntity);
        } else {
            throw new EntityNotFoundException("Entidade com o ID " + id + " não encontrada");
        }
    }
}