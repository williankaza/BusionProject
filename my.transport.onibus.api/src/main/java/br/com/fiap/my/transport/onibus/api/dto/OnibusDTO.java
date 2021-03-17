package br.com.fiap.my.transport.onibus.api.dto;

import br.com.fiap.my.transport.onibus.api.entity.Linha;
import br.com.fiap.my.transport.onibus.api.entity.Onibus;
import br.com.fiap.my.transport.onibus.api.entity.Posicao;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class OnibusDTO {
    private Long id;
    private String codigo;
    private Linha linha;
    private boolean ativo;
    private Posicao posicao;

    public OnibusDTO(){
        super();
    }

    public OnibusDTO( Onibus onibus ){
        super();
        this.id = onibus.getId();
        this.codigo = onibus.getCodigo();
        this.ativo = onibus.isAtivo();
        this.linha = onibus.getLinha();

        Optional<Posicao> ultPosicao = onibus
                .getPosicao()
                .stream()
                .sorted(Comparator.comparing(Posicao::getDataAtualizacao).reversed())
                .findFirst();

        if (!ultPosicao.isEmpty()){
            this.posicao = ultPosicao.get();
        }

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

    public Linha getLinha() {
        return linha;
    }

    public void setLinha(Linha linha) {
        this.linha = linha;
    }

    public Posicao getPosicao() {
        return posicao;
    }

    public void setPosicao(Posicao posicao) {
        this.posicao = posicao;
    }
}
