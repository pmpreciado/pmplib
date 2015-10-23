/**
 * PoolBoneCpManager.java
 *
 * Creado el 22-oct-2015, 11:08:49
 */

package es.pmp.pmplib.db;

import es.pmp.pmplib.errores.Errores;
import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;
import es.pmp.pmplib.Comun;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import org.apache.logging.log4j.LogManager;


/**
 * Funciones para simplificar la gestión de un pool de conexiones basados en BoneCP.
 * http://jolbox.com/
 * 
 * BoneCP implementa pooles basados en particiones.
 * Una partición es un mecanismo para que el pool sea más rápido. De modo que, en lugar de tener, digamos, 100 conexiones
 * con 100 hilos compitiendo por obtener una conexión. la partición permite hacer conjuntos de conexiones más pequeños,
 * por ejemplo, 4 particiones x 25 conexiones, de modo que hay menos bloqueos y mayor rendimiento.
 * 
 * Uso de esta clase:
 *   1) Invocar al constructor para crear la instancia
 *   2) Inicializar la instancia usando uno de los métodos initPool
 *   3) Usar el método getPool para obtener el pool de conexiones que
 *   4) Cerrar el pool
 *
 * @author pmpreciadoo
 */
public class PoolBoneCpManager {

    /** Sentencias SQL para probar las conexiones */
    private static final String SQL_TEST_MYSQL = "SELECT 1";
    private static final String SQL_TEST_INFORMIX = "SELECT 1 FROM informix.systables WHERE tabid = 1";
    
    
    /** Parámetros de configuración predeterminados para los pooles */

        /**
        * Establece el número predeterminado de particiones.
        * Una partición es un mecanismo para que el pool se más rápido. De modo que, en lugar de tener, digamos, 100 conexiones
        * con 100 hilos compitiendo por obtener una conexión. la partición permite hacer conjuntos de conexiones más pequeños,
        * por ejemplo, 4 particiones x 25 conexiones, de modo que hay menos bloqueos y mayor rendimiento.
        */
        public static final int POOL_NUMERO_PARTICIONES = 3;

        /** Mínimo número de conexiones por partición */
        public static final int POOL_MIN_CONEXIONES_POR_PARTICION = 2;

        /** Máximo número de conexiones por partición */
        public static final int POOL_MAX_CONEXIONES_POR_PARTICION = 10;

        /** Tiempo que se esperará antes de que se dé por fallida la solicitud de una conexión (milisegundos) */
        public static final int POOL_SOLICITAR_CONEXION_TIMEOUT_MS = 8000;

        /** Tiempo de espera para que se libere una conexión no utilizada (minutos) */
        public static final int POOL_LIBERAR_CONEXION_OCIOSA_TIMEOUT_MIN = 60;

        /** Tiempo de espera para cerrar una conexión, esté ociosa o no (segundos). Si la conexión está en uso, se esperará a devolverla al pool antes de cerrarla */
        public static final int POOL_LIBERAR_CONEXION_TIMEOUT_S = 3600;

        /** Número de veces que se intentará obtener una conexión en caso de error a la primera */
        public static final int POOL_NUMERO_INTENTOS_ADQUIRIR_CONEXION = 3;
        

    
    /** Acceso al pool */
    private BoneCP pool;


    
    /** 
     * Crea la instancia de la clase.
     */
    public PoolBoneCpManager() {
        pool = null;
    }


    
    /**
     * Obtiene una sentecia SQL usada para probar la disponibilidad de la conexión.
     * La sentencia es específica para cada base de datos.
     * 
     * @return                              Sentencia SQL
     *                                      'null' si no está definida
     */
    private static String getSqlTest(String tipo_conexion) {
        
        String sql = null;
        if (tipo_conexion.equalsIgnoreCase(CinfoConexionBd.TC_MYSQL)) {
            sql = SQL_TEST_MYSQL;
        } else if (tipo_conexion.equalsIgnoreCase(CinfoConexionBd.TC_INFORMIX)) {
            sql = SQL_TEST_INFORMIX;
        }
        
        return sql;
    }
    
    

