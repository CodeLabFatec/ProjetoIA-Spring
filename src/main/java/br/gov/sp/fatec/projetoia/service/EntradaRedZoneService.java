package br.gov.sp.fatec.projetoia.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
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

    public List<EntradaRedZoneEntity> getAllWithTrueStatus() {
        return repo.findByRedZoneStatus(true);
    }

    public List<EntradaRedZoneEntity> getAllWithTrueStatusAndDateRange(LocalDate startDate, LocalDate endDate) {

        LocalDateTime firstDate = LocalDateTime.of(startDate, LocalTime.MIN);
        if (startDate != null && endDate != null) {
            LocalDateTime lastDate = LocalDateTime.of(endDate, LocalTime.MAX).truncatedTo(ChronoUnit.SECONDS);
            return repo.findByRedZoneStatusAndDataBetweenOrderByData(true, firstDate, lastDate);
        
        } else {
            return repo.findByRedZoneStatusAndDataGreaterThanEqualOrderByData(true, firstDate);
        }
    }

    public List<EntradaRedZoneEntity> getAllWithTrueStatusForDate(LocalDate specificDate) {
        LocalDateTime firstDate = LocalDateTime.of(specificDate, LocalTime.MIN);
        LocalDateTime lastDate = LocalDateTime.of(specificDate, LocalTime.MAX).truncatedTo(ChronoUnit.SECONDS);
        return repo.findByRedZoneStatusAndDataBetweenOrderByData(true, firstDate, lastDate);
    }

    public List<EntradaRedZoneEntity> findByFilters(Long areaId, Long redZoneId, LocalDate specificDate, LocalDate startDate, LocalDate endDate){
        LocalDateTime firstDate;
        LocalDateTime lastDate;
        if (specificDate != null){
            firstDate = LocalDateTime.of(specificDate, LocalTime.MIN);
            lastDate = LocalDateTime.of(specificDate, LocalTime.MAX).truncatedTo(ChronoUnit.SECONDS);
        }else if (startDate != null){
            firstDate = LocalDateTime.of(startDate, LocalTime.MIN);
            lastDate = LocalDateTime.of(endDate, LocalTime.MAX).truncatedTo(ChronoUnit.SECONDS);
        }else{
            firstDate = null;
            lastDate = null;
        }
        return repo.findByFilters(true, areaId, redZoneId, firstDate, lastDate);
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
