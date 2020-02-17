package com.christian130.rxsamples.DTO.local;

import java.io.Serializable;

public class SimplePOJO implements Serializable {
    private String descripcion;
    private boolean consumaton;
    private int prioridad;


    public SimplePOJO(String descripcion, boolean consumaton, int prioridad) {
        this.descripcion = descripcion;
        this.consumaton = consumaton;
        this.prioridad = prioridad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isConsumaton() {
        return consumaton;
    }

    public void setConsumaton(boolean consumaton) {
        this.consumaton = consumaton;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }
}
