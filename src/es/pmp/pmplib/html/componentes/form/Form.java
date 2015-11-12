/**
 * Form.java
 *
 * Creado el 06-nov-2015, 14:27:23
 */

package es.pmp.pmplib.html.componentes.form;

import es.pmp.pmplib.Cadenas;
import es.pmp.pmplib.html.base.ListaAtributos;
import es.pmp.pmplib.html.base.Tag;
import es.pmp.pmplib.tipos.general.CFecha;
import es.pmp.pmplib.tipos.general.CHora;
import java.util.ArrayList;
import java.util.List;

/**
 * Funciones para facilitar la generación de formularios HTML.
 * 
 * @author pmpreciado
 */
public class Form extends ListaAtributos {

    /** Posibles métodos de envío de los datos */
    public static final String METHOD_GET               = "get";
    public static final String METHOD_POST              = "post";
    
    public static final String METHOD_PREDETERMINADO    = METHOD_POST;
    

    /** Posibles tipos de codificación de los datos del formulario (sólo para método post) */
    public static final String ENCTYPE_URLENCODED   = "application/x-www-form-urlencoded";
    public static final String ENCTYPE_MULTIPART    = "multipart/form-data";
    public static final String ENCTYPE_TEXT         = "text/plain";
    
    /** Posibles valores para mostrar la respuesta recibida tras enviar el formulario */
    public static final String TARGET_BLANK         = "blank";
    public static final String TARGET_SELF          = "_self";
    public static final String TARGET_PARENT        = "_parent";
    public static final String TARGET_TOP           = "_top";
    
    
    /** Posibles posiciones que puede ocupar la etiqueta con respecto al campo */
    public static final int POS_ETIQUETA_IZQUIERDA  = 1;
    public static final int POS_ETIQUETA_ARRIBA     = 2;
    
    public static final int POS_ETIQUETA_PREDETERMINADA = POS_ETIQUETA_IZQUIERDA;
    
    
    /** Elementos del formulario */
    List <CElementoForm> l_elementos;
    
    
    /** Método del form (Form.METHOD_xxx) */
    String method;
    
    /** Action del form */
    String action;
    
    /** Lugar para mostrar la respuesta recibida tras enviar el formulario (Form.TARGET_xxx) */
    String target;
    
    /** Tipo de codificación de los datos del formulario */
    String enctype;
    
    /** Juego de caracteres permitido. Se puede usar más de uno, si se separa por espacios en blanco. Ejemplos: "UTF-8", "ISO-8859-15" ...*/
    String accept_charset;
    
   
    /** Posición de las etiquetas con respecto al campo (Form.POS_ETIQUETA_xxx) */
    int posicion_etiqueta;
    
    /** Número de columnas de campos que habrá en el formulario */
    int numero_columnas;
    
    
    /** Estilo del formulario */
    EstiloForm estilo;

    
    
    
    /**
     * Crea una instancia de la clase.
     */
    public Form() {
        l_elementos = new ArrayList();
        posicion_etiqueta = POS_ETIQUETA_PREDETERMINADA;
        numero_columnas = 1;
        estilo = EstiloForm.getEstiloPredeterminado();
        method = METHOD_PREDETERMINADO;
    }
    
    
    /**
     * Crea una instancia de la clase.
     * 
     * @param titulo                            Título del formulario
     */
    public Form(String titulo) {
        this();
        this.addTitulo(titulo);
    }
    

    /**
     * Establece el estilo del formulario.
     *
     * @param estilo                            Objeto de clase EstiloForm con el estilo a utilizar
     */
    public void setEstilo(EstiloForm estilo) {
        this.estilo = estilo;
    }


