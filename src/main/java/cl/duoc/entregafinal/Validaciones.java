package cl.duoc.entregafinal;

public class Validaciones {

    public static Boolean validarRut(String rut) {

        boolean validacion = false;
        try {
            rut =  rut.toUpperCase();
            rut = rut.replace(".", "");
            rut = rut.replace("-", "");
            int rutAux = Integer.parseInt(rut.substring(0, rut.length() - 1));

            char dv = rut.charAt(rut.length() - 1);

            int m = 0, s = 1;
            for (; rutAux != 0; rutAux /= 10) {
                s = (s + rutAux % 10 * (9 - m++ % 6)) % 11;
            }
            if (dv == (char) (s != 0 ? s + 47 : 75)) {
                validacion = true;
            }

        } catch (java.lang.NumberFormatException e) {
            return false;
        } catch (Exception e) {
            return false;
        }
        return validacion;
    }

    public static boolean esEntero(String numero){
        try{
            Integer.valueOf(numero);
            return true;
        }
        catch(Exception e){
            return false;
        }
    }
}
