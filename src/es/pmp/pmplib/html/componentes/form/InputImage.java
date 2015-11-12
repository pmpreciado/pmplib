/**
 * InputImage.java
 *
 * Creado el 09-nov-2015, 12:59:23
 */

package es.pmp.pmplib.html.componentes.form;

/**
 * Definición de un elemento de formulario de tipo "image".
 * Este tipo de elemento se utiliza para usar una imagen como botón de submit.
 * 
 * Este tipo de elemento fue introducido en HTML5.
 * 
 * @author pmpreciado
 */
public class InputImage extends FormItemBase {

    private static final String TYPE = "image";
    
    
    /** Atributos de uso común para los input image */
    private static final String ATR_SRC         = "src";
    private static final String ATR_ALT         = "alt";
    
    
    
    /**
     * Crea una instancia de la clase.
     */
    public InputImage() {
        super(TYPE);
    }
    
    
    /**
     * Establece el atributo "src", con el origen de la imagen.
     * 
     * @param valor                             Valor del atributo src
     */
    public void setSrc(String valor) {
        setAtributo(ATR_SRC, valor);
    }
    
    
    /**
     * Obtiene el valor del atributo "src", con el origen de la imagen.
     * 
     * @return                                  Valor del atributo src
     */
    public String getSrc() {
        String size = super.getValorAtributo(ATR_SRC);
        return size;
    }
    
    
    /**
     * Establece el atributo "alt", con el texto alternativo a mostrar si no está disponible la imagen.
     * 
     * @param valor                             Valor del atributo alt
     */
    public void setAlt(String valor) {
        setAtributo(ATR_ALT, valor);
    }
    
    
    /**
     * Obtiene el valor del atributo "src", con el texto alternativo a mostrar si no está disponible la imagen.
     * 
     * @return                                  Valor del atributo alt
     */
    public String getAlt() {
        String size = super.getValorAtributo(ATR_ALT);
        return size;
    }
}