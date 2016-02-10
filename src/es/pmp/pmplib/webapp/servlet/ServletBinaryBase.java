/**
 * ServletBinaryBase.java
 *
 * Creado el 10-feb-2016, 19:11:40
 */

package es.pmp.pmplib.webapp.servlet;

import es.pmp.pmplib.errores.Errores;
import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Estructura base para un servlet encargado de servir contenido binario.
 * 
 * @author pmpreciado
 */
public class ServletBinaryBase implements ServletInterface {

    /** Algunos Content-Type comunes */
    public static final String CONTENT_TYPE_AVI     = "video/x-msvideo";
    public static final String CONTENT_TYPE_DOC     = "application/msword";
    public static final String CONTENT_TYPE_JPEG    = "image/jpeg";
    public static final String CONTENT_TYPE_PNG     = "image/png";
    public static final String CONTENT_TYPE_MP4     = "video/mp4";
    public static final String CONTENT_TYPE_MPEG    = "video/mpeg";
    public static final String CONTENT_TYPE_PDF     = "application/pdf";
    public static final String CONTENT_TYPE_XLS     = "application/vnd.ms-excel";
    public static final String CONTENT_TYPE_WMV     = "video/x-ms-wmv";
    public static final String CONTENT_TYPE_ZIP     = "application/zip";
    
    
    protected ServletContext sc;
    protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected HttpSession session;

    
    /** Content-type de la página */
    private String content_type;
    
    /** Contenido que se va a servir */
    private byte [] contenido;
    
    
    /**
     * Establece el tipo de contenido que se va a servir.
     * 
     * @param content_type                      Tipo de contenido que se va a servir (ServletBinaryBase.CONTENT_TYPE_xxx u otro)
     */
    @Override
    public void setContentType(String content_type) {
        this.content_type = content_type;
    }
    
    
    /**
     * Obtiene el tipo de contenido que se va a servir.
     * 
     * @return                                  Tipo de contenido que se va a servir (ServletBinaryBase.CONTENT_TYPE_xxx u otro)
     */
    @Override
    public String getContentType() {
        return content_type;
    }
    
    
    /**
     * Establece el contenido que se va a servir.
     * 
     * @param contenido                         Contenido que se va a servir
     */
    public void setContenido(byte [] contenido) {
        this.contenido = contenido;
    }
    
    /**
     * Obtiene el contenido que se va a servir.
     * 
     * @return                                  Contenido que se va a servir
     */
    public byte [] getContenido() {
        return contenido;
    }
    
    
    
    /** 
     * Inicializa la clase.
     * Almacena localmente los objetos request y response.
     * Esta función es un buen lugar para abrir las conexiones a la base de datos.
     * 
     * @param sc                                Contexto del Servlet
     * @param request                           Request
     * @param response                          Response
     * 
     * @throws Throwable                        No se puede obtener el stream de salida
     */
    @Override
    public void init(ServletContext sc, HttpServletRequest request, HttpServletResponse response) throws Throwable {

        this.sc = sc;
        this.session = request.getSession();
        this.request = request;
        this.response = response;
        

        /*
        // EJEMPLO DE USO DE ATRIBUTOS DE SESION Y CONEXIONES:
        
        cnt = (Contenedor) sc.getAttribute("cnt");
        try {
            sesion = (Sesion) session.getAttribute("sesion");
        } catch (java.lang.ClassCastException ccex) {
            sesion = null;
        }

        if (sesion == null) {
            sesion = new Sesion();
            session.setAttribute("sesion", sesion);
        }
        
        try {
            conexion = cnt.getConexion();
            conexion_informix = cnt.getConexionInformix();
        } catch (CajalnetLogException cex) {
            throw new ServletException(cex);
        }
        **/
    }
    
    
    /**
     * Realiza las tareas previas a la ejecución del servlet, como comprobaciones y registros.
     *
     * @throws Throwable
     */
    @Override
    public void preLaunch() throws Throwable {
    }
    
    
    /**
     * Genera la salida del servlet.
     * 
     * @throws Throwable                        Error al generar la salida
     */
    @Override
    public void generateOutput() throws Throwable {
        try {

        } catch (Throwable th) {
            if (th instanceof ServletException) {
                throw (ServletException) th;
            }
            
            ServletException sex = new ServletException(th);
            throw sex;
        }
    }
    
    
    /**
     * Obtiene el contenido_texto_html a retornar, y lo vuelca a la salida del servlet.
     * El contenido_texto_html puede ser texto/html o binario.
     * Tras volcar el contenido_texto_html, cierra el "stream" de salida.
     * 
     * @throws Exception                        Error al enviar la salida de la página
     */
    @Override
    public void output() throws Exception {
        if (content_type != null) {
            response.setContentType(content_type);
        }
        
        ServletOutputStream sos = null;
        try {
            sos = response.getOutputStream();
            if (contenido != null) {
                sos.write(contenido);
            }
        } catch (IOException ioex) {
            Exception ex = new Exception(Errores.getMensaje(Errores.ERR_WEBAPP_ENVIAR_SALIDA_PAGINA), ioex);
            throw ex;
        }

        if (sos != null) {
            try {
                sos.close();
            } catch (IOException ioex) {
                Exception ex = new Exception(Errores.getMensaje(Errores.ERR_WEBAPP_CERRAR_SALIDA_PAGINA), ioex);
                throw ex;
            }
        }
    }
    
    
    /**
     * Finaliza la ejecución del servlet.
     * Esta función es un buen lugar para cerrar y devolver al pool las conexiones a la base de datos.
     * Es obligatorio llamar este método al finalizar el servlet, por lo que es una buena idea hacer la llamada dentro del bloque "finally" de un try-catch.
     * 
     * @throws ServletException                 Error al cerrar la generar la salida
     */
    @Override
    public void end() throws ServletException {
        
        /*
        // Ejemplo de devolver al pool la conexión de MySQL
        if (conexion != null) {
            try {
                conexion.close();
            } catch (Throwable sqlex) {
                String mensaje = Errores.getMensaje(Errores.ERR_BD_CERRAR_CONEXION);
                LogManager.getRootLogger().error("Error al cerrar la conexión de MySQL", sqlex);
            }
        }
        */
            
        this.contenido = null;
        this.sc = null;
        this.request = null;
        this.response = null;
    }

}
