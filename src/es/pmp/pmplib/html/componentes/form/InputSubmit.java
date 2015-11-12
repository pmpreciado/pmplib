/**
 * InputSubmit.java
 *
 * Creado el 09-nov-2015, 12:42:22
 */

package es.pmp.pmplib.html.componentes.form;

/**
 * Definición de un elemento de formulario de tipo "submit".
 * 
 * @author pmpreciado
 */
public class InputSubmit extends FormItemBase {

    private static final String TYPE = "submit";
    
    
    /**
     * Crea una instancia de la clase.
     */
    public InputSubmit() {
        super(TYPE);
    }
    
    
    /**
     * Crea una instancia de la clase, inicializada con los parámetros de entrada.
     * 
     * @param texto                             Texto del botón
     */
    public InputSubmit(String texto) {
        this();
        setValue(texto);
    }
    
    
    /**
     * Crea una instancia de la clase, inicializada con los parámetros de entrada.
     * 
     * @param nombre                            Nombre del campo
     * @param texto                             Texto del botón
     */
    public InputSubmit(String nombre, String texto) {
        this();
        setName(nombre);
        setValue(texto);
    }
}