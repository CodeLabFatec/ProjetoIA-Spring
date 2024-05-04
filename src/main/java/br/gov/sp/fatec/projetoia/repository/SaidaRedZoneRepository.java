package br.gov.sp.fatec.projetoia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.gov.sp.fatec.projetoia.entity.SaidaRedZoneEntity;

public interface SaidaRedZoneRepository extends JpaRepository<SaidaRedZoneEntity, Long>{

    @Query("SELECT COUNT(e) FROM SaidaRedZoneEntity e WHERE (:redZoneId IS NULL OR e.redZone.id = :redZoneId)")
    Long countPeopleInRedZone(@Param("redZoneId") Long redZoneId);
    
}
