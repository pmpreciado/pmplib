/**
 * Ficheros.java
 *
 * Creado el 21-oct-2015, 17:58:07
 */

package es.pmp.pmplib;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;


/**
 * Funciones de propósito general relacionadas con ficheros.
 * @author pmpreciado
 */
public class Ficheros {


    /**
     * Concatena un directorio y un nombre de fichero, obteniendo una sola cadena.
     * Si el directorio no acaba en el caracter "\", esta función lo añade.
     *
     * @param directorio                    Directorio
     * @param nombre                        Nombre de fichero
     * @return                              Concatenación directorio + nombre
     */
    public static String concatenarDirectorioYNombre(String directorio, String nombre)
    {
        if (directorio == null) {
            directorio = "";
        }
        if (nombre == null) {
            nombre = "";
        }

        if (directorio.length() == 0) {
            return nombre;
        }
        String ultimo = directorio.substring(directorio.length() - 1);

        if (ultimo.equals("\\") || ultimo.equals("/")) {
            return directorio + nombre;
        }

        return directorio + "\\" + nombre;
    }


    /**
     * Concatena un nombre de fichero con una extensión, añadiendo un punto entre ambos.
     * Si la extensión está en blanco, solo se retorna el nombre del fichero, sin el punto.
     *
     * @param nombre                        Nombre de fichero
     * @param extension                     Extension
     * @return                              Concatenación Nombre de fichero + "." + extensión
     */
    public static String concatenarNombreFicheroYExtension(String nombre, String extension)
    {
        if (Cadenas.vacio(extension)) return nombre;
        String result = nombre + "." + extension;
        return result;
    }


    /**
     * Dada una cadena con un directorio y un nombre de fichero, retorna la parte correspondiente
     * al nombre del fichero.
     *
     * @param ruta                          Directorio + Nombre del fichero
     * @return                              Nombre del fichero
     * @see getDirectorioFromRuta
     */
    public static String getNombreFromRuta(String ruta)
    {
        int p = ruta.lastIndexOf("\\");
        if (p == -1) p = ruta.lastIndexOf("/");
        if (p == -1) return ruta;

        String nombre = ruta.substring(p + 1);
        return nombre;
    }


    /**
     * Dada una cadena con un directorio y un nombre de fichero, retorna la parte correspondiente
     * al directorio.
     *
     * @param ruta                          Directorio + Nombre del fichero
     * @return                              Directorio (sin el "\" al final)
     * @see getNombreFromRuta
     */
    public static String getDirectorioFromRuta(String ruta)
    {
        int p = ruta.lastIndexOf("\\");
        if (p == -1) {
            p = ruta.lastIndexOf("/");
        }
        if (p == -1) {
            return "";
        }

        String nombre = Cadenas.miSubstring(ruta, 0, p - 1);
        return nombre;
    }


    /**
     * Dado un nombre de un fichero (o directorio + nombre), obtiene la extensión.
     * al nombre del fichero.
     *
     * @param nombre_fichero                Nombre del fichero
     * @return                              Extensión (sin el punto)
     * @see quitarExtension
     */
    public static String getExtension(String nombre_fichero)
    {
        // Quitamos el directorio, si lo hubiera
        int d = nombre_fichero.lastIndexOf("\\");
        if (d >= 0) {
            nombre_fichero = Cadenas.ultimos(nombre_fichero, nombre_fichero.length() - d - 1);
        }
        
        int p = nombre_fichero.lastIndexOf(".");
        if (p == -1) {
            return "";
        }

        String extension = nombre_fichero.substring(p + 1);
        return extension;
    }

    /**
     * Dado un nombre de fichero (o directorio + nombre), le quita la extensión.
     *
     * @param nombre_fichero                Nombre del fichero
     * @return                              Nombre sin .extensión
     * @see getExtension
     */
    public static String quitarExtension(String nombre_fichero)
    {
        String extension = getExtension(nombre_fichero);

        if (extension.length() > 0) {
            nombre_fichero = Cadenas.primeros(nombre_fichero, nombre_fichero.length() - extension.length() - 1);
        }

        return nombre_fichero;
    }


