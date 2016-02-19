/*
 * CFecha.java
 *
 * Creado el 9 de noviembre de 2007, 11:04
 *
 */

package es.pmp.pmplib.tipos.general;

import es.pmp.pmplib.Cadenas;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Clase para almacenar y gestionar una fecha.
 *
 * @author pmpreciado
 */
public class CFecha implements Cloneable, Comparable <CFecha> {

    /** Fecha mínima y máxima */
    public static final int FECHA_MIN                   = 0;
    public static final int FECHA_MAX                   = 99999999;

    
    /** Año límite para determinar el siglo al que pertenece un año expresado mediante dos dígitos */
    private static final int AÑO_LIMITE_SIGLO = 29;
    
    
    /** Carácteres separadores comunes */
    public static final String SEPARADOR_PUNTO = ".";
    public static final String SEPARADOR_BARRA = "/";

    /** Carácter separador predeterminado para la fecha */
    public static final String SEPARADOR_FECHA = SEPARADOR_PUNTO;


    /** Formato de la fecha AAAAMMDD */
    public static final int FF_AAAAMMDD     = 1;

    /** Formato de la fecha DDMMAAAA */
    public static final int FF_DDMMAAAA     = 2;

    /** Formato de la fecha AAMMDD */
    public static final int FF_AAMMDD       = 3;

    /** Formato de la fecha DDMMAA */
    public static final int FF_DDMMAA       = 4;

    /** Formato de la fecha AAAAMM */
    public static final int FF_AAAAMM       = 5;

    /** Formato de la fecha MMAAAA */
    public static final int FF_MMAAAA       = 6;

    /** Formato de la fecha AAMM */
    public static final int FF_AAMM         = 7;
    
    /** Formato de la fecha MMAA */
    public static final int FF_MMAA         = 8;

    /** Formato de la fecha AA_MM */
    public static final int FF_AA_MM        = 9;
    
    /** Formato de la fecha MM_AA */
    public static final int FF_MM_AA        = 10;
    
    /** Formato de la fecha DD.MM.AAAA (da igual el separador, que este caso es el punto) */
    public static final int FF_DD_MM_AAAA   = 11;

    /** Formato de la fecha AAAA.MM.DD (da igual el separador, que este caso es el punto) */
    public static final int FF_AAAA_MM_DD   = 12;

    
    
    /** Días de la semana */
    public static final int LUNES           = 1;
    public static final int MARTES          = 2;
    public static final int MIERCOLES       = 3;
    public static final int JUEVES          = 4;
    public static final int VIERNES         = 5;
    public static final int SABADO          = 6;
    public static final int DOMINGO         = 7;

    /** Meses del año */
    public static final int ENERO           = 1;
    public static final int FEBRERO         = 2;
    public static final int MARZO           = 3;
    public static final int ABRIL           = 4;
    public static final int MAYO            = 5;
    public static final int JUNIO           = 6;
    public static final int JULIO           = 7;
    public static final int AGOSTO          = 8;
    public static final int SEPTIEMBRE      = 9;
    public static final int OCTUBRE         = 10;
    public static final int NOVIEMBRE       = 11;
    public static final int DICIEMBRE       = 12;

    /** Flag que indica si la fecha ha sido definida */
    boolean fecha_definida;


    /** Día de la fecha */
    int dia;

    /** Mes de la fecha */
    int mes;

    /** Año de la fecha */
    int año;

    /** Separador para la fecha */
    String separador;


    /** Descripción del error producido al definir la fecha */
    String desc_error;


    
    
    /**
     * Retorna una instancia de esta clase, sin definir la fecha.
     * 
     * @return                                  Fecha no definida
     */
    public static CFecha noDefinida() {
        return new CFecha();
    }


    /**
     * Retorna la fecha actual, como un entero, en formato YYYYMMDD.
     *
     * @return                              Entero con la fecha actual en formato YYYYMMDD
     */
    public static int fechaSistemaInt() {
        Calendar cal = new GregorianCalendar();

        int año = cal.get(Calendar.YEAR);
        int mes = cal.get(Calendar.MONTH) + 1;
        int dia = cal.get(Calendar.DAY_OF_MONTH);

        int fecha = año * 10000 + mes * 100 + dia;
        return fecha;
    }
    
    
    /**
     * Crea una instancia de la clase inicializada con la fecha del sistema.
     * 
     * @return                                  Fecha del sistema
     */
    public static CFecha fechaSistema() {
        
        int fecha_actual = fechaSistemaInt();
        CFecha f = new CFecha(fecha_actual);
        return f;
    }

    
    /**
     * Verifica que una fecha sea válida.
     * Se comprueba que la fecha sea correcta, teniendo en cuenta los días
     * que hay en cada mes, incluso en el mes de febrero de los años bisiestos.
     *
     * @param fecha                 String que representa la fecha en formato AAAAMMDD
     * @return                      'true' si la fecha es válida, y 'false' si no
     */
    public static boolean esFechaValida(String fecha) {

        int n_fecha = Integer.parseInt(fecha);

        boolean es_fecha_valida = esFechaValida(n_fecha);
        return es_fecha_valida;
    }

    
    

