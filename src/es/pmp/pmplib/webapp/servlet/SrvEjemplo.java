/**
 * SrvEjemplo.java
 *
 * Creado el 28-ene-2016, 15:04:12
 */

package es.pmp.pmplib.webapp.servlet;

import es.pmp.pmplib.html.WebComun;
import es.pmp.pmplib.html.componentes.Head;
import es.pmp.pmplib.html.componentes.form.Form;
import es.pmp.pmplib.html.componentes.info.Info;
import es.pmp.pmplib.html.componentes.table.Table;

/**
 * Servlet para mostrar un ejemplo de uso.
 * 
 * @author pmpreciado
 */
public class SrvEjemplo extends ServletBase implements ServletInterface {

    
    /**
     * Genera la salida del servlet.
     * 
     * @throws Throwable                        Error al generar la salida
     */
    @Override
    public void generateOutput() throws Throwable {
        
        Head head = new Head();
        head.addCss("/cttb/css/pmplib_azul.css");
        super.addContenido(head.toString());

        
        super.addContenido("<h1>");
        super.addContenido("HOLA MUNDO");
        super.addContenido("</h1>");


        Table t = Table.generarTablaPruebas();
        StringBuilder s_t = t.toStringBuilder();
        addContenido(s_t);
        
        String separacion = WebComun.getSeparacion();
        addContenido(separacion);

        Info info = Info.generarInfoPruebas();
        StringBuilder s_info = info.toStringBuilder();
        addContenido(s_info);
        
        Form form = Form.generarFormPruebas();
        StringBuilder s_form = form.toStringBuilder();
        addContenido(s_form);
        
        
        /*
        try {

        } catch (Throwable th) {
            if (th instanceof ServletException) {
                throw (ServletException) th;
            }
            
            ServletException sex = new ServletException(th);
            throw sex;
        }*/
    }
    
    
}
