/**
 * CElementoForm.java
 *
 * Creado el 06-nov-2015, 14:34:44
 */

package es.pmp.pmplib.html.componentes.form;

import es.pmp.pmplib.Comun;
import es.pmp.pmplib.html.base.Tag;

/**
 * Clase para definir un elemento de un objeto Form.
 * 
 * @author pmpreciado
 */
public class CElementoForm {

    /** Tipos de elemento */
    public static final int TE_TITULO           = 1;
    public static final int TE_SECCION          = 2;
    public static final int TE_CAMPO            = 3;
    public static final int TE_HTML_CAMPO       = 4;
    public static final int TE_HTML_FILA        = 5;
    public static final int TE_GRUPO_ELEMENTOS  = 6;
    public static final int TE_FILA_BOTONES     = 7;
    public static final int TE_SEPARACION       = 8;
    
    
    
    /** Tipo de elemento */
    public int tipo;
    
    /** Etiqueta asociada al elemento */
    private String etiqueta;
    
    /** Campo */
    private FormItemBase campo;
    
    /** Para algunos tipos de campo, texto o cadena HTML alternativos al campo */
    private String texto;
    
    /** Explicación */
    private String explicacion;
    
    /** Flag que indica que este elemento del form no llevará etiqueta ni explicación */
    private boolean sin_etiqueta;
    
    
    /**
     * Crea una instancia de la clase.
     */
    public CElementoForm() {
        sin_etiqueta = false;
    }
        
    
    /**
     * Crea una instancia de la clase.
     *
     * @param tipo                              Tipo de este elemento
     */
    public CElementoForm(int tipo) {
        this();
        this.tipo = tipo;
    }
    
    
    /**
     * Obtiene el tipo de este elemento.
     *
     * @return                                  Tipo de este elemento
     */
    public int getTipo() {
        return tipo;
    }
    
    
    /**
     * Establece el tipo de este elemento.
     *
     * @param tipo                              Tipo de este elemento
     */
    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
    
    
    /**
     * Obtiene la etiqueta asociada al elemento.
     * 
     * @return                                  Etiqueta asociada al elemento
     */
    public String getEtiqueta() {
        return etiqueta;
    }

