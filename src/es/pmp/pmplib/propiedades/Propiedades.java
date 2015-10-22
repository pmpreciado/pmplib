/**
 * Propiedades.java
 *
 * Creado el 22-oct-2015, 12:48:09
 */

package es.pmp.pmplib.propiedades;

import es.pmp.pmplib.Cadenas;
import es.pmp.pmplib.Comun;
import es.pmp.pmplib.errores.Errores;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;

/**
 * Clase para facilitar la lectura de ficheros de propiedades.
 * 
 * @author pmpreciado
 */
public class Propiedades {

    /** Ruta del fichero */
    private String ruta_fichero;
    
    /** Flag que indica si la ruta del fichero de propiedades es una ruta del sistema de ficheros o relativa al classpath */
    private boolean es_ruta_classpath;
    

    /** Handle del fichero */
    private Properties properties;
    
    
    /** Lista para guardar las propiedades que se consideran obligatorias */
    List <String> l_propiedades_obligatorias;
    

    
    /** 
     * Crea la instancia de la clase.
     */
    public Propiedades() {
        properties = new Properties();
        l_propiedades_obligatorias = new ArrayList <> ();
    }
    

    /** 
     * Crea la instancia de la clase.
     * 
     * @param ruta_fichero                      Ruta del fichero de propiedades
     * @param es_ruta_classpath                 Si es 'true', la ruta es relativa al classpath del proyecto, por ejemplo: "es/pmp/miproyecto/miproyecto.properties";
     *                                          Si es 'false', es una ruta del sistema de ficheros, por ejemplo: "propiedades/miproyecto.properties
     */
    public Propiedades(String ruta_fichero, boolean es_ruta_classpath) {
        this();
        this.ruta_fichero = ruta_fichero;
        this.es_ruta_classpath = es_ruta_classpath;
    }

    
    /**
     * Establece el nombre de una propiedad obligatoria, es decir, que debe figurar en el fichero.
     * 
     * @param nombre                            Nombre de la propiedad obligatoria
     */
    public void addPropiedadObligatoria(String nombre) {
        l_propiedades_obligatorias.add(nombre);
    }    
    
    
    /**
     * Obtiene el valor de una propiedad dada por su nombre.
     *
     * @param nombre_propiedad                  Nombre de la propiedad que se quiere obtener
     * @return                                  Valor de la propiedad
     */
    public String getPropiedad(String nombre_propiedad)
    {
        String valor = properties.getProperty(nombre_propiedad);
        valor = Cadenas.trim(valor);
        return valor;
    }
    
    
    /**
     * Obtiene el valor de una propiedad dada por su nombre.
     * Si no existe la propiedad, en lugar de 'null', retorna un valor predeterminado que se pasa por parámetro.
     *
     * @param nombre_propiedad                  Nombre de la propiedad que se quiere obtener
     * @param valor_predeterminado              Valor a retornar si la propiedad no existe
     * @return                                  Valor de la propiedad
     */
    public String getPropiedad(String nombre_propiedad, String valor_predeterminado)
    {
        String valor = properties.getProperty(nombre_propiedad);
        valor = Cadenas.trim(valor);
        if (valor == null) valor = valor_predeterminado;
        return valor;
    }
    
    
    /**
     * Obtiene el valor de una propiedad numérica dada por su nombre.
     *
     * @param nombre_propiedad                  Nombre de la propiedad que se quiere obtener
     * @return                                  Valor de la propiedad
     */
    public int getPropiedadInt(String nombre_propiedad)
    {
        String s_valor = getPropiedad(nombre_propiedad);
        int valor = Cadenas.toEntero(s_valor);
        return valor;
    }
    
    
    /**
     * Obtiene el valor de una propiedad numérica dada por su nombre.
     * Si no existe la propiedad, en lugar de 'null', retorna un valor predeterminado que se pasa por parámetro.
     *
     * @param nombre_propiedad                  Nombre de la propiedad que se quiere obtener
     * @param valor_predeterminado              Valor a retornar si la propiedad no existe
     * 
     * @return                                  Valor de la propiedad
     */
    public int getPropiedadInt(String nombre_propiedad, int valor_predeterminado)
    {
        String s_valor = getPropiedad(nombre_propiedad);
        if (s_valor == null) return valor_predeterminado;
        int valor = Cadenas.toEntero(s_valor);
        return valor;
    }
    
    
    /**
     * Obtiene el valor de una propiedad booleana dada por su nombre.
     *
     * @param nombre_propiedad                  Nombre de la propiedad que se quiere obtener
     * @return                                  Valor de la propiedad
     */
    public boolean getPropiedadBoolean(String nombre_propiedad)
    {
        int valor = getPropiedadInt(nombre_propiedad);
        return valor > 0;
    }
    
    
    /**
     * Obtiene el valor de una propiedad dada por su nombre.
     * Si la propiedad no está definida, lanza una excepción.
     *
     * @param nombre_propiedad                  Nombre de la propiedad que se quiere obtener
     * @param ruta_fichero                      Ruta del fichero de propiedades
     * 
     * @return                                  Valor de la propiedad
     * 
     * @throws Exception                        Falta propiedad en el el fichero de propiedades
     */
    public String getPropiedadOb(String nombre_propiedad, String ruta_fichero) throws Exception
    {
        String valor = properties.getProperty(nombre_propiedad);
        if (valor == null) {
            String mensaje = Errores.getMensajeError(Errores.ERR_AP_FICH_PROPIEDADES_FALTA_PROPIEDAD, nombre_propiedad, ruta_fichero);
            throw new Exception(mensaje);
        }
        
        return valor;
    }
    
    
    /**
     * Obtiene el valor de una propiedad numérica dada por su nombre.
     * Si la propiedad no está definida, lanza una excepción.
     * 
     * @param nombre_propiedad                  Nombre de la propiedad que se quiere obtener
     * @param ruta_fichero                      Ruta del fichero de propiedades
     * 
     * @return                                  Valor de la propiedad
     * 
     * @throws Exception                        Falta propiedad en el el fichero de propiedades
     */
    private int getPropiedadIntOb(String nombre_propiedad, String ruta_fichero) throws Exception
    {
        String s_valor = getPropiedadOb(nombre_propiedad, ruta_fichero);
        int valor = Cadenas.toEntero(s_valor);
        return valor;
    }
    
    
    /**
     * Obtiene el valor de una propiedad booleana dada por su nombre.
     * Si la propiedad no está definida, lanza una excepción.
     * 
     * @param nombre_propiedad                  Nombre de la propiedad que se quiere obtener
     * @param ruta_fichero                      Ruta del fichero de propiedades
     * 
     * @return                                  Valor de la propiedad
     * 
     * @throws Exception                        Falta propiedad en el el fichero de propiedades
     */
    private boolean getPropiedadBooleanOb(String nombre_propiedad, String ruta_fichero) throws Exception
    {
        int valor = getPropiedadIntOb(nombre_propiedad, ruta_fichero);
        return valor > 0;
    }
    
    
    