    /**
     * Crea una instancia de la clase, sin definir la fecha.
     * La fecha se puede establecer posteriormente mediante setFecha().
     */
    public CFecha() {
        init();
    }


    /**
     * Crea una instancia de la clase.
     *
     * @param s_fecha                           String con la fecha en formato AAAAMMDD
     */
    public CFecha(String s_fecha) {

        init();
        setFecha(s_fecha);
    }


    /**
     * Crea una instancia de la clase.
     *
     * @param s_fecha                           String con la fecha en cualquier formato
     * @param formato                           Formato utilizado para expresar la fecha
     *   <ul>
     *     <li> FF_AAAAMMDD
     *     <li> FF_DDMMAAAA
     *     <li> FF_AAMMDD
     *     <li> FF_DDMMAA
     *     <li> FF_DD_MM_AAAA
     *     <li> FF_AAAA_MM_DD
     *   </ul>
     */
    public CFecha(String s_fecha, int formato) {

        init();
        setFecha(s_fecha, formato);
    }


    /**
     * Crea una instancia de la clase.
     *
     * @param fecha                         Fecha en cualquier formato
     * @param formato                       Formato utilizado para expresar la fecha
     *   <ul>
     *     <li> FF_AAAAMMDD
     *     <li> FF_DDMMAAAA
     *     <li> FF_AAMMDD
     *     <li> FF_DDMMAA
     *   </ul>
     */
    public CFecha(int fecha, int formato) {
        init();
        setFecha(fecha, formato);
    }


    /**
     * Crea una instancia de la clase.
     *
     * @param fecha                         Fecha en formato AAAAMMDD
     */
    public CFecha(int fecha) {
        init();
        setFecha(fecha);
    }


    /**
     * Crea una instancia de la clase, inicializándola con una fecha existente.
     *
     * @param fecha                         Fecha
     */
    public CFecha(CFecha fecha) {
        init();
        setFecha(fecha);
    }


    /**
     * Crea una instancia de la clase.
     *
     * @param dia                           Día
     * @param mes                           Mes
     * @param año                           Año
     */
    public CFecha(int dia, int mes, int año) {
        init();
        setFecha(dia, mes, año);
    }


    /**
     * Crea una instancia de la clase.
     *
     * @param cal                           Objeto de tipo Calendar con la fecha
     */
    public CFecha(Calendar cal) {
        init();
        int dia = cal.get(Calendar.DAY_OF_MONTH);
        int mes = cal.get(Calendar.MONTH);
        int año = cal.get(Calendar.YEAR);

        setFecha(dia, mes, año);
    }


    /**
     * Crea una instancia de la clase.
     *
     * @param fecha                         Objeto de tipo java.util.Date con la Fecha
     */
    public CFecha(java.util.Date fecha) {
        init();
        
        DateFormat df = new SimpleDateFormat("yyyyMMdd");
        String s_fecha = df.format(fecha);
        setFecha(s_fecha, FF_AAAAMMDD);
    }

    

    /**
     * Inicializa la clase.
     */
    protected void init() {
        año = -1;
        mes = -1;
        dia = -1;

        fecha_definida = false;
        separador = SEPARADOR_FECHA;

        desc_error = null;
    }


    /**
     * Retorna un copia exacta de esta instancia de la clase.
     *
     * @return                              Objeto de CFecha con un clone de la instancia
     */
    @Override
    public CFecha clone() {
        CFecha c = new CFecha(this);
        return c;
    }


    /**
     * Permite comparar esta fecha con otra.
     *
     * @param f2                            Fecha con la que comparar
     *
     * @return                              +1, si this > f2
     *                                      0, si this == f2
     *                                      -1, si this < f2
     */
    @Override
    public int compareTo(CFecha f2) {
        if (this.esAnterior(f2)) return +1;
        if (this.esPosterior(f2)) return -1;
        return 0;
    }


    /**
     * Establece la fecha.
     *
     * @param s_fecha                       Fecha en formato AAAAMMDD
     */
    public void setFecha(String s_fecha) {
        setFecha(s_fecha, FF_AAAAMMDD);
    }


