package br.com.fiap.my.transport.onibus.api.dto;

import br.com.fiap.my.transport.onibus.api.entity.Linha;

public class RotaCreateUpdateDTO {
    private Integer ordem;
    private String latitude;
    private String longitude;
    private boolean ativo;

    public RotaCreateUpdateDTO(){ super(); }

    public Integer getOrdem() {
        return ordem;
    }

    public void setOrdem(Integer ordem) {
        this.ordem = ordem;
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

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}
