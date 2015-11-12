/**
 * Table.java
 *
 * Creado el 23-oct-2015, 10:16:21
 */

package es.pmp.pmplib.html.componentes.table;

import es.pmp.pmplib.Arrays;
import es.pmp.pmplib.Cadenas;
import es.pmp.pmplib.html.base.HtmlBase;
import es.pmp.pmplib.html.base.ListaAtributos;
import es.pmp.pmplib.html.base.Tag;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * Clase para facilitar la generación de tablas HTML.
 * 
 * @author pmpreciado
 */
public class Table extends ListaAtributos {

    /** Texto predeterminado a mostrar cuando la tabla esté vacía */
    protected static final String TEXTO_TABLA_VACIA = "No hay elementos";
    
    
    /** Filas de la tabla */
    List <CFila> l_filas;
    
    /** Texto que aparecerá cuando la tabla esté vacía */
    String texto_tabla_vacia;
    
    
    /** Estilo de la tabla */
    EstiloTabla estilo;
    
    
    
    /**
     * Alineación horizontal general para todas las columnas de la tabla.
     * Si se ha establecido una alineación específica para una columna en concreto, esta última tendrá preferencia
     */
    HtmlBase.ALINEACION alineacion_general_columnas;
    
    /**
     * Alineación horizontal específica de cada columna. Tiene preferencia sobre la alineación general.
     */
    HashMap <Integer, HtmlBase.ALINEACION> hm_alineacion_columnas;

    /** Columnas ante las que hay separaciones verticales */
    Set <Integer> set_separaciones_verticales;

    /** Columnas que se van a resaltar */
    Set <Integer> set_columnas_resaltar;
    
    /** Columnas que se van a ocultar */
    Set <Integer> set_columnas_ocultar;
    
    
    /**
     * Crea una nueva instancia de la clase.
     */
    public Table() {
        texto_tabla_vacia = TEXTO_TABLA_VACIA;
        alineacion_general_columnas     = null;
        l_filas                         = new ArrayList <> ();
        hm_alineacion_columnas          = new HashMap <> ();
        set_separaciones_verticales     = new HashSet <> ();
        set_columnas_resaltar           = new HashSet <> ();
        set_columnas_ocultar            = new HashSet <> ();
        estilo = EstiloTabla.getEstiloPredeterminado();
    }
    
    
    
    /**
     * Establece el estilo de la tabla.
     *
     * @param estilo                            Objeto de clase EstiloTabla con el estilo a utilizar
     */
    public void setEstilo(EstiloTabla estilo) {
        this.estilo = estilo;
    }


    /**
     * Obtiene el estilo de la tabla.
     *
     * @return                                  Objeto de clase EstiloTabla con el estilo utilizado
     */
    public EstiloTabla getEstilo() {
        return estilo;
    }
    
    
    /**
     * Añade una fila al cuerpo de la tabla.
     *
     * @param fila                              Fila a añadir
     */
    public void addFila(CFila fila) {
        l_filas.add(fila);
    }
    

    /**
     * Añade una fila a la tabla. El tipo de fila es especificado.
     *
     * @param tipo_fila                         Tipo de fila (TipoFila.TF_xxx)
     * @param arr_celdas                        Contenido de las celdas de la fila
     */
    public void addFila(int tipo_fila, String ... arr_celdas) {
        CFila fila = new CFila(tipo_fila, arr_celdas);
        addFila(fila);
    }
    
    
    /**
     * Añade una fila al cuerpo de la tabla. El tipo de fila es especificado.
     *
     * @param tipo_fila                         Tipo de fila (TipoFila.TF_xxx)
     * @param l_celdas                          Contenido de las celdas de la fila
     */
    public void addFila(int tipo_fila, List <String> l_celdas) {
        CFila fila = new CFila(tipo_fila, l_celdas);
        addFila(fila);
    }
    
    
    /**
     * Añade una fila de datos al cuerpo de la tabla.
     *
     * @param arr_celdas                        String [] con el contenido de las celdas de la fila
     */
    public void addFila(String [] arr_celdas) {
        addFila(CFila.TF_DATOS, arr_celdas);
    }