    /**
     * Establece la fecha.
     *
     * @param s_fecha                       Fecha en cualquier formato
     * @param formato                       Formato utilizado para expresar la fecha
     *   <ul>
     *     <li> FF_AAAAMMDD
     *     <li> FF_DDMMAAAA
     *     <li> FF_AAMMDD
     *     <li> FF_DDMMAA
     *   </ul>
     */
    public void setFecha(String s_fecha, int formato) {
        
        if (s_fecha == null || s_fecha.length() == 0) {
            fecha_definida = false;
            return;
        }

        String s_año = "";
        String s_mes = "";
        String s_dia = "";

        switch (formato) {
            case FF_AAAAMMDD:
                s_año = Cadenas.primeros(s_fecha, 4);
                s_mes = Cadenas.miSubstringLong(s_fecha, 4, 2);
                s_dia = Cadenas.ultimos(s_fecha, 2);
                break;

            case FF_DDMMAAAA:
                s_dia = Cadenas.primeros(s_fecha, 2);
                s_mes = Cadenas.miSubstringLong(s_fecha, 2, 2);
                s_año = Cadenas.ultimos(s_fecha, 4);
                break;

            case FF_AAMMDD:
                s_año = Cadenas.primeros(s_fecha, 2);
                s_mes = Cadenas.miSubstringLong(s_fecha, 2, 2);
                s_dia = Cadenas.ultimos(s_fecha, 2);
                break;

            case FF_DDMMAA:
                s_dia = Cadenas.primeros(s_fecha, 2);
                s_mes = Cadenas.miSubstringLong(s_fecha, 2, 2);
                s_año = Cadenas.ultimos(s_fecha, 2);
                break;
                
            case FF_AAAAMM:
                s_año = Cadenas.primeros(s_fecha, 4);
                s_mes = Cadenas.miSubstringLong(s_fecha, 4, 2);
                s_dia = "01";
                break;
                
            case FF_MMAAAA:
                s_dia = "01";
                s_mes = Cadenas.primeros(s_fecha, 2);
                s_año = Cadenas.ultimos(s_fecha, 4);
                break;

            case FF_DD_MM_AAAA:
                if (s_fecha.length() == 9) {
                    s_fecha = "0" + s_fecha;
                }
                s_dia = Cadenas.primeros(s_fecha, 2);
                s_mes = Cadenas.miSubstringLong(s_fecha, 3, 2);
                s_año = Cadenas.ultimos(s_fecha, 4);
                break;

            case FF_AAAA_MM_DD:
                s_año = Cadenas.primeros(s_fecha, 4);
                s_mes = Cadenas.miSubstringLong(s_fecha, 5, 2);
                s_dia = Cadenas.ultimos(s_fecha, 2);
                break;
        }
        
        int n_año = Cadenas.toEntero(s_año);
        int n_mes = Cadenas.toEntero(s_mes);
        int n_dia = Cadenas.toEntero(s_dia);
        n_año = getAño4Digitos(n_año);
        
        boolean fecha_valida = esFechaValida(n_dia, n_mes, n_año);
        if (!fecha_valida) {
            fecha_definida = false;
            desc_error = "La fecha '" + s_fecha + "' es incorrecta o inexistente";
            return;
        }

        this.dia = n_dia;
        this.mes = n_mes;
        this.año = n_año;

        fecha_definida = true;
    }


    /**
     * Establece la fecha.
     *
     * @param fecha                             Fecha en formato AAAAMMDD
     */
    public void setFecha(int fecha) {
        setFecha(fecha, FF_AAAAMMDD);
    }


    /**
     * Establece la fecha, a partir de un objeto de tipo fecha.
     *
     * @param fecha                             Objeto de tipo fecha
     */
    public void setFecha(CFecha fecha) {
        int n_fecha = fecha.getFecha();
        setFecha(n_fecha);
    }


    /**
     * Establece la fecha.
     * @param fecha                             Fecha en formato AAAAMMDD
     * @param formato                           Formato utilizado para expresar la fecha (CFecha.FF_xxx)
     */
    protected void setFecha(int fecha, int formato) {

        String s_fecha = Integer.toString(fecha);
        setFecha(s_fecha, formato);
    }


    /**
     * Establece la fecha.
     *
     * @param dia                               Día
     * @param mes                               Mes
     * @param año                               Año
     */
    protected void setFecha(int dia, int mes, int año) {
        boolean fecha_valida = esFechaValida(dia, mes, año);
        if (!fecha_valida) {
            desc_error = "La fecha es incorrecta o inexistente";
            return;
        }

        this.dia = dia;
        this.mes = mes;
        this.año = año;

        fecha_definida = true;
    }


    /**
     * Establece el día para la fecha actual.
     *
     * @param dia                               Nuevo día
     */
    public void setDia(int dia) {
        this.dia = dia;

        boolean fecha_valida = esFechaValida(dia, mes, año);
        if (!fecha_valida) {
            desc_error = "La fecha es incorrecta o inexistente";
            fecha_definida = false;
        }
    }


    /**
     * Establece el mes para la fecha actual.
     *
     * @param mes                               Nuevo mes
     */
    public void setMes(int mes) {
        this.mes = mes;

        boolean fecha_valida = esFechaValida(dia, mes, año);
        if (!fecha_valida) {
            desc_error = "La fecha es incorrecta o inexistente";
            fecha_definida = false;
        }
    }


    /**
     * Establece el año para la fecha actual.
     *
     * @param año                               Nuevo año
     */
    public void setAño(int año) {
        this.año = año;

        boolean fecha_valida = esFechaValida(dia, mes, año);
        if (!fecha_valida) {
            desc_error = "La fecha es incorrecta o inexistente";
            fecha_definida = false;
        }
    }


