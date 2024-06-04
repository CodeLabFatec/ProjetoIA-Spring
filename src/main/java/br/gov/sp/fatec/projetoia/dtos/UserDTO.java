package br.gov.sp.fatec.projetoia.dtos;

public class UserDTO {
    private String Nome;
    private String Email;
    private Long IdPapel;

    public String getNome() { return this.Nome; }
    public String getEmail() { return this.Email; }
    public Long getIdPapel() { return this.IdPapel; }
    public void setNome(String nome) { this.Nome = nome; }
    public void setEmail(String email) { this.Email = email; }
    public void setIdPapel(Long idPapel) { this.IdPapel = idPapel; }
}
