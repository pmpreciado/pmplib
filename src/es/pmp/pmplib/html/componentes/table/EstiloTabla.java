/**
 * EstiloTabla.java
 *
 * Creado el 26-oct-2015, 11:00:34
 */

package es.pmp.pmplib.html.componentes.table;


/**
 * Atributos que definen el estilo de la tabla.
 * 
 * @author pmpreciado
 */
public class EstiloTabla {

    public static final String COLOR_AMARILLO                       = "#FBB03F";
    public static final String COLOR_AZUL_MUY_CLARO                 = "#A8B4E6";
    public static final String COLOR_MARRON                         = "#CE9C6F";
    
    public static final String SEPARACION_VERTICAL_ANCHO            = "1pt";
    public static final String SEPARACION_VERTICAL_COLOR_CABECERA   = COLOR_AZUL_MUY_CLARO;
    public static final String SEPARACION_VERTICAL_COLOR_DATOS      = COLOR_AMARILLO;
    public static final String SEPARACION_VERTICAL_COLOR_TOTAL      = COLOR_MARRON;
    
    
    public String clase_table;
    public String clase_tr_titulo;
    public String clase_td_titulo;
    public String clase_td_titulo_derecha;
    public String clase_tr_subtitulo;
    public String clase_td_subtitulo;
    public String clase_tr_cabecera;
    public String clase_td_cabecera;
    public String clase_tr_seccion;
    public String clase_td_seccion;
    public String clase_td_seccion_derecha;
    public String clase_tr_separacion;
    public String clase_tr_datos_impar;
    public String clase_tr_datos_par;
    public String clase_tr_datos_impar_resaltada;
    public String clase_tr_datos_par_resaltada;
    public String clase_td_datos;
    public String clase_tr_total;
    public String clase_td_total;
    public String clase_tr_tabla_vacia;
    public String clase_td_tabla_vacia;
    //public String clase_td_separacion_vertical;
    
    public String separacion_vertical_ancho;
    public String separacion_vertical_color_cabecera;
    public String separacion_vertical_color_datos;
    public String separacion_vertical_color_total;
    
    
    
    
    /**
     * Crea una instancia de la clase, y la inicializa con valores predeterminados.
     */
    public void EstiloTabla() {
        EstiloTabla estilo_predeterminado = getEstiloTablaAzul();
        setEstilo(estilo_predeterminado);
    }
    

    
    /**
     * Crea una instancia de la clase, y la inicializa a partir del estilo pasado por parámetro.
     * 
     * @param estilo                            Estilo a partir cual se inicializará esta instancia
     */
    public void EstiloTabla(EstiloTabla estilo) {
        setEstilo(estilo);
    }
    
    
    /**
     * Establece el estilo a utilizar a partir del estipo pasado por parámetro.
     * 
     * @param estilo                            Estilo a establecer
     */
    public void setEstilo(EstiloTabla estilo) {
        this.clase_table                            = estilo.clase_table;
        this.clase_tr_titulo                        = estilo.clase_tr_titulo;
        this.clase_td_titulo                        = estilo.clase_td_titulo;
        this.clase_td_titulo_derecha                = estilo.clase_td_titulo_derecha;
        this.clase_tr_subtitulo                     = estilo.clase_tr_subtitulo;
        this.clase_td_subtitulo                     = estilo.clase_td_subtitulo;
        this.clase_tr_cabecera                      = estilo.clase_tr_cabecera;
        this.clase_td_cabecera                      = estilo.clase_td_cabecera;
        this.clase_tr_seccion                       = estilo.clase_tr_seccion;
        this.clase_td_seccion                       = estilo.clase_td_seccion;
        this.clase_td_seccion_derecha               = estilo.clase_td_seccion_derecha;
        this.clase_tr_separacion                    = estilo.clase_tr_separacion;
        this.clase_tr_datos_impar                   = estilo.clase_tr_datos_impar;
        this.clase_tr_datos_par                     = estilo.clase_tr_datos_par;
        this.clase_tr_datos_impar_resaltada         = estilo.clase_tr_datos_impar_resaltada;
        this.clase_tr_datos_par_resaltada           = estilo.clase_tr_datos_par_resaltada;
        this.clase_td_datos                         = estilo.clase_td_datos;
        this.clase_tr_total                         = estilo.clase_tr_total;
        this.clase_td_total                         = estilo.clase_td_total;
        this.clase_tr_tabla_vacia                   = estilo.clase_tr_tabla_vacia;
        this.clase_td_tabla_vacia                   = estilo.clase_td_tabla_vacia;
        this.separacion_vertical_ancho              = estilo.separacion_vertical_ancho;
        this.separacion_vertical_color_cabecera     = estilo.separacion_vertical_color_cabecera;
        this.separacion_vertical_color_datos        = estilo.separacion_vertical_color_datos;
        this.separacion_vertical_color_total        = estilo.separacion_vertical_color_total;
    }

    
    public static EstiloTabla getEstiloTablaAzul() {
        EstiloTabla estilo_azul = new EstiloTabla();
        estilo_azul.clase_table                         = "azul_table";
        estilo_azul.clase_tr_titulo                     = "azul_tr_titulo";
        estilo_azul.clase_td_titulo                     = "azul_td_titulo";
        estilo_azul.clase_td_titulo_derecha             = "azul_td_titulo_derecha";
        estilo_azul.clase_tr_subtitulo                  = "azul_tr_subtitulo";
        estilo_azul.clase_td_subtitulo                  = "azul_td_subtitulo";
        estilo_azul.clase_tr_cabecera                   = "azul_tr_cabecera";
        estilo_azul.clase_td_cabecera                   = "azul_td_cabecera";
        estilo_azul.clase_tr_seccion                    = "azul_tr_seccion";
        estilo_azul.clase_td_seccion                    = "azul_td_seccion";
        estilo_azul.clase_td_seccion_derecha            = "azul_td_seccion_derecha";
        estilo_azul.clase_tr_separacion                 = "azul_tr_separacion";
        estilo_azul.clase_tr_datos_impar                = "azul_tr_datos_impar";
        estilo_azul.clase_tr_datos_par                  = "azul_tr_datos_par";
        estilo_azul.clase_tr_datos_impar_resaltada      = "azul_tr_datos_impar_resaltada";
        estilo_azul.clase_tr_datos_par_resaltada        = "azul_tr_datos_par_resaltada";
        estilo_azul.clase_td_datos                      = "azul_td_datos";
        estilo_azul.clase_tr_total                      = "azul_tr_total";
        estilo_azul.clase_td_total                      = "azul_td_total";
        estilo_azul.clase_tr_tabla_vacia                = "azul_tr_tabla_vacia";
        estilo_azul.clase_td_tabla_vacia                = "azul_td_tabla_vacia";
        estilo_azul.separacion_vertical_ancho           = SEPARACION_VERTICAL_ANCHO;
        estilo_azul.separacion_vertical_color_cabecera  = SEPARACION_VERTICAL_COLOR_CABECERA;
        estilo_azul.separacion_vertical_color_datos     = SEPARACION_VERTICAL_COLOR_DATOS;
        estilo_azul.separacion_vertical_color_total     = SEPARACION_VERTICAL_COLOR_TOTAL;
        
        return estilo_azul;
    }
    
}
