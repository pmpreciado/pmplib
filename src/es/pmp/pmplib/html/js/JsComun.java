/**
 * JsComun.java
 *
 * Creado el 22-oct-2015, 18:52:37
 */

package es.pmp.pmplib.html.js;


/**
 * Generación de código HTML para invocar funciones JavaScript genéricas.
 *
 * @author pmpreciado
 */
public class JsComun {


    /** Código JavaScript para volver a la página anterior */
    public static final String JS_ANTERIOR  = "javascript:history.go(-1)";


    /**
     * Envuelve un código JavaScript dado entre las etiquetas <script>...</script>.
     *
     * @param js                        Código JS original
     * @return                          Código JS entre las etiquetas <script>...</script>
     */
    public static String scriptMe(String js) {
        String s = "<script language=\"JavaScript\">\n" +
                       js + "\n" +
                   "</script>\n";
        return s;
    }


    /**
     * Envuelve un código JavaScript dado entre las etiquetas <script>...</script>.
     *
     * @param js                        Código JS a envolver
     */
    public static void scriptMe(StringBuilder js) {
        js.insert(0, "<script language=\"JavaScript\">\n");
            js.append("\n");
        js.append("</script>\n");
    }


    /**
     * Añade el identificador "javascript:" antes de una llamada a una función JavaScript.
     *
     * @param js                        Código JS original
     * @return                          Código JS al que se le ha antepuesto "javascript:"
     */
    public static String javascriptMe(String js) {
        String s = "javascript:" + js;
        return s;
    }

    
    /**
     * Obtiene el código HTML/JavaScript para saltar a otra página.
     * El salto creará una nueva entrada en la historia.
     *
     * @param nueva_url                 Nueva dirección
     *
     * @return                          Código JS generado
     */
    public static String ir(String nueva_url) {

        String s = "window.location='" + nueva_url + "';\n";
        return s;
    }


    /**
     * Obtiene el código HTML/JavaScript para redireccionar la página actual.
     * El salto no creará una nueva entrada en la historia.
     *
     * @param nueva_url                 Nueva dirección
     *
     * @return                          Código JS generado
     */
    public static String redireccionar(String nueva_url) {

        String s = "window.location.replace('" + nueva_url + "');\n";
        return s;
    }


    /**
     * Obtiene el código HTML/JavaScript para saltar a otra página desde el elemento "top" de la página actual.
     * El salto no creará una nueva entrada en la historia.
     *
     * @param nueva_url                 Nueva dirección
     *
     * @return                          Código JS generado
     */
    public static String irTop(String nueva_url) {

        String s = "top.location=\"" + nueva_url + "\";\n";
        return s;
    }


    /**
     * Obtiene el código HTML/JavaScript para redireccionar el elemento "top" de la página actual.
     * El salto no creará una nueva entrada en la historia.
     *
     * @param nueva_url                 Nueva dirección
     *
     * @return                          Código JS generado
     */
    public static String redireccionarTop(String nueva_url) {

        String s = "top.location.replace('" + nueva_url + "');\n";
        return s;
    }


    /**
     * Obtiene el código JavaScript para cerrar la ventana actual.
     *
     * @return                          Código JS generado, sin incluir las etiquetas <script>
     */
    public static String windowClose() {

        String js = "window.close();";
        return js;
    }

    
    
    /**
     * Esta función hace uso de la función JavaScript: setFocus(String)
     * 
     * Obtiene la llamada a la función JavaScript para establecer el foco (cursor) en un campo dado.
     *
     * @param id_fld                    Id. del campo que recibirá el foco
     *
     * @return                          Código JS generado, sin incluir las etiquetas <script>
     */
    public static String setFocus(String id_fld) {
        String js = "setFocus('" + id_fld + "');";
        return js;
    }
}
