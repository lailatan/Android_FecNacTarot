package com.example.fechanactarot;

public class NumeroEspecial {
    public static final String C_KARMICO = "K";
    public static final String C_MAESTRO = "M";
    private Integer myId;
    private String myTipo;
    private Integer myNumero;
    private String myDescripcion;
    private String myContra;

    public NumeroEspecial(Integer myId, String myTipo, Integer myNumero, String myDescripcion, String myContra) {
        this.myId = myId;
        this.myTipo = myTipo;
        this.myNumero = myNumero;
        this.myDescripcion = myDescripcion;
        this.myContra = myContra;
    }

    public Integer getMyNumero() {
        return myNumero;
    }

    public void setMyNumero(Integer myNumero) {
        this.myNumero = myNumero;
    }

    public String getMyTipo() {
        return myTipo;
    }

    public void setMyTipo(String myTipo) {
        this.myTipo = myTipo;
    }

    public String getMyDescripcion() {
        return myDescripcion;
    }

    public void setMyDescripcion(String myDescripcion) {
        this.myDescripcion = myDescripcion;
    }

    public Integer getMyId() {
        return myId;
    }

    public void setMyId(Integer myId) {
        this.myId = myId;
    }

    public String getMyContra() {
        return myContra;
    }

    public void setMyContra(String myContra) {
        this.myContra = myContra;
    }

    public static Boolean esNumeroKararmico (Integer numero){
        Boolean eskarmico=false;
        if (numero==13 || numero==14 || numero==16 || numero==19) eskarmico=true;
        return eskarmico;
    }

    public static Boolean esNumeroMaestro (Integer numero){
        Boolean esmaestro=false;
        if (numero==11 || numero==22 || numero==33 || numero==44) esmaestro=true;
        return esmaestro;
    }

    public static String tipoNumeroEspecial(Integer numero){
        String tipo="";
        if (esNumeroKararmico(numero)) tipo = C_KARMICO;
        if (esNumeroMaestro(numero)) tipo = C_MAESTRO;
        return tipo;
    }
}
