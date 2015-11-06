/**
 * Holder.java
 *
 * Creado el 04-nov-2015, 10:16:43
 */

package es.pmp.pmplib.util;


/**
 * Clase para albergar valores del tipo primitivo de Java, de modo que puedan ser pasados a funciones por referencia.
 * 
 * @param <Tipo>                                Tipo de dato a albergar
 */
public class Holder <Tipo> {

    Tipo valor;
    
    
    /**
     * Crea una instancia de la clase.
     */
    public Holder() {
        this.valor = null;
    }

    
    /**
     * Crea una instancia de la clase.
     *
     * @param valor                             Valor a guardar
     */
    public Holder(Tipo valor) {
        this.valor = valor;
    }
    
    
    /**
     * Establece el valor.
     *
     * @param valor                             Valor a guardar
     */
    public void setValor(Tipo valor) {
        this.valor = valor;
    }
    
    /**
     * Establece el valor, como un tipo entero.
     *
     * @param valor                             Valor a guardar
     */
    public void setValor(int valor) {
        Integer i = valor;
        this.valor = (Tipo) i;
    }
    
    
    /**
     * Retorna el valor guardado.
     *
     * @return                                  Valor
     */
    public Tipo getValor() {
        return valor;
    }


    /**
     * Genera una cadena con el valor del objeto.
     *
     * @return                                  Cadena con el valor del objeto
     */
    @Override
    public String toString() {
        return valor.toString();
    }
}
