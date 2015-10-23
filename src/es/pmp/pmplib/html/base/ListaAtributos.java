/**
 * ListaAtributos.java
 *
 * Creado el 23-oct-2015, 10:20:05
 */

package es.pmp.pmplib.html.base;

import es.pmp.pmplib.Arrays;
import java.util.ArrayList;
import java.util.List;


/**
 * Clase para gestionar una lista de atributos aplicables a cualquier elemento.
 * Se puede hacer que una clase extienda de esta para incorporar listas de atributos.
 * Los atributos aquí utilizados son de tipo String.
 *
 * @author pmpreciado
 */
public class ListaAtributos {

    
    /** Carácter separador predeterminado */
    public static final String SEPARADOR = ", ";
    
    /** Símbolo igual */
    public static final String SIMBOLO_IGUAL = "=";
    
    /** Carácter encerrador */
    public static final String CARACTER_ENCERRADOR = "\"";
    
    /** Prefijo DATA usado para los atributos personalizados de los elementos del DOM en HTML5 */
    public static final String PREFIJO_DATA = "data-";
    
    

    public class CAtributo {

        /** Nombre del atributo */
        protected String nombre;

        /** Valor */
        protected String valor;


        /**
         * Crea un atributo.
         *
         * @param nombre                        Nombre del atributo
         * @param valor                         Valor
         */
        public CAtributo(String nombre, String valor) {
            this.nombre = nombre;
            this.valor = valor;
        }

        /**
         * Crea un atributo sin valor definido.
         *
         * @param nombre                        Nombre del atributo
         */
        public CAtributo(String nombre) {
            this.nombre = nombre;
            this.valor = null;
        }

        /**
         * Obtiene el nombre del atributo.
         *
         * @return                              Nombre del atributo
         */
        public String getNombre() {
            return nombre;
        }


        /**
         * Obtiene el valor del atributo.
         *
         * @return                              Valor del atributo
         */
        public String getValor() {
            return valor;
        }


        /**
         * Obtiene una cadena del tipo nombre="valor" con el nombre y el valor del atributo.
         * Si el atributo no tiene valor, solo obtiene el nombre.
         * Se puede especificar el carácter utilizado como símbolo de igual (=) y para encerrar los valores (por defecto, comillas)
         *
         * @param simbolo_igual                 Caracter utilizado como símbolo de igual (=)
         * @param caracter_encerrador           Caracter utilizado para encerrar los valores
         *
         * @return                              Cadena
         */
        public String toString(String simbolo_igual, String caracter_encerrador) {
            if (valor == null) return nombre;
            String cadena = nombre + simbolo_igual + caracter_encerrador + valor + caracter_encerrador;
            return cadena;
        }


        /**
         * Obtiene una cadena del tipo nombre="valor" con el nombre y el valor del atributo.
         * Si el atributo no tiene valor, solo obtiene el nombre.
         * Se puede especificar el carácter utilizado como símbolo de igual (=)
         *
         * @param simbolo_igual                 Caracter utilizado como símbolo de igual (=)
         *
         * @return                              Cadena
         */
        public String toString(String simbolo_igual) {
            return toString("=", "\"");
        }


        /**
         * Obtiene una cadena del tipo nombre="valor" con el nombre y el valor del atributo.
         * Si el atributo no tiene valor, solo obtiene el nombre.
         *
         * @return                              Cadena
         */
        @Override
        public String toString() {
            return toString("=");
        }

    }

    /** Carácter (o cadena) para separar los elementos de la lista (por defecto, la coma) */
    protected String separador;

    /** Carácter usado como símbolo igual (por defecto, el símbolo '=') */
    protected String simbolo_igual;

    /** Carácter usado para encerrar los valores (por defecto, comillas dobles) */
    protected String caracter_encerrador;


    /** Lista con los atributos */
    protected List <CAtributo> l_atributos;



    /**
     * Crea la instancia de la clase.
     */
    public ListaAtributos() {
        l_atributos = new ArrayList();
        this.separador = SEPARADOR;
        this.simbolo_igual = SIMBOLO_IGUAL;
        this.caracter_encerrador = CARACTER_ENCERRADOR;
    }

    
    
