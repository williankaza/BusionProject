package br.com.fiap.my.transport.onibus.api.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name="TB_Onibus")
public class Onibus {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column( name="id_onibus" )
    private Long id;

    @Column( name="codigo_onibus" )
    private String codigo;

    @ManyToOne()
    @JoinColumn(name = "id_linha")
    private Linha linha;

    private boolean ativo;

    @OneToMany()
    @JoinColumn( name = "id_posicao")
    private Set<Posicao> posicao = new HashSet<>();

    public Onibus(){
        super();
    }

    public Onibus(Linha linha){
        super();
        this.setLinha(linha);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Linha getLinha() {
        return linha;
    }

    public void setLinha(Linha linha) {
        this.linha = linha;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public Set<Posicao> getPosicao() {
        return posicao;
    }

    public void setPosicao(Set<Posicao> posicao) {
        this.posicao = posicao;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}
