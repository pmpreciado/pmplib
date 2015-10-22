/**
 * Parametros.java
 *
 * Creado el 22-oct-2015, 13:45:56
 */

package es.pmp.pmplib.db;

import es.pmp.pmplib.Arrays;
import es.pmp.pmplib.Comun;
import es.pmp.pmplib.tipos.general.CDecimal;
import es.pmp.pmplib.tipos.general.CFecha;
import es.pmp.pmplib.tipos.general.CHora;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


/**
 * Métodos para la gestión de los parámetros usados en las consultas SQL.
 *
 * @author pmpreciado
 */
public class Parametros {
    
    public class ClaseParametro {
        public int tipo;
        public Object valor;
        public long long_blob;
    }
            
    
    /** Tipo de parámetro entero */
    public static final int TP_INT       = 1;
    
    /** Tipo de parámetro entero */
    public static final int TP_LONG      = 2;
    
    /** Tipo de parámetro double */
    public static final int TP_DOUBLE    = 3;

    /** Tipo de parámetro boolean */
    public static final int TP_BOOLEAN   = 4;

    /** Tipo de parámetro cadena */
    public static final int TP_STRING    = 10;

    /** Tipo de parámetro blob */
    public static final int TP_BLOB      = 11;

    /** Tipo de parámetro bytes */
    public static final int TP_BYTES     = 12;
    
    
    
    List <ClaseParametro> l_parametros;
    
    
    /**
     * Crea la instancia de la clase
     */
    public Parametros() {
        l_parametros = new ArrayList();
    }

    /**
     * Añade un parámetro de tipo entero.
     *
     * @param v                                 Parámetro
     */
    public void add(int v) {
        ClaseParametro param = new ClaseParametro();
        param.tipo = TP_INT;
        param.valor = v;
        l_parametros.add(param);
    }
    
    
    /**
     * Añade un parámetro de tipo long.
     *
     * @param v                                 Parámetro
     */
    public void add(long v) {
        ClaseParametro param = new ClaseParametro();
        param.tipo = TP_LONG;
        param.valor = v;
        l_parametros.add(param);
    }
    
    
    /**
     * Añade un parámetro de tipo double.
     *
     * @param v                                 Parámetro
     */
    public void add(double v) {
        ClaseParametro param = new ClaseParametro();
        param.tipo = TP_DOUBLE;
        param.valor = v;
        l_parametros.add(param);
    }
    
    
    /**
     * Añade un parámetro de tipo String.
     *
     * @param v                                 Parámetro
     */
    public void add(String v) {
        ClaseParametro param = new ClaseParametro();
        param.tipo = TP_STRING;
        param.valor = v;
        l_parametros.add(param);
    }


