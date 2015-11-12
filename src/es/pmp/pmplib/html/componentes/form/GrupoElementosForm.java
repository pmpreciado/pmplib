/**
 * GrupoElementosForm.java
 *
 * Creado el 10-nov-2015, 13:48:37
 */

package es.pmp.pmplib.html.componentes.form;

import es.pmp.pmplib.Comun;
import es.pmp.pmplib.html.base.Tag;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase para definir un grupo de elementos de un formulario.
 * 
 * @author pmpreciado
 */
public class GrupoElementosForm extends FormItemBase {


    /** Elementos que contiene el grupo */
    List <CElementoForm> l_elementos;
    
    /**
     * Crea una instancia de la clase.
     */
    public GrupoElementosForm() {
        l_elementos = new ArrayList();
    }
    
    
    /** 
     * Añade un elemento al grupo.
     * 
     * @param elemento                          Elemento a añadir
     */
    public void addElemento(CElementoForm elemento) {
        l_elementos.add(elemento);
    }
    
    
    /** 
     * Obtiene los elementos del grupo.
     * 
     * @return                                  Elementos del grupo
     */
    public List <CElementoForm> getElementos() {
        return l_elementos;
    }
    
    
    /**
     * Elimina los elementos del grupo.
     */
    public void clear() {
        l_elementos.clear();
    }
    
    
    /**
     * Genera la tabla para contener los elementos del grupo.
     * 
     * @param form                              Formulario que contiene el elemento
     * 
     * @return                                  Tag generado para la tabla
     */
    public Tag getTagTable(Form form) {
        
        Tag tag_table = new Tag("table");
        tag_table.setClass("nodeco");
        
        Tag tag_tr = new Tag("tr");
        
        for (CElementoForm elemento : l_elementos) {
            Tag tag_td = new Tag("td");
            tag_td.setClass("nodeco");
            
            StringBuilder html_td_elemento = elemento.getHtmlCampo(form);
            //StringBuilder html_td_elemento = elemento.getTdGrupoElementos(form);
            tag_tr.addContenido(html_td_elemento);
        }

        tag_table.addContenido(tag_tr);
        
        return tag_table;
    }

    
    /**
     * Genera la tabla para contener los elementos del grupo.
     * 
     * @param form                              Formulario que contiene el elemento
     * 
     * @return                                  Tag generado para la tabla
     */
    public StringBuilder getHtml(Form form) {
        
        Tag tag_table = getTagTable(form);
        StringBuilder s = tag_table.toStringBuilder();
        
        return s;
    }
    
    
    /**
     * Obtiene una cadena con la información de la instancia.
     * 
     * @return                                  Cadena de texto generada
     */
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("");
        
        s.append("Número de elementos: " + l_elementos.size() + " " + Comun.NL);

        for (int i = 0; i < l_elementos.size(); i++) {
            CElementoForm e = l_elementos.get(i);
            s.append(i + ": " + Comun.NL);
            s.append(e.toString() + Comun.NL);
        }
        

        return s.toString();
    }
    
}
