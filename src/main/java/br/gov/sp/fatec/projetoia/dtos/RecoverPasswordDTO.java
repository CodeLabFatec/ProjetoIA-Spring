package br.gov.sp.fatec.projetoia.dtos;

public class RecoverPasswordDTO {
    private String email;
    private String token;
    private String password;

    public String getEmail() {
        return this.email; 
    }

    public String getToken() { 
        return this.token; 
    }
    
    public String getPassword() { 
        return this.password; 
    }
    
    public void setEmail(String email) { 
        this.email = email; 
    }
    
    public void setToken(String token) { 
        this.token = token; 
    }
    
    public void setPassword(String password) { 
        this.password = password; 
    }
}
