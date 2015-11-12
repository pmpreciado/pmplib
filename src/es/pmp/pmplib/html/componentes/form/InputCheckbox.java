/**
 * InputCheckbox.java
 *
 * Creado el 09-nov-2015, 13:20:06
 */

package es.pmp.pmplib.html.componentes.form;

/**
 * Definición de un elemento de formulario de tipo "checkbox".
 * 
 * @author pmpreciado
 */
public class InputCheckbox extends FormItemBase {

    private static final String TYPE = "checkbox";
    
    /** Atributos de uso común para los input de tipo radio */
    private static final String ATR_CHECKED     = "checked";
    
    
    
    /**
     * Crea una instancia de la clase.
     */
    public InputCheckbox() {
        super(TYPE);
    }
    
    

    /**
     * Crea una instancia de la clase, inicializada con los parámetros de entrada.
     * 
     * @param nombre                            Nombre del campo
     * @param valor                             Valor
     */
    public InputCheckbox(String nombre, String valor) {
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
    public InputCheckbox(String nombre, String valor, boolean checked) {
        this(nombre, valor);
        if (checked) {
            setChecked(checked);
        }
    }
    
    
    /**
     * Establece si el campo estará preseleccionado.
     * 
     * @param checked                           'true' para que el campo esté preseleccionado
     *                                          'false' para que no lo esté
     */
    public void setChecked(boolean checked) {
        if (checked) {
            setAtributo(ATR_CHECKED);
        } else {
            removeAtributo(ATR_CHECKED);
        }
    }
    
    
    /**
     * Comprueba si este campo estará preseleccionado.
     * 
     * @return                                  'true' si el campo estará preseleccionado
     *                                          'false' si no lo estará
     */
    public boolean getChecked() {
        boolean ro = super.existeAtributo(ATR_CHECKED);
        return ro;
    }
    
    
    /**
     * Retorna una instancia de la clase, inicializada con los parámetros de entrada.
     * 
     * @param nombre                            Nombre del campo
     * @param valor                             Valor
     * 
     * @return                                  Instancia de la clase
     */
    public static InputCheckbox getInputCheckbox(String nombre, String valor) {
        InputCheckbox campo = new InputCheckbox(nombre, valor);
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
    public static InputCheckbox getInputCheckbox(String nombre, String valor, boolean checked) {
        InputCheckbox campo = new InputCheckbox(nombre, valor, checked);
        return campo;
    }
}
