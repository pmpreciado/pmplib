/**
 * Info.java
 *
 * Creado el 29-oct-2015, 14:43:20
 */

package es.pmp.pmplib.html.componentes.info;

import es.pmp.pmplib.Cadenas;
import es.pmp.pmplib.html.base.Style;
import es.pmp.pmplib.html.componentes.table.CCelda;
import es.pmp.pmplib.html.componentes.table.CFila;
import es.pmp.pmplib.html.componentes.table.Table;
import java.util.ArrayList;
import java.util.List;

/**
 * Elementos HTML para la generación de tablas con información.
 * Las tablas con información son instancias de la clase Table adaptadas.
 * 
 * @author pmpreciado
 */
public class Info {

    
    /**
     * Número de columnas predeterminado.
     * En cada columna se muestra una etiqueta, un valor y, opcionalmente, una explicación.
     */
    private static final int NUMERO_COLUMNAS_ELEMENTOS_PREDETERMINADO = 1;
    
    
    /** Estilo del info */
    EstiloInfo estilo;

    
    /**
     * Número de columnas de elementos.
     * Cada columna de elementos contiene 3 columnas de tabla, a saber:
     *    1: Etiqueta
     *    2: Valor (en elementos de tipo dato, también en títulos a la derecha)
     *    3: Explicación (opcional en columnas de datos)
     */
    int numero_columnas_elementos;
    
    
    /** Elementos que componen el Info */
    List <CElementoInfo> l_elementos;

    
    /**
     * Crea una instancia de la clase.
     */
    public Info() {
        numero_columnas_elementos = NUMERO_COLUMNAS_ELEMENTOS_PREDETERMINADO;
        l_elementos = new ArrayList();
        estilo = EstiloInfo.getEstiloInfoAzul();
        
    }
    
    
    /**
     * Crea una instancia de la clase.
     * Establece un título para el info.
     * 
     * @param titulo                            Texto del título a añadir
     */
    public Info(String titulo) {
        this();
        addTitulo(titulo);
    }
    
    
    /**
     * Establece el estilo del info.
     *
     * @param estilo                            Objeto de clase EstiloInfo con el estilo a utilizar
     */
    public void setEstilo(EstiloInfo estilo) {
        this.estilo = estilo;
    }
    
    
    /**
     * Establece el número de columnas.
     * Cada columna de elementos contiene 3 columnas de tabla, a saber:
     *    1: Etiqueta
     *    2: Valor (en elementos de tipo dato, también en títulos a la derecha)
     *    3: Explicación (opcional en columnas de datos)
     *
     * @param numero_columnas_elementos         Número de columnas
     */
    public void setNumeroColumnasElementos(int numero_columnas_elementos) {
        this.numero_columnas_elementos = numero_columnas_elementos;
    }
    
    
    /**
     * Añade un elemento al info.
     *
     * @param elemento                          Elemento a añadir
     */
    public void addElemento(CElementoInfo elemento) {
        
        l_elementos.add(elemento);
    }
    
    
    /**
     * Añade un título al info.
     *
     * @param titulo                            Texto del título a añadir
     */
    public void addTitulo(String titulo) {
        
        CElementoInfo ei = new CElementoInfo(CElementoInfo.TE_TITULO);
        ei.setEtiqueta(titulo);
        addElemento(ei);
    }


    /**
     * Añade un título al info. El título también contiene un texto a la derecha.
     *
     * @param titulo                            Texto del título a añadir
     * @param texto_derecha                     Texto que acompañará al título, alineado a la derecha
     */
    public void addTitulo(String titulo, String texto_derecha) {
        
        CElementoInfo ei = new CElementoInfo(CElementoInfo.TE_TITULO);
        ei.setEtiqueta(titulo);
        ei.setValor(texto_derecha);
        addElemento(ei);
    }
    
    
    /**
     * Añade un subtítulo al info.
     *
     * @param subtitulo                         Texto del subtítulo a añadir
     */
    public void addSubtitulo(String subtitulo) {
        
        CElementoInfo ei = new CElementoInfo(CElementoInfo.TE_SUBTITULO);
        ei.setEtiqueta(subtitulo);
        addElemento(ei);
    }

    
    /**
     * Añade una sección al info.
     *
     * @param titulo                            Título de la sección a añadir
     */
    public void addSeccion(String titulo) {
        
        CElementoInfo ei = new CElementoInfo(CElementoInfo.TE_SECCION);
        ei.setEtiqueta(titulo);
        addElemento(ei);
    }