    /**
     * Dado un nombre de directorio, si no termina con el caracter "\", lo añade.
     * Si el directorio es "", no hace nada.
     *
     * @param dir                           Nombre de directorio
     * @return                              Directorio con el "\" al final
     */
    public static String ponerBarraDirectorio(String dir)
    {
        if (dir.length() == 0) {
            return dir;
        }

        if (dir.charAt(dir.length() - 1) != '\\') {
            dir = dir + "\\";
        }
        return dir;
    }


    /**
     * Añade un sufijo a un nombre de fichero, preservando la extensión.
     * Por ejemplo:
     *    \datos\fichero.txt --> \datos\fichero_proc.txt
     *
     * @param nombre_fichero                Nombre del fichero original. Puede ser directorio y nombre
     * @param sufijo                        Sufijo a añadir
     * @return                              Nombre con el sufijo
     */
    public static String añadirSufijoAFichero(String nombre_fichero, String sufijo) {

        String ext              = getExtension(nombre_fichero);
        String nombre_sin_ext   = quitarExtension(nombre_fichero);

        String nuevo_nombre = nombre_sin_ext + sufijo;
        if (ext.length() > 0) {
            nuevo_nombre = nuevo_nombre + "." + ext;
        }
        return nuevo_nombre;
    }


    

    /**
     * Obtiene un nombre de fichero inexistente a partir de un nombre de fichero dado.
     * Para ello, comprueba si ya existe un fichero con el nombre dado y, en caso
     * afirmativo, le añade un contador al nombre dado.
     * El contador se irá incrementando hasta dar con un nombre único.
     * Al nombre del fichero original se le añade un guión bajo y el contador (dos cifras)
     *
     * Ejemplos:
     *    ENTRADA               SALIDA
     *    fichero.txt   -->     fichero.txt (si no existe)
     *                  -->   o fichero_01.txt (si existe fichero.txt)
     *                  -->   o fichero_02.txt (si existen fichero.txt y fichero_01.txt)
     *                          ...
     *
     * @param nombre_inicial                Nombre del fichero inicial, incluyendo extensión, o ruta completa.
     * @return                              Nombre de fichero que no existe
     */
    public static String getNombreFicheroInexistente(String nombre_inicial) {
        File f = new File(nombre_inicial);

        String extension = getExtension(nombre_inicial);

        // Al nombre inicial le quitamos el ".extension"
        nombre_inicial = quitarExtension(nombre_inicial);

        String nombre = nombre_inicial;
        String ultimos_2 = Cadenas.ultimos(nombre_inicial, 2);
        int contador;
        boolean nombre_inicial_con_contador;
        if (nombre_inicial.length() > 3 &&  nombre_inicial.charAt(nombre_inicial.length() - 3) == '_' && Numeros.esEntero(ultimos_2))
        {
            contador = Integer.parseInt(ultimos_2);
            nombre_inicial_con_contador = true;
        } else {
            contador = 0;
            nombre_inicial_con_contador = false;
        }


        while (f.exists()) {
            contador++;
            String s_contador = Integer.toString(contador);
            while (s_contador.length() < 2) s_contador = "0" + s_contador;

            if (nombre_inicial_con_contador)
            {
                nombre = Cadenas.primeros(nombre_inicial, nombre_inicial.length() - 3) + "_" + s_contador;
            } else {
                nombre = nombre_inicial + "_" + s_contador;
            }
            f = new File(nombre + "." + extension);
        }

        if (extension.length() == 0) return nombre;

        return nombre + "." + extension;
    }


