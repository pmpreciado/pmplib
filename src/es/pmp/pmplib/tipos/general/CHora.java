/*
 * CHora.java
 *
 * Creado el 3 de diciembre de 2007, 8:46
 *
 */

package es.pmp.pmplib.tipos.general;

import es.pmp.pmplib.Cadenas;
import java.util.Calendar;
import java.util.GregorianCalendar;


/**
 * Clase para almacenar y gestionar una hora.
 *
 * @author pmpreciado
 */
public class CHora implements Cloneable, Comparable <CHora> {
    
    /** Hora mínima y máxima */
    public static final int HORA_MIN                    = 0;
    public static final int HORA_MAX                    = 235959;

    
    /** Carácter separador para la hora */
    public static final String SEPARADOR_HORA = ":";
    
    
    /** Formato de la hora HHMMSS */
    public static final int FH_HHMMSS       = 1;
    
    /** Formato de la hora HHMM */
    public static final int FH_HHMM         = 2;

    /** Formato de la hora HH:MM:SS */
    public static final int FH_HH_MM_SS     = 3;
    
    /** Formato de la hora HH:MM */
    public static final int FH_HH_MM        = 4;

    /** Formato de la hora desconocido */
    public static final int FH_DESCONOCIDO  = 5;

    
    
    /** Flag que indica si la hora ha sido definida */
    boolean hora_definida;
    
    
    /** Hora de la hora */
    int hora;
    
    /** Minuto de la hora */
    int minuto;
    
    /** Segundo de la hora */
    int segundo;
    
    /** Descripción del error producido al definir la hora */
    String desc_error;

    
    
    /**
     * Retorna una instancia de esta clase, sin definir la hora.
     * 
     * @return                                  Hora no definida
     */
    public static CHora noDefinida() {
        CHora h = new CHora();
        return h;
    }
    


    /**
     * Retorna la hora actual en formato HHMM.
     *
     * @return                          Entero con la hora actual en formato HHMM
     */
    public static int getHora4() {
        Calendar cal = new GregorianCalendar();

        int hora24 = cal.get(Calendar.HOUR_OF_DAY);
        int min    = cal.get(Calendar.MINUTE);
        int hora = hora24 * 100 + min;
        return hora;
    }


    /**
     * Retorna la hora actual en formato HHMMSS.
     *
     * @return                          Entero con la hora actual en formato HHMMSS
     */
    public static int getHora6() {
        Calendar cal = new GregorianCalendar();

        int hora24 = cal.get(Calendar.HOUR_OF_DAY);
        int min    = cal.get(Calendar.MINUTE);
        int sec    = cal.get(Calendar.SECOND);
        int hora = hora24 * 10000 + min * 100 + sec;
        return hora;
    }


    /**
     * Retorna la hora actual en formato HHMMSSmmm (con milisegundos)
     *
     * @return                          Entero con la hora actual en formato HHMMSSmmm
     */
    public static int getHora9() {
        Calendar cal = new GregorianCalendar();

        int hora24 = cal.get(Calendar.HOUR_OF_DAY);
        int min    = cal.get(Calendar.MINUTE);
        int sec    = cal.get(Calendar.SECOND);
        int ms     = cal.get(Calendar.MILLISECOND);
        int hora = hora24 * 10000000 + min * 100000 + sec * 1000 + ms;
        return hora;
    }
       
