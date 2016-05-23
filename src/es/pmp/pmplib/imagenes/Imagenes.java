/**
 * Imagenes.java
 *
 * Creado el 05-may-2016, 19:50:01
 */

package es.pmp.pmplib.imagenes;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.PixelGrabber;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;


/**
 * Funciones relacionadas con las imágenes.
 * 
 * @author pmpreciado
 */
public class Imagenes {

//    
//    /**
//     * Cambia el tamaño de una imagen suministrada como un byte [], y devuelve la imagen escalada en formato JPEG.
//     * No se permite expandir la imagen original (está desactivado manualmente), solo contraerla.
//     *
//     * Opcionalmente, si se suministra un filtro, se puede cambiar el color de la imagen resultante.
//     * Por ejemplo, para cambiar el color negro de la imagen por un verde, se puede pasar un filtro como este:
//     *      short umbral=10, R=10, G=200, B=10;
//     *      LookupOp filtro = WebComun.crearFiltroColorearNegro(umbral, R, G, B);
//     *      byte_imagen = resizeImagen(bytes, ancho, alto, filtro);
//     *
//     * Si no se sincroniza la función, el Tomcat a veces falla al redimensionar la imagen si entran varias peticiones simultáneas.
//     *
//     *
//     * @param input                         byte [] con la imagen original
//     * @param ancho                         Nuevo ancho para la imagen (Puede ser 0 para usar el ancho original)
//     * @param alto                          Nuevo alto (Puede ser 0 para usar el alto original)
//     * @param filtro                        Tabla de correspondencia de colores del filtro a aplicar (puede ser 'null', y no se aplica filtro
//     *
//     * @return                              byte [] con la imagen a escala
//     * 
//     * @throws java.io.IOException
//     * @throws java.lang.InterruptedException
//     *
//     * @see crearFiltroTeñir(short, short, short)
//     * @see crearFiltroColorearNegro(short, short, short, short)
//     */
//    public synchronized static byte [] resizeImagen(byte [] input, int ancho, int alto, LookupOp filtro) throws IOException, InterruptedException
//    {
//        // Si no se sincroniza, el Tomcat a veces falla al redimensionar la imagen si entran varias peticiones simultneas
//        java.awt.Image imagen_orig = new ImageIcon(input).getImage();
//
//        // El MediaTracker ayuda a cargar la imagen fluidamente a través de Internet
//        MediaTracker mediaTracker = new MediaTracker(new Container());
//        mediaTracker.addImage(imagen_orig, 0);
//        mediaTracker.waitForID(0);
//
//        // Alto y ancho de la imagen original
//        int ancho_orig = imagen_orig.getWidth(null);
//        int alto_orig = imagen_orig.getHeight(null);
//
//        // No permitimos expandir, solo reducir la imagen
//        if (ancho == 0 || ancho > ancho_orig) ancho = ancho_orig;
//        if (alto == 0 || alto > alto_orig) alto = alto_orig;
//
//        if (ancho != ancho_orig || alto != alto_orig) {
//            // Determinamos el tamaño de la imagen escalada, a partir del ancho y el alto
//            double ratio = (double)ancho / (double)alto;
//            double ratio_orig = (double)ancho_orig / (double)alto_orig;
//            if (ratio < ratio_orig) {
//              alto = (int)(ancho / ratio_orig);
//            } else {
//              ancho = (int)(alto * ratio_orig);
//            }
//        }
//
//        // Escalamos la imagen
//        BufferedImage imagen_escala = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_RGB);
//
//        Graphics2D graphics2D = imagen_escala.createGraphics();
//        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
//        graphics2D.drawImage(imagen_orig, 0, 0, ancho, alto, null);
//
//        // Guardamos la imagen escalada en un byte []
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        BufferedOutputStream out = new BufferedOutputStream(baos);
//
//        // Generamos el JPEG
//        if (filtro != null) {
//            BufferedImage imagen_coloreada = filtro.filter(imagen_escala, null);
//            ImageIO.write(imagen_coloreada, "jpeg", baos);
//        } else {
//            ImageIO.write(imagen_escala, "jpeg", baos);
//        }
//
//        byte [] output = baos.toByteArray();
//        out.close();
//
//        return output;
//    }
//
//
//
//    /**
//     * Cambia el tamaño de una imagen suministrada como un byte [], y devuelve la imagen escalada
//     * en formato JPEG. No se permite expandir la imagen original (está desactivado manualmente), solo contraerla.
//     * Si no se sincroniza la función, el Tomcat a veces falla al redimensionar la imagen si entran varias peticiones simultáneas.
//     *
//     * @param input                         byte [] con la imagen original
//     * @param ancho                         Ancho de la imagen
//     * @param alto                          Alto de la imagen
//     * 
//     * @return                              byte [] con la imagen filtrada
//     * 
//     * @throws java.io.IOException
//     * @throws java.lang.InterruptedException
//     */
//    public synchronized static byte [] resizeImagen(byte [] input, int ancho, int alto) throws IOException, InterruptedException
//    {
//        byte [] output = resizeImagen(input, ancho, alto, null);
//        return output;
//    }

    
    /**
     * Comprueba si la imagen dada contiene píxeles transparentes.
     * 
     * @param image                         Imagen a comprobar
     * 
     * @return                              'true' si la imagen contiene píxeles transparentes
     *                                      'false' en caso contrario
     */
    public static boolean hasAlpha(Image image) {
        
        // If buffered image, the color model is readily available
        if (image instanceof BufferedImage) {
            BufferedImage bimage = (BufferedImage)image;
            return bimage.getColorModel().hasAlpha();
        }

        // Use a pixel grabber to retrieve the image's color model;
        // grabbing a single pixel is usually sufficient
         PixelGrabber pg = new PixelGrabber(image, 0, 0, 1, 1, false);
        try {
            pg.grabPixels();
        } catch (InterruptedException e) {
        }

        // Get the image's color model
        ColorModel cm = pg.getColorModel();
        if (cm == null) {
            return false;
        }
        
        return cm.hasAlpha();
    }

    
    
