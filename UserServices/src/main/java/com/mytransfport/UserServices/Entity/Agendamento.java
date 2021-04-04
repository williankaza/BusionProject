package com.mytransfport.UserServices.Entity;

import com.google.cloud.Timestamp;
import com.google.cloud.firestore.GeoPoint;
import com.google.type.DateTime;

import java.time.LocalDateTime;

public class Agendamento {


    private String usuarioUid;
    private Timestamp dataAgendamento;
    private GeoPoint origemGeo;
    private GeoPoint destinoGeo;
    private LocalDateTime dataAgendamentoLDT;

    public LocalDateTime getDataAgendamentoLDT() {
        return dataAgendamentoLDT;
    }

    public void setDataAgendamentoLDT(LocalDateTime dataAgendamentoLDT) {
        this.dataAgendamentoLDT = dataAgendamentoLDT;
    }

    public String getUsuarioUid() {
        return usuarioUid;
    }

    public void setUsuarioUid(String usuarioUid) {
        this.usuarioUid = usuarioUid;
    }

    public Timestamp getDataAgendamento() {
        return dataAgendamento;
    }

    public void setDataAgendamento(Timestamp dataAgendamento) {
        this.dataAgendamento = dataAgendamento;
    }

    public GeoPoint getOrigemGeo() {
        return origemGeo;
    }

    public void setOrigemGeo(GeoPoint origemGeo) {
        this.origemGeo = origemGeo;
    }

    public GeoPoint getDestinoGeo() {
        return destinoGeo;
    }

    public void setDestinoGeo(GeoPoint destinoGeo) {
        this.destinoGeo = destinoGeo;
    }
}
