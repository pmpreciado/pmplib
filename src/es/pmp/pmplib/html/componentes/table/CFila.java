/**
 * CFila.java
 *
 * Creado el 23-oct-2015, 10:17:08
 */

package es.pmp.pmplib.html.componentes.table;

import es.pmp.pmplib.Arrays;
import es.pmp.pmplib.Cadenas;
import es.pmp.pmplib.Comun;
import es.pmp.pmplib.html.base.HtmlBase;
import es.pmp.pmplib.html.base.ListaAtributos;
import es.pmp.pmplib.html.base.Style;
import es.pmp.pmplib.html.base.Tag;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase para definir una fila de una tabla de la clase Table.
 * 
 * @author pmpreciado
 */
public final class CFila extends ListaAtributos {

    /** Tipos de fila */
    public static final int TF_DATOS             = 1;
    public static final int TF_TITULO            = 2;
    public static final int TF_SUBTITULO         = 3;
    public static final int TF_CABECERA          = 4;
    public static final int TF_SECCION           = 5;
    public static final int TF_SEPARACION        = 6;
    public static final int TF_HTML              = 7;
    public static final int TF_TOTAL             = 8;

    /** Tipo de fila predeterminado */
    public static final int TF_PREDETERMINADO   = TF_DATOS;

    
    
    /** Tipo de fila */
    int tipo;
    
    /** Flag que indica si la fila está resaltada */
    boolean resaltada;

    /** Flag que indica si la fila está oculta */
    boolean oculta;
    
    
    /** Flag que indica que la fila no provoca cambio en la secuencia de coloración filas */
    boolean no_cambiar_color_secuencia;
    
    
    /** Celdas de la fila */
    List <CCelda> l_celdas;
    
    
    /** Texto a la derecha (para filas de títulos y secciones) */
    String texto_derecha;
    
    
    /**
     * Crea una instancia de la clase.
     */
    public CFila() {
        this.tipo = TF_PREDETERMINADO;
        l_celdas = new ArrayList <> ();
    }
    
    
    /** 
     * Crea una nueva fila del tipo dado.
     * 
     * @param tipo_fila                         Tipo de fila (CFila.TF_xxx)
     */
    public CFila(int tipo_fila) {
        this();
        this.tipo = tipo_fila;
    }
    
    
    
