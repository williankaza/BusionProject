package br.com.fiap.my.transport.onibus.api.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="TB_Linha")
public class Linha {
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    @Column(name = "id_linha")
    private Long id;

    @Column( name="codigo_linha", unique = true)
    private String codigo;

    private boolean ativo;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Rota> rotas = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Onibus> onibus = new HashSet<>();

    public Set<Rota> getRotas() {
        return rotas;
    }

    public void setRotas(Set<Rota> rotas) {
        this.rotas = rotas;
    }

    public Set<Onibus> getOnibus() {
        return onibus;
    }

    public void setOnibus(Set<Onibus> onibus) {
        this.onibus = onibus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}
