/**
 * CCelda.java
 *
 * Creado el 23-oct-2015, 10:17:12
 */

package es.pmp.pmplib.html.componentes.table;

import es.pmp.pmplib.Cadenas;
import es.pmp.pmplib.Comun;
import es.pmp.pmplib.html.base.HtmlBase;
import es.pmp.pmplib.html.base.ListaAtributos;
import es.pmp.pmplib.html.base.Style;
import es.pmp.pmplib.html.base.Tag;
import java.util.List;

/**
 * Clase para definir una celda dentro de una fila de una tabla de la clase Table.
 * 
 * @author pmpreciado
 */
public class CCelda extends ListaAtributos {

    
    /** Clase del elemento TD */
    private String clase_td;

    /** Alineación horizontal del contenido de la celda */
    private HtmlBase.ALINEACION alineacion;
    
    /** Alineación horizontal de la columna que contiene la celda */
    private HtmlBase.ALINEACION alineacion_columna;
    
    /** Flag que indica si se debe establecer una separación vertical a la izquierda de la celda */
    boolean separacion_vertical_izq;
    
    /** Flag que indica si se debe establecer una separación vertical a la derecha de la celda */
    boolean separacion_vertical_der;

    /** Color a usar para la separación vertical */
    private String color_separacion_vertical;
    
    /** Número de celdas sobre las que se expande esta (1 = Sólo esta celda) */
    private int expandir;

    
    /** Contenido */
    private String contenido;

    
    public void CCelda() {
    }
    
    
    
    /**
     * Establece la clase del elemento TD.
     *
     * @param clase_td                          Clase del elemento TD
     */
    public void setClase(String clase_td) {
        this.clase_td = clase_td;
    }
    
    
    /**
     * Obtiene la clase del elemento TD.
     *
     * @return                                  Clase del elemento TD
     */
    public String getClase() {
        return clase_td;
    }
    
    
    /**
     * Establece la alineación horizontal del contenido de la celda.
     *
     * @param alineacion                        Alineación horizontal del contenido de la celda
     */
    public void setAlineacion(HtmlBase.ALINEACION alineacion) {
        this.alineacion = alineacion;
    }


    /**
     * Obtiene la alineación horizontal del contenido de la celda.
     *
     * @return                                  Alineación horizontal del contenido de la celda
     */
    public HtmlBase.ALINEACION getAlineacion() {
        return alineacion;
    }
    
    
    /**
     * Establece la alineación horizontal de la columna que contiene la celda.
     *
     * @param alineacion                        Alineación horizontal de la columna que contiene la celda
     */
    public void setAlineacionColumna(HtmlBase.ALINEACION alineacion) {
        this.alineacion = alineacion;
    }


    /**
     * Obtiene la alineación horizontal de la columna que contiene la celda.
     *
     * @return                                  Alineación horizontal de la columna que contiene la celda
     */
    public HtmlBase.ALINEACION getAlineacionColumna() {
        return alineacion;
    }
    
    
    /**
     * Indica si se debe establecer una separación vertical a la izquierda de la celda.
     * 
     * @param separacion_vertical_izq           'true' para establecer una separación vertical a la izquierda de la celda
     *                                          'false' para no hacerlo
     */
    public void setSeparacionVerticalIzq(boolean separacion_vertical_izq) {
        this.separacion_vertical_izq = separacion_vertical_izq;
    }
    
    
    /**
     * Indica si se debe establecer una separación vertical a la derecha de la celda.
     * 
     * @param separacion_vertical_der           'true' para establecer una separación vertical a la derecha de la celda
     *                                          'false' para no hacerlo
     */
    public void setSeparacionVerticalDer(boolean separacion_vertical_der) {
        this.separacion_vertical_der = separacion_vertical_der;
    }
    