    /** 
     * Crea una nueva fila del tipo dado, y con las celdas suministradas.
     * 
     * @param tipo_fila                         Tipo de fila (CFila.TF_xxx)
     * @param celdas                            String [] con las celdas de la fila
     */
    public CFila(int tipo_fila, String [] celdas) {
        this();
        setFila(tipo_fila, celdas);
    }
    
    
    /** 
     * Crea una nueva fila del tipo dado, y con las celdas suministradas.
     * 
     * @param tipo_fila                         Tipo de fila (CFila.TF_xxx)
     * @param l_celdas                          Lista con las celdas de la fila
     */
    public CFila(int tipo_fila, List <String> l_celdas) {
        this();
        setFila(tipo_fila, l_celdas);
    }
    
    
    /** 
     * Crea una nueva fila de datos  con las celdas suministradas.
     * 
     * @param l_celdas                          Lista con las celdas de la fila
     */
    public CFila(List <String> l_celdas) {
        this();
        setFila(TF_PREDETERMINADO, l_celdas);
    }
    
    
    /**
     * Establece el tipo de fila y las celdas de la fila.
     * 
     * @param tipo                              Tipo de fila (CFila.TF_xxx)
     * @param arr_celdas                        Array con las celdas de la fila
     */
    public final void setFila(int tipo, String [] arr_celdas) {
        
        this.tipo = tipo;
        
        for (int i = 0; i < Arrays.length(arr_celdas); i++) {
            CCelda celda = new CCelda(arr_celdas[i]);
            l_celdas.add(celda);
        }
    }
    
    
    /**
     * Establece el tipo de fila y las celdas de la fila.
     * 
     * @param tipo_fila                         Tipo de fila (CFila.TF_xxx)
     * @param l_celdas                          Lista con las celdas de la fila
     */
    public final void setFila(int tipo_fila, List <String> l_celdas) {
        
        this.tipo = tipo_fila;
        
        for (int i = 0; i < Arrays.size(l_celdas); i++) {
            CCelda celda = new CCelda(l_celdas.get(i));
            this.l_celdas.add(celda);
        }
    }
    
    
    
    
    /**
     * Define el tipo de la fila.
     *
     * @param tipo                              Tipo de fila (CFila.TF_xxx)
     */
    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
    
    
    /**
     * Obtiene el tipo de la fila.
     *
     * @return                                  Tipo de fila (CFila.TF_xxx)
     */
    public int getTipo() {
        return tipo;
    }

    
    /**
     * Comprueba si el tipo de la fila se corresponde con un tipo que ocupa una fila entera.
     * Los tipos que ocupan una fila entera son estos:
     *      - TF_TITULO
     *      - TF_SUBTITULO
     *      - TF_SECCION
     *      - TF_SEPARACION
     *      - TF_HTML
     * 
     * @return                                  'true' si este elemento es de un tipo que ocupa una fila entera
     *                                          'false' en caso contrario
     */
    public boolean esTipoFilaEntera() {
        switch (tipo) {
            case TF_TITULO:
            case TF_SUBTITULO:
            case TF_SECCION:
            case TF_SEPARACION:
            case TF_HTML:
                return true;
        }
        
        return false;
    }
    
    
    /**
     * Añade una celda al final de la fila.
     * Si la fila estuviera vacía, crea una nueva fila insertando la celda.
     * 
     * @param celda                             Celda a añadir
     */
    public void addCelda(CCelda celda) {
        l_celdas.add(celda);
    }

    
    /**
     * Añade una celda al final de la fila.
     * Si la fila estuviera vacía, crea una nueva fila insertando la celda.
     * 
     * @param texto                             Contenido de la celda
     */
    public void addCelda(String texto) {
        CCelda celda = new CCelda(texto);
        l_celdas.add(celda);
    }
    
    
    /**
     * Añade una celda al final de la fila.
     * Si la fila estuviera vacía, crea una nueva fila insertando la celda.
     * 
     * @param valor                             Contenido de la celda
     */
    public void addCelda(int valor) {
        CCelda celda = new CCelda(valor);
        l_celdas.add(celda);
    }
    
    
    /**
     * Añade una celda al final de la fila.
     * Si la fila estuviera vacía, crea una nueva fila insertando la celda.
     * 
     * @param valor                             Contenido de la celda
     */
    public void addCelda(long valor) {
        CCelda celda = new CCelda(valor);
        l_celdas.add(celda);
    }
    
    
    /**
     * Añade una celda al final de la fila.
     * En la celda establece un valor booleano, de modo que si es 'true', en la celda introduce "Sí". Si es 'false', introduce "No".
     * Si la fila estuviera vacía, crea una nueva fila insertando la celda.
     * 
     * @param valor                             Contenido de la celda
     */
    public void addCelda(boolean valor) {
        String s_valor = Cadenas.getSiNo(valor);
        addCelda(s_valor);
    }
    
    
    /**
     * Añade varias celdas en blanco al final de la fila.
     *
     * @param n                                 Número de celdas a añadir
     */
    public void addHuecos(int n) {
        for (int i = 0; i < n; i++) {
            addCelda("");
        }
    }

    
    /**
     * Añade una celda en blanco al final de la fila.
     */
    public void addHueco() {
        addHuecos(1);
    }
    