    /**
     * Añade una fila de datos al cuerpo de la tabla.
     *
     * @param l_celdas                          Contenido de las celdas de la fila
     */
    public void addFila(List <String> l_celdas) {
        addFila(CFila.TF_DATOS, l_celdas);
    }
    
    
    /**
     * Añade una fila al cuerpo de la tabla en la posición determinada.
     *
     * @param pos                               Posición en la que añadir la fila (la primera es 0)
     * @param fila                              Fila a añadir
     */
    public void addFilaAt(int pos, CFila fila) {
        l_filas.add(pos, fila);
    }

    
    /**
     * Añade una fila de datos a la tabla en la posición determinada.
     *
     * @param pos                               Posición en la que añadir la fila (la primera es 0)
     * @param l_celdas                          Contenido de las celdas de la fila
     */
    public void addFilaAt(int pos, List <String> l_celdas) {
        CFila fila = new CFila(l_celdas);
        addFilaAt(pos, fila);
    }
    
    
    /**
     * Añade una fila al cuerpo de la tabla en la posición determinada.
     *
     * @param pos                               Posición en la que añadir la fila (la primera es 0)
     * @param tipo_fila                         Tipo de fila (CFila.TF_xxx)
     * @param l_celdas                          Contenido de las celdas de la fila
     */
    public void addFilaAt(int pos, int tipo_fila, List <String> l_celdas) {
        CFila fila = new CFila(tipo_fila, l_celdas);
        addFilaAt(pos, fila);
    }
    
    
    /**
     * Añade una fila de tipo título a la tabla.
     *
     * @param contenido                         Texto del título a añadir
     */
    public void addTitulo(String contenido) {
        String [] arr_contenido = {contenido};
        CFila fila = new CFila(CFila.TF_TITULO, arr_contenido);
        addFila(fila);
    }


    /**
     * Añade un título a la tabla, junto con el texto a la derecha.
     *
     * @param contenido                         Texto del título a añadir
     * @param texto_derecha                     Texto que acompañará al título, alineado a la derecha
     */
    public void addTitulo(String contenido, String texto_derecha) {
        
        String [] arr_contenido = {contenido};
        CFila fila = new CFila(CFila.TF_TITULO, arr_contenido);
        fila.setTextoDerecha(texto_derecha);
        addFila(fila);
    }


    /**
     * Añade un subtítulo a la tabla.
     *
     * @param subtitulo                         Texto del subtítulo a añadir
     */
    public void addSubtitulo(String subtitulo) {
        String [] celdas = {subtitulo};
        addFila(CFila.TF_SUBTITULO, celdas);
    }
    
    
    
    /**
     * Añade una fila de tipo sección al cuerpo de la tabla.
     *
     * @param contenido                         Texto de la sección
     */
    public void addSeccion(String contenido) {
        String [] arr_contenido = {contenido};
        CFila fila = new CFila(CFila.TF_SECCION, arr_contenido);
        addFila(fila);
    }
    
    
    /**
     * Añade una fila de tipo sección al cuerpo de la tabla.
     *
     * @param contenido                         Texto de la sección
     * @param texto_derecha                     Texto a la derecha de la sección
     */
    public void addSeccion(String contenido, String texto_derecha) {
        String [] arr_contenido = {contenido};
        CFila fila = new CFila(CFila.TF_SECCION, arr_contenido);
        fila.setTextoDerecha(texto_derecha);
        addFila(fila);
    }
    
    
    /**
     * Añade una fila de separación al cuerpo de la tabla.
     */
    public void addSeparacion() {
        CFila fila = new CFila(CFila.TF_SEPARACION);
        addFila(fila);
    }
    
    
    /**
     * Añade una fila de tipo datos al cuerpo de la tabla.
     * 
     * @param arr_contenido                     Contenido de las celdas
     */
    public void addDatos(String ... arr_contenido) {
        addFila(arr_contenido);
    }

    
    /**
     * Añade una fila de cabecera a la tabla.
     *
     * @param arr_celdas                        Contenido de las celdas de la cabecera
     */
    public void addCabecera(String ... arr_celdas) {
        addFila(CFila.TF_CABECERA, arr_celdas);
    }
    
    
    /**
     * Añade una fila de cabecera a la tabla.
     *
     * @param l_celdas                          Contenido de las celdas de la cabecera
     */
    public void addCabecera(List <String> l_celdas) {
        addFila(CFila.TF_CABECERA, l_celdas);
    }

    
    /**
     * Añade una fila de total a la tabla.
     *
     * @param arr_celdas                        Contenido de las celdas del total
     */
    public void addTotal(String ... arr_celdas) {
        addFila(CFila.TF_TOTAL, arr_celdas);
    }
    
    
    /**
     * Añade una fila de total a la tabla.
     *
     * @param l_celdas                          Contenido de las celdas del total
     */
    public void addTotal(List <String> l_celdas) {
        addFila(CFila.TF_TOTAL, l_celdas);
    }
    
    
    /**
     * Añade una fila con código HTML al cuerpo de la tabla.
     *
     * @param html                              Contenido de la fila en formato HTML
     */
    public void addHtml(String html) {
        String [] celdas = {html};
        CFila fila = new CFila(CFila.TF_HTML, celdas);
        l_filas.add(fila);
    }
    
    
    
