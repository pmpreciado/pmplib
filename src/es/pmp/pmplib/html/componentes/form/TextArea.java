/**
 * TextArea.java
 *
 * Creado el 09-nov-2015, 13:44:51
 */

package es.pmp.pmplib.html.componentes.form;

import es.pmp.pmplib.Cadenas;
import es.pmp.pmplib.html.base.Tag;

/**
 * Define un elemento del formulario de tipo "textarea".
 * Desde HTML5 este campo también soporta la propiedad "maxlength"
 * 
 * @author pmpreciado
 */
public class TextArea extends FormItemBase {
    
    private static final String TYPE = "textarea";
    
    /** Atributos de uso común para los input de tipo textarea */
    private static final String ATR_COLS        = "cols";
    private static final String ATR_ROWS        = "rows";
    
    
    /** Contenido del textarea */
    String contenido;
    
    
    
    /**
     * Crea una instancia de la clase.
     */
    public TextArea() {
        super(TYPE);
    }
    
    
    /**
     * Crea una instancia de la clase, inicializada con los parámetros de entrada.
     * 
     * @param nombre                            Nombre del campo
     * @param contenido                         Contenido inicial del textarea
     */
    public TextArea(String nombre, String contenido) {
        this();
        setName(nombre);
        setValue(contenido);
    }
    
    
    /**
     * Crea una instancia de la clase, inicializada con los parámetros de entrada.
     * 
     * @param nombre                            Nombre del campo
     * @param contenido                         Contenido inicial del textarea
     * @param cols                              Número de columnas
     * @param rows                              Número de filas
     */
    public TextArea(String nombre, String contenido, int cols, int rows) {
        this(nombre, contenido);
        setCols(cols);
        setRows(rows);
    }

    
    /**
     * Crea una instancia de la clase, inicializada con los parámetros de entrada.
     * 
     * @param nombre                            Nombre del campo
     * @param contenido                         Contenido inicial del textarea
     * @param cols                              Número de columnas
     * @param rows                              Número de filas
     * @param maxlength                         Número máximo de caracteres
     */
    public TextArea(String nombre, String contenido, int cols, int rows, int maxlength) {
        this(nombre, contenido, cols, rows);
        super.setMaxLength(maxlength);
    }
    
    
    /**
     * Establece el atributo "cols", con el número de columnas.
     * 
     * @param cols                              Número de columnas
     */
    public void setCols(int cols) {
        setAtributo(ATR_COLS, cols);
    }
    
    
    /**
     * Obtiene el valor del atributo "cols", con el número de columnas.
     * 
     * @return                                  Número de columnas
     */
    public int getCols() {
        int cols = super.getValorAtributoInt(ATR_COLS);
        return cols;
    }
    
    
    /**
     * Establece el atributo "rows", con el número de filas.
     * 
     * @param rows                              Número de filas
     */
    public void setRows(int rows) {
        setAtributo(ATR_ROWS, rows);
    }
    
    
    /**
     * Obtiene el valor del atributo "rows", con el número de filas.
     * 
     * @return                                  Número de filas
     */
    public int getRows() {
        int rows = super.getValorAtributoInt(ATR_ROWS);
        return rows;
    }
    
    
    /**
     * Establece el contenido del textarea.
     * 
     * @param contenido                         Contenido del textarea
     */
    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    
    /**
     * Obtiene el contenido del textarea.
     * 
     * @return                                  Contenido del textarea
     */
    public String getContenido() {
        return contenido;
    }
    
    
    /**
     * Obtiene el código HTML para definir el control.
     *
     * @return                                  String con el código HTML del control
     */
    @Override
    public StringBuilder getHtml() {
        
        Tag tag = new Tag("textarea");
        tag.addAtributos(this);
        if (!Cadenas.vacio(contenido)) {
            tag.addContenido(contenido);
        }
        
        StringBuilder s_tag = tag.toStringBuilder();
        return s_tag;
    }
    
    
    
    /**
     * Obtiene el código HTML para definir el control.
     *
     * @return                                  String con el código HTML del control
     */
    @Override
    public String toString() {
        StringBuilder html = getHtml();
        return html.toString();
    }

}
