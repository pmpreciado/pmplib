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
     * La clase es inicializada con la cadena de estilos dada
     *
     * @param cadena_style                      Cadena de estilos para inicializar la clase
     *                                          Por ejemplo: "font-size: 20px; text-color: blue; height: 20px; width: 100px;"
     */
    public Style(String cadena_style) {
        this();
        setStyle(cadena_style);
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
     * Obtiene una cadena con el valor del style.
     * 
     * @return                                  Cadena con el valor del style
     */
    public String getCadenaStyle() {
        String cadena = super.getCadenaAtributos();
        return cadena;
    }
    

}
