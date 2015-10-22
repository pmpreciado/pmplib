/**
 * CDecimal.java
 *
 * Creado el 22-oct-2015, 10:21:42
 */

package es.pmp.pmplib.tipos.general;

import es.pmp.pmplib.Cadenas;
import es.pmp.pmplib.Numeros;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Clase para almacenar y gestionar números en decimal.
 *
 * @author pmpreciado
 */

/**
 * Clase para almacenar y gestionar un tipo numérico decimal.
 *
 * 
 */
public class CDecimal implements Cloneable, Comparable <CDecimal>  {

    /** Numeros menores de 30 representados como una cadena */
	private static final String [] MENORES_30 = { 
        "CERO",         "UNO",          "DOS",          "TRES",         "CUATRO",   
        "CINCO",        "SEIS",         "SIETE",        "OCHO",         "NUEVE",
        "DIEZ",         "ONCE",         "DOCE",         "TRECE",        "CATORCE",  
        "QUINCE",       "DIECISEIS",    "DIECISIETE",   "DIECIOCHO",    "DIECINUEVE",
        "VEINTE",       "VEINTIUNO",    "VEINTIDOS",    "VEINTITRES",   "VEINTICUATRO",
        "VEINTICINCO",  "VEINTISEIS",   "VEINTISIETE",  "VEINTIOCHO",   "VEINTINUEVE"
    };

    private static final String [] DECENAS = {
        "DIEZ", "VEINTE", "TREINTA", "CUARENTA", "CINCUENTA", "SESENTA", "SETENTA", "OCHENTA", "NOVENTA" 
    };
    
    private static final String [] CENTENAS = {
        "CIEN", "DOSCIENTOS","TRESCIENTOS", "CUATROCIENTOS", "QUINIENTOS", "SEISCIENTOS", "SETECIENTOS", "OCHOCIENTOS", "NOVECIENTOS" 
    };
        
    private static final String MIL = "MIL";
    private static final String [] MILLON  = { "UN MILLON", "MILLONES" };
    private static final String [] BILLON  = { "UN BILLON", "BILLONES" };

    private static final String MENOS = "MENOS";
    private static final String CON = "CON";
    
    
    /** Número de decimales predeterminado */
    protected static int NUM_DECIMALES = 2;

    /** Importe mínimo y máximo */
    public static final String IMPORTE_MIN              = "-9999999999";
    public static final String IMPORTE_MAX              = "9999999999";
    

    /** Flag que indica si el número sido definido */
    protected boolean valor_definido;

    /** Representación normalizada del número. Aúna la parte entera y la decimal */
    protected long mantisa;

    /** Decimales a usar para el almacenamiento interno */
    protected int num_decimales;

    /** Signo */
    protected boolean signo;

    /** Decimales a usar para la representación */
    protected int num_decimales_scr;

    /** Flag que indica si el número de decimales ha sido definido */
    protected boolean num_decimales_scr_definidos;


    /** Descripción del error producido al definir el número */
    String desc_error;


    /**
     * Retorna una instancia de esta clase, sin definir el número.
     * 
     * @return                                  Instancia creada
     */
    public static CDecimal noDefinido() {
        return new CDecimal();
    }


    /**
     * Crea una instancia de la clase, sin definir el valor.
     * El valor se puede establecer posteriormente mediante setValor().
     */
    public CDecimal() {
        init();
    }


    /**
     * Retorna una instancia de esta clase, definiendo el valor a partir de una cadena.
     *
     * @param valor                         Valor inicial
     */
    public CDecimal(String valor) {
        init();
        setValor(valor);
    }


    /**
     * Retorna una instancia de esta clase, definiendo el valor a partir de una entero.
     * Se utiliza el número de decimales predeterminado.
     *
     * @param valor                         Valor inicial
     */
    public CDecimal(int valor) {
        this(valor, 2);
    }


    /**
     * Retorna una instancia de esta clase, definiendo el valor a partir de un double.
     * Se utiliza el número de decimales predeterminado, a menos que el valor suministrado tenga más
     * decimales, en cuyo caso, se utiliza el del valor suministrado.
     *
     * @param valor                         Valor inicial
     */
    public CDecimal(double valor) {
        init();
        setValor(valor);
    }


    /**
     * Retorna una instancia de esta clase, definiendo el valor a partir de un BigDecimal.
     * Se utiliza el número de decimales predeterminado, a menos que el valor suministrado tenga más
     * decimales, en cuyo caso, se utiliza el del valor suministrado.
     *
     * @param valor                         Valor inicial
     */
    public CDecimal(BigDecimal valor) {
        init();
        setValor(valor);
    }


    /**
     * Retorna una instancia de esta clase, inicializándola con un decimal ya existente.
     *
     * @param valor                         Valor inicial
     */
    public CDecimal(CDecimal valor) {
        init();
        setValor(valor);
        setNumDecimales(valor.getNumDecimales());
    }


    /**
     * Retorna una instancia de esta clase, definiendo el valor a partir de una cadena.
     * Se utiliza el número de decimales predeterminado, a menos que el valor suministrado tenga más
     * decimales, en cuyo caso, se utiliza el del valor suministrado.
     *
     * @param valor                         Valor inicial
     * @param num_decimales                 Número de decimales
     */
    public CDecimal(String valor, int num_decimales) {
        init();
        setValor(valor);
        setNumDecimales(num_decimales);
    }


