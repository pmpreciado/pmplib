/**
 * ServletInterface.java
 *
 * Creado el 22-oct-2015, 18:31:13
 */

package es.pmp.pmplib.webapp.servlet;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Interfaz para los servlet que generan páginas HTML.
 * 
 * @author pmpreciado
 */
public interface ServletInterface {


    /**
     * Establece el tipo de contenido que se va a servir.
     * 
     * @param content_type                      Tipo de contenido que se va a servir
     *                                          Ejemplos:
     *                                              text/html;charset=UTF-8
     *                                              image/jpeg
     */
    public void setContentType(String content_type);
    
    /**
     * Obtiene el tipo de contenido que se va a servir.
     * 
     * @return                                  Tipo de contenido que se va a servir
     */
    public String getContentType();
    
    /**
     * Inicializa la clase.
     * 
     * @param sc                                Contexto del Servlet
     * @param request                           Request
     * @param response                          Response
     * 
     * @throws Throwable                        No se puede obtener el stream de salida
     */
    public void init(ServletContext sc, HttpServletRequest request, HttpServletResponse response) throws Throwable;

    /**
     * Realiza las tareas previas a la ejecución del servlet, como comprobaciones y registros.
     *
     * @throws Throwable
     */
    public void preLaunch() throws Throwable;

    /**
     * Genera la salida del servlet.
     * 
     * @throws Throwable
     */
    public void generateOutput() throws Throwable;
    
    /**
     * Obtiene el contenido HTML de la página web, y lo vuelca a la salida HTTP.
     * Tras volcar el contenido, cierra el "stream" de salida.
     * 
     * @throws Throwable                        Error al enviar la salida de la página
     */
    public void output() throws Throwable;
    
    /**
     * Finaliza la ejecución del servlet, cerrando las conexiones a la base de datos.
     * 
     * @throws Throwable
     */
    public void end() throws Throwable;
    
}
