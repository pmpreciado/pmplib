/**
 * FormItemBase.java
 *
 * Creado el 09-nov-2015, 10:15:19
 */

package es.pmp.pmplib.html.componentes.form;

import es.pmp.pmplib.html.base.ListaAtributos;
import es.pmp.pmplib.html.base.Tag;


/**
 * Propiedades y funciones básicas para los elementos del formulario.
 * De esta clase pueden extender los elementos más específicos.
 * 
 * @author pmpreciado
 */
public class FormItemBase extends ListaAtributos {

    
    /** Atributos de uso común para los input */
    private static final String ATR_TYPE        = "type";
    private static final String ATR_SIZE        = "size";
    private static final String ATR_MAXLENGTH   = "maxlength";
    private static final String ATR_READONLY    = "readonly";
    private static final String ATR_PLACEHOLDER = "placeholder";
    private static final String ATR_AUTOFOCUS   = "autofocus";          // Soportado en HTML5
    private static final String ATR_PATTERN     = "pattern";            // Soportado en HTML5
    private static final String ATR_REQUIRED    = "required";           // Soportado en HTML5
    
    
    
    /** Tipo HTML del elemento (text, hidden, button...) */
    String tipo;
    
    
    
    /**
     * Crea una instancia de la clase.
     */
    public FormItemBase() {
    }
    
    
    /**
     * Crea una instancia de la clase.
     * 
     * @param tipo                              Tipo HTML del elemento (text, hidden, button...)
     */
    public FormItemBase(String tipo) {
        this.tipo = tipo;
    }

    
    /**
     * Establece el tipo HTML del elemento.
     * 
     * @param tipo                              Tipo HTML del elemento (text, hidden, button...)
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    
    /**
     * Obtiene el tipo HTML del elemento.
     * 
     * @return                                  Tipo HTML del elemento (text, hidden, button...)
     */
    public String getTipo() {
        return tipo;
    }
    
    
    /**
     * Establece el atributo "type".
     * 
     * @param valor                             Valor del atributo type
     */
    public void setType(String valor) {
        setAtributo(ATR_TYPE, valor);
    }
    
    
    /**
     * Obtener el valor del atributo "type".
     * 
     * @return                                  Valor del atributo type
     */
    public String getType() {
        String size = super.getValorAtributo(ATR_TYPE);
        return size;
    }
    
    
    /**
     * Establece el atributo "size".
     * 
     * @param size                              Valor del atributo size
     */
    public void setSize(String size) {
        setAtributo(ATR_SIZE, size);
    }
    
    
    /**
     * Establece el atributo "size".
     * 
     * @param size                              Valor del atributo size
     */
    public void setSize(int size) {
        setAtributo(ATR_SIZE, size);
    }
    
    
    
