/**
 * JsLlamada.java
 *
 * Creado el 22-oct-2015, 18:53:43
 */

package es.pmp.pmplib.html.js;

import es.pmp.pmplib.Arrays;
import java.util.ArrayList;
import java.util.List;


/**
 * Clase para facilitar facilita la confección de las llamadas a funciones JavaScript.
 * 
 * @author pmpreciado
 */
public class JsLlamada {

    /** Encerrador predeterminado para los argumentos */
    final String ENCERRADOR = "'";
    
    public static final int TIPO_CADENA = 1;
    public static final int TIPO_OTRO   = 2;
    public static final int TIPO_NULL   = 3;
    
    
    
    /** Nombre de la función JavaScript */
    String nombre_funcion;
    
    /** Argumentos a pasar */
    List <String> l_argumentos;

    /** Tipos de los argumentos */
    List <Integer> l_tipos;

    /** Encerrador para los argumentos */
    String encerrador;
    
    
    /** 
     * Crea la instancia de la clase.
     * 
     * @param nombre_funcion                Nombre de la función JavaScript a llamar
     */
    public JsLlamada(String nombre_funcion) {
        init();
        setNombreFuncion(nombre_funcion);
    }


    /** 
     * Inicializa la clase.
     */
    protected void init() {
        nombre_funcion = null;
        l_argumentos = new ArrayList();
        l_tipos = new ArrayList();
        encerrador = ENCERRADOR;
    }
    
    /**
     * Establece el nombre de la función JavaScript a llamar.
     * 
     * @param nombre_funcion                Nombre de la función JavaScript a llamar
     */
    public void setNombreFuncion(String nombre_funcion) {
        this.nombre_funcion = nombre_funcion;
    }
    
    
    /**
     * Añade un argumento 'null' a la lista de argumentos.
     */
    public void addArgumentoNull() {
        l_argumentos.add(null);
        l_tipos.add(TIPO_NULL);
    }
    
    /**
     * Añade un argumento a la lista de argumentos.
     * 
     * @param tipo                          Tipo de argumento (TIPO_xxx)
     * @param arg                           Argumento a añadir
     */
    public void addArgumento(int tipo, String arg) {
        l_argumentos.add(arg);
        l_tipos.add(tipo);
    }
    
    
    /**
     * Añade un argumento a la lista de argumentos.
     * 
     * @param arg                           Argumento a añadir
     */
    public void addArgumento(String arg) {
        if (arg == null) {
            addArgumentoNull();
        } else {
            l_argumentos.add(arg);
            l_tipos.add(TIPO_CADENA);
        }
    }
    
    
    /**
     * Añade un argumento a la lista.
     * 
     * @param arg                           Argumento a añadir
     */
    public void addArgumento(int arg) {
        String s_arg = Integer.toString(arg);
        l_argumentos.add(s_arg);
        l_tipos.add(TIPO_OTRO);
    }

    /**
     * Añade un argumento a la lista.
     * 
     * @param arg                           Argumento a añadir
     */
    public void addArgumento(boolean arg) {
        String s_arg;
        if (arg) s_arg = "true"; else s_arg = "false";
        l_argumentos.add(s_arg);
        l_tipos.add(TIPO_OTRO);
    }
    
    
    /**
     * Establece el encerrador a utilizar para los argumentos.
     * 
     * @param encerrador                    Encerrador a utilizar
     */
    public void setEncerrador(String encerrador) {
        this.encerrador = encerrador;
    }
    
    
    /**
     * Obtiene la llamada a la función JavaScript, incluyendo los argumentos.
     * 
     * @return                              Llamada a la función JavaScript
     */
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(nombre_funcion);
        s.append("(");
        
        for (int i = 0; i < Arrays.size(l_argumentos); i++) {
            if (i > 0) s.append(", ");
            String arg = l_argumentos.get(i);
            int tipo = l_tipos.get(i);
            if (tipo == TIPO_CADENA) {
                s.append(encerrador + arg + encerrador);
            } else if (tipo == TIPO_CADENA) {
                s.append("null");
            } else {
                s.append(arg);
            }
        }
        s.append(");");
        
        return s.toString();
    }
}
