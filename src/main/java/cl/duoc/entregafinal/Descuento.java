package cl.duoc.entregafinal;

public class Descuento {

    private String nombreDescuento;
    private Integer porcentajeDescuento;

    public Descuento(String nombreDescuento, Integer porcentajeDescuento){
        this.nombreDescuento = nombreDescuento;
        this.porcentajeDescuento = porcentajeDescuento;
    }

    public void imprimirDescuento(){
        System.out.println("Descuento " + this.nombreDescuento + " " + this.porcentajeDescuento + "%");
    }

    public String getNombreDescuento() {
        return nombreDescuento;
    }

    public Integer getPorcentajeDescuento() {
        return porcentajeDescuento;
    }

    public Double getFactorDescuento(){
        return Double.valueOf((100d - Double.valueOf(porcentajeDescuento))/100d);
    }

}
