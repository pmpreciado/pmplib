/**
 * DbCore.java
 *
 * Creado el 22-oct-2015, 13:44:45
 */

package es.pmp.pmplib.db;

import es.pmp.pmplib.Comun;
import es.pmp.pmplib.errores.Errores;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * Funciones de acceso a la base de datos, de bajo nivel.
 *
 * @author Pedro María Preciado
 */
public class DbCore {

    
    /**
     * Crea la instancia de la clase.
     */
    public DbCore() {
    }
    
    
    /**
     * Comprueba que la conexión dada sea válida.
     * En caso de que no lo sea, lanza una excepción.
     * 
     * @param conexion                          Conexión a comprobar
     * 
     * @throws Exception                        Conexión no establecida
     */
    private static void checkConexion(Connection conexion) throws Exception {
        if (conexion == null) {
            String mensaje = Errores.getMensajeError(Errores.ERR_BD_CONEXION_NO_ESTABLECIDA);
            throw new Exception(mensaje);
        }
    }
    
    
    /**
     * Cambia el estado del modo auto-commit para la conexión dada.
     *
     * @param conexion                          Conexión con la base de datos
     * @param nuevo_estado                      Nuevo estado para el auto-commit
     * 
     * @throws Exception                        Error en el acceso a la base de datos
     *                                          Conexión no establecida
     */
    public static void setAutoCommit(Connection conexion, boolean nuevo_estado) throws Exception {
        
        checkConexion(conexion);
        try {
            conexion.setAutoCommit(nuevo_estado);
        } catch (SQLException sqlex) {
            String mensaje = Errores.getMensajeError(Errores.ERR_BD_ACCESO_BD);
            throw new Exception(mensaje, sqlex);
        }
    }
    

    /**
     * Obtiene el estado del modo auto-commit para la conexión dada.
     *
     * @param conexion                          Conexión con la base de datos
     * @return nuevo_estado                     Estado del modo auto-commit
     * 
     * @throws Exception                        Error en el acceso a la base de datos
     *                                          Conexión no establecida
     */
    public static boolean getAutoCommit(Connection conexion) throws Exception {
        
        checkConexion(conexion);
        
        try {
            return conexion.getAutoCommit();
        } catch (SQLException sqlex) {
            String mensaje = Errores.getMensajeError(Errores.ERR_BD_ACCESO_BD);
            throw new Exception(mensaje, sqlex);
        }
    }    
    
    
    /**
     * Hace un commit en la conexión dada, haciendo efectivos todos los cambios efectuados en la base de datos desde el último commit.
     * Sólo tiene efecto en modo auto-commit desactivado.
     *
     * @param conexion                          Conexión con la base de datos
     * 
     * @throws Exception                        Error en el acceso a la base de datos
     */
    public static void commit(Connection conexion) throws Exception {
        checkConexion(conexion);
        
        try {
            conexion.commit();
        } catch (SQLException sqlex) {
            String mensaje = Errores.getMensajeError(Errores.ERR_BD_ACCESO_BD);
            throw new Exception(mensaje, sqlex);
        }
    }
    

