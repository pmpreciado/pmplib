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
import es.pmp.pmplib.util.Holder;
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
     * Número de columnas en el info de manera predeterminada.
     * En cada columna se muestra una etiqueta, un valor y, opcionalmente, una explicación.
     */
    private static final int NUMERO_COLUMNAS_INFO_PREDETERMINADO = 1;
    
    /** Espacio predeterminado para la separación entre columnas de elementos */
    private static final String ESPACIO_SEPARACION_COLUMNAS = "24pt";
    
    
    /** Estilo del info */
    EstiloInfo estilo;

    
    /**
     * Número de columnas de elementos en el info.
     * Cada columna de elementos contiene 3 columnas de tabla, a saber:
     *    1: Etiqueta
     *    2: Valor (en elementos de tipo dato, también en títulos a la derecha)
     *    3: Explicación (opcional en columnas de datos)
     */
    int numero_columnas_info;
    
    
    /** Elementos que componen el Info */
    List <CElementoInfo> l_elementos;

    
    /** Espacio de separación entre columnas de elementos, por ejemplo "12pt" o "24px" */
    String espacio_separacion_columnas;
    
    /** Flag para indicar si se va a utilizar una línea vertical para separar las columnas de elementos ('true' por defecto) */
    boolean usar_linea_separacion_columnas;
    
    
    /**
     * Crea una instancia de la clase.
     */
    public Info() {
        numero_columnas_info = NUMERO_COLUMNAS_INFO_PREDETERMINADO;
        l_elementos = new ArrayList();
        estilo = EstiloInfo.getEstiloPredeterminado();
        espacio_separacion_columnas = ESPACIO_SEPARACION_COLUMNAS;
        usar_linea_separacion_columnas = true;
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
     * @param numero_columnas_info              Número de columnas
     */
    public void setNumeroColumnasInfo(int numero_columnas_info) {
        this.numero_columnas_info = numero_columnas_info;
    }
    
    
    /**
     * Obtiene el número de columnas.
     * Cada columna de elementos contiene 3 columnas de tabla, a saber:
     *    1: Etiqueta
     *    2: Valor (en elementos de tipo dato, también en títulos a la derecha)
     *    3: Explicación (opcional en columnas de datos)
     *
     * @return                                  Número de columnas
     */
    public int getNumeroColumnasInfo() {
        return numero_columnas_info;
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
        ei.setTextoDerecha(texto_derecha);
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
        ei.setTextoDerecha(texto_derecha);
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
     * Añade un hueco al info.
     */
    public void add() {
        CElementoInfo ei = new CElementoInfo(CElementoInfo.TE_HUECO);
        addElemento(ei);
    }
    
    
    /**
     * Añade un hueco al info.
     * Esta función es un alias de add().
     */
    public void addHueco() {
        add();
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
     * Establece el espacio de separación entre columnas de elementos del info.
     * 
     * @param espacio_separacion_columnas       Espacio de separación entre columnas de elementos
     */
    public void setEspacioSeparacionColumnas(String espacio_separacion_columnas) {
        this.espacio_separacion_columnas = espacio_separacion_columnas;
    }

    /**
     * Obtiene el espacio de separación entre columnas de elementos del info.
     * 
     * @return                                  Espacio de separación entre columnas de elementos
     */
    public String getEspacioSeparacionColumnas() {
        return espacio_separacion_columnas;
    }
    
    /**
     * Establece si se va a utilizar una línea vertical para separar las columnas de elementos.
     * 
     * @param usar_linea_separacion_columnas    'true' para utilizar una línea vertical para separar las columnas de elementos
     *                                          'false' para no utilizarla
     */
    public void setUsarLineaSeparacionColumnas(boolean usar_linea_separacion_columnas) {
        this.usar_linea_separacion_columnas = usar_linea_separacion_columnas;
    }
    
    /**
     * Comprueba si se va a utilizar una línea vertical para separar las columnas de elementos.
     * 
     * @return                                  'true' si se va a utilizar una línea vertical para separar las columnas de elementos
     *                                          'false' si no se va a utilizar
     */
    public boolean getUsarLineaSeparacionColumnas() {
        return usar_linea_separacion_columnas;
    }
    
    
    
    
    /**
     * Obtiene una fila con un elemento de tipo título, para añadir al info.
     * 
     * @param ei                                Elemento que contiene el título
     * 
     * @return                                  Fila de tipo título
     */
    private CFila getNextFilaTitulo(CElementoInfo ei) {
        CFila fila = new CFila(CFila.TF_TITULO);
        if (ei.texto_derecha != null) {
            fila.setTextoDerecha(ei.texto_derecha);
        }

        CCelda celda = new CCelda(ei.etiqueta);
        if (ei.style_td_etiqueta != null) {
            celda.setAtributoStyle(ei.style_td_etiqueta);
        }
        
        fila.addCelda(celda);
        
        return fila;
    }
    
    
    /**
     * Obtiene una fila con un elemento de tipo subtítulo, para añadir al info.
     * 
     * @param ei                                Elemento que contiene el subtítulo
     * 
     * @return                                  Fila de tipo subtítulo
     */
    private CFila getNextFilaSubtitulo(CElementoInfo ei) {
        CFila fila = new CFila(CFila.TF_SUBTITULO);
        CCelda celda = new CCelda(ei.etiqueta);
        if (ei.style_td_etiqueta != null) {
            celda.setAtributoStyle(ei.style_td_etiqueta);
        }
        
        fila.addCelda(celda);
        
        return fila;
    }
        
    
    /**
     * Obtiene una fila con un elemento de tipo sección, para añadir al info.
     * 
     * @param ei                                Elemento que contiene la sección
     * 
     * @return                                  Fila de tipo sección
     */
    private CFila getNextFilaSeccion(CElementoInfo ei) {
        CFila fila = new CFila(CFila.TF_SECCION);
        if (ei.texto_derecha != null) {
            fila.setTextoDerecha(ei.texto_derecha);
        }
        
        CCelda celda = new CCelda(ei.etiqueta);
        if (ei.style_td_etiqueta != null) {
            celda.setAtributoStyle(ei.style_td_etiqueta);
        }
        fila.addCelda(celda);
        fila.setTextoDerecha(ei.valor);
        
        return fila;
    }
    
    
    /**
     * Obtiene una fila con un elemento de tipo separación, para añadir al info.
     * 
     * @param ei                                Elemento que contiene la separación
     * 
     * @return                                  Fila de tipo separación
     */
    private CFila getNextFilaSeparacion() {
        CFila fila = new CFila(CFila.TF_SEPARACION);
        return fila;
    }
    
    
    
    
    /**
     * Añade un elemento de tipo dato a la fila dada.
     * 
     * @param fila                              Fila a la que se añadirá el elemento
     * @param ei                                Elemento a añadir
     * @param estilo_impar                      'true' para usar el estilo de fila impar
     *                                          'false' para usar el estilo de fila par
     * @param num_elementos_fila                Número de elementos que actualmente hay en la fila
     */
    private void addElementoDatoAFila(CFila fila, CElementoInfo ei, boolean estilo_impar, int num_elementos_fila) {
        
        // Etiqueta
        CCelda celda = new CCelda(ei.etiqueta);
        if (estilo_impar) {
            celda.setAtributoClass(estilo.clase_td_etiqueta_impar);
        } else {
            celda.setAtributoClass(estilo.clase_td_etiqueta_par);
        }

        if (ei.etiqueta_expandida) {
            celda.setExpandir(2);
        }

        if (ei.style_td_etiqueta != null) {
            celda.setAtributoStyle(ei.style_td_etiqueta);
        }
        fila.addCelda(celda);


        // Valor
        if (!ei.etiqueta_expandida) {

            // En elementos que se expanden hasta el final, calculamos el colspan
            int colspan_valor = 0;
            if (ei.expandir_hasta_final && !ei.hayExplicacion()) {
                colspan_valor = 2 + (numero_columnas_info - num_elementos_fila * 3);
            }

            celda = new CCelda(ei.valor);
            if (estilo_impar) {
                celda.setAtributoClass(estilo.clase_td_valor_impar);
            } else {
                celda.setAtributoClass(estilo.clase_td_valor_par);
            }
            if (ei.style_td_valor != null) {
                celda.setAtributoStyle(ei.style_td_valor);
            }
            if (colspan_valor > 1) {
                celda.setExpandir(colspan_valor);
            }

            fila.addCelda(celda);
        }

        // Explicación
        if (!ei.expandir_hasta_final || ei.hayExplicacion()) {
            int colspan_explicacion = 0;

            if (ei.expandir_hasta_final && ei.hayExplicacion()) {
                colspan_explicacion = 1 + (numero_columnas_info - num_elementos_fila * 3);
            }

            celda = new CCelda(ei.explicacion);
            if (estilo_impar) {
                celda.setAtributoClass(estilo.clase_td_explicacion_impar);
            } else {
                celda.setAtributoClass(estilo.clase_td_explicacion_par);
            }

            if (ei.style_td_explicacion != null) {
                celda.setAtributoStyle(ei.style_td_explicacion);
            }

            if (colspan_explicacion > 1) {
                celda.setExpandir(colspan_explicacion);
            }

            fila.addCelda(celda);
        }
    }
    

    /**
     * Añade un elemento de tipo HTML a la fila dada.
     * 
     * @param fila                              Fila a la que se añadirá el elemento
     * @param ei                                Elemento a añadir
     * @param estilo_impar                      'true' para usar el estilo de fila impar
     *                                          'false' para usar el estilo de fila par
     * @param num_elementos_fila                Número de elementos que actualmente hay en la fila
     */
    private void addElementoHtmlAFila(CFila fila, CElementoInfo ei, boolean estilo_impar, int num_elementos_fila) {
        
        // Etiqueta
        CCelda celda = new CCelda(ei.etiqueta);
        if (estilo_impar) {
            celda.setAtributoClass(estilo.clase_td_etiqueta_impar);
        } else {
            celda.setAtributoClass(estilo.clase_td_etiqueta_par);
        }
        
        if (ei.style_td_etiqueta != null) {
            celda.setAtributoStyle(ei.style_td_etiqueta);
        }
        
        int colspan = 3;
        if (ei.expandir_hasta_final) {
            colspan = 3 + (numero_columnas_info - num_elementos_fila * 3);
        }
        
        celda.setExpandir(colspan);
        
        fila.addCelda(celda);
    }
    
    
    /**
     * Añade un elemento de tipo hueco a la fila dada.
     * 
     * @param fila                              Fila a la que se añadirá el elemento
     * @param ei                                Elemento a añadir
     * @param estilo_impar                      'true' para usar el estilo de fila impar
     *                                          'false' para usar el estilo de fila par
     * @param num_elementos_fila                Número de elementos que actualmente hay en la fila
     */
    private void addHuecoAFila(CFila fila, CElementoInfo ei, boolean estilo_impar, int num_elementos_fila) {
        addElementoDatoAFila(fila, ei, estilo_impar, num_elementos_fila);
    }
    
    
    
    /**
     * Añade el espacio de separación entre dos columnas del info.
     * Sólo lo añadirá si es preciso, es decir, si está definido que haya separación, si las columnas son interiores, y si la fila no es de tipo título o similar.
     * El espacio es añadido como un "padding" tras la celda actual.
     * 
     * @param fila                              Fila que se está generando
     * @param ei                                Elemento del info que se está ubicando dentro de la fila
     * @param indice_elemento_en_fila           Índice que ocupa el elemento dentro de la fila (>= 0)
     */
    private void añadirEspacioSeparacionEntreColumnas(CFila fila, CElementoInfo ei, int indice_elemento_en_fila) {

        if (!Cadenas.vacio(espacio_separacion_columnas)) {
            if (indice_elemento_en_fila < numero_columnas_info - 1 && !ei.esTipoFilaEntera() ) {
                CCelda ultima_celda = fila.getUltimaCelda();

                Style style_separacion = new Style();

                style_separacion.addAtributo("padding-right", espacio_separacion_columnas);
                Style style_actual = ultima_celda.getStyle();
                Style style_combinado = Style.combinar(style_separacion, style_actual);
                ultima_celda.setStyle(style_combinado);
            }
        }
    }
    
    
    /**
     * Obtiene una fila cuyo primer elemento es de tipo dato, HTML o hueco.
     * La fila se irá completando con los elementos siguientes, hasta que la función determine que la fila está completa.
     * 
     * @param ei                                Elemento que contiene el título
     * @param estilo_impar                      'true' para usar el estilo de fila impar
     *                                          'false' para usar el estilo de fila par
     * @param holder_num_elementos_añadidos     Parámetro de salida. Número de elementos añadidos a la fila
     * 
     * @return                                  Fila de tipo título
     */
    private CFila getNextFilaDatoHtmlHueco(int indice_elemento_actual, boolean estilo_impar, Holder <Integer> holder_num_elementos_añadidos) {
        
        CFila fila = new CFila(CFila.TF_DATOS);
        
        int num_elementos_fila = 0;
        
        do {
            CElementoInfo ei = l_elementos.get(indice_elemento_actual++);
            
            switch (ei.tipo) {
                case CElementoInfo.TE_DATO:
                    addElementoDatoAFila(fila, ei, estilo_impar, num_elementos_fila);
                    break;
                    
                case CElementoInfo.TE_HTML:
                    addElementoHtmlAFila(fila, ei, estilo_impar, num_elementos_fila);
                    break;
                    
                case CElementoInfo.TE_HUECO:
                    addHuecoAFila(fila, ei, estilo_impar, num_elementos_fila);
                    break;
                    
                default:
                    break;
            }

            // Espacio de separación entre columnas
            añadirEspacioSeparacionEntreColumnas(fila, ei, num_elementos_fila);
            
            num_elementos_fila++;
            
            // Si el elemento actual se expande hasta el final, cerramos ya la fila
            if (ei.expandir_hasta_final) {
                break;
            }
            
            // Si hemos alcanzado el máximo número de columnas del info, cerramos ya la fila
            if (num_elementos_fila >= numero_columnas_info) {
                break;
            }

            // Si no hay más elementos, en el info, o el siguiente elemento ocupa una fila entera, completamos la fila con huecos
            boolean rellenar_hasta_fin_fila = false;
            if (indice_elemento_actual >= l_elementos.size()) {
                rellenar_hasta_fin_fila = true;
            } else {
                CElementoInfo sig_ei = l_elementos.get(indice_elemento_actual);
                if (sig_ei.esTipoFilaEntera()) {
                    rellenar_hasta_fin_fila = true;
                }
            }
            
            if (rellenar_hasta_fin_fila) {
                CElementoInfo ei_hueco = new CElementoInfo(CElementoInfo.TE_HUECO);
                for (int i = num_elementos_fila; i < numero_columnas_info; i++) {
                    addHuecoAFila(fila, ei_hueco, estilo_impar, i);
                }
            }
            
            if (rellenar_hasta_fin_fila) {
                break;
            }
            
            
        } while (true);
        
        holder_num_elementos_añadidos.setValor(num_elementos_fila);
        
        return fila;
    }
    
    
    
    /**
     * Obtiene la siguiente fila a añadir al info.
     * 
     * @param indice_elemento_actual            Índice del elemento que toca añadir al info, dentro de l_elementos
     * @param estilo_impar                      'true' para usar el estilo de fila impar
     *                                          'false' para usar el estilo de fila par
     * @param holder_num_elementos_añadidos     Parámetro de salida. Número de elementos añadidos a la fila
     * 
     * @return                                  Objeto de clase CFila con la siguiente fila
     *                                          'null' si ya no hay más filas
     */
    private CFila getNextFila(int indice_elemento_actual, boolean estilo_impar, Holder <Integer> holder_num_elementos_añadidos) {
        
        CFila fila = null;
        
        if (indice_elemento_actual >= l_elementos.size()) {
            return null;
        }
        
        CElementoInfo ei = l_elementos.get(indice_elemento_actual);
        holder_num_elementos_añadidos.setValor(1);
        
        switch (ei.tipo) {

            case CElementoInfo.TE_TITULO:
                fila = getNextFilaTitulo(ei);
                break;

            case CElementoInfo.TE_SUBTITULO:
                fila = getNextFilaSubtitulo(ei);
                break;

            case CElementoInfo.TE_SECCION:
                fila = getNextFilaSeccion(ei);
                break;

            case CElementoInfo.TE_SEPARACION:
                fila = getNextFilaSeparacion();
                break;

            case CElementoInfo.TE_DATO:
            case CElementoInfo.TE_HTML:
            case CElementoInfo.TE_HUECO:
                
                fila = getNextFilaDatoHtmlHueco(indice_elemento_actual, estilo_impar, holder_num_elementos_añadidos);
                break;
        }
            
        return fila;
    }
    
    
    
    /**
     * Obtiene el código HTML para mostrar el info.
     *
     * @return                                  Código HTML del info
     */
    public StringBuilder toStringBuilder() {
        
        Table table = new Table();
        
        // Añadimos separaciones verticales para separar las columnas de los elementos del info
        if (usar_linea_separacion_columnas) {
            for (int i = 1; i <= numero_columnas_info - 1; i++) {
                int indice = i * 3 - 1;
                table.addSeparacionVertical(indice);
            }
        }
        
        CFila fila;
        
        int indice_elemento_actual = 0;
        boolean estilo_impar = true;
        
        do {
            if (l_elementos.size() <= indice_elemento_actual) {
                break;
            }
            
            CElementoInfo ei = l_elementos.get(indice_elemento_actual);

            Holder <Integer> holder_num_elementos_añadidos = new Holder();
            fila = getNextFila(indice_elemento_actual, estilo_impar, holder_num_elementos_añadidos);

            int num_elementos_añadidos = holder_num_elementos_añadidos.getValor();
            indice_elemento_actual += num_elementos_añadidos;

            if (fila != null) {
                table.addFila(fila);
            }

            if (ei.tipo != CElementoInfo.TE_SEPARACION) {
                if (ei.esTipoFilaEntera()) {
                    estilo_impar = true;
                } else {
                    estilo_impar = !estilo_impar;
                }
            }

        } while (fila != null);
        
        return table.toStringBuilder();
    }
    
    

    /**
     * Obtiene el código HTML para mostrar el info.
     * Esta función es similar a toStringBuilder(). Sólo cambia el tipo de salida.
     * 
     * @return                                  Código HTML del info
     */
    @Override
    public String toString() {
        
        StringBuilder s = toStringBuilder();
        return s.toString();
    }
    
    
    
    /**
     * Genera un info de pruebas.
     * 
     * @return                                  Info generado
     */
    public static Info generarInfoPruebas() {
        Info info = new Info();
        info.setNumeroColumnasInfo(2);
        info.setEspacioSeparacionColumnas("100pt");
        
        info.addTitulo("Datos del cliente", "03.258.286K");
        info.addSubtitulo("Actualizado a noviembre de 2015");
        info.addSeccion("Datos identificativos");
        info.add("Nombre", "Ramón");
        info.add("Apellidos", "Toledano Miura");
        info.add("Domicilio", "C/ Temprano Solís, 34 B");
        info.add("Localidad", "Mérida");
        info.add("Código postal", "06800");
        info.add();
        info.add("e-mail", "kocihod@sgmsa.com", "Este e-mail es provisional");
        
        info.addSeccion("Productos contratados");
        info.add("Cuenta corriente", "30010041854110299722");
        info.add("Plazo fijo", "30010041854130536789");
        info.addSeparacion();
        info.add("Préstamo 1", "300100418569503956406", "Préstamo dudoso");
        info.add("Préstamo 2", "300100418556467788668");

        return info;
    }

}
