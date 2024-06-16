package br.gov.sp.fatec.projetoia.responses;

import java.util.List;

import br.gov.sp.fatec.projetoia.entity.AreaEntity;
import br.gov.sp.fatec.projetoia.entity.RedZoneEntity;
import br.gov.sp.fatec.projetoia.entity.UserEntity;

public class AuthResponse {
    public Long idUsuario;
    private String email;  
    private Long idPapel;
    private String token;
    private List<AreaEntity> areas;
    private List<RedZoneEntity> redzones;

    public AuthResponse(Long idUsuario, String email, Long idPapel, String token, List<AreaEntity> areas, List<RedZoneEntity> redzones){
        this.idPapel = idPapel;
        this.idUsuario = idUsuario;
        this.email = email;
        this.token = token;
        this.areas = areas;
        this.redzones = redzones;
    }

    public AuthResponse(UserEntity user, String token){
        this.idPapel = user.getPapel().getId();
        this.idUsuario = user.getId();
        this.email = user.getEmail();
        this.token = token;
        this.areas = user.getAreas().stream().toList();
        this.redzones = user.getRedzones().stream().toList();        
    }

    public String getEmail() {
        return email;
    }
  
    public void setEmail(String email) {
        this.email = email;
    }
  
    public Long getIdUsuario() {
        return idUsuario;
    }
  
    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
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
    public List<AreaEntity> getAreas(){
        return this.areas;
    }
    public void setAreas(List<AreaEntity> areas){
        this.areas = areas;
    }
    public List<RedZoneEntity> getRedzones(){
        return this.redzones;
    }
    public void setRedzones(List<RedZoneEntity> redzones){
        this.redzones = redzones;
    }
}
