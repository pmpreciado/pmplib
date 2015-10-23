/**
 * Errores.java
 *
 * Creado el 22-oct-2015, 11:51:53
 */


package es.pmp.pmplib.errores;

import es.pmp.pmplib.Cadenas;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Definición de mensajes de error de propósito general
 *
 * @author pmpreciado
 */
public class Errores {
    

    //--------------------------------------------------------------------------
    // ERRORES DE APLICACIÓN
    //--------------------------------------------------------------------------
    
    public static final String ERR_AP_INDETERMINADO                                     = "Error indeterminado";
    public static final String ERR_AP_PERSONALIZADO                                     = "%0%";
    public static final String ERR_AP_INTERNO_SERVIDOR                                  = "Error interno del servidor";
    public static final String ERR_AP_FICH_PROPIEDADES_NO_ESTABLECIDO                   = "No se ha establecido el nombre del fichero de propiedades";
    public static final String ERR_AP_FICH_PROPIEDADES_NO_ENCONTRADO                    = "No se encuentra el fichero de propiedades %0%";
    public static final String ERR_AP_FICH_PROPIEDADES_NO_CARGADO                       = "El fichero de propiedades no ha sido cargado";
    public static final String ERR_AP_LEER_FICH_PROPIEDADES                             = "Error al leer el fichero de propiedades %0%";
    public static final String ERR_AP_FICH_PROPIEDADES_FALTA_PROPIEDAD                  = "Falta propiedad %0% en el el fichero de propiedades %1%";
    public static final String ERR_AP_FICH_PROPIEDADES_FALTAN_PROPIEDADES               = "Faltan propiedades en el el fichero de propiedades %0%";
    public static final String ERR_AP_PAGINA_NO_EXISTENTE                               = "La página solicitada no existe";
    public static final String ERR_AP_LEER_FICHERO                                      = "No se puede leer el fichero %0%";
    public static final String ERR_AP_CREAR_FICHERO                                     = "No se puede crear el fichero %0%";
    public static final String ERR_AP_ESCRIBIR_FICHERO                                  = "No se puede escribir en el fichero %0%";
    public static final String ERR_AP_CERRAR_FICHERO                                    = "No se puede cerrar el fichero %0%";
    public static final String ERR_AP_APLICACION_NO_INICIALIZADA                        = "La aplicación no está inicializada";
    public static final String ERR_AP_OBJETO_CONTENEDOR_NO_INICIALIZADO                 = "El objeto contenedor no está inicializado";

    
    //--------------------------------------------------------------------------
    // ERRORES DE BASE DE DATOS
    //--------------------------------------------------------------------------