    /**
     * Establece el carácter (o cadena) para separar los elementos de la lista (por defecto, la coma).
     *
     * @param separador                 Carácter usado como separador
     */
    public void setSeparador(String separador) {
        this.separador = separador;
    }


    /**
     * Establece el carácter usado como símbolo igual (=).
     *
     * @param simbolo_igual                 Carácter usado como símbolo igual
     */
    public void setSimboloIgual(String simbolo_igual) {
        this.simbolo_igual = simbolo_igual;
    }

    /**
     * Establece el carácter usado como encerrador de los valres (por defecto, las comillas).
     *
     * @param caracter_encerrador           Carácter usado como encerrador de los valores
     */
    public void setCaracterEncerrador(String caracter_encerrador) {
        this.caracter_encerrador = caracter_encerrador;
    }


    /**
     * Añade un atributo.
     *
     * @param atributo                      Atributo a añadir
     */
    public void add(CAtributo atributo) {
        l_atributos.add(atributo);
    }


    /**
     * Añade un atributo.
     *
     * @param nombre                        Nombre del atributo
     * @param valor                         Valor
     */
    public void add(String nombre, String valor) {
        CAtributo atributo = new CAtributo(nombre, valor);
        l_atributos.add(atributo);
    }


    /**
     * Añade un atributo.
     *
     * @param nombre                        Nombre del atributo
     * @param valor                         Valor
     */
    public void add(String nombre, int valor) {
        String s_valor = Integer.toString(valor);
        add(nombre, s_valor);
    }


    /**
     * Añade un atributo sin valor.
     *
     * @param nombre                        Nombre del atributo
     */
    public void add(String nombre) {
        add(nombre, "");
    }

    
    /**
     * Añade una lista de atributos a la instancia actual.
     * 
     * @param l_atributos                   Atributos a añadir
     */
    public void addAll(List <CAtributo> l_atributos) {
        l_atributos.addAll(l_atributos);
    }
    
    
    /**
     * Añade los atributos de la lista de atributos dada a la instancia actual.
     * 
     * @param lista_atributos               Lista de atributos a añadir a la instancia actual
     */
    public void add(ListaAtributos lista_atributos) {
        l_atributos.addAll(lista_atributos.getListaAtributos());
    }
    
    
    /**
     * Si alguno de los atributos a añadir ya existe, es reemplazado por el nuevo.
     * 
     * @param l_atributos                   Atributos a añadir
     */
    public void addAllReplace(List <CAtributo> l_atributos) {
        
        for (CAtributo atributo : l_atributos) {
            set(atributo);
        }
    }
    
    
    /**
     * Añade un atributo añadiendo automáticamente al nombre el prefijo DATA (HTML5).
     *
     * @param nombre                        Nombre del atributo
     * @param valor                         Valor
     */
    public void addData(String nombre, String valor) {
        add(PREFIJO_DATA + nombre, valor);
    }


    /**
     * Añade un atributo añadiendo automáticamente al nombre el prefijo DATA (HTML5).
     *
     * @param nombre                        Nombre del atributo
     * @param valor                         Valor
     */
    public void addData(String nombre, int valor) {
        add(PREFIJO_DATA + nombre, valor);
    }


    /**
     * Añade un atributo sin valor, añadiendo automáticamente al nombre el prefijo DATA (HTML5).
     *
     * @param nombre                        Nombre del atributo
     */
    public void addData(String nombre) {
        add(PREFIJO_DATA + nombre, "");
    }
    

    
    /**
     * Establece un atributo. Si ya existía, elimina el anterior.
     *
     * @param atributo                      Atributo a establecer
     */
    public void set(CAtributo atributo) {
        remove(atributo);
        add(atributo);
    }
    
    
    /**
     * Establece un atributo. Si ya existía, elimina el anterior.
     *
     * @param nombre                        Nombre del atributo
     * @param valor                         Valor
     */
    public void set(String nombre, String valor) {
        remove(nombre);
        add(nombre, valor);
    }


