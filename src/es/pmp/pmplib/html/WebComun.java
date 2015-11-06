/**
 * WebComun.java
 *
 * Creado el 05-nov-2015, 13:45:32
 */

package es.pmp.pmplib.html;

import es.pmp.pmplib.html.base.Style;
import es.pmp.pmplib.html.base.Tag;


/**
 * Definiciones y funciones para la generación de elementos HTML comunes.
 * 
 * @author pmpreciado
 */
public class WebComun {

    
    public static final String SEPARACION_ESTANDAR = "16pt";
    public static final String SEPARACION_DOBLE    = "32pt";
    
    
    
    /**
     * Genera el código HTML para añadir una separación vertical.
     *
     * @param alto                              Alto (por ejemplo, "4pt")
     *
     * @return                                  Código HTML de la separación
     */
    public static String getSeparacion(String alto) {
        Tag tag_hr = new Tag("hr");
        Style style = new Style();
        style.addAtributo("display", "block");
        style.addAtributo("margin-top", "0pt");
        style.addAtributo("margin-bottom", alto);
        style.addAtributo("border-width", "0px");
        style.addAtributo("margin-left", "0pt");
        style.addAtributo("margin-right", "0pt");
        style.addAtributo("border-style", "inset");
        style.addAtributo("border-width", "0px");
        tag_hr.setAtributoStyle(style);
        String html = tag_hr.toString();
        return html;
    }

    
    /**
     * Genera el código HTML para añadir una separación vertical de altura estándar.
     *
     * @return                                  Código HTML de la separación
     */
    public static String getSeparacion() {
        String html = getSeparacion(SEPARACION_ESTANDAR);
        return html;
    }
}
