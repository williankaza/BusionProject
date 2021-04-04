package com.mytransfport.UserServices.DTO.Update;

import com.mytransfport.UserServices.Entity.GeoLocalizacao;

import java.time.LocalDateTime;

public class UpdateAgendamentoDTO {
    private LocalDateTime dataAgendamento;
    private GeoLocalizacao origemGeo;
    private GeoLocalizacao destinoGeo;
    private String idAgendamento;

    public String getIdAgendamento() {
        return idAgendamento;
    }

    public void setIdAgendamento(String idAgendamento) {
        this.idAgendamento = idAgendamento;
    }

    public LocalDateTime getDataAgendamento() {
        return dataAgendamento;
    }

    public void setDataAgendamento(LocalDateTime dataAgendamento) {
        this.dataAgendamento = dataAgendamento;
    }

    public GeoLocalizacao getOrigemGeo() {
        return origemGeo;
    }

    public void setOrigemGeo(GeoLocalizacao origemGeo) {
        this.origemGeo = origemGeo;
    }

    public GeoLocalizacao getDestinoGeo() {
        return destinoGeo;
    }

    public void setDestinoGeo(GeoLocalizacao destinoGeo) {
        this.destinoGeo = destinoGeo;
    }
}