    /**
     * Retorna una instancia de esta clase, definiendo el valor a partir de una cadena.
     *
     * @param valor                         Valor inicial
     * @param num_decimales                 Número de decimales
     */
    public CDecimal(int valor, int num_decimales) {
        init();
        setValor(valor);
        setNumDecimales(num_decimales);
    }


    /**
     * Inicializa la clase.
     */
    protected void init() {
        num_decimales_scr = NUM_DECIMALES;
        valor_definido = false;

        mantisa = 0;
        num_decimales = 0;
        signo = false;
        num_decimales_scr_definidos = false;
    }


    /**
     * Obtiene un número decimal que es una copia exacta de la instancia actual.
     *
     * @return                              Objeto de CDecimal con la copia
     */
    @Override
    public CDecimal clone() {

        CDecimal c = new CDecimal();
        c.valor_definido = this.valor_definido;
        c.mantisa = this.mantisa;
        c.num_decimales = this.num_decimales;
        c.signo = this.signo;
        c.num_decimales_scr = this.num_decimales_scr;
        c.desc_error = this.desc_error;
        c.num_decimales_scr_definidos = this.num_decimales_scr_definidos;

        return c;
    }

    

    /**
     * Formatea un importe en formato seeeee.dd (s = signo opcional, e = parte entera, d = parte decimal)
     * para su presentación en pantalla, usando separador de miles y coma decimal.
     *
     * @param importe                   Importe en formato seeeee.dd
     * @param decimales                 Número de decimales a utilizar
     * @return                          String con el importe en formato seee.eee,dd
     */
    public static String formatearImporte(String importe, int decimales) {

        importe = Numeros.redondearNDecimales(importe, decimales);


        // Desmenuzamos el importe en: signo + parte_entera + parte_decimal
        String signo = Cadenas.miSubstringLong(importe, 0, 1);
        if (signo.equals("-")) {
            importe = importe.substring(1);
        } else {
            signo = "";
        }

        String parte_entera = "";
        String parte_decimal = "";

        int punto = importe.indexOf(",");
        if (punto == -1) punto = importe.indexOf(".");


        if (punto == -1) {
            // No hay decimales en el número de entrada
            parte_entera = importe;
        } else {
            // Hay decimales en el número de entrada
            parte_entera = importe.substring(0, punto);
            parte_decimal = importe.substring(punto + 1, importe.length());
        }

        // Si hay más decimales de la cuenta, los redondeamos
        if (parte_decimal.length() > decimales) {
            parte_decimal = Numeros.redondearEntero(parte_decimal, decimales);
        }

        // Añadimos tantos ceros a los decimales como se requiera
        while (parte_decimal.length() < decimales) parte_decimal = parte_decimal + "0";

        // Separamos por puntos la parte entera
        String parte_entera_sep = "";
        for (int i = 0; i < parte_entera.length(); i++) {
            String c = Cadenas.miSubstringLong(parte_entera, parte_entera.length() - i - 1, 1);
            if (i > 0 && i % 3 == 0) parte_entera_sep = "." + parte_entera_sep;
            parte_entera_sep = c + parte_entera_sep;
        }

        // Componemos y retornamos el número formateado
        String nuevo_importe;
        if (decimales > 0) nuevo_importe = signo + parte_entera_sep + "," + parte_decimal;
        else nuevo_importe = signo + parte_entera_sep;

        return nuevo_importe;
    }


    /**
     * Formatea un importe en formato seeeee.dd (s = signo opcional, e = parte entera, d = parte decimal)
     * para su presentación en pantalla, usando separador de miles y coma decimal.
     *
     * @param importe                   Importe
     * @param decimales                 Número de decimales a utilizar
     * @return                          String con el importe en formato seee.eee,dd
     */
    public static String formatearImporte(BigDecimal importe, int decimales) {
        String s_importe = importe.toString();
        return formatearImporte(s_importe, decimales);
    }

    

    /**
     * Permite comparar este número con otro.
     *
     * @param d2                        Número con el que comparar
     *
     * @return                          +1, si this > d2
     *                                   0, si this == d2
     *                                  -1, si this < d2
     */
        @Override
    public int compareTo(CDecimal d2) {
        if (this.esMayor(d2)) return +1;
        if (this.esMenor(d2)) return -1;
        return 0;
    }


    /**
     * Establece el número de decimales a utilizar para mostrar el número.
     * Si como resultado quedaran decimales ocultos, el número mostrado será redondeado.
     * Esta función sólo afecta a la representación, no afectando al número guardado.
     *
     * @param decimales                     Número de decimales a usar
     */
    public void setNumDecimales(int decimales) {
        this.num_decimales_scr = decimales;
        if (this.num_decimales < decimales) {
            while (this.num_decimales < decimales) {
                mantisa *= 10;
                this.num_decimales++;
            }
            while (this.num_decimales > decimales) {
                mantisa /= 10;
                this.num_decimales--;
            }

            this.num_decimales = decimales;
        }

        num_decimales_scr_definidos = true;
    }


    /**
     * Obtiene el número de decimales que se están utilizando para mostrar el número.
     *
     * @return                              Número de decimales
     */
    public int getNumDecimales() {
        return num_decimales_scr;
    }


