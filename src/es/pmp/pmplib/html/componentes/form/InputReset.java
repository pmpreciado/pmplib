/**
 * InputReset.java
 *
 * Creado el 09-nov-2015, 13:23:14
 */

package es.pmp.pmplib.html.componentes.form;

/**
 * Definición de un elemento de formulario de tipo "reset".
 * 
 * @author pmpreciado
 */
public class InputReset extends FormItemBase {

    private static final String TYPE = "reset";
    
    
    /**
     * Crea una instancia de la clase.
     */
    public InputReset() {
        super(TYPE);
    }
 
    
    /**
     * Crea una instancia de la clase, inicializada con los parámetros de entrada.
     * 
     * @param texto                             Texto del botón
     */
    public InputReset(String texto) {
        this();
        setValue(texto);
    }
    
    
    /**
     * Crea una instancia de la clase, inicializada con los parámetros de entrada.
     * 
     * @param nombre                            Nombre del campo
     * @param texto                             Texto del botón
     */
    public InputReset(String nombre, String texto) {
        this();
        setName(nombre);
        setValue(texto);
    }
}