    /**
     * Establece el carácter separador para las fechas a mostrar formateadas.
     *
     * @param separador                         Carácter separador
     */
    public void setSeparador(String separador) {
        this.separador = separador;
    }

    
    /**
     * Obtiene el carácter separador para las fechas a mostrar formateadas.
     *
     * @return                                  Carácter separador
     */
    public String getSeparador() {
        return separador;
    }

    
    /**
     * Verifica que una fecha sea válida.
     * Se comprueba que la fecha sea correcta, teniendo en cuenta los días
     * que hay en cada mes, incluso en el mes de febrero de los años bisiestos.
     *
     * @param dia                   Entero que representa el día
     * @param mes                   Entero que representa el mes
     * @param año                   Entero que representa el año
     * 
     * @return                      'true' si la fecha es válida, y 'false' si no
     */
    public static boolean esFechaValida(int dia, int mes, int año) {

        int [] dias_mes = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        if (año < 1900) return false;
        if (mes < 1 || mes > 12) return false;

        if (mes != 2) {
            if (dia < 1 || dia > dias_mes[mes-1]) return false;
        } else { // Estamos en febrero
            // Años bisiestos: 2000, 2004...
            int dias_febrero = 28;
            if (año % 4 == 0) dias_febrero = 29;
            if (dia < 1 || dia > dias_febrero) return false;
        }

        return true;
    }

    
    /**
     * Comprueba que una fecha sea correcta.
     *
     * @param fecha                         Fecha en formato AAAAMMDD
     * @return                              'true' si la fecha existe y es correcta
     *                                      'false' en caso contrario
     */
    public static boolean esFechaValida(int fecha) {

        CFecha fecha_obj = new CFecha(fecha);
        
        int dia = fecha_obj.getDia();
        int mes = fecha_obj.getMes();
        int año = fecha_obj.getAño();

        boolean es_fecha_valida = esFechaValida(dia, mes, año);
        return es_fecha_valida;
    }



    /**
     * Retorna el estado de si la fecha está definida o no.
     *
     * @return                              'true' si la fecha ha sido definida
     *                                      'false' si no
     */
    public boolean fechaDefinida() {
        return fecha_definida;
    }


    /**
     * Retorna la fecha en el formato solicitado. Si la fecha no está definida, retorna 0.
     *
     * @param formato                       Formato utilizado para expresar la fecha
     *   <ul>
     *     <li> FF_AAAAMMDD
     *     <li> FF_DDMMAAAA
     *     <li> FF_AAMMDD
     *     <li> FF_DDMMAA
     *     <li> FF_AAAAMM
     *     <li> FF_MMAAAA
     *   </ul>
     *
     * @return                              Fecha en formato AAAAMMDD
     */
    public int getFecha(int formato) {

        if (!fecha_definida) {
            return 0;
        }

        int fecha = 0;

        switch (formato) {
            case FF_AAAAMMDD:
                fecha = año * 10000 + mes * 100 + dia;
                break;

            case FF_DDMMAAAA:
                fecha = dia * 1000000 + mes * 10000 + año;
                break;

            case FF_AAMMDD:
                fecha = (año % 100) * 10000 + mes * 100 + dia;
                break;

            case FF_DDMMAA:
                fecha = dia * 10000 + mes * 100 + (año % 100);
                break;

            case FF_AAAAMM:
                fecha = año * 100 + mes;
                break;

            case FF_MMAAAA:
                fecha = mes * 10000 + año;
                break;
        }

        return fecha;
    }


    /**
     * Retorna la fecha en formato AAAAMMDD.
     *
     * @return                              Fecha en formato AAAAMMDD
     */
    public int getFecha() {
        int fecha = getFecha(FF_AAAAMMDD);
        return fecha;
    }


    /**
     * Retorna la fecha en formato AAAAMMDD. Si la fecha no estuviera definida, retorna la fecha mínima (Comun.FECHA_MIN).
     *
     * @return                              Fecha en formato AAAAMMDD
     */
    public int getFechaOMin() {
        if (!fechaDefinida()) return FECHA_MIN;
        return getFecha();
    }


    /**
     * Retorna la fecha suministrada en formato AAAAMMDD. Si la fecha fuera 'null' o no estuviera definida, retorna la fecha mínima (Comun.FECHA_MIN).
     *
     * @param fecha                         Fecha a convertir
     * @return                              Fecha en formato AAAAMMDD
     */
    public static int getFechaOMin(CFecha fecha) {
        if (fecha == null || !fecha.fechaDefinida()) return FECHA_MIN;
        return fecha.getFecha();
    }


    /**
     * Retorna la fecha en formato AAAAMMDD. Si la fecha no estuviera definida, retorna la fecha máxima (Comun.FECHA_MAX).
     *
     * @return                              Fecha en formato AAAAMMDD
     */
    public int getFechaOMax() {
        if (!fechaDefinida()) return FECHA_MAX;
        return getFecha();
    }


    /**
     * Retorna la fecha suministrada en formato AAAAMMDD. Si la fecha fuera 'null' o no estuviera definida, retorna la fecha máxima (Comun.FECHA_MAX).
     *
     * @param fecha                         Fecha a convertir
     * @return                              Fecha en formato AAAAMMDD
     */
    public static int getFechaOMax(CFecha fecha) {
        if (fecha == null || !fecha.fechaDefinida()) return FECHA_MAX;
        return fecha.getFecha();
    }


    /**
     * Retorna la fecha suministrada en formato AAAAMMDD. Si la fecha fuera 'null' o no estuviera definida, retorna 0.
     *
     * @param fecha                         Fecha a convertir
     * @return                              Fecha en formato AAAAMMDD
     */
    public static int getFechaONoDefinido(CFecha fecha) {
        if (fecha == null || !fecha.fechaDefinida()) return 0;
        return fecha.getFecha();
    }