    /**
     * Establece el contenido de una fila de la tabla, sustituyendo a la fila ya existente si la hubiera.
     * Si el índice de la fila celda supera el número de filas, la tabla es expandida.
     *
     * @param indice                            Índice de la fila dentro de la tabla (empezando en 0)
     * @param fila                              Nuevo contenido de la fila
     */
    public void setFilaAt(int indice, CFila fila) {
        while (getNumeroFilas() < indice - 1) {
            CFila fila_vacia = new CFila();
            addFila(fila_vacia);
        }

        CFila f = getFila(indice);
        l_filas.set(indice, fila);
    }
    
    
    /**
     * Establece el contenido de una fila de la tabla, sustituyendo a la fila ya existente si la hubiera.
     * Si el índice de la fila celda supera el número de filas, la tabla es expandida.
     *
     * @param indice                        Índice de la fila dentro de la tabla (empezando en 0)
     * @param fila                          Nuevo contenido de la fila
     */
    public void setFila(int indice, CFila fila) {
        while (getNumeroFilas() < indice - 1) {
            CFila fila_vacia = new CFila();
            addFila(fila_vacia);
        }

        CFila f = getFila(indice);
        l_filas.set(indice, fila);
    }
    

    /**
     * Añade o sustituye una celda al cuerpo de la tabla, permitiendo especificar la ubicación exacta.
     * Si la celda ya existía, es sustituida por la nueva.
     *
     * @param indice_fila                   Índice de la fila
     * @param indice_columna                Índice de la columna
     * @param celda                         Celda a añadir
     */
    public void setCelda(int indice_fila, int indice_columna, CCelda celda) {

        // La fila está fuera del rango actual
        if (indice_fila >= getNumeroFilas()) {
            CFila nueva_fila = new CFila();
            nueva_fila.setCelda(indice_columna, celda);
            setFila(indice_fila, nueva_fila);
        } else {
            CFila fila_existente = getFila(indice_fila);
            fila_existente.setCelda(indice_columna, celda);
        }
    }


    /**
     * Añade o sustituye una fila al cuerpo de la tabla, permitiendo especificar la ubicación exacta.
     *
     * @param indice_fila                   Índice de la fila
     * @param indice_columna                Índice de la columna
     * @param texto                         Texto de la celda
     */
    public void setCelda(int indice_fila, int indice_columna, String texto) {
        CCelda celda = new CCelda(texto);
        setCelda(indice_fila, indice_columna, celda);
    }
    
    
    /**
     * Elimina la fila dada por su índice.
     *
     * @param pos                               Posición de la fila a eliminar (la primera es 0)
     */
    public void removeFila(int pos) {
        l_filas.remove(pos);
    }

    
    /**
     * Recupera una fila de la tabla, dada por su índice.
     *
     * @param indice                            Índice de la fila a recuperar dentro de la tabla (empezando en 0)
                                          Si la fila no existeAtributo, retorna 'null'
     * 
     * @return                                  Objeto de clase CFila con la fila
     */
    public CFila getFila(int indice) {
        int nf = getNumeroFilas();
        if (indice < 0 || nf - 1 < indice) {
            return null;
        }
        CFila fila = l_filas.get(indice);
        return fila;
    }
    
    
    /**
     * Obtiene la última fila de la tabla.
     *
     * @return                              Objeto de TipoFila con la fila
     *                                      'null' si la tabla no contiene ninguna fila
     */
    public CFila getUltimaFila() {
        int indice = getNumeroFilas() - 1;
        if (indice < 0) {
            return null;
        }

        CFila ultima_fila = l_filas.get(indice);
        return ultima_fila;
    }
    
    
    /**
     * Recupera todas las filas de la tabla.
     *
     * @return                                  Filas de la tabla
     */
    public List <CFila> getFilas() {
        return l_filas;
    }
    
    
    /**
     * Obtiene el número de filas de la tabla.
     * Se incluyen las filas de todo tipo.
     *
     * @return                                  Número de filas de la tabla
     */
    public int getNumeroFilas() {
        int nf = Arrays.size(l_filas);
        return nf;
    }
    