    /**
     * Ejecuta una aplicación o abre un fichero.
     *
     * @param fichero                           Aplicación a ejecutar o fichero a abrir
     * 
     * @throws IOException                      Error al abrir el fichero
     */
    public static void abrirFichero(String fichero) throws IOException {
        String sistema_operativo = System.getProperty("os.name" ).toLowerCase();
        boolean linux   = sistema_operativo.startsWith("mac os x");
        boolean macosx  = sistema_operativo.startsWith("linux");
        boolean win95   = sistema_operativo.startsWith("windows 95");
        boolean win_any = sistema_operativo.startsWith("windows");

        if (macosx) {
            Runtime.getRuntime().exec(new String[] {"open", fichero});
        } else if (linux) {
            Runtime.getRuntime().exec(new String[] {"./xdg-open", fichero});
        } else if (win95) {
            Runtime.getRuntime().exec(new String[] {"command.com", "/C", "start", fichero});
        } else if (win_any) {
            Runtime.getRuntime().exec(new String[] {"cmd.exe", "/C", "start", fichero});
        } else {
            Runtime.getRuntime().exec(new String[] {"open", fichero});
        }
    }

    
    /**
     * Abre una carpeta en una ventana del explorador mediante la shell de comandos.
     *
     * @param dir                               Directorio de la carpeta a abrir
     * 
     * @return                                  String [] con el comando y los argumentos empleados
     * 
     * @throws IOException                      Error al abrir la carpeta
     */
    public static String [] abrirCarpeta(String dir) throws IOException {
        String sistema_operativo = System.getProperty("os.name" ).toLowerCase(); 
        boolean macosx  = sistema_operativo.startsWith("mac os x");
        boolean linux   = sistema_operativo.startsWith("linux");
        boolean win95   = sistema_operativo.startsWith("windows 95");
        boolean win_any = sistema_operativo.startsWith("windows");
        
        String [] comando = null;
        
        if (macosx) { 
            comando = new String [] {"open", dir};
        } else if (linux) { 
            comando = new String [] {"./xdg-open", dir};
        } else if (win95) { 
            comando = new String [] {"explorer.exe", dir};
        } else if (win_any) { 
            comando = new String [] {"explorer.exe", dir};
        } else { 
            comando = new String [] {"open", dir};
        }
        
        if (comando != null) {
            Runtime.getRuntime().exec(comando); 
        }
        
        return comando;
    }
    
    

    /**
     * Obtiene un array de bytes con el contenido de un InputStream.
     *
     * @param is                            InputStream
     *
     * @return                              byte [] con el contenido del InputStream
     *
     * @throws IOException                  Error de entrada / salida
     */
    public static byte [] inputStream2ByteArray(InputStream is) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        final int TAMÑO_BUFFER = 1024;          // Buffer de 1KiB

        byte[] buffer = new byte[TAMÑO_BUFFER];
        int bytes_leidos = -1;
        while((bytes_leidos = is.read(buffer)) > -1) {
            out.write(buffer, 0, bytes_leidos);
        }
        is.close();
        byte [] result = out.toByteArray();
        return result;
    }
    
    
    /**
     * Lee y retorna el contenido de un fichero dado.
     * 
     * @param nombre_fichero                Fichero a leer
     * 
     * @return                              Contenido del fichero
     * 
     * @throws FileNotFoundException        Fichero no encontrado
     * @throws IOException                  Error al leer el fichero
     */
    public static StringBuilder leerFichero(String nombre_fichero) throws FileNotFoundException, IOException {
        
        StringBuilder s = new StringBuilder();
        
        FileInputStream fstream = new FileInputStream(nombre_fichero);
        DataInputStream in = new DataInputStream(fstream);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));

        String linea;
        while ((linea = br.readLine()) != null)   {
            s.append(linea);
        }

        in.close();
        
        return s;
    }
    
    
    /**
     * Genera un fichero con el contenido suministrado.
     * 
     * @param nombre_fichero                Fichero a generar
     * @param is                            InputStream con el contenido a escribir
     * 
     * @return                              Fichero generado
     * 
     * @throws FileNotFoundException        Fichero no encontrado
     * @throws IOException                  Error al escribir en el fichero
     */
    public static File escribirFichero(String nombre_fichero, InputStream is) throws FileNotFoundException, IOException {
        
        OutputStream os = null;

        try {
            File f = new File(nombre_fichero);
            os = new FileOutputStream(f);

            int read = 0;
            byte [] bytes = new byte[1024];

            while ((read = is.read(bytes)) != -1) {
                os.write(bytes, 0, read);
            }

            return f;

        } finally {
            if (os != null) {
                os.close();
            }
        }
    }
}