    /**
     * Obtiene el estilo del formulario.
     *
     * @return                                  Objeto de clase EstiloForm con el estilo utilizado
     */
    public EstiloForm getEstilo() {
        return estilo;
    }
    
    
    /**
     * Establece la posición de las etiquetas con respecto al campo (Form.POS_ETIQUETA_xxx).
     * 
     * @param posicion_etiqueta                 Posición de las etiquetas con respecto al campo (Form.POS_ETIQUETA_xxx)
     */
    public void setPosicionEtiqueta(int posicion_etiqueta) {
        this.posicion_etiqueta = posicion_etiqueta;
    }

    
    /**
     * Obtiene la posición de las etiquetas con respecto al campo (Form.POS_ETIQUETA_xxx).
     * 
     * @return                                  Posición de las etiquetas con respecto al campo (Form.POS_ETIQUETA_xxx)
     */
    public int getPosicionEtiqueta() {
        return posicion_etiqueta;
    }
    
    
    /**
     * Establece el número de columnas de campos que habrá en el formulario.
     * 
     * @param numero_columnas                 Número de columnas de campos que habrá en el formulario
     */
    public void setNumeroColumnas(int numero_columnas) {
        this.numero_columnas = numero_columnas;
    }

    
    /**
     * Obtiene el número de columnas de campos que habrá en el formulario.
     * 
     * @return                                  Número de columnas de campos que habrá en el formulario
     */
    public int getNumeroColumnas() {
        return numero_columnas;
    }
    
    
    /**
     * Establece el método del form.
     * 
     * @param method                            Método del form (Form.METHOD_xxx)
     */
    public void setMethod(String method) {
        this.method = method;
    }

    
    /**
     * Obtiene el método del form.
     * 
     * @return                                  Método del form (Form.METHOD_xxx)
     */
    public String getMethod() {
        return method;
    }
    
    
    /**
     * Establece el action del form.
     * 
     * @param action                            Action del form
     */
    public void setAction(String action) {
        this.action = action;
    }

    
    /**
     * Obtiene el action del form.
     * 
     * @return                                  Action del form
     */
    public String getAction() {
        return action;
    }
    
    
    /**
     * Establece el lugar para mostrar la respuesta recibida tras enviar el formulario.
     * 
     * @param target                            Lugar para mostrar la respuesta recibida tras enviar el formulario (Form.TARGET_xxx)
     */
    public void setTarget(String target) {
        this.target = target;
    }

    
    /**
     * Obtiene el lugar para mostrar la respuesta recibida tras enviar el formulario..
     * 
     * @return                                  Lugar para mostrar la respuesta recibida tras enviar el formulario (Form.TARGET_xxx)
     */
    public String getTarget() {
        return target;
    }

    
    /**
     * Establece el tipo de codificación de los datos del formulario.
     * 
     * @param enctype                           Tipo de codificación de los datos del formulario (Form.ENCTYPE_xxx)
     */
    public void setEncType(String enctype) {
        this.enctype = enctype;
    }

    
    /**
     * Obtiene el tipo de codificación de los datos del formulario.
     * 
     * @return                                  Tipo de codificación de los datos del formulario (Form.ENCTYPE_xxx)
     */
    public String getEncType() {
        return enctype;
    }

    
    /**
     * Establece el juego de caracteres permitido.
     * Se puede usar más de uno, si se separa por espacios en blanco.
     * Ejemplos de charsets: "UTF-8", "ISO-8859-15" ...
     * 
     * @param accept_charset                    Juego de caracteres permitido
     */
    public void setAcceptCharset(String accept_charset) {
        this.accept_charset = accept_charset;
    }

    
    /**
     * Obtiene el juego de caracteres permitido.
     * Ejemplos de charsets: "UTF-8", "ISO-8859-15" ...
     * 
     * @return                                  Juego de caracteres permitido
     */
    public String getAcceptCharset() {
        return accept_charset;
    }

    
    /**
     * Añade un elemento de tipo título al formulario.
     * El texto del título se guarda en el atributo "etiqueta".
     *
     * @param titulo                            Texto del título a añadir
     */
    public void addTitulo(String titulo) {
        
        CElementoForm elemento_form = new CElementoForm(CElementoForm.TE_TITULO);
        elemento_form.setTexto(titulo);
        l_elementos.add(elemento_form);
    }
    
    
    /**
     * Añade un elemento de tipo sección al formulario.
     * El texto del título se guarda en el atributo "etiqueta".
     * 
     * @param titulo                            Texto de la sección a añadir
     */
    public void addSeccion(String titulo) {
        
        CElementoForm elemento_form = new CElementoForm(CElementoForm.TE_SECCION);
        elemento_form.setTexto(titulo);
        l_elementos.add(elemento_form);
    }
    

    /**
     * Añade una separación horizontal al formulario.
     */
    public void addSeparacion() {
        
        CElementoForm elemento_form = new CElementoForm(CElementoForm.TE_SEPARACION);
        l_elementos.add(elemento_form);
    }
    
    
    
