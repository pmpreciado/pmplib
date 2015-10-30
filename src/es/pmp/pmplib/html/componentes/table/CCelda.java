/**
 * CCelda.java
 *
 * Creado el 23-oct-2015, 10:17:12
 */

package es.pmp.pmplib.html.componentes.table;

import es.pmp.pmplib.Comun;
import es.pmp.pmplib.html.base.HtmlBase;
import es.pmp.pmplib.html.base.ListaAtributos;
import es.pmp.pmplib.html.base.Tag;

/**
 * Clase para definir una celda dentro de una fila de una tabla de la clase Table.
 * 
 * @author pmpreciado
 */
public final class CCelda extends ListaAtributos {

    
    /** Alineación horizontal del contenido de la celda */
    private HtmlBase.ALINEACION alineacion;
    
    /** Alineación horizontal de la columna que contiene la celda */
    //private HtmlBase.ALINEACION alineacion_columna;
    
    /** Flag que indica si se debe establecer una separación vertical a la izquierda de la celda */
    //boolean separacion_vertical_izq;
    
    /** Flag que indica si se debe establecer una separación vertical a la derecha de la celda */
    //boolean separacion_vertical_der;

    /** Color a usar para la separación vertical */
    //private String color_separacion_vertical;
    
    /** Número de celdas sobre las que se expande esta (1 = Sólo esta celda) */
    private int expandir;
    
