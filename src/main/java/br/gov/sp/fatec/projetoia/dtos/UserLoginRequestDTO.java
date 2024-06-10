package br.gov.sp.fatec.projetoia.dtos;

public class UserLoginRequestDTO {

    private String email;
    private String senha;
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return senha;
    }
    public void setPassword(String senha) {
        this.senha = senha;
    }
    
}
