/**
 * CFila.java
 *
 * Creado el 23-oct-2015, 10:17:08
 */

package es.pmp.pmplib.html.componentes.table;

import es.pmp.pmplib.Arrays;
import es.pmp.pmplib.Comun;
import es.pmp.pmplib.html.base.ListaAtributos;
import es.pmp.pmplib.html.base.Tag;
import java.util.List;

/**
 * Clase para definir una fila de una tabla de la clase Table.
 * 
 * @author pmpreciado
 */
public class CFila extends ListaAtributos {

    /** Tipos de fila */
    public static final int TF_DATOS             = 1;
    public static final int TF_TITULO            = 2;
    public static final int TF_SUBTITULO         = 3;
    public static final int TF_CABECERA          = 4;
    public static final int TF_SECCION           = 5;
    public static final int TF_SEPARACION        = 6;
    public static final int TF_HTML              = 7;
    public static final int TF_TOTAL             = 8;

    
    /** Celdas de la fila */
    List <CCelda> l_celdas;
    
    
    /**
     * Genera el código HTML que muestra la fila, incluyendo las etiquetas TR.
     * 
     * @return                              Código HTML generado
     */
    public String getHtml() {
        
        Tag tag_tr = new Tag("tr");
        
        List <CAtributo> l_atributos = super.getListaAtributos();
        tag_tr.setAll(l_atributos);
        
        StringBuilder contenido_tr = new StringBuilder();
        
        for (int i = 0; i < Arrays.size(l_celdas); i++) {
            CCelda celda = l_celdas.get(i);
            String html_celda = celda.getHtml();
            contenido_tr.append(html_celda);
        }
        
        tag_tr.setContenido(contenido_tr);
        
        String html = tag_tr.getBloque();
        return html;
    }
    
    
    /**
     * Obtiene una cadena con la información de la instancia.
     * 
     * @return                                  Cadena de texto generada
     */
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("");
        
        s.append("Número de celdas: " + Arrays.size(l_celdas) + Comun.NL);

        return s.toString();
    }
    
}
