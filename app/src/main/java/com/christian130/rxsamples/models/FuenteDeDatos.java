package com.christian130.rxsamples.models;

import com.christian130.rxsamples.DTO.local.SimplePOJO;

import java.util.ArrayList;
import java.util.List;

public class FuenteDeDatos {
    public static List<SimplePOJO> getAllDatosCableados(){
        List<SimplePOJO> simplePOJOS = new ArrayList<SimplePOJO>();
        simplePOJOS.add(new SimplePOJO("La Descripcion que yo quiera00",true,4));
        simplePOJOS.add(new SimplePOJO("La Descripcion que yo quiera01",false,2));
        simplePOJOS.add(new SimplePOJO("La Descripcion que yo quiera02",false,1));
        simplePOJOS.add(new SimplePOJO("La Descripcion que yo quiera03",true,3));
        simplePOJOS.add(new SimplePOJO("La Descripcion que yo quiera04",true,5));
        simplePOJOS.add(new SimplePOJO("La Descripcion que yo quiera05",false,6));
        return simplePOJOS;
    }

}