    /**
     * Hace un rollback en la conexión dada, deshaciendo todos los cambios efectuados desde el último commit.
     * Sólo tiene efecto en modo auto-commit desactivado.
     *
     * @param conexion                          Conexión con la base de datos
     * 
     * @throws Exception                        Error en el acceso a la base de datos
     */
    public static void rollback(Connection conexion) throws Exception {

        checkConexion(conexion);
        
        try {
            conexion.rollback();
        } catch (SQLException sqlex) {
            Exception pi2ex = new Exception(Errores.ERR_BD_ACCESO_BD, sqlex);
            throw pi2ex;
        }
    }
    
    
    /**
     * Sustituye las '?' de una consulta SQL parametrizada por los correspondientes
     * parámetros.
     *
     * @param sql                               Consulta SQL con parámetros ('?')
     * @param parametros                        Objeto de clase Parametros con los parámetros a sustituir donde se encuentren los '?'
     * 
     * @return                                  Consulta SQL con los parámetros en lugar de las '?'
     */
    public static String sustituirParametrosEnConsulta(String sql, Parametros parametros)
    {
        if (parametros == null || parametros.size() == 0) {
            return sql;
        }
        
        int p = 0;
        while (p < parametros.size() && sql.indexOf('?') != -1) {
            String param = parametros.getAsString(p++);
            try {
                sql = sql.replaceFirst("\\?", "'" + param + "'");
            } catch (Exception ex) {
                return sql;
            }
        }

        return sql;
    }
    
    
    /**
     * Introduce los parámetros dentro de un objeto "PreparedStatement" DADO.
     *
     * @param pst                               Objeto de clase PreparedStatement
     * @param parametros                        Parámetros a introducir
     * 
     * @throws Exception                        Error en el acceso a la base de datos
     */
    private static void parametros2Statement(PreparedStatement pst, Parametros parametros) throws Exception
    {
        if (parametros == null) {
            return;
        }
        
        for (int i = 0; i < parametros.size(); i++) {
            Parametros.ClaseParametro param = parametros.get(i);

            try {
                switch (param.tipo) {
                    case Parametros.TP_INT:
                        int int_valor = ((Integer)param.valor);
                        pst.setInt(i + 1, int_valor);
                        break;

                    case Parametros.TP_LONG:
                        long long_valor = ((Long)param.valor);
                        pst.setLong(i + 1, long_valor);
                        break;
                        
                    case Parametros.TP_DOUBLE:
                        double double_valor = ((Double)param.valor);
                        pst.setDouble(i + 1, double_valor);
                        break;

                    case Parametros.TP_STRING:
                        String string_valor = (String)param.valor;
                        pst.setString(i + 1, string_valor);
                        break;

                    case Parametros.TP_BOOLEAN:
                        boolean boolean_valor = ((Boolean)param.valor);
                        int_valor = boolean_valor? 1 : 0;
                        pst.setInt(i + 1, int_valor);
                        break;

                    case Parametros.TP_BLOB:
                        long long_blob = param.long_blob;
                        InputStream is = (InputStream)param.valor;
                        pst.setBinaryStream(i + 1, is, (int)long_blob);
                        break;
                        
                    case Parametros.TP_BYTES:
                        byte [] bytes = (byte []) param.valor;
                        pst.setBytes(i + 1, (byte []) bytes);
                        break;
                }
            } catch (SQLException sqlex) {
                String mensaje = "Error en el parámetro de la consulta: " + param.valor;
                Logger logger = LogManager.getRootLogger();
                logger.error(mensaje, sqlex);
                try {
                    pst.setString(i + 1, "");
                } catch (SQLException sqlex2) {
                    mensaje = Errores.getMensajeError(Errores.ERR_BD_ACCESO_BD);
                    Exception ex = new Exception(mensaje, sqlex2);
                    throw ex;   
                }
            }
        }
    }

    
    /**
     * Obtiene el objeto de tipo "Statement" asociado al ResultSet suministrado.
     * En caso de excepción al cerrar, la registra.
     * 
     * @param rs                                ResultSet
     * 
     * @return                                  Objeto Statement asociado al ResultSet
     * 
     * @throws Exception                        Error al obtener la sentencia asociada al resultado
     */
    public static Statement getStatement(ResultSet rs) throws Exception {
        
        try {
            Statement st = rs.getStatement();
            return st;
        } catch (SQLException sqlex) {
            String mensaje = Errores.getMensajeError(Errores.ERR_BD_OBTENER_SENTENCIA_RESULTSET);
            Exception ex = new Exception(mensaje, sqlex);
            throw ex;
        }
    }
    
    
    