    /**
     * Crea una instancia de la clase inicializada con la hora del sistema.
     * 
     * @return                                  Hora del sistema
     */
    public static CHora horaSistema() {
        int hora_actual = getHora6();
        CHora h = new CHora(hora_actual);
        return h;
    }

    
    /** 
     * Crea una instancia de la clase, sin definir la hora.
     * La hora se puede establecer posteriormente mediante setHora().
     */
    public CHora() {
        init();
    }
    
    
    /** 
     * Crea una instancia de la clase.
     * Trata de averiguar el formato en el que se ha suministrado la hora.
     *
     * @param s_h                   String con la hora en cualquier formato:
     *                                  - FH_HHMMSS
     *                                  - FH_HHMM
     *                                  - FH_HH_MM_SS
     *                                  - FH_HH_MM
     */
    public CHora(String s_h) {
        
        init();
        
        // Intentamos averiguar el formato
        int formato = getFormato(s_h);
        setHora(s_h, formato);
    }
    
    
    /** 
     * Crea una instancia de la clase.
     *
     * @param s_h                   String con la hora en cualquier formato
     * @param formato               Formato utilizado para expresar la hora
     *   <ul>
     *     <li> FH_HHMMSS
     *     <li> FH_HHMM
     *     <li> FH_HH_MM_SS
     *     <li> FH_HH_MM
     *   </ul>
     */
    public CHora(String s_h, int formato) {
        
        init();
        setHora(s_h, formato);
    }

    
    /** 
     * Crea una instancia de la clase.
     *
     * @param h                     Hora en cualquier formato
     * @param formato               Formato utilizado para expresar la hora
     *   <ul>
     *     <li> FH_HHMMSS
     *     <li> FH_HHMM
     *     <li> FH_HH_MM_SS
     *     <li> FH_HH_MM
     *   </ul>

     */
    public CHora(int h, int formato) {
        init();
        setHora(h, formato);
    }
    
    
    /** 
     * Crea una instancia de la clase.
     *
     * @param h                     Hora en formato HHMMSS
     */
    public CHora(int h) {
        init();
        setHora(h);
    }
    
    
    /** 
     * Crea una instancia de la clase.
     *
     * @param h                     Hora en formato HHMMSS
     */
    public CHora(CHora h) {
        init();
        setHora(h);
    }
    
    
    /** 
     * Crea una instancia de la clase.
     *
     * @param hora                  Hora (0 a 23)
     * @param minutos               Minutos (0 a 59)
     * @param segundos              Segundos (0 a 59)
     */
    public CHora(int hora, int minutos, int segundos) {
        init();
        setHora(hora, minutos, segundos);
    }
    
    
    /**
     * Inicializa la clase.
     */
    protected void init() {
        hora = 0;
        minuto = 0;
        segundo = 0;
        
        hora_definida = false;
    }
    
    
    /**
     * Retorna un copia exacta de esta instancia de la clase.
     * 
     * @return                      Objeto de CHora con un clone de la instancia
     */
    @Override
    public CHora clone() {
        CHora c = new CHora(this);
        return c;
    }
    
    
    /**
     * Permite comparar esta hora con otra.
     * 
     * @param h2                        Hora con la que comparar
     * 
     * @return                          +1, si this > h2
     *                                   0, si this == h2
     *                                  -1, si this < h2
     */
    public int compareTo(CHora h2) {
        if (this.esAnterior(h2)) return +1;
        if (this.esPosterior(h2)) return -1;
        return 0;
    }
    
    
    /**
     * Dada una cadena con una hora, intenta averiguar el formato en el que está expresada.
     * 
     * @param s_h                           String con la hora
     * @return                              Formato en el que está la hora (FH_xxx)
     */
    public static int getFormato(String s_h) {
        
        if (Cadenas.vacio(s_h)) {
            return FH_DESCONOCIDO;
        }
        
        int pos_1 = s_h.indexOf(":");
        int pos_2 = s_h.lastIndexOf(":");
        
        int separadores = 0;

        if (pos_1 == -1) {
            separadores = 0;
        } else if (pos_1 == pos_2) {
            separadores = 1;
        } else {
            separadores = 2;
        }
                
        int l = s_h.length();
        
        switch (separadores) {
            
            // HMM, HHMM, HMMSS, HHMMSS
            case 0:
//                if (l < 3 || l > 6) return FH_DESCONOCIDO;
//                if (l == 3 || l == 4) return FH_HHMM;
//                if (l == 5 || l == 6) return FH_HHMMSS;
                if (l > 6) return FH_DESCONOCIDO;
                return FH_HHMMSS;
            
            // H:MM, HH:MM
            case 1:
                if (l < 4 || l > 5) return FH_DESCONOCIDO;
                return FH_HH_MM;
                
            // H:MM:SS, HH:MM:SS
            case 2:
                if (l < 7 || l > 8) return FH_DESCONOCIDO;
                return FH_HH_MM_SS;
        }
        
        return FH_DESCONOCIDO;
    }
    
    
    /**
     * Establece la hora.
     *
     * @param s_h                   Hora en formato HHMMSS
     */
    public void setHora(String s_h) {
        setHora(s_h, FH_HHMMSS);
    }
    

