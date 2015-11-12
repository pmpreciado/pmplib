/**
 * EstiloForm.java
 *
 * Creado el 10-nov-2015, 12:36:48
 */

package es.pmp.pmplib.html.componentes.form;

/**
 * Atributos que definen el estilo del form.
 * 
 * @author pmpreciado
 */
public class EstiloForm {

    public String clase_table;
    public String clase_td_titulo;
    public String clase_td_seccion;
    public String clase_td_campo_etiqueta;
    public String clase_td_campo;
    public String clase_td_campo_explicacion;
    public String clase_td_html;
    public String clase_table_separacion;
    public String clase_table_botones;
    
    
    public boolean inclur_separacion_tras_titulo;
    public boolean inclur_separacion_tras_seccion;
    
    
    /**
     * Crea una instancia de la clase, y la inicializa con valores predeterminados.
     */
    public void EstiloForm() {
        EstiloForm estilo_predeterminado = getEstiloPredeterminado();
        setEstilo(estilo_predeterminado);
    }

    
    /**
     * Crea una instancia de la clase, y la inicializa a partir del estilo pasado por parámetro.
     * 
     * @param estilo                            Estilo a partir cual se inicializará esta instancia
     */
    public void EstiloForm(EstiloForm estilo) {
        setEstilo(estilo);
    }
    
    
    /**
     * Establece el estilo a utilizar a partir del estipo pasado por parámetro.
     * 
     * @param estilo                            Estilo a establecer
     */
    public void setEstilo(EstiloForm estilo) {
        this.clase_table                            = estilo.clase_table;
        this.clase_td_titulo                        = estilo.clase_td_titulo;
        this.clase_td_seccion                       = estilo.clase_td_seccion;
        this.clase_td_campo_etiqueta                = estilo.clase_td_campo_etiqueta;
        this.clase_td_campo                         = estilo.clase_td_campo;
        this.clase_td_campo_explicacion             = estilo.clase_td_campo_explicacion;
        this.clase_td_html                          = estilo.clase_td_html;
        this.clase_table_separacion                 = estilo.clase_table_separacion;
        this.clase_table_botones                    = estilo.clase_table_botones;
        this.inclur_separacion_tras_titulo          = estilo.inclur_separacion_tras_titulo;
        this.inclur_separacion_tras_seccion         = estilo.inclur_separacion_tras_seccion;
    }

    
    
    
    
    /**
     * Crea un estilo por defecto.
     * 
     * @return                                  Clase con el estilo creado
     */
    public static EstiloForm getEstiloPredeterminado() {
        EstiloForm estilo = new EstiloForm();
        estilo.clase_table                          = "form_table";
        estilo.clase_td_titulo                      = "form_td_titulo";
        estilo.clase_td_seccion                     = "form_td_seccion";
        estilo.clase_td_campo_etiqueta              = "form_td_campo_etiqueta";
        estilo.clase_td_campo                       = "form_td_campo";
        estilo.clase_td_campo_explicacion           = "form_td_campo_explicacion";
        estilo.clase_td_html                        = "form_td_html";
        estilo.clase_table_separacion               = "form_table_separacion";
        estilo.clase_table_botones                  = "form_table_botones";

        estilo.inclur_separacion_tras_titulo        = true;
        estilo.inclur_separacion_tras_seccion       = true;

        return estilo;
    }
    
}
