package com.mytransfport.UserServices.Entity;

import com.google.cloud.firestore.GeoPoint;
import com.google.type.DateTime;

public class Agendamento {


    private Usuario usuarioUid;
    private DateTime dataAgendamento;
    private GeoPoint origemGeo;
    private GeoPoint destinoGeo;

    public Usuario getUsuarioUid() {
        return usuarioUid;
    }

    public void setUsuarioUid(Usuario usuarioUid) {
        this.usuarioUid = usuarioUid;
    }

    public DateTime getDataAgendamento() {
        return dataAgendamento;
    }

    public void setDataAgendamento(DateTime dataAgendamento) {
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
