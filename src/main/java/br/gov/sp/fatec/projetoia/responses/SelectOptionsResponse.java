package br.gov.sp.fatec.projetoia.responses;

import br.gov.sp.fatec.projetoia.entity.AreaEntity;
import br.gov.sp.fatec.projetoia.entity.PaperEntity;
import br.gov.sp.fatec.projetoia.entity.RedZoneEntity;
import br.gov.sp.fatec.projetoia.entity.UserEntity;

public class SelectOptionsResponse {
    public Long Id;
    public String Nome;

    public SelectOptionsResponse(RedZoneEntity redzone){
        this.Id = redzone.getId();
        this.Nome = redzone.getNome();
    }

    public SelectOptionsResponse(AreaEntity area){
        this.Id = area.getId();
        this.Nome = area.getNome();
    }

    public SelectOptionsResponse(PaperEntity papel){
        this.Id = papel.getId();
        this.Nome = papel.getNome();
    }

    public SelectOptionsResponse(UserEntity user){
        this.Id = user.getId();
        this.Nome = user.getNome();
    }

    public Long getId() { return Id; }
    public void setId(Long id) { this.Id = id; }
    public String getNome() { return Nome; }
    public void setNome(String nome) { this.Nome = nome; }
}
