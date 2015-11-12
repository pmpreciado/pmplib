/**
 * InputNumber.java
 *
 * Creado el 09-nov-2015, 10:51:23
 */

package es.pmp.pmplib.html.componentes.form;


/**
 * Definición de un elemento de formulario de tipo "number".
 * Este tipo de elemento facilita la introducción de campos de tipo entero.
 * 
 * Este tipo de elemento fue introducido en HTML5.
 * Los atributos "size" y "maxlength" son ignorados en Chrome (ya que así lo define la norma). En otros navegadores pueden funcionar.
 * 
 * @author pmpreciado
 */
public class InputNumber extends FormItemBase {

    private static final String TYPE = "number";
    
    /** Atributos de uso común para los input de tipo number */
    private static final String ATR_MIN         = "min";
    private static final String ATR_MAX         = "max";
    private static final String ATR_STEP        = "step";
    
    
    /**
     * Crea una instancia de la clase.
     */
    public InputNumber() {
        super(TYPE);
    }
    
    /**
     * Crea una instancia de la clase, inicializada con los parámetros de entrada.
     * 
     * @param nombre                            Nombre del campo
     */
    public InputNumber(String nombre) {
        this();
        setName(nombre);
    }
    
    
    /**
     * Crea una instancia de la clase, inicializada con los parámetros de entrada.
     * 
     * @param nombre                            Nombre del campo
     * @param valor                             Valor inicial
     */
    public InputNumber(String nombre, String valor) {
        this(nombre);
        setValue(valor);
    }
    
    
    /**
     * Crea una instancia de la clase, inicializada con los parámetros de entrada.
     * 
     * @param nombre                            Nombre del campo
     * @param valor                             Valor inicial
     */
    public InputNumber(String nombre, int valor) {
        this(nombre);
        setValue(valor);
    }
    
    
    /**
     * Crea una instancia de la clase, inicializada con los parámetros de entrada.
     * 
     * @param nombre                            Nombre del campo
     * @param valor                             Valor inicial
     * @param min                               Mínimo valor permitido
     * @param max                               Máximo valor permitido
     */
    public InputNumber(String nombre, String valor, int min, int max) {
        this(nombre, valor);
        setMin(min);
        setMax(max);
    }
    
    
    /**
     * Crea una instancia de la clase, inicializada con los parámetros de entrada.
     * 
     * @param nombre                            Nombre del campo
     * @param valor                             Valor inicial
     * @param min                               Mínimo valor permitido
     * @param max                               Máximo valor permitido
     */
    public InputNumber(String nombre, int valor, int min, int max) {
        this(nombre, valor);
        setMin(min);
        setMax(max);
    }
    
    
    /**
     * Retorna una instancia de la clase, inicializada con los parámetros de entrada.
     * 
     * @param nombre                            Nombre del campo
     * @param valor                             Valor inicial
     * 
     * @return                                  Instancia de la clase
     */
    public static InputNumber getInputNumber(String nombre, String valor) {
        InputNumber campo = new InputNumber(nombre, valor);
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
    public static InputNumber getInputNumber(String nombre, int valor) {
        InputNumber campo = new InputNumber(nombre, valor);
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
    public static InputNumber getInputNumber(String nombre, String valor, int size, int maxlength) {
        InputNumber campo = new InputNumber(nombre, valor, size, maxlength);
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
    public static InputNumber getInputNumber(String nombre, int valor, int size, int maxlength) {
        InputNumber campo = new InputNumber(nombre, valor, size, maxlength);
        return campo;
    }
    
    
    /**
     * Establece el atributo "min", con el mínimo valor permitido.
     * 
     * @param min                               Mínimo valor permitido
     */
    public void setMin(int min) {
        setAtributo(ATR_MIN, min);
    }
    
    
    /**
     * Obtiene el valor del atributo "min", con el mínimo valor permitido.
     * 
     * @return                                  Mínimo valor permitido
     */
    public int getMin() {
        int min = super.getValorAtributoInt(ATR_MIN);
        return min;
    }
    
    
    /**
     * Establece los atributos "size" y "maxlength" automáticamente a partir del máximo valor permitido para este campo.
     * Los atributos "size" y "maxlength" no son aplicables a los input de tipo "number", según la norma. Por lo que hay navegadores, como Chrome, que los ignoran.
     * 
     * @param max                               Máximo valor permitido
     */
    public void autoSetSizeYMaxLength(int max) {
        int num_digitos = Integer.toString(max).length();
        
        int size = num_digitos + 1;
        int maxlength = num_digitos;
        setSize(size);
        setMaxLength(maxlength);        
    }
    
    
    /**
     * Establece el atributo "max", con el máximo valor permitido.
     * 
     * @param max                               Máximo valor permitido
     */
    public void setMax(int max) {
        setAtributo(ATR_MAX, max);
        autoSetSizeYMaxLength(max);
        
    }
    
    
    /**
     * Obtiene el valor del atributo "max", con el máximo valor permitido.
     * 
     * @return                                  Máximo valor permitido
     */
    public int getMax() {
        int max = super.getValorAtributoInt(ATR_MAX);
        return max;
    }
    
    
    /**
     * Establece el atributo "step", con el intervalo de valores permitido.
     * 
     * @param step                              Intervalo de valores permitido.
     */
    public void setStep(int step) {
        setAtributo(ATR_STEP, step);
    }
    
    
    /**
     * Obtiene el valor del atributo "step", con el intervalo de valores permitido.
     * 
     * @return                                  Intervalo de valores permitido.
     */
    public int getStep() {
        int step = super.getValorAtributoInt(ATR_STEP);
        return step;
    }
}