    /**
     * Establece una celda de la fila de la cabecera, sustituyendo el contenido existente por el nuevo.
     * Si el índice de la celda supera las dimensiones de la fila, esta última es expandida.
     *
     * @param indice                            Índice de la celda dentro de la fila (empezando en 0)
     * @param celda                             Nuevo contenido de la celda
     */
    public void setCelda(int indice, CCelda celda) {
        while (getNumeroCeldas() < indice + 1) {
            CCelda hueco = new CCelda("");
            addCelda(hueco);
        }

        l_celdas.set(indice, celda);
    }
    
    
    
    
    /**
     * Comprueba si la fila contiene texto a la derecha (para filas de títulos y secciones).
     *
     * @return                                  'true' si la fila contiene texto a la derecha (para filas de títulos y secciones)
     *                                          'false' en caso contrario
     */
    public boolean hayTextoDerecha() {
        return texto_derecha != null;
    }

    
    /**
     * Obtiene el texto a la derecha (para filas de títulos y secciones).
     *
     * @return                                  Texto a la derecha (para filas de títulos y secciones)
     */
    public String getTextoDerecha() {
        return texto_derecha;
    }

    
    /**
     * Establece el texto a la derecha (para filas de títulos y secciones).
     *
     * @param texto_derecha                     Texto a la derecha (para filas de títulos y secciones)
     */
    public void setTextoDerecha(String texto_derecha) {
        this.texto_derecha = texto_derecha;
    }

    
    /**
     * Establece el flag que indica que la fila está resaltada.
     * 
     * @param resaltada                         Si es 'true', la fila estará resaltada
     *                                          Si es 'false', no estará resaltada
     */
    public void setResaltada(boolean resaltada) {
        this.resaltada = resaltada;
    }

    
    /**
     * Obitene si la fila está resaltada.
     * 
     * @return                              'true', si la fila está resaltada
     *                                      'false', si no está resaltada
     */
    public boolean isResaltada() {
        return resaltada;
    }
    
    
    /**
     * Establece el flag que indica que la fila está oculta.
     * 
     * @param oculta                        Si es 'true', la fila estará oculta
     *                                      Si es 'false', estará visible   
     */
    public void setOculta(boolean oculta) {
        this.oculta = oculta;
    }

    
    /**
     * Obitene si la fila está oculta.
     * 
     * @return                              'true', si la fila está oculta
     *                                      'false', si está visible   
     */
    public boolean isOculta() {
        return oculta;
    }
    
    
    /**
     * Establece el flag que indica que la fila no provoca cambio en la secuencia de coloración filas.
     * 
     * @param no_cambiar_color_secuencia        Si es 'true', la fila estará oculta
     *                                          Si es 'false', estará visible   
     */
    public void setNoCambiarColorSecuencia(boolean no_cambiar_color_secuencia) {
        this.no_cambiar_color_secuencia = no_cambiar_color_secuencia;
    }

    
    /**
     * Obtiene el flag que indica que la fila no provoca cambio en la secuencia de coloración filas.
     * 
     * @return                                  'true', si la fila está oculta
     *                                          'false', si está visible   
     */
    public boolean getNoCambiarColorSecuencia() {
        return no_cambiar_color_secuencia;
    }
    
    
    /**
     * Obtiene el número de celdas que hay en la fila.
     *
     * @return                                  Número de celdas en la fila
     */
    public int getNumeroCeldas() {

        int nc = Arrays.size(l_celdas);
        return nc;
    }
    
    
    /**
     * Obtiene una celda de la fila.
     *
     * @param indice                            Índice de la celda a obtener (la primera es 0)
     * @return                                  Objeto de clase CCelda con la celda
     */
    public CCelda getCelda(int indice) {
        if (getNumeroCeldas() - 1 < indice) return null;
        CCelda c = l_celdas.get(indice);
        return c;
    }


