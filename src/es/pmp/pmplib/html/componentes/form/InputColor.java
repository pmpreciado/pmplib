/**
 * InputColor.java
 *
 * Creado el 09-nov-2015, 12:57:38
 */

package es.pmp.pmplib.html.componentes.form;

/**
 * Definición de un elemento de formulario de tipo "color".
 * Este campo permite seleccionar un color.
 * 
 * Este tipo de elemento fue introducido en HTML5.
 * 
 * @author pmpreciado
 */
public class InputColor extends FormItemBase {

    private static final String TYPE = "color";
    
    
    /**
     * Crea una instancia de la clase.
     */
    public InputColor() {
        super(TYPE);
    }    
}
