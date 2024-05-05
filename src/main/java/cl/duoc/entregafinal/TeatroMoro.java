package cl.duoc.entregafinal;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TeatroMoro {

    static String[] opcionesMenuPrincipal = {"Venta de entradas", "Promociones", "Busqueda de Entradas", "Eliminar Entrada", "Ver Asientos del Teatro", "Ventas Realizas", "Imprimir Boleta", "Salir"};
    static String[] pasillos = {"A", "B", "C", "D", "E", "F", "G", "H", "I"};
    static Integer[] butacasPorPasillo = {6, 6, 6, 8, 8, 8, 10, 10, 10};
    List<Butaca> butacas = new ArrayList<>();
    List<Venta> ventas = new ArrayList<>();
    Scanner leer = new Scanner(System.in);

    public TeatroMoro() {

        //Se agregan las butacas iterando los pasillos y butacas por pasillo
        for (int i = 0; i < pasillos.length; i++) {
            String tipoButaca = null;
            String pasillo = pasillos[i];
            if (pasillo.equals("A") || pasillo.equals("B") || pasillo.equals("C")) { // pasillos A,B,C son VIP
                tipoButaca = Butaca.BUTACA_VIP;
            } else if (pasillo.equals("D") || pasillo.equals("E") || pasillo.equals("F")) { // pasillos D, E, F son PLATEA
                tipoButaca = Butaca.BUTACA_PLATEA;
            } else {
                tipoButaca = Butaca.BUTACA_GENERAL;
            }
            for (int j = 0; j < butacasPorPasillo[i]; j++) {
                butacas.add(new Butaca(pasillos[i], j + 1, tipoButaca));
            }
        }
    }



    public void imprimirDescuentos() {
        System.out.println("\r\rDescuentos disponibles:");
        List<Descuento> descuentos = Descuentos.getDesuentos();
        for (int i = 0; i < descuentos.size(); i++) {
            Descuento descuento = descuentos.get(i);
            descuento.imprimirDescuento();
        }
        System.out.println("\r\r");
    }

    public void imprimirButacas(String filtroTipoButaca) {
        System.out.println("Butacas:");
        if(filtroTipoButaca==null || filtroTipoButaca.equals("")){
            System.out.println("(V) VIP (P) Platea (G) General");
        }

        System.out.println("(D) Disponible (R) Reservado");
        String pasillo = null;
        for (int i = 0; i < this.butacas.size(); i++) {
            Butaca butaca = butacas.get(i);
            if (filtroTipoButaca != null && !filtroTipoButaca.equals(butaca.getTipoButaca())) {
                continue;
            }
            if (pasillo != null && !pasillo.equals(butaca.getPasillo())) {
                System.out.println("\r");
            }
            pasillo = butaca.getPasillo();
            butaca.imprimirButaca();
            System.out.print("\t");
        }
    }

    public String seleccionarTipoButaca() {
        String tipoButaca = null;
        System.out.println("Ingrese tipo de butaca: VIP, Platea o General (Si desea salir presione S)");
        do {
            tipoButaca = leer.nextLine();
            if (tipoButaca != null && (tipoButaca.equalsIgnoreCase(Butaca.BUTACA_GENERAL) || tipoButaca.equalsIgnoreCase(Butaca.BUTACA_PLATEA) || tipoButaca.equalsIgnoreCase(Butaca.BUTACA_VIP))) {
                return tipoButaca.toUpperCase();
            } else if (tipoButaca.equalsIgnoreCase("S")) {
                return null;
            } else {
                System.out.println("Debe ingresar VIP, Platea o General (Si desea salir presione S)");
            }
        } while (true);

    }

    public Butaca seleccionarButaca(String tipoButaca, Boolean confirmar) {
        Boolean ingresoCorrecto = Boolean.FALSE;
        do {
            System.out.println("Ingrese la butaca, pasillo seguido de numero de butaca, ej: B4 ");
            String butaca = leer.nextLine();
            if (butaca == null || butaca.length() < 2 || !Validaciones.esEntero(butaca.substring(1, butaca.length()))) {
                System.out.print("Codigo de butaca mal ingresado, dese ingresar pasillo seguido del numero de asiento");
            } else {
                String pasillo = butaca.substring(0, 1).toUpperCase();
                String numeroButaca = butaca.substring(1, butaca.length());
                Butaca butacaSeleccionada = new Butaca(pasillo, Integer.valueOf(numeroButaca));
                if (this.butacas.contains(butacaSeleccionada)) {
                    Butaca butacaDelTeatro = this.butacas.get(this.butacas.indexOf(butacaSeleccionada));
                    if (!confirmar) {
                        return butacaDelTeatro;
                    }

                    if (butacaDelTeatro != null && butacaDelTeatro.estaDisponible() && tipoButaca.equals(butacaDelTeatro.getTipoButaca())) {
                        butacaDelTeatro.imprimirDetalleButaca();
                        System.out.println("¿Desea comprar esta butaca?  Si o No (presione S para salir)");
                        String deseaButaca = null;
                        Boolean ingresoSiNoCorrecto = Boolean.FALSE;
                        do {
                            deseaButaca = leer.nextLine();
                            if (deseaButaca == null || (!deseaButaca.equalsIgnoreCase("SI") && !deseaButaca.equalsIgnoreCase("NO"))) {
                                System.out.println("Debe ingresar Si o No");
                            } else if (deseaButaca.equalsIgnoreCase("S") || deseaButaca.equalsIgnoreCase("NO")) {
                                return null;
                            } else {
                                return butacaDelTeatro;
                            }
                        }
                        while (!ingresoSiNoCorrecto);
                    } else {
                        System.out.println("Butaca no esta disponible o no es de la categoria " + tipoButaca);
                    }
                } else {
                    System.out.print("Codigo de butaca mal ingresado o no existe, dese ingresar pasillo seguido del numero de asiento");
                }
            }
        } while (!ingresoCorrecto);
        return null;
    }

    public Cliente ingresarCliente() {
        System.out.println("Ingrese el nombre cliente o S para salir");
        String nombreCliente = null;
        Boolean nombrevalido = null;
        do {
            nombreCliente = leer.nextLine();
            nombrevalido = nombreCliente != null && !nombreCliente.equals("");
            if (nombrevalido != null && nombreCliente.equalsIgnoreCase("S")) {
                return null;
            }
        } while (!nombrevalido);

        System.out.println("Ingrese el rut del cliente o S para salir");
        String rutCliente = null;
        Boolean rutValido = null;
        do {
            rutCliente = leer.nextLine();
            rutValido = rutCliente != null && Validaciones.validarRut(rutCliente);
            if (rutCliente != null && rutCliente.equalsIgnoreCase("S")) {
                return null;
            }
            if (!rutValido) {
                System.out.println("Rut no valido");
            }
        } while (!rutValido);

        System.out.println("Ingrese el edad del cliente o S para salir");
        String edadCliente = null;
        Boolean edadValido = null;
        do {
            edadCliente = leer.nextLine();
            edadValido = edadCliente != null && Validaciones.esEntero(edadCliente);
            if (edadCliente != null && edadCliente.equalsIgnoreCase("S")) {
                return null;
            }
            if (!edadValido) {
                System.out.println("Edad debe ser nuemerico, ej: 38");
            }
        } while (!edadValido);

        System.out.println("Cliente es estudiante, ingrese Si o No (S para salir)");
        String estudianteCliente = null;
        Boolean estudianteValido = null;
        do {
            estudianteCliente = leer.nextLine();
            estudianteValido = estudianteCliente != null && (estudianteCliente.equalsIgnoreCase("SI") || estudianteCliente.equalsIgnoreCase("NO"));
            if (estudianteCliente != null && estudianteCliente.equalsIgnoreCase("S")) {
                return null;
            }
            if (!estudianteValido) {
                System.out.println("Debe ingresar Si o No (S para salir)");
            }
        } while (!estudianteValido);

        return new Cliente(rutCliente, nombreCliente, Integer.valueOf(edadCliente), estudianteCliente.equalsIgnoreCase("SI"));

    }

    public void venderEntrada() {
        String tipoButaca = this.seleccionarTipoButaca();
        if (tipoButaca == null) {
            return;
        }
        Butaca butaca = this.seleccionarButaca(tipoButaca, true);
        if (butaca == null) {
            return;
        }
        Cliente cliente = this.ingresarCliente();
        if (cliente == null) {
            return;
        }
        Venta venta = new Venta(butaca, cliente);
        butaca.reservarButaca();
        this.ventas.add(venta);
        venta.imprimirBoleta();
    }

    public void eliminarEntrada() {
        if (this.ventas != null && !this.ventas.isEmpty()) {
            Butaca butaca = this.seleccionarButaca(null, false);
            int posVentaEliminar = -1;
            for (int i = 0; i < this.ventas.size(); i++) {
                Venta venta = this.ventas.get(i);
                if (venta.getButaca().equals(butaca)) {
                    posVentaEliminar = i;
                }
            }
            Butaca butacaReservada = this.butacas.get(this.butacas.indexOf(butaca));
            butacaReservada.eliminarReserva();
            if (posVentaEliminar != -1) {
                this.ventas.remove(posVentaEliminar);
            } else {
                System.out.println("Butaca no vendida");
            }

        } else {
            System.out.println("No hay ventas");
        }
    }

    public void mostrarCompras() {

        if (this.ventas != null && !this.ventas.isEmpty()) {
            System.out.println("Ventas realizadas: \r");
            for (int i = 0; i < this.ventas.size(); i++) {
                Venta venta = this.ventas.get(i);
                venta.imprimirVenta();
            }
        } else {
            System.out.println("No hay ventas");
        }
    }

    public void imprimirBoleta() {
        Boolean boletaEncontrada = Boolean.FALSE;
        if (this.ventas != null && !this.ventas.isEmpty()) {
            Butaca butaca = this.seleccionarButaca(null, false);
            for (int i = 0; i < this.ventas.size(); i++) {
                Venta venta = this.ventas.get(i);
                if (venta.getButaca().equals(butaca)) {
                    venta.imprimirBoleta();
                    boletaEncontrada = Boolean.TRUE;
                }
            }
            if (!boletaEncontrada) {
                System.out.println("Butaca no vendida");
            }
        } else {
            System.out.println("No hay ventas");
        }

    }


    public static void main(String[] args) {
        TeatroMoro teatroMoro = new TeatroMoro();
        Scanner leer = new Scanner(System.in);
        boolean mostrarMenu = true;
        String opcionMenuPrincipal = null;
        do {
            opcionMenuPrincipal = null;
            if (mostrarMenu) {
                System.out.println("\r");
                System.out.println("...::: Menu :::...");
                for (int i = 0; i < opcionesMenuPrincipal.length; i++) {
                    System.out.println((i + 1) + ") " + opcionesMenuPrincipal[i]);
                }
            }
            System.out.println("Ingrese el numero de su opcion de menú");
            opcionMenuPrincipal = leer.nextLine();
            if (opcionMenuPrincipal != null && opcionMenuPrincipal.equals("1")) {
                teatroMoro.venderEntrada();
                mostrarMenu = true;
            } else if (opcionMenuPrincipal != null && opcionMenuPrincipal.equals("2")) {
                teatroMoro.imprimirDescuentos();
                mostrarMenu = true;
            } else if (opcionMenuPrincipal != null && opcionMenuPrincipal.equals("3")) {
                String tipoButaca = teatroMoro.seleccionarTipoButaca();
                if (tipoButaca != null) {
                    teatroMoro.imprimirButacas(tipoButaca);
                }
                mostrarMenu = true;
            } else if (opcionMenuPrincipal != null && opcionMenuPrincipal.equals("4")) {
                teatroMoro.eliminarEntrada();
                mostrarMenu = true;
            } else if (opcionMenuPrincipal != null && opcionMenuPrincipal.equals("5")) {
                teatroMoro.imprimirButacas(null);
                mostrarMenu = true;
            } else if (opcionMenuPrincipal != null && opcionMenuPrincipal.equals("6")) {
                teatroMoro.mostrarCompras();
                mostrarMenu = true;
            } else if (opcionMenuPrincipal != null && opcionMenuPrincipal.equals("7")) {
                teatroMoro.imprimirBoleta();
                mostrarMenu = true;
            } else if (opcionMenuPrincipal != null && !opcionMenuPrincipal.equals("8")) {
                System.out.println("Debe ingresar 1,2,3,4,5,6,7 o 8");
                mostrarMenu = false;
            }
        }
        while (!opcionMenuPrincipal.equals("8"));//Se mantiene en el menu principal mientra no seleccione opcion 2) Salir
    }

}