    /**
     * Dadas las dos últimas cifras del año, obtiene el año con cuatro cifras.
     * Ejemplos:
     *   12 --> 2012
     *   84 --> 1984
     * 
     * Para determinar el siglo, se utiliza como corte el parámetro año_limite_siglo, de modo que:
     *   Si año_2_digitos <= año_limite_siglo se considera que el año es 20xx
     *   Si año_2_digitos  > año_limite_siglo se considera que el año es 19xx
     * 
     * @param año_2_digitos                     Año expresado con dos dígitos. Si se pasa un año de 4 dígitos, retorna el este mismo año, sin hacer nada
     * @param año_limite_siglo                  Año tomado como referencia para determinar el siglo
     * 
     * @return                                  Año expresado con cuatro dígitos
     */
    public static int getAño4Digitos(int año_2_digitos, int año_limite_siglo) {
        
        if (año_2_digitos >= 1000) {
            return año_2_digitos;
        }
        
        int año_4_digitos;
        
        if (año_2_digitos <= año_limite_siglo) {
            año_4_digitos = 2000 + año_2_digitos;
        } else {
            año_4_digitos = 1900 + año_2_digitos;
        }
        
        return año_4_digitos;
    }
    
    
    /**
     * Dadas las dos últimas cifras del año, obtiene el año con cuatro cifras.
     * Ejemplos:
     *   12 --> 2012
     *   84 --> 1984
     * 
     * Para determinar el siglo, se utiliza como corte la constante AÑO_LIMITE_SIGLO.
     * 
     * @param año_2_digitos                     Año expresado con dos dígitos. Si se pasa un año de 4 dígitos, retorna el este mismo año, sin hacer nada
     * @return                                  Año expresado con cuatro dígitos
     */
    public static int getAño4Digitos(int año_2_digitos) {
        int año_4_digitos = getAño4Digitos(año_2_digitos, AÑO_LIMITE_SIGLO);
        return año_4_digitos;
    }
    
    
    /**
     * Obtiene el nombre de un día de la semana.
     *
     * @param dia                               Número de día (LUNES, MARTES, MIERCOLES, JUEVES, VIERNES, SABADO, DOMINGO)
     * @return                                  Nombre del día
     */
    public static String getNombreDia(int dia) {
        switch (dia) {
            case LUNES:         return "Lunes";
            case MARTES:        return "Martes";
            case MIERCOLES:     return "Miércoles";
            case JUEVES:        return "Jueves";
            case VIERNES:       return "Viernes";
            case SABADO:        return "Sábado";
            case DOMINGO:       return "Domingo";
        }

        return "";
    }


    /**
     * Obtiene el nombre del día de la semana.
     *
     * @return                                  Nombre del día
     */
    public String getNombreDia() {
        return getNombreDia(getDia());
    }


    /**
     * Obtiene el nombre de un mes del año.
     *
     * @param mes                               Número de mes (ENERO, FEBRERO, MARZO, ABRIL, MAYO, JUNIO, JULIO, AGOSTO, SEPTIEMBRE, OCTUBRE, NOVIEMBRE, DICIEMBRE)
     * @return                                  Nombre del mes
     */
    public static String getNombreMes(int mes) {
        switch (mes) {
            case ENERO:         return "Enero";
            case FEBRERO:       return "Febrero";
            case MARZO:         return "Marzo";
            case ABRIL:         return "Abril";
            case MAYO:          return "Mayo";
            case JUNIO:         return "Junio";
            case JULIO:         return "Julio";
            case AGOSTO:        return "Agosto";
            case SEPTIEMBRE:    return "Septiembre";
            case OCTUBRE:       return "Octubre";
            case NOVIEMBRE:     return "Noviembre";
            case DICIEMBRE:     return "Diciembre";
        }

        return "";
    }

    /**
     * Obtiene el nombre del mes del año.
     *
     * @return                                  Nombre del mes
     */
    public String getNombreMes() {
        return getNombreMes(getMes());
    }



    /**
     * Obtiene el nombre abreviado (tres letras) de un mes del año.
     *
     * @param mes                               Número de mes (ENERO, FEBRERO, MARZO, ABRIL, MAYO, JUNIO, JULIO, AGOSTO, SEPTIEMBRE, OCTUBRE, NOVIEMBRE, DICIEMBRE)
     * @return                                  Nombre abreviado del mes
     */
    public static String getNombreAbreviadoMes(int mes) {
        switch (mes) {
            case ENERO:         return "Ene";
            case FEBRERO:       return "Feb";
            case MARZO:         return "Mar";
            case ABRIL:         return "Abr";
            case MAYO:          return "May";
            case JUNIO:         return "Jun";
            case JULIO:         return "Jul";
            case AGOSTO:        return "Ago";
            case SEPTIEMBRE:    return "Sep";
            case OCTUBRE:       return "Oct";
            case NOVIEMBRE:     return "Nov";
            case DICIEMBRE:     return "Dic";
        }

        return "";
    }