    public static final String ERR_BD_INDETERMINADO                                     = "Error indeterminado de base de datos";
    public static final String ERR_BD_DRIVER_NO_ENCONTRADO                              = "No se encuentra el driver JDBC para el acceso a la base de datos %0%";
    public static final String ERR_BD_NO_HAY_PARAMETROS                                 = "No se han suministrado los parámetros de conexión a la base de datos";
    public static final String ERR_BD_CONEXION_NO_ESTABLECIDA                           = "No está establecida la conexión con la base de datos";
    public static final String ERR_BD_CONEXION                                          = "Error en la conexión con la base de datos";
    public static final String ERR_BD_ESTABLECER_CONEXION                               = "Error al establecer la conexión con la base de datos %0%";
    public static final String ERR_BD_CERRAR_CONEXION                                   = "Error al cerrar la conexión con la base de datos";
    public static final String ERR_BD_CREAR_POOL                                        = "Error al crear el pool de conexiones con la base de datos";
    public static final String ERR_BD_OBTENER_POOL                                      = "Error al obtener el pool de conexiones con la base de datos";
    public static final String ERR_BD_CONSULTA_BD                                       = "Error en la consulta a la base de datos";
    public static final String ERR_BD_ACCESO_BD                                         = "Error en el acceso a la base de datos";
    public static final String ERR_BD_CAMPO_DEMASIADO_LARGO                             = "El texto introducido en el campo '%0%' es demasiado largo";
    public static final String ERR_BD_PREPARAR_CONSULTA_SQL                             = "Error al preparar la consulta SQL";
    public static final String ERR_BD_CONSULTA_SQL                                      = "Error en la consulta SQL";
    public static final String ERR_BD_OBTENER_RESULTADO_CONSULTA                        = "Error al obtener el resultado de la consulta SQL";
    public static final String ERR_BD_SET_AUTOCOMMIT                                    = "Error al establecer el modo de commit automático para las transacciones de base de datos";
    public static final String ERR_BD_INICIAR_TRANSACCION                               = "Error al iniciar una transacción";
    public static final String ERR_BD_COMMIT_TRANSACCION                                = "Error al hacer el commit de la transacción";
    public static final String ERR_BD_ROLLBACK_TRANSACCION                              = "Error al hacer el rollback de la transacción";
    public static final String ERR_BD_OBTENER_SENTENCIA_RESULTSET                       = "Error al obtener la sentencia asociada al resultado";
    public static final String ERR_BD_CERRAR_SENTENCIA                                  = "Error al cerrar la sentencia";
    public static final String ERR_BD_CERRAR_RESULTADO                                  = "Error al cerrar el resultado";
    public static final String ERR_BD_DESCARGAR_DRIVER_JDBC                             = "Error al descargar el driver JDBC %0%";
    public static final String ERR_BD_DRIVER_JDBC_NO_DESCARGADO                         = "El driver JDBC %0% no ha podido ser descargado";    
    public static final String ERR_BD_POOL_NO_INICIALIZADO                              = "El pool de conexiones %0% no está inicializado";
    public static final String ERR_BD_OBTENER_CONEXION_POOL                             = "Error al obtener una conexión del pool de conexiones %0%";
    public static final String ERR_BD_ALTA_REGISTRO                                     = "Error al dar de alta el registro";
    public static final String ERR_BD_ACTUALIZAR_REGISTRO                               = "Error al actualizar el registro";
    public static final String ERR_BD_BORRAR_REGISTRO                                   = "Error al borrar el registro";

    
    //--------------------------------------------------------------------------
    // ERRORES DE APLICACIONES WEB
    //--------------------------------------------------------------------------
    
    public static final String ERR_WEBAPP_PAGINA_NO_EXISTENTE                           = "La página solicitada no existe";
    

    

    /**
     * Obtiene el mensaje resultante, susituyendo los posibles tags por cadenas vacías.
     *
     * @param mensaje_original                  Mensaje original
     * 
     * @return                                  Descripción del mensaje de error
     */
    public static String getMensaje(String mensaje_original)
    {
        String tag = "";
        String mensaje_tags = Errores.getMensaje(mensaje_original, tag);
        return mensaje_tags;
    }
    
    
    /**
     * Obtiene el mensaje resultante de introducir los tags suministrados dentro del mensaje original.
     *
     * @param mensaje_original                  Mensaje original
     * @param tags                              Tags a incluir dentro del mensaje
     * 
     * @return                                  Descripción del mensaje de error
     */
    public static String getMensaje(String mensaje_original, String ... tags)
    {
        List <String> l_tags = new ArrayList();
        l_tags.addAll(Arrays.asList(tags));
        String mensaje_tags = Cadenas.sustituirTagsCadena(mensaje_original, l_tags);
        return mensaje_tags;
    }
    
    
    /**
     * Obtiene el mensaje resultante de introducir los tags suministrados dentro del mensaje original.
     *
     * @param mensaje_original                  Mensaje original
     * @param tags                              Tags a incluir dentro del mensaje
     * 
     * @return                                  Descripción del mensaje de error
     */
    public static String getMensaje(String mensaje_original, int ... tags)
    {
        String [] arr_s_tags = new String [tags.length];
        for (int i = 0; i < tags.length; i++) {
            String s_tag = Integer.toString(tags[i]);
            arr_s_tags[i] = s_tag;
        }
        String mensaje_tags = Errores.getMensaje(mensaje_original, arr_s_tags);
        return mensaje_tags;
    }

    
    /**
     * Obtiene el mensaje resultante de introducir los tags suministrados dentro del mensaje original.
     *
     * @param mensaje_original                  Mensaje original
     * @param l_tags                            Tag a incluir dentro del mensaje
     * 
     * @return                                  Descripción del mensaje de error
     */
    public static String getMensaje(String mensaje_original, List <String> l_tags)
    {
        String mensaje_tags = Cadenas.sustituirTagsCadena(mensaje_original, l_tags);
        return mensaje_tags;
    }
    
    
}
