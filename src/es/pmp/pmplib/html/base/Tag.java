/**
 * Tag.java
 *
 * Creado el 23-oct-2015, 12:16:54
 */

package es.pmp.pmplib.html.base;

import es.pmp.pmplib.Arrays;
import es.pmp.pmplib.Comun;
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
    private final String SIMBOLO_IGUAL = "=";

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
     * Solo hace falta indicarlo si se va a usar la función toStringBuilder().
     * El contenido puede estar formado por texto, etiquetas Tag o ambos.
     */
    List <Object> l_contenido;


    /**
     * Crea la instancia de la clase.
     */
    public Tag() {
        nombre = "";
        super.setSeparador(SEPARADOR);
        super.setSimboloIgual(SIMBOLO_IGUAL);
        super.setCaracterEncerrador(CARACTER_ENCERRADOR);   
        
        tiene_cierre = true;
        
        l_contenido = new ArrayList <> ();
    }


    /**
     * Crea la instancia de la clase.
     *
     * @param nombre                            Nombre de la etiqueta
     */
    public Tag(String nombre) {
        this();
        this.nombre = nombre;
    }


    /**
     * Crea la instancia de la clase.
     *
     * @param nombre                            Nombre de la etiqueta
     * @param tiene_cierre                      Flag que indica que esta etiqueta tiene o no cierre
     */
    public Tag(String nombre, boolean tiene_cierre) {
        this();
        this.nombre = nombre;
        this.tiene_cierre = tiene_cierre;
    }
    
    
    /**
     * Establece el nombre de la etiqueta.
     *
     * @param nombre                            Nombre de la etiqueta
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
            this.setAtributo("name", name);
        }

        /**
         * Establece el atributo "id".
         *
         * @param id                            Valor del atributo "id"
         */
        public void setId(String id) {
            this.setAtributo("id", id);
        }


        /**
         * Establece el atributo "class".
         *
         * @param clase                         Valor del atributo "class"
         */
        public void setClass(String clase) {
            this.setAtributo("class", clase);
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
            this.setAtributo("src", src);
        }


        /**
         * Establece el atributo "title".
         *
         * @param title                         Valor del atributo "title"
         */
        public void setTitle(String title) {
            this.setAtributo("title", title);
        }


        /**
         * Establece el atributo "value".
         *
         * @param value                         Valor del atributo "value"
         */
        public void setValue(String value) {
            this.setAtributo("value", value);
        }


        /**
         * Establece el atributo "width".
         *
         * @param width                         Valor del atributo "width"
         */
        public void setWidth(String width) {
            this.setAtributo("width", width);
        }


        /**
         * Establece el atributo "height".
         *
         * @param height                        Valor del atributo "height"
         */
        public void setHeight(String height) {
            this.setAtributo("height", height);
        }


        /**
         * Establece el atributo "size".
         *
         * @param size                          Valor del atributo "size"
         */
        public void setSize(String size) {
            this.setAtributo("size", size);
        }


        /**
         * Establece el atributo "maxlength".
         *
         * @param maxlength                     Valor del atributo "maxlength"
         */
        public void setMaxlength(String maxlength) {
            this.setAtributo("maxlength", maxlength);
        }


        /**
         * Establece el atributo "readonly".
         */
        public void setReadonly() {
            this.setAtributo("readonly");
        }


        /**
         * Establece el atributo "disabled".
         */
        public void setDisabled() {
            this.setAtributo("disabled");
        }





    /**************************************************************************/

    
    /**
     * Establece el flag que indica que esta etiqueta tiene o no cierre.
     * Si no tiene cierre, en la apertura se incluirá un "/" al final.
     *
     * @param tiene_cierre                      Flag que indica que esta etiqueta tiene o no cierre
     */
    public void setTieneCierre(boolean tiene_cierre) {
        this.tiene_cierre = tiene_cierre;
    }

    
    /**
     * Obtiene el flag que indica que esta etiqueta tiene o no cierre.
     *
     * @return                                  'true' si la etiqueta tiene cierre
     *                                          'false' si no lo tiene
     */
    public boolean tieneCierre() {
        return tiene_cierre;
    }
    

    /**
     * Establece el contenido encerrado entre la etiquetas de apertura y la de cierre.
     * Es lo que va desde desde <tag> hasta </tag>.
     *
     * @param contenido                         Contenido encerrado entre la etiquetas de apertura y la de cierre
     *
     * @see #toStringBuilder()
     */
    public void setContenido(Tag contenido) {
        clearContenido();
        this.l_contenido.add(contenido);
    }


    /**
     * Establece el contenido encerrado entre la etiquetas de apertura y la de cierre.
     * Es lo que va desde desde <tag> hasta </tag>.
     *
     * @param contenido                         Contenido encerrado entre la etiquetas de apertura y la de cierre
     *
     * @see #toStringBuilder()
     */
    public void setContenido(String contenido) {
        clearContenido();
        this.l_contenido.add(contenido);
    }


    /**
     * Establece el contenido encerrado entre la etiquetas de apertura y la de cierre.
     * Es lo que va desde desde <tag> hasta </tag>.
     *
     * @param contenido                         Contenido encerrado entre la etiquetas de apertura y la de cierre
     *
     * @see #toStringBuilder()
     */
    public void setContenido(StringBuilder contenido) {
        clearContenido();
        this.l_contenido.add(contenido);
    }


    /**
     * Añade contenido encerrado entre la etiquetas de apertura y la de cierre.
     * Es lo que va desde desde <tag> hasta </tag>.
     *
     * @param contenido                         Contenido encerrado entre la etiquetas de apertura y la de cierre
     *
     * @see #toStringBuilder()
     */
    public void addContenido(Tag contenido) {
        this.l_contenido.add(contenido);
    }
    
    
    /**
     * Añade contenido encerrado entre la etiquetas de apertura y la de cierre.
     * Es lo que va desde desde <tag> hasta </tag>.
     *
     * @param contenido                         Contenido encerrado entre la etiquetas de apertura y la de cierre
     *
     * @see #toStringBuilder()
     */
    public void addContenido(String contenido) {
        this.l_contenido.add(contenido);
    }
    
    
    /**
     * Añade contenido encerrado entre la etiquetas de apertura y la de cierre.
     * Es lo que va desde desde <tag> hasta </tag>.
     *
     * @param contenido                         Contenido encerrado entre la etiquetas de apertura y la de cierre
     *
     * @see #toStringBuilder()
     */
    public void addContenido(StringBuilder contenido) {
        this.l_contenido.add(contenido);
    }
    
    
    /**
     * Elimina el contenido del tag.
     */
    public void clearContenido() {
        l_contenido.clear();
    }
    
    
    /**
     * Comprueba si se han definido atributos para esta etiqueta, sin incluir el estilo.
     *
     * @return                                  'true' si se han definido atributos para esta etiqueta
     *                                          'false' en caso contrario
     */
    public boolean hayAtributos() {
        if (Arrays.size(l_atributos) > 0) return true;
        return false;
    }



    /**
     * Obtiene la cadena con la etiqueta HTML de apertura y los atributos.
     *
     * @return                                  Cadena HTML, por ejemplo:
     *                                              <td class="titulo" style="font-size: 12px; background-color: red;">\n
     */
    public String getEtiquetaApertura() {
        
        StringBuilder s = new StringBuilder();
        s.append("<");
        s.append(nombre);

        String cadena_atributos = super.getCadenaAtributos();
        if (cadena_atributos.length() > 0) {
            s.append(" ");
            s.append(cadena_atributos);
        }

        if (!tiene_cierre) {
            s.append(" /");
        }

        s.append(">");

        return s.toString();
    }


    /**
     * Obtiene la cadena con la etiqueta HTML de cierre.
     * Si la etiqueta no tiene cierre, retorna la cadena vacía.
     *
     * @return                              Cadena HTML, por ejemplo:
     *                                          </td>\n
     */
    public String getEtiquetaCierre() {
        String s = "";
        if (tiene_cierre) {
            s = "</" + nombre + ">";
        }
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
    public StringBuilder toStringBuilder() {

        StringBuilder s = new StringBuilder();

        // Etiqueta de apertura
        s.append(getEtiquetaApertura());
        s.append(Comun.NL);
        
        // Contenido interno
        for (int i = 0; i < l_contenido.size(); i++) {
            
            Object contenido = (Object) l_contenido.get(i);
            if (contenido instanceof Tag) {
                Tag tag = (Tag) contenido;
                s.append(tag.toStringBuilder());
            } else if (contenido instanceof String) {
                String s_contenido = (String) contenido;
                s.append(s_contenido);
            } else if (contenido instanceof StringBuilder) {
                StringBuilder s_contenido = (StringBuilder) contenido;
                s.append(s_contenido);
            }
        }
        

        // Etiqueta de cierre
        s.append(getEtiquetaCierre());
        s.append(Comun.NL);
        
        return s;
    }


    /**
     * Obtiene el bloque compuesto por la etiqueta de apertura, el s_contenido entre <tag> y </tag> y la etiqueta de cierre.
     *
     * @return                              Cadena compuesta por:
     *                                      <tag>
     *                                          s_contenido
     *                                      </tag>
     */
    @Override
    public String toString() {
        return toStringBuilder().toString();
    }
}
