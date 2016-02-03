/**
 * DbResult.java
 *
 * Creado el 02-feb-2016, 19:41:20
 */

package es.pmp.pmplib.db;

import es.pmp.pmplib.Arrays;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Funciones para simplificar la obtención de resultados de las consultas.
 * 
 * @author pmpreciado
 */
public class DbResult {

    
    /**
     * Comprueba si un ResultSet está vacío.
     * 
     * @param rs                                Objeto ResultSet
     * @return                                  'true' si el resultado está vacío
     *                                          'false' en caso contrario
     * 
     * @throws SQLException         Error al acceder a la base de datos
     */
    public boolean vacio(ResultSet rs) throws SQLException {
        if (rs == null) {
            return true;
        }
        return !rs.next();
    }
    
    
    /**
     * Obtiene el número de columnas existentes en un objeto ResultSet.
     *
     * @param rs                                ResultSet
     * 
     * @return                                  Número de columnas en el ResultSet
     * 
     * @throws SQLException                     Error al acceder a la base de datos
     */
    public static int getNumCols(ResultSet rs) throws SQLException {
                
        ResultSetMetaData info_rs = rs.getMetaData();
        int num_cols = info_rs.getColumnCount();
        return num_cols;
    }      
    
    
    /**
     * Obtiene el número de filas existentes en un objeto ResultSet.
     *
     * @param rs                                ResultSet
     * 
     * @return                                  Número de filas en el ResultSet
     * 
     * @throws SQLException                     Error al acceder a la base de datos
     */
    public int getNumFilas(ResultSet rs) throws SQLException {
        
        int fila_actual = rs.getRow();
        rs.last();
        int num_filas = rs.getRow();
        rs.absolute(fila_actual);

        return num_filas;
    }     
    
    
    
    /**
     * A partir de un objeto ResultSet, retorna un String[] con el primer registro del mismo.
     * Opcionalmente, se puede cerrar el ResultSet tras obtener el resultado.
     *
     * @param rs                                ResultSet
     * @param cerrar_rs                         Si es 'true', cierra el ResultSet tras obtener el resultado 
     * 
     * @return                                  String [] con las columnas del primer registro
     *                                          Array vacío, si la consulta no dio ningún resultado
     * 
     * @throws SQLException                     Error al acceder a la base de datos
     * @throws Exception                        Error al cerrar el resultado
     */
    public static String [] getRegistro(ResultSet rs, boolean cerrar_rs) throws SQLException, Exception {
        if (rs == null) {
            return new String[0];
        }
        
        if (!rs.next()) {
            return new String[0];
        }
        
        int num_cols = getNumCols(rs);
        
        String [] result = new String [num_cols];
        
        for (int i = 1; i < num_cols + 1; i++) {        // Las columnas se empiezan a numerar por 1
            String campo = rs.getString(i);
            result[i - 1] = campo;
        }
        
        if (cerrar_rs) {
            DbCore.cerrarResultSet(rs);
        }
        
        return result;
    }
    
    
    /**
     * A partir de un objeto ResultSet, retorna un String [] con el primer registro del mismo.
     * Tras obtener el resultado, se cierra el ResultSet.
     *
     * @param rs                                ResultSet
     * 
     * @return                                  String [] con las columnas del primer registro
     *                                          Array vacío, si la consulta no dio ningún resultado
     * 
     * @throws SQLException                     Error al acceder a la base de datos
     * @throws Exception                        Error al cerrar el resultado
     */
    public static String [] getRegistro(ResultSet rs) throws SQLException, Exception {
        boolean cerrar_rs = true;
        String [] result = getRegistro(rs, cerrar_rs);
        return result;
    }
    

    /**
     * A partir de un objeto ResultSet, retorna una lista con todos los registros del mismo.
     *
     * @param rs                                ResultSet
     * @param cerrar_rs                         Si es 'true', cierra el ResultSet tras obtener el resultado 
     * 
     * @return                                  Lista con los registros del ResultSet
     *                                          Lista vacía, si la consulta no dio ningún resultado)
     * 
     * @throws SQLException                     Error al acceder a la base de datos
     * @throws Exception                        Error al cerrar el resultado
     */
    public static List <String []> getListaRegistros(ResultSet rs, boolean cerrar_rs) throws SQLException, Exception {
        
        List <String []> l_registros = new ArrayList();
        
        if (rs == null) {
            return l_registros;
        }
        
        int num_cols = getNumCols(rs);
        
        while (rs.next()) {
            String [] fila = new String[num_cols];
            for (int i = 1; i < num_cols + 1; i++) {        // Las columnas se empiezan a numerar por 1
                String campo = rs.getString(i);
                fila[i - 1] = campo;
            }
            l_registros.add(fila);
        }
        
        if (cerrar_rs) {
            DbCore.cerrarResultSet(rs);
        }
        
        return l_registros;
    }
    

