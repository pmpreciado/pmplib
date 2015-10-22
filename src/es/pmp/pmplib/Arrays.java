/**
 * Arrays.java
 *
 * Creado el 21-oct-2015, 18:01:59
 */

package es.pmp.pmplib;

import java.util.ArrayList;
import java.util.List;

/**
 * Funciones de propósito general relacionadas con arrays y colecciones.
 * 
 * @author pmpreciado
 */
public class Arrays {


    /**
     * Obtiene el tamaño de un int [], aceptando incluso que el vector sea 'null'.
     *
     * @param array                             Array del que se va a obtener el tamaño
     * @return                                  Tamaño del array (0, si el vector es 'null')
     */
    public static int length(int [] array) {
        if (array == null) {
            return 0;
        }
        return array.length;
    }


    /**
     * Obtiene el tamaño de un String [], aceptando incluso que el vector sea 'null'.
     *
     * @param array                             Array del que se va a obtener el tamaño
     * @return                                  Tamaño del array (0, si el vector es 'null')
     */
    public static int length(String [] array) {
        if (array == null) {
            return 0;
        }
        return array.length;
    }


    /**
     * Obtiene el tamaño de un Object [], aceptando incluso que el vector sea 'null'.
     *
     * @param array                             Array del que se va a obtener el tamaño
     * @return                                  Tamaño del array (0, si el vector es 'null')
     */
    public static int length(Object [] array) {
        if (array == null) {
            return 0;
        }
        return array.length;
    }


    /**
     * Obtiene el tamaño de un String [][], aceptando incluso que el vector sea 'null'.
     *
     * @param array                             Array del que se va a obtener el tamaño
     * @return                                  Tamaño del array (0, si el vector es 'null')
     */
    public static int length(String [][] array) {
        if (array == null) {
            return 0;
        }
        return array.length;
    }
    
    
    /**
     * Obtiene el tamaño de una lista, aceptando incluso que sea 'null'.
     *
     * @param l                             List del que se va a obtener el tamaño
     * @return                              Tamaño de la lista (0, si es 'null')
     */
    public static int size(List l) {
        if (l == null) return 0;
        return l.size();
    }
    
    
    /**
     * Comprueba si un array está vacío. Si es 'null' o no tiene elementos, se considera vacío.
     *
     * @param array                             Array comprobar
     * 
     * @return                                  'true' si está vacío
     *                                          'false' si no
     */
    public static boolean vacio(Object [] array) {
        if (array == null) {
            return true;
        }
        if (array.length == 0) {
            return true;
        }
        return false;
    }


    /**
     * Comprueba si un String [][] está vacío. Si el String [][] es 'null' o no tiene elementos, se considera vacío.
     *
     * @param array                             Array a comprobar
     * 
     * @return                                  'true' si está vacío
     *                                          'false' si no
     */
    public static boolean vacio(Object [][] array) {
        if (array == null) {
            return true;
        }
        if (array.length == 0) {
            return true;
        }
        return false;
    }
    
    
    /**
     * Comprueba si una lista dada está vacía. Si la lista es 'null' o no tiene elementos, se considera vacía.
     *
     * @param lista                             Lista a comprobar
     * @return                                  'true' si la lista está vacía
     *                                          'false' si no
     */
    public static boolean vacio(List lista) {
        if (lista == null) {
            return true;
        }
        if (lista.isEmpty()) {
            return true;
        }
        return false;
    }

    
    /**
     * Transforma un List con elementos de tipo Integer, en un int [].
     *
     * @param l_int                         List con los int
     * @return                              int [] con los elementos del List original
     *                                      'null' si la lista original era 'null'
     */
    public static int [] list2Int(List <Integer> l_int) {

        if (l_int == null) return null;

        int [] result = new int[l_int.size()];
        for (int i = 0; i < l_int.size(); i++) {
            result[i] = l_int.get(i);
        }

        return result;
    }


    /**
     * Transforma una lista con elementos de tipo String, en un String [].
     *
     * @param l_string                      ArrayList con los String
     * @return                              String [] con los elementos del List original
     *                                      'null' si la lista original era 'null'
     */
    public static String [] list2String(List <String> l_string) {

        if (l_string == null) return null;

        String [] result = new String[l_string.size()];
        for (int i = 0; i < l_string.size(); i++) {
            result[i] = l_string.get(i);
        }

        return result;
    }

    
    /**
     * Elimina los duplicados existentes en un String [].
     *
     * @param array                 String [] original
     * @return                      String [] sin duplicados
     */
    public static String [] eliminarDuplicados(String [] array) {

        if (array == null) return null;

        List <String> l_result = new ArrayList();
        for (int i = 0; i < array.length; i++) {
            String elemento = array[i];
            if (l_result.contains(elemento)) continue;
            l_result.add(elemento);
        }
        String [] result = list2String(l_result);
        return result;
    }


