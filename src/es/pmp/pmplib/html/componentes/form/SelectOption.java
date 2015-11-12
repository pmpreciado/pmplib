/**
 * SelectOption.java
 *
 * Creado el 09-nov-2015, 19:36:26
 */

package es.pmp.pmplib.html.componentes.form;

import es.pmp.pmplib.html.base.Tag;

/**
 * Clase para guardar las propiedades de una opción de un select.
 * 
 * @author pmpreciado
 */
public class SelectOption implements Comparable <SelectOption> {

    /** Valor */
    String valor;
    
    /** Texto */
    String texto;
    
    
    /**
     * Crea una instancia de la clase.
     */
    public SelectOption() {
    }
    
    
    /**
     * Crea una instancia de la clase.
     *
     * @param texto                             Texto de la opción
     * @param valor                             Valor
     */
    public SelectOption(String texto, String valor) {
        this.texto = texto;
        this.valor = valor;
    }

    
    /**
     * Crea una instancia de la clase.
     *
     * @param texto                             Texto de la opción
     * @param valor                             Valor
     */
    public SelectOption(String texto, int valor) {
        this.texto = texto;
        this.valor = Integer.toString(valor);
    }
    
    
    /**
     * Establece el valor de elemento del select.
     * 
     * @param valor                             Valor del elemento
     */
    public void setValor(String valor) {
        this.valor = valor;
    }
    
    
    /**
     * Obtiene el valor del elemento del select.
     * 
     * @return                                  Valor del elemento
     */
    public String getValor() {
        return valor;
    }
    
    
    /**
     * Establece el texto de elemento del select.
     * 
     * @param texto                             Texto del elemento
     */
    public void setTexto(String texto) {
        this.valor = texto;
    }
    
    
    /**
     * Obtiene el texto del elemento del select.
     * 
     * @return                                  Texto del elemento
     */
    public String getTexto() {
        return texto;
    }
    
    
    /**
     * Comprueba si esta opción está seleccionada.
     * 
     * @param texto_opcion_seleccionada         Texto de la opción seleccionada (puede ser 'null')
     * @param valor_opcion_seleccionada         Valor de la opción seleccionada (puede ser 'null')
     * 
     * @return                                  'true' si la opción está seleccionada
     *                                          'false' si no lo está
     */
    public boolean seleccionada(String texto_opcion_seleccionada, String valor_opcion_seleccionada) {
        
        if (texto_opcion_seleccionada != null && texto_opcion_seleccionada.equals(texto)) {
            return true;
        }

        if (valor_opcion_seleccionada != null && valor_opcion_seleccionada.equals(valor)) {
            return true;
        }

        return false;
    }

    
    /**
     * Compara esta opción con otra.
     * La comparación se realiza por orden alfabético del texto.
     *
     * @param opcion_2                      Opción a comparar con esta
     * 
     * @return                              Resultado de la comparación
     */
    @Override
    public int compareTo(SelectOption opcion_2) {
        int resultado = this.texto.compareToIgnoreCase(opcion_2.texto);
        return resultado;
    }

    
    /**
     * Obtiene el código HTML para definir la opción.
     *
     * @param texto_opcion_seleccionada         Texto de la opción seleccionada (puede ser 'null')
     * @param valor_opcion_seleccionada         Valor de la opción seleccionada (puede ser 'null')
     * 
     * @return                                  Código HTML generado
     */
    public StringBuilder getHtml(String texto_opcion_seleccionada, String valor_opcion_seleccionada) {

        Tag tag = new Tag("option");
        tag.addAtributo("value", valor);
        if (seleccionada(texto_opcion_seleccionada, valor_opcion_seleccionada)) {
            tag.addAtributo("selected");
        }
        tag.addContenido(texto);

        StringBuilder s_tag = tag.toStringBuilder();
        return s_tag;
    }
}