    /**
     * Obtiene el número de filas del tipo dado que hay en la tabla.
     *
     * @param tipo_fila                         Tipo de fila a contar (CFila.TF_xxx)
     * 
     * @return                                  Número de filas del tipo dado en la tabla
     */
    public int getNumeroFilas(int tipo_fila) {

        int nf = Arrays.size(l_filas);
        int cont = 0;
        for (int i = 0; i < nf; i++) {
            CFila fila = getFila(i);
            if (fila.getTipo() == tipo_fila) cont++;
        }

        return cont;
    }
    
    
    /**
     * Obtiene el número de filas de datos y de tipo HTML del cuerpo de la tabla.
     *
     * @return                                  Número de filas de datos y de tipo HTML de la tabla
     */
    public int getNumeroFilasDatosYHtml() {

        int nd = getNumeroFilas(CFila.TF_DATOS);
        int nh = getNumeroFilas(CFila.TF_HTML);
        int cont = nd + nh;
        return cont;
    }
    
    
    
    /**
     * Obtiene el número de columnas de la tabla.
     * El número de columnas se calcula como mayor número de celdas entre todas las filas visibles.
     * A la hora de hacer el cálculo, se tiene en cuenta la posible expansión de la celda. 
     *
     * @return                                  Número de columnas de la tabla
     */
    public int getNumeroColumnas() {
        int max = 0;
        int num_filas = getNumeroFilas();
        for (int i = 0; i < num_filas; i++) {
            CFila fila = l_filas.get(i);
            if (fila.isOculta()) {
                continue;
            }
            
            List <CCelda> l_celdas = fila.getCeldas();
            
            int num_columnas_fila = 0;
            for (int j = 0; j < Arrays.size(l_celdas); j++) {
                
                if (esColumnaOculta(j)) {
                    continue;
                }
                
                CCelda celda = l_celdas.get(j);
                num_columnas_fila += celda.getExpandir();
            }
            
            if (num_columnas_fila > max) {
                max = num_columnas_fila;
            }
        }

        return max;
    }
    
    
    /**
     * Comprueba si la tabla está vacía.
     * Se considera vacía si no tiene filas.
     *
     * @return                                  'true' si la tabla está vacía
     *                                          'false' si no lo está
     */
    public boolean vacia() {
        int nf = getNumeroFilas();
        return nf == 0;
    }
    
    
    /**
     * Comprueba si la tabla tiene filas de datos o de tipo HTML.
     *
     * @return                                  'true' si la tabla tiene filas de datos o de tipo HTML
     *                                          'false' si no las tiene
     */
    public boolean hayFilasDatosOHtml() {
        int nf = getNumeroFilasDatosYHtml();
        return nf > 0;
    }
    
    
    
