package br.com.fiap.my.transport.onibus.api.dto;

import br.com.fiap.my.transport.onibus.api.entity.Onibus;
import br.com.fiap.my.transport.onibus.api.entity.Posicao;
import org.springframework.stereotype.Service;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Service
public class PosicaoDTO {
    private Long id;
    private Onibus onibus;
    private String latitude;
    private String longitude;
    private LocalDateTime dataAtualizacao;
    private int lotacaoAtual;

    public PosicaoDTO(){ super(); }
    public PosicaoDTO(Posicao posicao){
        this.id = posicao.getId();
        this.onibus = posicao.getOnibus();
        this.latitude = posicao.getLatitude();
        this.longitude = posicao.getLongitude();
        this.dataAtualizacao = posicao.getDataAtualizacao();
        this.lotacaoAtual = posicao.getLotacaoAtual();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Onibus getOnibus() {
        return onibus;
    }

    public void setOnibus(Onibus onibus) {
        this.onibus = onibus;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public LocalDateTime getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(LocalDateTime dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    public int getLotacaoAtual() {
        return lotacaoAtual;
    }

    public void setLotacaoAtual(int lotacaoAtual) {
        this.lotacaoAtual = lotacaoAtual;
    }
}