    /**
     * Añade un parámetro de tipo boolean.
     *
     * @param v                                 Parámetro
     */
    public void add(boolean v) {

        ClaseParametro param = new ClaseParametro();
        param.tipo = TP_BOOLEAN;
        param.valor = v;
        l_parametros.add(param);
    }
    
    
    /**
     * Añade un parámetro de tipo fecha.
     * La fecha es añadida en format AAAAMMDD
     * 
     * @param v                                 Parámetro
     */
    public void add(CFecha v) {

        add(v.getFecha());
    }

    
    /**
     * Añade un parámetro de tipo hora.
     * La hora es añadida en formato HHMM
     *
     * @param v                                 Parámetro
     */
    public void add(CHora v) {

        add(v.getHora(CHora.FH_HHMM));
    }

    
    /**
     * Añade un parámetro de tipo decimal.
     *
     * @param v                                 Parámetro
     */
    public void add(CDecimal v) {

        add(v.getValor());
    }
    
    
    /**
     * Añade un parámetro de tipo Blob.
     *
     * @param is                                InputStream con el contenido del blob
     * @param long_blob                         Longitud del blob (número de bytes)
     */
    public void add(InputStream is, long long_blob) {
        ClaseParametro param = new ClaseParametro();
        param.tipo = TP_BLOB;
        param.valor = is;
        param.long_blob = long_blob;
        
        l_parametros.add(param);
    }

    
    /**
     * Añade un parámetro de tipo byte [].
     *
     * @param v                                 Parámetro
     */
    public void add(byte [] v) {
        ClaseParametro param = new ClaseParametro();
        param.tipo = TP_BYTES;
        param.valor = v;
        l_parametros.add(param);
    }
    
    
    /**
     * Retorna el número  de parámetros que hay almacenados.
     *
     * @return                                  Número de parámetros
     */
    public int size() {
        return l_parametros.size();
    }
    
    
    /**
     * Obtiene el parámetro de la lista cuyo índice se suministra.
     *
     * @param indice                            Índice del parámetro a recuperar
     * @return                                  Parámetro (objeto de ClaseParametro)
     */
    public ClaseParametro get(int indice) {
        ClaseParametro param = l_parametros.get(indice);
        return param;
    }
    
    
    /**
     * Obtiene la representación en forma de cadena del parámetro de la lista cuyo índice se suministra.
     *
     * @param indice                            Índice del parámetro a recuperar
     * @return                                  Parámetro en un String
     */
    public String getAsString(int indice) {
        ClaseParametro param = l_parametros.get(indice);
        
        switch (param.tipo) {
            case Parametros.TP_INT:
                int int_valor = (Integer) param.valor;
                return Integer.toString(int_valor);

            case Parametros.TP_LONG:
                long long_valor = (Long) param.valor;
                return Long.toString(long_valor);

            case Parametros.TP_DOUBLE:
                double double_valor = (Double) param.valor;
                return Double.toString(double_valor);
                
            case Parametros.TP_BOOLEAN:
                boolean boolean_valor = (Boolean) param.valor;
                if (boolean_valor) return "1";
                else return "0";

            case Parametros.TP_STRING:
                String string_valor = (String) param.valor;
                return string_valor;

            case Parametros.TP_BLOB:
                String blob = "<blob " + Long.toString(param.long_blob) + " bytes>";
                return blob;
                
            case Parametros.TP_BYTES:
                String bytes = "<byte [" + ((byte []) param.valor).length + "]>";
                return bytes;

        }
                
        return "";
    }
    
    
    /**
     * Obtiene un String [] con los distintos parámetros.
     * 
     * @return                                  String [] con los parámetros
     */
    public String [] getStrings() {
        int n = size();
        String [] result = new String[n];
        
        for (int i = 0; i < n; i++) {
            result[i] = getAsString(i);
        }
        
        return result;
    }
    
    
    /**
     * Obtiene la cadena de "?" separados por comas y entre paréntesis, para la sustitución de los parámetros
     * en las sentencias INSERT.
     * Habrá un "?" por cada parámetro.
     *
     * @return                                  Cadena de "?", por ejemplo "(?, ?, ?)"
     */
    public String getCadenaValues() {
        StringBuilder s = new StringBuilder("");
        s.append("(");
        
        for (int i = 0; i < size(); i++) {
            s.append("?");
            if (i < size() - 1) s.append(", ");
        }
        
        s.append(")");
        return s.toString();
    }
    
    
    /**
     * Obtiene la cadena de valores, ya sustituidos, separados por comas y entre paréntesis, para su uso en las sentencias INSERT.
     *
     * @return                                  Cadena de "?", por ejemplo "(?, ?, ?)"
     */
    public String getCadenaValuesSustituidos() {
        StringBuilder s = new StringBuilder("");
        s.append("(");
        
        for (int i = 0; i < size(); i++) {
            s.append("'" + getAsString(i) + "'");
            if (i < size() - 1) s.append(", ");
        }
        
        s.append(")");
        return s.toString();
    }

    
    /**
     * Genera una cadena con la información de la instancia.
     * 
     * @return                                  Cadena generada
     */
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        
        String NL = Comun.NL;
        
        for (int i = 0; i < Arrays.size(l_parametros); i++) {
            ClaseParametro param = l_parametros.get(i);
            s.append("Parámetro " + i + ": " + getAsString(i) + NL);
        }
        
        return s.toString();
    }
    
}