    /**
     * A partir de un objeto ResultSet, retorna una lista con todos los registros del mismo.
     * Tras obtener el resultado, se cierra el ResultSet.
     * 
     * @param rs                                ResultSet
     * 
     * @return                                  Lista con los registros del ResultSet
     *                                          Lista vacía, si la consulta no dio ningún resultado)
     * 
     * @throws SQLException                     Error al acceder a la base de datos
     * @throws Exception                        Error al cerrar el resultado
     */
    public static List <String []> getListaRegistros(ResultSet rs) throws SQLException, Exception {
        
        boolean cerrar_rs = true;
        List <String []> l_registros = getListaRegistros(rs, cerrar_rs);
        return l_registros;
    }
    
    
    /**
     * A partir de un objeto ResultSet, retorna un String[][] con todos los registros del mismo.
     *
     * @param rs                                ResultSet
     * @param cerrar_rs                         Si es 'true', cierra el ResultSet tras obtener el resultado 
     * 
     * @return                                  String [][] con los registros del ResultSet
     *                                          Array vacío, si la consulta no dio ningún resultado)
     * 
     * @throws SQLException                     Error al acceder a la base de datos
     * @throws Exception                        Error al cerrar el resultado
     */
    public static String [][] getRegistros(ResultSet rs, boolean cerrar_rs) throws SQLException, Exception {
        
        List <String []> l_registros = getListaRegistros(rs, cerrar_rs);

        int num_registros = Arrays.size(l_registros);
        
        if (num_registros == 0) {
            return new String [0][0];
        }
        
        //int num_columnas = l_registros.get(0).length;
        //String [][] arr_registros = new String[num_registros][num_columnas];
        
        String [][] arr_registros = (String [][]) l_registros.toArray();
        return arr_registros;
    }

    
    /**
     * A partir de un objeto ResultSet, retorna un String[][] con todos los registros del mismo.
     * Tras obtener el resultado, se cierra el ResultSet.
     * 
     * @param rs                                ResultSet
     * 
     * @return                                  String [][] con los registros del ResultSet
     *                                          Array vacío, si la consulta no dio ningún resultado)
     * 
     * @throws SQLException                     Error al acceder a la base de datos
     * @throws Exception                        Error al cerrar el resultado
     */
    public String [][] getRegistros(ResultSet rs) throws SQLException, Exception {
        
        boolean cerrar_rs = true;
        String [][] arr_registros = getRegistros(rs, cerrar_rs);
        return arr_registros;
    }


    /**
     * A partir de un objeto ResultSet, retorna un String [] con el valor de la primera columna de todas las filas.
     *
     * @param rs                                ResultSet
     * @param cerrar_rs                         Si es 'true', cierra el ResultSet tras obtener el resultado 
     * 
     * @return                                  String [] con el valor de la primera columna de todas las filas
     *                                          Array vacío, si la consulta no dio ningún resultado)
     * 
     * @throws SQLException                     Error al acceder a la base de datos
     * @throws Exception                        Error al cerrar el resultado
     */
    public static String [] getColumna(ResultSet rs, boolean cerrar_rs) throws SQLException, Exception {
        if (rs == null) {
            return new String[0];
        }
        
        List <String> l = new ArrayList <> ();
        
        while (rs.next()) {
            String campo = rs.getString(1);
            l.add(campo);
        }

        if (cerrar_rs) {
            DbCore.cerrarResultSet(rs);
        }

        if (l.isEmpty()) {
            return new String[0];
        }
        
        String [] arr_registros = (String []) l.toArray();
        return arr_registros;
    }
    
    
    /**
     * A partir de un objeto ResultSet, retorna un String [] con el valor de la primera columna de todas las filas.
     * Tras obtener el resultado, se cierra el ResultSet.
     * 
     * @param rs                                ResultSet
     * 
     * @return                                  String [] con el valor de la primera columna de todas las filas
     *                                          Array vacío, si la consulta no dio ningún resultado)
     * 
     * @throws SQLException                     Error al acceder a la base de datos
     * @throws Exception                        Error al cerrar el resultado
     */
    public static String [] getColumna(ResultSet rs) throws SQLException, Exception {
        boolean cerrar_rs = true;

        String [] arr_registros = getColumna(rs, cerrar_rs);
        return arr_registros;
    }
    
    

    /**
     * A partir de un objeto ResultSet, donde se espera encontrar un dato numérico en el primer registro de la primera fila, se retorna ese dato numérico.
     *
     * @param rs                                Objeto ResultSet
     * @param cerrar_rs                         Si es 'true', cierra el ResultSet tras obtener el resultado 
     * 
     * @return                                  Entero con el valor numérico del primer registro de la primera fila
     *                                          -1, si el resultado está vacío
     *                              
     * @throws SQLException                     Error al acceder a la base de datos
     * @throws Exception                        Error al cerrar el resultado
     */
    public static int getValor(ResultSet rs, boolean cerrar_rs) throws SQLException, Exception {
        
        int valor = -1;
        
        if (rs == null) {
            return -1;
        }
        
        rs.next();
        if (rs.getRow() == 0) {
            valor = -1;
        } else {
            String campo = rs.getString(1);

            if (campo == null) {
                valor = -1;
            } else {
                valor = Integer.parseInt(campo);
            }
        }
        
        if (cerrar_rs) {
            DbCore.cerrarResultSet(rs);
        }
        
        return valor;
    }

    