    /**
     * Establece la hora.
     *
     * @param s_hora                            Hora en cualquier formato
     * @param formato                           Formato utilizado para expresar la hora (CHora.FH_xxx)
     *   <ul>
     *     <li> FH_HHMMSS
     *     <li> FH_HHMM
     *     <li> FH_HH_MM_SS
     *     <li> FH_HH_MM
     *   </ul>
     */
    public void setHora(String s_hora, int formato) {
        
        /*
        int h;
        if (formato == FH_HH_MM_SS || formato == FH_HH_MM) {
            
            try {
                if (s_h == null || s_h.length() == 0) {
                    h = Comun.NO_DEFINIDO;
                } else {
                    if (formato == FH_HH_MM_SS) {
                        int tamaño = 6;
                        h = Comun.desformatearHora(s_h, tamaño);
                        setHora(h, FH_HHMMSS);
                    } else {
                        int tamaño = 4;
                        h = Comun.desformatearHora(s_h, tamaño);
                        setHora(h, FH_HHMM);
                    }
                }
            } catch (NumberFormatException ex) {
                desc_error = "La hora '" + s_h + "' es incorrecta";
                return;
            }
        } else {
            h = Comun.toEntero(s_h);   
            setHora(h, formato);
        }*/
        

        if (s_hora == null || s_hora.length() == 0) {
            hora_definida = false;
            return;
        }

        String s_h = "";
        String s_m = "";
        String s_s = "";

        switch (formato) {
            case FH_HHMMSS:
                while (s_hora.length() < 6) {
                    s_hora = "0" + s_hora;
                }
                s_h = Cadenas.primeros(s_hora, 2);
                s_m = Cadenas.miSubstringLong(s_hora, 2, 2);
                s_s = Cadenas.ultimos(s_hora, 2);
                break;

            case FH_HHMM:
                while (s_hora.length() < 4) {
                    s_hora = "0" + s_hora;
                }
                s_h = Cadenas.primeros(s_hora, 2);
                s_m = Cadenas.miSubstringLong(s_hora, 2, 2);
                s_s = "00";
                break;

            case FH_HH_MM_SS:
                while (s_hora.length() < 8) {
                    s_hora = "0" + s_hora;
                }
                s_h = Cadenas.primeros(s_hora, 2);
                s_m = Cadenas.miSubstringLong(s_hora, 3, 2);
                s_s = Cadenas.ultimos(s_hora, 2);
                break;

            case FH_HH_MM:
                while (s_hora.length() < 5) {
                    s_hora = "0" + s_hora;
                }
                s_h = Cadenas.primeros(s_hora, 2);
                s_m = Cadenas.miSubstringLong(s_hora, 3, 2);
                s_s = "00";
                break;
        }
        
        int n_h = Cadenas.toEntero(s_h);
        int n_m = Cadenas.toEntero(s_m);
        int n_s = Cadenas.toEntero(s_s);
        
        boolean hora_valida = esHoraValida(n_h, n_m, n_s);
        if (!hora_valida) {
            hora_definida = false;
            desc_error = "La hora '" + s_hora + "' es incorrecta o inexistente";
            return;
        }

        this.hora = n_h;
        this.minuto = n_m;
        this.segundo = n_s;
        hora_definida = true;
    }
    
    
    /**
     * Establece la hora.
     *
     * @param h                     Hora en formato HHMMSS
     */
    public void setHora(int h) {
        setHora(h, FH_HHMMSS);
    }
    
    
    /**
     * Establece la hora, a partir de un objeto de tipo hora.
     *
     * @param h                 
     */
    public void setHora(CHora h) {
        int nh = h.getHora();
        setHora(nh);
    }
    
    
    /** 
     * Crea una instancia de la clase, a partir de la hora, minutos y segundos.
     *
     * @param hora                  Hora (0 a 23)
     * @param minutos               Minutos (0 a 59)
     * @param segundos              Segundos (0 a 59)
     */
    public void setHora(int hora, int minutos, int segundos) {
        
        boolean hora_valida = esHoraValida(hora, minutos, segundos);
        if (!hora_valida) {
            hora_definida = false;
            desc_error = "La hora es incorrecta o inexistente";
            return;
        }

        this.hora = hora;
        this.minuto = minutos;
        this.segundo = segundos;
        hora_definida = true;
    }
    
    
    /**
     * Establece la hora.
     *
     * @param h                     Hora 
     * @param formato               Formato utilizado para expresar la hora
     *   <ul>
     *     <li> FH_HHMMSS
     *     <li> FH_HHMM
     *   </ul>
     */
    public void setHora(int h, int formato) {
        
        String s_h = Integer.toString(h);
        setHora(s_h, formato);        
    }
    

