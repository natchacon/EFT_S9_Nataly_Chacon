package cl.duoc.entregafinal;

public class Venta {

    public Butaca butaca;
    public Cliente cliente;
    public Double valorVenta;
    public Double valorSinDescuentos;
    public Descuento descuento;

    public Venta(Butaca butaca, Cliente cliente) {
        this.butaca = butaca;
        this.cliente = cliente;
        this.valorSinDescuentos = this.butaca.getValorAsientoEntradaGenerl();
        Descuento descuento = Descuentos.aplicaDescuento(butaca, cliente);
        if (descuento != null) {
            this.descuento = descuento;
        }
        if(descuento==null){
            this.valorVenta = this.valorSinDescuentos;
        } else {
            this.valorVenta = this.valorSinDescuentos * descuento.getFactorDescuento();
        }

    }

    public Butaca getButaca() {
        return butaca;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Double getValorVenta() {
        return valorVenta;
    }

    public Double getValorSinDescuentos() {
        return valorSinDescuentos;
    }

    public Descuento getDescuento() {
        return descuento;
    }

    public void imprimirBoleta() {
        System.out.println("\r\r");
        System.out.println("-------------------------------------------");
        System.out.println("               Teatro Moro                 ");
        System.out.println("-------------------------------------------");

        System.out.println("Nombre cliente: " + cliente.getNombre());
        System.out.println("Rut cliente: " + cliente.getRut());
        butaca.imprimirDetalleButaca();
        if (descuento != null) {
            System.out.println("Descuento aplicado: " + descuento.getNombreDescuento() + " (" + descuento.getPorcentajeDescuento() + "%)");

        }

        System.out.println("Valor pagado: $" + valorVenta);
        System.out.println("-------------------------------------------");
        System.out.println("Gracias por su visita al Teatro Moro");
        System.out.println("-------------------------------------------");
        System.out.println("\r\r");
    }

    public void imprimirVenta(){
        System.out.println("Venta de asiento: " + butaca.getNombreButaca() + " monto venta: " + valorVenta + " cliente: " + cliente.getNombre());
    }



}