    /**
     * Borra todas las filas de la tabla, incluyendo el título y las cabeceras.
     */
    public void clear() {
        l_filas.clear();
    }
    
    
    /**
     * Establece la alineación horizontal general para todas las columnas de la tabla.
     * Si se ha establecido una alineación específica para una columna en concreto, esta última tendrá preferencia.
     *
     * @param alineacion                        Alineación a aplicar a todas las columnas (HtmlBase.ALINEACION)
     */
    public void setAlineacionGeneralColumnas(HtmlBase.ALINEACION alineacion) {

        alineacion_general_columnas = alineacion;
    }
    
    
    /**
     * Obtiene la alineación horizontal predeterminada para todas las columnas de la tabla.
     * Si se ha establecido una alineación específica para una columna en concreto, esta última tendrá preferencia.
     *
     * @return                                  Alineación a aplicar a todas las columnas (HtmlBase.ALINEACION)
     *                                          'null' si no ha sido establecida
     */
    public HtmlBase.ALINEACION getAlineacionGeneralColumnas() {

        return alineacion_general_columnas;
    }
    
    
    /**
     * Establece la alineación horizontal para una columna específica de la tabla.
     * La columna se identifica por el índice de la misma.
     * La alineación específica tiene preferencia sobre la alineación general.
     *
     * @param indice_columna                    Índice de la columna (la primera es 0)
     * @param alineacion                        Alineación a aplicar a la columna (HtmlBase.ALINEACION)
     */
    public void setAlineacionColumna(int indice_columna, HtmlBase.ALINEACION alineacion) {
        
        hm_alineacion_columnas.put(indice_columna, alineacion);
    }
    
    
    /**
     * Obtiene la alineación horizontal para una columna del cuerpo de la tabla.
     * La columna se identifica por el índice de la misma.
     * La alineación de la celda tiene preferencia sobre la alineación global de la columna.
     *
     * @param indice_columna                    Índice de la columna (la primera es 0)
     * 
     * @return                                  Alineación de la columna (HtmlBase.ALINEACION)
     *                                          'null' si no ha sido establecida
     */
    public HtmlBase.ALINEACION getAlineacionColumna(int indice_columna) {
        HtmlBase.ALINEACION alineacion = hm_alineacion_columnas.get(indice_columna);
        return alineacion;
    }
    

    /**
     * Obtiene la alineación horizontal efectiva para una columna del cuerpo de la tabla.
     * Si la columna tiene una alineación específica, será esa la que se use.
     * En caso contrario, se utilizará la alineación general de todas las columnas de la tabla.
     *
     * @param indice_columna                    Índice de la columna (la primera es 0)
     * 
     * @return                                  Alineación efectiva a aplicar a la columna (HtmlBase.ALINEACION)
     *                                          'null' si no ha sido establecida
     */
    public HtmlBase.ALINEACION getAlineacionEfectivaColumna(int indice_columna) {
        
        HtmlBase.ALINEACION alineacion_columna = getAlineacionColumna(indice_columna);
        if (alineacion_columna == null) {
            alineacion_columna = getAlineacionGeneralColumnas(); 
        }
        
        return alineacion_columna;
    }

    

    /**
     * Establece una fila que se va a resaltar.
     * La fila ya debe existir en la tabla.
     *
     * @param indice                            Índice de la fila a resaltar (la primera es 0)
     */
    public void resaltarFila(int indice) {
        if (indice >= getNumeroFilas()) {
            return;
        }
        CFila fila = getFila(indice);
        fila.setResaltada(true);
    }


    /**
     * Establece una columna que se va a resaltar.
     *
     * @param indice                            Índice de la columna a resaltar (la primera es 0)
     */
    public void resaltarColumna(int indice) {
        set_columnas_resaltar.add(indice);
    }


    /**
     * Verifica si una columna se va a resaltar a o no.
     *
     * @param indice                            Índice de la columna a comprobar
     * 
     * @return                                  'true' si la columna se debe resaltar
     *                                          'false' si no
     */
    public boolean esColumnaResaltada(int indice) {
        
        boolean es_columna_resaltada = set_columnas_resaltar.contains(indice);
        return es_columna_resaltada;
    }
    
    
    /**
     * Establece una columna que se va a ocultar.
     * La columna oculta afecta, tanto a la cabecera, como al cuerpo de la tabla.
     *
     * @param indice                            Índice de la columna a ocultar (la primera es 0)
     */
    public void ocultarColumna(int indice) {
        set_columnas_ocultar.add(indice);
    }

    
    /**
     * Establece las columnas que se van a ocultar, sustituyendo a el conjunto anterior.
     * Las columnas ocultas afectan, tanto a la cabecera, como al cuerpo de la tabla.
     *
     * @param indices                           Índices de las columnas a ocultar
     */
    public void ocultarColumnas(int ... indices) {
        
        for (int indice : indices) {
            set_columnas_ocultar.add(indice);
        }
    }
    
    
    /**
     * Verifica si una columna está oculta.
     *
     * @param indice                            Índice de la columna a comprobar
     * 
     * @return                                  'true' si la columna está oculta
     *                                          'false' si no
     */
    public boolean esColumnaOculta(int indice) {
        
        boolean es_columna_oculta = set_columnas_ocultar.contains(indice);
        return es_columna_oculta;
    }
    
    

