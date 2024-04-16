package br.gov.sp.fatec.projetoia.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.sp.fatec.projetoia.entity.ExampleEntity;

public interface ExampleEntityRepository extends JpaRepository<ExampleEntity, Long>{
    
}