    /**
     * Trunca sin redondear los dígitos decimales del número.
     * Ejemplo:
     *    12.286  -> trunc 1 -> 12.200
     *
     * Esta función no altera el número de decimales a mostrar
     *
     * @param d                             Número de decimales a usar, truncando el resto
     */
    public void truncParteDecimal(int d) {
        if (d < num_decimales) {
            int dif = num_decimales - d;
            for (int i = 0; i < dif; i++) {
                mantisa = mantisa / 10;
            }
        }

        else if (d > num_decimales) {
            int dif = d - num_decimales;

            for (int i = 0; i < dif; i++) {
                mantisa = mantisa * 10;
            }
        }

        num_decimales = d;
    }

    
    /**
     * Establece el número decimal a partir de una cadena.
     *
     * @param valor                         Nuevo número, con punto decimal y sin separador de miles
     */
    public void setValor(String valor) {
        
        if (valor == null) {
            init();
            return;
        }
        
        valor = valor.trim();

        boolean numero_valido = esNumeroValido(valor);
        if (!numero_valido) {
            desc_error = "El número '" + valor + "' es incorrecto";
            return;
        }

        // ¿Número expresado en forma exponencial? (Por ejemplo, 0E-8)
        // Si es así, lo pasamos a notación normal
        if (valor.contains("E")) {
            valor = Double.toString(Double.parseDouble(valor));
        }
        
        // Obtenemos el signo del valor
        String s = Cadenas.primeros(valor, 1);
        if (s.equals("-")) {
            signo = true;
        }

        // Eliminamos el posible signo "+" al principio del valor
        if (s.equals("+") || s.equals("-")) {
            valor = Cadenas.eliminarPrimeros(valor, 1);
        }

        // Establecemos el exponente del número
        if (!valor.contains(".")) {
            num_decimales = 0;
        } else {
            num_decimales = valor.length() - valor.indexOf(".") - 1;
        }

        // Eliminamos el punto decimal
        String s_mantisa = Cadenas.replaceAll(valor, ".", "");
        
        // Si el número está expresado en notación científica, por ejemplo: 1.2493902458916666E7, la mantisa será 12493902
        if (s_mantisa.contains("E")) {
            int p = s_mantisa.indexOf("E");
            String s_exp = Cadenas.eliminarPrimeros(s_mantisa, p + 1);
            int exp = Cadenas.toEntero(s_exp);
            s_mantisa = Cadenas.primeros(s_mantisa, exp + 1);
        }       

        /*
        int lm = s_mantisa.length();
        if (lm > 18) {
            s_mantisa = Comun.primeros(s_mantisa, 18);
            int dif = lm - 18;
            num_decimales = num_decimales - dif;
        }*/

        if (!num_decimales_scr_definidos) {
            num_decimales_scr = num_decimales;
        }

        mantisa = Long.parseLong(s_mantisa);

        valor_definido = true;

    }


    /**
     * Establece el número decimal a partir de una cadena formateada.
     *
     * @param valor                         Nuevo número, con coma decimal y posible separador de miles
     */
    public void setValorScr(String valor) {
        String valor_unformat = unformat(valor);
        setValor(valor_unformat);
    }


    /**
     * Establece el número decimal a partir de un entero.
     *
     * @param valor                         Nuevo número
     */
    public void setValor(int valor) {

        String s_valor = Integer.toString(valor);
        setValor(s_valor);
    }


    /**
     * Establece el número decimal a partir de un double.
     *
     * @param valor                         Nuevo número
     */
    public void setValor(double valor) {
        String s_valor = Double.toString(valor);
        try {
            setValor(s_valor);
        } catch (NumberFormatException nfex) {
            setValor(s_valor);
        }
    }


    /**
     * Establece el número decimal a partir de un BigDecimal.
     *
     * @param valor                         Nuevo número
     */
    public void setValor(BigDecimal valor) {
        String s_valor = valor.toPlainString();
        int pos_punto = s_valor.indexOf(".");
        
        // Las representaciones de BigDecimal pueden dar números del tipo "138.4100000000000000". Eliminamos los 0 sobrantes tras el punto decimal
        if (pos_punto >= 0) {
            while (s_valor.charAt(s_valor.length() - 1) == '0' && s_valor.length() > pos_punto + 2) {
                s_valor = Cadenas.eliminarUltimos(s_valor, 1);
            }
        }
        
        setValor(s_valor);
    }


    /**
     * Establece el número decimal, a partir de un objeto de tipo decimal.
     *
     * @param valor
     */
    public void setValor(CDecimal valor) {
        if (valor == null) return;
        setValor(valor.getValor());
    }


    /**
     * Establece el número decimal, especificando de forma separada la parte entera y la decimal.
     *
     * @param parte_entera                  Parte entera
     * @param parte_decimal                 Parte decimal
     */
    public void setValorED(String parte_entera, String parte_decimal) {
        String valor = parte_entera;
        if (parte_decimal.length() > 0) valor += "." + parte_decimal;
        setValor(valor);
    }


    /**
     * Establece el número decimal, especificando de forma separada la parte entera y la decimal.
     *
     * @param parte_entera                  Parte entera
     * @param parte_decimal                 Parte decimal
     */
    public void setValorED(int parte_entera, int parte_decimal) {
        setValorED(Integer.toString(parte_entera), Integer.toString(parte_decimal));
    }