    /**
     * Añade un elemento de tipo HTML al formulario.
     * El elemento HTML se expande toda una fila.
     * El contenido HTML se guarda en el atributo "etiqueta".
     * 
     * @param html                              Contenido HTML
     */
    public void addHtmlFila(String html) {
        
        CElementoForm elemento_form = new CElementoForm(CElementoForm.TE_HTML_FILA);
        elemento_form.setTexto(html);
        
        l_elementos.add(elemento_form);
    }
    
    
    /**
     * Añade un elemento de tipo HTML al formulario.
     * El elemento HTML sólo ocupa el espacio de un campo (etiqueta + campo + explicación).
     * 
     * @param html                              Contenido HTML
     */
    public void addHtmlCampo(String html) {
        
        CElementoForm elemento_form = new CElementoForm(CElementoForm.TE_HTML_CAMPO);
        elemento_form.setTexto(html);
        elemento_form.setSinEtiqueta(true);
        
        l_elementos.add(elemento_form);
    }
    
    
    /**
     * Añade un elemento al formulario.
     *
     * @param campo                             Elemento a añadir
     */
    public void addElemento(CElementoForm campo) {
        l_elementos.add(campo);
    }
    
    
    /**
     * Añade un campo al formulario.
     *
     * @param etiqueta                          Etiqueta que acompaña al campo
     * @param campo                             Campo a añadir
     */
    public void addCampo(String etiqueta, FormItemBase campo) {
        CElementoForm elemento = new CElementoForm(CElementoForm.TE_CAMPO);
        elemento.setEtiqueta(etiqueta);
        elemento.setCampo(campo);
        
        l_elementos.add(elemento);
    }
    

