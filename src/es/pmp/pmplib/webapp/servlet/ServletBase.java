/**
 * ServletBase.java
 *
 * Creado el 22-oct-2015, 18:42:09
 */

package es.pmp.pmplib.webapp.servlet;

import es.pmp.pmplib.errores.Errores;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Estructura base para un servlet encargado de generar una página HTML.
 * 
 * Al invocar una página web, se crea una instancia de esta clase, y, por este orden, se irán llamando a estos métodos:
 * 
 *      1. init(ServletContext, HttpServletRequest, HttpServletResponse)
 *      2. preLaunch()
 *      3. generateOutput()
 *      4. output()
 *      5. end()
 *
 * Si hay que utilizar conexiones a BD, una buena opción es abrirlas en el método init(..) y cerrarlas en end()
 * 
 * @author Pedro María Preciado
 */
public abstract class ServletBase implements ServletInterface {

    public static final String DOCTYPE = "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 Transitional//EN\"\n" +
                                   "\"http://www.w3.org/TR/html4/loose.dtd\">\n";

    /** Algunos Content-Type comunes */
    protected static final String CONTENT_TYPE_HTML_UTF_8               = "text/html;charset=UTF-8";
    protected static final String CONTENT_TYPE_HTML_ISO_8859_1          = "text/html;charset=ISO-8859-1";
    protected static final String CONTENT_TYPE_HTML_ISO_8859_15         = "text/html;charset=ISO-8859-15";
    protected static final String CONTENT_TYPE_XML_UTF_8                = "application/xml;charset=UTF-8";
    
    protected static final String CONTENT_TYPE_PREDETERMINADO           = CONTENT_TYPE_HTML_UTF_8;

    /**
     * Tipos de salida de la página.
     * Si se establece un tipo de salida distinto del predeterminado, hay que cambiar el content-type de la página.
     */
    protected static final int TIPO_SALIDA_TEXTO_HTML       = 1;
    protected static final int TIPO_SALIDA_BINARIO          = 2;
    protected static final int TIPO_SALIDA_PREDETERMINADO   = TIPO_SALIDA_TEXTO_HTML;
    
    protected ServletContext sc;
    protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected HttpSession session;

    /** Content-type de la página */
    private String content_type;
    
    /** Tipo de salida de la página (ServletBase.TIPO_SALIDA_xxx) */
    private int tipo_salida;

    /** Contenido de la página para las salidas de tipo texto/html */
    private StringBuilder contenido_texto_html;

    
    /** 
     * Crea la instancia de la clase.
     */
    public ServletBase() {
        content_type = CONTENT_TYPE_PREDETERMINADO;
        tipo_salida = TIPO_SALIDA_PREDETERMINADO;
        contenido_texto_html = new StringBuilder();
    }
    
    
    /**
     * Establece el tipo de contenido que se va a servir.
     * 
     * @param content_type                      Tipo de contenido que se va a servir (ServletBase.CONTENT_TYPE_xxx u otro)
     */
    @Override
    public void setContentType(String content_type) {
        this.content_type = content_type;
    }
    
    
    /**
     * Obtiene el tipo de contenido que se va a servir.
     * 
     * @return                                  Tipo de contenido que se va a servir (ServletBase.CONTENT_TYPE_xxx u otro)
     */
    @Override
    public String getContentType() {
        return content_type;
    }

    
    /**
     * Establece el tipo de salida para la página.
     * Si se utiliza un tipo de salida distinto del predeterminado, también hay que cambiar el content-type de la página.
     * 
     * @param tipo_salida                       Tipo de salida de la página (ServletBase.TIPO_SALIDA_xxx)
     * 
     * @see setContentType(String)
     */
    public void setTipoSalida(int tipo_salida) {
        this.tipo_salida = tipo_salida;
    }
    
    
    /**
     * Establece el tipo de salida para la página y el content type de la misma.
     * 
     * @param tipo_salida                       Tipo de salida de la página (ServletBase.TIPO_SALIDA_xxx)
     * @param content_type                      "Content type" a utilizar
     *                                          Si se desea, se puede utilizar alguna de las constantes ServletBase.CONTENT_TYPE_xxx
     */
    public void setTipoSalida(int tipo_salida, String content_type) {
        setTipoSalida(tipo_salida);
        setContentType(content_type);
    }
    
    
    /**
     * Obtiene el tipo de salida para la página.
     * 
     * @return                                  Tipo de salida de la página (ServletBase.TIPO_SALIDA_xxx)
     */
    public int getTipoSalida() {
        return tipo_salida;
    }



    
    /**
     * Añade contenido de tipo texto/html al cuerpo de la página.
     * 
     * @param contenido                         Contenido a añadir
     */
    protected void addContenido(String contenido) {
        this.contenido_texto_html.append(contenido);
    }

    
    /**
     * Añade contenido de tipo texto/html al cuerpo de la página.
     * 
     * @param contenido                         Contenido a añadir
     */
    protected void addContenido(StringBuilder contenido) {
        this.contenido_texto_html.append(contenido);
    }

    
    /**
     * Establece el contenido de tipo texto/html al cuerpo de la página, sustituyendo al contenido anterior, si lo hubiera.
     * 
     * @param contenido                         Contenido a añadir
     */
    protected void setContenido(String contenido) {
        contenido_texto_html = new StringBuilder(contenido);
    }

    
    /**
     * Añade contenido de tipo texto/html al cuerpo de la página, sustituyendo al contenido anterior, si lo hubiera.
     * 
     * @param contenido                         Contenido a añadir
     */
    protected void setContenido(StringBuilder contenido) {
        contenido_texto_html = new StringBuilder(contenido);
    }
    
    
    /**
     * Elimina el contenido que se hubiera añadido a la salida del servlet.
     */
    protected void clearContenido() {
        contenido_texto_html = new StringBuilder();
    }
    
    
    /**
     * Obtiene el contenido de tipo texto/html que haya sido añadido a la salida del servlet.
     * 
     * @return                                  Contenido de tipo texto/html
     */
    protected StringBuilder getContenido() {
        return contenido_texto_html;
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
        
        PrintWriter out = null;
        try {
            out = response.getWriter();
            if (contenido_texto_html != null) {
                out.print(contenido_texto_html);
            }
        } catch (IOException ioex) {
            Exception ex = new Exception(Errores.getMensaje(Errores.ERR_WEBAPP_ENVIAR_SALIDA_PAGINA), ioex);
            throw ex;
        }

        out.close();
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
            
        this.contenido_texto_html = null;
        this.sc = null;
        this.request = null;
        this.response = null;
    }
   
}