    /**
     * Establece la hora dada por un número de segundos.
     *
     * @param segundos              Hora suministrada en segundos
     */
    public void setHoraFromSegundos(int segundos) {
        
        int h = segundos / 3600;
        segundos = segundos % 3600;
        
        int m = segundos / 60;
        int s = segundos % 60;

        setHora(h, m, s);
    }
    
    

    /**
     * Verifica que una hora sea válida.
     *
     * @param hora                  Entero que representa la hora
     * @param minuto                Entero que representa los minutos
     * @param segundo               Entero que representa los segundos
     * @return                      'true' si la hora es válida, y 'false' si no
     */
    public static boolean esHoraValida(int hora, int minuto, int segundo) {

        if (hora < 0 || hora > 23) return false;
        if (minuto < 0 || minuto > 59) return false;
        if (segundo < 0 || segundo > 59) return false;

        return true;
    }

    
    /**
     * Comprueba que una hora sea correcta.
     *
     * @param h                     Hora en formato HHMMSS
     * @return                      'true' si la hora es correcta
     *                              'false' en caso contrario
     */
    public static boolean esHoraValida(int h) {
        
        CHora hora_obj = new CHora(h);
        int hora = hora_obj.getHora();
        int minuto = hora_obj.getMinutos();
        int segundo = hora_obj.getSegundos();

        boolean es_hora_valida = esHoraValida(hora, minuto, segundo);
        return es_hora_valida;
    }
    
    
    /**
     * Retorna el estado de si la hora está definida o no.
     *
     * @return                      'true' si la hora ha sido definida
     *                              'false' si no
     */
    public boolean horaDefinida() {
        return hora_definida;
    }
    
    
    /**
     * Retorna la hora en el formato solicitado.
     * Si la hora no está definida, retorna el valor Comun.NO_DEFINIDO_BD (0).
     *
     * @param formato               Formato utilizado para expresar la hora
     *   <ul>
     *     <li> FH_HHMMSS
     *     <li> FH_HHMM
     *   </ul>
     * @return                      Hora en formato HHMMSS
     */
    public int getHora(int formato) {
        
        if (!hora_definida) {
            return 0;
        }
        
        int h = 0;
        
        switch (formato) {
            case FH_HHMMSS:
                h = hora * 10000 + minuto * 100 + segundo;
                break;
            
            case FH_HHMM:
                h = hora * 100 + minuto;
                break;

        }
        
        return h;
    }
    
    
    /**
     * Retorna la hora en formato HHMMSS.
     *
     * @return                      Hora en formato HHMMSS
     */
    public int getHora() {
        int h = getHora(FH_HHMMSS);
        return h;
    }
    
