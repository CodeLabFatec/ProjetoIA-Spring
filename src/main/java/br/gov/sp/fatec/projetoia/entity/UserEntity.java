package br.gov.sp.fatec.projetoia.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;


@Entity
@Table(name = "usuario")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "email")
    private String email;

    @Column(name = "senha")
    private String senha;

    @OneToOne
    @JoinColumn(name = "id_papel")
    private PaperEntity papel;

    @ManyToMany
    @JoinTable(
        name = "usuario_area",
        joinColumns = @JoinColumn(name = "id_usuario"),
        inverseJoinColumns = @JoinColumn(name = "id_area")
    )
    private Set<AreaEntity> areas = new HashSet<>();

    @ManyToMany
    @JoinTable(
        name = "usuario_redzone",
        joinColumns = @JoinColumn(name = "id_usuario"),
        inverseJoinColumns = @JoinColumn(name = "id_redzone")
    )
    private Set<RedZoneEntity> redzones = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public PaperEntity getPapel() {
        return papel;
    }

    public void setPapel(PaperEntity papel) {
        this.papel = papel;
    }

    public Set<AreaEntity> getAreas(){
        return areas;
    }

    public void setAreas(Set<AreaEntity> areas) {
        this.areas = areas;
    }

    public Set<RedZoneEntity> getRedzones(){
        return redzones;
    }
    
    public void setRedzones(Set<RedZoneEntity> redzones) {
        this.redzones = redzones;
    }
}

