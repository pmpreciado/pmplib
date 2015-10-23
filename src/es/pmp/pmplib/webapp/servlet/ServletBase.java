/**
 * ServletBase.java
 *
 * Creado el 22-oct-2015, 18:42:09
 */

package es.pmp.pmplib.webapp.servlet;

import es.pmp.pmplib.Comun;
import es.pmp.pmplib.html.componentes.Href;
import es.pmp.pmplib.html.js.JsComun;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Estructura base para un servlet encargado de generar una página HTML.
 *
 * @author Pedro María Preciado
 */
public abstract class ServletBase implements ServletInterface {

    public static final String DOCTYPE = "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 Transitional//EN\"\n" +
                                   "\"http://www.w3.org/TR/html4/loose.dtd\">\n";

    /** Tipos de contenido */
    protected static final String CONTENT_TYPE_UTF_8            = "text/html;charset=UTF-8";
    protected static final String CONTENT_TYPE_ISO_8859_1       = "text/html;charset=ISO-8859-1";
    protected static final String CONTENT_TYPE_ISO_8859_15      = "text/html;charset=ISO-8859-15";

    protected static final String CONTENT_TYPE_PREDETERMINADO   = CONTENT_TYPE_UTF_8;

    /** Tipos de salida de la página */
    
        /** Tipo de salida HTML. Las etiquetas Doctype, html, head y body se generarán automáticamente */
        protected static final int TIPO_SALIDA_HTML    = 1;
        
        /** Tipo de salida plana. No se genera nada automáticamente */
        protected static final int TIPO_SALIDA_PLANA   = 2;
    
    
    
    protected ServletContext sc;
    protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected HttpSession session;
    private PrintWriter out;

    
    /** Contenido de la página */
    private StringBuilder contenido;

    /** Tipo de salida de la página (TIPO_SALIDA_xxx) */
    private int tipo_salida;
    
    /** Identificador del campo que recibirá el foco al cargar la página */
    private String id_fld_focus;

    
    
    /** 
     * Crea la instancia de la clase.
     */
    public ServletBase() {
        contenido = new StringBuilder();
        tipo_salida = TIPO_SALIDA_HTML;
    }
    
    
    /**
     * Obtiene el "content type" a utilizar para el envío de la página generada.
     * Esta función retorna siempre el CONTENT_TYPE_PREDETERMINADO.
     * Si se desea utilizar otro "content type", hay que sobrecargar esta función para que retorne el deseado.
     *
     * @return                              "Content type" a utilizar (CONTENT_TYPE_xxx)
     *                                      Si es 'null', no se utilizará ninguno
     */
    @Override
    public String getContentType() {
        return CONTENT_TYPE_PREDETERMINADO;
    }

    
    /**
     * Establece el tipo de salida para la página.
     * 
     * @param tipo_salida                       Tipo de salida de la página (TIPO_SALIDA_xxx)
     */
    public void setTipoSalida(int tipo_salida) {
        this.tipo_salida = tipo_salida;
    }
    
    
    /**
     * Obtiene el tipo de salida para la página.
     * 
     * @return                                  Tipo de salida de la página (TIPO_SALIDA_xxx)
     */
    public int getTipoSalida() {
        return tipo_salida;
    }

    
    /**
     * Establece el identificador del campo que recibirá automáticamente el "focus" al cargar esta página.
     *
     * @param id_fld_focus                  Identificador del campo que recibirá automáticamente el "focus"
     */
    protected void setFocus(String id_fld_focus) {
        this.id_fld_focus = id_fld_focus;
    }


    /**
     * Obtiene el identificador del campo que recibirá automáticamente el "focus" al cargar esta página.
     *
     * @return                              Identificador del campo que recibirá automáticamente el "focus"
     */
    protected String getFocus() {
        return this.id_fld_focus;
    }
    
    
    /**
     * Añade una llamada JavaScript al documento actual para redireccionar la página.
     *
     * @param url                           Nueva dirección
     */
    protected void redirect(String url) {
        Href href = new Href(url);
        redirect(href);
    }


