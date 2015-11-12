/**
 * Select.java
 *
 * Creado el 09-nov-2015, 19:27:09
 */

package es.pmp.pmplib.html.componentes.form;

import es.pmp.pmplib.html.base.Tag;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase para definir una lista desplegable para un formulario.
 *
 * @author pmpreciado
 */
public class Select extends FormItemBase {

    public static class SelectItem implements Comparable <SelectItem> {
        
        public static final int TSI_OPCION  = 1;
        public static final int TSI_GRUPO   = 2;
        
        public int tipo;
        
        public SelectOption opcion;
        public SelectOptionGroup grupo;
        
        public SelectItem(SelectOption opcion) {
            this.tipo = TSI_OPCION;
            this.opcion = opcion;
            this.grupo = null;
        }
        
        public SelectItem(SelectOptionGroup grupo) {
            this.tipo = TSI_GRUPO;
            this.opcion = null;
            this.grupo = grupo;
        }
        
        
        /**
         * Compara este elemento del select con otro.
         * La comparación se realiza por orden alfabético del texto.
         *
         * @param item_2                            Opción a comparar con esta
         * 
         * @return                                  Resultado de la comparación
         */
        @Override
        public int compareTo(SelectItem item_2) {
            
            String texto;
            if (tipo == TSI_OPCION) {
                texto = opcion.texto;
            } else {
                texto = grupo.nombre;
            }
            
            String texto_2;
            if (item_2.tipo == TSI_OPCION) {
                texto_2 = item_2.opcion.texto;
            } else {
                texto_2 = item_2.grupo.nombre;
            }
            
            
            int resultado = texto.compareToIgnoreCase(texto_2);
            return resultado;
        }
        
        
        /**
         * Obtiene el código HTML para defirnir este elemento del select.
         * 
         * @param texto_opcion_seleccionada         Texto de la opción seleccionada (puede ser 'null')
         * @param valor_opcion_seleccionada         Valor de la opción seleccionada (puede ser 'null')
         * 
         * @return                                  Código HTML generado
         */
        public StringBuilder getHtml(String texto_opcion_seleccionada, String valor_opcion_seleccionada) {
            StringBuilder s = new StringBuilder();
            
            if (tipo == TSI_OPCION) {
                s = this.opcion.getHtml(texto_opcion_seleccionada, valor_opcion_seleccionada);
            } else if (tipo == TSI_GRUPO) {
                s = this.grupo.getHtml(texto_opcion_seleccionada, valor_opcion_seleccionada);
            }
            
            return s;
        }
        
    }
    
    
    public List <SelectItem> l_items;
    
    
    /** Texto de la opción seleccionada */
    String texto_opcion_seleccionada;
    
    /** Valor de la opción seleccionada */
    String valor_opcion_seleccionada;

    
    
    /**
     * Crea una instancia de la clase.
     */
    public Select() {
        l_items = new ArrayList();
        texto_opcion_seleccionada = null;
        valor_opcion_seleccionada = null;
    }

    

    
    
    /**
     * Crea una instancia de la clase.
     *
     * @param name                              Nombre del elemento
     */
    public Select(String name) {
        this();
        super.setName(name);
    }


    /**
     * Añade una opción al "select".
     *
     * @param opcion                            Opción a añadir
     */
    public void addOpcion(SelectOption opcion) {
        SelectItem item = new SelectItem(opcion);
        l_items.add(item);
    }

    
    /**
     * Añade una opción al "select" en la posición indicada.
     * Si la posición indicada sobrepasa el número de opciones, se insertará en la última posición.
     *
     * @param posicion                          Posición donde estará la opción (la primera es 0)
     * @param opcion                            Opción a añadir
     */
    public void addOpcion(int posicion, SelectOption opcion) {
        
        if (posicion >= l_items.size()) {
            addOpcion(opcion);
        } else {
            SelectItem item = new SelectItem(opcion);
            l_items.add(posicion, item);
        }
    }
    
    
    /**
     * Añade una opción al "select".
     * La opción estará preseleccionada.
     *
     * @param opcion                            Opción a añadir
     * @param selected                          Si es 'true', la opción estará seleccionada
     *                                          'false' para que no lo esté
     */
    public void addOpcion(SelectOption opcion, boolean selected) {
        SelectItem item = new SelectItem(opcion);
        l_items.add(item);
        
        if (selected) {
            this.setOpcionSeleccionadaTexto(opcion.texto);
        }
    }
    
    
    /**
     * Añade una opción al "select".
     *
     * @param texto                             Texto de la opción
     * @param valor                             Valor
     */
    public void addOpcion(String texto, String valor) {
        SelectOption option = new SelectOption(texto, valor);
        addOpcion(option);
    }
    