    /**
     * Elimina los duplicados existentes en un int [].
     *
     * @param array                 int [] original
     * @return                      int [] sin duplicados
     */
    public static int [] eliminarDuplicados(int [] array) {

        if (array == null) return null;

        List <Integer> l_result = new ArrayList();
        for (int i = 0; i < array.length; i++) {
            int elemento = array[i];
            if (l_result.contains(elemento)) continue;
            l_result.add(elemento);
        }
        int [] result = list2Int(l_result);
        return result;
    }
    
    

    /**
     * Elimina los duplicados existentes en un vector con elementos de tipo String.
     *
     * @param l_array               List <String> original
     * @return                      List <String> sin duplicados
     */
    public static List <String> eliminarDuplicadosListString(List <String> l_array) {

        if (l_array == null) return null;

        List <String> l_result = new ArrayList();
        for (int i = 0; i < l_array.size(); i++) {
            String elemento = l_array.get(i);
            if (!l_result.contains(elemento)) l_result.add(elemento);
        }

        return l_result;
    }



    /**
     * Elimina los duplicados existentes en un vector con elementos de tipo String.
     *
     * @param lista                 List <String> original
     * @return                      List <String> sin duplicados
     */
    public static List <Integer> eliminarDuplicadosListInt(List <Integer> lista) {

        if (lista == null) return null;

        List <Integer> l_result = new ArrayList();
        for (int i = 0; i < lista.size(); i++) {
            int elemento = lista.get(i);
            if (!l_result.contains(elemento)) l_result.add(elemento);
        }

        return l_result;
    }


    /**
     * Dada una cadena con una lista de elementos enteros, obtiene un array con dichos elementos.
     * Los elementos de la lista deben estar separados por comas. Los espacios en blanco dentro de la lista son ignorados.
     * Dentro de la lista se pueden incluir rangos.
     * Los elementos del resultado están ordenados y no contienen duplicados.
     * 
     * Ejemplos:
     *      1, 2, 3  => [1, 2, 3]
     *      1-3      => [1, 2, 3]
     *      3-1      => [1, 2, 3]
     *      1, 4-8, 10-12, 15 => [1, 4, 5, 6, 7, 8, 10, 11, 12, 15]
     * 
     * @param lista                         Cadena con la lista
     * 
     * @return                              int [] con los elementos de la lista
     */
    public static int [] splitExt(String lista) {

        if (lista == null) return null;
        
        List <Integer> l_result = new ArrayList();
        
        String [] elementos = lista.split(",");
        
        for (int i = 0; i < elementos.length; i++) {
            
            String elemento = elementos[i].trim();
            String [] elementos_rango = elemento.split("-");

            if (elementos_rango.length == 1) {
                int n = Cadenas.toEntero(elementos_rango[0].trim());
                l_result.add(n);
            } else if (elementos_rango.length == 2) {
                int r1 = Cadenas.toEntero(elementos_rango[0].trim());
                int r2 = Cadenas.toEntero(elementos_rango[1].trim());
                
                int min = Numeros.menor(r1, r2);
                int max = Numeros.mayor(r1, r2);
                for (int n = min; n <= max; n++) {
                    l_result.add(n);
                }
            }
        }
                
        int [] result = list2Int(l_result);
        result = eliminarDuplicados(result);
        java.util.Arrays.sort(result);
        
        return result;
    }
    
    


