package br.gov.sp.fatec.projetoia.security;


public class Login {

    private String email;

    private String password;
  
    private Long idPapel;
  
    private String token;

    public Login() {}

    public Login(String email, String password) {
        this.email = email;
        this.password = password;
    }
    
    public String getEmail() {
        return email;
    }
  
    public void setEmail(String email) {
        this.email = email;
    }
  
    public String getPassword() {
        return password;
    }
  
    public void setPassword(String password) {
        this.password = password;
    }
  
    public String getToken() {
        return token;
    }
  
    public void setToken(String token) {
        this.token = token;
    }
  
    public Long getIdPapel() {
        return idPapel;
    }
  
    public void setIdPapel(Long idPapel) {
        this.idPapel = idPapel;
    }
      
  }