    /**
     * Añade una opción al "select" en la posición indicada.
     * Si la posición indicada sobrepasa el número de opciones, se insertará en la última posición.
     * 
     * @param posicion                          Posición donde estará la opción (la primera es 0)
     * @param texto                             Texto de la opción
     * @param valor                             Valor
     */
    public void addOpcion(int posicion, String texto, String valor) {
        SelectOption option = new SelectOption(texto, valor);
        addOpcion(posicion, option);
    }
    
    
    /**
     * Añade una opción al "select".
     *
     * @param texto                             Texto de la opción
     * @param valor                             Valor
     * @param selected                          Si es 'true', la opción estará seleccionada
     *                                          'false' para que no lo esté
     */
    public void addOpcion(String texto, String valor, boolean selected) {
        SelectOption option = new SelectOption(texto, valor);
        addOpcion(option, selected);
    }
    
    
    /**
     * Añade una opción al "select".
     *
     * @param texto                             Texto de la opción
     * @param valor                             Valor
     */
    public void addOpcion(String texto, int valor) {
        SelectOption option = new SelectOption(texto, valor);
        addOpcion(option);
    }


    /**
     * Añade una opción al "select" en la posición indicada.
     * Si la posición indicada sobrepasa el número de opciones, se insertará en la última posición.
     * 
     * @param posicion                          Posición donde estará la opción (la primera es 0)
     * @param texto                             Texto de la opción
     * @param valor                             Valor
     */
    public void addOpcion(int posicion, String texto, int valor) {
        SelectOption option = new SelectOption(texto, valor);
        addOpcion(posicion, option);
    }
    
    
    /**
     * Añade una opción al "select".
     *
     * @param texto                             Texto de la opción
     * @param valor                             Valor
     * @param selected                          Si es 'true', la opción estará seleccionada
     *                                          'false' para que no lo esté
     */
    public void addOpcion(String texto, int valor, boolean selected) {
        SelectOption option = new SelectOption(texto, valor);
        addOpcion(option, selected);
    }
    
    
    /**
     * Establece la opción seleccionada a partir del texto de la misma.
     * 
     * @param texto_opcion_seleccionada         Texto de la opción seleccionada
     */
    public void setOpcionSeleccionadaTexto(String texto_opcion_seleccionada) {
        this.texto_opcion_seleccionada = texto_opcion_seleccionada;
        this.valor_opcion_seleccionada = null;
    }

    
    /**
     * Establece la opción seleccionada a partir del valor de la misma.
     * 
     * @param valor_opcion_seleccionada         Valor de la opción seleccionada
     */
    public void setOpcionSeleccionadaValor(String valor_opcion_seleccionada) {
        this.texto_opcion_seleccionada = null;
        this.valor_opcion_seleccionada = valor_opcion_seleccionada;
    }
    
    
    /**
     * Establece la opción seleccionada a partir del valor de la misma.
     * 
     * @param valor_opcion_seleccionada         Valor de la opción seleccionada
     */
    public void setOpcionSeleccionadaValor(int valor_opcion_seleccionada) {
        String s_valor_opcion_seleccionada = Integer.toString(valor_opcion_seleccionada);
        setOpcionSeleccionadaValor(s_valor_opcion_seleccionada);
    }
    
    
    /**
     * Establece la opción seleccionada a partir de su índice dentro del select.
     * Los grupos de opciones no se tienen en cuenta a la hora de computar el índice.
     * 
     * @param indice                            Índice de la opción preseleccionada (empezando por 0)
     */
    public void setOpcionSeleccionadaIndice(int indice) {
        
        this.texto_opcion_seleccionada = null;
        this.valor_opcion_seleccionada = null;
        
        List <SelectOption> l_opciones = getListaOpciones();
        if (indice >= l_opciones.size()) {
            return;
        }
        
        SelectOption opcion = l_opciones.get(indice);
        this.texto_opcion_seleccionada = opcion.texto;
    }

    
    /**
     * Obtiene el número de opciones que hay en el select.
     * Tiene en cuenta las opciones que puedan estar dentro de un grupo.
     * No cuenta los elementos que son nombres de grupo, sólo las opciones.
     * 
     * @return                                  Número de opciones que hay en el select
     */
    public int getNumeroOpciones() {
        int n = 0;
        for (SelectItem item : l_items) {
            if (item.tipo == SelectItem.TSI_OPCION) {
                n++;
            } else {
                SelectOptionGroup grupo = item.grupo;
                n += grupo.size();
            }
        }
        
        return n;
    }

    
    /**
     * Obtiene el número de elementos que hay en el select.
     * Tiene en cuenta las opciones que puedan estar dentro de un grupo.
     * Cuenta, tanto las opciones, como los grupos.
     * 
     * @return                                  Número de elementos que hay en el select
     */
    public int getNumeroElementos() {
        int n = 0;
        for (SelectItem item : l_items) {
            n++;
            if (item.tipo == SelectItem.TSI_GRUPO) {
                SelectOptionGroup grupo = item.grupo;
                n += grupo.size();
            }
        }
        
        return n;
    }
    

