package br.com.fiap.my.transport.onibus.api.dto;

public class OnibusCreateUpdateDTO {
    private String codigo;
    private boolean ativo;

    public OnibusCreateUpdateDTO(){
        super();
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}
