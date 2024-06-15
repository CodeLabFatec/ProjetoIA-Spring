package br.gov.sp.fatec.projetoia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import br.gov.sp.fatec.projetoia.dtos.RecoverPasswordDTO;
import br.gov.sp.fatec.projetoia.entity.UserEntity;
import br.gov.sp.fatec.projetoia.entity.UserPasswordTokenEntity;
import br.gov.sp.fatec.projetoia.repository.UserRepository;
import br.gov.sp.fatec.projetoia.responses.AuthResponse;
import br.gov.sp.fatec.projetoia.security.JwtUtils;
import br.gov.sp.fatec.projetoia.security.Login;
import br.gov.sp.fatec.projetoia.service.UserService;

@RestController
@CrossOrigin
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authManager;
  
    @PostMapping("/login")
    public AuthResponse autenticar(@RequestBody Login login) throws JsonProcessingException {
      Authentication auth = new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword());
      auth = authManager.authenticate(auth);
      login.setToken(JwtUtils.generateToken(auth));
      login.setIdPapel(Long.parseLong(auth.getAuthorities().iterator().next().getAuthority()));

      UserEntity user = userRepository.findByEmail(login.getEmail());
      AuthResponse authResponse = new AuthResponse(user, login.getToken());

      return authResponse;
    }

    @PostMapping("/recover")
    public ResponseEntity<Void> recover(@RequestBody RecoverPasswordDTO dto){
        UserEntity user = userRepository.findByEmail(dto.getEmail());
        if(user == null) return ResponseEntity.notFound().build();

        userService.createPasswordResetTokenForUser(user.getId());

        return ResponseEntity.ok().build();
    }
    @PostMapping("/token")
    public ResponseEntity<Void> token(@RequestBody RecoverPasswordDTO dto) throws Exception{
        if(dto == null) return ResponseEntity.badRequest().build();

        UserPasswordTokenEntity userPasswordTokenEntity = userService.getUserByPasswordResetToken(dto.getToken());
        if(userPasswordTokenEntity == null) return ResponseEntity.notFound().build();

        userService.changeUserPassword(userPasswordTokenEntity.getUser(), dto.getPassword(), userPasswordTokenEntity);

        return ResponseEntity.ok().build();
    }
}

