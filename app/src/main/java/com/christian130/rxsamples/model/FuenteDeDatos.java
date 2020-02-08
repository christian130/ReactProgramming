package com.christian130.rxsamples.model;

import com.christian130.rxsamples.model.DTO.DatosDummy;

import java.util.ArrayList;
import java.util.List;

public class FuenteDeDatos {
public static List<DatosDummy> llenarLista(){
    List<DatosDummy>  datosDummyList = new ArrayList<DatosDummy>();
    datosDummyList.add(new DatosDummy("Descripcion 00",true,1));
    datosDummyList.add(new DatosDummy("Descripcion 01",true,2));
    datosDummyList.add(new DatosDummy("Descripcion 02",false,3));
    datosDummyList.add(new DatosDummy("Descripcion 02",false,3));
    datosDummyList.add(new DatosDummy("Descripcion 03",false,5));
    datosDummyList.add(new DatosDummy("Descripcion 04",true,4));
    datosDummyList.add(new DatosDummy("Descripcion 04",true,4));
    return datosDummyList;

}
}