    /**
     * Comprueba que el número suministrado en un String sea correcto.
     *
     * @param valor                         Número a comprobar, con punto decimal y sin separador de miles
     * @return                              'true' si el número es correcto
     *                                      'false' en caso contrario
     */
    public static boolean esNumeroValido(String valor) {

        try {
            Double.parseDouble(valor);
        } catch (NumberFormatException ex) {
            return false;
        }

        return true;
    }


    /**
     * Trata de averiguar si el número suministrado está formateado o no.
     * Se considera que está formateado si se expresa con coma decimal y separador de miles.
     * Si el valor de entrada no contiene un número correcto, el resultado puede ser impredecible.
     *
     * @param valor                         Número a comprobar
     * @return                              0, si no se puede determinar
     *                                      1, si el número está formateado
     *                                      -1, si no lo está
     */
    public static int estaFormateado(String valor) {

        // Si hay coma decimal, es que el número está formateado
        int p = valor.indexOf(',');
        if (p >= 0) return 1;

        // No hay coma decimal, pero puede ser que el número no tenga decimales
        // Comprobamos si hay separador de miles
        int p1 = valor.indexOf(".");
        int p2 = valor.lastIndexOf(".");

        // Hay dos puntos en el número, por lo que deben ser dos separadores de miles, es decir, que el número está formateado
        if (p1 >= 0 && p2 >= 0 && p1 != p2) return 1;

        // Hay un sólo punto en el número. Varias posibilidades
        // Si, y sólo si, tras el punto hay tres dígitos, habría la duda de si el punto es un separador de miles o de decimales:
        //    1.456
        //    123.456
        //    0.123   (Si a la izquierda del punto hay un 0, no hay duda)
        //
        // Si tras el punto hay un número de dígitos distinto de tres, se considera que es separador decimal
        if (p1 >= 0) {
            String valor_tras_punto = valor.substring(p1, valor.length());
            if (valor_tras_punto.length() != 3) return -1;

            // Duda de si el número está formateado o no
            if (valor.charAt(0) == '0') return -1;      // El primer dígito es un 0, por lo que el punto no era un separador de miles

            // Imposible determinar si el número está formateado o no
            return 0;
        }

        // El número no contiene ni separadores decimales ni de miles, por lo que no está formateado
        return -1;
    }


    /**
     * Transforma un número, expresado con coma decimal y posible separador de miles, al
     * formato de punto decimal y sin separador.
     *
     * @param valor                         Número a transformar, con coma decimal y posible separador de miles
     * @return                              Número con punto decimal y sin separador de miles
     */
    public static String unformat(String valor) {
        if (valor == null) return null;
        valor = valor.replaceAll("\\.", "");
        valor = valor.replace(",", ".");
        return valor;

    }


    /**
     * Retorna el estado de si el número está definido o no.
     *
     * @return                              'true' si el número ha sido definido
     *                                      'false' si no
     */
    public boolean definido() {
        return valor_definido;
    }


    /**
     * Comprueba que el número esté definido y tenga un valor distinto de 0.
     *
     * @return                              'true' si el número ha sido definido y tiene un valor distinto de 0
     *                                      'false' si no
     */
    public boolean definidoYNoCero() {
        return definido() && !esCero();
    }
    
    
    /**
     * Retorna el número sin formato.
     * Si el número no está definido, retorna "0.00".
     *
     * @return                              String con el número
     */
    public String getValor() {
        if (!definido()) {
            String s_valor = "0";
            if (num_decimales_scr > 0) {
                s_valor += ".";
                for (int i = 0; i < num_decimales_scr; i++) {
                    s_valor += "0";
                }
            }
            return s_valor;
        }

        String s_parte_entera = getParteEntera();
        String s_parte_decimal = getParteDecimal();
        String s_signo = signo ? "-" : "";

        String s_valor = s_signo + s_parte_entera + "." + s_parte_decimal;
        s_valor = Numeros.redondearNDecimales(s_valor, num_decimales_scr);

        // Justificamos la parte decimal con 0 a la derecha
        int pos = s_valor.indexOf(".");
        int nd = s_valor.length() - pos;
        for (int i = 0; i < num_decimales_scr - nd; i++) {
            s_valor += "0";
        }

        return s_valor;
    }


    /**
     * Retorna el número sin formato.
     * Si el número no está definido, retorna "0".
     *
     * @return                              String con el número
     */
    public String getValorOCero() {
        if (!definido()) return "0";
        return getValor();
    }


    /**
     * Retorna el número sin formato.
     * Si el número no está definido, retorna "".
     *
     * @return                              String con el número
     */
    public String getValorOBlanco() {
        if (!definido()) return "";
        return getValor();
    }


    /**
     * Retorna el número sin formato.
     * Si el número no está definido, retorna el valor mínimo (Comun.IMPORTE_MIN).
     *
     * @return                              String con el número
     */
    public String getValorOMin() {
        if (!definido()) return IMPORTE_MIN;
        return getValor();
    }


    /**
     * Retorna el importe dado sin formato.
     * Si el importe dado es 'null' o no está definido, retorna el valor mínimo (Comun.IMPORTE_MIN).
     *
     * @param importe                       Importe original
     * 
     * @return                              String con el número
     */
    public static String getValorOMin(CDecimal importe) {
        if (importe == null || !importe.definido()) return IMPORTE_MIN;
        return importe.getValor();
    }