    /**
     * Retorna la hora en formato HHMMSS. Si la hora no estuviera definida, retorna la hora mínima (Comun.HORA_MIN).
     *
     * @return                      Fecha en formato AAAAMMDD
     */
    public int getHoraOMin() {
        if (!horaDefinida()) {
            return HORA_MIN;
        }
        return getHora();
    }
    
    
    /**
     * Retorna la hora suministrada en formato HHMMSS. Si la hora fuera 'null' o no estuviera definida, retorna la hora mínima (Comun.HORA_MAX).
     *
     * @param hora                  Hora a retornar
     * @return                      Hora en formato HHMMSS
     */
    public static int getHoraOMin(CHora hora) {
        if (hora == null || !hora.horaDefinida()) {
            return HORA_MIN;
        }
        return hora.getHora();
    }
    
    
    /**
     * Retorna la hora en formato HHMMSS. Si la hora no estuviera definida, retorna la hora máxima (Comun.HORA_MAX).
     *
     * @return                      Fecha en formato AAAAMMDD
     */
    public int getHoraOMax() {
        if (!horaDefinida()) {
            return HORA_MAX;
        }
        return getHora();
    }

    
    /**
     * Retorna la hora suministrada en formato HHMMSS. Si la hora fuera 'null' o no estuviera definida, retorna la hora máxima (Comun.HORA_MAX).
     *
     * @param hora                  Hora a retornar
     * @return                      Hora en formato HHMMSS
     */
    public static int getHoraOMax(CHora hora) {
        if (hora == null || !hora.horaDefinida()) {
            return HORA_MAX;
        }
        return hora.getHora();
    }


    /**
     * Retorna la hora suministrada en formato HHMMSS. Si la hora fuera 'null' o no estuviera definida, retorna
     * Comun.NO_DEFINIDO.
     *
     * @param hora                  Hora a retornar
     * @return                      Hora en formato HHMMSS
     */
    public static int getHoraONoDefinido(CHora hora) {
        if (hora == null || !hora.horaDefinida()) {
            return 0;
        }
        return hora.getHora();
    }
    
    
    /**
     * Obtiene la parte de las horas.
     *
     * @return                      Hora
     */
    public int getHoras() {
        return hora;
    }

    
    /**
     * Obtiene la parte de los minutos.
     *
     * @return                      Minutos
     */
    public int getMinutos() {
        return minuto;
    }
    

    /**
     * Obtiene la parte de los segundos.
     *
     * @return                      Segundos
     */
    public int getSegundos() {
        return segundo;
    }
    
    
    /**
     * Pasa la hora a segundos
     *
     * @return                      Segundos
     */
    public int toSegundos() {
        int s = segundo + minuto * 60 + hora * 3600;
        return s;
    }
    
    
    /**
     * Comprueba si la hora es anterior a la suministrada.
     *
     * @param hora_2                Hora a comparar
     * @return                      'true' si la hora actual es anterior a hora_2
     *                              'false' si es posterior o igual
     */
    public boolean esAnterior(CHora hora_2) {
        if (getHora() < hora_2.getHora()) return true;
        return false;
    }
    
    
    /**
     * Comprueba si la fecha es posterior a la suministrada.
     *
     * @param hora_2                Hora a comparar
     * @return                      'true' si la hora actual es posterior a hora_2
     *                              'false' si es anterior o igual
     */
    public boolean esPosterior(CHora hora_2) {
        if (getHora() > hora_2.getHora()) return true;
        return false;
    }

    
    /**
     * Comprueba si la hora es la misma a la suministrada.
     *
     * @param hora_2                Hora a comparar
     * @return                      'true' si la hora coincide con hora_2
     *                              'false' si no coincide
     */
    public boolean esIgual(CHora hora_2) {
        if (getHora() == hora_2.getHora()) return true;
        return false;
    }
    
    
    /**
     * Obtiene la descripción del último error, si la hubiera.
     *
     * @return                      Descripción del último error ('null' si no hay)
     */
    public String getDescError() {
        return desc_error;
    }
    
    
    /**
     * Añade (o resta) un número de segundos a la fecha.
     *
     * @param num_segundos          Número de segundos a añadir (puede ser negativo)
     */
    public void addSegundos(int num_segundos) {
        
        int s = toSegundos();
        s += num_segundos;
        setHoraFromSegundos(s);
        
        
        /*
        segundo += num_segundos;
        if (segundo < 0) {
            segundo = Math.abs(segundo);
            int m = (segundo / 60) + 1;
            addMinutos(-m);
            segundo = segundo % 60;
        }
        
        if (segundo >= 60) {
            int m = segundo / 60;
            addMinutos(m);
            segundo = segundo % 60;            
        }*/
        
    }
    
    
    /**
     * Añade (o resta) un número de minutos a la fecha.
     *
     * @param num_minutos           Número de minutos a añadir (puede ser negativo)
     */
    public void addMinutos(int num_minutos) {
        
        int s = toSegundos();
        s += num_minutos * 60;
        setHoraFromSegundos(s);
        
        /*
        minuto += num_minutos;
        if (minuto < 0) {
            minuto = Math.abs(minuto);
            int h = (minuto / 60) + 1;
            addHoras(-h);
            minuto = minuto % 60;
        }
        
        if (minuto >= 60) {
            int h = minuto / 60;
            addHoras(h);
            minuto = minuto % 60;            
        }*/
    }
    
    
    /**
     * Añade (o resta) un número de horas a la fecha.
     *
     * @param num_horas             Número de horas a añadir (puede ser negativo)
     * 
     * @return                      Instancia actual
     */
    public CHora addHoras(int num_horas) {
        int s = toSegundos();
        s += num_horas * 3600;
        setHoraFromSegundos(s);
        
        return this;
    }
    
    
    /**
     * Obtiene un objeto de CHora con la suma de la hora actual y una hora suministrada
     *
     * @param hora_a_anadir         Objeto de CHora con la hora a añadir a la actual
     */
    public CHora addHora(CHora hora_a_anadir) {
        
        int s1 = this.toSegundos();
        int s2 = hora_a_anadir.toSegundos();
        
        int s = s1 + s2;
        
        CHora nueva_hora = new CHora();
        nueva_hora.setHoraFromSegundos(s);
        
        return nueva_hora;
    }
    
    
    /**
     * Obtiene un objeto de CHora la hora actual menos una hora suministrada.
     *
     * @param hora_a_restar         Objeto de CHora con la hora a restar de la actual
     */
    public CHora subHora(CHora hora_a_restar) {
        
        int s1 = this.toSegundos();
        int s2 = hora_a_restar.toSegundos();
        
        int s = s1 - s2;
        
        CHora nueva_hora = new CHora();
        nueva_hora.setHoraFromSegundos(s);
        
        return nueva_hora;
    }
    
    