    /**
     * Establece el color a usar para la separación vertical.
     * 
     * @param color_separacion_vertical         Color a usar para la separación vertical
     */
    public void setColorSeparacionVertical(String color_separacion_vertical) {
        this.color_separacion_vertical = color_separacion_vertical;
    }
    
            
    /**
     * Obtiene el color a usar para la separación vertical.
     * 
     * @return                                  Color a usar para la separación vertical
     */
    public String getColorSeparacionVertical() {
        return color_separacion_vertical;
    }
    
    
    /**
     * Establece el número de columnas que se expande esta celda.
     * Al expandirse, la celda cubre a las celdas a continuación.
     * 
     * @param expandir                          Número de columnas que se expande esta celda
     */
    public void setExpandir(int expandir) {
        this.expandir = expandir;
    }

           
    /**
     * Obtiene el número de columnas que se expande esta celda.
     * 
     * @return                                  Número de columnas que se expande esta celda
     */
    public int getExpandir() {
        if (expandir == 0) {
            return 1;
        }
        return expandir;
    }
    
    
    /**
     * Establece el contenido de la celda.
     *
     * @param contenido                         Contenido de la celda
     */
    public void setContenido(String contenido) {
        if (contenido == null) {
            contenido = "";
        }
        this.contenido = contenido;
    }


    /**
     * Obtiene el contenido de la celda.
     *
     * @return                                  Contenido de la celda
     */
    public String getContenido() {
        return contenido;
    }
    

    /**
     * Obtiene la alineación efectiva de la celda.
     * Si la propia celda tiene establecida alineación, será esta. Si no, se usará la alineación de la columna.
     * 
     * @param alineacion_celda                  Alineación de la celda
     * @param alineacion_columna                Alineación de la columna
     * 
     * @return                                  Alineación efectiva
     */
    private HtmlBase.ALINEACION getAlineacionEfectiva(HtmlBase.ALINEACION alineacion_celda, HtmlBase.ALINEACION alineacion_columna) {
        HtmlBase.ALINEACION alineacion_efectiva = null;
        
        if (alineacion_celda != null) {
            alineacion_efectiva = alineacion_celda;
        } else if (alineacion_columna != null) {
            alineacion_efectiva = alineacion_columna;
        }
        
        return alineacion_efectiva;
    }
    
    
    /**
     * Obtiene el style a aplicar a la etiqueta TD.
     * El style lo obtiene combinando el style asignado de forma manual con el style que manualmente se debe establecer.
     * En caso de conflicto, tiene preferencia el style asignado de forma manual.
     * 
     * @return                                  Valor para la etiqueta style
     */
    private Style getStyle() {
        
        Style style = new Style();
        
        // Style preestablecido para este TD
        CAtributo atr_style = this.get("style");
        if (atr_style != null) {
            style.setStyle(atr_style.getValor());
        }
        
        // Styles a añadir automáticamente
        
            // Alineación horizontal
            HtmlBase.ALINEACION alineacion_efectiva = getAlineacionEfectiva(alineacion, alineacion_columna);
            if (alineacion_efectiva != null) {

                // En caso de conflicto, preservamos la alineación manual
                if (!style.existe("text-align")) {
                    style.add("text-align", alineacion_efectiva.toString());
                }
            }
            
            // Color borde izquierdo (separación vertical)
            if (separacion_vertical_izq && !Cadenas.vacio(color_separacion_vertical)) {
                if (!style.existe("border-left-width")) {
                    style.add("border-left-width", "1px");
                }
                if (!style.existe("border-left-color")) {
                    style.add("border-left-color", color_separacion_vertical);
                }
            }

            // Color borde derecho (separación vertical)
            if (separacion_vertical_izq && !Cadenas.vacio(color_separacion_vertical)) {
                if (!style.existe("border-left-width")) {
                    style.add("border-left-width", "1px");
                }
                if (!style.existe("border-left-color")) {
                    style.add("border-left-color", color_separacion_vertical);
                }
            }

        return style;
    }

    
    
    

    

    /**
     * Genera el código HTML que muestra la celda, incluyendo las etiquetas TD.
     * 
     * @return                              Código HTML generado
     */
    public String getHtml() {
        
        Tag tag_td = new Tag("td");
        
        List <CAtributo> l_atributos = super.getListaAtributos();
        tag_td.setAll(l_atributos);
        
        if (!Cadenas.vacio(clase_td)) {
            tag_td.set("class", clase_td);
        }
        
        Style style = getStyle();
        String cadena_style = style.getCadenaStyle();
        tag_td.set("style", cadena_style);

        if (expandir > 1) {
            tag_td.set("colspan", expandir);
        }
        
        tag_td.setContenido(this.contenido);

        String html = tag_td.getBloque();
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
        
        s.append("alineacion: " + alineacion + Comun.NL);
        s.append("expandir: " + expandir + Comun.NL);
        s.append("contenido:" + contenido + Comun.NL);

        return s.toString();
    }

}