    /**
     * Añade un campo de tipo texto al formulario.
     *
     * @param etiqueta                          Etiqueta que acompaña al campo
     * @param nombre                            Nombre del campo
     * @param valor                             Contenido inicial del campo (puede ser 'null')
     */
    public void addText(String etiqueta, String nombre, String valor) {
        InputText campo = new InputText(nombre, valor);
        CElementoForm elemento = new CElementoForm(CElementoForm.TE_CAMPO);
        elemento.setEtiqueta(etiqueta);
        elemento.setCampo(campo);
        
        l_elementos.add(elemento);
    }

    
    /**
     * Añade un campo de tipo texto al formulario.
     *
     * @param etiqueta                          Etiqueta que acompaña al campo
     * @param nombre                            Nombre del campo
     * @param valor                             Contenido inicial del campo
     * @param size                              Tamaño del campo
     * @param maxlength                         Número máximo de caracteres
     */
    public void addText(String etiqueta, String nombre, String valor, int size, int maxlength) {
        InputText campo = new InputText(nombre, valor, size, maxlength);
        CElementoForm elemento = new CElementoForm(CElementoForm.TE_CAMPO);
        elemento.setEtiqueta(etiqueta);
        elemento.setCampo(campo);
        
        l_elementos.add(elemento);
    }
    
    
    /**
     * Añade un campo de tipo texto al formulario.
     *
     * @param etiqueta                          Etiqueta que acompaña al campo
     * @param nombre                            Nombre del campo
     */
    public void addText(String etiqueta, String nombre) {
        String valor = "";
        addText(etiqueta, nombre, valor);
    }
    
    
    /**
     * Añade un campo de tipo texto al formulario.
     *
     * @param etiqueta                          Etiqueta que acompaña al campo
     * @param nombre                            Nombre del campo
     * @param size                              Tamaño del campo
     * @param maxlength                         Número máximo de caracteres
     */
    public void addText(String etiqueta, String nombre, int size, int maxlength) {
        String valor = "";
        addText(etiqueta, nombre, valor, size, maxlength);
    }
    
    
    /**
     * Añade un campo de tipo entero al formulario.
     *
     * @param etiqueta                          Etiqueta que acompaña al campo
     * @param nombre                            Nombre del campo
     * @param valor                             Contenido inicial del campo
     */
    public void addTextNumber(String etiqueta, String nombre, String valor) {
        InputNumber campo = new InputNumber(nombre, valor);
        
        CElementoForm elemento = new CElementoForm(CElementoForm.TE_CAMPO);
        elemento.setEtiqueta(etiqueta);
        elemento.setCampo(campo);
        
        l_elementos.add(elemento);
    }
    
    
    /**
     * Añade un campo de tipo entero al formulario.
     *
     * @param etiqueta                          Etiqueta que acompaña al campo
     * @param nombre                            Nombre del campo
     * @param valor                             Contenido inicial del campo
     */
    public void addTextNumber(String etiqueta, String nombre, int valor) {
        String s_valor = Integer.toString(valor);
        addTextNumber(etiqueta, nombre, s_valor);
    }
    
    
    /**
     * Añade un campo de tipo entero al formulario.
     *
     * @param etiqueta                          Etiqueta que acompaña al campo
     * @param nombre                            Nombre del campo
     * @param valor                             Contenido inicial del campo
     * @param size                              Tamaño del campo
     * @param maxlength                         Número máximo de caracteres
     */
    public void addTextNumber(String etiqueta, String nombre, String valor, int size, int maxlength) {
        InputNumber campo = new InputNumber(nombre, valor, size, maxlength);

        CElementoForm elemento = new CElementoForm(CElementoForm.TE_CAMPO);
        elemento.setEtiqueta(etiqueta);
        elemento.setCampo(campo);
        
        l_elementos.add(elemento);
    }
    
    
    /**
     * Añade un campo de tipo entero al formulario.
     *
     * @param etiqueta                          Etiqueta que acompaña al campo
     * @param nombre                            Nombre del campo
     */
    public void addTextNumber(String etiqueta, String nombre) {
        String valor = "";
        addTextNumber(etiqueta, nombre, valor);
    }
    
    
    /**
     * Añade un campo de tipo entero al formulario.
     *
     * @param etiqueta                          Etiqueta que acompaña al campo
     * @param nombre                            Nombre del campo
     * @param size                              Tamaño del campo
     * @param maxlength                         Número máximo de caracteres
     */
    public void addTextNumber(String etiqueta, String nombre, int size, int maxlength) {
        String valor = "";
        addTextNumber(etiqueta, nombre, valor, size, maxlength);
    }
    
    
    /**
     * Añade un campo de tipo fecha al formulario.
     * El campo añadido no está soportado por Internet Explorer ni Firefox.
     * Si está soportado por Chrome y Edge.
     * @see addTextDate(...) para el campo compatible.
     * 
     * @param etiqueta                          Etiqueta que acompaña al campo
     * @param nombre                            Nombre del campo
     * @param valor                             Contenido inicial del campo
     */
    public void addDate(String etiqueta, String nombre, CFecha valor) {
        
        String s_valor = valor.toString(CFecha.FF_AAAA_MM_DD, "-");
        InputDate campo = new InputDate(nombre, s_valor);
        
        CElementoForm elemento = new CElementoForm(CElementoForm.TE_CAMPO);
        elemento.setEtiqueta(etiqueta);
        elemento.setCampo(campo);
        
        l_elementos.add(elemento);
    }
    
    
    /**
     * Añade un campo de tipo fecha al formulario.
     * Se utiliza un campo input normal para recoger la fecha (en lugar de un campo "date", que está menos soportado).
     * El campo es compatible con todos los navegadores.
     * 
     * @param etiqueta                          Etiqueta que acompaña al campo
     * @param nombre                            Nombre del campo
     * @param valor                             Contenido inicial del campo (puede ser 'null')
     */
    public void addTextDate(String etiqueta, String nombre, CFecha valor) {
        String s_valor = null;
        if (valor != null) {
            s_valor = valor.toStringScr();
        }
        
        int size = 11;
        int maxlength = 10;
        addText(etiqueta, nombre, s_valor, size, maxlength);
    }
    
    
    /**
     * Añade un campo de tipo fecha al formulario.
     * Se utiliza un campo input normal para recoger la fecha (en lugar de un campo "date", que está menos soportado).
     * El campo es compatible con todos los navegadores.
     * 
     * @param etiqueta                          Etiqueta que acompaña al campo
     * @param nombre                            Nombre del campo
     */
    public void addTextDate(String etiqueta, String nombre) {
        CFecha valor = null;
        addTextDate(etiqueta, nombre, valor);
    }
    
    
    
