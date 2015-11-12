/**
 * InputHidden.java
 *
 * Creado el 09-nov-2015, 10:33:45
 */

package es.pmp.pmplib.html.componentes.form;


/**
 * Definición de un elemento de formulario de tipo "hidden".
 * 
 * @author pmpreciado
 */
public class InputHidden extends FormItemBase {

    private static final String TYPE = "hidden";
    
    
    
    /**
     * Crea una instancia de la clase.
     */
    public InputHidden() {
        super(TYPE);
    }
    
    
    /**
     * Crea una instancia de la clase, inicializada con los parámetros de entrada.
     * 
     * @param nombre                            Nombre del campo
     * @param valor                             Valor inicial
     */
    public InputHidden(String nombre, String valor) {
        this();
        setName(nombre);
        setValue(valor);
    }
}