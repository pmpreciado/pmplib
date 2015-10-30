/**
 * Head.java
 *
 * Creado el 28-oct-2015, 17:44:30
 */

package es.pmp.pmplib.html.componentes;

import es.pmp.pmplib.Cadenas;
import es.pmp.pmplib.Comun;
import java.util.ArrayList;
import java.util.List;

/**
 * Funciones para facilitar la sección head de las páginas web.
 * 
 * @author pmpreciado
 */
public class Head {

    /** Instrucción DOCTYPE predeterminada. Aunque no forma parte de la sección head, se genera aquí */
    private static final String DOCTYPE = "<!DOCTYPE html>";
    
    /** Etiqueta meta para especificar una codificación de la página UTF-8 */
    public static final String META_CHARSET_UTF8    = "meta charset=\"UTF-8\"";
    
    /** Etiqueta meta para especificar la descripción del contenido */
    public static final String META_DESCRIPTION     = "name=\"description\" content=\"%0%\"";
    
    /** Etiqueta meta para especificar las palabras clave */
    public static final String META_KEYWORDS        = "name=\"keywords\" content=\"%0%\"";

    /** Etiqueta meta para especificar el autor */
    public static final String META_AUTHOR          = "name=\"author\" content=\"%0%\"";
    
    /** Etiqueta meta para especificar los segundos de actualización automática */
    public static final String META_REFRESH         = "http-equiv=\"refresh\" content=\"%0%\"";

    
    
    /** Versión de la aplicación */
    private String version;
    
    /** Título de la página */
    private String title;
    
    /** Ruta para el icono del enlace a la aplicación (.ico) */
    private String ruta_icono_enlace;
    
    /** Ruta para el icono de la aplicación (.jpg, .png)) */
    private String ruta_icono;
    
    /** Ficheros css a vincular */
    private List <String> l_css;

    /** Ficheros JavaScript a vincular */
    private List <String> l_script;

    /** Etiquetas meta a incluir en la sección head */
    private List <String> l_meta;
    
    /** Contenido libre */
    List <String> l_libre;

    
    /**
     * Crea la instancia de la clase.
     */
    public Head() {
        l_css       = new ArrayList <> ();
        l_script    = new ArrayList <> ();
        l_meta      = new ArrayList <> ();
        l_libre     = new ArrayList();
    }
    
    
    /**
     * Establece el título de la página.
     *
     * @param title                             Nuevo título de la página (puede ser 'null')
     */
    public void setTitle(String title) {
        this.title = title;
    }
    
    
    /**
     * Obtiene el título de la página.
     *
     * @return                                  Título de la página (puede ser 'null')
     */
    public String getTitle() {
        return title;
    }
    
    
    /**
     * Establece la versión de la aplicación.
     *
     * @param version                           Versión de la aplicación (puede ser 'null')
     */
    public void setVersion(String version) {
        this.version = version;
    }
    
    
    /**
     * Obtiene la versión de la aplicación.
     *
     * @return                                  Versión de la aplicación (puede ser 'null')
     */
    public String getVersion() {
        return version;
    }
    
    
    /**
     * Establece la ruta para el icono de la aplicación (.jpg, .png).
     *
     * @param ruta_icono                        Ruta para el icono de la aplicación (.jpg, .png).
     */
    public void setRutaIcono(String ruta_icono) {
        this.ruta_icono = ruta_icono;
    }
    
    
    /**
     * Obtiene la ruta para el icono de la aplicación (.jpg, .png).
     *
     * @return                                  Ruta para el icono de la aplicación (.jpg, .png)
     */
    public String getRutaIcono() {
        return ruta_icono;
    }
    
    
    /**
     * Establece la ruta del icono para el enlace a la aplicación (.ico).
     *
     * @param ruta_icono_enlace                 Ruta del icono para el enlace a la aplicación (.ico)
     */
    public void setRutaIconoEnlace(String ruta_icono_enlace) {
        this.ruta_icono_enlace = ruta_icono_enlace;
    }
    
    
    /**
     * Obtiene la ruta del icono para el enlace a la aplicación (.ico).
     *
     * @return                                  Ruta del icono para el enlace a la aplicación (.ico)
     */
    public String getRutaIconoEnlace() {
        return ruta_icono_enlace;
    }
    

