/**
 * CinfoConexionBd.java
 *
 * Creado el 22-oct-2015, 10:50:58
 */

package es.pmp.pmplib.db;

import es.pmp.pmplib.Cadenas;
import es.pmp.pmplib.Comun;


/**
 * Clase para almacenar los parámetros de una conexión a base de datos.
 */
public class CinfoConexionBd {

    /** Tipos de conexión */
    public static final String TC_MYSQL     = "MYSQL";
    public static final String TC_INFORMIX  = "INFORMIX";
    public static final String TC_ORACLE    = "ORACLE";
    
    
    /** Nombre de la conexión (por ejemplo, "MySQL Principal") */
    private String nombre;
    
    /** Tipo de conexión (CinfoConexionBd.TC_xxx) */
    private String tipo;
    
    /** Driver JDBC de la base de datos */
    private String driver;
    
    /** URL para la base de datos */
    private String url;
    
    /** Usuario */
    private String user;
    
    /** Contraseña */
    private String password;

    
    
    
    /** 
     * Crea una instancia de la clase.
     */
    public CinfoConexionBd() {
    }
    
    /** 
     * Crea una instancia de la clase.
     * 
     * @param nombre                            Nombre de la conexión
     */
    public CinfoConexionBd(String nombre) {
        this.nombre = nombre;
    }
    
    
    /** 
     * Crea una instancia de la clase.
     * 
     * @param nombre                            Nombre de la conexión
     * @param tipo                              Tipo de conexión
     * @param driver                            Driver de la conexión
     * @param url                               URL de la base de datos
     * @param user                              Usuario
     * @param password                          Contraseña
     */
    public CinfoConexionBd(String nombre, String tipo, String driver, String url, String user, String password) {
        this.nombre     = nombre;
        this.tipo       = tipo;
        this.driver     = driver;
        this.url        = url;
        this.user       = user;
        this.password   = password;
    }
    
    
    /** 
     * Crea una instancia de la clase.
     * 
     * @param driver                            Driver de la conexión
     * @param url                               URL de la base de datos
     * @param user                              Usuario
     * @param password                          Contraseña
     */
    public CinfoConexionBd(String driver, String url, String user, String password) {
        this.driver     = driver;
        this.url        = url;
        this.user       = user;
        this.password   = password;
        
        this.setTipoFromDriver();
    }
    
    
    
    /**
     * Establece el tipo de conexión.
     * 
     * @param tipo                              Tipo de conexión
     */
    public void setTipo(String tipo) {
        if (tipo != null) tipo = tipo.trim();
        this.tipo = tipo;
    }
    
    
    /**
     * Obtiene el tipo de conexión.
     * 
     * @return                                  Tipo de conexión
     */
    public String getTipo() {
        return tipo;
    }
    
    
    /**
     * Detecta el tipo de conexión a partir del driver de conexión usado.
     * 
     * @param driver                            Driver de la conexión
     * 
     * @return                                  Tipo de conexión detectada (TC_xxx)
     *                                          'null' si no se puede detectar
     */
    public static String getTipoFromDriver(String driver) {
        if (driver == null) {
            return null;
        }
        
        if (driver.contains("mysql")) {
            return TC_MYSQL;
        }
        if (driver.contains("informix") || driver.contains("ifx")) {
            return TC_INFORMIX;
        }
        if (driver.contains("oracle")) {
            return TC_ORACLE;
        }
        
        return null;
    }
    
    
    /**
     * Establece el tipo de conexión a partir del driver de conexión usado.
     */
    public void setTipoFromDriver() {
        String tc = getTipoFromDriver(driver);
        setTipo(tc);
    }
    
    
    /**
     * Establece el tipo de conexión a partir del driver de conexión suministrado.
     * 
     * @param driver                            Driver de la conexión
     */
    public void setTipoFromDriver(String driver) {
        String tc = getTipoFromDriver(driver);
        setTipo(tc);
    }
    
    
    /**
     * Establece el driver de la conexión.
     * 
     * @param driver                            Driver de la conexión
     */
    public void setDriver(String driver) {
        if (driver != null) driver = driver.trim();
        this.driver = driver;
    }
    
    
    /**
     * Obtiene el driver de la conexión.
     * 
     * @return                                  Driver de la conexión
     */
    public String getDriver() {
        return driver;
    }
    
    
    /**
     * Establece la URL de la conexión.
     * 
     * @param url                               URL de la conexión
     */
    public void setURL(String url) {
        if (url != null) url = url.trim();
        this.url = url;
    }
    
    
    /**
     * Obtiene la URL de la conexión.
     * 
     * @return                                  URL de la conexión
     */
    public String getURL() {
        return url;
    }
    

    /**
     * Establece el usuario de la conexión.
     * 
     * @param user                              Usuario de la conexión
     */
    public void setUser(String user) {
        if (user != null) user = user.trim();
        this.user = user;
    }

    
    /**
     * Obtiene el usuario de la conexión.
     * 
     * @return                                  Usuario de la conexión
     */
    public String getUser() {
        return user;
    }
    
    
    /**
     * Establece la contraseña de la conexión.
     * 
     * @param password                          Contraseña de la conexión
     */
    public void setPassword(String password) {
        if (password != null) password = password.trim();
        this.password = password;
    }
    
    
    /**
     * Obtiene la contraseña de la conexión.
     * 
     * @return                                  Contraseña de la conexión
     */
    public String getPassword() {
        return password;
    }
    
