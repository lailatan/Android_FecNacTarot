package com.example.fechanactarot;


import java.io.Serializable;

public class Arcano  implements Serializable {
    private Integer myid;
    private String myNombre;
    private String myImagen;
    private String myDescripcion;
    private String myDescripcionCortaDerecho;
    private String myDescripcionCortaReves;


    public Arcano(Integer myid, String myImagen,String myNombre,   String myDescripcion , String myDescripcionCortaDerecho, String myDescripcionCortaReves) {
        this.myid = myid;
        this.myNombre = myNombre;
        this.myImagen = myImagen;
        this.myDescripcionCortaDerecho = myDescripcionCortaDerecho;
        this.myDescripcionCortaReves = myDescripcionCortaReves;
        this.myDescripcion = myDescripcion;
    }

    public Integer getMyid() {
        return myid;
    }

    public void setMyid(Integer myid) {
        this.myid = myid;
    }

    public String getMyNombre() {
        return myNombre;
    }

    public void setMyNombre(String myNombre) {
        this.myNombre = myNombre;
    }

    public String getMyImagen() {
        return myImagen;
    }

    public void setMyImagen(String myImagen) {
        this.myImagen = myImagen;
    }

    public String getMyDescripcionCortaDerecho() {
        return myDescripcionCortaDerecho;
    }

    public void setMyDescripcionCortaDerecho(String myDescripcionCortaDerecho) {
        this.myDescripcionCortaDerecho = myDescripcionCortaDerecho;
    }

    public String getMyDescripcionCortaReves() {
        return myDescripcionCortaReves;
    }

    public void setMyDescripcionCortaReves(String myDescripcionCortaReves) {
        this.myDescripcionCortaReves = myDescripcionCortaReves;
    }

    public String getMyDescripcion() {
        return myDescripcion;
    }

    public void setMyDescripcion(String myDescripcion) {
        this.myDescripcion = myDescripcion;
    }
}