    /**
     * Añade una separación vertical a la tabla.
     *
     * @param columna_posterior                 Número de columna posterior a la que se añadirá la separación
     * 
     */
    public void addSeparacionVertical(int columna_posterior) {
        set_separaciones_verticales.add(columna_posterior);
    }


    /**
     * Comprueba si, previa a la fila dada, se ha establecido una separación vertical.
     *
     * @param columna_posterior                 Columna posterior a la separación que se quiere consultar
     * 
     * @return                                  'true' si hay separación
     *                                          'false' si no
     */
    public boolean haySeparacionVertical(int columna_posterior) {
        boolean hay_separacion_vertical = set_separaciones_verticales.contains(columna_posterior);
        return hay_separacion_vertical;
    }
    

    /**
     * Ordena las filas de datos de la tabla, de acuerdo a la columna dada.
     * Las filas que no sean de datos, no son ordenadas -se quedan en la posición en la que estén-.
     * 
     * @param columna_orden                 Número de columna por la que se va a ordenar (empezando en 0)
     */
    public void ordenar(int columna_orden) {

        List <CFilaOrdenable> l_filas_ordenables = new ArrayList <> ();
        List <CFila> l_filas_ordenadas = new ArrayList <> ();
        
        for (int i = 0; i < Arrays.size(l_filas); i++) {
            CFila fila = l_filas.get(i);
            int pos_original = i;
            CFilaOrdenable fila_ordenar = new CFilaOrdenable(fila, pos_original, columna_orden);
            l_filas_ordenables.add(fila_ordenar);
        }
        
        // Obtenemos el nuevo orden
        Collections.sort(l_filas_ordenables);
        
        // Reorganizamos las filas de datos, en función del nuevo orden
        int num_filas = Arrays.size(l_filas);
        
        CFilaOrdenable fila_ordenar = new CFilaOrdenable();
        int indice_sig_fila_ordenada = 0;
        
        for (int i = 0; i < num_filas; i++) {
            CFila fila = l_filas.get(i);
            
            if (fila.tipo == CFila.TF_DATOS) {
                indice_sig_fila_ordenada = fila_ordenar.getPosSiguienteFilaDatos(l_filas_ordenables, indice_sig_fila_ordenada);
                if (indice_sig_fila_ordenada != -1) {
                    CFilaOrdenable siguiente_fila_datos = l_filas_ordenables.get(indice_sig_fila_ordenada);
                    l_filas_ordenadas.add(siguiente_fila_datos.fila);
                    indice_sig_fila_ordenada++;
                }
            } else {
                l_filas_ordenadas.add(fila);
            }
        }
        
        l_filas = l_filas_ordenadas;
    }

    

