package br.gov.sp.fatec.projetoia.security;

import java.util.Date;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.IOException;
import io.jsonwebtoken.security.Keys;

public class JwtUtils {

    private static final String KEY = "br.gov.sp.fatec.projetoiasecuritytoken";

    public static String generateToken(Authentication usuario) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Login usuarioSemSenha = new Login();
        usuarioSemSenha.setEmail(usuario.getName());
        if (!usuario.getAuthorities().isEmpty()) {
            Long idPapel = Long.parseLong(usuario.getAuthorities().stream().toList().get(0).getAuthority());

            usuarioSemSenha.setIdPapel(idPapel);
        }
        String usuarioJson = mapper.writeValueAsString(usuarioSemSenha);
        Date agora = new Date();
        Long hora = 1000L * 60L * 60L * 24; // Um dia
        return Jwts.builder()
            .claim("userDetails", usuarioJson)
            .setIssuer("br.gov.sp.fatec")
            .setSubject(usuario.getName())
            .setExpiration(new Date(agora.getTime() + hora))
            .signWith(Keys.hmacShaKeyFor(KEY.getBytes()), SignatureAlgorithm.HS256).compact();
    }
  
    public static Authentication parseToken(String token)
        throws IOException, JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String credentialsJson = Jwts.parserBuilder()
            .setSigningKey(Keys.hmacShaKeyFor(KEY.getBytes())).build()
            .parseClaimsJws(token).getBody().get("userDetails", String.class);
        Login usuario = mapper.readValue(credentialsJson, Login.class);

        UserDetails userDetails = User.builder().username(usuario.getEmail()).password("secret")
            .authorities(usuario.getIdPapel().toString()).build();
        return new UsernamePasswordAuthenticationToken(usuario.getEmail(), usuario.getPassword(),
            userDetails.getAuthorities());
    }

}