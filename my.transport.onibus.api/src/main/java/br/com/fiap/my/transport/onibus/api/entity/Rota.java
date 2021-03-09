package br.com.fiap.my.transport.onibus.api.entity;

import javax.persistence.*;

@Entity
@Table(name = "TB_Rota")
public class Rota {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column(name = "id_rota")
    private Long id;
    private String latitude;
    private String longitude;
    private boolean ativo;

    @ManyToOne()
    @JoinColumn( name = "id_linha")
    private Linha linha;

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
