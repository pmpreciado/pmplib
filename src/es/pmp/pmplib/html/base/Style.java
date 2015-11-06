/**
 * Style.java
 *
 * Creado el 23-oct-2015, 13:38:20
 */

package es.pmp.pmplib.html.base;

import es.pmp.pmplib.Cadenas;

/**
 * Clase para simplificar la generación de los atributos "style" para los elementos HTML.
 * @author pmpreciado
 */
public class Style extends ListaAtributos {

    /** Carácter para separar los elementos de la lista (por defecto, la coma) */
    private final String SEPARADOR = "; ";

    /** Carácter usado como símbolo igual */
    private final String SIMBOLO_IGUAL = ": ";

    /** Carácter Encerrador predeterminado para los argumentos */
    private final String CARACTER_ENCERRADOR = "";
    
    
    /**
     * Crea la instancia de la clase.
     */
    public Style() {
        super.setSeparador(SEPARADOR);
        super.setSimboloIgual(SIMBOLO_IGUAL);
        super.setCaracterEncerrador(CARACTER_ENCERRADOR);        
    }
    
    
    /**
     * Crea la instancia de la clase.
     * La clase es inicializada con el estilo dado.
     *
     * @param cadena_style                      Cadena de estilos para inicializar la clase
     *                                          Por ejemplo: "font-size: 20px; text-color: blue; height: 20px; width: 100px;"
     */
    public Style(String cadena_style) {
        this();
        setStyle(cadena_style);
    }
    
    
    /**
     * Crea la instancia de la clase.
     * La clase es inicializada con las propiedades del estilo dado.
     *
     * @param style                             Estilo a partir del cual se inicializará esta instancia
     */
    public Style(Style style) {
        this();
        this.setAtributos(style);
    }
    
    
    /**
     * Inicializa la clase con la cadena de estilos dada.
     * 
     * @param cadena_style                      Cadena de estilos para inicializar la clase
     *                                          Por ejemplo: "font-size: 20px; text-color: blue; height: 20px; width: 100px;"
     */
    public void setStyle(String cadena_style) {
        
        if (cadena_style == null) {
            return;
        }
        
        String [] elementos = cadena_style.split(SEPARADOR);
        for (String elemento : elementos) {
            String [] par = elemento.split(SIMBOLO_IGUAL);
            if (par.length == 0) {
                continue;
            }
            
            String etiqueta = par[0];
            
            if (par.length > 1) {
                String valor = Cadenas.trim(par[1]);
                if (
                        (valor.startsWith("'") && valor.endsWith("'")) ||
                        (valor.startsWith("\"") && valor.endsWith("\""))
                ) {
                    valor = Cadenas.eliminarPrimeros(valor, 1);
                    valor = Cadenas.eliminarUltimos(valor, 1);
                }
                
                super.addAtributo(etiqueta, valor);
            } else {
                super.addAtributo(etiqueta);
            }
        }
    }
    
    
    /**
     * Comprueba si el estilo está vacío, es decir, si no hay ningún atributo asignado.
     * 
     * @return                                  'true' si el estilo está vacío
     *                                          'false' en caso contrario
     */
    public boolean vacio() {
        return super.vacia();
    }

    
    /**
     * Obtiene una cadena con el valor del style.
     * 
     * @return                                  Cadena con el valor del style
     */
    public String getCadenaStyle() {
        String cadena = super.getCadenaAtributos();
        return cadena;
    }
    
    
    /**
     * Combina los estilos de las instancias dadas, generando un objeto style combinado.
     * Si el mismo atributo se repite en los dos styles, tiene preferencia el atributo del style más a la izquierda (style_1).
     * 
     * @param style_1                           Primer estilo a combinar. Si es 'null', se ignora
     * @param style_2                           Segundo estilo a combinar. Si es 'null', se ignora
     * 
     * @return                                  Estilo combinado
     */
    public static Style combinar(Style style_1, Style style_2) {
        Style [] arr_style = {style_1, style_2};
        Style style_combinado = combinar(arr_style);
        return style_combinado;
    }

    
    /**
     * Combina los estilos de las instancias dadas, generando un objeto style combinado.
     * Si el mismo atributo se repite en más de un style, tiene preferencia el atributo del style más a la izquierda (según el orden del parámetro).
     * 
     * @param style                             Estilos a combinar. Si algún elemento es 'null', se ignora
     * 
     * @return                                  Estilo combinado
     */
    public static Style combinar(Style ... style) {
        ListaAtributos lista_atributos_combinada = ListaAtributos.combinar(style);
        Style style_combinado = new Style();
        style_combinado.addAtributos(lista_atributos_combinada);
        return style_combinado;
    }
}