    /**
     * Establece la etiqueta asociada al elemento.
     * 
     * @param etiqueta                          Etiqueta asociada al elemento
     */
    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }
    

    /**
     * Obtiene el campo asociado al elemento.
     * 
     * @return                                  Campo asociado al elemento
     */
    public FormItemBase getCampo() {
        return campo;
    }

    
    /**
     * Establece el valor asociado al elemento.
     * 
     * @param campo                             Campo asociado al elemento
     */
    public void setCampo(FormItemBase campo) {
        this.campo = campo;
    }
    
    
    /**
     * Establece el texto asociado al elemento, cuando el campo está representado por un texto o cadena HTML alternativos al campo.
     * 
     * @return                                  Texto asociado al campo
     */
    public String getTexto() {
        return texto;
    }
    
    
    /**
     * Establece el texto asociado al elemento, cuando el campo está representado por un texto o cadena HTML alternativos al campo.
     * 
     * @param texto                             Texto asociado al campo
     */
    public void setTexto(String texto) {
        this.texto = texto;
    }
    
    
    /**
     * Obtiene la explicacion asociada al elemento.
     * 
     * @return                                  Explicacion asociada al elemento
     */
    public String getExplicacion() {
        return explicacion;
    }

    
    /**
     * Establece la explicación asociada al elemento.
     * Con HTML5 está disponible el atributo del input "placeholder" que permite establecer una explicación de un campo de formulario de manera elegante.
     * 
     * @param explicacion                       Explicación asociada al elemento
     */
    public void setExplicacion(String explicacion) {
        this.explicacion = explicacion;
    }
    
    
    /**
     * Establece si este elemento del form debe llevar o no etiqueta ni explicación.
     * Si no llevara etiqueta ni explicación, se utilizará este espacio para el campo.
     * Ejemplos de campos que normalmente no tendrán etiqueta son los de tipo "input button" o los TE_HTML_CAMPO.
     * 
     * @param sin_etiqueta                      'true' para que no lleve etiqueta ni explicación
     *                                          'false' para que si lo lleve
     */
    public void setSinEtiqueta(boolean sin_etiqueta) {
        this.sin_etiqueta = sin_etiqueta;
    }
    
    /**
     * Comprueba si este elemento del form debe llevar o no etiqueta ni explicación.
     * Si no llevara etiqueta ni explicación, se utilizará este espacio para el campo.
     * Ejemplos de campos que normalmente no tendrán etiqueta son los de tipo "input button" o los TE_HTML_CAMPO.
     * 
     * @return                                  'true' si el campo no debe llevar etiqueta ni explicación
     *                                          'false' si sí los llevará
     */
    public boolean getSinEtiqueta() {
        return sin_etiqueta;
    }    
    
    
    
    /**
     * Comprueba si este tipo de elemento ocupa una fila completa dentro del formulario.
     * 
     * @return                                  'true' si este tipo de elemento ocupa una fila completa dentro del formulario
     *                                          'false' en caso contrario
     */
    public boolean ocupaFilaCompleta() {
        switch (tipo) {
            case TE_TITULO:
            case TE_SECCION:
            case TE_HTML_FILA:
            case TE_GRUPO_ELEMENTOS:
            case TE_FILA_BOTONES:
            case TE_SEPARACION:
                return true;
        }
        
        return false;
    }
    
    
    /**
     * Genera el código HTML de un elemento del formulario de tipo título.
     * 
     * @param form                              Formulario que contiene el elemento
     * 
     * @return                                  Tag generado
     */
    private Tag getTdTitulo(Form form) {
        Tag tag_td = new Tag("td");
        tag_td.setClass(form.estilo.clase_td_titulo);
      
        int numero_columnas_form = form.getNumeroColumnas();
        int colspan = numero_columnas_form * form.getNumeroCeldasPorElemento();
        tag_td.addAtributo("colspan", colspan);
        
        tag_td.setContenido(texto);
        return tag_td;
    }
    
    
    /**
     * Genera el código HTML de un elemento del formulario de tipo sección.
     * 
     * @param form                              Formulario que contiene el elemento
     * 
     * @return                                  Tag generado
     */
    private Tag getTdSeccion(Form form) {
        Tag tag_td = new Tag("td");
        tag_td.setClass(form.estilo.clase_td_seccion);
        
        int numero_columnas_form = form.getNumeroColumnas();
        int colspan = numero_columnas_form * form.getNumeroCeldasPorElemento();
        tag_td.addAtributo("colspan", colspan);
        
        tag_td.setContenido(texto);
        return tag_td;
    }
    
    
    /**
     * Genera el código HTML de un elemento del formulario de tipo campo.
     * 
     * @param form                              Formulario que contiene el elemento
     * 
     * @return                                  Código HTML generado
     *                                          El contenido va entre etiquetas TD
     */
    StringBuilder getHtmlCampo(Form form) {
        
        StringBuilder html = new StringBuilder();
        
        // Etiqueta
        if (!sin_etiqueta) {
            Tag tag_td_etiqueta = new Tag("td");
            tag_td_etiqueta.setClass(form.estilo.clase_td_campo_etiqueta);
            tag_td_etiqueta.setContenido(etiqueta);
            StringBuilder s_tag_td_etiqueta = tag_td_etiqueta.toStringBuilder();
            html.append(s_tag_td_etiqueta);
        }
        
        // Campo y explicación en una tabla aparte
        Tag tag_table_contenedora = Tag.getTagTable();
            tag_table_contenedora.setClass("nodeco");
            Tag tag_tr = tag_table_contenedora.add(Tag.getTagTr());
            
                // Campo
                Tag tag_td_campo = Tag.getTagTd();
                    tag_td_campo.setClass(form.estilo.clase_td_campo);
                    if (texto != null) {
                        tag_td_campo.setContenido(texto);
                    } else {
                        StringBuilder html_campo = campo.getHtml();
                        tag_td_campo.setContenido(html_campo);
                    }
                    tag_tr.add(tag_td_campo);
                    
                // Explicación
                if (!sin_etiqueta) {
                    Tag tag_td_explicacion = Tag.getTagTd();
                        tag_td_explicacion.setClass(form.estilo.clase_td_campo_explicacion);
                        tag_td_explicacion.setContenido(explicacion);
                    //StringBuilder s_tag_td_explicacion = tag_td_explicacion.toStringBuilder();
                    //html.append(s_tag_td_explicacion);
                    
                    tag_tr.add(tag_td_explicacion);
                }

        Tag tag_td_campo_y_explicacion = new Tag("td");
            tag_td_campo_y_explicacion.setClass("nodeco");
            tag_td_campo_y_explicacion.add(tag_table_contenedora);
        

        StringBuilder s_tag_td_campo_y_explicacion = tag_td_campo_y_explicacion.toStringBuilder();
        html.append(s_tag_td_campo_y_explicacion);
        
        return html;
    }
    
    
    /**
     * Genera el TD de un elemento del formulario de tipo HTML.
     * Este tipo de elemento ocupa el espacio de la etiqueta, el propio campo y la explicación.
     * 
     * @param form                              Formulario que contiene el elemento
     * 
     * @return                                  Tag generado
     */
    private Tag getTdHtmlCampo(Form form) {
        Tag tag_td = new Tag("td");
        tag_td.setClass(form.estilo.clase_td_html);

        int colspan = form.getNumeroCeldasPorElemento();
        tag_td.addAtributo("colspan", colspan);
        
        tag_td.setContenido(texto);
        return tag_td;
    }
    
    
    /**
     * Genera el código HTML de un elemento del formulario de tipo HTML que ocupa toda una fila.
     * 
     * @param form                              Formulario que contiene el elemento
     * 
     * @return                                  Tag generado
     */
    private Tag getTdHtmlFila(Form form) {
        Tag tag_td = new Tag("td");
        tag_td.setClass(form.estilo.clase_td_html);
        
        int numero_columnas_form = form.getNumeroColumnas();
        int colspan = numero_columnas_form * form.getNumeroCeldasPorElemento();
        tag_td.addAtributo("colspan", colspan);
        
        tag_td.setContenido(texto);
        return tag_td;
    }
    
    
    /**
     * Genera el código HTML de un elemento del formulario que, a su vez, agrupa un conjunto de elemnentos.
     * Los elementos del grupo se muestran en la misma fila.
     * 
     * @param form                              Formulario que contiene el elemento
     * 
     * @return                                  Tag generado
     */
    private Tag getTdGrupoElementos(Form form) {
        Tag tag_td = new Tag("td");
        
        int numero_columnas_form = form.getNumeroColumnas();
        int colspan = numero_columnas_form * form.getNumeroCeldasPorElemento();
        tag_td.addAtributo("colspan", colspan);
        
        // Tabla interior, para cada elemento del grupo
        GrupoElementosForm grupo_elementos = (GrupoElementosForm) campo;
        Tag tag_table_grupo_elementos = grupo_elementos.getTagTable(form);
        StringBuilder html_grupo_elementos = tag_table_grupo_elementos.toStringBuilder();
        
        tag_td.setContenido(html_grupo_elementos);
        return tag_td;
    }
    

    /**
     * Genera el código HTML de un elemento del formulario que agrupa un conjunto de botones.
     * Este tipo de elemento es apropiado para el botón de submit, reset, etc.
     * Hace uso del componente GrupoElementos genérico.
     * 
     * @param form                              Formulario que contiene el elemento
     * 
     * @return                                  Tag generado
     */
    private Tag getTdGrupoBotones(Form form) {
        Tag tag_td = new Tag("td");
        
        int numero_columnas_form = form.getNumeroColumnas();
        int colspan = numero_columnas_form * form.getNumeroCeldasPorElemento();
        tag_td.addAtributo("colspan", colspan);
        
        // Tabla interior, para cada elemento del grupo
        GrupoElementosForm grupo_elementos = (GrupoElementosForm) campo;
        Tag tag_table_grupo_elementos = grupo_elementos.getTagTable(form);
        tag_table_grupo_elementos.setClase(form.estilo.clase_table_botones);
        
        tag_td.setContenido(tag_table_grupo_elementos);
        return tag_td;
    }
    
    
    /**
     * Genera el código HTML de una fila (TR) para separación.
     * 
     * @param form                              Formulario que contiene el elemento
     * @param margen_superior                   Margen superior a utilizar
     *                                          Si es 'null', se ignora
     * 
     * @return                                  Tag generado
     */
    public Tag getTrSeparacion(Form form, String margen_superior) {
        Tag tag_tr = new Tag("tr");
        StringBuilder td_separacion = getTdSeparacion(form, margen_superior);
        tag_tr.setContenido(td_separacion);
        
        return tag_tr;
    }
    
    
    /**
     * Genera el código HTML de un elemento del formulario de tipo separación.
     * 
     * @param form                              Formulario que contiene el elemento
     * @param margen_superior                   Margen superior a utilizar
     *                                          Si es 'null', se ignora
     * 
     * @return                                  Código HTML generado
     *                                          El contenido va entre etiquetas TD
     */
    private StringBuilder getTdSeparacion(Form form, String margen_superior) {
        Tag tag_td = new Tag("td");
        
        int numero_columnas_form = form.getNumeroColumnas();
        int colspan = numero_columnas_form * form.getNumeroCeldasPorElemento();
        tag_td.addAtributo("colspan", colspan);

        Tag tag_table = new Tag("table");
        tag_table.setClass("form_table_separacion");
        if (margen_superior != null) {
            tag_table.setStyle("margin-top: " + margen_superior);
        }
        tag_table.addContenido("<tr><td></td></tr>");

        tag_td.setContenido(tag_table);
        
        StringBuilder html = tag_td.toStringBuilder();
        return html;
    }
    
    

    /**
     * Genera el código HTML del TD que contiene al elemento actual.
     * 
     * @param form                              Formulario que contiene el elemento
     * 
     * @return                                  Código HTML del TD
     */
    StringBuilder getTdElemento(Form form) {
        
        StringBuilder html = new StringBuilder();
        
        switch (tipo) {
            case TE_TITULO:
                html = getTdTitulo(form).toStringBuilder();
                break;
                
            case TE_SECCION:
                html = getTdSeccion(form).toStringBuilder();
                break;
                
            case TE_CAMPO:
                html = getHtmlCampo(form);
                break;
                
            case TE_HTML_CAMPO:
                html = getTdHtmlCampo(form).toStringBuilder();
                break;
                
            case TE_HTML_FILA:
                html = getTdHtmlFila(form).toStringBuilder();
                break;
                
            case TE_GRUPO_ELEMENTOS:
                html = getTdGrupoElementos(form).toStringBuilder();
                break;

            case TE_FILA_BOTONES:
                html = getTdGrupoBotones(form).toStringBuilder();;
                break;
                
            case TE_SEPARACION:
                String margen_superior = null;
                html = getTdSeparacion(form, margen_superior);
                break;
        }

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
        
        s.append("Tipo de elemento: " + tipo + Comun.NL);
        s.append("Etiqueta: " + etiqueta + Comun.NL);
        s.append("Campo: " + campo.getName() + Comun.NL);
        s.append("Texto alternativo: " + texto + Comun.NL);
        s.append("Explicación: " + explicacion + Comun.NL);
        

        return s.toString();
    }
    
}