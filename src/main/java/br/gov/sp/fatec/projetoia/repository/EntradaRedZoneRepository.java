package br.gov.sp.fatec.projetoia.repository;

import br.gov.sp.fatec.projetoia.entity.EntradaRedZoneEntity;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EntradaRedZoneRepository extends JpaRepository<EntradaRedZoneEntity, Long> {

    @Query("SELECT COUNT(e) FROM EntradaRedZoneEntity e WHERE DATE(e.data) BETWEEN :startDate AND :endDate")
    long countByDateBetween(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query("SELECT COUNT(e) FROM EntradaRedZoneEntity e WHERE (:redZoneId IS NULL OR e.redZone.id = :redZoneId)")
    Long countPeopleInRedZone(@Param("redZoneId") Long redZoneId);

}