    /**
     * Añade una sección al info. La sección también contiene un texto a la derecha.
     *
     * @param titulo                            Título de la sección a añadir
     * @param texto_derecha                     Texto que acompañará al título, alineado a la derecha
     */
    public void addSeccion(String titulo, String texto_derecha) {
        
        CElementoInfo ei = new CElementoInfo(CElementoInfo.TE_SECCION);
        ei.setEtiqueta(titulo);
        ei.setValor(texto_derecha);
        addElemento(ei);
    }    
    
    
    /**
     * Añade una separación al info.
     */
    public void addSeparacion() {
        
        CElementoInfo ei = new CElementoInfo(CElementoInfo.TE_SEPARACION);
        addElemento(ei);
    }
    

    /**
     * Añade un elemento de tipo HTML al info.
     * El elemento ocupa el espacio de la etiqueta, el valor y la explicación (si la hubiera).
     * Se puede usar la función expandirUltimo() para hacer que se expanda al resto de la fila.
     * 
     * @param html                              Contenido HTML a añadir al info
     */
    public void addHtml(String html) {
        
        CElementoInfo ei = new CElementoInfo(CElementoInfo.TE_HTML);
        ei.setEtiqueta(html);
        
        addElemento(ei);
    }
    
    
    /**
     * Añade un campo al info.
     * Permite incluir una explicación sobre el campo.
     *
     * @param etiqueta                          Etiqueta
     * @param valor                             Valor
     * @param explicacion                       Explicación
     */
    public void add(String etiqueta, String valor, String explicacion) {
        
        CElementoInfo ei = new CElementoInfo(CElementoInfo.TE_DATO);
        ei.setEtiqueta(etiqueta);
        ei.setValor(valor);
        ei.setExplicacion(explicacion);
        
        addElemento(ei);
    }
    
    
    /**
     * Añade un campo al info.
     * Permite incluir una explicación sobre el campo.
     *
     * @param etiqueta                          Etiqueta
     * @param valor                             Valor
     */
    public void add(String etiqueta, String valor) {
        
        CElementoInfo ei = new CElementoInfo(CElementoInfo.TE_DATO);
        ei.setEtiqueta(etiqueta);
        ei.setValor(valor);
        
        addElemento(ei);
    }
    
    
    /**
     * Añade un campo al info.
     * El campo sólo consta de una etiqueta que se expande al espacio del valor.
     *
     * @param etiqueta                          Etiqueta
     */
    public void add(String etiqueta) {
        
        CElementoInfo ei = new CElementoInfo(CElementoInfo.TE_DATO);
        ei.setEtiqueta(etiqueta);
        
        addElemento(ei);
    }
    
        
    /**
     * Añade una etiqueta al info.
     * La etiqueta se expande al espacio del valor.
     * Esta función es un alias de add(String)
     *
     * @param etiqueta                          Etiqueta
     */
    public void addEtiqueta(String etiqueta) {
        add(etiqueta);
    }
    
    
    /**
     * Borra todos los elementos del info.
     */
    public void clear() {
        l_elementos.clear();
    }
    
    
    /**
     * Obtiene el último elemento añadido al info.
     * 
     * @return                                  Último elemento añadido al info
     *                                          'null' si no hay ninguno
     */
    public CElementoInfo getUltimoElemento() {
        if (l_elementos.isEmpty()) {
            return null;
        }
        
        CElementoInfo ei = l_elementos.get(l_elementos.size() - 1);
        return ei;
    }
    
    
    /**
     * Hace que el valor del último campo añadido se expanda hasta el final de la fila.
     */
    public void expandirUltimoHastaFinal() {
        CElementoInfo ei = getUltimoElemento();
        if (ei != null) {
            ei.setExpandirHastaFinal(true);
        }
    }
    
    
    /**
     * Establece el estilo del elemento TD que contrendrá la etiqueta del último elemento añadido al info.
     * Estos se aplica a las etiquetas de los elementos de tipo CElementoInfo.TE_DATO, así como a los títulos, subtítulos, secciones y elementos HTML.
     * 
     * @param style_td_etiqueta                 Estilo personalizado para el TD con la etiqueta
     */
    public void setStyleTdEtiquetaUltimoElemento(Style style_td_etiqueta) {
        CElementoInfo ei = getUltimoElemento();
        if (ei != null) {
            ei.setStyleTdEtiqueta(style_td_etiqueta);
        }
    }
    
    
    /**
     * Establece el estilo del elemento TD que contrendrá el valor del último elemento añadido al info.
     * Esto sólo se aplica a los valores de elementos de tipo CElementoInfo.TE_DATO, y al texto a la derecha de los títulos y secciones.
     * 
     * @param style_td_valor                    Estilo personalizado para el TD con el valor
     */
    public void setStyleTdValorUltimoElemento(Style style_td_valor) {
        CElementoInfo ei = getUltimoElemento();
        if (ei != null) {
            ei.setStyleTdValor(style_td_valor);
        }
    }
    
    
    /**
     * Establece el estilo del elemento TD que contrendrá la explicación del último elemento añadido al info.
     * Esto sólo se aplica a las explicaciones de los elementos de tipo CElementoInfo.TE_DATO.
     * 
     * @param style_td_explicacion              Estilo personalizado para el TD con la explicación
     */
    public void setStyleTdExplicacionUltimoElemento(Style style_td_explicacion) {
        CElementoInfo ei = getUltimoElemento();
        if (ei != null) {
            ei.setStyleTdExplicacion(style_td_explicacion);
        }
    }
    
    
    
