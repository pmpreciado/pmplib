/**
 * Where.java
 *
 * Creado el 22-oct-2015, 13:57:22
 */

package es.pmp.pmplib.db;

import es.pmp.pmplib.Arrays;
import java.util.ArrayList;
import java.util.List;

/**
 * Métodos para la generación de las cláusulas "where" usadas en las consultas SQL.
 *
 * @author pmpreciado
 */
public class Where {

    public static final String OP_LOGICO_AND                = "AND";
    public static final String OP_LOGICO_OR                 = "OR";
    
    static final String OP_LOGICO_PREDETERMINADO            = OP_LOGICO_AND;
    

    List <String> l_condiciones;
    List <String> l_operadores_logicos;
    
    
    /** 
     * Crea la instancia de la clase.
     */
    public Where() {
        l_condiciones = new ArrayList();
        l_operadores_logicos = new ArrayList();
    }


    /**
     * Añade una condición.
     * La condición se liga con el operador lógico predeterminado (AND).
     *
     * @param condicion                         Condición
     */
    public void add(String condicion) {
        l_condiciones.add(condicion);
        l_operadores_logicos.add(OP_LOGICO_PREDETERMINADO);
    }

    
    /**
     * Añade una condición que se ligará con el operador lógico AND.
     *
     * @param condicion                         Condición
     */
    public void addAnd(String condicion) {
        l_condiciones.add(condicion);
        l_operadores_logicos.add(OP_LOGICO_AND);
    }
    

    /**
     * Añade una condición que se ligará con el operador lógico OR.
     * La primera condición no se puede ligar con OR.
     *
     * @param condicion                         Condición
     */
    public void addOr(String condicion) {
        l_condiciones.add(condicion);
        l_operadores_logicos.add(OP_LOGICO_OR);
    }

    
    /**
     * Retorna el número de condiciones que hay establecidas.
     *
     * @return                                  Número de condiciones
     */
    public int size() {
        return l_condiciones.size();
    }
    
    
    /**
     * Obtiene la cláusula "where" que contiene las condiciones establecidas.
     *
     * @return                                  Cláusula "where"
     */
    @Override
    public String toString() {
        
        if (Arrays.size(l_condiciones) == 0) return "";
        String condicion_inicial = l_condiciones.get(0);
         
        StringBuilder where = new StringBuilder(" WHERE (" + condicion_inicial + ")");
        for (int i = 1; i < Arrays.size(l_condiciones); i++) {
            String condicion = l_condiciones.get(i);
            String operador = l_operadores_logicos.get(i);
            
            where.append(" " + operador + " (" + condicion + ")");
        }
         
        where.append(" ");
        return where.toString();
    }
    
    
}
