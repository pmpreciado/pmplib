/**
 * DbQuery.java
 *
 * Creado el 03-feb-2016, 10:59:03
 */

package es.pmp.pmplib.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;

/**
 * Funciones para simplificar las consultas comunes a bases de datos.
 * 
 * @author pmpreciado
 */
public class DbQuery {

    
    /**
     * Retorna los registros de una tabla que cumplan las condiciones especificadas en el "where".
     * Tras obtener el resultado, se cierra el ResultSet.
     * 
     * @param conexion                          Conexión con la base de datos
     * @param tabla                             Tabla de la que se quieren obtener los registros
     * @param where                             Condiciones para la selección
     *                                          Si es 'null', se retornan todos los registros
     * 
     * @return                                  String [][] con los registros
     * 
     * @throws Exception                        Error al ejecutar la consulta
     */
    public static String [][] select(Connection conexion, String tabla, Where where) throws Exception {
        
        if (where == null) {
            where = new Where();
        }
        
        String sql = "SELECT * FROM " + tabla + where.toString();
        
        ResultSet rs = DbCore.consulta(conexion, sql);
        boolean cerrar_rs = true;
        String [][] result = DbResult.getRegistros(rs, cerrar_rs);
        
        return result;
    }
    
    
    /**
     * Retorna todos los registros de una tabla.
     * Tras obtener el resultado, se cierra el ResultSet.
     * 
     * @param conexion                          Conexión con la base de datos
     * @param tabla                             Tabla de la que se quieren obtener los registros
     * 
     * @return                                  String [][] con los registros
     * 
     * @throws Exception                        Error al ejecutar la consulta
     */
    public static String [][] selectAll(Connection conexion, String tabla) throws Exception {
        Where where = null;
        String [][] result = select(conexion, tabla, where);
        return result;
    }
    
    
    /**
     * Comprueba si existe un registro que cumple las condiciones dadas dentro de la tabla.
     * Tras hacer la consulta, se cierra el ResultSet.
     * 
     * @param conexion                          Conexión con la base de datos
     * @param tabla                             Tabla en la que se va a buscar
     * @param where                             Condiciones para la búsqueda
     * @param parametros                        Parámetros a sustituir donde se encuentren los '?'
     * 
     * @return                                  'true' si existe el registro que cumple con las condiciones
     *                                          'false' si no existe
     * 
     * @throws Exception                        Error al ejecutar la consulta
     */
    public static boolean existe(Connection conexion, String tabla, Where where, Parametros parametros) throws Exception
    {
        String sql = "SELECT COUNT(*) FROM " + tabla + " " + where.toString();
        
        ResultSet rs = DbCore.consulta(conexion, sql, parametros);
        boolean cerrar_rs = true;
        int valor = DbResult.getValor(rs, cerrar_rs);
        boolean existe = valor > 0;
        
        return existe;
    }

    
    /**
     * Comprueba si existe un registro que cumple las condiciones dadas dentro de la tabla.
     * Tras hacer la consulta, se cierra el ResultSet.
     * 
     * @param conexion                          Conexión con la base de datos
     * @param tabla                             Tabla en la que se va a buscar
     * @param where                             Condiciones para la búsqueda
     * 
     * @return                                  'true' si existe el registro que cumple con las condiciones
     *                                          'false' si no existe
     * 
     * @throws Exception                        Error al ejecutar la consulta
     */
    public static boolean existe(Connection conexion, String tabla, Where where) throws Exception
    {
        Parametros parametros = null;
        boolean existe = existe(conexion, tabla, where, parametros);
        return existe;
    }
    
    
    /**
     * Comprueba si existe un registro en una tabla, permitiendo especificar una condición sombre un campo.
     * Tras hacer la consulta, se cierra el ResultSet.
     * 
     * @param conexion                          Conexión con la base de datos
     * @param tabla                             Tabla en la que se va a buscar
     * @param nombre_campo                      Nombre del campo al que se aplica la condición (por ejemplo, "id_registro")
     * @param valor_campo                       Valor del campo para la condición (por ejemplo, 32 ó "32")
     * 
     * @return                                  'true' si existe el registro que cumple con las condiciones
     *                                          'false' si no existe
     * 
     * @throws Exception                        Error al ejecutar la consulta
     */
    public static boolean existe(Connection conexion, String tabla, String nombre_campo, Object valor_campo) throws Exception
    {
        Where where = new Where();
        where.add(nombre_campo + "  = " + valor_campo.toString());
        boolean existe = existe(conexion, tabla, where);
        
        return existe;
    }
    
    
    /**
     * Obtiene el siguiente valor disponible para un identificador único de una tabla dada.
     * Si la tabla está vacía, retorna un 1.
     * Permite acotar la consulta especificando una cláusula "where".
     * Tras hacer la consulta, se cierra el ResultSet.
     * 
     * @param conexion                          Conexión con la base de datos
     * @param tipo_conexion                     Tipo de conexión (CinfoConexionBd.TC_xxx)
     * @param tabla                             Nombre de la tabla para la que se busca el contador
     * @param campo_contador                    Nombre del campo que guarda el contador (de tipo numérico)
     * @param where                             Condiciones para la selección
     *                                          Puede ser 'null'
     * 
     * @return                                  Siguiente valor disponible para el identificador único
     * 
     * @throws Exception                        Error al ejecutar la consulta
     */
    public static int getNextId(Connection conexion, String tipo_conexion, String tabla, String campo_contador, Where where) throws Exception
    {
        if (where == null) {
            where = new Where();
        }
        
        String sql;

        if (tipo_conexion.equalsIgnoreCase(CinfoConexionBd.TC_MYSQL)) {
            sql = "SELECT MAX(" + campo_contador + ") + 1 " +
                     "FROM " + tabla + " " +
                     where;

        } else {
            sql = "SELECT FIRST 1 " + campo_contador + " + 1 "+
                     "FROM " + tabla + " " +
                     where + " " +
                     "ORDER BY 1 DESC";
        }

        ResultSet rs = DbCore.consulta(conexion, sql);
        boolean cerrar_rs = true;
        int next_id = DbResult.getValor(rs, cerrar_rs);

        if (next_id <= 0) {
            next_id = 1;
        }
        
        return next_id;
    }
        
    
    /**
     * Obtiene el siguiente valor disponible para un identificador único de una tabla dada.
     * Si la tabla está vacía, retorna un 1.
     * Permite acotar la consulta especificando una cláusula "where".
     * Tras hacer la consulta, se cierra el ResultSet.
     * 
     * @param conexion                          Conexión con la base de datos
     * @param tipo_conexion                     Tipo de conexión (CinfoConexionBd.TC_xxx)
     * @param tabla                             Nombre de la tabla para la que se busca el contador
     * @param campo_contador                    Nombre del campo que guarda el contador (de tipo numérico)
     * 
     * @return                                  Siguiente valor disponible para el identificador único
     * 
     * @throws Exception                        Error al ejecutar la consulta
     */
    public static int getNextId(Connection conexion, String tipo_conexion, String tabla, String campo_contador) throws Exception
    {
        Where where = null;
        int next_id = getNextId(conexion, tipo_conexion, tabla, campo_contador, where);
        return next_id;
    }
    