    /** 
     * Crea, inicializa y retorna una instancia del pool de conexiones BoneCP.
     * El pool es inicializado con la configuración suministrada.
     * 
     * @param info_conexion                 Información de la conexión
     * @param config                        Parámetros de la conexión
     * 
     * @throws Exception                    No se encuentra el driver JDBC para el acceso a la base de datos
     *                                      Error al establecer la conexión con la base de datos
     *                                      Error al cerrar la conexión con la base de datos
     * 
     * @return                              Pool de conexiones
     */
    public BoneCP initPool(CinfoConexionBd info_conexion, BoneCPConfig config) throws Exception {
        
        Connection conexion = null;
        String driver = info_conexion.getDriver();
        
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException cnfex) {
            String mensaje = Errores.getMensaje(Errores.ERR_BD_DRIVER_NO_ENCONTRADO, driver);
            throw new Exception(mensaje, cnfex);
        }
        
        String sql_test = null;
        try {
            pool = new BoneCP(config); // setup the connection pool

            // Probamos el pool
            conexion = pool.getConnection();
            if (conexion == null) {
                String mensaje = Errores.getMensaje(Errores.ERR_BD_ESTABLECER_CONEXION, info_conexion.getNombre());
                throw new Exception(mensaje);
            }
            
            // Conexión correcta. Probamos una consulta
            sql_test = getSqlTest(info_conexion.getTipo());
            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery(sql_test);
            rs.close();
            
            conexion.close();
            
        } catch (SQLException sqlex) {
            String mensaje = Errores.getMensaje(Errores.ERR_BD_CONSULTA_BD, info_conexion.getNombre());
            LogManager.getRootLogger().debug("SQL: " + sql_test);
            
            throw new Exception(mensaje, sqlex);
            
        } finally {
            if (conexion != null) {
                try {
                    conexion.close();
                } catch (SQLException sqlex) {
                    String mensaje = Errores.getMensaje(Errores.ERR_BD_CERRAR_CONEXION, info_conexion.getNombre());
                    throw new Exception(mensaje, sqlex);
                }
                
            }
        }
        