    /**
     * Añade un campo de tipo hora al formulario.
     * Se utiliza un campo input normal para recoger la hora.
     * El campo es compatible con todos los navegadores.
     * 
     * @param etiqueta                          Etiqueta que acompaña al campo
     * @param nombre                            Nombre del campo
     * @param valor                             Contenido inicial del campo (puede ser 'null')
     */
    public void addTextHora(String etiqueta, String nombre, CHora valor) {
        
        String s_valor = null;
        if (valor != null) {
            s_valor = valor.toStringScr();
        }
        
        int size = 9;
        int maxlength = 8;
        addText(etiqueta, nombre, s_valor, size, maxlength);
    }
    
    
    /**
     * Añade un campo de tipo hidden al formulario.
     *
     * @param nombre                            Nombre del campo
     * @param valor                             Contenido inicial del campo
     */
    public void addHidden(String nombre, String valor) {
        InputHidden campo = new InputHidden(nombre, valor);
        
        CElementoForm elemento = new CElementoForm(CElementoForm.TE_CAMPO);
        elemento.setCampo(campo);
        
        l_elementos.add(elemento);
    }

    
    /**
     * Añade un campo de tipo textarea al formulario.
     *
     * @param etiqueta                          Etiqueta que acompaña al campo
     * @param nombre                            Nombre del campo
     * @param contenido                         Contenido inicial del campo
     */
    public void addTextArea(String etiqueta, String nombre, String contenido) {
        TextArea textarea = new TextArea(nombre, contenido);
        
        CElementoForm elemento = new CElementoForm(CElementoForm.TE_CAMPO);
        elemento.setEtiqueta(etiqueta);
        elemento.setCampo(textarea);
        
        l_elementos.add(elemento);
    }

    
    /**
     * Añade un campo de tipo textarea al formulario.
     *
     * @param etiqueta                          Etiqueta que acompaña al campo
     * @param nombre                            Nombre del campo
     */
    public void addTextArea(String etiqueta, String nombre) {
        String contenido = null;
        addTextArea(etiqueta, nombre, contenido);
    }
    
    
    /**
     * Añade un campo de tipo textarea al formulario.
     *
     * @param etiqueta                          Etiqueta que acompaña al campo
     * @param nombre                            Nombre del campo
     * @param contenido                         Contenido inicial del campo
     * @param cols                              Número de columnas
     * @param rows                              Número de filas
     * @param maxlength                         Número máximo de caracteres
     */
    public void addTextArea(String etiqueta, String nombre, String contenido, int cols, int rows, int maxlength) {
        TextArea textarea = new TextArea(nombre, contenido, cols, rows, maxlength);
        
        CElementoForm elemento = new CElementoForm(CElementoForm.TE_CAMPO);
        elemento.setEtiqueta(etiqueta);
        elemento.setCampo(textarea);
        
        l_elementos.add(elemento);
    }
    
    
    /**
     * Añade un campo de tipo textarea al formulario.
     *
     * @param etiqueta                          Etiqueta que acompaña al campo
     * @param nombre                            Nombre del campo
     * @param cols                              Número de columnas
     * @param rows                              Número de filas
     * @param maxlength                         Número máximo de caracteres
     */
    public void addTextArea(String etiqueta, String nombre, int cols, int rows, int maxlength) {
        String contenido = null;
        addTextArea(etiqueta, nombre, contenido, cols, rows, maxlength);
    }
    
    
    /**
     * Añade un campo de tipo select al formulario.
     *
     * @param etiqueta                          Etiqueta que acompaña al select
     * @param select                            Select a añadir
     */
    public void addSelect(String etiqueta, Select select) {

        CElementoForm elemento = new CElementoForm(CElementoForm.TE_CAMPO);
        elemento.setEtiqueta(etiqueta);
        elemento.setCampo(select);
        l_elementos.add(elemento);
    }
    
    
    /**
     * Añade un campo de tipo checkbox group al formulario.
     *
     * @param etiqueta                          Etiqueta que acompaña al select
     * @param checkboxgroup                     Checkbox group a añadir
     */
    public void addCheckboxGroup(String etiqueta, CheckboxGroup checkboxgroup) {

        CElementoForm elemento = new CElementoForm(CElementoForm.TE_CAMPO);
        elemento.setEtiqueta(etiqueta);
        elemento.setCampo(checkboxgroup);
        l_elementos.add(elemento);
    }
    
    
    /**
     * Añade un campo de tipo radio group al formulario.
     *
     * @param etiqueta                          Etiqueta que acompaña al select
     * @param radiogroup                        Radio group a añadir
     */
    public void addRadioGroup(String etiqueta, RadioGroup radiogroup) {

        CElementoForm elemento = new CElementoForm(CElementoForm.TE_CAMPO);
        elemento.setEtiqueta(etiqueta);
        elemento.setCampo(radiogroup);
        l_elementos.add(elemento);
    }
    
    
    /**
     * Añade un campo de tipo button al formulario.
     *
     * @param nombre                            Nombre del botón
     * @param texto                             Texto que aparecerá en el botón
     */
    public void addButton(String nombre, String texto) {
        InputButton campo = new InputButton(nombre, texto);
        
        CElementoForm elemento = new CElementoForm(CElementoForm.TE_CAMPO);
        elemento.setCampo(campo);
        elemento.setSinEtiqueta(true);
        
        l_elementos.add(elemento);
    }
    
    
    /**
     * Añade un campo de tipo button al formulario.
     *
     * @param texto                             Texto que aparecerá en el botón
     */
    public void addButton(String texto) {
        addButton(null, texto);
    }
    
    
    /**
     * Añade un campo de tipo reset al formulario.
     *
     * @param nombre                            Nombre del botón
     * @param texto                             Texto que aparecerá en el botón
     */
    public void addReset(String nombre, String texto) {
        InputReset campo = new InputReset(nombre, texto);
        
        CElementoForm elemento = new CElementoForm(CElementoForm.TE_CAMPO);
        elemento.setCampo(campo);
        elemento.setSinEtiqueta(true);
        
        l_elementos.add(elemento);
    }
    
    
    /**
     * Añade un campo de tipo reset al formulario.
     *
     * @param texto                             Texto que aparecerá en el botón
     */
    public void addReset(String texto) {
        addReset(null, texto);
    }
    
    
    /**
     * Añade un campo de tipo submit al formulario.
     *
     * @param nombre                            Nombre del botón
     * @param texto                             Texto que aparecerá en el botón
     */
    public void addSubmit(String nombre, String texto) {
        InputSubmit campo = new InputSubmit(nombre, texto);
        
        CElementoForm elemento = new CElementoForm(CElementoForm.TE_CAMPO);
        elemento.setCampo(campo);
        elemento.setSinEtiqueta(true);
        
        l_elementos.add(elemento);
    }
    
    
    /**
     * Añade un campo de tipo submit al formulario.
     *
     * @param texto                             Texto que aparecerá en el botón
     */
    public void addSubmit(String texto) {
        addSubmit(null, texto);
    }
    
    
    /**
     * Función para añadir de manera rápida una fila con botones al formulario.
     * Se usa un elemento de tipo GrupoElementosForm para ubicar los botones.
     * 
     * @param inc_submit                        'true' para añadir el botón de submit
     *                                          'false' para no añadirlo
     * @param inc_reset                         'true' para añadir el botón de reset
     *                                          'false' para no añadirlo
     */
    public void addFilaBotones(boolean inc_submit, boolean inc_reset) {
        
        GrupoElementosForm grupo = new GrupoElementosForm();
        
        if (inc_submit) {
            InputSubmit submit = new InputSubmit();
            CElementoForm elemento = new CElementoForm(CElementoForm.TE_CAMPO);
            elemento.setCampo(submit);
            elemento.setSinEtiqueta(true);
            grupo.addElemento(elemento);
        }
        
        if (inc_reset) {
            InputReset submit = new InputReset();
            CElementoForm elemento = new CElementoForm(CElementoForm.TE_CAMPO);
            elemento.setCampo(submit);
            elemento.setSinEtiqueta(true);
            grupo.addElemento(elemento);
        }
        
        CElementoForm elemento_grupo = new CElementoForm(CElementoForm.TE_FILA_BOTONES);
        
        elemento_grupo.setCampo(grupo);
        elemento_grupo.setSinEtiqueta(true);
        
        this.addElemento(elemento_grupo);
    }
    
    
    /**
     * Función para añadir de manera rápida una fila con un botón de submit en el formulario.
     * Se usa un elemento de tipo GrupoElementosForm para ubicar el botón.
     */
    public void addFilaSubmit() {
        boolean inc_submit = true;
        boolean inc_reset = false;
        addFilaBotones(inc_submit, inc_reset);
    }

    
    /**
     * Obtiene el último elemento añadido al formulario.
     * 
     * @return                                  Último elemento añadido al formulario
     *                                          'null' si no hay ninguno
     */
    public CElementoForm getUltimoElemento() {
        if (l_elementos.isEmpty()) {
            return null;
        }
        
        return l_elementos.get(l_elementos.size() - 1);
    }
    
    
    /**
     * Obtiene el último campo añadido al formulario.
     * 
     * @return                                  Último campo añadido al formulario
     *                                          'null' si no hay ninguno
     */
    public FormItemBase getUltimoCampo() {
        for (int i = l_elementos.size() - 1; i >= 0; i--) {
            CElementoForm e = l_elementos.get(i);
            FormItemBase campo = e.getCampo();
            if (campo != null) {
                return campo;
            }
            
        }
        return null;
    }
    