    /**
     * Retorna el número sin formato.
     * Si el número no está definido, retorna el valor máximo (Comun.IMPORTE_MAX).
     *
     * @return                              String con el número
     */
    public String getValorOMax() {
        if (!definido()) return IMPORTE_MAX;
        return getValor();
    }


    /**
     * Retorna el importe dado sin formato.
     * Si el importe dado es 'null' o no está definido, retorna el valor máximo (Comun.IMPORTE_MAX).
     *
     * @param importe                       Importe original
     * 
     * @return                              String con el número
     */
    public static String getValorOMax(CDecimal importe) {
        if (importe == null || !importe.definido()) return IMPORTE_MAX;
        return importe.getValor();
    }


    /**
     * Retorna el número como si fuera un double.
     *
     * @return                              String con el número
     */
    public double toDouble() {
        String valor = getValor();
        double d = Double.parseDouble(valor);
        return d;
    }


    /**
     * Retorna el número como si fuera un BigDecimal.
     *
     * @return                              String con el número
     */
    public BigDecimal toBigDecimal() {
        String valor = getValor();
        BigDecimal bd = new BigDecimal(valor);
        return bd;
    }


    /**
     * Retorna el número redondeado hacia el entero más próximo.
     *
     * @return                              Entero más próximo
     */
    public int toInt() {
        double d = toDouble();
        float f = Math.round(d);
        int n = Math.round(f);
        return n;
    }


    /**
     * Obtiene la parte entera del número.
     * Si el número no está definido, retorna 0.
     *
     * @return                              Parte entera del número
     */
    public String getParteEntera() {
        if (!definido()) return "0";

        String s_mantisa = Long.toString(mantisa);
        String s_parte_entera = Cadenas.primeros(s_mantisa, s_mantisa.length() - num_decimales);
        if (s_parte_entera.length() == 0) s_parte_entera = "0";
        return s_parte_entera;
    }


    /**
     * Obtiene un entero la parte entera del número.
     * Si el número no está definido, retorna 0.
     *
     * @return                              Parte entera del número
     */
    public int getParteEnteraInt() {
        String e = getParteEntera();
        int ne = Integer.parseInt(e);
        return ne;
    }


    /**
     * Obtiene la parte decimal del número. No se justifica la parte decimal con 0 a la derecha.
     * Si el número no está definido, retorna 0.
     *
     * @return                              Parte decimal del número
     */
    public String getParteDecimal() {
        if (!definido()) return "0";

        String s_mantisa = Long.toString(mantisa);
        while (s_mantisa.length() < num_decimales) s_mantisa = "0" + s_mantisa;
        String s_parte_decimal = Cadenas.ultimos(s_mantisa, num_decimales);
        return s_parte_decimal;
    }


    /**
     * Obtiene un entero la parte decimal del número. No se justifica la parte decimal con 0 a la derecha.
     * Si el número no está definido, retorna 0.
     *
     * @return                              Parte decimal del número
     */
    public int getParteDecimalInt() {
        String d = getParteDecimal();
        int nd = Integer.parseInt(d);
        return nd;
    }


    /**
     * Invierte el signo del número actual.
     */
    public void invertir() {
        signo = !signo;
    }


    /**
     * Retorna un nuevo CDecimal cuyo valor es el negativo al del número actual.
     *
     * @return                              Nuevo número de CDecimal con signo inverso
     */
    public CDecimal inverso() {
        CDecimal inverso = new CDecimal(this);
        inverso.invertir();
        return inverso;
    }


    /**
     * Retorna un nuevo CDecimal cuyo valor es el resultado de sumar el número suministrado al actual.
     *
     * @param numero_2                      Número a sumar al actual
     * @return                              Nuevo número de CDecimal con la suma
     */
    public CDecimal add(CDecimal numero_2) {
        BigDecimal bd1 = this.toBigDecimal();
        BigDecimal bd2 = numero_2.toBigDecimal();
        BigDecimal bd_suma = bd1.add(bd2);

        CDecimal suma = new CDecimal(bd_suma);

        int max_dc = Numeros.mayor(this.getNumDecimales(), numero_2.getNumDecimales(), NUM_DECIMALES);
        if (suma.getNumDecimales() > max_dc) {
            suma.setNumDecimales(max_dc);
        } else {
            suma.setNumDecimales(this.getNumDecimales());
        }

        return suma;
    }


    /**
     * Retorna un nuevo CDecimal cuyo valor es el resultado de sumar el número suministrado al actual.
     *
     * @param numero_2                      Número a sumar al actual
     * @return                              Nuevo número de CDecimal con la suma
     */
    public CDecimal add(int numero_2) {
        CDecimal d_numero_2 = new CDecimal(numero_2);
        CDecimal suma = add(d_numero_2);
        return suma;
    }


