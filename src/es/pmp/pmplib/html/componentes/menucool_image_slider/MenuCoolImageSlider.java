/**
 * MenuCoolImageSlider.java
 *
 * Creado el 05-may-2016, 19:12:09
 */

package es.pmp.pmplib.html.componentes.menucool_image_slider;

import es.pmp.pmplib.html.base.Tag;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase para facilitar la generación de presentaciones de imágenes basadas en la API de MenuCool.
 * Aunque esta API permite funciones interesantes, como thumbnails, controles de navegación, etc., esta clase sólo soporta la presentación básica.
 * Para que las imágenes se muestren correctamente, todas deben tener el mismo tamaño.
 * La API ninja-slider de MenuCool soporta el escalado de imágenes, pero tiene una licencia de pago.
 * 
 * Para que funcione la presentación, hay que incluir en la página este JavaScript y está hoja de estilos:
 * 
 *    <link href="themes/1/js-image-slider.css" rel="stylesheet" type="text/css" />
 *    <script src="themes/1/js-image-slider.js" type="text/javascript"></script>
 * 
 * @see http://www.menucool.com/jquery-slider
 * 
 * @author pmpreciado
 */
public class MenuCoolImageSlider {

    private static final String ID_DIV_SLIDER_FRAME     = "sliderFrame";
    private static final String ID_DIV_SLIDER           = "slider";
    
    
    /** Imágenes que componen la presentación */
    List <MenuCoolImage> l_img;
    
    
    /**
     * Crea una instancia de la clase.
     */
    public MenuCoolImageSlider() {
        l_img = new ArrayList <> ();
    }
    
    
    /**
     * Incorpora una imagen al slider.
     * 
     * @param image                             Imagen a incorporar
     */
    public void addImagen(MenuCoolImage image) {
        l_img.add(image);
    }
    
    
    /**
     * Genera el código HTML que muestra el slider.
     * 
     * @return                                  Código HTML correspondiente a la imagen
     */
    public StringBuilder getHtml() {
        
        Tag tag_div_slider_frame = new Tag("div");
        tag_div_slider_frame.addAtributo("id", ID_DIV_SLIDER_FRAME);
        
        Tag tag_div_slider = new Tag("div");
        tag_div_slider.addAtributo("id", ID_DIV_SLIDER);
        tag_div_slider_frame.add(tag_div_slider);
        
        for (int i = 0; i < l_img.size(); i++) {
            MenuCoolImage img = l_img.get(i);
            StringBuilder html_img = img.getHtml();
            tag_div_slider.add(html_img);
        }
        
        StringBuilder s = tag_div_slider.toStringBuilder();
        return s;
    }
    
}