    /**
     * Obtiene la última celda.
     *
     * @return                                  Objeto de clase CCelda con la celda
     */
    public CCelda getUltimaCelda() {
        int indice = getNumeroCeldas() - 1;
        return getCelda(indice);
    }
    
    
   /**
     * Obtiene todas las celdas de la fila.
     *
     * @return                                  Lista con las celdas
     */
    public List <CCelda> getCeldas() {
        return this.l_celdas;
    }    

    
    /**
     * Retorna la clase que hay que utilizar para una fila de datos o similar, teniendo en cuenta si la fila ocupa una posición par o impar,
     * o si la fila está resaltada.
     * 
     * @param estilo_table                      Estilos de la tabla
     * @param estilo_impar                      'true' para usar el estilo de fila impar
     *                                          'false' para usar el estilo de fila par
     * @param resaltada                         'true' si la fila está resaltada
     *                                          'false' si no lo está
     * 
     * @return                                  Clase a utilizar
     */
    private static String getClaseFilaDatos(EstiloTabla estilo_table, boolean estilo_impar, boolean resaltada) {
        String clase;
        
        if (estilo_impar) {
            if (resaltada) {
                clase = estilo_table.clase_tr_datos_impar_resaltada;
            } else {
                clase = estilo_table.clase_tr_datos_impar;
            }
        } else {
            if (resaltada) {
                clase = estilo_table.clase_tr_datos_par_resaltada;
            } else {
                clase = estilo_table.clase_tr_datos_par;
            }
        }
        return clase;
    }
    
    
    /**
     * Genera la tabla interior, que contiene el título y el título a la derecha, para una fila de tipo título o sección.
     * 
     * @param table                             Tabla que contiene la fila
     * 
     * @return                                  Tag con la tabla interior
     */
    private Tag getHtmlFilaTituloOSeccionTablaInterior(Table table) {
        Tag tag_table_interior = new Tag("table");
        tag_table_interior.setAtributoClass("nodeco");
        tag_table_interior.setAtributoStyle("width: 100%");
        
        Tag tag_tr_interior = new Tag("tr");
        
        // Título o título de la sección
        CCelda celda_0 = getCelda(0);
        StringBuilder td_titulo = celda_0.getHtml(table, tipo, null);
        tag_tr_interior.addContenido(td_titulo);
        
        // Texto a la derecha
        if (this.hayTextoDerecha()) {
            Tag tag_td_titulo_derecha = new Tag("td");
            
            if (tipo == CFila.TF_TITULO) {
                tag_td_titulo_derecha.setAtributoClass(table.estilo.clase_td_titulo_derecha);
            } else if (tipo == CFila.TF_SECCION) {
                tag_td_titulo_derecha.setAtributoClass(table.estilo.clase_td_seccion_derecha);
            }
            
            tag_td_titulo_derecha.setContenido(texto_derecha);
            tag_tr_interior.addContenido(tag_td_titulo_derecha);
        }
        
        
        tag_table_interior.addContenido(tag_tr_interior);
        return tag_table_interior;
    }
    
    
    /**
     * Obtiene el código HTML de la fila cuando es de tipo título o de tipo sección.
     * Ambos tipos usan la misma función porque el código es muy similar; sólo varía la clase HTML a utilizar.
     * Se utiliza una tabla interna a la fila para introducir el título y el texto a la derecha en dos filas separadas.
     * 
     * @param table                             Tabla que contiene la fila
     * @param num_columnas                      Número de columnas que hay en la tabla
     * @param lista_atributos_heredados         Lista de atributos heredados del elemento padre
     *                                          En caso de colisión, tienen preferencia los de la propia fila
     *                                          Si es 'null', se ignora
     * 
     * @return                                  Contenido de la fila, entre las etiquetas TR
     */
    private StringBuilder getHtmlFilaTituloOSeccion(Table table, ListaAtributos lista_atributos_heredados) {
        
        // Tabla interior
        Tag tag_table_interior = getHtmlFilaTituloOSeccionTablaInterior(table);
        
        // Fila de título
        Tag tag_tr = new Tag("tr");
        
        ListaAtributos lista_atributos_generado = new ListaAtributos();
        if (tipo == CFila.TF_TITULO) {
            lista_atributos_generado.setAtributoClass(table.estilo.clase_tr_titulo);
        } else {
            lista_atributos_generado.setAtributoClass(table.estilo.clase_tr_seccion);
        }
        
        ListaAtributos lista_atributos_combinada = ListaAtributos.combinar(this, lista_atributos_generado, lista_atributos_heredados);
        tag_tr.addAtributos(lista_atributos_combinada);

        Tag tag_td = new Tag("td");
        tag_td.setAtributoClass("nodeco");
        int num_columnas = table.getNumeroColumnas();
        if (num_columnas > 1) {
            tag_td.setAtributo("colspan", num_columnas);
        }
        
        tag_td.setContenido(tag_table_interior);
        tag_tr.setContenido(tag_td);

        return tag_tr.toStringBuilder();
    }
    
    
    /**
     * Obtiene el código HTML de la fila cuando es de tipo subtítulo.
     * Se utiliza una tabla interna a la fila para introducir el título y el texto a la derecha en dos filas separadas.
     * 
     * @param table                             Tabla que contiene la fila
     * @param lista_atributos_heredados         Lista de atributos heredados del elemento padre
     *                                          En caso de colisión, tienen preferencia los de la propia fila
     *                                          Si es 'null', se ignora
     * @param estilo_impar                      Sólo para filas HTML
     *                                          'true' para usar el estilo de fila impar
     *                                          'false' para usar el estilo de fila par
     * 
     * @return                                  Contenido de la fila, entre las etiquetas TR
     */
    private StringBuilder getHtmlFilaSubtituloOHtml(Table table, ListaAtributos lista_atributos_heredados, boolean estilo_impar) {
        
        Tag tag_tr = new Tag("tr");
        
        ListaAtributos lista_atributos_generado = new ListaAtributos();
        if (tipo == CFila.TF_CABECERA) {
            lista_atributos_generado.setAtributoClass(table.estilo.clase_tr_subtitulo);
        } else {
            String clase = getClaseFilaDatos(table.estilo, estilo_impar, resaltada);
            lista_atributos_generado.setAtributoClass(clase);
        }
        
        ListaAtributos lista_atributos_combinada = ListaAtributos.combinar(this, lista_atributos_generado, lista_atributos_heredados);
        tag_tr.addAtributos(lista_atributos_combinada);

        Tag tag_td = new Tag("td");
        tag_td.setAtributoClass("nodeco");
        int num_columnas = table.getNumeroColumnas();
        if (num_columnas > 1) {
            tag_td.setAtributo("colspan", num_columnas);
        }
        
        CCelda celda_0 = getCelda(0);
        StringBuilder html_subtitulo = celda_0.getHtml(table, tipo, null);
        tag_td.setContenido(html_subtitulo);
        
        return tag_tr.toStringBuilder();
    }
    

