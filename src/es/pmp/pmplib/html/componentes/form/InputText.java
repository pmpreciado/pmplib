/**
 * InputText.java
 *
 * Creado el 09-nov-2015, 10:13:14
 */

package es.pmp.pmplib.html.componentes.form;


/**
 * Definición de un elemento de formulario de tipo "text".
 * 
 * @author pmpreciado
 */
public class InputText extends FormItemBase {

    private static final String TYPE = "text";
    
    
    /**
     * Crea una instancia de la clase.
     */
    public InputText() {
        super(TYPE);
    }

    
    /**
     * Crea una instancia de la clase, inicializada con los parámetros de entrada.
     * 
     * @param nombre                            Nombre del campo
     */
    public InputText(String nombre) {
        this();
        setName(nombre);
    }
    
    
    /**
     * Crea una instancia de la clase, inicializada con los parámetros de entrada.
     * 
     * @param nombre                            Nombre del campo
     * @param valor                             Valor inicial
     */
    public InputText(String nombre, String valor) {
        this(nombre);
        setValue(valor);
    }

    
    /**
     * Crea una instancia de la clase, inicializada con los parámetros de entrada.
     * 
     * @param nombre                            Nombre del campo
     * @param valor                             Valor inicial
     * @param size                              Tamaño del campo
     * @param maxlength                         Número máximo de caracteres
     */
    public InputText(String nombre, String valor, int size, int maxlength) {
        this(nombre, valor);
        setSize(size);
        setMaxLength(maxlength);
    }
    
    
    /**
     * Retorna una instancia de la clase, inicializada con los parámetros de entrada.
     * 
     * @param nombre                            Nombre del campo
     * 
     * @return                                  Instancia de la clase
     */
    public static InputText getInputText(String nombre) {
        InputText campo = new InputText(nombre);
        return campo;
    }
    
    
    /**
     * Retorna una instancia de la clase, inicializada con los parámetros de entrada.
     * 
     * @param nombre                            Nombre del campo
     * @param valor                             Valor inicial
     * 
     * @return                                  Instancia de la clase
     */
    public static InputText getInputText(String nombre, String valor) {
        InputText campo = new InputText(nombre, valor);
        return campo;
    }
    
    
    /**
     * Retorna una instancia de la clase, inicializada con los parámetros de entrada.
     * 
     * @param nombre                            Nombre del campo
     * @param valor                             Valor inicial
     * @param size                              Tamaño del campo
     * @param maxlength                         Número máximo de caracteres
     * 
     * @return                                  Instancia de la clase
     */
    public static InputText getInputText(String nombre, String valor, int size, int maxlength) {
        InputText campo = new InputText(nombre, valor, size, maxlength);
        return campo;
    }
    
}
