package cl.duoc.entregafinal;

import java.util.Objects;

public class Butaca {

    private String pasillo;
    private Integer numeroAsiento;
    private Boolean estaDisponible;
    private String tipoButaca;

    public static final String BUTACA_VIP = "VIP";
    public static final String BUTACA_PLATEA = "PLATEA";
    public static final String BUTACA_GENERAL = "GENERAL";


    public Butaca(String pasillo, Integer numeroAsiento, String tipoAsiento){
        this.pasillo = pasillo;
        this.numeroAsiento = numeroAsiento;
        this.tipoButaca = tipoAsiento;
        estaDisponible = Boolean.TRUE;
    }

    public Butaca(String pasillo, Integer numeroAsiento){
        this.pasillo = pasillo;
        this.numeroAsiento = numeroAsiento;
    }

    public void reservarButaca(){
        this.estaDisponible = Boolean.FALSE;
    }

    public void eliminarReserva(){
        this.estaDisponible = Boolean.TRUE;
    }

    public Boolean estaDisponible(){
        return this.estaDisponible;
    }

    public Double getValorAsientoEntradaGenerl(){
        Double valorButaca = null;
        switch (this.tipoButaca){
            case BUTACA_GENERAL:
                valorButaca = 7200d;
                break;
            case BUTACA_PLATEA:
                valorButaca = 19000d;
                break;
            case BUTACA_VIP:
                valorButaca = 25000d;
                break;
            default:
                break;
        }
        return valorButaca;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Butaca butaca = (Butaca) o;
        return Objects.equals(pasillo, butaca.pasillo) && Objects.equals(numeroAsiento, butaca.numeroAsiento);
    }

    public String getPasillo(){
        return this.pasillo;
    }

    public String getTipoButaca(){
        return this.tipoButaca;
    }

    public void imprimirButaca(){
        System.out.print("(" + this.tipoButaca.substring(0,1) + ")" + "_" + this.pasillo + this.numeroAsiento + "_" + (this.estaDisponible?"(D)":"(R)"));
    }

    public void imprimirDetalleButaca(){
        System.out.println("Categoria: " + this.tipoButaca);
        System.out.println("Pasillo: " + this.pasillo);
        System.out.println("Número: " + this.numeroAsiento);
        System.out.println("Valor general: " + this.getValorAsientoEntradaGenerl());
    }

    public String getNombreButaca(){
        return pasillo + numeroAsiento;
    }

}