    /**
     * Obtiene el nombre abreviado (tres letras) de un mes del año.
     *
     * @return                                  Nombre abreviado del mes
     */
    public String getNombreAbreviadoMes() {
        return getNombreAbreviadoMes(getMes());
    }


    /**
     * Obtiene el día.
     *
     * @return                                  Día
     */
    public int getDia() {
        return dia;
    }


    /**
     * Obtiene el mes.
     *
     * @return                                  Mes
     */
    public int getMes() {
        return mes;
    }


    /**
     * Obtiene el año.
     *
     * @return                                  Año
     */
    public int getAño() {
        return año;
    }

    /**
     * Retorna el día de la semana en que cae la fecha.
     *
     * @return                                  Día de la semana de la fecha dada (1 = Lunes, 6 = Sábado, 7 = Domingo)
     */
    public int getDiaSemana()
    {
        Calendar cal = new GregorianCalendar(año, mes - 1, dia);
        int dia_ingles = cal.get(Calendar.DAY_OF_WEEK);         // 1 = Domingo, 7 = Sábado

        int dia_español = dia_ingles - 1;
        if (dia_español == 0) dia_español = 7;
        return dia_español;
    }


    /**
     * Calcula los días que faltan hasta llegar a la fecha suministrada.
     * Si la fecha suministrada es anterior a esta, los días devueltos estarán en negativo.
     *
     * @param fecha_hasta
     * @return                                  Número de días que faltan hasta fecha_hasta
     */
    public long getDiasHasta(CFecha fecha_hasta) {

        // Pasamos las fechas a calendarios
        Calendar cal_final = new GregorianCalendar(fecha_hasta.getAño(), fecha_hasta.getMes() - 1, fecha_hasta.getDia());
        Calendar cal_inicial = new GregorianCalendar(año, mes - 1, dia);

        long dif_miliseg = cal_final.getTimeInMillis() - cal_inicial.getTimeInMillis();
        long dif_dias = dif_miliseg / (1000 * 60 * 60 * 24);
        return dif_dias;
    }


    /**
     * Calcula los días que han transcurrido desde la fecha suministrada.
     * Si la fecha suministrada es posterior a esta, los días devueltos estarán en negativo.
     *
     * @param fecha_desde
     * @return                                  Número de días transucrridos desde fecha_desde
     */
    public long getDiasDesde(CFecha fecha_desde) {

        // Pasamos las fechas a calendarios
        Calendar cal_final = new GregorianCalendar(año, mes - 1, dia);
        Calendar cal_inicial = new GregorianCalendar(fecha_desde.getAño(), fecha_desde.getMes() - 1, fecha_desde.getDia());

        long dif_miliseg = cal_final.getTimeInMillis() - cal_inicial.getTimeInMillis();
        long dif_dias = dif_miliseg / (1000 * 60 * 60 * 24);
        return dif_dias;
    }


    /**
     * Calcula los días que faltan hasta llegar al cumpleaños.
     *
     * @return                                  Número de días que faltan hasta su cumpleaños (0 a 365)
     */
    public int getDiasHastaCumpleaños() {

        if (!fechaDefinida()) return 0;

        CFecha hoy = fechaSistema();
        int hoy_año = hoy.getAño();

        CFecha fecha_temp = new CFecha(this);
        fecha_temp.setAño(hoy_año);

        if (hoy.esPosterior(fecha_temp)) fecha_temp.addAños(1);

        long n = hoy.getDiasHasta(fecha_temp);
        return (int) n;
    }


    /**
     * Añade (o resta) un número de días a la fecha.
     *
     * @param num_dias                      Número de días a añadir (puede ser negativo)
     * @return                              Instancia actual
     */
    public CFecha addDias(int num_dias) {

        Calendar cal = new GregorianCalendar(año, mes - 1, dia);
        cal.add(Calendar.DAY_OF_MONTH, num_dias);
        dia = cal.get(Calendar.DAY_OF_MONTH);
        mes = cal.get(Calendar.MONTH) + 1;
        año = cal.get(Calendar.YEAR);

        return this;
    }


    /**
     * Añade (o resta) un número de meses a la fecha.
     *
     * @param num_meses                     Número de meses a añadir (puede ser negativo)
     * @return                              Instancia actual
     */
    public CFecha addMeses(int num_meses) {

        Calendar cal = new GregorianCalendar(año, mes - 1, dia);
        cal.add(Calendar.MONTH, num_meses);
        dia = cal.get(Calendar.DAY_OF_MONTH);
        mes = cal.get(Calendar.MONTH) + 1;
        año = cal.get(Calendar.YEAR);

        return this;
    }


    /**
     * Añade (o resta) un número de años a la fecha.
     *
     * @param num_años                      Número de años a añadir (puede ser negativo)
     * @return                              Instancia actual
     */
    public CFecha addAños(int num_años) {

        Calendar cal = new GregorianCalendar(año, mes - 1, dia);
        cal.add(Calendar.YEAR, num_años);
        dia = cal.get(Calendar.DAY_OF_MONTH);
        mes = cal.get(Calendar.MONTH) + 1;
        año = cal.get(Calendar.YEAR);

        return this;
    }

    
    /**
     * Añade (o resta) un número de días, meses y años a la fecha.
     *
     * @param num_años                      Número de años a añadir (puede ser negativo)
     * @return                              Instancia actual
     */
    public CFecha add(int num_dias, int num_meses, int num_años) {

        this.addDias(num_dias);
        this.addMeses(num_meses);
        this.addAños(num_años);
        
        return this;
    }
    



