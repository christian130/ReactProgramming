package com.christian130.rxsamples.model.DTO;

import java.io.Serializable;

public class DatosDummy implements Serializable {
    private String descripcion;
    private boolean apagadoBit;
    private int importancia;

    public DatosDummy(String descripcion, boolean apagadoBit, int importancia) {
        this.descripcion = descripcion;
        this.apagadoBit = apagadoBit;
        this.importancia = importancia;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isApagadoBit() {
        return apagadoBit;
    }

    public void setApagadoBit(boolean apagadoBit) {
        this.apagadoBit = apagadoBit;
    }

    public int getImportancia() {
        return importancia;
    }

    public void setImportancia(int importancia) {
        this.importancia = importancia;
    }
}
