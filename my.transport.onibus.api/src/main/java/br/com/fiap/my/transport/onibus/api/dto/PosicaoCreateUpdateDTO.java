package br.com.fiap.my.transport.onibus.api.dto;

import br.com.fiap.my.transport.onibus.api.entity.Onibus;

import java.time.LocalDateTime;

public class PosicaoCreateUpdateDTO {
    private String latitude;
    private String longitude;
    private int lotacaoAtual;

    public PosicaoCreateUpdateDTO(){
        super();
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

    public int getLotacaoAtual() {
        return lotacaoAtual;
    }

    public void setLotacaoAtual(int lotacaoAtual) {
        this.lotacaoAtual = lotacaoAtual;
    }
}
