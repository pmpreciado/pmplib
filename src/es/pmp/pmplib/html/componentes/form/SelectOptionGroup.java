/**
 * SelectOptionGroup.java
 *
 * Creado el 09-nov-2015, 19:51:28
 */

package es.pmp.pmplib.html.componentes.form;

import es.pmp.pmplib.html.base.Tag;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase para guardar las propiedades de una agrupación de opciones de un select.
 * 
 * @author pmpreciado
 */
public class SelectOptionGroup {

    /** Nombre del grupo */
    String nombre;
    
    /** Opciones que contiene el grupo */
    List <SelectOption> l_opciones;
    
    
    /**
     * Crea una instancia de la clase.
     */
    public SelectOptionGroup() {
        l_opciones = new ArrayList();
    }
    
    
    /**
     * Establece el nombre del grupo.
     * 
     * @param nombre                            Nombre del grupo
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
    /**
     * Obtiene el nombre del grupo.
     * 
     * @return                                  Nombre del grupo
     */
    public String getNombre() {
        return nombre;
    }
    
    
    /**
     * Añade una opción al grupo.
     * 
     * @param opcion                            Opción a añadir
     */
    public void addOpcion(SelectOption opcion) {
        l_opciones.add(opcion);
    }
    
    
    /**
     * Añade una opción al grupo en la posición indicada. El resto de opciones son desplazadas.
     * Si la posición indicada sobrepasa el número de opciones, se insertará en la última posición.
     *
     * @param posicion                          Posición que ocupará la opción en el grupo (el principio es 0)
     * @param opcion                            Opción a añadir
     */
    public void addOpcion(int posicion, SelectOption opcion) {
        if (posicion >= l_opciones.size()) {
            addOpcion(opcion);
        } else {
            l_opciones.add(posicion, opcion);
        }
    }
    
    
    /**
     * Añade una opción al grupo.
     *
     * @param texto                             Texto de la opción
     * @param valor                             Valor
     */
    public void addOpcion(String texto, String valor) {
        SelectOption opcion = new SelectOption(texto, valor);
        addOpcion(opcion);
    }


    /**
     * Añade una opción al grupo.
     *
     * @param texto                             Texto de la opción
     * @param valor                             Valor
     */
    public void addOpcion(String texto, int valor) {
        SelectOption opcion = new SelectOption(texto, valor);
        addOpcion(opcion);
    }
    
    
    /**
     * Añade una opción al grupo en la posición indicada. El resto de opciones son desplazadas.
     * Si la posición indicada sobrepasa el número de opciones, se insertará en la última posición.
     *
     * @param posicion                          Posición que ocupará la opción en el grupo (el principio es 0)
     * @param texto                             Texto de la opción
     * @param valor                             Valor
     */
    public void addOpcion(int posicion, String texto, String valor) {
        SelectOption opcion = new SelectOption(texto, valor);
        addOpcion(posicion, opcion);
    }
    

    /**
     * Obtiene el número de opciones que hay en el grupo.
     * 
     * @return                                  Número de opciones que hay en el grupo
     */
    public int size() {
        int s = l_opciones.size();
        return s;
    }
    
    
    /**
     * Elimina todas las opciones del grupo.
     */
    public void clear() {
        l_opciones.clear();
    }

    
    /**
     * Ordena alfabéticamente las opciones del grupo.
     */
    public void ordenar() {
        java.util.Collections.sort(l_opciones);
    }

    
    /**
     * Obtiene las opciones del grupo.
     *
     * @return                                  Lista con las opciones
     */
    public List <SelectOption> getOpciones() {
        return l_opciones;
    }
    
    
    /**
     * Obtiene el código HTML para defirnir este grupo de opciones del select.
     * 
     * @param texto_opcion_seleccionada         Texto de la opción seleccionada (puede ser 'null')
     * @param valor_opcion_seleccionada         Valor de la opción seleccionada (puede ser 'null')
     * 
     * @return                                  Código HTML generado
     */
    public StringBuilder getHtml(String texto_opcion_seleccionada, String valor_opcion_seleccionada) {
        
        Tag tag = new Tag("optgroup");
        tag.addAtributo("label", this.nombre);
        
        for (SelectOption opcion : l_opciones) {
            StringBuilder html_opcion = opcion.getHtml(texto_opcion_seleccionada, valor_opcion_seleccionada);
            tag.addContenido(html_opcion);
        }
        
        StringBuilder s_tag = tag.toStringBuilder();
        return s_tag;
    }
    
    
}
