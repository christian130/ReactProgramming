package com.christian130.rxsamples05.DTO;

import java.io.Serializable;

public class Tarea implements Serializable {
    private String descripcion;
    private boolean completadoBooleano;
    private int proridad;

    public Tarea(String descripcion, boolean completadoBooleano, int proridad) {
        this.descripcion = descripcion;
        this.completadoBooleano = completadoBooleano;
        this.proridad = proridad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isCompletadoBooleano() {
        return completadoBooleano;
    }

    public void setCompletadoBooleano(boolean completadoBooleano) {
        this.completadoBooleano = completadoBooleano;
    }

    public int getProridad() {
        return proridad;
    }

    public void setProridad(int proridad) {
        this.proridad = proridad;
    }
}
