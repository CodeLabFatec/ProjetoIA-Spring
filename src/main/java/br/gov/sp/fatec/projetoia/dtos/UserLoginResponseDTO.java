package br.gov.sp.fatec.projetoia.dtos;

import br.gov.sp.fatec.projetoia.entity.PaperEntity;

public class UserLoginResponseDTO {

    public String nome;
    public String email;
    public PaperEntity papel;
    public String token;
    
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public PaperEntity getPapel() {
        return papel;
    }
    public void setPapel(PaperEntity papel) {
        this.papel = papel;
    }
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    


    
}