    /**
     * A partir de un objeto ResultSet, donde se espera encontrar un dato numérico en el primer registro de la primera fila, se retorna ese dato numérico.
     * Tras obtener el resultado, se cierra el ResultSet.
     * 
     * @param rs                                Objeto ResultSet
     * 
     * @return                                  Entero con el valor numérico del primer registro de la primera fila
     *                                          -1, si el resultado está vacío
     *                              
     * @throws SQLException                     Error al acceder a la base de datos
     * @throws Exception                        Error al cerrar el resultado
     */
    public static int getValor(ResultSet rs) throws SQLException, Exception {

        boolean cerrar_rs = true;
        int valor = getValor(rs, cerrar_rs);
        return valor;
    }

    
    /**
     * A partir de un objeto ResultSet, retorna un InputStream con un campo blob del mismo.
     *
     * @param rs                                Objeto ResultSet
     * @param indice                            Índice de la columna donde se aloja el blob (la primera es 1)
     * @param cerrar_rs                         Si es 'true', cierra el ResultSet tras obtener el resultado 
     *
     * @return                                  InputStream con el blob 
     *                                          'null', si la consulta no dio ningún resultado
     * 
     * @throws SQLException                     Error al acceder a la base de datos
     * @throws Exception                        Error al cerrar el resultado
     */
    public static InputStream getBlob(ResultSet rs, int indice, boolean cerrar_rs) throws SQLException, Exception {

        if (rs == null) {
            return null;
        }
        
        InputStream is = null;
        if (rs.next()) {
            is = rs.getBinaryStream(indice);
        }
        
        if (cerrar_rs) {
            DbCore.cerrarResultSet(rs);
        }
        
        return is;
    }
    
    
    /**
     * A partir de un objeto ResultSet, retorna un InputStream con un campo blob del mismo.
     * Tras obtener el resultado, se cierra el ResultSet.
     * 
     * @param rs                                Objeto ResultSet
     * @param indice                            Índice de la columna donde se aloja el blob (la primera es 1)
     *
     * @return                                  InputStream con el blob 
     *                                          'null', si la consulta no dio ningún resultado
     * 
     * @throws SQLException                     Error al acceder a la base de datos
     * @throws Exception                        Error al cerrar el resultado
     */
    public static InputStream getBlob(ResultSet rs, int indice) throws SQLException, Exception {
        boolean cerrar_rs = true;
        InputStream is = getBlob(rs, indice, cerrar_rs);
        return is;
    }
    
    
    /**
     * A partir de un objeto ResultSet, retorna los bytes correspondientes a un campo blob del mismo.
     *
     * @param rs                                Objeto ResultSet
     * @param indice                            Índice de la columna donde se aloja el blob (la primera es 1)
     * @param cerrar_rs                         Si es 'true', cierra el ResultSet tras obtener el resultado 
     * 
     * @return                                  byte [] con el blob ('null', si la consulta no dio ningún resultado)
     * 
     * @throws SQLException                     Error al acceder a la base de datos
     * @throws Exception                        Error al cerrar el resultado
     */
    public static byte [] getBytes(ResultSet rs, int indice, boolean cerrar_rs) throws SQLException, Exception {
        
        if (rs == null) {
            return null;
        }
        
        byte [] result = null;
        
        if (rs.next()) {
            Blob blob = rs.getBlob(1);
            result = blob.getBytes(1, (int)blob.length());
        }
        
        if (cerrar_rs) {
            DbCore.cerrarResultSet(rs);
        }
        
        return result;
    }
    
    
    /**
     * A partir de un objeto ResultSet, retorna los bytes correspondientes a un campo blob del mismo.
     * Tras obtener el resultado, se cierra el ResultSet.
     * 
     * @param rs                                Objeto ResultSet
     * @param indice                            Índice de la columna donde se aloja el blob (la primera es 1)
     * 
     * @return                                  byte [] con el blob ('null', si la consulta no dio ningún resultado)
     * 
     * @throws SQLException                     Error al acceder a la base de datos
     * @throws Exception                        Error al cerrar el resultado
     */
    public static byte [] getBytes(ResultSet rs, int indice) throws SQLException, Exception {
        
        boolean cerrar_rs = true;
        byte [] result = getBytes(rs, indice, cerrar_rs);
        return result;
    }
    
    
}
