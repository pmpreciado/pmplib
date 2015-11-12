/**
 * CheckboxGroup.java
 *
 * Creado el 09-nov-2015, 18:41:13
 */

package es.pmp.pmplib.html.componentes.form;

import es.pmp.pmplib.Comun;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase para definir un grupo de elementos de formulario de tipo checkbox.
 * 
 * @author pmpreciado
 */
public class CheckboxGroup extends FormItemBase {

    
    /** Orientación de los elementos dentro del grupo */
    public static final int OR_HORIZONTAL = 0;
    public static final int OR_VERTICAL   = 1;
    
    public static final int OR_PREDETERMINADA = OR_HORIZONTAL;
    

    /** En los RadioGroup horizontales, cada cuentos radio-buttons hay que comenzar una nueva línea */
    protected static int NUM_ELEMENTOS_POR_LINEA_HORIZONTAL = 10;


    /** Orientación de los elementos del radio group */
    int orientacion;

    /** Etiquetas de los radio buttons */
    List <String> l_etiquetas;
    
    /** Radio buttons */
    List <InputCheckbox> l_campos;


    /** En los grupos horizontales, cada cuentos radio-buttons hay que comenzar una nueva línea */
    int num_elementos_por_linea_horizontal;


    /**
     * Crea una instancia de la clase.
     */
    public CheckboxGroup() {
        l_etiquetas = new ArrayList();
        l_campos = new ArrayList();
        orientacion = OR_PREDETERMINADA;
        num_elementos_por_linea_horizontal = NUM_ELEMENTOS_POR_LINEA_HORIZONTAL;
    }


    /**
     * Establece la orientación de los checkboxex dentro del grupo.
     *
     * @param orientacion                       Orientación
     *   <ul>
     *     <li> OR_HORIZONTAL
     *     <li> OR_VERTICAL
     *   </ul>
     */
    public void setOrientacion(int orientacion) {
        this.orientacion = orientacion;
    }


    /**
     * Establece el número de elementos al cabo de los cuales hay que comenzar una nueva línea cuando la orientación es horizontal.
     *
     * @param num_elementos_por_linea_horizontal    Número de elementos
     */
    public void setNumElementosPorLineaHorizontal(int num_elementos_por_linea_horizontal) {
        this.num_elementos_por_linea_horizontal = num_elementos_por_linea_horizontal;
    }


    /**
     * Añade un checkbox al grupo.
     *
     * @param etiqueta                          Etiqueta asociada a este checkbox individual
     * @param campo                             Checkbox a añadir
     */
    public void addCheckbox(String etiqueta, InputCheckbox campo) {
        l_etiquetas.add(etiqueta);
        l_campos.add(campo);
    }


    /**
     * Obtiene el checkbox dado por su nombre.
     * 
     * @param name                              Nombre del checkbox
     * 
     * @return                                  Objeto con el checkbox
     *                                          'null' si en el grupo no hay ningún checkbox con ese nombre
     */
    public InputCheckbox getCheckbox(String name) {
        
        for (InputCheckbox checkbox : l_campos) {
            if (checkbox.getName().equals(name)) {
                return checkbox;
            }
        }
        
        return null;
    }


    /**
     * Obtiene el último checkbox añadido al grupo.
     * 
     * @return                                  Último checkbox añadido al grupo
     *                                          'null' si el grupo está vacío
     */
    public InputCheckbox getUltimoCheckbox() {
        if (l_campos.isEmpty())  {
            return null;
        }
        
        InputCheckbox checkbox = l_campos.get(l_campos.size() - 1);
        return checkbox;
    }
    
    
    /**
     * Activa o desactiva la marca en uno de los elementos del checkbox group, dado por su nombre.
     *
     * @param name                              Nombre del elemento a marcar
     * @param checked                           'true' para marcar el elemento
     *                                          'false' para desmarcarlo
     */
    public void setCheckedName(String name, boolean checked) {
        for (InputCheckbox checkbox : l_campos)
        {
            if (checkbox.getName().equals(name)) {
                checkbox.setChecked(checked);
            }
        }
    }
    
    
    /**
     * Activa la marca en uno de los elementos del checkbox group, dado por su nombre.
     *
     * @param name                              Nombre del elemento a marcar
     */
    public void setCheckedName(String name) {
        
        boolean checked = true;
        setCheckedName(name, checked);
    }

    
    /**
     * Activa o desactiva la marca en uno de los elementos del checkbox group, dado por su valor.
     *
     * @param value                              Valor del elemento a marcar
     * @param checked                           'true' para marcar el elemento
     *                                          'false' para desmarcarlo
     */
    public void setCheckedValue(String value, boolean checked) {
        for (InputCheckbox checkbox : l_campos)
        {
            if (checkbox.getValue().equals(value)) {
                checkbox.setChecked(checked);
            }
        }
    }
    
    
    /**
     * Activa la marca en uno de los elementos del checkbox group, dado por su valor.
     *
     * @param value                              Valor del elemento a marcar
     */
    public void setCheckedValue(String value) {
        
        boolean checked = true;
        setCheckedValue(value, checked);
    }

    
    /**
     * Marca todos los elementos del checkbox group.
     */
    public void marcarTodos() {
        for (InputCheckbox checkbox : l_campos)
        {
            checkbox.setChecked(false);
        }
    }
    
    
    /**
     * Desmarca todos los elementos del checkbox group.
     */
    public void desmarcarTodos() {
        for (InputCheckbox checkbox : l_campos)
        {
            checkbox.setChecked(false);
        }
    }
    
    

    /**
     * Obtiene el código HTML que muestra el checkbox group en horizontal.
     *
     * @return                                  Código HTML generado
     */
    public StringBuilder getHtmlHorizontal() {
        StringBuilder s = new StringBuilder();

        for (int i = 0; i < l_campos.size(); i++) {
            String etiqueta = l_etiquetas.get(i);
            InputCheckbox checkbox = l_campos.get(i);

            StringBuilder html_checkbox = checkbox.getHtml();
            s.append(html_checkbox);
            s.append(etiqueta);

            if (i > 0 && i % (num_elementos_por_linea_horizontal - 1) == 0) {
                s.append("<br />" + Comun.NL);
            } else {
                if (i < l_campos.size() - 1) {
                    s.append("&nbsp; &nbsp;");
                }
            }
        }

        return s;
    }
    

    /**
     * Obtiene el código HTML que muestra el radio group en vertical.
     *
     * @return                                  Código HTML generado
     */
    public StringBuilder getHtmlVertical() {
        StringBuilder s = new StringBuilder("");

        s.append("<table class=\"nodeco\">" + Comun.NL);

            for (int i = 0; i < l_campos.size(); i++) {
                String etiqueta = l_etiquetas.get(i);
                InputCheckbox checkbox = l_campos.get(i);
                s.append("<tr>" + Comun.NL);
                    s.append("<td>" + Comun.NL);
                        StringBuilder html_checkbox = checkbox.getHtml();
                        s.append(html_checkbox);
                        s.append(etiqueta);
                    s.append("</td>" + Comun.NL);
                s.append("</tr>" + Comun.NL);
            }

        s.append("</table>" + Comun.NL);

        return s;
    }


    /**
     * Obtiene el código HTML que muestra el radio group.
     *
     * @return                                  Código HTML generado
     */
    @Override
    public StringBuilder getHtml() {

        StringBuilder html;
        if (orientacion == OR_HORIZONTAL) {
            html = getHtmlHorizontal();
        } else {
            html = getHtmlVertical();
        }

        return html;
    }

}
