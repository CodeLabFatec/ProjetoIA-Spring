package br.gov.sp.fatec.projetoia.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.sp.fatec.projetoia.entity.PaperEntity;

public interface PaperRepository extends JpaRepository<PaperEntity, Long>{
    
}
