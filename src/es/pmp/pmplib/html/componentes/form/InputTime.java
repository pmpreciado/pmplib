/**
 * InputTime.java
 *
 * Creado el 09-nov-2015, 13:16:06
 */

package es.pmp.pmplib.html.componentes.form;

import es.pmp.pmplib.tipos.general.CHora;

/**
 * Definición de un elemento de formulario de tipo "time".
 * Este campo permite seleccionar una hora.
 * 
 * Este tipo de elemento fue introducido en HTML5.
 * AVISO: Este tipo de campo no está soportado en Internet Explorer ni Firefox. Sí está soportado en Chrome o Edge
 * 
 * @author pmpreciado
 */
public class InputTime extends FormItemBase {

    private static final String TYPE = "time";
    
    /** Atributos de uso común para los input de tipo date */
    private static final String ATR_MIN         = "min";
    private static final String ATR_MAX         = "max";
    
    /** Tamaño y longitud máxima predeterminada para los campos de tipo date */
    private static final int SIZE       = 10;
    private static final int MAXLENGTH  = 8;
    
    
    /**
     * Crea una instancia de la clase.
     */
    public InputTime() {
        super(TYPE);
        setSize(SIZE);
        setMaxLength(MAXLENGTH);
    }
    
    
    
    /**
     * Establece el atributo "min", con la mínima hora permitida.
     * 
     * @param min                               Mínima hora permitida (Formato HH:MM)
     */
    public void setMin(String min) {
        setAtributo(ATR_MIN, min);
    }
    
    
    /**
     * Establece el atributo "min", con la mínima hora permitida.
     * 
     * @param min                               Mínima hora permitida
     */
    public void setMin(CHora min) {
        String s_min = min.toString(CHora.FH_HH_MM);
        setMin(s_min);
    }
    
    
    /**
     * Obtiene el valor del atributo "min", con la mínima hora permitida.
     * 
     * @return                                  Mínima hora permitida (Formato HH:MM)
     */
    public String getMin() {
        String min = super.getValorAtributo(ATR_MIN);
        return min;
    }
    
    
    /**
     * Establece el atributo "max", con la máxima hora permitida.
     * 
     * @param max                               Máximo hora permitida (Formato HH:MM)
     */
    public void setMax(String max) {
        setAtributo(ATR_MAX, max);
    }
    
    
    /**
     * Establece el atributo "max", con la máxima hora permitida.
     * 
     * @param max                               Máxima hora permitida
     */
    public void setMax(CHora max) {
        String s_max = max.toString(CHora.FH_HH_MM);
        setMin(s_max);

    }
    
    
    /**
     * Obtiene el valor del atributo "max", con la máxima hora permitida.
     * 
     * @return                                  Máximo hora permitida (Formato HH:MM)
     */
    public String getMax() {
        String max = super.getValorAtributo(ATR_MAX);
        return max;
    }
    
    
}

