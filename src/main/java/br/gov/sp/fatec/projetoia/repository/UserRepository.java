package br.gov.sp.fatec.projetoia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.sp.fatec.projetoia.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long>{
    
    public List<UserEntity> findByEmail(String email);

    public UserEntity findByEmailAndPassword(String email, String senha);

}
