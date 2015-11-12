/**
 * InputDate.java
 *
 * Creado el 09-nov-2015, 10:43:24
 */

package es.pmp.pmplib.html.componentes.form;

import es.pmp.pmplib.tipos.general.CFecha;


/**
 * Definición de un elemento de formulario de tipo "date".
 * Este campo permite seleccionar una fecha.
 * 
 * Este tipo de elemento fue introducido en HTML5.
 * AVISO: Este tipo de campo no está soportado en Internet Explorer ni Firefox. Sí está soportado en Chrome o Edge
 * 
 * @author pmpreciado
 */
public class InputDate extends FormItemBase {

    private static final String TYPE = "date";
    
    /** Atributos de uso común para los input de tipo date */
    private static final String ATR_MIN         = "min";
    private static final String ATR_MAX         = "max";

    /** Tamaño y longitud máxima predeterminada para los campos de tipo date */
    private static final int SIZE       = 12;
    private static final int MAXLENGTH  = 10;
    
    
    /**
     * Crea una instancia de la clase.
     */
    public InputDate() {
        super(TYPE);
        setSize(SIZE);
        setMaxLength(MAXLENGTH);
    }
    
    
    /**
     * Crea una instancia de la clase, inicializada con los parámetros de entrada.
     * 
     * @param nombre                            Nombre del campo
     * @param valor                             Valor inicial
     */
    public InputDate(String nombre, String valor) {
        this();
        setName(nombre);
        setValue(valor);
    }
    
    
    /**
     * Establece el atributo "min", con la mínima fecha permitida.
     * 
     * @param min                               Mínima fecha permitida (Formato AAAA-MM-DD)
     */
    public void setMin(String min) {
        setAtributo(ATR_MIN, min);
    }
    
    
    /**
     * Establece el atributo "min", con la mínima fecha permitida.
     * 
     * @param min                               Mínima fecha permitida
     */
    public void setMin(CFecha min) {
        
        CFecha min_aux = new CFecha(min);
        min_aux.setSeparador("-");
        String s_min = min.toString(CFecha.FF_AAAA_MM_DD);
        setMin(s_min);
    }
    
    
    /**
     * Obtiene el valor del atributo "min", con la mínima fecha permitida.
     * 
     * @return                                  Mínima fecha permitida (Formato AAAA-MM-DD)
     */
    public String getMin() {
        String min = super.getValorAtributo(ATR_MIN);
        return min;
    }
    
    
    /**
     * Establece el atributo "max", con el máxima fecha permitida.
     * 
     * @param max                               Máximo fecha permitida (Formato AAAA-MM-DD)
     */
    public void setMax(String max) {
        setAtributo(ATR_MAX, max);
    }
    
    
    /**
     * Establece el atributo "max", con la máxima fecha permitida.
     * 
     * @param max                               Máxima fecha permitida
     */
    public void setMax(CFecha max) {
        
        CFecha max_aux = new CFecha(max);
        max_aux.setSeparador("-");
        String s_max = max.toString(CFecha.FF_AAAA_MM_DD);
        setMin(s_max);
    }
    
    
    /**
     * Obtiene el valor del atributo "max", con la máxima fecha permitida.
     * 
     * @return                                  Máximo fecha permitida (Formato AAAA-MM-DD)
     */
    public String getMax() {
        String max = super.getValorAtributo(ATR_MAX);
        return max;
    }
    
    
}