    /**
     * Redimensiona una imagen al ancho y al alto dados.
     * 
     * @param imagen_original
     * @param nuevo_ancho
     * @param nuevo_alto
     * 
     * @return 
     */
    public static BufferedImage escalarImagen(BufferedImage imagen_original, int nuevo_ancho, int nuevo_alto) {
        
        int ancho = imagen_original.getWidth();
        int alto = imagen_original.getHeight();
        
        BufferedImage imagen_escala = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = imagen_escala.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(imagen_original, 0, 0, nuevo_ancho, nuevo_alto, null);
        g2.dispose();
        
        
        BufferedImage imagen_escala_recortada = imagen_escala.getSubimage(0, 0, nuevo_ancho, nuevo_alto);
        imagen_escala_recortada.flush();
        return imagen_escala_recortada;
    }
    
    
    /**
     * Escala una imagen para que ocupe como máximo el ancho y el alto dados.
     * 
     * @param imagen_original
     * @param max_ancho
     * @param max_alto
     * 
     * @return 
     */
    public static BufferedImage escalarImagenMax(BufferedImage imagen_original, int max_ancho, int max_alto) {
        
        int ancho = imagen_original.getWidth();
        int alto = imagen_original.getHeight();

        float escala_horizontal = 1;
        float escala_vertical = 1;
        
        if (ancho > max_ancho) {
            escala_horizontal = (float) max_ancho / (float) ancho;
        }
        if (alto > max_alto) {
            escala_vertical = (float) max_alto / (float) alto;
        }
        
        float escala = Math.min(escala_horizontal, escala_vertical);
        
        float f_nuevo_ancho = (float) ancho * escala;
        float f_nuevo_alto = (float) alto * escala;
        
        int nuevo_ancho = (int) f_nuevo_ancho;
        int nuevo_alto = (int) f_nuevo_alto;
        
        BufferedImage imagen_escala = escalarImagen(imagen_original, nuevo_ancho, nuevo_alto);
        
        return imagen_escala;
    }
    
    
    /**
     * Guarda en disco una imagen en formato PNG.
     * 
     * @param imagen                            Imagen a guardar
     * @param ruta_fichero                      Ruta para guardar la imagen
     * 
     * @return                                  Ruta del fichero generado
     */
    public static String guardarImagenPng(BufferedImage imagen, String ruta_fichero) {

        File fichero_salida = new File(ruta_fichero);
        try {
            ImageIO.write(imagen, "png", fichero_salida);
        } catch (IOException ioex) {
            String mensaje = "No se puede guardar el fichero " + fichero_salida.getAbsolutePath();
            Logger.getLogger(Imagenes.class.getName()).log(Level.SEVERE, mensaje, ioex);
        }
        
        String ruta = fichero_salida.getAbsolutePath();
        return ruta;
    }

    
    /**
     * Carga una imagen en formato PNG.
     * 
     * @param ruta_fichero                      Ruta del fichero que contiene la imagen
     * 
     * @return                                  Imagen cargada
     */
    public static BufferedImage cargarImagenPng(String ruta_fichero) {
        BufferedImage image = null;
        File fichero_entrada = new File(ruta_fichero);
        try {
            image = ImageIO.read(fichero_entrada);
            
        } catch (IOException ioex) {
            String mensaje = "No se puede cargar el fichero " + fichero_entrada.getAbsolutePath();
            Logger.getLogger(Imagenes.class.getName()).log(Level.SEVERE, mensaje, ioex);
        }
        
        return image;
    }
    
    
    
    /**
     * Dibuja un punto grueso sobre una imagen.
     * El punto ocupa un área de 3x3.
     * 
     * @param imagen
     * @param x
     * @param y
     * @param color
     */
    public static void dibujarPuntoGrueso(BufferedImage imagen, int x, int y, Color color) {
        int rgb = color.getRGB();
        
        int ancho = imagen.getWidth();
        int alto = imagen.getHeight();
        
        int x2 = x;
        int y2 = y;
        
        x2 = x - 1;
        y2 = y - 1;
        if (x2 >= 0 && y2 >= 0) {
            imagen.setRGB(x2, y2, rgb);
        }
        
        x2 = x;
        y2 = y - 1;
        if (y2 >= 0) {
            imagen.setRGB(x2, y2, rgb);
        }
        
        x2 = x + 1;
        y2 = y - 1;
        if (x2 < ancho && y2 >= 0) {
            imagen.setRGB(x2, y2, rgb);
        }

        x2 = x - 1;
        y2 = y;
        if (x2 >= 0) {
            imagen.setRGB(x2, y2, rgb);
        }

        x2 = x;
        y2 = y;
        imagen.setRGB(x2, y2, rgb);
        
        x2 = x + 1;
        y2 = y;
        if (x2 < ancho) {
            imagen.setRGB(x2, y2, rgb);
        }
        
        
        x2 = x - 1;
        y2 = y + 1;
        if (x2 >= 0 && y2 < alto) {
            imagen.setRGB(x2, y2, rgb);
        }
        
        x2 = x;
        y2 = y + 1;
        if (y2 < alto) {
            imagen.setRGB(x2, y2, rgb);
        }
        
        x2 = x + 1;
        y2 = y + 1;
        if (x2 < ancho && y2 < alto) {
            imagen.setRGB(x2, y2, rgb);
        }
    }

}
