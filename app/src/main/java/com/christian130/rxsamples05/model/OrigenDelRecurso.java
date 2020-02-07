package com.christian130.rxsamples05.model;

import com.christian130.rxsamples05.DTO.Tarea;

import java.util.ArrayList;
import java.util.List;

public class OrigenDelRecurso {
    public static List<Tarea> crearLista(){
        List<Tarea> tareas= new ArrayList<Tarea>();
        tareas.add(new Tarea("description 0",true,3));
        tareas.add(new Tarea("description 1",false,2));
        tareas.add(new Tarea("description 2",true,1));
        tareas.add(new Tarea("description 3",false,0));
        tareas.add(new Tarea("description 4",true,5));
        return tareas;
    }
}