    /**
     * Retorna un nuevo CDecimal cuyo valor es el resultado de restar el número suministrado al actual.
     *
     * @param numero_2                      Número a restar al actual
     * @return                              Nuevo número de CDecimal con la resta
     */
    public CDecimal sub(CDecimal numero_2) {
        BigDecimal bd1 = this.toBigDecimal();
        BigDecimal bd2 = numero_2.toBigDecimal();
        BigDecimal bd_resta = bd1.subtract(bd2);

        CDecimal resta = new CDecimal(bd_resta);

        int max_dc = Numeros.mayor(this.getNumDecimales(), numero_2.getNumDecimales(), NUM_DECIMALES);
        if (resta.getNumDecimales() > max_dc) {
            resta.setNumDecimales(max_dc);
        } else {
            resta.setNumDecimales(this.getNumDecimales());
        }

        return resta;
    }


    /**
     * Retorna un nuevo CDecimal cuyo valor es el resultado de restar el número suministrado al actual.
     *
     * @param numero_2                      Número a sumar al actual
     * @return                              Nuevo número de CDecimal con la suma
     */
    public CDecimal sub(int numero_2) {
        CDecimal d_numero_2 = new CDecimal(numero_2);
        CDecimal resta = sub(d_numero_2);
        return resta;
    }


    /**
     * Retorna un nuevo CDecimal cuyo valor es el resultado de multiplicar el número suministrado al actual.
     *
     * @param numero_2                      Número a multiplicar al actual
     * @return                              Nuevo número de CDecimal con la multiplicación
     */
    public CDecimal mul(CDecimal numero_2) {
        BigDecimal bd1 = this.toBigDecimal();
        BigDecimal bd2 = numero_2.toBigDecimal();
        BigDecimal bd_mul = bd1.multiply(bd2);

        CDecimal mul = new CDecimal(bd_mul);

        int max_dc = Numeros.mayor(this.getNumDecimales(), numero_2.getNumDecimales(), NUM_DECIMALES);
        if (mul.getNumDecimales() > max_dc) {
            mul.setNumDecimales(max_dc);
        } else {
            mul.setNumDecimales(this.getNumDecimales());
        }

        return mul;
    }


    /**
     * Retorna un nuevo CDecimal cuyo valor es el resultado de multiplicar el número suministrado al actual.
     *
     * @param numero_2                      Número a multiplicar al actual
     * @return                              Nuevo número de CDecimal con la multiplicación
     */
    public CDecimal mul(int numero_2) {

        CDecimal d_numero_2 = new CDecimal(numero_2);
        return mul(d_numero_2);
    }



    /**
     * Retorna un nuevo CDecimal cuyo valor es el resultado de dividir el número actual entre el suministrado.
     *
     * @param divisor                       Divisor
     * @return                              Nuevo número de CDecimal con el resultado de la división
     */
    public CDecimal div(CDecimal divisor) {

        CDecimal div;
        try {
            BigDecimal bd1 = this.toBigDecimal();
            BigDecimal bd_divisor = divisor.toBigDecimal();
            BigDecimal bd_cociente = bd1.divide(bd_divisor, 8, RoundingMode.HALF_UP);
            div = new CDecimal(bd_cociente);
        } catch (ArithmeticException aex) {
            double d1 = this.toDouble();
            double d_divisor = divisor.toDouble();
            double d_cociente = d1 / d_divisor;
            div = new CDecimal(d_cociente);
        }

        int max_dc = Numeros.mayor(this.getNumDecimales(), divisor.getNumDecimales(), NUM_DECIMALES);
        if (div.getNumDecimales() > max_dc) {
            div.setNumDecimales(max_dc);
        } else {
            div.setNumDecimales(this.getNumDecimales());
        }

        return div;
    }


    /**
     * Retorna un nuevo CDecimal cuyo valor es el resultado de dividir el número actual entre el suministrado.
     *
     * @param divisor                       Divisor
     * @return                              Nuevo número de CDecimal con el resultado de la división
     */
    public CDecimal div(int divisor) {
        CDecimal d_divisor = new CDecimal(divisor);
        return div(d_divisor);
    }


    /**
     * Retorna un nuevo CDecimal cuyo valor es el valor absoluto del actual.
     *
     * @return                              Nuevo número de CDecimal con el valor absoluto
     */
    public CDecimal abs() {
        CDecimal num2 = this.clone();
        num2.signo = false;
        return num2;
    }


    /**
     * Comprueba si el número es mayor al suministrado.
     *
     * @param numero_2                      Número a comparar
     * @return                              'true' si el número actual es mayor a numero_2
     *                                      'false' si es menor o igual
     */
    public boolean esMayor(CDecimal numero_2) {
        BigDecimal bd_1 = toBigDecimal();
        BigDecimal bd_2 = numero_2.toBigDecimal();

        if (bd_1.compareTo(bd_2) == 1) return true;
        return false;
    }


    /**
     * Comprueba si el número es mayor o igual al suministrado.
     *
     * @param numero_2                      Número a comparar
     * @return                              'true' si el número actual es mayor o igual a numero_2
     *                                      'false' si es menor o igual
     */
    public boolean esMayorOIgual(CDecimal numero_2) {
        BigDecimal bd_1 = toBigDecimal();
        BigDecimal bd_2 = numero_2.toBigDecimal();

        if (bd_1.compareTo(bd_2) == 0) return true;
        if (bd_1.compareTo(bd_2) == 1) return true;
        return false;
    }


