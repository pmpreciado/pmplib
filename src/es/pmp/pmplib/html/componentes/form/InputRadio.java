/**
 * InputRadio.java
 *
 * Creado el 09-nov-2015, 13:10:56
 */

package es.pmp.pmplib.html.componentes.form;

/**
 * Definición de un elemento de formulario de tipo "radio".
 * 
 * @author pmpreciado
 */
public class InputRadio extends InputCheckbox {

    private static final String TYPE = "radio";
    
    /**
     * Crea una instancia de la clase.
     */
    public InputRadio() {
        setTipo(TYPE);
    }
    
    
    /**
     * Crea una instancia de la clase, inicializada con los parámetros de entrada.
     * 
     * @param nombre                            Nombre del campo
     * @param valor                             Valor
     */
    public InputRadio(String nombre, String valor) {
        this();
        setName(nombre);
        setValue(valor);
    }
    
    
    /**
     * Crea una instancia de la clase, inicializada con los parámetros de entrada.
     * 
     * @param nombre                            Nombre del campo
     * @param valor                             Valor
     * @param checked                           'true' si el radio va a estar inicialmente seleccionado
     *                                          'false' si no
     */
    public InputRadio(String nombre, String valor, boolean checked) {
        this(nombre, valor);
        if (checked) {
            super.setChecked(checked);
        }
    }
    
    
    /**
     * Retorna una instancia de la clase, inicializada con los parámetros de entrada.
     * 
     * @param nombre                            Nombre del campo
     * @param valor                             Valor
     * 
     * @return                                  Instancia de la clase
     */
    public static InputRadio getInputRadio(String nombre, String valor) {
        InputRadio campo = new InputRadio(nombre, valor);
        return campo;
    }
    
    
    /**
     * Retorna una instancia de la clase, inicializada con los parámetros de entrada.
     * 
     * @param nombre                            Nombre del campo
     * @param valor                             Valor
     * @param checked                           'true' si el radio va a estar inicialmente seleccionado
     *                                          'false' si no
     * 
     * @return                                  Instancia de la clase
     */
    public static InputRadio getInputRadio(String nombre, String valor, boolean checked) {
        InputRadio campo = new InputRadio(nombre, valor, checked);
        return campo;
    }
}
