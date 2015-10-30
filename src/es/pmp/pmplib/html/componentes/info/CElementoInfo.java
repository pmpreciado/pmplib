/**
 * CElementoInfo.java
 *
 * Creado el 29-oct-2015, 14:44:49
 */

package es.pmp.pmplib.html.componentes.info;

import es.pmp.pmplib.Comun;
import es.pmp.pmplib.html.base.Style;

/**
 * Clase para definir un elemento de un objeto Info.
 * 
 * @author pmpreciado
 */
public class CElementoInfo {

    
    /** Tipos de elemento */
    public static final int TE_DATO              = 1;
    public static final int TE_TITULO            = 2;
    public static final int TE_SUBTITULO         = 3;
    public static final int TE_SECCION           = 4;
    public static final int TE_SEPARACION        = 5;
    public static final int TE_HTML              = 6;
    public static final int TE_HUECO             = 7;
    
    /** Tipo de elemento predeterminado */
    public static final int TE_PREDETERMINADO = TE_DATO;
    
    
    /** Tipo de elemento (CElementoInfo.TE_xxx) */
    int tipo;
    
    /** Etiqueta */
    String etiqueta;
    
    /** Valor */
    String valor;
    
    /** Explicación */
    String explicacion;

    /** Estilo personalizado del TD con la etiqueta */
    Style style_td_etiqueta;
    
    /** Estilo personalizado del TD con el valor */
    Style style_td_valor;

    /** Estilo personalizado del TD con la explicación */
    Style style_td_explicacion;

    
    /** 
     * Para elementos de tipo TE_DATO.
     * Flag que indica que el elemento no tiene valor. Sólo consta de una etiqueta que se expande al espacio del valor.
     */
    boolean etiqueta_expandida;
    
    /** 
     * Para elementos de tipo TE_DATO.
     * Flag que indica que el valor de este elemento se debe expandir hasta el final de la fila.
     */
    boolean expandir_hasta_final;

    
    
    /**
     * Crea una instancia de la clase.
     */
    public CElementoInfo() {
        tipo = TE_PREDETERMINADO;
        etiqueta_expandida = false;
    }
    
    
    /**
     * Crea una instancia de la clase, usando el tipo suministrado.
     * 
     * @param tipo                              Tipo de elemento (CElementoInfo.TE_xxx) 
     */
    public CElementoInfo(int tipo) {
        this();
        this.tipo = tipo;
    }
    

    /**
     * Crea una instancia de la clase.
     * Es inicializada con el tipo dato, usando una etiqueta que se expande al espacio del valor.
     * 
     * @param etiqueta                          Etiqueta
     */
    public CElementoInfo(String etiqueta) {
        this();
        this.etiqueta = etiqueta;
        etiqueta_expandida = true;
    }
    
    
    /**
     * Crea una instancia de la clase.
     * Es inicializada con el tipo dato, usando una etiqueta y un valor dados.
     * 
     * @param etiqueta                          Etiqueta
     * @param valor                             Valor
     */
    public CElementoInfo(String etiqueta, String valor) {
        this();
        this.etiqueta = etiqueta;
        this.valor = valor;
    }
    
    
    
    
    /**
     * Establece la etiqueta del elemento.
     * 
     * @param etiqueta                          Etiqueta
     */
    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }
    
    
    /**
     * Obtiene la etiqueta del elemento.
     * 
     * @return                                  Etiqueta
     */
    public String getEtiqueta() {
        return etiqueta;
    }
    
    
    /**
     * Establece el valor del elemento.
     * 
     * @param valor                             Valor
     */
    public void setValor(String valor) {
        this.valor = valor;
    }
    
    
    /**
     * Obtiene el valor del elemento.
     * 
     * @return                                  Valor
     */
    public String getValor() {
        return valor;
    }
    
    
    /**
     * Establece la explicación del elemento.
     * 
     * @param explicacion                       Explicación
     */
    public void setExplicacion(String explicacion) {
        this.explicacion = explicacion;
    }
    
    
    /**
     * Obtiene la explicación del elemento.
     * 
     * @return                                  Explicación
     */
    public String getExplicacion() {
        return explicacion;
    }
    
    
    /**
     * Establece un estilo personalizado para el TD con la etiqueta.
     * 
     * @param style_td_etiqueta                 Estilo personalizado para el TD con la etiqueta
     */
    public void setStyleTdEtiqueta(Style style_td_etiqueta) {
        this.style_td_etiqueta = style_td_etiqueta;
    }
    
    
    /**
     * Obtiene el estilo personalizado para el TD con la etiqueta.
     * 
     * @return                                  Estilo personalizado para el TD con la etiqueta
     */
    public Style getStyleTdEtiqueta() {
        return style_td_etiqueta;
    }
    
    
    /**
     * Establece un estilo personalizado para el TD con el valor.
     * 
     * @param style_td_valor                    Estilo personalizado para el TD con el valor
     */
    public void setStyleTdValor(Style style_td_valor) {
        this.style_td_valor = style_td_valor;
    }
    
    
    /**
     * Obtiene el estilo personalizado para el TD con el valor.
     * 
     * @return                                  Estilo personalizado para el TD con el valor
     */
    public Style getStyleTdValor() {
        return style_td_valor;
    }
    
    /**
     * Establece un estilo personalizado para el TD con la explicación.
     * 
     * @param style_td_explicacion                 Estilo personalizado para el TD con la explicación
     */
    public void setStyleTdExplicacion(Style style_td_explicacion) {
        this.style_td_explicacion = style_td_explicacion;
    }
    
    /**
     * Obtiene el estilo personalizado para el TD con la explicación.
     * 
     * @return                                  Estilo personalizado para el TD con la explicación
     */
    public Style getStyleTdExplicacion() {
        return style_td_explicacion;
    }
    
    
    
    /** 
     * Para elementos de tipo TE_DATO.
     * Establece si la etiqueta de este elemento se debe expandir al espacio del valor.
     * 
     * @param etiqueta_expandida                'true' para expandir la etiqueta  al espacio del valor
     *                                          'false' para no hacerlo
     */
    public void setEtiquetaExpandida(boolean etiqueta_expandida) {
        this.etiqueta_expandida = etiqueta_expandida;
    }
    
    
    /** 
     * Para elementos de tipo TE_DATO.
     * Establece si el valor de este elemento se debe expandir hasta el final de la fila.
     * 
     * @param expandir_hasta_final              'true' para expandir el valor de este elemento se debe expandir hasta el final de la fila
     *                                          'false' para no hacerlo
     */
    public void setExpandirHastaFinal(boolean expandir_hasta_final) {
        this.expandir_hasta_final = expandir_hasta_final;
    }
    
    
    
    /**
     * Obtiene una cadena con la información de la instancia.
     * 
     * @return                                  Cadena de texto generada
     */
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("");
        
        s.append("etiqueta: " + etiqueta + Comun.NL);
        s.append("valor: " + valor + Comun.NL);
        s.append("explicacion:" + explicacion + Comun.NL);

        return s.toString();
    }
}