    /**
     * Obtiene el valor del atributo "size".
     * 
     * @return                                  Valor del atributo size
     */
    public String getSize() {
        String size = super.getValorAtributo(ATR_SIZE);
        return size;
    }

    
    /**
     * Establece el atributo "maxlength".
     * 
     * @param maxlength                         Valor del atributo "maxlength"
     */
    public void setMaxLength(String maxlength) {
        setAtributo(ATR_MAXLENGTH, maxlength);
    }
    
    
    /**
     * Establece el atributo "maxlength".
     * 
     * @param maxlength                         Valor del atributo "maxlength"
     */
    public void setMaxLength(int maxlength) {
        setAtributo(ATR_MAXLENGTH, maxlength);
    }
    
    
    /**
     * Obtiene el valor del atributo "maxlength".
     * 
     * @return                                  Valor del atributo "maxlength"
     */
    public int getMaxLength() {
        return getValorAtributoInt(ATR_MAXLENGTH);
    }
    
    
    /**
     * Establece si este campo es de solo lectura.
     * 
     * @param readonly                          'true' para que este campo sea de solo lectura
     *                                          'false' para que se pueda editar
     */
    public void setReadOnly(boolean readonly) {
        if (readonly) {
            setAtributo(ATR_READONLY);
        } else {
            removeAtributo(ATR_READONLY);
        }
    }
    
    
    /**
     * Comprueba si este campo debe recibir el foco automáticamente al cargar el formulario.
     * 
     * @return                                  'true' si este campo es de solo lectura
     *                                          'false' si es editable
     */
    public boolean getReadOnly() {
        boolean ro = super.existeAtributo(ATR_READONLY);
        return ro;
    }

    
    /**
     * Establece el atributo "placeholder".
     * Este atributo permite especificar una ayuda corta para describir el valor esperado en el campo.
     * 
     * @param placeholder                       Valor del atributo placeholder
     */
    public void setPlaceHolder(String placeholder) {
        setAtributo(ATR_PLACEHOLDER, placeholder);
    }
    
    
    /**
     * Obtiene el valor del atributo "placeholder".
     * Este atributo permite especificar una ayuda corta para describir el valor esperado en el campo.
     * 
     * @return                                  Valor del atributo placeholder
     */
    public String getPlaceHolder() {
        String placeholder = super.getValorAtributo(ATR_PLACEHOLDER);
        return placeholder;
    }
    
    
    /**
     * Establece si este campo debe recibir el foco automáticamente al cargar el formulario.
     * 
     * @param autofocus                         'true' para que este campo reciba el foco automáticamente al cargar el formulario
     *                                          'false' para que no
     */
    public void setAutofocus(boolean autofocus) {
        if (autofocus) {
            setAtributo(ATR_AUTOFOCUS);
        } else {
            removeAtributo(ATR_AUTOFOCUS);
        }
    }
    
    
    /**
     * Comprueba si este campo debe recibir el foco automáticamente al cargar el formulario.
     * 
     * @return                                  'true' si este campo debe recibir el foco automáticamente al cargar el formulario
     *                                          'false' si no
     */
    public boolean getAutofocus() {
        boolean af = super.existeAtributo(ATR_AUTOFOCUS);
        return af;
    }
    

    /**
     * Establece el atributo "pattern", con la expresión regular contra la que se validará el contenido introducido.
     * Este atributo ha sido introducido en HTML5.
     * 
     * @param pattern                           Valor del atributo "pattern"
     */
    public void setPattern(String pattern) {
        setAtributo(ATR_PATTERN, pattern);
    }
    
    
    /**
     * Obtiene el valor del atributo "pattern", con la expresión regular contra la que se validará el contenido introducido.
     * Este atributo ha sido introducido en HTML5.
     * 
     * @return                                  Valor del atributo "pattern"
     */
    public String getPattern() {
        return getValorAtributo(ATR_PATTERN);
    }
    

    /**
     * Establece si este campo es obligatorio.
     * Este atributo ha sido introducido en HTML5.
     * 
     * @param required                          'true' para que este campo sea obligatorio
     *                                          'false' para que se opcional
     */
    public void setRequired(boolean required) {
        if (required) {
            setAtributo(ATR_REQUIRED);
        } else {
            removeAtributo(ATR_REQUIRED);
        }
    }
    
    
    /**
     * Comprueba si este campo es obligatorio.
     * Este atributo ha sido introducido en HTML5.
     * 
     * @return                                  'true' si este campo es obligatorio
     *                                          'false' si es opcional
     */
    public boolean getRequired() {
        boolean required = super.existeAtributo(ATR_REQUIRED);
        return required;
    }

    
    
    
    
    /**
     * Obtiene una cadena con el código HTML que representa el elemento.
     * 
     * @return                                  Cadena generada
     */
    public StringBuilder getHtml() {

        Tag tag = new Tag("input");
        tag.setTieneCierre(false);
        if (tipo != null) {
            tag.addAtributo("type", tipo);
        }
        tag.addAtributos(this);
        
        StringBuilder html = tag.toStringBuilder();
        return html;
    }
    
}
