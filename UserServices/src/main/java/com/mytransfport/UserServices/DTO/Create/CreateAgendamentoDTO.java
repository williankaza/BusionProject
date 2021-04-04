package com.mytransfport.UserServices.DTO.Create;

import com.google.cloud.firestore.GeoPoint;
import com.mytransfport.UserServices.Entity.GeoLocalizacao;


import java.time.LocalDateTime;

public class CreateAgendamentoDTO {

    private String usuarioUid;
    private LocalDateTime dataAgendamento;
    private GeoLocalizacao origemGeo;
    private GeoLocalizacao destinoGeo;

    public String getUsuarioUid() {
        return usuarioUid;
    }

    public void setUsuarioUid(String usuarioUid) {
        this.usuarioUid = usuarioUid;
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