    /**
     * Obtiene el código HTML de la fila cuando es de tipo cabecera, de tipo datos o de tipo total.
     * Ambos tipos usan la misma función porque el código es muy similar; sólo varía la clase HTML a utilizar.
     * 
     * @param table                             Tabla que contiene la fila
     * @param lista_atributos_heredados         Lista de atributos heredados del elemento padre
     *                                          En caso de colisión, tienen preferencia los de la propia fila
     *                                          Si es 'null', se ignora
     * @param estilo_impar                      Sólo para filas de datos.
     *                                          'true' para usar el estilo de fila impar
     *                                          'false' para usar el estilo de fila par
     * 
     * @return                                  Contenido de la fila, entre las etiquetas TR
     */
    private StringBuilder getHtmlFilaCabeceraODatosOTotal(Table table, ListaAtributos lista_atributos_heredados, boolean estilo_impar) {
        
        Tag tag_tr = new Tag("tr");
        
        String clase_tr = null;
        String color_separacion_vertical = null;
        
        
        switch (tipo) {
            case CFila.TF_CABECERA:
                clase_tr = table.estilo.clase_tr_cabecera;
                color_separacion_vertical = table.estilo.separacion_vertical_color_cabecera;
                break;
                
            case CFila.TF_DATOS:
                clase_tr = getClaseFilaDatos(table.estilo, estilo_impar, resaltada);
                color_separacion_vertical = table.estilo.separacion_vertical_color_datos;
                break;
                
            case CFila.TF_TOTAL:
                clase_tr = table.estilo.clase_tr_total;
                color_separacion_vertical = table.estilo.separacion_vertical_color_total;
                break;
        }
        
        ListaAtributos lista_atributos_generado = new ListaAtributos();
        if (clase_tr != null) {
            lista_atributos_generado.setAtributoClass(clase_tr);
        }
        
        ListaAtributos lista_atributos_combinada = ListaAtributos.combinar(this, lista_atributos_generado, lista_atributos_heredados);
        tag_tr.addAtributos(lista_atributos_combinada);

        for (int i = 0; i < Arrays.size(l_celdas); i++) {
            CCelda celda = l_celdas.get(i);
            
            ListaAtributos lista_atributos_heredados_celda = new ListaAtributos();
            
            Style style_celda = new Style();
            
            if (table.haySeparacionVertical(i)) {
                style_celda.addAtributo("border-right-width", table.estilo.separacion_vertical_ancho);
                if (color_separacion_vertical != null) {
                    style_celda.addAtributo("border-right-color", color_separacion_vertical);
                }
                style_celda.addAtributo("border-right-style", "solid");
            }
            
            HtmlBase.ALINEACION alineacion = table.getAlineacionEfectivaColumna(i);
            if (alineacion != null) {
                style_celda.addAtributo("text-align", alineacion.toString());
            }
            
            if (celda.getExpandir() > 1) {
                lista_atributos_heredados_celda.setAtributo("colspan", celda.getExpandir());
            }
            
            if (!style_celda.vacio()) {
                lista_atributos_heredados_celda.setAtributoStyle(style_celda);
            }
            
            StringBuilder html_celda = celda.getHtml(table, tipo, lista_atributos_heredados_celda);
            tag_tr.addContenido(html_celda);
        }
        
        return tag_tr.toStringBuilder();
    }
    
    
    

