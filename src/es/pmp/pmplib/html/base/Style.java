/**
 * Style.java
 *
 * Creado el 23-oct-2015, 13:38:20
 */

package es.pmp.pmplib.html.base;

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
    private final String CARACTER_ENCERRADOR = "'";
    
    
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
        String [] elementos = cadena_style.split(SEPARADOR);
        for (String elemento : elementos) {
            String [] par = elemento.split(SIMBOLO_IGUAL);
            String etiqueta = par[0];
            
            if (par.length > 1) {
                String valor = par[1];
                super.add(etiqueta, valor);
            } else {
                super.add(etiqueta);
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
