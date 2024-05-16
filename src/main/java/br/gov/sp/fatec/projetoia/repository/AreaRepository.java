package br.gov.sp.fatec.projetoia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.gov.sp.fatec.projetoia.entity.AreaEntity;

public interface AreaRepository extends JpaRepository<AreaEntity, Long> {
    @Query("SELECT r FROM AreaEntity r WHERE r.status = true")
    List<AreaEntity> findAllByStatusTrue();
}