    /**
     * Cierra el objeto de tipo "Statement" suministrado.
     * En caso de excepción al cerrar, la registra.
     * El ResultSet asociado, si lo hubiera, también es cerrado automáticamente.
     * 
     * @param st                                Statement a cerrar. Si es 'null', no hace nada
     * 
     * @throws Exception                        Error al cerrar la sentencia
     */
    public static void cerrarStatement(Statement st) throws Exception {
        if (st == null) {
            return;
        }
        
        try {
            st.close();
        } catch (SQLException sqlex) {
            String mensaje = Errores.getMensajeError(Errores.ERR_BD_CERRAR_SENTENCIA);
            Exception ex = new Exception(mensaje, sqlex);
            throw ex;
        }
    }
    
    
    /**
     * Cierra el objeto de tipo "ResultSet" suministrado.
     * En caso de excepción al cerrar, la registra.
     * 
     * @param rs                                ResultSet a cerrar. Si es 'null', no hace nada
     * 
     * @throws Exception                        Error al cerrar el resultado
     */
    public static void cerrarResultSet(ResultSet rs) throws Exception {
        if (rs == null) {
            return;
        }
        
        try {
            rs.close();
        } catch (SQLException sqlex) {
            String mensaje = Errores.getMensajeError(Errores.ERR_BD_CERRAR_RESULTADO);
            Exception ex = new Exception(mensaje, sqlex);
            throw ex;

        }
    }
    
    
    /**
     * Crea un objeto de tipo PreparedStatement para la conexión dada, y con la consulta SQL dada.
     * Una vez usado, el PreparedStatement obligatoriamente debe ser cerrado. Si se quedará abierto, no se podría cerrar la conexión con la base de datos.
     * 
     * @param conexion                          Conexión
     * @param sql                               Consulta SQL
     * 
     * @return                                  Objeto de tipo PreparedStatement
     * 
     * @throws Exception                        Error al preparar la sentencia
     */
    private static PreparedStatement prepararSentencia(Connection conexion, String sql) throws Exception {
        PreparedStatement pst = null;
        
        try {
            pst = conexion.prepareStatement(sql);
            return pst;
            
        } catch (SQLException sqlex) {
            String mensaje = Errores.getMensajeError(Errores.ERR_BD_PREPARAR_CONSULTA_SQL);
            mensaje += Comun.NL + "SQL: " + sql;
            Exception ex = new Exception(mensaje, sqlex);
            throw ex;
        
        }
    }
    
    
    /**
     * Ejecuta una consulta SQL de selección y retorna el resultado.
     * El resultado retornado está abierto, y es responsabilidad del invocador su cierre para liberar la conexión de la BD.
     * 
     * @param conexion                          Conexión con la base de datos
     * @param sql                               Consulta SQL con parámetros ('?')
     * @param parametros                        String [] con los parámetros a sustituir donde se encuentren los '?'
     * 
     * @return                                  ResultSet con el resultado de la consulta
     *                                          Una vez finalizado su uso, debe ser cerrado
     * 
     * @throws Exception                        Conexión no establecida
     */
    public static ResultSet consulta(Connection conexion, String sql, Parametros parametros) throws Exception
    {
        checkConexion(conexion);
        
        Logger logger = LogManager.getRootLogger();
        String consulta = sustituirParametrosEnConsulta(sql, parametros);
        
        PreparedStatement pst = null;
        ResultSet rs = null;
        
        try {
            pst = prepararSentencia(conexion, sql);
            parametros2Statement(pst, parametros);
            rs = pst.executeQuery();
            
        } catch (Exception ex) {
            String mensaje = Errores.getMensajeError(Errores.ERR_BD_PREPARAR_CONSULTA_SQL);
            mensaje += Comun.NL + "SQL: " + consulta;
            Exception ex2 = new Exception(mensaje, ex);
            throw ex2;
        }

        return rs;
    }
    
    
    /**
     * Ejecuta una consulta SQL de selección y retorna el resultado.
     * El resultado retornado está abierto, y es responsabilidad del invocador su cierre para liberar la conexión de la BD.
     * 
     * @param conexion                          Conexión con la base de datos
     * @param sql                               Consulta SQL a ejecutar
     * 
     * @return                                  ResultSet con el resultado de la consulta
     *                                          Una vez finalizado su uso, debe ser cerrado
     * 
     * @throws Exception                        Conexión no establecida
     *                                          Error al ejecutar la consulta
     */
    public static ResultSet consulta(Connection conexion, String sql) throws Exception
    {
        Parametros parametros = null;
        ResultSet rs = consulta(conexion, sql, parametros);
        return rs;
    }

    
    /**
     * Ejecuta sentencia SQL de actualización (INSERT, UPDATE) de forma parametrizada.
     * El Statement utilizado es cerrado al finalizar esta función.
     * 
     * @param conexion                          Conexión con la base de datos
     * @param sql                               Consulta SQL con parámetros ('?')
     * @param parametros                        Objeto de clase Parametros con los parámetros a sustituir donde se encuentren los '?'
     * 
     * @return                                  Número de registros afectados
     * 
     * @throws Exception                        Conexión no establecida
     *                                          Error al crear la consulta
     */
    public static int consultaActualizacion(Connection conexion, String sql, Parametros parametros) throws Exception
    {
        // Para depuración
        String consulta = sustituirParametrosEnConsulta(sql, parametros);
        
        checkConexion(conexion);

        int result = 0;
        PreparedStatement pst = null;
        try {
            pst = prepararSentencia(conexion, sql);
            parametros2Statement(pst, parametros);
            result = pst.executeUpdate();
            
        } catch (Exception ex) {
            cerrarStatement(pst);
            
            String mensaje = Errores.getMensajeError(Errores.ERR_BD_CONSULTA_SQL);
            mensaje += Comun.NL + "SQL: " + consulta;
            Exception ex2 = new Exception(mensaje, ex);
            throw ex2;
            
        } finally {
            cerrarStatement(pst);
        }
        
        return result;
    }

    
    /**
     * Ejecuta una consulta SQL de actualización (INSERT, UPDATE, DELETE).
     * El Statement utilizado es cerrado al finalizar esta función.
     * 
     * @param conexion                          Conexión con la base de datos
     * @param sql                               Consulta SQL a ejecutar
     * 
     * @return                                  Número de registros afectados
     * 
     * @throws Exception                        Conexión no establecida
     *                                          Error al crear la consulta
     */
    public int consultaActualizacion(Connection conexion, String sql) throws Exception
    {
        Parametros parametros = null;
        return consultaActualizacion(conexion, sql, parametros);
    }

}
