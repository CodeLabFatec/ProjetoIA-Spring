package br.gov.sp.fatec.projetoia.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.sp.fatec.projetoia.entity.UserEntity;
import br.gov.sp.fatec.projetoia.entity.UserPasswordTokenEntity;

public interface UserPasswordTokenRepository extends JpaRepository<UserPasswordTokenEntity, Long> {
    Optional<UserPasswordTokenEntity> findByToken(String token);
    void deleteByUser(UserEntity user);
}