    /**
     * Obtiene el número de opciones que hay en el select.
     *
     * @return                                  Número de opciones que hay en el select
     */
    public int size() {
        int s = getNumeroOpciones();
        return s;
    }


    /**
     * Elimina todas las opciones y grupos del "select".
     */
    public void clear() {
        l_items.clear();
    }
    
    
    /**
     * Obtiene, en una lista única, todas las opciones del select, juntando las que están agrupadas con las que no.
     * 
     * @return                                  Lista con las opciones del select
     */
    private List <SelectOption> getListaOpciones() {
        List <SelectOption> l_opciones = new ArrayList();
        
        for (SelectItem item : l_items) {
            if (item.tipo == SelectItem.TSI_OPCION) {
                l_opciones.add(item.opcion);
            } else {
                SelectOptionGroup grupo = item.grupo;
                List <SelectOption> l_opciones_grupo = grupo.getOpciones();
                l_opciones.addAll(l_opciones_grupo);
            }
        }
        
        return l_opciones;
    }
    
    

    /**
     * Obtiene la opción preseleccionada.
     * 
     * @return                                  Opción preseleccionada
     *                                          'null' si no hubiera ninguna
     */
    public SelectOption getSelected() {
        
        if (texto_opcion_seleccionada == null && valor_opcion_seleccionada == null) {
            return null;
        }
        
        List <SelectOption> l_opciones = getListaOpciones();
        for (SelectOption opcion : l_opciones) {
            if (texto_opcion_seleccionada != null) {
                if (opcion.texto.equals(texto_opcion_seleccionada)) {
                    return opcion;
                }
            }
            
            if (texto_opcion_seleccionada != null) {
                if (opcion.valor.equals(texto_opcion_seleccionada)) {
                    return opcion;
                }
            }
        }
        return null;
    }
    
    
    /**
     * Obtiene una opción, dada por su índice dentro del select.
     * Tiene en cuenta los posibles grupos de opciones.
     * Los grupos de opciones no se tienen en cuenta a la hora de computar el índice.
     * 
     * @param indice                            Índice de la opción a obtener (empezando en 0)
     * 
     * @return                                  Opción
     *                                          'null' si no existiera la opción con el índice dado
     */
    public SelectOption getOpcion(int indice) {
        
        List <SelectOption> l_opciones = getListaOpciones();
        
        if (indice >= l_opciones.size()) {
            return null;
        }
        
        SelectOption opcion = l_opciones.get(indice);
        return opcion;
    }
    
    
    /**
     * Ordena alfabéticamente las opciones del "select".
     */
    public void ordenar() {
        java.util.Collections.sort(l_items);
    }

    
    /**
     * Obtiene el código HTML para definir el control.
     *
     * @return                                  String con el código HTML del control
     */
    public StringBuilder getHtml() {
        
        Tag tag = new Tag("select");
        tag.addAtributos(this);
        
        for (SelectItem item : l_items) {
            
            StringBuilder s_item = item.getHtml(texto_opcion_seleccionada, valor_opcion_seleccionada);
            tag.addContenido(s_item);
        }
        
        StringBuilder s_tag = tag.toStringBuilder();
        return s_tag;
    }
    
    
    
    /**
     * Obtiene el código HTML para definir el control.
     *
     * @return                                  String con el código HTML del control
     */
    @Override
    public String toString() {
        StringBuilder html = getHtml();
        return html.toString();
    }
}