    /**
     * Retorna la hora en un String con el formato especificado.
     * Si la hora no está definida, retorna "".
     *
     * @param formato                   Formato para expresar la hora
     *   <ul>
     *     <li> FH_HHMMSS
     *     <li> FH_HHMM
     *     <li> FH_HH_MM_SS
     *     <li> FH_HH_MM
     *   </ul>
     *
     * @return                          String con la hora actual AAAAMMDD
     */
    public String toString(int formato) {
        if (!hora_definida) return "";
        
        StringBuffer s = new StringBuffer("");
        String s_hora = Integer.toString(hora);
        String s_minuto = Integer.toString(minuto);
        String s_segundo = Integer.toString(segundo);
        
        while (s_hora.length() < 2) s_hora = "0" + s_hora;
        while (s_minuto.length() < 2) s_minuto = "0" + s_minuto;
        while (s_segundo.length() < 2) s_segundo = "0" + s_segundo;
        
        switch (formato) {
                
            case FH_HHMMSS:
                s.append(s_hora);
                s.append(s_minuto);
                s.append(s_segundo);
                break;
            
            case FH_HHMM:
                s.append(s_hora);
                s.append(s_minuto);
                break;

            case FH_HH_MM_SS:
                s.append(s_hora);
                s.append(SEPARADOR_HORA);
                s.append(s_minuto);
                s.append(SEPARADOR_HORA);
                s.append(s_segundo);
                break;

            case FH_HH_MM:
                s.append(s_hora);
                s.append(SEPARADOR_HORA);
                s.append(s_minuto);
                break;
        }
        
        return s.toString();
    }
    

    /**
     * Retorna la hora en formato para su presentación en pantalla, tipo HH:MM:SS.
     * Si la hora no está definida, retorna "".
     *
     * @return                          String con la hora actual en formato HH:MM:SS
     */
    public String toStringScr() {
        return toString(FH_HH_MM_SS);
    }
    
    
    /**
     * Retorna la hora en un String con formato HHMMSS.
     * Si la hora no está definida, retorna "".
     *
     * @return                          String con la hora actual HHMMSS
     */
    @Override
    public String toString() {
        return toString(FH_HHMMSS);
    }
}
