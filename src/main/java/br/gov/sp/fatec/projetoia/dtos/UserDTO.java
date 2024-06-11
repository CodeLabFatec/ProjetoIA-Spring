package br.gov.sp.fatec.projetoia.dtos;

import java.util.List;

public class UserDTO {
    private String Nome;
    private String Email;
    private Long IdPapel;
    private List<Long> Areas;
    private List<Long> Redzones;

    public String getNome() { return this.Nome; }
    public String getEmail() { return this.Email; }
    public Long getIdPapel() { return this.IdPapel; }
    public List<Long> getAreas() { return this.Areas; }
    public List<Long> getRedzones() { return this.Redzones; }
    public void setNome(String nome) { this.Nome = nome; }
    public void setEmail(String email) { this.Email = email; }
    public void setIdPapel(Long idPapel) { this.IdPapel = idPapel; }
    public void setAreas(List<Long> areas) { this.Areas = areas; }
    public void setRedzones(List<Long> redzones) { this.Redzones = redzones; }
}
