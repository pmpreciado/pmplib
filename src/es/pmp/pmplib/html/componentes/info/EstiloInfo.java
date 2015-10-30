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
    
    public String clase_td_etiqueta;
    public String clase_td_valor;
    public String clase_td_explicacion;

    
    /**
     * Crea una instancia de la clase, y la inicializa con valores predeterminados.
     */
    public void EstiloInfo() {
        EstiloInfo estilo_predeterminado = getEstiloInfoAzul();
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
        this.clase_td_etiqueta                      = estilo.clase_td_etiqueta;
        this.clase_td_valor                         = estilo.clase_td_valor;
        this.clase_td_explicacion                   = estilo.clase_td_explicacion;
    }

    
    public static EstiloInfo getEstiloInfoAzul() {
        EstiloInfo estilo_azul = new EstiloInfo();
        estilo_azul.clase_td_etiqueta               = "azul_td_etiqueta";
        estilo_azul.clase_td_valor                  = "azul_td_valor";
        estilo_azul.clase_td_explicacion            = "azul_td_explicacion";
        return estilo_azul;
    }

}
