package br.gov.sp.fatec.projetoia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.gov.sp.fatec.projetoia.dtos.RecoverPasswordDTO;
import br.gov.sp.fatec.projetoia.dtos.UserLoginRequestDTO;
import br.gov.sp.fatec.projetoia.dtos.UserLoginResponseDTO;
import br.gov.sp.fatec.projetoia.entity.UserEntity;
import br.gov.sp.fatec.projetoia.entity.UserPasswordTokenEntity;
import br.gov.sp.fatec.projetoia.exception.AuthenticationException;
import br.gov.sp.fatec.projetoia.repository.UserRepository;
import br.gov.sp.fatec.projetoia.service.JwtService;
import br.gov.sp.fatec.projetoia.service.UserService;

@RestController
public class AuthController {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

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
    @PostMapping("/recover/{email}")
    public ResponseEntity<Void> recover(@PathVariable String email){
        UserEntity user = userRepository.findByEmail(email);
        if(user == null) return ResponseEntity.notFound().build();

        userService.createPasswordResetTokenForUser(user.getId());

        return ResponseEntity.ok().build();
    }
    @PostMapping("/token")
    public ResponseEntity<Void> token(@RequestBody RecoverPasswordDTO dto){
        if(dto == null) return ResponseEntity.badRequest().build();

        UserPasswordTokenEntity userPasswordTokenEntity = userService.getUserByPasswordResetToken(dto.getToken());
        if(userPasswordTokenEntity == null) return ResponseEntity.notFound().build();

        userService.changeUserPassword(userPasswordTokenEntity.getUser(), dto.getPassword(), userPasswordTokenEntity);

        return ResponseEntity.ok().build();
    }
}

