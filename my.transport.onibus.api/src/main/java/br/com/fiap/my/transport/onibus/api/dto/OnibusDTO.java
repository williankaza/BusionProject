package br.com.fiap.my.transport.onibus.api.dto;

import br.com.fiap.my.transport.onibus.api.entity.Linha;
import br.com.fiap.my.transport.onibus.api.entity.Onibus;
import br.com.fiap.my.transport.onibus.api.entity.Posicao;

import java.util.Comparator;

public class OnibusDTO {
    private Long id;
    private String codigo;
    private Linha linha;
    private boolean ativo;
    private Posicao ultimaPosicao;

    public OnibusDTO(){
        super();
    }

    public OnibusDTO( Onibus onibus ){
        super();
        this.id = onibus.getId();
        this.codigo = onibus.getCodigo();
        this.ativo = onibus.isAtivo();
        this.linha = onibus.getLinha();
        this.ultimaPosicao = onibus
                .getPosicao()
                .stream()
                .sorted(Comparator.comparing(Posicao::getDataAtualizacao))
                .findFirst()
                .get();
    }
}
