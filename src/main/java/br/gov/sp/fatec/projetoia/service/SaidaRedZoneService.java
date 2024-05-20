package br.gov.sp.fatec.projetoia.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
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
    

    public List<SaidaRedZoneEntity> getAllWithTrueStatus() {
        return repo.findByRedZoneStatus(true);
    }

    public List<SaidaRedZoneEntity> getAllWithTrueStatusAndDateRange(LocalDate startDate, LocalDate endDate) {

        LocalDateTime firstDate = LocalDateTime.of(startDate, LocalTime.MIN);
        if (startDate != null && endDate != null) {
            LocalDateTime lastDate = LocalDateTime.of(endDate, LocalTime.MAX).truncatedTo(ChronoUnit.SECONDS);
            return repo.findByRedZoneStatusAndDataBetweenOrderByData(true, firstDate, lastDate);
        
        } else {
            return repo.findByRedZoneStatusAndDataGreaterThanEqualOrderByData(true, firstDate);
        }
    }

    public List<SaidaRedZoneEntity> getAllWithTrueStatusForDate(LocalDate specificDate) {
        LocalDateTime firstDate = LocalDateTime.of(specificDate, LocalTime.MIN);
        LocalDateTime lastDate = LocalDateTime.of(specificDate, LocalTime.MAX).truncatedTo(ChronoUnit.SECONDS);
        return repo.findByRedZoneStatusAndDataBetweenOrderByData(true, firstDate, lastDate);
    }

    public List<SaidaRedZoneEntity> findByFilters(Long areaId, Long redZoneId, LocalDate specificDate, LocalDate startDate, LocalDate endDate){
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
