package br.gov.sp.fatec.projetoia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.gov.sp.fatec.projetoia.entity.UserEntity;
import br.gov.sp.fatec.projetoia.repository.UserRepository;

@Service
public class SegurancaService implements UserDetailsService {

    @Autowired
    private UserRepository usuarioRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity usuario = usuarioRepo.findByName(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado!"));

        return User.withUsername(username)
                .password(usuario.getPassword())
                .authorities(usuario.getPapel().getNome()) // Obtém diretamente o nome do papel
                .build();
    }
}