    /**
     * Carga el fichero de propiedades
     *
     * @throws Exception                        Error al leer el fichero de propiedades
     *                                          Falta alguna propiedad
     */
    public void cargarFicheroPropiedades() throws Exception {
        
        try {
            if (Cadenas.vacio(ruta_fichero )) {
                String mensaje = Errores.getMensajeError(Errores.ERR_AP_FICH_PROPIEDADES_NO_ESTABLECIDO);
                throw new Exception(mensaje);
            }
            
            //ruta_fichero = FP_PREDETERMINADO_RUTA;
            //cnt.logger.info("Cargando fichero de propiedades " + ruta_fichero);
            
            String posible_mensaje_error = Errores.getMensajeError(Errores.ERR_AP_FICH_PROPIEDADES_NO_ENCONTRADO, ruta_fichero);
            if (es_ruta_classpath) {
                try (InputStream is = getClass().getClassLoader().getResourceAsStream(ruta_fichero)) {
                    if (is == null) {
                        throw new Exception(posible_mensaje_error);
                    }

                    properties.load(is);
                }
                
            } else {
                try (FileInputStream fis = new FileInputStream(ruta_fichero)) {
                    if (fis == null) {
                        throw new Exception(posible_mensaje_error);
                    }
                    properties.load(fis);
                }
            }
            
        } catch (IOException ioex) {
            String mensaje = Errores.getMensajeError(Errores.ERR_AP_LEER_FICH_PROPIEDADES, ruta_fichero);
            throw new Exception(mensaje, ioex);
        }
    }
    
    
    /**
     * Comprueba la presencia de las propiedades consideradas obligatorias.
     * En caso de faltar alguna, genera una excepción con el mensaje de error correspondiente.
     * 
     * @throws Exception                        Faltan propiedades en el fichero de propiedades
     */
    public void checkPropiedadesObligatorias() throws Exception {
        
        List <String> l_propiedades_no_encontradas = new ArrayList <> ();
                l_propiedades_obligatorias.stream().mapToInt(x -> Integer.parseInt(x));
                
        for (String po : l_propiedades_obligatorias) {
            String valor = properties.getProperty(po);
            if (valor == null) {
                l_propiedades_no_encontradas.add(valor);
            }
        }
        
        if (l_propiedades_no_encontradas.isEmpty()) {
            return;
        }
        
        StringBuilder s = new StringBuilder();
        String mensaje = Errores.getMensajeError(Errores.ERR_AP_FICH_PROPIEDADES_FALTAN_PROPIEDADES, ruta_fichero);
        s.append(mensaje + Comun.NL);
        s.append("Propiedades no encontradas: " + Comun.NL);
        for (String p : l_propiedades_no_encontradas) {
            s.append("    - " + p + Comun.NL);
        }
        
        throw new Exception(s.toString());
    }

    
    /**
     * Obtiene una cadena con las propiedades cargadas en el fichero.
     * 
     * @return                                  Cadena con las propiedades cargadas
     */
    public StringBuilder getCadenaPropiedadesCargadas() {
        StringBuilder s = new StringBuilder();
        
        s.append("Propiedades cargadas: " + Comun.NL);
        Set <String> set_nombres_propiedades = properties.stringPropertyNames();
        
        for (String nombre_propiedad : set_nombres_propiedades) {
            String valor = getPropiedad(nombre_propiedad);
            s.append("    " + nombre_propiedad + ": " + valor + Comun.NL);
        }

        return s;
    }
}