    /**
     * Comprueba si el número es menor al suministrado.
     *
     * @param numero_2                      Número a comparar
     * @return                              'true' si el número actual es menor a numero_2
     *                                      'false' si es mayor o igual
     */
    public boolean esMenor(CDecimal numero_2) {
        BigDecimal bd_1 = toBigDecimal();
        BigDecimal bd_2 = numero_2.toBigDecimal();

        if (bd_1.compareTo(bd_2) == -1) return true;
        return false;
    }


    /**
     * Comprueba si el número es menor o igual al suministrado.
     *
     * @param numero_2                      Número a comparar
     * @return                              'true' si el número actual es menor o igual a numero_2
     *                                      'false' si es mayor o igual
     */
    public boolean esMenorOIgual(CDecimal numero_2) {
        BigDecimal bd_1 = toBigDecimal();
        BigDecimal bd_2 = numero_2.toBigDecimal();

        if (bd_1.compareTo(bd_2) == 0) return true;
        if (bd_1.compareTo(bd_2) == -1) return true;
        return false;
    }


    /**
     * Comprueba si el número es igual al suministrado.
     *
     * @param numero_2                      Número a comparar
     * @return                              'true' si el número actual es igual a numero_2
     *                                      'false' si es distinto
     */
    public boolean esIgual(CDecimal numero_2) {
        BigDecimal bd_1 = toBigDecimal();
        BigDecimal bd_2 = numero_2.toBigDecimal();

        if (bd_1.compareTo(bd_2) == 0) return true;
        return false;
    }


    /**
     * Comprueba si el número es igual al suministrado, al menos hasta el decimal especificado.
     *
     * @param numero_2                      Número a comparar
     * @param decimales                     Número de decimales a considerar (los números son redondeados para la comparación)
     * @return                              'true' si el número actual es igual a numero_2
     *                                      'false' si es distinto
     */
    public boolean esIgual(CDecimal numero_2, int decimales) {
        BigDecimal bd_1 = toBigDecimal();
        BigDecimal bd_2 = numero_2.toBigDecimal();

        bd_1 = bd_1.setScale(decimales, BigDecimal.ROUND_HALF_EVEN);
        bd_2 = bd_2.setScale(decimales, BigDecimal.ROUND_HALF_EVEN);

        if (bd_1.compareTo(bd_2) == 0) return true;
        return false;
    }


    /**
     * Comprueba si el número es 0.
     *
     * @return                              'true' si el número es cero
     *                                      'false' si no
     */
    public boolean esCero() {
        if (mantisa == 0) return true;
        return false;
    }


    /**
     * Comprueba si el número es positivo.
     *
     * @return                              'true', si el número es 0 o positivo
     *                                      'false', si el número es negativo
     */
    public boolean esPositivo() {
        return !esNegativo();
    }


    /**
     * Comprueba si el número es negativo.
     *
     * @return                              'true', si el número es negativo
     *                                      'false', si el número es 0 o positivo
     */
    public boolean esNegativo() {
        return signo;
    }

    
    /**
     * Obtiene el signo del número, expresado como un entero.
     * 
     * @return                              +1 Si el signo es positivo
     *                                       0 Si el número es 0
     *                                      -1 Si el signo es negativo
     */
    public int getSignoInt() {
        if (this.esCero()) {
            return 0;
        }
        
        if (this.esPositivo()) {
            return 1;
        }
        
        return -1;
    }
    
    

    /**
     * Obtiene la descripción del último error, si la hubiera.
     *
     * @return                              Descripción del último error ('null' si no hay)
     */
    public String getDescError() {
        return desc_error;
    }