    /**
     * Busca si en un array existe un elemento determinado.
     * Si hubiera más de una coincidencia, retorna la primera encontrada.
     * 
     * @param array                             Array donde se realiza la búsqueda
     * @param elemento                          Elemento a buscar
     * 
     * @return                                  Posición en la que se encontró el elemento
     *                                          -1, si el elemento no figura en el array
     */
    public static int pos(int [] array, int elemento) {
        if (array == null) return -1;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == elemento) {
                return i;
            }
        }
        return -1;
    }
    
    
    /**
     * Busca si en un array existe un elemento determinado.
     * Si hubiera más de una coincidencia, retorna la primera encontrada.
     * 
     * @param array                             Array donde se realiza la búsqueda
     * @param elemento                          Elemento a buscar
     * 
     * @return                                  Posición en la que se encontró el elemento
     *                                          -1, si el elemento no figura en el array
     */
    public static int pos(char [] array, char elemento) {
        if (array == null) return -1;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == elemento) {
                return i;
            }
        }
        return -1;
    }


    /**
     * Busca si en un String [] existe un elemento determinado.
     * Si hubiera más de una coincidencia, retorna la primera encontrada.
     *
     * @param array                             Array donde se realiza la búsqueda
     * @param elemento                          Elemento a buscar
     * 
     * @return                                  Posición en la que se encontró el elemento
     *                                          -1, si el elemento no figura en el array
     */
    public static int pos(String [] array, String elemento) {
        if (array == null) return -1;
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(elemento)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Busca si en un String [] existe un elemento determinado.
     * Si hubiera más de una coincidencia, retorna la primera encontrada.
     * La búsqueda es insensible a mayúsculas y minúsculas.
     * 
     * @param array                             Array donde se realiza la búsqueda
     * @param elemento                          Elemento a buscar
     * 
     * @return                                  Posición en la que se encontró el elemento
     *                                          -1, si el elemento no figura en el array
     */
    public static int posIgnoreCase(String [] array, String elemento) {
        if (array == null) return -1;
        for (int i = 0; i < array.length; i++) {
            if (array[i].equalsIgnoreCase(elemento)) return i;
        }
        return -1;
    }
    

    /**
     * Transforma un array de elementos a un String con los elementos del array separados por el separador dado.
     * Los elementos del String son encerrados entre comillas o el caracter que se elija para encerrarlos.
     * Ejemplo: {1, 2, 3}  =>   "'1'|'2'|'3'" (usando el separador "|" y el encerrador "'")
     * Si el array de entrada es nulo o vacío, retorna la cadena vacía.
     *
     * @param array                         Array con los elementos
     * @param separador                     Carácter (o caracteres) separador
     * @param encerrador                    Carácter (o caracteres) para encerrar a cada elemento de la lista
     *
     * @return                              String con los elementos separados
     */
    public static String array2Lista(String [] array, String separador, String encerrador) {
        StringBuilder cadena = new StringBuilder("");
        if (array == null || array.length == 0) return "";

        boolean primero = true;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == null) continue;

            if (!primero) cadena.append(separador);
            cadena.append(encerrador + array[i] + encerrador);
            primero = false;
        }

        return cadena.toString();
    }


    /**
     * Transforma un array de elementos a un String con los elementos del array
     * separados por el separador dado.
     * Los elementos del String son encerrados entre comillas o el caracter que se elija para encerrarlos.
     * Ejemplo: {1, 2, 3}  =>   "'1'|'2'|'3'" (usando el separador "|" y el encerrador "'")
     * Si el array de entrada es nulo o vacío, retorna la cadena vacía.
     *
     * @param array                         Array con los elementos
     * @param separador                     Carácter (o caracteres) separador
     * @param encerrador                    Carácter (o caracteres) para encerrar a cada elemento de la lista
     *
     * @return                              String con los elementos separados
     */
    public static String array2Lista(int [] array, String separador, String encerrador) {
        StringBuilder cadena = new StringBuilder("");
        if (array == null || array.length == 0) return "";

        boolean primero = true;
        for (int i = 0; i < array.length; i++) {
            if (!primero) cadena.append(separador);
            cadena.append(encerrador + Integer.toString(array[i]) + encerrador);
            primero = false;
        }

        return cadena.toString();
    }


    /**
     * Transforma una lista de elementos a un String con dichos elementos separados por el separador dado.
     * Los elementos del String son encerrados entre comillas o el caracter que se elija para encerrarlos.
     * Ejemplo: {1, 2, 3}  =>   "'1'|'2'|'3'" (usando el separador "|" y el encerrador "'")
     * Si el array de entrada es nulo o vacío, retorna la cadena vacía.
     *
     * @param l_lista                       List con los elementos
     * @param separador                     Carácter (o caracteres) separador
     * @param encerrador                    Carácter (o caracteres) para encerrar a cada elemento de la lista
     *
     * @return                              String con los elementos separados
     */
    public static String list2Lista(List <String> l_lista, String separador, String encerrador) {

        String [] lista = list2String(l_lista);
        String result = array2Lista(lista, separador, encerrador);
        return result;
    }
    

    /**
     * Transforma un array de elementos a un String con los elementos del array
     * separados por el separador dado.
     * Ejemplo: {1, 2, 3}  =>   "1|2|3" (usando el separador "|")
     * Si el array de entrada es nulo o vacío, retorna la cadena vacía.
     *
     * @param array                         Array con los elementos
     * @param separador                     Carácter (o caracteres) separador
     * @return                              String con los elementos separados
     */
    public static String array2Lista(String [] array, String separador) {
        String lista = array2Lista(array, separador, "");
        return lista;
    }


    /**
     * Transforma un array de elementos a un String con los elementos del array
     * separados por el separador dado.
     * Ejemplo: {1, 2, 3}  =>   "1|2|3" (sin las comillas) (usando el separador "|")
     * Si el array de entrada es nulo o vacío, retorna la cadena vacía.
     *
     * @param array                         Array con los elementos
     * @param separador                     Carácter (o caracteres) separador
     * @return                              String con los elementos separados
     */
    public static String array2Lista(int [] array, String separador) {
        String lista = array2Lista(array, separador, "");
        return lista;
    }

    
    /**
     * Genera una cadena con el contenido del array suministrado.
     * 
     * @param array                         Array con los elementos
     * 
     * @return                              Cadena generada
     */
    public static String array2String(Object array[]) {
        StringBuilder s = new StringBuilder();
                
        for (int i = 0; i < length(array); i++) {
            Object o = array[i];
            s.append(i + ": ");
            if (o == null) s.append("null");
            else s.append(o.toString());
            s.append(Cadenas.NL);
        }
        
        return s.toString();
    }
    
    
    /**
     * Genera una cadena con el contenido del array suministrado.
     * 
     * @param array                         Array con los elementos
     * 
     * @return                              Cadena generada
     */
    public static String array2String(Object array[][]) {
        StringBuilder s = new StringBuilder();
                
        for (int i = 0; i < length(array); i++) {
            s.append(i + ": ");
            Object o2 = array[i];
            if (o2 == null) s.append("null");
            else {
                for (int j = 0; j < length(array[i]); j++) {
                    if (j > 0) s.append(" , ");
                    
                    Object o = array[i][j];                    
                    if (o == null) s.append("null");
                    else s.append(o.toString());
                }
            }
            s.append(Cadenas.NL);
        }
        
        return s.toString();
    }
    
    
    /**
     * Transforma una lista de elementos a un String con dichos elementos separados por el separador dado.
     * Los elementos del String son encerrados entre comillas o el caracter que se elija para encerrarlos.
     * Ejemplo: {1, 2, 3}  =>   "'1'|'2'|'3'" (usando el separador "|" y el encerrador "'")
     * Si el array de entrada es nulo o vacío, retorna la cadena vacía.
     *
     * @param l_lista                       List <String> con los elementos
     * @param separador                     Carácter (o caracteres) separador
     *
     * @return                              String con los elementos separados
     */
    public static String list2Lista(List <String> l_lista, String separador) {
        String result = list2Lista(l_lista, separador, "");
        return result;
    }

    
    /**
     * Concatena todos los elementos de un String [] en una única cadena.
     *
     * @param array                         String [] con los elementos a concatenar
     * @return                              Cadena resultado de concatenar los elementos
     */
    public static String concatenarArray(String [] array) {
        StringBuilder s = new StringBuilder();

        for (int i = 0; i < length(array); i++) {
            if (array[i] != null) s.append(array[i]);
        }

        return s.toString();
    }

    

    /**
     * Invierte los elementos de un array.
     *
     * @param array                         Array original
     * @return                              Array con los elementos en orden inverso
     *                                      'null' si el array de entrada era 'null'
     */
    public static int [] invertirArray(int [] array) {
        if (array == null) return null;
        int [] array_inverso = new int [array.length];

        int p = array_inverso.length - 1;
        for (int i = 0; i < array.length; i++, p--) {
            array_inverso[p] = array[i];
        }

        return array_inverso;
    }


    /**
     * Invierte los elementos de un array.
     *
     * @param array                         Array original
     * @return                              Array con los elementos en orden inverso
     *                                      'null' si el array de entrada era 'null'
     */
    public static String [] invertirArray(String [] array) {
        if (array == null) return null;
        String [] array_inverso = new String [array.length];

        int p = array_inverso.length - 1;
        for (int i = 0; i < array.length; i++, p--) {
            array_inverso[p] = array[i];
        }

        return array_inverso;
    }

    


    /**
     * Realiza un 'trim' en todos los elementos de un array.
     *
     * @param array                         String [] con los elementos a los que hacer el trim
     */
    public static void trimArray(String [] array)
    {
        if (array == null) return;
        for (int i = 0; i < array.length; i++) {
            array[i] = array[i].trim();
        }
    }

    
}