    /**
     * Establece un atributo. Si ya existía, elimina el anterior.
     *
     * @param nombre                        Nombre del atributo
     * @param valor                         Valor
     */
    public void set(String nombre, int valor) {
        remove(nombre);
        add(nombre, valor);
    }


    /**
     * Establece un atributo sin valor. Si ya existía, elimina el anterior.
     *
     * @param nombre                        Nombre del atributo
     */
    public void set(String nombre) {
        remove(nombre);
        add(nombre, "");
    }
    
    
    /**
     * Establece los atributos de la lista dada.
     * Elimina los atributos anteriormente establecidos.
     * 
     * @param l_atributos                   Atributos a establecer
     */
    public void setAll(List <CAtributo> l_atributos) {
        l_atributos.clear();
        this.l_atributos.addAll(l_atributos);
    }
    
    
    /**
     * Establece los atributos de la lista de atributos dada a la instancia actual.
     * Elimina los atributos anteriormente establecidos.
     * 
     * @param lista_atributos               Lista de atributos a añadir a la instancia actual
     */
    public void set(ListaAtributos lista_atributos) {
        l_atributos.clear();
        l_atributos.addAll(lista_atributos.getListaAtributos());
    }
    
    
    /**
     * Establece un atributo, añadiendo automáticamente al nombre el prefijo DATA (HTML5). Si ya existía, elimina el anterior.
     *
     * @param nombre                        Nombre del atributo
     * @param valor                         Valor
     */
    public void setAtributoData(String nombre, String valor) {
        set(PREFIJO_DATA +  nombre, valor);
    }


    /**
     * Establece un atributo, añadiendo automáticamente al nombre el prefijo DATA (HTML5). Si ya existía, elimina el anterior.
     *
     * @param nombre                        Nombre del atributo
     * @param valor                         Valor
     */
    public void setAtributoData(String nombre, int valor) {
        set(PREFIJO_DATA +  nombre, valor);
    }


    /**
     * Establece un atributo sin valor, añadiendo automáticamente al nombre el prefijo DATA (HTML5). Si ya existía, elimina el anterior.
     *
     * @param nombre                        Nombre del atributo
     */
    public void setAtributoData(String nombre) {
        set(PREFIJO_DATA +  nombre);
    }


    /**
     * Comprueba si ha sido definido el atributo dado por su nombre.
     * El nombre es insensible a mayúsculas y minúsculas.
     *
     * @param nombre                        Nombre del atributo
     * @return                              'true' si el atributo ha sido definido
     *                                      'false' en caso contrario
     */
    public boolean existe(String nombre) {
        for (int i = 0; i < Arrays.size(l_atributos); i++) {
            CAtributo atributo = l_atributos.get(i);
            if (atributo.getNombre().equalsIgnoreCase(nombre)) return true;
        }

        return false;
    }

    /**
     * Comprueba si ha sido definido el atributo dado por su nombre. Añade el prefijo DATA antes de realizar la búsqueda.
     * El nombre es insensible a mayúsculas y minúsculas.
     *
     * @param nombre                        Nombre del atributo
     * @return                              'true' si el atributo ha sido definido
     *                                      'false' en caso contrario
     */
    public boolean existeData(String nombre) {
        return existe(PREFIJO_DATA + nombre);
    }
    
    
    /**
     * Obtiene un atributo dado por su nombre.
     * El nombre es insensible a mayúsculas y minúsculas.
     *
     * @param nombre                        Nombre del atributo
     * @return                              Valor
     *                                      'null' si no existe el atributo
     */
    public CAtributo get(String nombre) {
        for (int i = 0; i < Arrays.size(l_atributos); i++) {
            CAtributo atributo = l_atributos.get(i);
            if (atributo.getNombre().equalsIgnoreCase(nombre)) {
                return atributo;
            }
        }

        return null;
    }
    
    
    /**
     * Obtiene un atributo dado por su nombre. Añade el prefijo DATA al atributo a obtener.
     * El nombre es insensible a mayúsculas y minúsculas.
     *
     * @param nombre                        Nombre del atributo
     * @return                              Valor
     *                                      'null' si no existe el atributo
     */
    public CAtributo getData(String nombre) {
        return get(PREFIJO_DATA + nombre);
    }

    
    /**
     * Obtiene el valor de un atributo dado por su nombre.
     * El nombre es insensible a mayúsculas y minúsculas.
     *
     * @param nombre                        Nombre del atributo
     * @return                              Valor
     *                                      'null' si no existe el atributo
     */
    public String getValor(String nombre) {
        CAtributo atributo = get(nombre);
        if (atributo == null) {
            return null;
        }
        
        return atributo.getValor();
    }

