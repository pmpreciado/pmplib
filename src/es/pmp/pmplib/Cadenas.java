/**
 * Cadenas.java
 *
 * Creado el 21-oct-2015, 17:44:59
 */

package es.pmp.pmplib;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * Funciones de propósito general relacionadas con cadenas.
 * 
 * @author pmpreciado
 */
public class Cadenas {

    /** Tabulaciones */
    public static final String TAB_1 = "    ";
    public static final String TAB_2 = TAB_1 + TAB_1;
    public static final String TAB_3 = TAB_2 + TAB_1;
    public static final String TAB_4 = TAB_3 + TAB_1;
    public static final String TAB_5 = TAB_4 + TAB_1;
    public static final String TAB_6 = TAB_5 + TAB_1;


    /** Tipos de mayúsculas / minúsculas */
    public static final int TMM_NINGUNA                 = 0;
    public static final int TMM_MAYUSCULAS              = 1;
    public static final int TMM_MINUSCULAS              = 2;
    public static final int TMM_PRIMERA_MAYUSCULAS      = 3;

    
    /** Alineaciones */
    public static final int AL_IZQUIERDA                = 0;
    public static final int AL_CENTRO                   = 1;
    public static final int AL_DERECHA                  = 2;
    public static final int AL_JUSTIFICADO              = 3;

    
    
    /**
     * Comprueba si una cadena dada está vacía. Si la cadena es 'null' o no tiene caracteres, se considera vacía.
     *
     * @param cadena                    Cadena a comprobar
     * @return                          'true' si la cadena está vacía
     *                                  'false' si no
     */
    public static boolean vacio(String cadena) {
        if (cadena == null) return true;
        if (cadena.length() == 0) return true;
        return false;
    }


    /**
     * Reimplementación de la función "substring", pero más fácil de utilizar que la nativa
     * de Java, que es un coñazo.
     * Si la subcadena a obtener sobrepasa la longitud de la inicial, se ajusta el resultado
     * a la cadena dentro de los límites, sin lanzar excepción.
     * Ejemplos:
     *   <ul>
     *     <li> miSubstring("amigo", 0, 0) => "a"
     *     <li> miSubstring("amigo", 0, 1) => "am"
     *     <li> miSubstring("amigo", 1, 4) => "migo"
     *   </ul>

     * @param cadena                    Cadena sobre la que se quiere extraer un subconjunto
     * @param desde                     Primer caracter a extraer (empezando en la posición 0)
     * @param hasta                     Último caracter (empezando en la posición 0)
     * @return                          Subcadena
     */
    public static String miSubstring(String cadena, int desde, int hasta) {
        if (cadena.length() == 0) return "";

        if (desde < 0) desde = 0;
        if (desde >= cadena.length()) return "";
        if (hasta >= cadena.length()) hasta = cadena.length() - 1;

        String subcadena = cadena.substring(desde, hasta + 1);
        return subcadena;
    }


    /**
     * Reimplementación de la función "substring", pero más fácil de utilizar que la nativa
     * de Java, que es un coñazo.
     * Si la subcadena a obtener sobrepasa la longitud de la inicial, se ajusta el resultado
     * a la cadena dentro de los límites, sin lanzar excepción.
     * Ejemplos:
     *   <ul>
     *     <li> miSubstringLong("amigo", 0, 0) => ""
     *     <li> miSubstringLong("amigo", 0, 1) => "a"
     *     <li> miSubstringLong("amigo", 3, 2) => "go"
     *   </ul>
     *
     * @param cadena                    Cadena sobre la que se quiere extraer un subconjunto
     * @param desde                     Primer caracter a extraer (empezando en la posición 0)
     * @param longitud                  Número de caracteres a extraer
     * @return                          Subcadena
     */
    public static String miSubstringLong(String cadena, int desde, int longitud) {
        if (cadena.length() == 0) return "";

        if (desde < 0) desde = 0;
        if (desde >= cadena.length()) return "";
        if (desde + longitud > cadena.length()) longitud = cadena.length() - desde;

        String subcadena = cadena.substring(desde, desde + longitud);

        return subcadena;
    }


