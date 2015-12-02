/**
 * Do.java
 *
 * Creado el 22-oct-2015, 19:19:30
 */

package es.pmp.pmplib.webapp.servlet;

import es.pmp.pmplib.Cadenas;
import es.pmp.pmplib.errores.Errores;
import java.io.IOException;
import java.lang.reflect.Constructor;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * Clase de la que pueden extender los servlets que servirán de puntos de entrada a las aplicaciones web.
 * 
 * @author pmpreciado
 */
public class Do extends HttpServlet {

    final String SERVLET_CLASS_BASE = this.getClass().getPackage().getName();
    final String SERVLET_SUFIJO = ".do";
    
    
    /**
     * Obtiene el nombre de la clase a llamar a partir de la ruta de la petición web en formato /SrvXXX.
     * 
     * @param request                           Request
     * 
     * @return                                  Nombre de la clase, por ejemplo, es.pmp.pmplib.servlets.do.SrvTest
     */
    private String getClaseFromRuta1(HttpServletRequest request) {
        String path_info = request.getPathInfo();     // Ejemplo: /SrvTest
        String ruta_relativa = path_info;
        
        if (ruta_relativa == null) {
            ruta_relativa = "";
        } else {
            ruta_relativa = Cadenas.eliminarPrimeros(ruta_relativa, 1);
            ruta_relativa = ruta_relativa.replaceAll("/", ".");
        }
        
        String nombre_clase = SERVLET_CLASS_BASE + "." + ruta_relativa;
        return nombre_clase;
    }
    
    
    /**
     * Obtiene el nombre de la clase a llamar a partir de la ruta de la petición web en formato .SrvXXX.do
     * 
     * @param request                           Request
     * @param sufijo                            Sufijo de las peticiones, por ejemplo, ".do"
     * 
     * @return                                  Nombre de la clase, por ejemplo, es.pmp.pmplib.servlets.do.SrvTest
     */
    private String getClaseFromRuta2(HttpServletRequest request, String sufijo) {
        
        String uri = request.getRequestURI();           // Ejemplo /miweb/SrvInicio.do
        int pos_primero = uri.lastIndexOf("/");
        int pos_ultimo = uri.lastIndexOf(sufijo);
        
        String ruta_relativa = Cadenas.miSubstring(uri, pos_primero + 1, pos_ultimo - 1);
        
        String nombre_clase = SERVLET_CLASS_BASE + "." + ruta_relativa;
        return nombre_clase;
    }
    
    
    
    /**
     * Obtiene la clase a invocar.
     * 
     * @param request                           Servlet request
     * 
     * @return                                  Clase a invocar
     * 
     * @throws Exception                        La página solicitada no existe
     */
    private Class getClaseAInvocar(HttpServletRequest request) throws Exception {
        // Obtenemos la clase a invocar
        String nombre_clase;
        Class clase = null;
        try {
            // Usar uno de estos dos según el patrón del servlet
            nombre_clase = getClaseFromRuta1(request);
            nombre_clase = getClaseFromRuta2(request, SERVLET_SUFIJO);
            
            clase = Class.forName(nombre_clase);
        } catch (Exception ex) {
            String mensaje = Errores.getMensaje(Errores.ERR_AP_PAGINA_NO_EXISTENTE);
            throw new Exception(mensaje, ex);
        }
        
        return clase;
    }
    
    
    
    /**
     * Ejemplo de procesamiento de las las peticiones HTTP y POST hacia este servlet.
     * Esta función es de ejemplo.
     * La clase que extienda de esta debe implementar su propia función processRequest(HttpServletRequest, HttpServletResponse).
     *
     * @param request                           Servlet request
     * @param response                          Servlet response
     * 
     * @throws ServletException                 Error específico del servlet
     * @throws IOException                      Error de entrada/salida
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ServletInterface servlet = null;
        Logger logger = LogManager.getRootLogger();
        
        try {
        
            ServletContext sc = getServletContext();
            HttpSession session = request.getSession();

            // Obtenemos la clase a invocar
            Class clase = getClaseAInvocar(request);

            // Ejecutamos la clase solicitada
            Class[] clase_args = new Class[] {};
            Object [] args = {};
        
            try {
                Constructor cons = clase.getConstructor(clase_args);
                servlet = (ServletInterface) cons.newInstance(args);
                servlet.init(sc, request, response);
            } catch (Throwable th) {
                String mensaje = Errores.getMensaje(Errores.ERR_WEBAPP_CARGAR_PAGINA);
                throw new Exception(mensaje, th);
            }
        
            servlet.preLaunch();
            servlet.generateOutput();
            servlet.output();
        
        } catch (Throwable th) {
            
            throw new ServletException(th);
            
        } finally  {
            // Esta línea va en el finally para que, aunque haya error, nos aseguremos de que se cierra el stream de salida y se devuelven las conexiones al pool
            if (servlet != null) {
                try {
                    servlet.end();
                } catch (Throwable th) {
                    logger.error(Errores.getMensaje(Errores.ERR_WEBAPP_FINALIZAR_SERVLET), th);
                }
            }
        }
    }
   

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Punto de entrada general a la aplicación web";
    }// </editor-fold>

}
