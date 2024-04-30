package br.gov.sp.fatec.projetoia.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.sp.fatec.projetoia.entity.EntradaRedZoneEntity;
import br.gov.sp.fatec.projetoia.repository.EntradaRedZoneRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
@Transactional
public class EntradaRedZoneService {

    @Autowired
    private EntradaRedZoneRepository repo;

    public List<EntradaRedZoneEntity> getAll() {
        return repo.findAll();
    }

    public Optional<EntradaRedZoneEntity> getById(Long id) {
        return repo.findById(id);
    }

    public EntradaRedZoneEntity insert(EntradaRedZoneEntity entity) {
        entity.setData(LocalDateTime.now());
        return repo.save(entity);
    }

    public void delete(EntradaRedZoneEntity entity) {
        repo.delete(entity);
    }

    public EntradaRedZoneEntity update(Long id, EntradaRedZoneEntity updatedEntity) {
        Optional<EntradaRedZoneEntity> existingEntityOptional = repo.findById(id);

        if (existingEntityOptional.isPresent()) {
            EntradaRedZoneEntity existingEntity = existingEntityOptional.get();
            existingEntity.setData(updatedEntity.getData());
            return repo.save(existingEntity);
        } else {
            throw new EntityNotFoundException("Entidade de entrada com o ID " + id + " não encontrada");
        }
    }

    public long countPeopleEnteredRedZoneByDate(LocalDate startDate, LocalDate endDate) {
        return repo.countByDateBetween(startDate, endDate);
    }

    public Long countPeopleInRedZone(Long redZoneId) {
        return repo.countPeopleInRedZone(redZoneId);
    }
        

}
