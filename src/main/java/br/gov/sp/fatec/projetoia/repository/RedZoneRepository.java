package br.gov.sp.fatec.projetoia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.gov.sp.fatec.projetoia.entity.RedZoneEntity;

public interface RedZoneRepository extends JpaRepository<RedZoneEntity, Long>{
    @Query("SELECT r FROM RedZoneEntity r WHERE r.status = true")
    List<RedZoneEntity> findAllByStatusTrue();

    List<RedZoneEntity> findByAreaId(Long id);
}
