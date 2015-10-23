/**
 * Href.java
 *
 * Creado el 22-oct-2015, 18:48:25
 */

package es.pmp.pmplib.html.componentes;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Funciones para generar referencias a otras páginas.
 *
 * 
 */
public class Href implements Cloneable {

    /** Dirección URL */
    String target;
    
    /** Lista de los parámetros. Cada elemento es un par (nombre, valor) */
    List <String []> l_parametros;
    
    /** Primer separador (por defecto, "?") */
    String primer_separador;
    
    /** Siguientes separadores (por defecto, "&") */
    String siguientes_separadores;



    
    /**
     * Crea una instancia de la clase.
     *
     * @param target                    Página destino
     */
    public Href() {
        this.primer_separador = "?";
        this.siguientes_separadores = "&";
        this.l_parametros = new ArrayList();
    }
    
    
    /**
     * Crea una instancia de la clase.
     *
     * @param target                            Página destino
     */
    public Href(String target) {
        this();
        this.target = target;
    }


    /**
     * Crea una instancia de la clase, permitiendo especificar directamente un primer
     * parámetro.
     *
     * @param target                            Página destino
     * @param nombre                            Nombre del parámetro
     * @param valor                             Valor
     */
    public Href(String target, String nombre, String valor) {
        this(target);
        addParametro(nombre, valor);
    }
    
    
    /**
     * Crea una instancia de la clase, permitiendo especificar directamente un primer
     * parámetro.
     *
     * @param target                            Página destino
     * @param nombre                            Nombre del parámetro
     * @param valor                             Valor
     */
    public Href(String target, String nombre, int valor) {
        this(target);
        addParametro(nombre, valor);
    }
    
    
    /**
     * Establece el símbolo a utilizar como primer separador. Por defecto es "?".
     *
     * @param simbolo                           Símbolo a utilizar
     */
    public void setSimboloPrimerSeparador(String simbolo) {
        primer_separador = simbolo;
    }


    /**
     * Establece el símbolo a utilizar como siguientes separadores. Por defecto es "&".
     *
     * @param simbolo                           Símbolo a utilizar
     */
    public void setSimboloSiguientesSeparadores(String simbolo) {
        siguientes_separadores = simbolo;
    }

    
    
    /**
     * Añade un parámetro a la dirección URL.
     * El parámetro añadido tiene la sintaxis "&parametro=valor" o "?parametro=valor".
     *
     * @param nombre                            Nombre del parámetro
     * @param valor                             Valor
     */
    public void addParametro(String nombre, String valor) {
        
        if (valor == null) {
            valor = "";
        }
        
        try {
            valor = URLEncoder.encode(valor, "UTF-8"); 
        } catch (UnsupportedEncodingException ex) {}

        String [] item = new String [] {nombre, valor};
        l_parametros.add(item);
    }

    
    /**
     * Añade un parámetro a la dirección URL.
     * El parámetro añadido tiene la sintaxis "&parametro=valor" o "?parametro=valor".
     *
     * @param nombre                            Nombre del parámetro
     * @param valor                             Valor
     */
    public void addParametro(String nombre, int valor) {
        
        addParametro(nombre, Integer.toString(valor));
    }
    
    
    /**
     * Añade un parámetro a la dirección URL.
     * El parámetro añadido tiene la sintaxis "&parametro=valor" o "?parametro=valor".
     *
     * @param nombre                            Nombre del parámetro
     * @param valor                             Valor
     */
    public void addParametro(String nombre, long valor) {
        
        addParametro(nombre, Long.toString(valor));
    }
    
    
    /**
     * Añade un parámetro a la dirección URL.
     * El parámetro añadido tiene la sintaxis "&parametro=valor" o "?parametro=valor".
     *
     * @param nombre                            Nombre del parámetro
     * @param valor                             Valor
     */
    public void addParametro(String nombre, boolean valor) {
        String s_valor;
        if (valor) s_valor = "1";
        else s_valor = "0";
        addParametro(nombre, s_valor);
    }


    /**
     * Obtiene la dirección URL junto con los parámetros.
     *
     * @return                                  Dirección destino
     */
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("");
        
        s.append(target);
        
        for (int i = 0; i < l_parametros.size(); i++) {
            if (i == 0) {
                s.append(primer_separador); 
            } else {
                s.append(siguientes_separadores);
            }
            
            String [] item = l_parametros.get(i);
            s.append(item[0]);
            s.append("=");
            s.append(item[1]);
        }
        
        return s.toString();
    }
    
}