    /**
     * Desplaza la fecha el número de meses indicado.
     * La fecha resultante tendrá el mismo día que la original, a menos que sea
     * un día imposible, en cuyo caso se obtendrá la fecha correcta anterior más cercana.
     * 
     * Por ejemplo, desplazar 3 meses el 5/06/2012 dará 5/09/2012.
     * Desplazar 1 mes el 31/01/2012 dará 29/02/2012.
     * Desplazar 3 meses el 31/10/2012 dará 31/01/2013.
     *
     * @param num_meses                     Número de meses a desplazar (puede ser negativo)
     * 
     * @return                              Instancia actual
     */
    public CFecha desplazarMeses(int num_meses) {

        int inc_años = num_meses / 12;
        int inc_meses = num_meses % 12;
        
        mes += inc_meses;
        if (mes > 12) {
            mes -= 12;
            inc_años++;
        } else if (mes < 1) {
            mes += 12;
            inc_años--;
        }
        
        año += inc_años;
        
        while (!esFechaValida(dia, mes, año) && dia > 28) {
            dia--;
        }
        
        return this;
    }


    /**
     * Desplaza la fecha el número de años indicado.
     * La fecha resultante tendrá el mismo día y el mismo mes que la original, a menos que sea
     * un día imposible,  en cuyo caso se obtendrá la fecha correcta anterior más cercana.
     * 
     * Por ejemplo, desplazar 3 años el 5/06/2012 dará 5/06/2015.
     * Por ejemplo, desplazar 1 año el 29/02/2012 dará 28/02/2013.
     *
     * @param num_años                          Número de años a desplazar (puede ser negativo)
     * 
     * @return                                  Instancia actual
     */
    public CFecha desplazarAños(int num_años) {

        año += num_años;

        while (!esFechaValida(dia, mes, año) && dia > 28) {
            dia--;
        }

        return this;
    }
    
    
    /**
     * Obtiene el número total de días de la fecha.
     * Se calcula como, años * 365 + meses * 30 + dias.
     *
     * @return                                  Número de días
     */
    public int fecha2Dias() {
        if (!fechaDefinida()) return 0;
        int dias = 0;
        dias = getAño() * 365 + getMes() * 30 + getDia();

        return dias;
    }


    /**
     * Comprueba si la fecha es anterior a la suministrada.
     *
     * @param fecha_2                           Fecha a comparar
     * @return                                  'true' si la fecha actual es anterior a fecha_2
     *                                          'false' si es posterior o igual
     */
    public boolean esAnterior(CFecha fecha_2) {
        int dias_1 = fecha2Dias();
        int dias_2 = fecha_2.fecha2Dias();

        if (dias_1 < dias_2) return true;
        return false;
    }


    /**
     * Comprueba si la fecha es posterior a la suministrada.
     *
     * @param fecha_2                           Fecha a comparar
     * @return                                  'true' si la fecha actual es posterior a fecha_2
     *                                          'false' si es anterior o igual
     */
    public boolean esPosterior(CFecha fecha_2) {
        int dias_1 = fecha2Dias();
        int dias_2 = fecha_2.fecha2Dias();

        if (dias_1 > dias_2) return true;
        return false;
    }


    /**
     * Comprueba si la fecha es la misma a la suministrada.
     *
     * @param fecha_2                           Fecha a comparar
     * @return                                  'true' si la fecha coincide con fecha_2
     *                                          'false' si no coincide
     */
    public boolean esIgual(CFecha fecha_2) {
        int dias_1 = fecha2Dias();
        int dias_2 = fecha_2.fecha2Dias();

        if (dias_1 == dias_2) return true;
        return false;
    }

    /**
     * Comprueba si el día de hoy es cumpleaños de acuerdo a la fecha actual.
     *
     * @return                                  'true' si hoy es cumpleaños
     *                                          'false' si no
     */
    public boolean hoyEsCumpleaños() {
        CFecha fecha_sistema = fechaSistema();
        if (fecha_sistema.getDia() == this.getDia() && fecha_sistema.getMes() == this.getMes()) return true;
        return false;
    }


    /**
     * Obtiene el número de días existentes en el mes dado del año dado.
     * Hace uso de las funciones de calendario de Java.
     * 
     * @param mes                               Mes
     * @param año                               Año
     * 
     * @return                                  Número de días existentes en el mes dado del año dado
     */
    public static int getNumeroDiasMes(int mes, int año) {
        int mes_java = mes - 1;
        Calendar cal = new GregorianCalendar(año, mes_java, 1); 
        
        int dias = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        return dias;
    }
    
    
    /**
     * Comprueba si la fecha se corresponde con el último día del mes.
     */
    public boolean esUltimoDiaMes() {
        int num_dias_mes = getNumeroDiasMes(mes, año);
        if (dia == num_dias_mes) {
            return true;
        }
        
        return false;
    }

    
    /**
     * Comprueba si se ha producido algún error al establecer la fecha.
     *
     * @return                                  'true' si ha habido error
     *                                          'false' en caso contrario
     */
    public boolean hayError() {
        return desc_error != null;
    }


