package com.mytransfport.UserServices.Entity;

import java.util.List;

public class Favoritos {

    private Usuario usuarioUid;
    private List<String> Linha;

    public Usuario getUsuarioUid() {
        return usuarioUid;
    }

    public void setUsuarioUid(Usuario usuarioUid) {
        this.usuarioUid = usuarioUid;
    }

    public List<String> getLinha() {
        return Linha;
    }

    public void setLinha(List<String> linha) {
        Linha = linha;
    }
}