    /**
     * Obtiene el número de celdas que puede ocupar cada elemento del form.
     * Usualmente serán dos: etiqueta + campo.
     * 
     * @return                                  Número de celdas que puede ocupar cada elemento del form
     */
    int getNumeroCeldasPorElemento() {
        return 2;
    }

    
    /**
     * Establece en el tag del formulario los atributos HTML establecidos en la instancia.
     * 
     * @param tag_form                          Tag con el formulario
     */
    private void addAtributosTagForm(Tag tag_form) {
        if (!Cadenas.vacio(method)) {
            tag_form.addAtributo("method", method);
        }
        if (!Cadenas.vacio(action)) {
            tag_form.addAtributo("action", action);
        }
        if (!Cadenas.vacio(target)) {
            tag_form.addAtributo("target", target);
        }
        if (!Cadenas.vacio(enctype)) {
            tag_form.addAtributo("enctype", enctype);
        }
        if (!Cadenas.vacio(accept_charset)) {
            tag_form.addAtributo("accept_charset", accept_charset);
        }
    }
    
    
    /**
     * Genera la tabla interna usada para ubicar los elementos del formulario.
     * 
     * @return                                  Tag con la tabla
     */
    public Tag getTablaInterna() {
        
        Tag tag_table = new Tag("table");
        tag_table.setClase(estilo.clase_table);
        
        int columna_actual = 0;
        Tag tag_tr = new Tag("tr");
        
        for (int i = 0; i < l_elementos.size(); i++) {
            
            CElementoForm elemento = l_elementos.get(i);

            boolean abrir_fila = false;
            if (i == 0 || columna_actual == 0 || elemento.ocupaFilaCompleta()) {
                abrir_fila = true;
            }
            
            if (abrir_fila) {
                tag_tr = new Tag("tr");
            }
            
            StringBuilder html_td_elemento = elemento.getTdElemento(this);
            tag_tr.addContenido(html_td_elemento);
            
            boolean cerrar_fila = false;
            if (i >= l_elementos.size() - 1 || columna_actual >= numero_columnas - 1 || elemento.ocupaFilaCompleta()) {
                cerrar_fila = true;
            }
            
            if (cerrar_fila) {
                // Completamos con huecos la fila, si no llegaba hasta el final
                while (columna_actual < numero_columnas - 1) {
                    Tag tag_td = new Tag("td");
                    int colspan = getNumeroCeldasPorElemento();
                    tag_td.setAtributo("colspan", colspan);
                    tag_tr.addContenido(tag_td);
                    columna_actual++;
                }
                
                // Añadimos la fila a la tabla
                tag_table.addContenido(tag_tr);
                
                columna_actual = 0;
                tag_tr = new Tag("tr");
            }
            
            
            // Tras las filas de tipo título y sección, se puede incluir una separación (es opcional)
            if ((elemento.tipo == CElementoForm.TE_TITULO && estilo.inclur_separacion_tras_titulo) ||
                (elemento.tipo == CElementoForm.TE_SECCION && estilo.inclur_separacion_tras_seccion)) {
                String margen_superior = "0px";
                Tag tr_fila = elemento.getTrSeparacion(this, margen_superior);
                tag_table.addContenido(tr_fila);
            }
            
        }
        
        return tag_table;
    }
    
    
    /**
     * Genera el código HTML para representar el form.
     * Se utiliza una tabla para ordenar los componentes dentro del form.
     * 
     * @return                                  Código HTML generado
     */
    public StringBuilder toStringBuilder() {

        Tag tag_form = new Tag("form");
        
        // Establecemos en el tag los atributos de la instancia
        addAtributosTagForm(tag_form);
        Tag tag_tabla_interna = getTablaInterna();
        tag_form.addContenido(tag_tabla_interna);
        
        StringBuilder html = tag_form.toStringBuilder();
        return html;
    }
    
    
    /**
     * Obtiene el código HTML para mostrar el form.
     * Esta función es un alias de toStringBuilder().
     * 
     * @return                                  Código HTML del form
     */
    public String getHtml() {
        return toString();
    }
    
    
    /**
     * Obtiene el código HTML para mostrar el form.
     * Esta función es similar a toStringBuilder(). Sólo cambia el tipo de salida.
     *
     * @return                                  Código HTML del form
     */
    @Override
    public String toString() {
        StringBuilder s = toStringBuilder();
        return s.toString();
    }

    
    
