/**
 * Numeros.java
 *
 * Creado el 21-oct-2015, 17:50:45
 */

package es.pmp.pmplib;

/**
 * Funciones de propósito general relacionadas con cadenas.
 * 
 * @author pmpreciado
 */
public class Numeros {

    
    /**
     * Obtiene el mayor entre dos números dados.
     *
     * @param numero_1                      Primer número
     * @param numero_2                      Segundo número
     *
     * @return                              El mayor de los dos números
     */
    public static int mayor(int numero_1, int numero_2) {
        if (numero_1 > numero_2) return numero_1;
        return numero_2;
    }


    /**
     * Obtiene el mayor entre tres números dados.
     *
     * @param numero_1                      Primer número
     * @param numero_2                      Segundo número
     * @param numero_3                      Tercer número
     *
     * @return                              El mayor de los tres números
     */
    public static int mayor(int numero_1, int numero_2, int numero_3) {
        int m1 = mayor(numero_1, numero_2);
        int m2 = mayor(m1, numero_3);
        return m2;
    }


    /**
     * Obtiene el mayor entre dos números dados.
     *
     * @param numero_1                      Primer número
     * @param numero_2                      Segundo número
     *
     * @return                              El mayor de los dos números
     */
    public static float mayor(float numero_1, float numero_2) {
        if (numero_1 > numero_2) return numero_1;
        return numero_2;
    }


    /**
     * Obtiene el segundo mayor entre tres números dados.
     *
     * @param numero_1                      Primer número
     * @param numero_2                      Segundo número
     * @param numero_3                      Tercer número
     *
     * @return                              El segundo mayor de los tres números
     */
    public static int segundoMayor(int numero_1, int numero_2, int numero_3) {

        if (numero_1 >= numero_2 && numero_1 >= numero_3) {
            int sm = mayor(numero_2, numero_3);
            return sm;
        }

        if (numero_2 >= numero_1 && numero_2 >= numero_3) {
            int sm = mayor(numero_1, numero_3);
            return sm;
        }

        int sm = mayor(numero_1, numero_2);
        return sm;
    }


    /**
     * Obtiene el menor entre dos números dados.
     *
     * @param numero_1                      Primer número
     * @param numero_2                      Segundo número
     *
     * @return                              El menor de los dos números
     */
    public static int menor(int numero_1, int numero_2) {
        if (numero_1 < numero_2) return numero_1;
        return numero_2;
    }


    /**
     * Obtiene el menor entre dos números dados.
     *
     * @param numero_1                      Primer número
     * @param numero_2                      Segundo número
     *
     * @return                              El menor de los dos números
     */
    public static float menor(float numero_1, float numero_2) {
        if (numero_1 < numero_2) return numero_1;
        return numero_2;
    }


    /**
     * Obtiene los 'n' primeros dígitos de un número.
     *
     * @param numero                        Número sobre el que se quiere extraer los dígitos
     * @param n                             'n' primeros dígitos a extraer
     * 
     * @return                              Dígitos extraídos
     */
    public static int primeros(int numero, int n) {
        String s_numero = Integer.toString(numero);
        String s_primeros = Cadenas.primeros(s_numero, n);
        return Integer.parseInt(s_primeros);
    }


    /**
     * Obtiene los 'n' últimos dígitos de un número.
     *
     * @param numero                        Número sobre el que se quiere extraer los dígitos
     * @param n                             'n' últimos dígitos a extraer
     * @return                              Dígitos extraídos
     */
    public static int ultimos(int numero, int n) {
        String s_numero = Integer.toString(numero);
        String s_ultimos = Cadenas.ultimos(s_numero, n);
        return Integer.parseInt(s_ultimos);
    }


    /**
     * Dado un número, obtiene el literal correspondiente.
     * Si no hay literal, obtiene el mismo número en una cadena.
     *
     * @param numero                        Número para el que se va a obtener el texto
     * @return                              Texto literal (por ejemplo, "cuatro")
     */
    public static String numero2Literal(int numero) {
        switch (numero) {
            case 0: return "cero";
            case 1: return "uno";
            case 2: return "dos";
            case 3: return "tres";
            case 4: return "cuatro";
            case 5: return "cinco";
            case 6: return "seis";
            case 7: return "siete";
            case 8: return "ocho";
            case 9: return "nueve";
            case 10: return "diez";
            case 11: return "once";
            case 12: return "doce";
            case 13: return "trece";
            case 14: return "catorce";
            case 15: return "quince";
            case 16: return "dieciséis";
            case 17: return "diecisiete";
            case 18: return "dieciocho";
            case 19: return "diecinueve";
            case 20: return "veinte";
            case 21: return "veintiuno";
            case 22: return "veintidós";
            case 23: return "veintitrés";
            case 24: return "veinticuatro";
            case 25: return "veinticinco";
            case 26: return "veintiséis";
            case 27: return "veintisiete";
            case 28: return "veintiocho";
            case 29: return "veintinueve";
            case 30: return "treinta";
            case 40: return "cuarenta";
            case 50: return "cincuenta";
            case 60: return "sesenta";
            case 70: return "setenta";
            case 80: return "ochenta";
            case 90: return "noventa";
            case 100: return "cien";
            case 1000: return "mil";
            case 10000: return "diez mil";
            case 100000: return "cien mil";
            case 1000000: return "un millón";
        }

        return Integer.toString(numero);
    }

    /**
     * Comprueba si una cadena dada representa un número entero.
     *
     * @param cadena                Cadena
     * @return                      'true' si la cadena representa un entero, 'false' si no
     */
    public static boolean esEntero(String cadena)
    {
        for (int i = 0; i < cadena.length(); i++) {
            char c = cadena.charAt(i);
            if (i == 0 && c == '-') continue;
            if (c < '0' || c > '9') return false;
        }

        return true;
    }


    /**
     * Comprueba si una cadena dada representa un número entero positivo.
     *
     * @param cadena                Cadena
     * @return                      'true' si la cadena representa un entero, 'false' si no
     */
    public static boolean esEnteroPositivo(String cadena)
    {
        for (int i = 0; i < cadena.length(); i++) {
            char c = cadena.charAt(i);
            if (c < '0' || c > '9') return false;
        }

        return true;
    }


    /**
     * Comprueba si una cadena de entrada representa un número, entero o en coma flotante.
     * Para la comprobación se utiliza un expresión regular.
     *
     * @param cadena                Cadena con un posible número, por ejemplo -8234.06
     * @return                      'true' si la cadena contiene un número
     *                              'false' si no
     */
    public static boolean esNumero(String cadena) {
        if (cadena.length() == 0) return false;
        // Regex para reconocer un número en coma flotante tipo, -8234.06
        return cadena.matches("[\\-\\+]?[0-9]*\\.?[0-9]*");
    }


    /**
     * Comprueba si un carácter de entrada contiene un número.
     *
     * @param c                     Carácter con un posible número
     * @return                      'true' si el carácter contiene un número
     *                              'false' si no
     */
    public static boolean esNumero(char c) {
        if (c >= '0' && c <= '9') return true;
        return false;
    }

}