        return pool;
    }
    
    
    /** 
     * Crea, inicializa y retorna una instancia del pool de conexiones BoneCP.
     * El pool es inicializado con la configuración predeterminada.
     * 
     * @param info_conexion                 Información de la conexión
     * 
     * @throws Exception                    No se encuentra el driver JDBC para el acceso a la base de datos
     *                                      Error al establecer la conexión con la base de datos
     *                                      Error al cerrar la conexión con la base de datos
     */
    public void initPool(CinfoConexionBd info_conexion) throws Exception {

        BoneCPConfig config = null;
        try {
            config = new BoneCPConfig();
        } catch (Throwable th) {
            String mensaje = Errores.getMensaje(Errores.ERR_BD_CREAR_POOL);
            Exception ex = new Exception(mensaje, th);
            LogManager.getRootLogger().debug("No se puede crear el pool de base de datos. Posiblemente falte alguna dependencia (jar)");
            throw ex;
        }
        
        if (info_conexion.getTipo() == null) {
            String mensaje = Errores.getMensaje(Errores.ERR_BD_CREAR_POOL);
            LogManager.getRootLogger().debug("Tipo de conexión no definido");
            throw new Exception(mensaje);
        }
        
        config.setPoolName(info_conexion.getNombre());
        config.setJdbcUrl(info_conexion.getURL());
        config.setUsername(info_conexion.getUser()); 
        config.setPassword(info_conexion.getPassword());
        config.setMinConnectionsPerPartition(POOL_MIN_CONEXIONES_POR_PARTICION);
        config.setMaxConnectionsPerPartition(POOL_MAX_CONEXIONES_POR_PARTICION);
        config.setPartitionCount(POOL_NUMERO_PARTICIONES);
        config.setConnectionTimeoutInMs(POOL_SOLICITAR_CONEXION_TIMEOUT_MS);
        config.setIdleMaxAgeInMinutes(POOL_LIBERAR_CONEXION_OCIOSA_TIMEOUT_MIN);
        config.setMaxConnectionAgeInSeconds(POOL_LIBERAR_CONEXION_TIMEOUT_S);
        config.setAcquireRetryAttempts(POOL_NUMERO_INTENTOS_ADQUIRIR_CONEXION);
        String sql_test = getSqlTest(info_conexion.getTipo());
        config.setConnectionTestStatement(sql_test);
        
        pool = initPool(info_conexion, config);
    }

    
    /**
     * Obtiene el pool de conexiones.
     * 
     * @return                              Pool de conexiones
     */
    public BoneCP getPool() {
        return pool;
    }
    
    
    /**
     * Obtiene una cadena con el estado del pool de conexiones.
     * 
     * @return                              Cadena con el estado del pool
     */
    public String getInfoPool() {
        StringBuilder info = new StringBuilder();
        
        if (pool == null) {
            info.append("El pool no está creado");
        } else {

            int conexiones_libres = pool.getTotalFree();
            int conexiones_creadas_totales = pool.getTotalCreatedConnections();
            int conexiones_liberadas_totales = pool.getTotalLeased();
            
            BoneCPConfig config = pool.getConfig();
           
            info.append("Pool " + config.getPoolName() + ": ");
            info.append("Libres: " + conexiones_libres + " / ");
            info.append("Tot. creadas: " + conexiones_creadas_totales + " / ");
            info.append("Tot. liberadas: " + conexiones_liberadas_totales + Comun.NL);
        }
        
        info.append(Comun.NL);
        
        return info.toString();
    }
    
    
    
    /**
     * Desregistra los drivers JDBC.
     * 
     * @return                                  Lista con las excepciones que se han producido al descargar los drivers
     *                                          Si no ha habido ninguna, lista vacía
     */
    public List <Exception> desregistrarDriversJdbc() {
        
        List <Exception> l_excepciones = new ArrayList <> ();
        
        Enumeration <Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            Driver driver = drivers.nextElement();
            String nombre_driver = driver.getClass().getName();
            
            if (driver.getClass().getClassLoader() == getClass().getClassLoader()) {
                try {
                    DriverManager.deregisterDriver(driver);
                } catch (SQLException sqlex) {
                    String mensaje = Errores.getMensaje(Errores.ERR_BD_DESCARGAR_DRIVER_JDBC, nombre_driver);
                    Exception ex = new Exception(mensaje, sqlex);
                    l_excepciones.add(ex);
                }
                
            } else {
                String mensaje = Errores.getMensaje(Errores.ERR_BD_DRIVER_JDBC_NO_DESCARGADO, nombre_driver);
                Exception ex = new Exception(mensaje);
                l_excepciones.add(ex);
            }

        }
        
        return l_excepciones;
    }
    
    
    /**
     * Cierra el pool de conexiones.
     */
    public void cerrarPool() {
        if (pool != null) {
            pool.shutdown();
        }
    }
    
    
    /**
     * Obtiene una conexión del pool de conexiones.
     * Tras su uso, obligatoriamente la conexión debe ser cerrada.
     * Una vez cerrada, será devuelta automáticamente al pool.
     * 
     * @return                                  Conexión
     * 
     * @throws Exception                        El pool de conexiones no está creado
     *                                          Error al establecer la conexión
     */
    public Connection getConexion() throws Exception {
        
        if (pool == null) {
            Exception ex = new Exception(Errores.ERR_BD_OBTENER_POOL);
            LogManager.getRootLogger().debug("El pool de conexiones no está creado");
            throw ex;
        }
        
        Connection c = null;
        try 
        {
            c = pool.getConnection();
        } catch (SQLException sqlex) {
            String mensaje = Errores.getMensaje(Errores.ERR_BD_ESTABLECER_CONEXION);
            throw new Exception(mensaje, sqlex);
        }
        
        return c;
    }
    
    
}