    /** 
     * Pasa un numero sin decimales a la cadena de texto que lo representa.
     * El número puede tener como mucho 19 digitos. En caso contrario se 
     * genera un error. Esto quiere decir que puede llegar a transformar
     * del orden de billones.
     * 
     * Función tomada de la clase Importe.java
     *
     * @param num                           Número del que se quiere obtener el literal
     * 
     * @return                              Cadena que represeta el número leido
     */
    public static String getLiteral(String num) {
        
        // Si el numero es 0, retorno 0
        if(num.equals("0")) return MENORES_30[0];
                
        long n = Long.parseLong(num);
        // si en este punto el numero es 0, return ""
        if(n == 0L) return "";
        
        // Debe indicar que es un numero negativo
        if (num.charAt(0) == '-') {
            return MENOS + " " + getLiteral(num.substring(1));
        }
        
        // A partir de aqui verifica todos los casos
        num = Long.toString(n);
        int l = num.length();
        if (l < 2) {
            // Las decenas
            return MENORES_30[Integer.parseInt(num)];
            
        } if (l < 3) {
            
            // Caso especial, el número tiene menos de 20 cifras           
            if (n < 30)
                return MENORES_30[(int)n];
            else {
                int cifra = Integer.parseInt(num.substring(0, 1)) - 1;
                if(n % 10 == 0)
                    return DECENAS[cifra];
                else 
                    return DECENAS[cifra] + " Y " + getLiteral(num.substring(1));
            }
        } else if (l < 4) {
            
            // Hasta centenas
            int cifra = Integer.parseInt(num.substring(0, 1)) - 1;
            if(n % 100 == 0) {
                return CENTENAS[cifra];
            } else if(n > 100 && n < 200) {
                return "CIENTO " + getLiteral(num.substring(1));
            } else {
                return CENTENAS[cifra] + " " + getLiteral(num.substring(1));
            }
                        
        } else if (l == 4) {

            if(n == 1000) return MIL;
            return MENORES_30[(int)n / 1000] + " " + MIL + " " + getLiteral(num.substring(1));
            
        } else if (l < 7) {

            int lm = l - 3;
            return getLiteral(num.substring(0, lm))
                 + " " + MIL + " "
                 + getLiteral(num.substring(lm));
            
        } else if (l < 13) {
            
            // Procesa el caso de los millones
            int lm = l - 6;
            if(n == 1000000) {
                return MILLON[0];
            } else if (n < 2000000) {
                return MILLON[0] + " " + getLiteral(num.substring(1));
            } else {
                return getLiteral(num.substring(0, lm)).trim()
                    + " " + MILLON[1] + " "
                    + getLiteral(num.substring(lm)).trim();
            }
        } else if (l < 22) {

            // Para el caso de los billones
            int lm = l-12;
            if(n == 1000000000000L) {
                return BILLON[0];
            } else if(n < 2000000000000L) {
                return BILLON[0] + " " + getLiteral(num.substring(1));
            } else {
                return getLiteral(num.substring(0, lm)).trim()
                    + " " + BILLON[1] + " "
                    + getLiteral(num.substring(lm)).trim();                
            }
        }
        return "";
    }

 
    /** 
     * Pasa un numero sin decimales a la cadena de texto que lo representa.
     * El número puede tener como mucho 19 digitos. En caso contrario se 
     * genera un error. Esto quiere decir que puede llegar a transformar
     * del orden de billones.
     * 
     * Función tomada de la clase Importe.java
     *
     * @param num                           Número del que se quiere obtener el literal
     * 
     * @return                              Cadena que represeta el número leido
     */
    public static String getLiteral(long num) {
        String s_num = Long.toString(num);
        String literal = getLiteral(s_num);
        return literal;
    }
    
        
    /** 
     * Obtiene una cadena literal con la parte entera el número.
     * El número puede tener como mucho 19 digitos. En caso contrario se genera un error.
     *
     * @return                              Cadena que represeta la parte entera del número
     */
    public String getParteEnteraLiteral() {
        String s_numero = this.getParteEntera();
        String literal = getLiteral(s_numero);
        return literal.trim();
    }
    
    
    /** 
     * Obtiene una cadena literal con la parte decimal del número.
     *
     * @return                              Cadena que represeta la parte decimal del número
     */
    public String getParteDecimalLiteral() {
        String s_numero = Integer.toString(this.getParteDecimalInt());
        String literal = getLiteral(s_numero);
        return literal.trim();
    }

    
    /** 
     * Obtiene una cadena literal el número. Tanto la parte entera como la parte decimal.
     *
     * @return                              Cadena que represeta el número
     */
    public String getLiteral() {
        String s_parte_entera = getParteEnteraLiteral();
        String s_parte_decimal = getParteDecimalLiteral();
        String literal = s_parte_entera + " " + CON + " " + s_parte_decimal;
        return literal;
    }
    
    /**
     * Retorna el número en formato para su presentación en pantalla, con "." para la separación de miles y "," para separar los decimales.
     * Si el número no está definido, retorna 0.
     *
     * @return                                  String con el número formateado para pantalla
     */
    public String toStringScr() {
        String v = getValor();

        String valor_scr = Numeros.formatearImporte(v, num_decimales_scr);
        return valor_scr;
    }

    
    /**
     * Retorna el número en formato para su presentación en pantalla, con "." para la separación de miles y "," para separar los decimales.
     * Permite especificar el número de decimales a utilizar para la presentación.
     * Si el número no está definido, retorna 0.
     *
     * @param num_decimales                     Número de decimales
     * 
     * @return                                  String con el número formateado para pantalla
     */
    public String toStringScr(int num_decimales) {
        String v = getValor();
        String valor_scr = Numeros.formatearImporte(v, num_decimales);
        return valor_scr;
    }
    

    /**
     * Retorna el número en formato para su presentación en pantalla, con "." para la separación de miles y "," para separar los decimales.
     * Si el número no está definido, retorna "".
     *
     * @return                              String con el número formateado para pantalla
     */
    public String toStringScrOBlanco() {
        if (!definido()) {
            return "";
        }
        return toStringScr();
    }


    /**
     * Retorna el número en formato para su presentación en pantalla, con "." para la separación de miles y "," para separar los decimales.
     * Permite especificar el número de decimales a utilizar para la presentación.
     * Si el número no está definido, retorna "".
     *
     * @param num_decimales                 Número de decimales a utilizar
     * 
     * @return                              String con el número formateado para pantalla
     */
    public String toStringScrOBlanco(int num_decimales) {
        if (!definido()) {
            return "";
        }
        return toStringScr(num_decimales);
    }
    
    
    /**
     * Retorna el número sin formato.
     * Si el número no está definido, retorna 0.
     * Esta función es equivalente a getValor()
     *
     * @return                              String con el número
     */
    @Override
    public String toString() {
        return getValor();
    }


    /**
     * Retorna el número sin formato.
     * Si el número no está definido, retorna "".
     *
     * @return                              String con el número
     */
    public String toStringOBlanco() {
        if (!definido()) return "";
        return toString();
    }

}
