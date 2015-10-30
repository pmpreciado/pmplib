/**
 * CFilaOrdenable.java
 *
 * Creado el 26-oct-2015, 13:11:47
 */

package es.pmp.pmplib.html.componentes.table;

import es.pmp.pmplib.Arrays;
import es.pmp.pmplib.Cadenas;
import es.pmp.pmplib.Comun;
import es.pmp.pmplib.Numeros;
import java.util.List;

/**
 * Clase auxiliar para ordenar las filas de la tabla.
 * 
 * @author pmpreciado
 */
public class CFilaOrdenable implements Comparable <CFilaOrdenable> {

    public CFila fila;
    public int pos_original;
    int columna_orden;

    public CFilaOrdenable() {
    }

    public CFilaOrdenable(CFila fila, int pos_original, int columna_orden) {
        this.fila = fila;
        this.pos_original = pos_original;
        this.columna_orden = columna_orden;
    }

    @Override
    public int compareTo(CFilaOrdenable o) {
        CCelda celda_orden_1 = this.fila.getCelda(columna_orden);
        CCelda celda_orden_2 = o.fila.getCelda(o.columna_orden);

        if (celda_orden_1 == null && celda_orden_2 == null) {
            return 0;
        } else if (celda_orden_1 == null) {
            return -1;
        } else if (celda_orden_2 == null) {
            return 1;
        }

        String texto_orden_1 = celda_orden_1.getContenido();
        String texto_orden_2 = celda_orden_2.getContenido();

        boolean es_numero_1 = Numeros.esNumero(texto_orden_1);
        boolean es_numero_2 = Numeros.esNumero(texto_orden_2);
        if (es_numero_1 && es_numero_2) {
            Integer numero_1 = Cadenas.toEntero(texto_orden_1);
            Integer numero_2 = Cadenas.toEntero(texto_orden_2);

            int c = numero_1.compareTo(numero_2);
            return c;
        } else {
            texto_orden_1 = texto_orden_1.toUpperCase();
            texto_orden_2 = texto_orden_2.toUpperCase();

            int c = texto_orden_1.compareTo(texto_orden_2);
            return c;
        }
    }


    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("Posición original: " + pos_original + ", " + Comun.NL);
        s.append(fila.toString());

        return s.toString();
    }


    /**
     * Busca la siguiente fila de datos existente dentro de la lista de filas dada.
     * La búsqueda comienza a partir de la posición dada por "pos_inicial".
     * 
     * @param l_filas_ordenar           Filas en la que se buscara la siguiente fila de datos
     * @param pos_inicial               Posición inicial para la búsqueda
     * 
     * @return                          Posición (índice) donde se encuentra la siguiente fila de datos
     *                                  -1 si no se encuentra
     */
    public int getPosSiguienteFilaDatos(List <CFilaOrdenable> l_filas_ordenar, int pos_inicial) {
        for (int i = pos_inicial; i < Arrays.size(l_filas_ordenar); i++) {
            CFilaOrdenable fila_ordenar = l_filas_ordenar.get(i);
            if (fila_ordenar.fila.tipo == CFila.TF_DATOS) {
                return i;
            }
        }

        return -1;
    }
}
