package br.com.fiap.my.transport.onibus.api.dto;

import br.com.fiap.my.transport.onibus.api.entity.Linha;
import br.com.fiap.my.transport.onibus.api.entity.Onibus;
import br.com.fiap.my.transport.onibus.api.entity.Rota;

import java.util.HashSet;
import java.util.Set;

public class LinhaDTO {

    private Long id;
    private String codigoLinha;
    private boolean ativo;
    private Set<Onibus> onibus;
    private Set<Rota> rotas;

    public LinhaDTO(){}
    public LinhaDTO(Long id, String codigoLinha, boolean ativo){
        super();
        this.id = id;
        this.codigoLinha = codigoLinha;
        this.ativo = ativo;
        this.onibus = new HashSet<>();
        this.rotas = new HashSet<>();
    }

    public LinhaDTO(Long id, String codigoLinha, boolean ativo, Set<Onibus> onibus, Set<Rota> rotas){
        super();
        this.id  = id;
        this.codigoLinha = codigoLinha;
        this.ativo = ativo;
        this.onibus = onibus;
        this.rotas = rotas;
    }

    public LinhaDTO(Linha linha) {
        super();
        this.id = linha.getId();
        this.codigoLinha = linha.getCodigo();
        this.ativo = linha.isAtivo();
        this.onibus = linha.getOnibus();
        this.rotas = linha.getRotas();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigoLinha() {
        return codigoLinha;
    }

    public void setCodigoLinha(String codigoLinha) {
        this.codigoLinha = codigoLinha;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public Set<Onibus> getOnibus() {
        return onibus;
    }

    public void setOnibus(Set<Onibus> onibus) {
        this.onibus = onibus;
    }

    public Set<Rota> getRotas() {
        return rotas;
    }

    public void setRotas(Set<Rota> rotas) {
        this.rotas = rotas;
    }
}
