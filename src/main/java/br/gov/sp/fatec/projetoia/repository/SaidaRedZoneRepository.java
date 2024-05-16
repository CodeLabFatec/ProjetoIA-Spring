package br.gov.sp.fatec.projetoia.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.gov.sp.fatec.projetoia.entity.SaidaRedZoneEntity;

public interface SaidaRedZoneRepository extends JpaRepository<SaidaRedZoneEntity, Long>{

    @Query("SELECT COUNT(e) FROM SaidaRedZoneEntity e WHERE (:redZoneId IS NULL OR e.redZone.id = :redZoneId)")
    Long countPeopleInRedZone(@Param("redZoneId") Long redZoneId);

    List<SaidaRedZoneEntity> findByRedZoneStatus(boolean status);

    List<SaidaRedZoneEntity> findByRedZoneStatusAndDataBetweenOrderByData(boolean status, LocalDateTime startDate, LocalDateTime endDate);
    List<SaidaRedZoneEntity> findByRedZoneStatusAndDataGreaterThanEqualOrderByData(boolean status, LocalDateTime date);

    
}