    /**
     * Obtiene la descripción del último error, si la hubiera.
     *
     * @return                                  Descripción del último error ('null' si no hay)
     */
    public String getDescError() {
        return desc_error;
    }


    /**
     * Retorna la fecha en un String con el formato especificado.
     * Si la fecha no está definida, retorna "".
     *
     * @param formato                           Formato para expresar la fecha
     *   <ul>
     *     <li> FF_AAAAMMDD
     *     <li> FF_DDMMAAAA
     *     <li> FF_AAMMDD
     *     <li> FF_DDMMAA
     *     <li> FF_DD_MM_AAAA
     *     <li> FF_AAAA_MM_DD
     *     <li> FF_AAAAMM
     *     <li> FF_AAMM
     *     <li> FF_MMAA
     *     <li> FF_AA_MM
     *     <li> FF_MM_AA
     *   </ul>
     *
     * @param separador                         Carácter usado para separar los elementos de la fecha
     * 
     * @return                                  String con la fecha actual AAAAMMDD
     */
    public String toString(int formato, String separador) {
        if (!fecha_definida) {
            return "";
        }

        StringBuilder s = new StringBuilder("");
        String s_año = Integer.toString(año);
        String s_año_2 = Integer.toString(año % 100);
        String s_mes = Integer.toString(mes);
        String s_dia = Integer.toString(dia);

        while (s_año.length() < 4) s_año = "0" + s_año;
        while (s_año_2.length() < 2) s_año_2 = "0" + s_año_2;
        while (s_mes.length() < 2) s_mes = "0" + s_mes;
        while (s_dia.length() < 2) s_dia = "0" + s_dia;

        switch (formato) {

            case FF_AAAAMMDD:
                s.append(s_año);
                s.append(s_mes);
                s.append(s_dia);
                break;

            case FF_DDMMAAAA:
                s.append(s_dia);
                s.append(s_mes);
                s.append(s_año);
                break;

            case FF_AAMMDD:
                s.append(s_año_2);
                s.append(s_mes);
                s.append(s_dia);
                break;

            case FF_DDMMAA:
                s.append(s_dia);
                s.append(s_mes);
                s.append(s_año_2);
                break;

            case FF_DD_MM_AAAA:
                s.append(s_dia);
                s.append(separador);
                s.append(s_mes);
                s.append(separador);
                s.append(s_año);
                break;

            case FF_AAAA_MM_DD:
                s.append(s_año);
                s.append(separador);
                s.append(s_mes);
                s.append(separador);
                s.append(s_dia);
                break;
                
            case FF_AAAAMM:
                s.append(s_año);
                s.append(s_mes);
                break;
                
            case FF_AAMM:
                s.append(s_año_2);
                s.append(s_mes);
                break;
                
            case FF_MMAA:
                s.append(s_mes);
                s.append(s_año_2);
                break;
                
            case FF_AA_MM:
                s.append(s_año_2);
                s.append(separador);
                s.append(s_mes);
                break;
                
            case FF_MM_AA:
                s.append(s_mes);
                s.append(separador);
                s.append(s_año_2);
                break;
        }

        return s.toString();
    }
    
    

    /**
     * Retorna la fecha en un String con el formato especificado.
     * Si la fecha no está definida, retorna "".
     *
     * @param formato                           Formato para expresar la fecha
     *   <ul>
     *     <li> FF_AAAAMMDD
     *     <li> FF_DDMMAAAA
     *     <li> FF_AAMMDD
     *     <li> FF_DDMMAA
     *     <li> FF_DD_MM_AAAA
     *     <li> FF_AAAA_MM_DD
     *     <li> FF_AAAAMM
     *     <li> FF_AAMM
     *     <li> FF_MMAA
     *     <li> FF_AA_MM
     *     <li> FF_MM_AA
     *   </ul>
     *
     * @return                                  String con la fecha actual AAAAMMDD
     */
    public String toString(int formato) {
        String s_fecha = toString(formato, separador);
        return s_fecha;
    }


    /**
     * Retorna la fecha en formato para su presentación en pantalla, tipo DD.MM.AAAA.
     * Si la fecha no está definida, retorna "".
     *
     * @return                                  String con la fecha actual en formato DD.MM.AAAA
     */
    public String toStringScr() {
        return toString(FF_DD_MM_AAAA);
    }


    /** 
     * Obtiene un literal con la fecha, por ejemplo: "13 de noviembre de 2014".
     *
     * @return                                  Literal con la fecha
     */
    public String getLiteral() {
        
        if (!fechaDefinida()) return "";
        String literal = getDia() + " de " + getNombreMes().toLowerCase() + " de " + getAño();
        return literal;
    }
    
    
    /**
     * Retorna la fecha en un String con formato AAAAMMDD.
     * Si la fecha no está definida, retorna "".
     *
     * @return                              String con la fecha actual AAAAMMDD
     */
    @Override
    public String toString() {
        return toString(FF_AAAAMMDD);
    }

    public long toDias() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
