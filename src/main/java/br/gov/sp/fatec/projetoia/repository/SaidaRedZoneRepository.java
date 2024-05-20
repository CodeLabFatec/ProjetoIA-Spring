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

    @Query("SELECT s FROM SaidaRedZoneEntity s " +
           "WHERE s.redZone.status = :status " +
           "AND (:areaId IS NULL OR s.redZone.area.id = :areaId) " +
           "AND (:redZoneId IS NULL OR s.redZone.id = :redZoneId) " +
           "AND (:startDate IS NULL OR s.data BETWEEN :startDate AND :endDate) " + 
           "ORDER BY s.data")
    List<SaidaRedZoneEntity> findByFilters(
        @Param("status") boolean status, 
        @Param("areaId") Long areaId,
        @Param("redZoneId") Long redZoneId,
        @Param("startDate") LocalDateTime startDate, 
        @Param("endDate") LocalDateTime endDate);
    
}