    /**
     * Añade una etiqueta meta a la sección head.
     * La etiqueta de entrada no debe incluir el tag meta ni los caracteres < y >
     * 
     * Ejemplos de etiqueta:
     *      http-equiv="Content-Type" content="text/html; charset=UTF-8"
     *      name="viewport" content="width=device-width, initial-scale=1.0"
     *      name="description" content="Mi web personal"
     * 
     * @param meta                              Etiqueta a añadir
     */
    public void addMeta(String meta) {
        l_meta.add(meta);
    }
    
    
    /**
     * Añade la etiqueta meta para especificar una codificación de la página UTF-8.
     */
    public void addMetaCharsetUtf8() {
        addMeta(META_CHARSET_UTF8);
    }
    
    
    /**
     * Añade la etiqueta meta para especificar la descripción del contenido.
     * 
     * @param descripcion                       Descripción del contenido
     */
    public void addMetaDescription(String descripcion) {
        String meta_description = Cadenas.sustituirTagCadena(META_DESCRIPTION, descripcion);
        addMeta(meta_description);
    }
    
    
    /**
     * Añade la etiqueta meta para especificar las palabras clave relacionadas.
     * 
     * @param keywords                          Palabras clave relacionadas
     */
    public void addMetaKeywords(String keywords) {
        String meta_keywords = Cadenas.sustituirTagCadena(META_KEYWORDS, keywords);
        addMeta(meta_keywords);
    }
    
    
    /**
     * Añade la etiqueta meta para especificar el autor.
     * 
     * @param author                            Autor
     */
    public void addMetaAuthor(String author) {
        String meta_author = Cadenas.sustituirTagCadena(META_AUTHOR, author);
        addMeta(meta_author);
    }
    
    
    /**
     * Añade la etiqueta meta para especificar los segundos de actualización automática.
     * 
     * @param refresh                           Segundos de actualización automática
     */
    public void addMetaRefresh(int refresh) {
        String meta_refresh = Cadenas.sustituirTagCadena(META_REFRESH, refresh);
        addMeta(meta_refresh);
    }
    
    
    /**
     * Añade una referencia a un fichero CSS a vincular.
     * 
     * @param css                               Ruta al fichero CSS, relativa al contexto de la web
     */
    public void addCss(String css) {
        l_css.add(css);
    }
    
    
    /**
     * Añade una referencia a un fichero JavaScript a vincular.
     * 
     * @param js                                Ruta al fichero JavaScript, relativa al contexto de la web
     */
    public void addScript(String js) {
        l_script.add(js);
    }
    
    
    /**
     * Añade contenido libre a la sección head.
     * 
     * @param contenido                         Contenido libre
     */
    public void addContenido(String contenido) {
        l_libre.add(contenido);
    }
    
    
    
//    /**
//     * Obtiene el enlace para establecer la "G" de GestEx como el icono de la página.
//     * 
//     * @return                              Enlace para el icono icono
//     */
//    public String getLinksIconoSms() {
//        String etiquetas =
//            "<link rel=\"icon\" type=\"image/png\" href=\"" + Recursos.IMG_G + "\" />\n" +
//            "<link rel=\"shortcut icon\" type=\"image/x-icon\" href=\"" + Recursos.ICONO_G + "\" />\n";
//        
//        return etiquetas;
//    }
    
    
    /**
     * Genera el contenido de la sección head.
     * 
     * @return                                  Contenido generado
     */
    public StringBuilder toStringBuilder() {
        StringBuilder s = new StringBuilder();
        s.append(DOCTYPE + Comun.NL);

        s.append("<head>" + Comun.NL);
        
            // Versión
            if (version != null) {
                s.append("<!-- Versión " + version + "-->" + Comun.NL);
            }
        
            // Etiquetas meta
            for (String meta : l_meta) {
                s.append("<meta " + meta + ">" + Comun.NL);
            }
            
            // Referencias css
            for (String css : l_css) {
                s.append("<link rel=\"stylesheet\" href=\"" + css + "\" />" + Comun.NL);
            }

            // Referencias JavaScript
            for (String script : l_script) {
                s.append("<script src=\"" + script + "\" />" + Comun.NL);
            }
            
            // Título de la página
            if (title != null) {
                s.append(title + Comun.NL);
            };
            
            // Icono de la aplicación (.jpg, .png)
            if (!Cadenas.vacio(ruta_icono)) {
                s.append("<link rel=\"icon\" type=\"image/png\" href=\"" + ruta_icono + "\" />"  + Comun.NL);
            }
            
            // Icono del enlace a la aplicación (.ico)
            if (!Cadenas.vacio(ruta_icono_enlace)) {
                s.append("<link rel=\"shortcut icon\" type=\"image/x-icon\" href=\"" + ruta_icono_enlace + Comun.NL);
            }
            
        s.append("</head>" + Comun.NL);
        
        return s;
    }

    
    /**
     * Genera el contenido de la sección head.
     * 
     * @return                                  Contenido generado
     */
    @Override
    public String toString() {
        StringBuilder s = toStringBuilder();
        return s.toString();
    }
    
}
