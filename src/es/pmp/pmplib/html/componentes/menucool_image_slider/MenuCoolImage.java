/**
 * MenuCoolImage.java
 *
 * Creado el 05-may-2016, 19:22:47
 */

package es.pmp.pmplib.html.componentes.menucool_image_slider;

import es.pmp.pmplib.Comun;
import es.pmp.pmplib.html.base.Tag;

/**
 * Guarda las propiedades de una imagen de las que aparecen en la presentación.
 * 
 * @author pmpreciado
 */
public class MenuCoolImage {

    /** Origen de la imagen */
    public String src;
    
    /** Título de la imagen */
    public String alt;
    
    
    /**
     * Crea una instancia de la clase.
     * 
     * @param src                               Origen de la imagen
     * @param alt                               Título de la imagen (puede ser 'null')
     */
    public MenuCoolImage(String src, String alt) {
        this.src = src;
        this.alt = alt;
    }
    
    
    /**
     * Crea una instancia de la clase.
     * 
     * @param src                               Origen de la imagen
     */
    public MenuCoolImage(String src) {
        this.src = src;
        this.alt = null;
    }
    
    
    /**
     * Genera el código HTML correspondiente a la imagen.
     * 
     * @return                                  Código HTML correspondiente a la imagen
     */
    public StringBuilder getHtml() {
        
        StringBuilder s = new StringBuilder();
        s.append("<img src=\"" + src + "\"");
        if (alt != null) {
            s.append(" alt=\"" + alt + "\"");
        }
        s.append(" />" + Comun.NL);
        return s;
    }
    
    
}
