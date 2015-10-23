/**
 * Tag.java
 *
 * Creado el 23-oct-2015, 12:16:54
 */

package es.pmp.pmplib.html.base;

import es.pmp.pmplib.Arrays;
import es.pmp.pmplib.Cadenas;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase para definir y generar etiquetas web genéricas.
 * 
 * @author pmpreciado
 */
public class Tag extends ListaAtributos {

    /** Cadena para identificar los atributos sin valor */
    private final String SIN_VALOR = "**SIN_VALOR**";

    /** Carácter para separar los elementos de la lista (por defecto, la coma) */
    private final String SEPARADOR = " ";

    /** Carácter usado como símbolo igual */
    private final String SIMBOLO_IGUAL = ": ";

    /** Carácter Encerrador predeterminado para los argumentos */
    private final String CARACTER_ENCERRADOR = "\"";

    

    /** Nombre de la etiqueta */
    String nombre;

    /**
     * Flag que indica que esta etiqueta tiene o no cierre. Si no tiene cierre, en la apertura se incluirá un "/" al final.
     * De manera predeterminada, tendrá cierre.
     */
    boolean tiene_cierre;


    /**
     * Contenido encerrado entre la etiquetas de apertura y la de cierre.
     * Es lo que va desde desde <tag> hasta </tag>.
     * Solo hace falta indicarlo si se va a usar la función getBloque().
     * El contenido puede ser una cadena (s_contenido) o bien otra etiqueta (contenido), excluyente entre sí.
     */
    String s_contenido;
    Tag contenido;



    /**
     * Crea la instancia de la clase.
     */
    public Tag() {
        nombre = "";
        super.setSeparador(SEPARADOR);
        super.setSimboloIgual(SIMBOLO_IGUAL);
        super.setCaracterEncerrador(CARACTER_ENCERRADOR);        
    }


    /**
     * Crea la instancia de la clase.
     *
     * @param nombre                        Nombre de la etiqueta
     */
    public Tag(String nombre) {
        this();
        this.nombre = nombre;
    }


    /**
     * Establece el nombre de la etiqueta.
     *
     * @param nombre                        Nombre de la etiqueta
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }



    /***************************************************************************
     * ALGUNOS ATRIBUTOS COMUNES
     **************************************************************************/


        /**
         * Establece el atributo "name".
         *
         * @param name                          Valor del atributo "name"
         */
        public void setName(String name) {
            this.set("name", name);
        }

        /**
         * Establece el atributo "id".
         *
         * @param id                            Valor del atributo "id"
         */
        public void setId(String id) {
            this.set("id", id);
        }


        /**
         * Establece el atributo "class".
         *
         * @param clase                         Valor del atributo "class"
         */
        public void setClass(String clase) {
            this.set("class", clase);
        }


        /**
         * Establece el atributo "class".
         * Esta función es equivalente a setClass(String).
         *
         * @param clase                         Valor del atributo "class"
         */
        public void setClase(String clase) {
            setClass(clase);
        }


        /**
         * Establece el atributo "src".
         *
         * @param src                           Valor del atributo "src"
         */
        public void setSrc(String src) {
            this.set("src", src);
        }


        /**
         * Establece el atributo "title".
         *
         * @param title                         Valor del atributo "title"
         */
        public void setTitle(String title) {
            this.set("title", title);
        }


        /**
         * Establece el atributo "value".
         *
         * @param value                         Valor del atributo "value"
         */
        public void setValue(String value) {
            this.set("value", value);
        }


        /**
         * Establece el atributo "width".
         *
         * @param width                         Valor del atributo "width"
         */
        public void setWidth(String width) {
            this.set("width", width);
        }


        /**
         * Establece el atributo "height".
         *
         * @param height                        Valor del atributo "height"
         */
        public void setHeight(String height) {
            this.set("height", height);
        }


        /**
         * Establece el atributo "size".
         *
         * @param size                          Valor del atributo "size"
         */
        public void setSize(String size) {
            this.set("size", size);
        }


        /**
         * Establece el atributo "maxlength".
         *
         * @param maxlength                     Valor del atributo "maxlength"
         */
        public void setMaxlength(String maxlength) {
            this.set("maxlength", maxlength);
        }


        /**
         * Establece el atributo "readonly".
         */
        public void setReadonly() {
            this.set("readonly");
        }


        /**
         * Establece el atributo "disabled".
         */
        public void setDisabled() {
            this.set("disabled");
        }





