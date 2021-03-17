package br.com.fiap.my.transport.onibus.api.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="TB_Posicao")
public class Posicao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_posicao")
    private Long id;

    @ManyToOne()
    @JoinColumn( name="id_onibus" )
    private Onibus onibus;

    private String latitude;
    private String longitude;
    private LocalDateTime dataAtualizacao;
    private int lotacaoAtual;

    public Posicao(){}

    public Posicao(Onibus onibus){
        this.onibus = onibus;
    }

    public int getLotacaoAtual() {
        return lotacaoAtual;
    }

    public void setLotacaoAtual(int lotacaoAtual) {
        this.lotacaoAtual = lotacaoAtual;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Onibus getOnibus() {
        return onibus;
    }

    public void setOnibus(Onibus onibus) {
        this.onibus = onibus;
    }
}
