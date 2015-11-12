/**
 * InputButton.java
 *
 * Creado el 09-nov-2015, 10:35:04
 */

package es.pmp.pmplib.html.componentes.form;

/**
 * Definición de un elemento de formulario de tipo "button".
 * 
 * @author pmpreciado
 */
public class InputButton extends FormItemBase {

    private static final String TYPE = "button";
    
    
    /**
     * Crea una instancia de la clase.
     */
    public InputButton() {
        super(TYPE);
    }
    
    
    /**
     * Crea una instancia de la clase, inicializada con los parámetros de entrada.
     * 
     * @param texto                             Texto del botón
     */
    public InputButton(String texto) {
        this();
        setValue(texto);
    }
    
    
    /**
     * Crea una instancia de la clase, inicializada con los parámetros de entrada.
     * 
     * @param nombre                            Nombre del campo
     * @param texto                             Texto del botón
     */
    public InputButton(String nombre, String texto) {
        this();
        setName(nombre);
        setValue(texto);
    }
}