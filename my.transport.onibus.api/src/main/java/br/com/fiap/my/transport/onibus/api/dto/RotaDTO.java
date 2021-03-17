package br.com.fiap.my.transport.onibus.api.dto;

import br.com.fiap.my.transport.onibus.api.entity.Linha;
import br.com.fiap.my.transport.onibus.api.entity.Rota;

public class RotaDTO {
    private Long id;
    private Integer ordem;
    private String latitude;
    private String longitude;
    private boolean ativo;
    private Linha linha;

    public RotaDTO(){ super(); }

    public RotaDTO(Rota rota){
        super();
        this.id = rota.getId();
        this.ativo = rota.isAtivo();
        this.latitude = rota.getLatitude();
        this.longitude = rota.getLongitude();
        this.linha = rota.getLinha();
        this.ordem = rota.getOrdem();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Linha getLinha() {
        return linha;
    }

    public void setLinha(Linha linha) {
        this.linha = linha;
    }
}