    /** Contenido */
    private String contenido;

    
    
    
    
    
    /**
     * Crea una instancia de la clase.
     */
    public void CCelda() {
    }
    
    
    /**
     * Crea la instancia de la clase.
     * 
     * @param contenido                         Contenido de la celda
     */
    public CCelda(String contenido) {
        setContenido(contenido);
    }
    
    
    /** 
     * Crea la instancia de la clase.
     * 
     * @param contenido                         Contenido de la celda
     */
    public CCelda(int contenido) {
        setContenido(contenido);
    }

    
    /** 
     * Crea la instancia de la clase.
     * 
     * @param contenido                         Contenido de la celda
     */
    public CCelda(long contenido) {
        setContenido(contenido);
    }

    
    /**
     * Establece el contenido de la celda.
     *
     * @param contenido                         Contenido de la celda
     */
    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
    
    
    /**
     * Establece el contenido de la celda.
     *
     * @param contenido                         Contenido de la celda
     */
    public void setContenido(int contenido) {
        this.contenido = Integer.toString(contenido);
    }
    
    
    /**
     * Establece el contenido de la celda.
     *
     * @param contenido                         Contenido de la celda
     */
    public void setContenido(long contenido) {
        this.contenido = Long.toString(contenido);
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
    
    
//    /**
//     * Indica si se debe establecer una separación vertical a la izquierda de la celda.
//     * 
//     * @param separacion_vertical_izq           'true' para establecer una separación vertical a la izquierda de la celda
//     *                                          'false' para no hacerlo
//     */
//    public void setSeparacionVerticalIzq(boolean separacion_vertical_izq) {
//        this.separacion_vertical_izq = separacion_vertical_izq;
//    }
//    
//    
//    /**
//     * Indica si se debe establecer una separación vertical a la derecha de la celda.
//     * 
//     * @param separacion_vertical_der           'true' para establecer una separación vertical a la derecha de la celda
//     *                                          'false' para no hacerlo
//     */
//    public void setSeparacionVerticalDer(boolean separacion_vertical_der) {
//        this.separacion_vertical_der = separacion_vertical_der;
//    }
//    
//
//    /**
//     * Establece el color a usar para la separación vertical.
//     * 
//     * @param color_separacion_vertical         Color a usar para la separación vertical
//     */
//    public void setColorSeparacionVertical(String color_separacion_vertical) {
//        this.color_separacion_vertical = color_separacion_vertical;
//    }
//    
//            
//    /**
//     * Obtiene el color a usar para la separación vertical.
//     * 
//     * @return                                  Color a usar para la separación vertical
//     */
//    public String getColorSeparacionVertical() {
//        return color_separacion_vertical;
//    }
    
    
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
     *                                          Será un número >= 1
     */
    public int getExpandir() {
        if (expandir == 0) {
            return 1;
        }
        return expandir;
    }
    

//    /**
//     * Obtiene la alineación efectiva de la celda.
//     * Si la propia celda tiene establecida alineación, será esta. Si no, se usará la alineación de la columna.
//     * 
//     * @param alineacion_celda                  Alineación de la celda
//     * @param alineacion_columna                Alineación de la columna
//     * 
//     * @return                                  Alineación efectiva
//     */
//    private HtmlBase.ALINEACION getAlineacionEfectiva(HtmlBase.ALINEACION alineacion_celda, HtmlBase.ALINEACION alineacion_columna) {
//        HtmlBase.ALINEACION alineacion_efectiva = null;
//        
//        if (alineacion_celda != null) {
//            alineacion_efectiva = alineacion_celda;
//        } else if (alineacion_columna != null) {
//            alineacion_efectiva = alineacion_columna;
//        }
//        
//        return alineacion_efectiva;
//    }
    
    
//    /**
//     * Obtiene el style a aplicar a la etiqueta TD.
//     * El style lo obtiene combinando el style asignado de forma manual con el style que manualmente se debe establecer.
//     * En caso de conflicto, tiene preferencia el style asignado de forma manual.
//     * 
//     * @return                                  Valor para la etiqueta style
//     */
//    private Style getStyle() {
//        
//        Style style = new Style();
//        
//        // Style preestablecido para este TD
//        CAtributo atr_style = this.getAtributo("style");
//        if (atr_style != null) {
//            style.setStyle(atr_style.getValorAtributo());
//        }
//        
//        // Styles a añadir automáticamente
//        
//            // Alineación horizontal
//            HtmlBase.ALINEACION alineacion_efectiva = getAlineacionEfectiva(alineacion, alineacion_columna);
//            if (alineacion_efectiva != null) {
//
//                // En caso de conflicto, preservamos la alineación manual
//                if (!style.existeAtributo("text-align")) {
//                    style.addAtributo("text-align", alineacion_efectiva.toString());
//                }
//            }
//            
//            // Color borde izquierdo (separación vertical)
//            if (separacion_vertical_izq && !Cadenas.vacio(color_separacion_vertical)) {
//                if (!style.existeAtributo("border-left-width")) {
//                    style.addAtributo("border-left-width", "1px");
//                }
//                if (!style.existeAtributo("border-left-color")) {
//                    style.addAtributo("border-left-color", color_separacion_vertical);
//                }
//            }
//
//            // Color borde derecho (separación vertical)
//            if (separacion_vertical_izq && !Cadenas.vacio(color_separacion_vertical)) {
//                if (!style.existeAtributo("border-left-width")) {
//                    style.addAtributo("border-left-width", "1px");
//                }
//                if (!style.existeAtributo("border-left-color")) {
//                    style.addAtributo("border-left-color", color_separacion_vertical);
//                }
//            }
//
//        return style;
//    }

    
    /**
     * Retorna la clase que hay que utilizar para una celda, dependiendo del tipo de fila en la que se encuentre.
     * 
     * @param tipo_fila                         Tipo de fila en la que se encuentra la celda (CFila.TF_xxx)
     * 
     * @return                                  Clase a utilizar
     */
    private static String getClaseCelda(int tipo_fila, EstiloTabla estilo_tabla) {
        String clase = null;
        
        switch (tipo_fila) {
            case CFila.TF_TITULO:
                clase = estilo_tabla.clase_td_titulo;
                break;
                
            case CFila.TF_SUBTITULO:
                clase = estilo_tabla.clase_td_subtitulo;
                break;
                
            case CFila.TF_CABECERA:
                clase = estilo_tabla.clase_td_cabecera;
                break;

            case CFila.TF_SECCION:
                clase = estilo_tabla.clase_td_seccion;
                break;
            
            case CFila.TF_DATOS:
            case CFila.TF_HTML:
                clase = estilo_tabla.clase_td_datos;
                break;

            case CFila.TF_TOTAL:
                clase = estilo_tabla.clase_td_total;
                break;
        }
        return clase;
    }
    
    
    
    /**
     * Genera el código HTML que muestra la celda, incluyendo las etiquetas TD.
     * 
     * @param table                             Tabla que contiene la celda
     * @param tipo_fila                         Tipo de fila en la que se encuentra la celda (CFila.TF_xxx)
     * @param lista_atributos_heredados         Lista de atributos heredados del elemento padre
     *                                          En caso de colisión, tienen preferencia los de la propia celda
     *                                          Si es 'null', se ignora
     * 
     * @return                                  Código HTML generado
     */
    public StringBuilder getHtml(Table table, int tipo_fila, ListaAtributos lista_atributos_heredados) {
        
        Tag tag_td = new Tag("td");
        String clase_td = getClaseCelda(tipo_fila, table.estilo) ;
        tag_td.setAtributoClass(clase_td);
        
        ListaAtributos lista_atributos_generado = new ListaAtributos();
        if (alineacion != null) {
            lista_atributos_generado.addAtributo("style", "text-align: " + alineacion.toString());
        }
        
        ListaAtributos lista_atributos_combinada = ListaAtributos.combinar(this, lista_atributos_generado, lista_atributos_heredados);
        tag_td.addAtributosReplace(lista_atributos_combinada);

        if (expandir > 1) {
            tag_td.setAtributo("colspan", expandir);
        }
        
        tag_td.setContenido(this.contenido);

        StringBuilder html = tag_td.toStringBuilder();
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