    /**
     * Obtiene el número de la columnas de elementos en la que se ubica el elemento dado por su índice.
     * 
     * @param indice_elemento                   Índice del elemento
     * 
     * @return                                  Número de la columna en la que se encuentra (< numero_columnas_elementos)
     */
    private int getNumeroColumnaElemento(int indice_elemento) {
        int numero_columna = 0;
        
        if (numero_columnas_elementos <= 1) {
            return 0;
        }
        
        
        for (int i = 0; i < l_elementos.size(); i++) {
            CElementoInfo ei = l_elementos.get(i);
            
            switch (ei.tipo) {
                
                case CElementoInfo.TE_TITULO:
                case CElementoInfo.TE_SUBTITULO:
                case CElementoInfo.TE_SECCION:
                case CElementoInfo.TE_SEPARACION:
                    numero_columna = 0;
                    break;

                case CElementoInfo.TE_DATO:
                case CElementoInfo.TE_HTML:
                    
                    break;

                case CElementoInfo.TE_HUECO:
                    break;
            }
            
            if (i == indice_elemento) {
                return numero_columna;
            }
        }
        
        return 0;
    }
    
    
    
    
    /**
     * Obtiene el código HTML para mostrar el info.
     *
     * @return                                  Código HTML del info
     */
    public StringBuilder toStringBuilder() {
        
        Table table = new Table();
        
        CFila fila;
        CCelda celda;
        
        int columna_actual = 0;
        
        for (int i = 0; i < l_elementos.size(); i++) {
            CElementoInfo ei = l_elementos.get(i);
            
            switch (ei.tipo) {
                
                case CElementoInfo.TE_TITULO:
                    table.addTitulo(ei.etiqueta, ei.valor);
                    break;

                case CElementoInfo.TE_SUBTITULO:
                    table.addSubtitulo(ei.etiqueta);
                    break;

                case CElementoInfo.TE_SECCION:
                    table.addSeccion(ei.etiqueta, ei.valor);
                    break;
                    
                case CElementoInfo.TE_SEPARACION:
                    table.addSeparacion();
                    
                case CElementoInfo.TE_DATO:
                    // Etiqueta
                    celda = new CCelda(ei.etiqueta);
                    fila.addCelda(celda);
                    
                    // Valor
                    celda = new CCelda(ei.valor);
                    fila.addCelda(celda);

                    // Explicación
                    celda = new CCelda(ei.explicacion);
                    fila.addCelda(celda);
                    
                    break;
                
                case CElementoInfo.TE_HTML:
                    break;

                case CElementoInfo.TE_HUECO:
                    break;
            }
        }
        
        
        
        Tag tag_table = new Tag("table");
        if (!Cadenas.vacio(estilo.clase_table)) {
            tag_table.setAtributoClass(estilo.clase_table);
        }
        tag_table.addAtributos(this.getListaAtributos());

        StringBuilder html_filas;
        if (!hayFilasDatosOHtml()) {
            html_filas = getHtmlTablaSinDatos();
        } else {
            html_filas = getHtmlFilas();
        }
        tag_table.setContenido(html_filas);
        
        StringBuilder html_table = tag_table.toStringBuilder();
        return html_table;
    }
}
