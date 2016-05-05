/**
 * Comun.java
 *
 * Creado el 21-oct-2015, 17:42:19
 */

package es.pmp.pmplib;

import es.pmp.pmplib.tipos.general.CHora;

/**
 * Definiciones, tipos y funciones compartidas y de propósito general.
 * 
 * @author pmpreciado
 */
public class Comun {
    
    /** Nueva línea */
    public static final String NL = System.getProperty("line.separator");   
    

   /**
    * Dado un número de segundos, genera una cadena representando el tiempo expresado en días, horas, minutos y segundos.
    * La cadena está pensada para su visualización en pantalla.
    *
     * @param total_segundos                    Número de segundos
     * 
    * @return                                   Cadena con el tiempo
    */
    public static String segundos2TiempoScr(long total_segundos) {
        
        long dias = total_segundos / (24 * 60 * 60);
        total_segundos = total_segundos - dias * 24 * 60 * 60;
        
        long horas = total_segundos / (60 * 60);
        total_segundos = total_segundos - horas * 60 * 60;
        
        long minutos = total_segundos / 60 ;
        total_segundos = total_segundos - minutos * 60;
        
        long segundos = total_segundos;
        
        String s_horas = Long.toString(horas);
        while (s_horas.length() < 2) {
            s_horas = "0" + s_horas;
        }

        String s_minutos = Long.toString(minutos);
        while (s_minutos.length() < 2) {
            s_minutos = "0" + s_minutos;
        }

        String s_segundos = Long.toString(segundos);
        while (s_segundos.length() < 2) {
            s_segundos = "0" + s_segundos;
        }

        String s = "";
        if (dias > 0) {
            s += dias + " días ";
        }
        
        s+= s_horas + " horas ";
        s+= s_minutos + " min ";
        s+= s_segundos + " seg";
        
        return s;
    }
    
    
    public static void main(String [] args) {
        long t = 8290813;
        String s = segundos2TiempoScr(t);
        System.out.println(s);
        
        t = 621906;
        s = segundos2TiempoScr(t);
        System.out.println(s);
        
        t = 616135;
        s = segundos2TiempoScr(t);
        System.out.println(s);
        
        t = 86465;
        s = segundos2TiempoScr(t);
        System.out.println(s);

        
        CHora h1 = new CHora(2315);
        CHora h2 = new CHora(2353);
        long d = h2.getSegundos() - h1.getSegundos();
        s = segundos2TiempoScr(d);
        System.out.println(s);
    }
    
}
