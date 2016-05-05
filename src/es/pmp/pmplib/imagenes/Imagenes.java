/**
 * Imagenes.java
 *
 * Creado el 05-may-2016, 19:50:01
 */

package es.pmp.pmplib.imagenes;

import java.awt.Container;
import java.awt.Graphics2D;
import java.awt.MediaTracker;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.image.LookupOp;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;


/**
 * Funciones relacionadas con las imágenes.
 * 
 * @author pmpreciado
 */
public class Imagenes {

    
    /**
     * Cambia el tamaño de una imagen suministrada como un byte [], y devuelve la imagen escalada en formato JPEG.
     * No se permite expandir la imagen original (está desactivado manualmente), solo contraerla.
     *
     * Opcionalmente, si se suministra un filtro, se puede cambiar el color de la imagen resultante.
     * Por ejemplo, para cambiar el color negro de la imagen por un verde, se puede pasar un filtro como este:
     *      short umbral=10, R=10, G=200, B=10;
     *      LookupOp filtro = WebComun.crearFiltroColorearNegro(umbral, R, G, B);
     *      byte_imagen = resizeImagen(bytes, ancho, alto, filtro);
     *
     * Si no se sincroniza la función, el Tomcat a veces falla al redimensionar la imagen si entran varias peticiones simultáneas.
     *
     *
     * @param input                         byte [] con la imagen original
     * @param ancho                         Nuevo ancho para la imagen (Puede ser 0 para usar el ancho original)
     * @param alto                          Nuevo alto (Puede ser 0 para usar el alto original)
     * @param filtro                        Tabla de correspondencia de colores del filtro a aplicar (puede ser 'null', y no se aplica filtro
     *
     * @return                              byte [] con la imagen a escala
     * 
     * @throws java.io.IOException
     * @throws java.lang.InterruptedException
     *
     * @see crearFiltroTeñir(short, short, short)
     * @see crearFiltroColorearNegro(short, short, short, short)
     */
    public synchronized static byte [] resizeImagen(byte [] input, int ancho, int alto, LookupOp filtro) throws IOException, InterruptedException
    {
        // Si no se sincroniza, el Tomcat a veces falla al redimensionar la imagen si entran varias peticiones simultneas
        java.awt.Image imagen_orig = new ImageIcon(input).getImage();

        // El MediaTracker ayuda a cargar la imagen fluidamente a través de Internet
        MediaTracker mediaTracker = new MediaTracker(new Container());
        mediaTracker.addImage(imagen_orig, 0);
        mediaTracker.waitForID(0);

        // Alto y ancho de la imagen original
        int ancho_orig = imagen_orig.getWidth(null);
        int alto_orig = imagen_orig.getHeight(null);

        // No permitimos expandir, solo reducir la imagen
        if (ancho == 0 || ancho > ancho_orig) ancho = ancho_orig;
        if (alto == 0 || alto > alto_orig) alto = alto_orig;

        if (ancho != ancho_orig || alto != alto_orig) {
            // Determinamos el tamaño de la imagen escalada, a partir del ancho y el alto
            double ratio = (double)ancho / (double)alto;
            double ratio_orig = (double)ancho_orig / (double)alto_orig;
            if (ratio < ratio_orig) {
              alto = (int)(ancho / ratio_orig);
            } else {
              ancho = (int)(alto * ratio_orig);
            }
        }

        // Escalamos la imagen
        BufferedImage imagen_escala = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_RGB);

        Graphics2D graphics2D = imagen_escala.createGraphics();
        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2D.drawImage(imagen_orig, 0, 0, ancho, alto, null);

        // Guardamos la imagen escalada en un byte []
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        BufferedOutputStream out = new BufferedOutputStream(baos);

        // Generamos el JPEG
        if (filtro != null) {
            BufferedImage imagen_coloreada = filtro.filter(imagen_escala, null);
            ImageIO.write(imagen_coloreada, "jpeg", baos);
        } else {
            ImageIO.write(imagen_escala, "jpeg", baos);
        }

        byte [] output = baos.toByteArray();
        out.close();

        return output;
    }



    /**
     * Cambia el tamaño de una imagen suministrada como un byte [], y devuelve la imagen escalada
     * en formato JPEG. No se permite expandir la imagen original (está desactivado manualmente), solo contraerla.
     * Si no se sincroniza la función, el Tomcat a veces falla al redimensionar la imagen si entran varias peticiones simultáneas.
     *
     * @param input                         byte [] con la imagen original
     * @param ancho                         Ancho de la imagen
     * @param alto                          Alto de la imagen
     * 
     * @return                              byte [] con la imagen filtrada
     * 
     * @throws java.io.IOException
     * @throws java.lang.InterruptedException
     */
    public synchronized static byte [] resizeImagen(byte [] input, int ancho, int alto) throws IOException, InterruptedException
    {
        byte [] output = resizeImagen(input, ancho, alto, null);
        return output;
    }

}