    /**
     * Da de alta un registro en una tabla.
     * 
     * @param conexion                          Conexión con la base de datos
     * @param tabla                             Nombre de la tabla en la que se insertará el registro
     * @param l_valores_campos                  Valores de los campos (debe haber uno por cada campo)
     * 
     * @return                                  Número de registros afectados tras la consulta
     * 
     * @throws Exception                        Error en la operación
     */
    public static int alta(Connection conexion, String tabla, List <Object> l_valores_campos) throws Exception
    {
        Parametros param = new Parametros();
        param.add(l_valores_campos);
        
        String sql = "INSERT INTO " + tabla + " " +
                "VALUES " + param.getCadenaValues();
        
        int result = DbCore.consultaActualizacion(conexion, sql, param);
        return result;
    }
    
    
    /**
     * Esteblece un valor en un campo de un registro.
     * 
     * @param conexion                          Conexión con la base de datos
     * @param tabla                             Nombre de la tabla en la que se modificará el registro
     * @param nombre_campo                      Nombre del campo a modificar (por ejemplo, "edad")
     * @param valor_campo                       Nuevo valor para el campo (por ejemplo, 30 ó "30")
     * @param where                             Condiciones que debe cumplir el registro (o registros) a modificar
     *                                          Puede ser 'null'
     * 
     * @return                                  Número de registros afectados tras la consulta
     * 
     * @throws Exception                        Error en la operación
     */
    public static int setValor(Connection conexion, String tabla, String nombre_campo, String valor_campo, Where where) throws Exception
    {
        if (where == null) {
            where = new Where();
        }
        
        Parametros param = new Parametros();
        param.add(valor_campo);
        
        String sql = "UPDATE " + tabla + " " +
                "SET " + nombre_campo + " = ? " +
                where.toString();
        
        int result = DbCore.consultaActualizacion(conexion, sql, param);
        return result;
    }
    

    /**
     * Elimina un registro de una tabla.
     * 
     * @param conexion                          Conexión con la base de datos
     * @param tabla                             Nombre de la tabla en la que se modificará el registro
     * @param where                             Condiciones que debe cumplir el registro (o registros) a eliminar
     *                                          Puede ser 'null' (eliminaría todos los registros)
     * 
     * @return                                  Número de registros afectados tras la consulta
     * 
     * @throws Exception                        Error en la operación
     */
    public static int delete(Connection conexion, String tabla, Where where) throws Exception
    {
        if (where == null) {
            where = new Where();
        }
        
        String sql = "DELETE FROM " + tabla + " " +
                where.toString();
        
        int result = DbCore.consultaActualizacion(conexion, sql);
        return result;
    }
    
}
