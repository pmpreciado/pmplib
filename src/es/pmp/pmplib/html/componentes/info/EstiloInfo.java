/**
 * EstiloInfo.java
 *
 * Creado el 29-oct-2015, 19:13:50
 */

package es.pmp.pmplib.html.componentes.info;

import es.pmp.pmplib.html.componentes.table.EstiloTabla;

/**
 * Atributos que definen el estilo del info.
 * 
 * @author pmpreciado
 */
public class EstiloInfo extends EstiloTabla {
    
    public String clase_td_etiqueta_impar;
    public String clase_td_etiqueta_par;
    public String clase_td_valor_impar;
    public String clase_td_valor_par;
    public String clase_td_explicacion_impar;
    public String clase_td_explicacion_par;

    
    /**
     * Crea una instancia de la clase, y la inicializa con valores predeterminados.
     */
    public void EstiloInfo() {
        EstiloInfo estilo_predeterminado = getEstiloPredeterminado();
        setEstilo(estilo_predeterminado);
    }
    

    
    /**
     * Crea una instancia de la clase, y la inicializa a partir del estilo pasado por parámetro.
     * 
     * @param estilo                            Estilo a partir cual se inicializará esta instancia
     */
    public void EstiloInfo(EstiloInfo estilo) {
        setEstilo(estilo);
    }
    
    
    /**
     * Establece el estilo a utilizar a partir del estipo pasado por parámetro.
     * 
     * @param estilo                            Estilo a establecer
     */
    public void setEstilo(EstiloInfo estilo) {
        super.setEstilo(estilo);
        this.clase_td_etiqueta_impar                = estilo.clase_td_etiqueta_impar;
        this.clase_td_etiqueta_par                  = estilo.clase_td_etiqueta_par;
        this.clase_td_valor_impar                   = estilo.clase_td_valor_impar;
        this.clase_td_valor_par                     = estilo.clase_td_valor_par;
        this.clase_td_explicacion_impar             = estilo.clase_td_explicacion_impar;
        this.clase_td_explicacion_par               = estilo.clase_td_explicacion_par;
    }

    
    /**
     * Crea un estilo por defecto.
     * 
     * @return                                  Clase con el estilo creado
     */
    public static EstiloInfo getEstiloPredeterminado() {
        EstiloInfo estilo = new EstiloInfo();
        estilo.clase_td_etiqueta_impar         = "info_td_etiqueta_impar";
        estilo.clase_td_etiqueta_par           = "info_td_etiqueta_par";
        estilo.clase_td_valor_impar            = "info_td_valor_impar";
        estilo.clase_td_valor_par              = "info_td_valor_par";
        estilo.clase_td_explicacion_impar      = "info_td_explicacion_impar";
        estilo.clase_td_explicacion_par        = "info_td_explicacion_par";
        
        return estilo;
    }

}
