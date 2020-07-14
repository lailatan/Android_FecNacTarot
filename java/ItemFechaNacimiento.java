package com.example.fechanactarot;

import java.io.Serializable;

public class ItemFechaNacimiento implements Serializable {
    private String myTipoDato;
    private String myTipoDatoDesc;
    private Integer myNumeroDirecto;
    private Integer myNumeroReducido;
    private Boolean myInvertido;
    private Arcano myArcano;

    public ItemFechaNacimiento (String myTipoDato, String myTipoDatoDesc, Integer myNumeroDirecto, Integer myNumeroReducido, Boolean myInvertido, Arcano myArcano) {
        this.myTipoDato = myTipoDato;
        this.myTipoDatoDesc = myTipoDatoDesc;
        this.myNumeroDirecto = myNumeroDirecto;
        this.myNumeroReducido = myNumeroReducido;
        this.myInvertido = myInvertido;
        this.myArcano = myArcano;
    }

    public String getMyTipoDato() {
        return myTipoDato;
    }

    public void setMyTipoDato(String myTipoDato) {
        this.myTipoDato = myTipoDato;
    }

    public String getMyTipoDatoDesc() {
        return myTipoDatoDesc;
    }

    public void setMyTipoDatoDesc(String myTipoDatoDesc) {
        this.myTipoDatoDesc = myTipoDatoDesc;
    }

    public Integer getMyNumeroDirecto() {
        return myNumeroDirecto;
    }

    public void setMyNumeroDirecto(Integer myNumeroDirecto) {
        this.myNumeroDirecto = myNumeroDirecto;
    }

    public Integer getMyNumeroReducido() {
        return myNumeroReducido;
    }

    public void setMyNumeroReducido(Integer myNumeroReducido) {
        this.myNumeroReducido = myNumeroReducido;
    }

    public Boolean getMyInvertido() {
        return myInvertido;
    }

    public void setMyInvertido(Boolean myInvertido) {
        this.myInvertido = myInvertido;
    }

    public Arcano getMyArcano() {
        return myArcano;
    }

    public void setMyArcano(Arcano myArcano) {
        this.myArcano = myArcano;
    }
}


