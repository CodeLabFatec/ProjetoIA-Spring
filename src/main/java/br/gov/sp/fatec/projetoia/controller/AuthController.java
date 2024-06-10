package br.gov.sp.fatec.projetoia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.gov.sp.fatec.projetoia.dtos.UserLoginRequestDTO;
import br.gov.sp.fatec.projetoia.dtos.UserLoginResponseDTO;
import br.gov.sp.fatec.projetoia.entity.UserEntity;
import br.gov.sp.fatec.projetoia.exception.AuthenticationException;
import br.gov.sp.fatec.projetoia.repository.UserRepository;
import br.gov.sp.fatec.projetoia.service.JwtService;

@RestController
public class AuthController {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public UserLoginResponseDTO login(@RequestBody UserLoginRequestDTO userLoginRequest) {
        UserEntity user = userRepository.findByEmailAndPassword(userLoginRequest.getEmail(), userLoginRequest.getPassword());
        if (user != null) {
            UserLoginResponseDTO response = new UserLoginResponseDTO();
            response.setNome(user.getNome());
            response.setEmail(user.getEmail());
            response.setPapel(user.getPapel());
            response.setToken(jwtService.generateToken(user.getEmail(), user.getNome(), user.getPapel().getNome()));
            return response;
        } else {
            throw new AuthenticationException("Email ou senha inválidos");
        }
    }
}