    /**************************************************************************/

    
    /**
     * Establece el flag que indica que esta etiqueta tiene o no cierre.
     * Si no tiene cierre, en la apertura se incluirá un "/" al final.
     *
     * @param tiene_cierre                  Flag que indica que esta etiqueta tiene o no cierre
     */
    public void setTieneCierre(boolean tiene_cierre) {
        this.tiene_cierre = tiene_cierre;
    }


    /**
     * Establece el s_contenido encerrado entre la etiquetas de apertura y la de cierre.
     * Es lo que va desde desde <tag> hasta </tag>.
     * Solo hace falta indicarlo si se va a usar la función getBloque()
     *
     * @param contenido                     Contenido encerrado entre la etiquetas de apertura y la de cierre
     *
     * @see getBloque()
     */
    public void setContenido(Tag contenido) {
        this.contenido = contenido;
        this.s_contenido = null;
    }


    /**
     * Establece el s_contenido encerrado entre la etiquetas de apertura y la de cierre.
     * Es lo que va desde desde <tag> hasta </tag>.
     * Solo hace falta indicarlo si se va a usar la función getBloque()
     *
     * @param s_contenido                   Contenido encerrado entre la etiquetas de apertura y la de cierre
     *
     * @see getBloque()
     */
    public void setContenido(String s_contenido) {
        this.s_contenido = s_contenido;
        this.contenido = null;
    }


    /**
     * Establece el s_contenido encerrado entre la etiquetas de apertura y la de cierre.
     * Es lo que va desde desde <tag> hasta </tag>.
     * Solo hace falta indicarlo si se va a usar la función getBloque()
     *
     * @param s_contenido                   Contenido encerrado entre la etiquetas de apertura y la de cierre
     *
     * @see getBloque()
     */
    public void setContenido(StringBuilder s_contenido) {
        setContenido(s_contenido.toString());
    }


    /**
     * Comprueba si se han definido atributos para esta etiqueta, sin incluir el estilo.
     *
     * @return                              'true' si se han definido atributos para esta etiqueta
     *                                      'false' en caso contrario
     */
    public boolean hayAtributos() {
        if (Arrays.size(l_atributos) > 0) return true;
        return false;
    }



    /**
     * Obtiene la cadena con la etiqueta HTML de apertura y los atributos.
     *
     * @return                              Cadena HTML, por ejemplo:
     *                                          <td class="titulo" style="font-size: 12px; background-color: red;">\n
     */
    public String getEtiquetaApertura() {
        if (l_atributos.isEmpty()) {
            return "";
        }

        StringBuilder s = new StringBuilder();
        s.append("<");
        s.append(nombre);

        String cadena_atributos = super.getCadenaAtributos();
        if (cadena_atributos.length() > 0) {
            s.append(" ");
        }
        s.append(cadena_atributos);

        if (!tiene_cierre) {
            s.append(" /");
        }

        s.append(">");

        return s.toString();
    }


    /**
     * Obtiene la cadena con la etiqueta HTML de cierre.
     *
     * @return                              Cadena HTML, por ejemplo:
     *                                          </td>\n
     */
    public String getEtiquetaCierre() {
        String s = "</" + nombre + ">\n";
        return s;
    }


    /**
     * Obtiene el bloque compuesto por la etiqueta de apertura, el s_contenido entre <tag> y </tag> y la etiqueta de cierre.
     *
     * @return                              Cadena compuesta por:
     *                                      <tag>
     *                                          s_contenido
     *                                      </tag>
     *
     * @see setContenido(String)
     */
    public String getBloque() {

        StringBuilder s = new StringBuilder();

        // Etiqueta de apertura
        s.append(getEtiquetaApertura());

        // Contenido interno
        if (contenido != null) {
            s.append(contenido.getBloque());
        } else if (s_contenido != null) {
            s.append(s_contenido);
        }

        // Etiqueta de cierre
        s.append(getEtiquetaCierre());

        return s.toString();
    }


    /**
     * Obtiene el bloque compuesto por la etiqueta de apertura, el s_contenido entre <tag> y </tag> y la etiqueta de cierre.
     * Esta función es un alias de getBloque().
     *
     * @return                              Cadena compuesta por:
     *                                      <tag>
     *                                          s_contenido
     *                                      </tag>
     */
    @Override
    public String toString() {
        return getBloque();
    }
}