    /**
     * Establece el driver, la base de datos, el usuario y la contraseña de la conexión.
     * 
     * @param driver                            Driver de la conexión
     * @param url                               URL de la base de datos
     * @param user                              Usuario
     * @param password                          Contraseña
     */
    public void setParametros(String driver, String url, String user, String password) {
        if (driver != null) driver = driver.trim();
        if (url != null) url = url.trim();
        if (user != null) user = user.trim();
        if (password != null) password = password.trim();
        
        setDriver(driver);
        setURL(url);
        setUser(user);
        setPassword(password);
    }
    
    
    /**
     * Establece la base de datos, el usuario y la contraseña de la conexión.
     * 
     * @param url                               URL de la base de datos
     * @param user                              Usuario
     * @param password                          Contraseña
     */
    public void setParametros(String url, String user, String password) {
        setURL(url);
        setUser(user);
        setPassword(password);
    }
    
    
    /**
     * Establece el nombre de la conexión.
     * 
     * @param nombre                            Nombre de la conexión
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
    /**
     * Obtiene el nombre de la conexión.
     * 
     * @return                                  Nombre de la conexión
     */
    public String getNombre() {
        return nombre;
    }
    
    
    
    /**
     * Retorna una cadena con los parámetros de la conexión.
     * 
     * @param inc_password                      Si es 'true', incluye la password en la cadena
     *                                          Si es 'false', no la incluye
     * 
     * @return                                  Cadena con los parámetros
     */
    public String parametros2String(boolean inc_password) {
        
        StringBuilder s = new StringBuilder();
        s.append("Driver: " + driver + Comun.NL);
        s.append("URL: " + url + Comun.NL);
        s.append("Usuario: " + user + Comun.NL);
        if (inc_password) {
            s.append("Password: " + password + Comun.NL);
        }
        s.append(Comun.NL);
        
        return s.toString();
    }
    
    
    /**
     * Comprueba si están definidas todas las propiedades de la conexión.
     * Se comprueba que esté definido: driver, db, user y password.
     * 
     * @return                                  'true' si están definidas todas las propiedades
     *                                          'false' si falta alguna
     */
    public boolean definida() {

        if (Cadenas.vacio(driver) || Cadenas.vacio(url) || Cadenas.vacio(user) || Cadenas.vacio(password)) {
            return false;
        }
        return true;
    }
    
    
    /**
     * Comprueba si la conexión establecida es de Informix.
     * 
     * @return                                  'true' si el tipo de conexión es de Informix
     *                                          'false' en caso contrario
     */
    public boolean esInformix() {
        if (Cadenas.vacio(tipo)) {
            return false;
        }
        if (tipo.equalsIgnoreCase(TC_INFORMIX)) {
            return true;
        }
        return false;
    }
    
    
    /**
     * Comprueba si la conexión establecida es de MySQL.
     * 
     * @return                                  'true' si el tipo de conexión es de MySQL
     *                                          'false' en caso contrario
     */
    public boolean esMySQL() {
        if (Cadenas.vacio(tipo)) {
            return false;
        }
        if (tipo.equalsIgnoreCase(TC_MYSQL)) {
            return true;
        }
        return false;
    }

    
    /**
     * Comprueba si la conexión pasada es de Informix.
     * 
     * @param tipo_conexion                     Tipo de conexión a comprobar
     * @return                                  'true' si el tipo de conexión es de Informix
     *                                          'false' en caso contrario
     */
    public static boolean esInformix(String tipo_conexion) {
        if (Cadenas.vacio(tipo_conexion)) {
            return false;
        }
        if (tipo_conexion.equalsIgnoreCase(TC_INFORMIX)) {
            return true;
        }
        return false;
    }
    
    
    /**
     * Comprueba si la conexión pasada es de MySQL.
     * 
     * @param tipo_conexion                     Tipo de conexión a comprobar
     * @return                                  'true' si el tipo de conexión es de MySQL
     *                                          'false' en caso contrario
     */
    public static boolean esMySQL(String tipo_conexion) {
        if (Cadenas.vacio(tipo_conexion)) {
            return false;
        }
        if (tipo_conexion.equalsIgnoreCase(TC_MYSQL)) {
            return true;
        }
        return false;
    }
    
    
    /**
     * Obtiene el primer parámetro de la conexión que no esté definido.
     * Si hay varios no definidos, sólo obtiene el primero.
     * Se comprueba el driver, la base de datos, el usuario y la contraseña, por ese orden
     * 
     * @return                                  Nombre del parámetro que no está definido (por ejemplo, "user")
     *                                          'null' si todos los parámetros están definidos
     */
    public String getParametroSinDefinir() {
        if (Cadenas.vacio(driver))    return "driver";
        if (Cadenas.vacio(url))       return "url";
        if (Cadenas.vacio(user))      return "user";
        if (Cadenas.vacio(password))  return "password";
        
        return null;
    }
    
    
    /**
     * Obtiene una cadena con la información de la conexión.
     * 
     * @return                                  Cadena de texto generada
     */
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("");
        
        s.append(getNombre() + Comun.NL);
        s.append("  - Tipo: " + getTipo() + Comun.NL);
        s.append("  - Driver: " + getDriver() + Comun.NL);
        s.append("  - URL: " + getURL() + Comun.NL);
        s.append("  - Usuario: " + getUser() + Comun.NL);
        s.append("  - Pw : " + getPassword() + Comun.NL);
        s.append(Comun.NL);

        return s.toString();
    }

}