    /**
     * Obtiene el código HTML para generar todas las filas de la tabla.
     * 
     * @return                                  Código HTML de las filas de la tabla
     */
    private StringBuilder getHtmlFilas() {
        StringBuilder html_filas = new StringBuilder();
        
        boolean estilo_impar = true;
        
        for (int i = 0; i < Arrays.size(l_filas); i++) {
            CFila fila = l_filas.get(i);
            
            if (fila.isOculta()) {
                continue;
            }
            
            ListaAtributos lista_atributos_heredados = new ListaAtributos();
            
            StringBuilder html_fila = fila.getHtml(this, lista_atributos_heredados, estilo_impar);
            html_filas.append(html_fila);
            
            if (fila.tipo != CFila.TF_SEPARACION) {
                if (fila.tipo == CFila.TF_DATOS || fila.tipo == CFila.TF_HTML) {
                    estilo_impar = !estilo_impar;
                } else {
                    estilo_impar = true;
                }
            }
        }
        
        return html_filas;
    }
    
    
    /**
     * Obtiene el código HTML para representar una tabla sin filas de datos o sin filas HTML.
     * 
     * @return                                  Código HTML de las filas de la tabla sin datos
     */
    private StringBuilder getHtmlTablaSinDatos() {
        StringBuilder html = getHtmlFilas();
        
        Tag tag_tr = new Tag("tr");
        tag_tr.setAtributoClass(estilo.clase_tr_tabla_vacia);
        
        Tag tag_td = new Tag("td");
        tag_td.setAtributoClass(estilo.clase_td_tabla_vacia);
        
        int num_columnas = getNumeroColumnas();
        if (num_columnas > 1) {
            tag_td.setAtributo("colspan", num_columnas);
        }
        
        tag_td.setContenido(texto_tabla_vacia);
        tag_tr.setContenido(tag_td);
        
        html.append(tag_tr.toStringBuilder());
        return html;
    }
    
    
    
    /**
     * Obtiene el código HTML para mostrar la tabla.
     *
     * @return                                  Código HTML de la tabla
     */
    public StringBuilder toStringBuilder() {
        
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


    /**
     * Obtiene el código HTML para mostrar la tabla.
     * Esta función es un alias de toStringBuilder().
     * 
     * @return                                  Código HTML de la tabla
     */
    public String getHtml() {
        return toString();
    }
    
    
    /**
     * Obtiene el código HTML para mostrar la tabla.
     * Esta función es similar a toStringBuilder(). Sólo cambia el tipo de salida.
     *
     * @return                                  Código HTML de la tabla
     */
    @Override
    public String toString() {
        StringBuilder s = toStringBuilder();
        return s.toString();
    }
    
    
    
    /**
     * Genera una tabla de pruebas.
     * 
     * @return                                  Tabla generada
     */
    public static Table generarTablaPruebas() {
        Table t = new Table();
        
        t.addSeparacionVertical(0);
        t.setAlineacionGeneralColumnas(HtmlBase.ALINEACION.right);
        t.setAlineacionColumna(0, HtmlBase.ALINEACION.left);
        
        t.addTitulo("Cultivos en el mundo", "Año 2015");
        
        t.addSubtitulo("Datos en millones de toneladas");
        t.addCabecera("País", "Maíz", "Arroz", "Soja", "Trigo", "Centeno", "Berenjenas");

        t.addSeccion("América");
        t.addDatos("EEUU", "15323,52", "96454,69", "438,56", "8583,79", "912,90", "494,80");
        t.addDatos("Canadá", "774,82", "6971,89", "78,91", "912,97", "82,15", "9823,45");
        t.addDatos("México", "14,82", "81,10", "5678,19", "78128,93", "12,72", "6,92");
        
        t.addSeccion("Europa", "Datos incompletos");
        t.addDatos("España", "8428,59", "9203,75", "4879,77", "94582,66", "688,84", "51494,80");
        t.addDatos("Portugal", "728.13", "603,95");
        t.getUltimaFila().addHuecos(2);
        t.getUltimaFila().addCelda("8523,42");
        t.getUltimaFila().addCelda("86,04");
                
        t.addDatos("Italia", "898,59", "512,01", "449,77", "9672,66", "78,80", "512,12");
        t.addDatos("Francia", "913,12", "78,91", "415,12");
        t.getUltimaFila().getUltimaCelda().setExpandir(4);
        
        t.addSeccion("Asia");
        t.addDatos("China", "898,59", "512,01", "52,81", "9672,66", "78,80", "512,12");
        t.addDatos("India", "898,59", "512,01", "4869,16", "9672,66", "78,80", "512,12");
        
        t.addSeccion("África");
        t.addDatos("Congo", "898,59", "512,01", "271,92", "9672,14", "78,80", "512,12");
        t.addDatos("Sudáfrica", "898,59", "512,01", "4797,10", "1652,66", "78,80", "512,12");
        
        t.addTotal("TOTAL", "538798,55", "53512,85", "4532,17", "649672,66", "76758,23", "64674,12");

        return t;
    }

}