    /**
     * Genera un form de pruebas.
     * 
     * @return                                  Info generado
     */
    public static Form generarFormPruebas() {
        Form form = new Form();
        form.setMethod(Form.METHOD_POST);

        form.addTitulo("Datos del usuario");
        
        // Datos personales
        form.addSeccion("Datos personales");
        
        form.addText("Nombre", "nombre", 30, 30);
        form.getUltimoCampo().setRequired(true);
        
        form.addText("Primer apellido", "apellido_1", 40, 40);
        form.addText("Segundo apellido", "apellido_2", 40, 40);
        form.addSeparacion();
  
        form.addText("DNI", "dni", 10, 9);
        form.getUltimoElemento().setExplicacion("Sin guiones");
        form.addText("Teléfono", "telefono", "924", 14, 16);
        
        form.addTextNumber("Edad", "edad", 1, 120);
        
        RadioGroup rg_sexo = new RadioGroup();
        InputRadio rd_hombre = new InputRadio("sexo", "h");
        rg_sexo.addRadio("Hombre", rd_hombre);
        InputRadio rd_mujer = new InputRadio("sexo", "m");
        rg_sexo.addRadio("Mujer", rd_mujer);
        form.addRadioGroup("Sexo", rg_sexo);
        
        // Domicilio
        form.addSeccion("Domicilio");
        Select select_tipo_via = new Select("tipo_via");
        select_tipo_via.addOpcion("Calle", "CL");
        select_tipo_via.addOpcion("Avenida", "AV");
        select_tipo_via.addOpcion("Paseo", "PS");
        select_tipo_via.addOpcion("Carretera", "CR");
        form.addSelect("Tipo de vía: ", select_tipo_via);
        
        InputText fld_calle = InputText.getInputText("calle");
        fld_calle.setPlaceHolder("Nombre de la calle, número, bloque, letra");
        fld_calle.setRequired(true);
        form.addCampo("Calle", fld_calle);
        
        form.addText("Localidad", "localidad", "Almendralejo");
        form.addText("Código postal", "cp", 6, 5);
        
        form.addHtmlFila("Recuerde <strong>revisar</strong> bien los datos");
        
        
        // Otros
        form.addSeccion("Otros datos de interés");
        form.addTextDate("Fecha de bautizo", "fecha_bautizo");
        CheckboxGroup cb_group = new CheckboxGroup();
        InputCheckbox cb_catolica = new InputCheckbox("religion", "1");
        InputCheckbox cb_judeismo = new InputCheckbox("religion", "2");
        InputCheckbox cb_islamismo = new InputCheckbox("religion", "3");
        InputCheckbox cb_budismo = new InputCheckbox("religion", "4");
        cb_group.addCheckbox("Católica", cb_catolica);
        cb_group.addCheckbox("Judeismo", cb_judeismo);
        cb_group.addCheckbox("Islamismo", cb_islamismo);
        cb_group.addCheckbox("Budismo", cb_budismo);
        form.addCheckboxGroup("Religón", cb_group);
        
        form.addTextArea("Comentarios", "comentarios", 40, 4, 200);
        form.getUltimoCampo().setPlaceHolder("Cualquier comentario que quiera hacer constar");
        
        // Botones
        form.addFilaBotones(true, true);

        return form;
    }

}
