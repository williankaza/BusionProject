package br.com.fiap.my.transport.onibus.api.dto;

public class LinhaCreateUpdateDTO {
    private String codigoLinha;
    private boolean ativo;

    public LinhaCreateUpdateDTO(){
        super();
    }

    public LinhaCreateUpdateDTO(String codigoLinha, boolean ativo){
        super();
        this.codigoLinha = codigoLinha;
        this.ativo = ativo;
    }

    public String getCodigoLinha() {
        return codigoLinha;
    }

    public void setCodigoLinha(String codigoLinha) {
        this.codigoLinha = codigoLinha;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

}