    /**
     * Obtiene el valor de un atributo dado por su nombre. Añade el prefijo DATA al atributo a obtener.
     * El nombre es insensible a mayúsculas y minúsculas.
     *
     * @param nombre                        Nombre del atributo
     * @return                              Valor
     *                                      'null' si no existe el atributo
     */
    public String getValorData(String nombre) {
        return getValor(PREFIJO_DATA + nombre);
    }

    
    /**
     * Elimina un atributo dado.
     *
     * @param atributo                      Atributo a eliminar
     */
    public void remove(CAtributo atributo) {
        for (int i = 0; i < Arrays.size(l_atributos); i++) {
            CAtributo a = l_atributos.get(i);
            if (a.getNombre().equalsIgnoreCase(atributo.nombre)) {
                l_atributos.remove(i);
            }
        }
    }
    
    
    /**
     * Elimina un atributo dado por su nombre.
     *
     * @param nombre                        Nombre del atributo
     */
    public void remove(String nombre) {
        for (int i = 0; i < Arrays.size(l_atributos); i++) {
            CAtributo atributo = l_atributos.get(i);
            if (atributo.getNombre().equalsIgnoreCase(nombre)) {
                l_atributos.remove(i);
            }
        }
    }
    
    
    /**
     * Elimina un atributo dado por su nombre. Añade el prefijo DATA al atributo a borrar.
     *
     * @param nombre                        Nombre del atributo
     */
    public void removeData(String nombre) {
        remove(PREFIJO_DATA + nombre);
    }
    
    
    /**
     * Elimina todos los atributos.
     */
    public void clear() {
        l_atributos.clear();
    }


    /**
     * Obtiene una lista con elementos de tipo CAtributo, con todos los atributos establecidos.
     * 
     * @return                                  Lista con elementos de tipo CAtributo
     */
    public List <CAtributo> getListaAtributos() {
        return l_atributos;
    }
    
    
    
    /**
     * Obtiene en una cadena la lista de atributos.
     * La lista va separada por el carácter predeterminado.
     *
     * @return                              Cadena con la lista de atributos
     */
    public String getCadenaAtributos() {

        StringBuilder s = new StringBuilder();
        for (int i = 0; i < Arrays.size(l_atributos); i++) {
            if (i > 0) s.append(separador);

            CAtributo atributo = l_atributos.get(i);
            s.append(atributo.toString(simbolo_igual, caracter_encerrador));
        }

        return s.toString();
    }


    /**
     * Obtiene en una cadena la lista de atributos.
     *
     * @param separador                     Carácter (o cadena) para separar los elementos de la lista
     * @return                              Cadena con la lista de atributos
     */
    public String getCadenaAtributos(String separador) {

        StringBuilder s = new StringBuilder();
        for (int i = 0; i < Arrays.size(l_atributos); i++) {
            if (i > 0) s.append(separador);

            CAtributo atributo = l_atributos.get(i);
            s.append(atributo.toString());
        }

        return s.toString();
    }


    /**
     * Obtiene en una cadena la lista de atributos.
     * La lista va separada por el carácter predeterminado.
     *
     * @return                              Cadena con la lista de atributos
     */
    @Override
    public String toString() {
        return getCadenaAtributos();
    }
}