    /**
     * Obtiene el código HTML de la fila cuando es de tipo separación.
     * Se utiliza una tabla interna a la fila para introducir el título y el texto a la derecha en dos filas separadas.
     * 
     * @param table                             Tabla que contiene la fila
     * @param lista_atributos_heredados         Lista de atributos heredados del elemento padre
     *                                          En caso de colisión, tienen preferencia los de la propia fila
     *                                          Si es 'null', se ignora
     * 
     * @return                                  Contenido de la fila, entre las etiquetas TR
     */
    private StringBuilder getHtmlFilaSeparacion(Table table, ListaAtributos lista_atributos_heredados) {
        
        Tag tag_tr = new Tag("tr");
        
        ListaAtributos lista_atributos_generado = new ListaAtributos();
        lista_atributos_generado.setAtributoClass(table.estilo.clase_tr_separacion);
        
        ListaAtributos lista_atributos_combinada = ListaAtributos.combinar(this, lista_atributos_generado, lista_atributos_heredados);
        tag_tr.addAtributos(lista_atributos_combinada);

        Tag tag_td = new Tag("td");
        int num_columnas = table.getNumeroColumnas();
        if (num_columnas > 1) {
            tag_td.setAtributo("colspan", num_columnas);
        }
        tag_tr.addContenido(tag_td);
       
        return tag_tr.toStringBuilder();
    }
    
    
    /**
     * Obtiene el código HTML para generar la fila.
     * 
     * @param table                             Tabla que contiene la fila
     * @param lista_atributos_heredados         Lista de atributos heredados del elemento padre
     *                                          En caso de colisión, tienen preferencia los de la propia fila
     *                                          Si es 'null', se ignora
     * @param estilo_impar                      'true' para usar el estilo de fila impar
     *                                          'false' para usar el estilo de fila par
     *
     * @return                                  Código HTML de la fila dada     
     */
    StringBuilder getHtml(Table table, ListaAtributos lista_atributos_heredados, boolean estilo_impar) {
        
        StringBuilder contenido_tr = new StringBuilder();
        
        switch (tipo) {
            case CFila.TF_TITULO:
            case CFila.TF_SECCION:
                contenido_tr = getHtmlFilaTituloOSeccion(table, lista_atributos_heredados);
                break;
                
            case CFila.TF_SUBTITULO:
            case CFila.TF_HTML:
                contenido_tr = getHtmlFilaSubtituloOHtml(table, lista_atributos_heredados, estilo_impar);
                break;

            case CFila.TF_CABECERA:
            case CFila.TF_DATOS:
            case CFila.TF_TOTAL:
                contenido_tr = getHtmlFilaCabeceraODatosOTotal(table, lista_atributos_heredados, estilo_impar);
                break;

            case CFila.TF_SEPARACION:
                contenido_tr = getHtmlFilaSeparacion(table, lista_atributos_heredados);
                break;
        }
        
        return contenido_tr;
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
