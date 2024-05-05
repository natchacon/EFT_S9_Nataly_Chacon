package cl.duoc.entregafinal;

import java.util.ArrayList;
import java.util.List;

public class Descuentos {

    private static List<Descuento> descuentos = null;

    private static void cargarDescuentos(){
        if (descuentos == null) {
            descuentos = new ArrayList<>();
            descuentos.add(new Descuento("Tercera Edad", 15));
            descuentos.add(new Descuento("Estudiante", 10));
        }
    }

    public static List<Descuento> getDesuentos() {
        if(descuentos==null){
            cargarDescuentos();
        }
        return descuentos;
    }

    public static Descuento aplicaDescuento(Butaca butaca, Cliente cliente) {
        if(descuentos==null){
            cargarDescuentos();
        }
        //Por ahora no se usa la butaca, pero podrian haber descuentos dependiendo el tipo de butaca
        if (cliente.esDeTerceraEdad()) {
            return descuentos.get(0);
        } else if (cliente.esEstudiante()) {
            return descuentos.get(1);
        } else {
            return null;
        }

    }

}