    /**
     * Añade una llamada JavaScript al documento actual para redireccionar la página.
     *
     * @param href                          Nueva dirección
     */
    protected void redirect(Href href) {
        String js_redirect = JsComun.redireccionar(href.toString());
        js_redirect = JsComun.scriptMe(js_redirect);
        addContenido(js_redirect);
    }
    
    
    /** 
     * Inicializa la clase.
     * Almacena localmente los objetos request y response.
     * Establece la conexión con la base de datos.
     * 
     * @param sc                            Contexto del Servlet
     * @param request                       Request
     * @param response                      Response
     * @throws Throwable                    No se puede obtener el stream de salida
     */
    @Override
    public void init(ServletContext sc, HttpServletRequest request, HttpServletResponse response) throws Throwable {

        this.sc = sc;
        this.session = request.getSession();
        this.request = request;
        this.response = response;
        try {
            String ct = getContentType();
            if (ct != null) {
                response.setContentType(ct);
            }
            out = response.getWriter();
        } catch (IOException ex) {
            throw new ServletException(ex);
        }

        

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
//        try {
//
//        } catch (Exception ex) {
//            ServletException sex = new ServletException(ex);
//            throw sex;
//        }
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
     * Obtiene el contenido HTML de la página web, y lo vuelca a la salida HTTP.
     * Tras volcar el contenido, cierra el "stream" de salida.
     */
    @Override
    public void output() {

        if (out == null) {
            return;
        }

        // Obtenemos el código HTML
        String html = this.toString();
        out.print(html);

        if (out != null) {
            out.close();
        }
    }
    
    
    /**
     * Finaliza la ejecución del servlet, devolviendo al pool las conexiones a la base de datos.
     * Es obligatorio llamar este método al finalizar el servlet, por lo que es una buena idea hacer la llamada dentro del bloque "finally" de un try-catch.
     * 
     * @throws ServletException             Error al cerrar la generar la salida
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
   

    
    /**
     * Añade contenido al cuerpo de la página.
     * 
     * @param obj                               Componente a añadir
     */
    protected void addContenido(Object obj) {
        String html = obj.toString();
        contenido.append(html);
    }
    
    
    /**
     * Establece el contenido del cuerpo de la página, sustituyendo el contenido anterior, si lo hubiera.
     * 
     * @param obj                               Componente a añadir
     */
    protected void setContenido(Object obj) {
        String html = obj.toString();
        contenido = new StringBuilder(html);
    }
    
    /**
     * Elimina el contenido que se hubiera añadido al cuerpo de la página.
     */
    protected void clearContenido() {
        contenido = new StringBuilder();
    }
    
    
    /**
     * Obtiene el contenido del cuerpo de la página.
     * 
     * @return                              Código HTML
     */
    protected StringBuilder getContenido() {
        return contenido;
    }
    
    
    /**
     * Retorna el contenido de la página web sin aplicar ningún estilo.
     * 
     * @return                                  Contenido estilo CajalnetLog
     */
    private StringBuilder getContenidoEstiloNormal() {
        
        StringBuilder s = new StringBuilder();
        s.append(contenido);
        return s;
    }        
    
    
    /**
     * Genera el código HTML que invoca las funciones necesarias al cargar el body de la página.
     *
     * @return                                  Código HTML generado
     */
    private String getBodyOnload() {
        String onload = "";
        if (id_fld_focus != null) {
            String js = JsComun.setFocus(id_fld_focus);
            onload = " onload=\"" + js + "\"";
        }
        
        return onload;
    }
    

    /**
     * Genera el código HTML de la página.
     * 
     * @return                              Código HTML de la página
     */
    @Override
    public String toString() {

        StringBuilder s = new StringBuilder();

        if (tipo_salida == TIPO_SALIDA_PLANA) {
            s.append(contenido);
        } else {

            s.append(DOCTYPE);

            s.append("<html>\n");

                // Body
                String body_onload = getBodyOnload();
                s.append("<body" + body_onload + ">" + Comun.NL);
                    StringBuilder contenido_estilo_normal = getContenidoEstiloNormal();
                    s.append(contenido_estilo_normal);
                s.append("</body>\n");

            s.append("</html>\n");
        }
        
        return s.toString();
    }

}
