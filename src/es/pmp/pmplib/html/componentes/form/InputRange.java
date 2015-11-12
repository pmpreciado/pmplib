/**
 * InputRange.java
 *
 * Creado el 09-nov-2015, 12:45:35
 */

package es.pmp.pmplib.html.componentes.form;

/**
 * Definición de un elemento de formulario de tipo "range".
 * Este campo permite seleccionar un valor aproximado.
 * 
 * Este tipo de elemento fue introducido en HTML5.
 * AVISO: Este tipo de campo no está soportado en Internet Explorer 9 ni anteriores.
 * 
 * @author pmpreciado
 */
public class InputRange extends InputNumber {

    private static final String TYPE = "range";
    
    
    /**
     * Crea una instancia de la clase.
     */
    public InputRange() {
        super.setType(TYPE);
    }    
}