    /**
     * Divide una cadena en partes, al igual que hace la función de Java String.split(String), pero añadiendo la funcionalidad de
     * que no divide si el carácter separador se encuentra encerrado.
     * Por ejemplo, supongamos que hay que partir allá donde se encuentre un espacio en blanco, pero cuidando de no partir
     * el texto encerrado entre comillas ("):
     *
     *      Texto original              Resultado String.split (Java)           Resultado this.split
     *
     *      Hola amigo                  [Hola] [amigo]                          [Hola] [amigo]
     *      Me dijo "hola amigo"        [Me] [dijo] ["hola] [amigo"]            [Me] [dijo] ["hola amigo"]
     *      Me " dijo "hola amigo"      [Me] ["] [dijo] ["hola] [amigo"]        [Me] [" dijo "] [hola] [amigo"]
     *
     * @param cadena                        Cadena a dividir
     * @param separador                     Carácter o cadena por la que se efectuarán las divisiones
     * @param encerrador                    Carácter o cadena usado para encerrar secciones que no se van dividir
     * 
     * @return                          Partes de la cadena
     */
    public static String [] split(String cadena, String separador, String encerrador) {

        List <String> l_partes = new ArrayList();
        String parte_actual = "";
        boolean encerrando = false;

        for (int i = 0; i < cadena.length(); i++) {
            String c = cadena.substring(i, i + 1);
            boolean es_separador = c.equals(separador);
            boolean es_encerrador = c.equals(encerrador);

            if (encerrando) {
                parte_actual += c;
                if (es_encerrador) {
                    encerrando = false;
                }

            } else {
                if (es_encerrador) {
                    parte_actual += c;
                    encerrando = true;
                } else {
                    if (es_separador) {
                        if (parte_actual.length() > 0) l_partes.add(parte_actual);
                        parte_actual = "";
                    } else {
                        parte_actual += c;
                    }
                }
            }
        }

        if (parte_actual.length() > 0) l_partes.add(parte_actual);

        String [] partes = Arrays.list2String(l_partes);
        return partes;
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
                int n = toEntero(elementos_rango[0].trim());
                l_result.add(n);
            } else if (elementos_rango.length == 2) {
                int r1 = toEntero(elementos_rango[0].trim());
                int r2 = toEntero(elementos_rango[1].trim());
                
                int min = Numeros.menor(r1, r2);
                int max = Numeros.mayor(r1, r2);
                for (int n = min; n <= max; n++) {
                    l_result.add(n);
                }
            }
        }
                
        int [] result = Arrays.list2Int(l_result);
        result = Arrays.eliminarDuplicados(result);
        java.util.Arrays.sort(result);
        
        return result;
    }

    
    /**
     * Divide una cadena en fragmentos del tamaño dado.
     * El último de los fragmentos sera el resto de la cadena, y puede ser de tamaño menor.
     * 
     * @param cadena                        Cadena a dividir
     * @param longitud                      Longitud de los fragmentos
     * 
     * @return                              Fragmentos
     */
    public static List <String> split(String cadena, int longitud) {
        List <String> l_result = new ArrayList();
        if (cadena == null) {
            return l_result;
        }
        
        if (cadena.length() == 0) {
            l_result.add(cadena);
            return l_result;
        }
        
        while (cadena.length() > 0) {
            String s = primeros(cadena, longitud);
            l_result.add(s);
            cadena = eliminarPrimeros(cadena, longitud);
        }
        
        return l_result;
    }
    

    /**
     * Divide una cadena en varias, troceándola por donde encuentre el caracter dado.
     * A diferencia de String.split(), esta función no usa expresiones regulares.
     * 
     * @param cadena                        Cadena a dividir
     * @param delimitador                   Caracter o palabra delimitador
     * 
     * @return                              Fragmentos
     */
    public static List <String> split(String cadena, String delimitador)
    {
        List <String> l = new ArrayList();

        while (true)
        {
            int index = cadena.indexOf(delimitador);
            if (index == -1)
            {
                l.add(cadena);
                return l;
            } else
            {
                l.add(cadena.substring(0, index));
                cadena = cadena.substring(index + delimitador.length());
            }
        }
    }
    
    
    /**
     * Une varias cadenas en una.
     * Las cadenas unidas van separadas por el separador dado.
     * 
     * @param arr_cadenas                       Cadenas a unir
     * @param separador                         Separador
     * 
     * @return                                  Cadena con el resultado
     */
    public static String join(String [] arr_cadenas, String separador) {
        
        if (arr_cadenas == null) {
            return null;
        }
        
        StringBuilder s = new StringBuilder();
        
        for (int i = 0; i < arr_cadenas.length; i++) {
            if (i > 0) {
                s.append(separador);
            }

            String cadena = arr_cadenas[i];
            s.append(cadena);
        }
        
        return s.toString();
    }
    
    
    /**
     * Une varias cadenas en una.
     * Las cadenas unidas van separadas por el separador dado.
     * 
     * @param l_cadenas                         Cadenas a unir
     * @param separador                         Separador
     * 
     * @return                                  Cadena con el resultado
     */
    public static String join(Collection <String> l_cadenas, String separador) {
        
        if (l_cadenas == null) {
            return null;
        }
        
        StringBuilder s = new StringBuilder();
        
        for (String cadena : l_cadenas) {
        
            if (s.length() > 0) {
                s.append(separador);
            }
            
            s.append(cadena);
        }
        
        return s.toString();
    }
    
    
    /**
     * Reemplaza una subcadena en una cadena, dada por su posición y su longitud.
     * Ejemplos:
     *   <ul>
     *     <li> reemplazarPosicion("amiguitos", 2, 4, "x") => "amxtos"
     *     <li> reemplazarPosicion("amiguitos", 2, 4, "123") => "am123tos"
     *   </ul>
     *
     * @param cadena                        Cadena original
     * @param indice                        Índice que ocupa el carácter a sustituir (empezando en la posición 0)
     * @param longitud                      Número de carácteres a sustituir
     * @param texto_nuevo                   Carácter (o cadena) que sustituirá al carácter
     * @return                              Nueva cadena
     */
    public static String reemplazarPosicion(String cadena, int indice, int longitud, String texto_nuevo) {
        if (indice < 0 || indice >= cadena.length()) return cadena;

        String primeros = primeros(cadena, indice);
        String ultimos = ultimos(cadena, cadena.length() - indice - longitud);
        String nueva_cadena = primeros + texto_nuevo + ultimos;
        return nueva_cadena;
    }


    /**
     * Reemplaza un carácter en una cadena, dado por su posición.
     * Ejemplos:
     *   <ul>
     *     <li> reemplazarPosicion("amigo", 2, "x") => "amxgo"
     *     <li> reemplazarPosicion("amigo", 2, "123") => "am123go"
     *   </ul>
     *
     * @param cadena                        Cadena original
     * @param indice                        Índice que ocupa el carácter a sustituir (empezando en la posición 0)
     * @param texto_nuevo                   Carácter (o cadena) que sustituirá al carácter
     * @return                              Nueva cadena
     */
    public static String reemplazarPosicion(String cadena, int indice, String texto_nuevo) {

        return reemplazarPosicion(cadena, indice, 1, texto_nuevo);
    }


    /**
     * Comprueba si la cadena 1 es mayor que la cadena 2 en orden alfabético.
     * Para la comparación no tiene en cuenta los acentos.
     *
     * @param cadena_1                  Primera cadena
     * @param cadena_2                  Segunda cadena
     * @return                          'true' si cadena_1 > cadena_2
     *                                  'false' si cadena_1 <= cadena_2
     */
    public static boolean esMayor(String cadena_1, String cadena_2) {
        if (cadena_1 == null || cadena_2 == null) return false;

        cadena_1 = quitarAcentos(cadena_1);
        cadena_2 = quitarAcentos(cadena_2);

        if (cadena_1.compareToIgnoreCase(cadena_2) < 0) return false;
        if (cadena_1.compareToIgnoreCase(cadena_2) == 0) return false;
        return true;
    }


    /**
     * Comprueba si la cadena 1 es menor que la cadena 2 en orden alfabético.
     * Para la comparación no tiene en cuenta los acentos.
     * 
     * @param cadena_1                  Primera cadena
     * @param cadena_2                  Segunda cadena
     * @return                          'true' si cadena_1 < cadena_2
     *                                  'false' si cadena_1 >= cadena_2
     */
    public static boolean esMenor(String cadena_1, String cadena_2) {
        if (cadena_1 == null || cadena_2 == null) return false;

        cadena_1 = quitarAcentos(cadena_1);
        cadena_2 = quitarAcentos(cadena_2);

        if (cadena_1.compareToIgnoreCase(cadena_2) > 0) return false;
        if (cadena_1.compareToIgnoreCase(cadena_2) == 0) return false;
        return true;
    }


    /**
     * Comprueba si la cadena 1 es igual a la cadena 2 en orden alfabético.
     * Para la comparación no tiene en cuenta los acentos.
     * 
     * @param cadena_1                  Primera cadena
     * @param cadena_2                  Segunda cadena
     * @return                          'true' si cadena_1 == cadena_2
     *                                  'false' si cadena_1 != cadena_2
     */
    public static boolean esIgual(String cadena_1, String cadena_2) {
        if (cadena_1 == null || cadena_2 == null) return false;

        if (cadena_1.compareToIgnoreCase(cadena_2) == 0) return true;
        return false;
    }


    /**
     * Obtiene los 'n' primeros caracteres de una cadena.
     *
     * @param cadena                        Cadena sobre la que se quiere extraer los caracteres
     * @param n                             'n' primeros caracteres a extraer
     * @return                              Subcadena extraída
     */
    public static String primeros(String cadena, int n) {
        if (cadena == null) return null;
        if (cadena.length() == 0) return "";
        if (n <= 0) return "";
        if (n > cadena.length()) n = cadena.length();

        String subcadena = cadena.substring(0, n);
        return subcadena;
    }


    /**
     * Obtiene los 'n' últimos caracteres de una cadena.
     *
     * @param cadena                        Cadena sobre la que se quiere extraer los caracteres
     * @param n                             'n' últimos caracteres a extraer
     * @return                              Subcadena extraída
     */
    public static String ultimos(String cadena, int n) {
        if (cadena == null) return null;
        if (cadena.length() == 0) return "";
        if (n <= 0) return "";
        if (n > cadena.length()) n = cadena.length();

        String subcadena = cadena.substring(cadena.length() - n);
        return subcadena;
    }


    /**
     * Elimina los 'n' primeros caracteres de una cadena.
     *
     * @param cadena                        Cadena sobre la que se quiere eliminar los caracteres
     * @param n                             'n' primeros caracteres a eliminar
     * @return                              Cadena final
     */
    public static String eliminarPrimeros(String cadena, int n) {
        String subcadena = ultimos(cadena, cadena.length() - n);
        return subcadena;
    }


    /**
     * Elimina los 'n' últimos caracteres de una cadena.
     *
     * @param cadena                        Cadena sobre la que se quiere eliminar los caracteres
     * @param n                             'n' últimos caracteres a eliminar
     * @return                              Cadena final
     */
    public static String eliminarUltimos(String cadena, int n) {
        String subcadena = primeros(cadena, cadena.length() - n);
        return subcadena;
    }


    /**
     * Transforma un char, representando un dígito, al entero correspondiente.
     * Ejemplo: '9' -> 9
     *
     * @param c                             Caracter con el dígito
     * @return                              Número entero correspondiente
     */
    public static int char2Int(char c) {
        int n = (int) c - 48;
        return n;
    }


    /**
     * Transforma un char a una cadena con ese caracter.
     * Ejemplo: 's' -> "s"
     *
     * @param c                             Caracter con el char
     * @return                              Cadena con el char
     */
    public static String char2String(char c) {
        char [] cc = {c};
        String s = new String(cc);
        return s;
    }


    /**
     * Dado un caracter, obtiene el carácter equivalente en mayúsculas.
     *
     * @param c                             Caracter original
     * @return                              Caracter en mayúsculas
     */
    public static char mayusculas(char c) {
        if (c >= 'a' && c <= 'z') return (char) (c - 32);
        if (c == 'ñ') return 'Ñ';
        if (c == 'á') return 'Á';
        if (c == 'é') return 'É';
        if (c == 'í') return 'Í';
        if (c == 'ó') return 'Ó';
        if (c == 'ú') return 'Ú';
        if (c == 'ü') return 'Ü';
        return c;
    }


    /**
     * Dado un caracter, obtiene el carácter equivalente en minúsculas.
     *
     * @param c                             Caracter original
     * @return                              Caracter en minúsculas
     */
    public static char minusculas(char c) {
        if (c >= 'A' && c <= 'Z') return (char) (c + 32);
        if (c == 'Ñ') return 'ñ';
        if (c == 'Á') return 'á';
        if (c == 'É') return 'é';
        if (c == 'Í') return 'í';
        if (c == 'Ó') return 'ó';
        if (c == 'Ú') return 'ú';
        if (c == 'Ü') return 'ú';
        return c;
    }


    /**
     * Dado un caracter, obtiene el carácter equivalente sin acentos ni diéresis.
     * La Ñ la respeta.
     *
     * @param c                             Caracter original
     * @return                              Caracter sin acentos
     */
    public static char sinAcentos(char c) {

        switch (c) {
            case 'Á': return 'A';
            case 'É': return 'E';
            case 'Í': return 'I';
            case 'Ó': return 'O';
            case 'Ú': return 'U';
            case 'Ü': return 'U';

            case 'á': return 'a';
            case 'é': return 'e';
            case 'í': return 'i';
            case 'ó': return 'o';
            case 'ú': return 'u';
            case 'ü': return 'u';
        }

        return c;
    }


    /**
     * Dada una cadena, obtiene si se encuentra en mayúsuclas o minúsculas.
     *
     * @param cadena                        Cadena a comprobar
     * @return                              Estado de las mayúsculas o minúsculas
     *                                          - TMM_NINGUNA
     *                                          - TMM_MAYUSCULAS
     *                                          - TMM_MINUSCULAS
     *                                          - TMM_PRIMERA_MAYUSCULAS
     */
    public static int getMayusculasMinusculas(String cadena) {
        if (vacio(cadena)) {
            return TMM_NINGUNA;
        }

        String cadena_mayusculas = cadena.toUpperCase();
        String cadena_minusculas = cadena.toLowerCase();

        if (cadena.equals(cadena_mayusculas)) {
            return TMM_MAYUSCULAS;
        }
        if (cadena.equals(cadena_minusculas)) {
            return TMM_MINUSCULAS;
        }

        if (eliminarPrimeros(cadena, 1).equals(eliminarPrimeros(cadena_minusculas, 1))) {
            return TMM_PRIMERA_MAYUSCULAS;
        }
        
        return TMM_NINGUNA;
    }


    /**
     * Transforma una cadena al estado de mayúsculas o minúsculas especificado.
     *
     * @param estado                        Estado de las mayúsculas o minúsculas
     *                                          - TMM_NINGUNA
     *                                          - TMM_MAYUSCULAS
     *                                          - TMM_MINUSCULAS
     *                                          - TMM_PRIMERA_MAYUSCULAS
     *
     * @param cadena                        Cadena a transformar
     * 
     * @return                              Cadena transformada
     */
    public static String setMayusculasMinusculas(int estado, String cadena) {
        if (vacio(cadena)) return cadena;

        String nueva_cadena = cadena;

        switch (estado) {
            case TMM_NINGUNA:
                nueva_cadena = cadena;
                break;

            case TMM_MAYUSCULAS:
                nueva_cadena = cadena.toUpperCase();
                break;

            case TMM_MINUSCULAS:
                nueva_cadena = cadena.toLowerCase();
                break;

            case TMM_PRIMERA_MAYUSCULAS:
                String inicial = primeros(cadena, 1);
                String resto = eliminarPrimeros(cadena, 1);

                nueva_cadena = inicial.toUpperCase() + resto;
                break;
        }

        return nueva_cadena;
    }


    /**
     * Dada una cadena, elimina repeticiones de espacios en blanco.
     * Ejemplo:
     * "Miguel     Ángel  "   => "Miguel Ángel ";
     *
     * @param cadena                        Cadena original
     * @return                              Cadena sin repeticiones de espacios en blanco
    */
    public static String eliminarRepeticionesBlancos(String cadena) {
        String cadena2 = "";

        do {
            cadena2 = cadena.replaceAll("  ", " ");
            if (cadena2.equals(cadena)) break;
            cadena = cadena2;
        } while (true);

        return cadena2;
    }


    /**
     * Retorna una cadena dada, a menos que sea nula en cuyo caso retorna blanco.
     *
     * @param cadena                        Cadena original
     * @return                              Cadena original o cadena vacía si la cadena original era 'null'
     */
    public static String getCadenaOBlanco(String cadena) {
        if (cadena == null) return "";
        return cadena;
    }

    /**
     * Justifica una cadena con caracteres a la izquierda.
     *
     * @param cadena                        Cadena a justificar
     * @param longitud                      Longitud final de la cadena
     * @param caracter                      Caracter a usar para justificar
     *
     * @return                              Cadena justificada
     */
    public static String justificarIzquierda(String cadena, int longitud, String caracter) {
        if (cadena == null) cadena = "";
        while (cadena.length() < longitud) cadena = caracter + cadena;
        return cadena;
    }


    /**
     * Justifica una cadena con caracteres a la derecha.
     *
     * @param cadena                        Cadena a justificar
     * @param longitud                      Longitud final de la cadena
     * @param caracter                      Caracter a usar para justificar
     *
     * @return                              Cadena justificada
     */
    public static String justificarDerecha(String cadena, int longitud, String caracter) {
        if (cadena == null) cadena = "";
        while (cadena.length() < longitud) cadena = cadena + caracter;
        return cadena;
    }


    /**
     * Justifica una cadena con caracteres a la izquierda y derecha.
     *
     * @param cadena                        Cadena a justificar
     * @param longitud                      Longitud final de la cadena
     * @param caracter                      Caracter a usar para justificar
     *
     * @return                              Cadena justificada
     */
    public static String justificarCentro(String cadena, int longitud, String caracter) {
        if (cadena == null) cadena = "";
        boolean izquierda = true;
        while (cadena.length() < longitud) {
            if (izquierda) cadena = caracter + cadena;
            else cadena = cadena + caracter;

            izquierda = !izquierda;
        }
        return cadena;
    }


    /**
     * Justifica una cadena con espacios a la izquierda.
     *
     * @param cadena                        Cadena a justificar
     * @param longitud                      Longitud final de la cadena
     *
     * @return                              Cadena justificada
     */
    public static String justificarIzquierda(String cadena, int longitud) {
        String caracter = " ";
        return justificarIzquierda(cadena, longitud, caracter);

    }


    /**
     * Justifica una cadena con espacios a la derecha.
     *
     * @param cadena                        Cadena a justificar
     * @param longitud                      Longitud final de la cadena
     *
     * @return                              Cadena justificada
     */
    public static String justificarDerecha(String cadena, int longitud) {
        String caracter = " ";
        return justificarDerecha(cadena, longitud, caracter);

    }


    /**
     * Justifica una cadena con espacios a la izquierda y derecha.
     *
     * @param cadena                        Cadena a justificar
     * @param longitud                      Longitud final de la cadena
     *
     * @return                              Cadena justificada
     */
    public static String justificarCentro(String cadena, int longitud) {
        String caracter = " ";
        return justificarCentro(cadena, longitud, caracter);
    }



    /**
     * Retorna una cadena formada por una secuencia de "n" caracteres.
     *
     * @param c                             Caracter de la secuencia
     * @param r                             Número de repeticiones
     * 
     * @return                              Cadena generada
     */
    public static String repetirCaracter(String c, int r)
    {
        StringBuilder s = new StringBuilder("");
        while (s.length() < r) s.append(c);
        return s.toString();
    }


    /**
     * Elimina los valores nulos de un String [], susitituyéndolos por cadenas vacías "".
     *
     * @param array_original                String [] original
     * @return                              String [] cuyos nulos han sido sustituidos por cadenas vacías
    */
    public static String [] eliminarNulos(String [] array_original) {
        if (array_original == null) return null;

        String [] result = new String [array_original.length];
        for (int i = 0; i < array_original.length; i++) {
            if (array_original[i] ==  null) result[i] = "";
            else result[i] = array_original[i];
        }

        return result;
    }


    /**
     * Elimina los valores nulos de un String [][], susitituyéndolos por cadenas vacías "".
     *
     * @param array_original                String [][] original
     * @return                              String [][] cuyos nulos han sido sustituidos por cadenas vacías
     */
    public static String [][] eliminarNulos(String [][] array_original) {
        if (array_original == null) return null;
        if (array_original[0] == null) return null;

        String [][] result = new String [array_original.length][array_original[0].length];

        for (int i = 0; i < array_original.length; i++) {
            for (int j = 0; j < array_original[0].length; j++) {
                if (array_original[i][j] ==  null) result[i][j] = "";
                else result[i][j] = array_original[i][j];
            }
        }

        return result;
    }

    
    /**
     * Hace un trim() a una cadena. Si la cadena es 'null' no provoca error.
     *
     * @param cadena                        Cadena original
     * @return                              Cadena con el trim
     */
    public static String trim(String cadena) {
        if (cadena == null) return null;
        return cadena.trim();
    }
    

    /**
     * Hace un trim() a todos los elementos de un String [].
     *
     * @param array_original                String [] original
     * @return                              String [] con el trim() de los elementos originales
     */
    public static String [] trim(String [] array_original) {
        if (array_original == null) return null;

        String [] result = new String [array_original.length];
        for (int i = 0; i < array_original.length; i++) {
            if (array_original[i] == null) result[i] = null;
            else result[i] = array_original[i].trim();
        }

        return result;
    }


    /**
     * Hace un trim() a todos los elementos de un String [][].
     *
     * @param array_original                String [][] original
     * @return                              String [][] con el trim() de los elementos originales
     */
    public static String [][] trim(String [][] array_original) {
        if (array_original == null) return null;
        if (array_original.length == 0) return array_original;
        if (array_original[0] == null) return null;

        String [][] result = new String [array_original.length][array_original[0].length];

        for (int i = 0; i < array_original.length; i++) {
            for (int j = 0; j < array_original[0].length; j++) {
                if (array_original[i][j] == null) result[i][j] = null;
                else result[i][j] = array_original[i][j].trim();
            }
        }

        return result;
    }


    /**
     * Convierte en mayúsculas todos los elementos de un String [].
     *
     * @param array_original                String [] original
     * @return                              String [] con los elementos en mayúsculas
     */
    public static String [] toUpperCase(String [] array_original) {
        if (array_original == null) return null;

        String [] result = new String [array_original.length];
        for (int i = 0; i < array_original.length; i++) {
            if (array_original[i] == null) result[i] = null;
            else result[i] = array_original[i].toUpperCase();
        }

        return result;
    }



    /**
     * Convierte en mayúsculas todos los elementos de un String [][].
     *
     * @param array_original                String [][] original
     * @return                              String [][] con los elementos en mayúsculas
     */
    public static String [][] toUpperCase(String [][] array_original) {
        if (array_original == null) return null;
        if (array_original[0] == null) return null;

        String [][] result = new String [array_original.length][array_original[0].length];

        for (int i = 0; i < array_original.length; i++) {
            for (int j = 0; j < array_original[0].length; j++) {
                if (array_original[i][j] == null) result[i][j] = null;
                else result[i][j] = array_original[i][j].toUpperCase();
            }
        }

        return result;
    }


    /**
     * Comprueba si un carácter de entrada contiene una letra de la A a la Z.
     * Los acentos y eñe también se consideran letras.
     *
     * @param c                     Carácter con la posible letra
     * @return                      'true' si el carácter contiene una letra
     *                              'false' si no
     */
    public static boolean esLetra(char c) {
        if (c >= 'A' && c <= 'Z') return true;
        if (c >= 'a' && c <= 'z') return true;
        if (c == 'Ñ' || c == 'ñ') return true;
        if (c == 'Á' || c == 'á') return true;
        if (c == 'É' || c == 'é') return true;
        if (c == 'Í' || c == 'í') return true;
        if (c == 'Ó' || c == 'ó') return true;
        if (c == 'Ú' || c == 'ú' || c == 'Ü' || c == 'ü') return true;

        return false;
    }


    /**
     * Comprueba si un carácter de es una letra vocal.
     *
     * @param c                     Carácter con la posible letra
     * @return                      'true' si el carácter contiene una vocal
     *                              'false' si no
     */
    public static boolean esVocal(char c) {
        if (c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U') return true;
        if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') return true;

        if (c == 'Á' || c == 'á') return true;
        if (c == 'É' || c == 'é') return true;
        if (c == 'Í' || c == 'í') return true;
        if (c == 'Ó' || c == 'ó') return true;
        if (c == 'Ú' || c == 'ú' || c == 'Ü' || c == 'ü') return true;

        return false;
    }


    /**
     * Comprueba si un carácter de es una letra consonante.
     *
     * @param c                     Carácter con la posible letra
     * @return                      'true' si el carácter contiene una consonante
     *                              'false' si no
     */
    public static boolean esConsonante(char c) {
        if (!esLetra(c)) return false;
        if (esVocal(c)) return false;
        return true;
    }


    /**
     * Dada una cadena, obtiene otra igual en la que se han reemplazado ciertos caracteres por otros.
     *
     * @param cadena                        Cadena original
     * @param caracteres_originales         Caracteres a reemplazar
     * @param caracteres_finales            Caracteres por los que serán reemplazados (El array debe tener la misma longitud)
     * 
     * @return                              Cadena sin acentos ni diéresis
     */
    public static String reemplazarCaracteres(String cadena, char [] caracteres_originales, char [] caracteres_finales) {

        StringBuilder r = new StringBuilder();
        
        for (int i = 0; i < cadena.length(); i++) {
            char c = cadena.charAt(i);
            char nuevo_c;
            
            int pos = Arrays.pos(caracteres_originales, c);
            if (pos == -1) {
                nuevo_c = c;
            } else {
                nuevo_c = caracteres_finales[pos];
            }
            
            r.append(nuevo_c);
        }
        
        return r.toString();
    }
    
    
    
    
    
    /**
     * Dada una cadena, obtiene otra igual pero en la que se han reemplazado los acentos y diéresis por las vocales equivalentes sin estas puntuaciones.
     * La Ñ la respeta.
     *
     * Ejemplo: Ángel -> Angel
     *          Cigüeña -> Cigueña
     *
     * @param cadena                        Cadena original
     * @return                              Cadena sin acentos ni diéresis
     */
    public static String quitarAcentos(String cadena) {

        char caracteres_originales [] = {'Á', 'É', 'Í', 'Ó', 'Ú', 'Ü', 'á', 'é', 'í', 'ó', 'ú', 'ü'};
        char caracteres_finales []    = {'A', 'E', 'I', 'O', 'U', 'U', 'a', 'e', 'i', 'o', 'u', 'u'};

        String s = reemplazarCaracteres(cadena, caracteres_originales, caracteres_finales);
        return s;
    }
    
    
    /**
     * Dada una cadena, obtiene otra igual pero en la que se han reemplazado los acentos y diéresis por las vocales equivalentes sin estas puntuaciones.
     * La Ñ es sustituida por la N.
     *
     * Ejemplo: Ángel -> Angel
     *          Cigüeña -> Cigueña
     *
     * @param cadena                        Cadena original
     * @return                              Cadena sin acentos ni diéresis
     */
    public static String quitarAcentosYEñes(String cadena) {

        char caracteres_originales [] = {'Á', 'É', 'Í', 'Ó', 'Ú', 'Ü', 'á', 'é', 'í', 'ó', 'ú', 'ü', 'ñ', 'Ñ'};
        char caracteres_finales []    = {'A', 'E', 'I', 'O', 'U', 'U', 'a', 'e', 'i', 'o', 'u', 'u', 'n', 'N'};

        String s = reemplazarCaracteres(cadena, caracteres_originales, caracteres_finales);
        return s;
    }


    /**
     * Comprueba si una cadena contiene a otra en su interior. Se ignoran las mayúsculas y minúsculas.
     *
     * @param cadena                        Cadena grande
     * @param subcadena                     Cadena a buscar
     *
     * @return                              'true' si la cadena grande contiene a la subcadena
     *                                      'false' si no
     */
    public static boolean contiene(String cadena, String subcadena) {

        if (vacio(subcadena)) {
            return true;
        }
        if (vacio(cadena)) {
            return false;
        }

        cadena = quitarAcentos(cadena);
        subcadena = quitarAcentos(subcadena);

        String regex = "(?i).*" + subcadena + ".*";
        boolean encontrado = cadena.matches(regex);
        return encontrado;
    }


    /**
     * Elimina caracteres no deseados dentro de una cadena.
     *
     * @param cadena                            Cadena original
     * @param piojos                            Cadena con los caracteres no deseados
     * @return                                  Nueva cadena donde se han eliminado los caracteres indeseados
     */
    public static String despiojar(String cadena, String piojos) {
        if (cadena == null) {
            return cadena;
        }

        String result = "";
        for (int i = 0; i < cadena.length(); i++) {
            char caracter = cadena.charAt(i);
            if (piojos.indexOf(caracter) == -1) result = result + caracter;

        }
        return result;
    }

    
    /**
     * Añade comillas simples al principio y al final de una cadena, siempre que esta no está ya entrecomillada.
     *
     * @param cadena                            Cadena original
     * @return                                  Cadena con comillas simples al principio y al final
     */
    public static String entrecomillar(String cadena) {
        if (cadena == null) {
            return null;
        }

        if (cadena.length() == 0) {
            return "''";
        }

        if (!primeros(cadena, 1).equals("'")) {
            cadena = "'" + cadena;
        }
        if (!ultimos(cadena, 1).equals("'")) {
            cadena = cadena + "'";
        }

        return cadena;
    }


    /**
     * Añade comillas simples al principio y al final de un número.
     *
     * @param numero                        Número
     * @return                              Cadena con comillas simples al principio y al final
     */
    public static String entrecomillar(int numero) {

        String cadena = "'" + Integer.toString(numero) + "'";
        return cadena;
    }


    /**
     * Obtiene una subcadena encerrada en otra cadena.
     * Ejemplos:
     *   Cadena                 Apertura      Cierre      Resultado
     *   [amigo]                [             ]           amigo
     *   amigo]                 [             ]           amigo]
     *   mi [amigo] del alma    [             ]           amigo
     *   [am[igo]               [             ]           am[igo
     *   mi [amigo] del [alma]  [             ]           amigo
     *   mi "amigo" del alma    "             "           amigo
     *
     * Si hubiera más de una subcadena, retorna solo la primera.
     *
     * @param cadena                        Cadena original
     * @param encerrador_apertura           Caracter o cadena que establece el inicio del cierre
     * @param encerrador_cierre             Caracter o cadena que establece el fin del cierre
     * 
     * @return                              Cadena resultante
     */
    public static String getCadenaEncerrada(String cadena, String encerrador_apertura, String encerrador_cierre) {
        int i1 = cadena.indexOf(encerrador_apertura);
        if (i1 == -1) return cadena;

        i1++;
        if (i1 >= cadena.length()) return cadena;

        int i2 = cadena.indexOf(encerrador_cierre, i1 + 1);
        if (i2 == -1) return cadena;

        String subcadena = cadena.substring(i1, i2);
        return subcadena;
    }


    /**
     * Dada una cadena de N caracteres, la divide en varias subcadenas de M caracteres cada una.
     *
     * @param cadena_larga          Cadena a dividir
     * @param num_caracteres_trozo  Número de caracteres máximo para cada trozo
     * @return                      String [] con las cadenas resultado de trocear la cadena larga
     */
    public static String [] trocear(String cadena_larga, int num_caracteres_trozo) {
        if (num_caracteres_trozo == 0) return new String[] {cadena_larga};

        int l = cadena_larga.length();
        int num_trozos = l / num_caracteres_trozo;
        if (l % num_caracteres_trozo > 0) num_trozos ++;

        String [] trozos = new String[num_trozos];

        for (int i = 0; i < num_trozos; i++) {

            int desde  = i * num_caracteres_trozo;
            int hasta = i * num_caracteres_trozo + num_caracteres_trozo - 1;

            trozos[i] = miSubstring(cadena_larga, desde, hasta);
        }

        return trozos;
    }


    /**
     * Convierte una cadena a un entero de modo que, si hay un error de conversión, retorna el valor 0.
     *
     * @param cadena                            Cadena que contiene el entero
     * 
     * @return                                  Entero
     */
     public static int toEntero(String cadena) {
         int n;

         try {
             n = Integer.parseInt(cadena.trim());
         } catch (Exception ex) {
             n = 0;
         }

         return n;
     }


    /**
     * Convierte una cadena a un booleano, de acuerdo a estas condiciones:
     * 
     *      cadena = null           --> false
     *      cadena = ""             --> false
     *      cadena = "0"            --> false
     *      cadena = "-1"           --> false
     *      cadena = "alfabético"   --> false
     *      cadena = "n"            --> true (n es un número entero, distinto de 0 y 1)
     *
     * @param cadena                            Cadena que contiene el valor
     * 
     * @return                                  Booleano
     */
     public static boolean toBoolean(String cadena) {
         
         boolean b;
         
         int n;
         try {
             n = Integer.parseInt(cadena.trim());
             if (n == 0 || n == -1) {
                 return false;
             }
             
             return true;
             
         } catch (Exception ex) {
             return false;
         }
     }
     
     
     
    /**
     * Convierte una cadena a un entero de modo que, si hay un error de conversión, retorna el valor predeterminado.
     *
     * @param cadena                            Cadena que contiene el entero
     * @param valor_predeterminado              Valor a retornar en caso de error
     *
     * @return                                  Entero
     */
     public static int toEntero(String cadena, int valor_predeterminado) {
         int n;

         try {
             n = Integer.parseInt(cadena.trim());
         } catch (Exception ex) {
             n = valor_predeterminado;
         }

         return n;
     }


    /**
     * Convierte una cadena a un entero largo de modo que, si hay un error de conversión, retorna el valor 0.
     *
     * @param cadena                            Cadena que contiene el entero
     * @return                                  Entero largo
     */
     public static long toLong(String cadena) {
         long n;

         try {
             n = Long.parseLong(cadena);
         } catch (NumberFormatException ex) {
             n = 0;
         }

         return n;
     }

     
    /**
     * Convierte una cadena a un entero largo de modo que, si hay un error de conversión, retorna el valor predeterminado.
     *
     * @param cadena                            Cadena que contiene el entero
     * @param valor_predeterminado              Valor a retornar en caso de error
     *
     * @return                                  Entero
     */
     public static long toLong(String cadena, long valor_predeterminado) {
         long n;

         try {
             n = Long.parseLong(cadena);
         } catch (Exception ex) {
             n = valor_predeterminado;
         }

         return n;
     }
     

    /**
     * Convierte una cadena a un double de modo que, si hay un error de conversión, retorna el valor 0.
     *
     * @param cadena                            Cadena que contiene el double
     * @return                                  Double
     */
     public static double toDouble(String cadena) {
         double n;

         try {
             n = Double.parseDouble(cadena);
         } catch (NumberFormatException ex) {
             n = 0;
         }

         return n;
     }


    /**
     * Obtiene el sufijo para expresar un término en plural, dependiendo de la cantidad dada.
     * @param cantidad
     * @return                                  "" o "s", en función de la cantidad
     */
    public static String getPluralS(int cantidad) {
        if (cantidad == 1) return "";
        return "s";
    }


    /**
     * Obtiene el sufijo de un plural, dependiendo de la cantidad dada.
     * @param cantidad
     * @return                                  "" o "es", en función de la cantidad
     */
    public static String getPluralEs(int cantidad) {
        if (cantidad == 1) return "";
        return "es";
    }


    /**
     * Obtiene la cadena Sí o No en función del valor del booleano de entrada.
     *
     * @param valor                         Valor booleano
     * @return                              Cadena "Sí", si valor es 'true'
     *                                      Cadena "No", si valor es 'false'
     */
    public static String getSiNo(boolean valor) {
        if (valor) return "Sí";
        else return "No";
    }


    /**
     * Obtiene la cadena Sí o No en función del valor de entrada.
     *
     * @param valor                         Valor entero que será 1 ó 0
     * @return                              Cadena "Sí", si valor es 1
     *                                      Cadena "No", si valor es 0
     */
    public static String getSiNo(int valor) {
        if (valor == 1) return "Sí";
        else return "No";
    }

   
    /**
     * Concatena varias cadenas en una sola.
     * Las cadenas concatendas van separadas por un espacio.
     *
     * @param cadena                            Cadenas a concatenar
     * 
     * @return                                  Texto con la concatenación
     */
    public static String concatenarCadenas(String ... cadena) {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < cadena.length; i++) {
            s.append(trim(cadena[i]));
            s.append(" ");
        }
        
        String result = s.toString().trim();
        return result;
    }

    
    /**
     * Concatena un nombre y dos apellidos en una sola cadena.
     *
     * @param nombre                            Nombre
     * @param apellido_1                        Primer apellido
     * @param apellido_2                        Segundo apellido
     * 
     * @return                                  Cadena con el nombre más los apellidos
     */
    public static String concatenarNombreYApellidos(String nombre, String apellido_1, String apellido_2) {
        
        String [] arr_cadenas = {nombre, apellido_1, apellido_2};
        String cadena = concatenarCadenas(arr_cadenas);
        return cadena;
    }
    
    
    /**
     * Concatena un nombre y un apellido en una sola cadena.
     *
     * @param nombre                            Nombre
     * @param apellido                          Apellido
     * 
     * @return                                  Cadena con el nombre más los apellidos
     */
    public static String concatenarNombreYApellidos(String nombre, String apellido) {
        String [] arr_cadenas = {nombre, apellido};
        String cadena = concatenarCadenas(arr_cadenas);
        return cadena;
    }

    
    /**
     * Dada una cadena con nombre y apellidos, obtiene la parte correspondiente al nombre.
     * 
     * @param nombre_y_apellidos                Cadena con el nombre y los apellidos.
     * 
     * @return                                  Nombre
     */
    public static String getNombre(String nombre_y_apellidos) {
        String [] partes = nombre_y_apellidos.split(" ");
        String nombre;
        if (partes.length > 3) {
            nombre = partes[0] + " " + partes[1];
        } else {
            nombre = partes[0];
        }
        
        return nombre;
    }

    
    /**
     * Dada una cadena con nombre y apellidos, obtiene la parte correspondiente al primer apellido.
     * 
     * @param nombre_y_apellidos                Cadena con el nombre y los apellidos.
     * 
     * @return                                  Primer apellido
     */
    public static String getPrimerApellido(String nombre_y_apellidos) {
        String [] partes = nombre_y_apellidos.split(" ");
        String apellido = "";
        if (partes.length == 1) {
            apellido = partes[0];
        } else if (partes.length == 2) {
            apellido = partes[1];
        } else {
            apellido = partes[partes.length - 2];
        }
        
        return apellido;
    }
    
    
    /**
     * Pone en mayúsculas la primera letra de la frase.
     *
     * @param cadena                    Cadena original (Ejemplo: "EL ZORRO GRIS")
     * @return                          Cadena con la primera letra en mayúsculas (Ejemplo: "El zorro gris");
     */
    public static String mayusculasPrimera(String cadena) {

        if (cadena == null) return null;
        if (cadena.length() <= 1) return cadena;
        
        String p = primeros(cadena, 1);
        String resto = eliminarPrimeros(cadena, 1);
        String result = p.toUpperCase() + resto;
        return result;
    }
    

    /**
     * Pone en mayúsculas la primera letra de cada palabra de la frase dada, como si fuera
     * un título.
     *
     * @param cadena                    Cadena original (Ejemplo: "El zorro gris")
     * @return                          Cadena como si fuera un título (Ejemplo: "El Zorro Gris");
     */
    public static String mayusculasTitulo(String cadena) {

        if (cadena == null) return null;

        StringBuilder result = new StringBuilder("");
        boolean sig_mayusculas = true;

        for (int i = 0; i < cadena.length(); i++) {
            String c = cadena.substring(i, i + 1);
            if (sig_mayusculas) c = c.toUpperCase(); else c = c.toLowerCase();
            result.append(c);
            if (c.equals(" ")) {
                sig_mayusculas = true; 
            } else {
                sig_mayusculas = false;
            }
        }

        return result.toString();
    }

    
    /**
     * Inserta etiquetas, almacenadas en un String [], dentro del mensaje dado.
     *
     * Cada posición del String se insertará en el mensaje donde encuentra las
     * etiquetas de sustitución, que tendrán el formato: %0%, %1%, %2%... (El Símbolo '%' puede cambiar)
     *
     * @param mensaje                   Mensaje de original (con las etiquetas)
     * @param tags                       Contenido para sustituir por las etiquetas
     *                                  Si es 'null', no se toca el mensaje original
     * @return                          Mensaje donde se han sustituido las etiquetas por los tags
     */
    public static String sustituirTagsMensaje(String mensaje, String [] tags) {

        if (tags == null) return mensaje;

        // Insertamos los textos dinámicos dentro del mensaje de error

        for (int i = 0; i < tags.length; i++) {
            String buscar = "%" + Integer.toString(i) + "%";

            if (mensaje.contains(buscar)) {
                String t = "";
                if (tags[i] != null) t = tags[i];
                mensaje = mensaje.replaceAll(buscar, t);
            }

        }

        return mensaje;
    }


    /**
     * Reemplaza todas las ocurrencias de un texto dentro de una cadena por otro texto.
     * Esta función se comporta como el replaceAll nativo de Java, pero no trata con expresiones regulares.
     * Sirve para paliar los problemas del replaceAll de Java con ciertos caracteres poco habituales, que dan errores de conversión.
     * La búsqueda del texto es sensible a las mayúsculas / minúsculas.
     * Ejemplos:
     *     replaceAll("pepito conejito", "ito", "?")    --> "pep? conej?"
     *     replaceAll("xxxxxxx", "x", "y")              --> "yyyyyy"
     *     replaceAll("xxxxxxx", "x", "yy")             --> "yyyyyyyyyyyy"
     *     replaceAll("xxxxxxx", "xx", "y")             --> "yyyx"
     *
     * @param cadena_original               Cadena original
     * @param texto_buscar                  Texto a buscar
     * @param texto_reemplazo               Texto por el que se va a reemplazar el texto a buscar
     *
     * @return                              Nueva cadena
     */
    public static String replaceAll(String cadena_original, String texto_buscar, String texto_reemplazo) {
        int lb = texto_buscar.length();
        int lr = texto_reemplazo.length();

        String result = cadena_original;
        if (result == null) {
            return null;
        }
        
        for (int i = 0; i < result.length() - lb + 1; i++) {
            String subcadena = miSubstringLong(result, i, lb);
            if (subcadena.equals(texto_buscar)) {
                result = reemplazarPosicion(result, i, lb, texto_reemplazo);
                i += lr - 1;
            }
        }

        return result;
    }




    /**
     * Inserta el contenido dinámico, almacenados en un Vector <String> dentro de una cadena.
     * La cadena de entrada contiene etiquetas de sustitución, delimitados por un caracter especial suministrado,
     * en formato: %0%, %1%, %2%..., suponiendo que el carácter de sustitución es el "%".
     * Estas etiquetas son sustituidas por los valores en cada posición del vector.
     *
     * @param cadena_original               Cadena original (con las etiquetas)
     * @param l_tags                        List <String> con el contenido a introducir en cada etiqueta
     *                                      Si es 'null', no se toca el mensaje original
     * @param delimitador                   Carácter usado para delimitar los tags dentro de la cadena original
     *
     * @return                              La cadena original donde se han sustituido las etiquetas por los tags
     */
    public static String sustituirTagsCadena(String cadena_original, List <String> l_tags, String delimitador) {

        // Insertamos los textos dinámicos dentro del mensaje de error
        for (int i = 0; i < Arrays.size(l_tags); i++) {
            String buscar = delimitador + Integer.toString(i) + delimitador;

            if (cadena_original.contains(buscar)) {
                String t = l_tags.get(i);
                cadena_original = cadena_original.replace(buscar, t);
            }
        }

        return cadena_original;
    }


    /**
     * Inserta el contenido dinámico, almacenados en un Vector <String> dentro de una cadena.
     * La cadena de entrada contiene etiquetas de sustitución en formato: %0%, %1%, %2%...
     * Estas etiquetas son sustituidas por los valores en cada posición del vector.
     *
     * @param cadena_original                   Cadena original (con las etiquetas)
     * @param l_tags                            Lista con el contenido a introducir en cada etiqueta
     *                                          Si es 'null', no se toca el mensaje original
     *
     * @return                                  La cadena original donde se han sustituido las etiquetas por los tags
     */
    public static String sustituirTagsCadena(String cadena_original, List <String> l_tags) {
        String delimitador = "%";
        String nueva_cadena = sustituirTagsCadena(cadena_original, l_tags, delimitador);
        return nueva_cadena;
    }


    /**
     * Inserta el contenido dinámico, almacenados en un Vector <String> dentro de una cadena.
     * La cadena de entrada contiene etiquetas de sustitución en formato: %0%, %1%, %2%...
     * Estas etiquetas son sustituidas por los valores en cada posición del vector.
     *
     * @param cadena_original                   Cadena original (con las etiquetas)
     * @param tags                              String [] con el contenido a introducir en cada etiqueta
     *                                          Si es 'null', no se toca el mensaje original
     *
     * @return                                  La cadena original donde se han sustituido las etiquetas por los tags
     */
    public static String sustituirTagsCadena(String cadena_original, String [] tags) {
        List <String> l_tags = java.util.Arrays.asList(tags);
        String nueva_cadena = sustituirTagsCadena(cadena_original, l_tags);
        return nueva_cadena;
    }


    /**
     * Inserta el contenido dinámico, almacenado en el parámetro "tag" suministrado, dentro de una cadena dada.
     * La cadena de entrada debe contener una etiqueta de sustitución en formato: %0%, que es sustituida por el contenido dinámico.
     *
     * @param cadena_original               Cadena original (con la etiqueta de sustitución)
     * @param tag                           String con el contenido a introducir en la etiqueta a
     *                                      Si es 'null', no se toca el mensaje original
     *
     * @return                              La cadena original donde se ha sustituido la etiqueta por el tag
     */
    public static String sustituirTagCadena(String cadena_original, String tag) {
        String nueva_cadena = sustituirTagsCadena(cadena_original, new String [] {tag});
        return nueva_cadena;
    }


    /**
     * Inserta el contenido dinámico, almacenado en los parámetros "tag_1" y "tag_2" suministrados, dentro de una cadena dada.
     * La cadena de entrada debe contener dos etiquetas de sustitución en formato: %0% y %1%, que son sustituidas por el contenido dinámico.
     *
     * @param cadena_original               Cadena original (con las etiquetas de sustitución)
     * @param tag_1                         String con el contenido a introducir en la primera etiqueta
     * @param tag_2                         String con el contenido a introducir en la segunda etiqueta
     *
     * @return                              La cadena original donde se ha sustituido las etiquetas por los tags
     */
    public static String sustituirTagsCadena(String cadena_original, String tag_1, String tag_2) {
        String nueva_cadena = sustituirTagsCadena(cadena_original, new String [] {tag_1, tag_2});
        return nueva_cadena;
    }


    /**
     * Inserta el contenido dinámico, almacenado en el parámetro "tag" suministrado, dentro de una cadena dada.
     * La cadena de entrada debe contener una etiqueta de sustitución en formato: %0%, que es sustituida por el contenido dinámico.
     *
     * @param cadena_original               Cadena original (con la etiqueta de sustitución)
     * @param tag                           Entero con el contenido a introducir en la etiqueta a
     *                                      Si es 'null', no se toca el mensaje original
     *
     * @return                              La cadena original donde se ha sustituido la etiqueta por el tag
     */
    public static String sustituirTagCadena(String cadena_original, int tag) {
        String nueva_cadena = sustituirTagsCadena(cadena_original, new String [] {Integer.toString(tag)});
        return nueva_cadena;
    }


    /**
     * Inserta el contenido dinámico, almacenado en los parámetros "tag_1" y "tag_2" suministrados, dentro de una cadena dada.
     * La cadena de entrada debe contener dos etiquetas de sustitución en formato: %0% y %1%, que son sustituidas por el contenido dinámico.
     *
     * @param cadena_original               Cadena original (con las etiquetas de sustitución)
     * @param tag_1                         Entero con el contenido a introducir en la primera etiqueta
     * @param tag_2                         Entero con el contenido a introducir en la segunda etiqueta
     *
     * @return                              La cadena original donde se ha sustituido las etiquetas por los tags
     */
    public static String sustituirTagsCadena(String cadena_original, int tag_1, int tag_2) {
        String nueva_cadena = sustituirTagsCadena(cadena_original, new String [] {Integer.toString(tag_1), Integer.toString(tag_2)});
        return nueva_cadena;
    }


    
    
    /**
     * Dado un texto, extrae la cadena comprendida entre las dos etiquetas dadas. Las etiquetas no se incluyen en el resultado.
     * El parámetro orden está por si hubiera más de una ocurrencia, para retornar la de orden dado (la primera es de orden 0).
     * La búsqueda es "case-sensitive".
     * 
     * Ejemplos:
     *    Texto; En_un_lugar_de_la_Mancha,_de_cuyo_nombre_no_quiero_acordarme,_no_ha_mucho_tiempo_que_vivía_un_hidalgo_de_los_de_lanza_en_astillero
     * 
     *    Etiqueta inicial: "Mancha"
     *    Etiqueta final: "quiero"
     *    Orden: 0
     *    Resultado: ",_de_cuyo_nombre_no_"
     * 
     *    Etiqueta inicial: ","
     *    Etiqueta final: ","
     *    Orden: 0
     *    Resultado: "_de_cuyo_nombre_no_quiero_acordarme"
     * 
     *    Etiqueta inicial: "de"
     *    Etiqueta final: "de" te brinda un asesoramiento integral o puntual, para que abrir la franquicia 
     *    Orden: 0
     *    Resultado: "_la Mancha,_"
     * 
     *    Etiqueta inicial: "de"
     *    Etiqueta final: "de"
     *    Orden: 1
     *    Resultado: "_cuyo_nombre_no_quiero_acordarme,_no_ha_mucho_tiempo_que_vivía_un_hidalgo_"
     * 
     * @param texto                         Texto donde se va a buscar
     * @param etiqueta_inicial              Etiqueta a partir de la cual se extraerá la cadena
     * @param etiqueta_final                Etiqueta que determinará el final de la extracción
     * @param orden                         Número de la ocurrencia a extraer (0 para la primera ocurrencia que se encuentre)
     * 
     * @return                              Cadena con el texto comprendido entre 'etiqueta_inicial' y 'etiqueta_final'
     *                                      'null' si no se encuentra alguna de las etiquetas
     */
    public static String getTextoEntreEtiquetas(String texto, String etiqueta_inicial, String etiqueta_final, int orden) {
        
        int long_etiqueta_inicial = etiqueta_inicial.length();
        
        orden++;
        int pos_inicial = 0;
        
        for (int i = 0; i < orden; i++) {
            int uno = 0;
            if (i > 0) uno = 1;
            
            pos_inicial = texto.indexOf(etiqueta_inicial, pos_inicial + uno);
            if (pos_inicial == -1) return null;
        }
        
        int pos_final = 0;
        for (int i = 0; i < orden; i++) {
            int uno = 0;
            if (i > 0) uno = 1;

            pos_final = texto.indexOf(etiqueta_final, pos_final + uno);
            if (pos_final == -1) return null;
        }
        
        if (pos_final <= pos_inicial + long_etiqueta_inicial) {
            pos_final = texto.indexOf(etiqueta_final, pos_final + long_etiqueta_inicial);
        }
        
        String result = texto.substring(pos_inicial + long_etiqueta_inicial, pos_final);
        return result;
    }

}



