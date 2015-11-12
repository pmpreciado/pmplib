/**
 * ListaAtributos.java
 *
 * Creado el 23-oct-2015, 10:20:05
 */

package es.pmp.pmplib.html.base;

import es.pmp.pmplib.Arrays;
import es.pmp.pmplib.Numeros;
import java.util.ArrayList;
import java.util.List;


/**
 * Clase para gestionar una lista de atributos aplicables a cualquier elemento.
 * Se puede hacer que una clase extienda de esta para incorporar listas de atributos.
 * Los atributos aquí utilizados son de tipo String. Se consideran case-insensitive, para las operaciones de búsqueda.
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
    
    
    /** Atributos de uso común */
    private static final String ATR_ID      = "id";
    private static final String ATR_CLASS   = "class";
    private static final String ATR_NAME    = "name";
    private static final String ATR_STYLE   = "style";
    private static final String ATR_VALUE   = "value";
    
    

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
            if (valor == null) {
                return nombre;
            }
            
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
     * Crea una instancia de la clase.
     */
    public ListaAtributos() {
        l_atributos = new ArrayList <> ();
        this.separador = SEPARADOR;
        this.simbolo_igual = SIMBOLO_IGUAL;
        this.caracter_encerrador = CARACTER_ENCERRADOR;
    }

    
    /**
     * Crea una instancia de la clase.
     * Es inicializada a partir de la lista de atributos dada.
     * 
     * @param lista_atributos                   Lista de atributos a partir de la cual se inicializará la instancia
     */
    public ListaAtributos(ListaAtributos lista_atributos) {
        
        l_atributos = new ArrayList();
        List <CAtributo> la = lista_atributos.getListaAtributos();
        l_atributos.addAll(la);
        
        this.separador = lista_atributos.separador;
        this.simbolo_igual = lista_atributos.simbolo_igual;
        this.caracter_encerrador = lista_atributos.caracter_encerrador;
    }
    
    
    /**
     * Establece el carácter (o cadena) para separar los elementos de la lista (por defecto, la coma).
     *
     * @param separador                         Carácter usado como separador
     */
    public void setSeparador(String separador) {
        this.separador = separador;
    }


    /**
     * Establece el carácter usado como símbolo igual (=).
     *
     * @param simbolo_igual                     Carácter usado como símbolo igual
     */
    public void setSimboloIgual(String simbolo_igual) {
        this.simbolo_igual = simbolo_igual;
    }

    /**
     * Establece el carácter usado como encerrador de los valres (por defecto, las comillas).
     *
     * @param caracter_encerrador               Carácter usado como encerrador de los valores
     */
    public void setCaracterEncerrador(String caracter_encerrador) {
        this.caracter_encerrador = caracter_encerrador;
    }


    /**
     * Añade un atributo.
     *
     * @param atributo                          Atributo a añadir
     */
    public void addAtributo(CAtributo atributo) {
        l_atributos.add(atributo);
    }


    /**
     * Añade un atributo.
     *
     * @param nombre                            Nombre del atributo
     * @param valor                             Valor
     */
    public void addAtributo(String nombre, String valor) {
        CAtributo atributo = new CAtributo(nombre, valor);
        l_atributos.add(atributo);
    }


    /**
     * Añade un atributo.
     *
     * @param nombre                            Nombre del atributo
     * @param valor                             Valor
     */
    public void addAtributo(String nombre, int valor) {
        String s_valor = Integer.toString(valor);
        addAtributo(nombre, s_valor);
    }


    /**
     * Añade un atributo sin valor.
     *
     * @param nombre                            Nombre del atributo
     */
    public void addAtributo(String nombre) {
        addAtributo(nombre, null);
    }

    
    /**
     * Añade una lista de atributos a la instancia actual.
     * 
     * @param l_atributos                       Atributos a añadir
     */
    public void addAtributos(List <CAtributo> l_atributos) {
        l_atributos.addAll(l_atributos);
    }
    
    
    /**
     * Añade los atributos de la lista de atributos dada a la instancia actual.
     * 
     * @param lista_atributos                   Lista de atributos a añadir a la instancia actual
     */
    public void addAtributos(ListaAtributos lista_atributos) {
        l_atributos.addAll(lista_atributos.getListaAtributos());
    }
    
    
    /**
     * Añade na lista de atributos a la instancia actual.
     * Si alguno de los atributos a añadir ya existe, es reemplazado por el nuevo.
     * 
     * @param l_atributos                       Atributos a añadir
     */
    public void addAtributosReplace(List <CAtributo> l_atributos) {
        
        for (CAtributo atributo : l_atributos) {
            setAtributo(atributo);
        }
    }
    
    /**
     * Añade los atributos de la lista de atributos dada a la instancia actual.
     * Si alguno de los atributos a añadir ya existe, es reemplazado por el nuevo.
     * 
     * @param lista_atributos                   Atributos a añadir
     */
    public void addAtributosReplace(ListaAtributos lista_atributos) {
        
        addAtributosReplace(lista_atributos.getListaAtributos());
    }
    
    
    /**
     * Añade un atributo añadiendo automáticamente al nombre el prefijo DATA (HTML5).
     *
     * @param nombre                            Nombre del atributo
     * @param valor                             Valor
     */
    public void addAtributoData(String nombre, String valor) {
        addAtributo(PREFIJO_DATA + nombre, valor);
    }


    /**
     * Añade un atributo añadiendo automáticamente al nombre el prefijo DATA (HTML5).
     *
     * @param nombre                            Nombre del atributo
     * @param valor                             Valor
     */
    public void addAtributoData(String nombre, int valor) {
        addAtributo(PREFIJO_DATA + nombre, valor);
    }


    /**
     * Añade un atributo sin valor, añadiendo automáticamente al nombre el prefijo DATA (HTML5).
     *
     * @param nombre                            Nombre del atributo
     */
    public void addAtributoData(String nombre) {
        addAtributo(PREFIJO_DATA + nombre, null);
    }
    

    /**
     * Añade un atributo al comienzo de la lista de atributos.
     *
     * @param atributo                          Atributo a añadir
     */
    public void addAtributoAlComienzo(CAtributo atributo) {
        l_atributos.add(0, atributo);
    }


    /**
     * Añade un atributo al comienzo de la lista de atributos.
     *
     * @param nombre                            Nombre del atributo
     * @param valor                             Valor
     */
    public void addAtributoAlComienzo(String nombre, String valor) {
        CAtributo atributo = new CAtributo(nombre, valor);
        l_atributos.add(0, atributo);
    }


    /**
     * Añade un atributo al comienzo de la lista de atributos.
     *
     * @param nombre                            Nombre del atributo
     * @param valor                             Valor
     */
    public void addAtributoAlComienzo(String nombre, int valor) {
        String s_valor = Integer.toString(valor);
        addAtributoAlComienzo(nombre, s_valor);
    }


    /**
     * Añade un atributo sin valor al comienzo de la lista de atributos.
     *
     * @param nombre                            Nombre del atributo
     */
    public void addAtributoAlComienzo(String nombre) {
        addAtributoAlComienzo(nombre, null);
    }
    
    
    /**
     * Establece un atributo. Si ya existía, elimina el anterior.
     *
     * @param atributo                          Atributo a establecer
     */
    public void setAtributo(CAtributo atributo) {
        removeAtributo(atributo);
        addAtributo(atributo);
    }
    
    
    /**
     * Establece un atributo. Si ya existía, elimina el anterior.
     *
     * @param nombre                            Nombre del atributo
     * @param valor                             Valor
     */
    public void setAtributo(String nombre, String valor) {
        removeAtributo(nombre);
        addAtributo(nombre, valor);
    }


    /**
     * Establece un atributo. Si ya existía, elimina el anterior.
     *
     * @param nombre                            Nombre del atributo
     * @param valor                             Valor
     */
    public void setAtributo(String nombre, int valor) {
        removeAtributo(nombre);
        addAtributo(nombre, valor);
    }


    /**
     * Establece un atributo sin valor. Si ya existía, elimina el anterior.
     *
     * @param nombre                            Nombre del atributo
     */
    public void setAtributo(String nombre) {
        removeAtributo(nombre);
        addAtributo(nombre, null);
    }
    
    
    /**
     * Establece los atributos de la lista dada.
     * Elimina los atributos anteriormente establecidos.
     * 
     * @param l_atributos                       Atributos a establecer
     */
    public void setAtributos(List <CAtributo> l_atributos) {
        l_atributos.clear();
        this.l_atributos.addAll(l_atributos);
    }
    
    
    /**
     * Establece los atributos de la lista de atributos dada a la instancia actual.
     * Elimina los atributos anteriormente establecidos.
     * 
     * @param lista_atributos                   Lista de atributos a añadir a la instancia actual
     */
    public void setAtributos(ListaAtributos lista_atributos) {
        l_atributos.clear();
        l_atributos.addAll(lista_atributos.getListaAtributos());
    }
    
    
    /**
     * Establece un atributo, añadiendo automáticamente al nombre el prefijo DATA (HTML5). Si ya existía, elimina el anterior.
     *
     * @param nombre                            Nombre del atributo
     * @param valor                             Valor
     */
    public void setAtributoData(String nombre, String valor) {
        setAtributo(PREFIJO_DATA +  nombre, valor);
    }


    /**
     * Establece un atributo, añadiendo automáticamente al nombre el prefijo DATA (HTML5). Si ya existía, elimina el anterior.
     *
     * @param nombre                            Nombre del atributo
     * @param valor                             Valor
     */
    public void setAtributoData(String nombre, int valor) {
        setAtributo(PREFIJO_DATA +  nombre, valor);
    }


    /**
     * Establece un atributo sin valor, añadiendo automáticamente al nombre el prefijo DATA (HTML5). Si ya existía, elimina el anterior.
     *
     * @param nombre                            Nombre del atributo
     */
    public void setAtributoData(String nombre) {
        setAtributo(PREFIJO_DATA +  nombre);
    }

    
    /**
     * Función rápida para establecer el atributo "id".
     * 
     * @param valor                             Valor del atributo id
     *                                          'null' para eliminar el atributo
     */
    public void setAtributoId(String valor) {
        if (valor == null) {
            removeAtributo(ATR_ID);
        } else {
            setAtributo(ATR_ID, valor);
        }
    }
    
    
    /**
     * Función rápida para obtener el atributo "id".
     * 
     * @return                                  Valor del atributo "id"
     */
    public String getAtributoId() {
        return getValorAtributo(ATR_ID);
    }

    
    /**
     * Función rápida para establecer el atributo "id".
     * 
     * @param valor                             Valor del atributo id
     *                                          'null' para eliminar el atributo
     */
    public void setId(String valor) {
        setAtributoId(valor);
    }
    
    
    /**
     * Función rápida para obtener el atributo "id".
     * 
     * @return                                  Valor del atributo "id"
     */
    public String getId() {
        String id = getAtributoId();
        return id;
    }
    
    
    /**
     * Función rápida para establecer el atributo "class".
     * 
     * @param valor                             Valor del atributo "class"
     *                                          'null' para eliminar el atributo
     */
    public void setAtributoClass(String valor) {
        if (valor == null) {
            removeAtributo(ATR_CLASS);
        } else {
            setAtributo(ATR_CLASS, valor);
        }
    }
    
    
    /**
     * Función rápida para obtener el atributo "class".
     * 
     * @return                                  Valor del atributo "id"
     */
    public String geAtributoClass() {
        return getValorAtributo(ATR_CLASS);
    }
    
    
    /**
     * Función rápida para establecer el atributo "class".
     * 
     * @param valor                             Valor del atributo "class"
     *                                          'null' para eliminar el atributo
     */
    public void setClass(String valor) {
        setAtributoClass(valor);
    }
    
    
    
    /**
     * Función rápida para establecer el atributo "name".
     * 
     * @param valor                             Valor del atributo "name"
     *                                          'null' para eliminar el atributo
     */
    public void setAtributoName(String valor) {
        if (valor == null) {
            removeAtributo(ATR_NAME);
        } else {
            setAtributo(ATR_NAME, valor);
        }
    }
    
    
    /**
     * Función rápida para obtener el atributo "name".
     * 
     * @return                                  Valor del atributo "name"
     */
    public String getAtributoName() {
        return getValorAtributo(ATR_NAME);
    }
    
    
    /**
     * Función rápida para establecer el atributo "name".
     * 
     * @param valor                             Valor del atributo "name"
     *                                          'null' para eliminar el atributo
     */
    public void setName(String valor) {
        if (valor != null) {
            setAtributoName(valor);
        }
    }
    
    /**
     * Función rápida para obtener el atributo "name".
     * 
     * @return                                  Valor del atributo "name"
     */
    public String getName() {
        return getAtributoName();
    }
    
    
    /**
     * Función rápida para establecer el atributo "value".
     * 
     * @param valor                             Valor del atributo "value"
     *                                          'null' para eliminar el atributo
     */
    public void setAtributoValue(String valor) {
        if (valor != null) {
            setAtributo(ATR_VALUE, valor);
        }
    }
    
    
    /**
     * Función rápida para obtener el atributo "value".
     * 
     * @return                                  Valor del atributo "value"
     */
    public String getAtributoValue() {
        return getValorAtributo(ATR_VALUE);
    }
    
    /**
     * Función rápida para establecer el atributo "value".
     * 
     * @param valor                             Valor del atributo "value"
     *                                          'null' para eliminar el atributo
     */
    public void setValue(String valor) {
        setAtributoValue(valor);
    }
    
    
    /**
     * Función rápida para establecer el atributo "value".
     * 
     * @param valor                             Valor del atributo "value"
     */
    public void setValue(int valor) {
        String s_valor = Integer.toString(valor);
        setAtributoValue(s_valor);
    }
    
    
    /**
     * Función rápida para obtener el atributo "value".
     * 
     * @return                                  Valor del atributo "value"
     */
    public String getValue() {
        return getAtributoValue();
    }
    
    
    /**
     * Función rápida para obtener el atributo "style".
     * 
     * @return                                  Valor del atributo "style"
     */
    public String getAtributoStyle() {
        return getValorAtributo(ATR_STYLE);
    }
    
    
    /**
     * Función rápida para establecer el atributo "style" a partir de una cadena con el estilo.
     * 
     * @param style                             Estilo del objeto
     *                                          'null' para eliminar el atributo
     */
    public void setAtributoStyle(String style) {
        if (style == null) {
            removeAtributo(ATR_STYLE);
        } else {
            setAtributo(ATR_STYLE, style);
        }
    }
    
    
    /**
     * Función rápida para establecer el atributo "style" a partir de un objeto de la clase Style dado.
     * 
     * @param style                             Estilo del objeto
     *                                          'null' para eliminar el atributo
     */
    public void setAtributoStyle(Style style) {
        
        String s_style = null;
        if (style != null) {
            s_style = style.getCadenaAtributos();
        }
        setStyle(s_style);
    }
    
    
    /**
     * Función rápida para establecer el atributo "style" a partir de una cadena con el estilo.
     * 
     * @param style                             Estilo del objeto
     *                                          'null' para eliminar el atributo
     */
    public void setStyle(String style) {
        setAtributoStyle(style);
    }
    
    
    /**
     * Función rápida para establecer el atributo "style" a partir de un objeto de la clase Style dado.
     * 
     * @param style                             Estilo del objeto
     *                                          'null' para eliminar el atributo
     */
    public void setStyle(Style style) {
        setAtributoStyle(style);
    }
    
    
    /**
     * Función rápida para obtener el "style" de dentro de un objeto de clase Style.
     * El objeto Style se crea a partir del contenido del atributo "style", por lo que este debe estar definido.
     * 
     * @return                                  Estilo del objeto
     *                                          'null' si el atributo style no está definido
     */
    public Style getStyle() {
        
        String s_style = getAtributoStyle();
        if (s_style == null) {
            return null;
        }
        
        Style style = new Style(s_style);
        return style;
    }
    
    
    /**
     * Comprueba si ha sido definido el atributo dado por su nombre.
     * El nombre es insensible a mayúsculas y minúsculas.
     *
     * @param nombre                            Nombre del atributo
     * @return                                  'true' si el atributo ha sido definido
     *                                          'false' en caso contrario
     */
    public boolean existeAtributo(String nombre) {
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
     * @param nombre                            Nombre del atributo
     * @return                                  'true' si el atributo ha sido definido
     *                                          'false' en caso contrario
     */
    public boolean existeAtributoData(String nombre) {
        return existeAtributo(PREFIJO_DATA + nombre);
    }
    
    
    /**
     * Comprueba si la lista de atributos está vacía, es decir, si no hay ningún atributo asignado.
     * 
     * @return                                  'true' si la lista de atributos está vacía
     *                                          'false' en caso contrario
     */
    public boolean vacia() {
        return l_atributos.isEmpty();
    }
    
    
    /**
     * Obtiene un atributo dado por su nombre.
     * El nombre es insensible a mayúsculas y minúsculas.
     *
     * @param nombre                            Nombre del atributo
     * @return                                  Valor
                                                'null' si no existe el atributo
     */
    public CAtributo getAtributo(String nombre) {
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
     * @param nombre                            Nombre del atributo
     * 
     * @return                                  Valor
                                                'null' si no existe el atributo
     */
    public CAtributo getAtributoData(String nombre) {
        return getAtributo(PREFIJO_DATA + nombre);
    }

    
    /**
     * Obtiene el valor de un atributo dado por su nombre.
     * El nombre es insensible a mayúsculas y minúsculas.
     *
     * @param nombre                            Nombre del atributo
     * 
     * @return                                  Valor
                                                'null' si no existe el atributo
     */
    public String getValorAtributo(String nombre) {
        CAtributo atributo = getAtributo(nombre);
        if (atributo == null) {
            return null;
        }
        
        return atributo.getValor();
    }

    
    /**
     * Obtiene el valor de un atributo dado por su nombre.
     * El valor es retornado como un entero.
     * El nombre es insensible a mayúsculas y minúsculas.
     *
     * @param nombre                            Nombre del atributo
     * 
     * @return                                  Valor
                                                0 si no existe el atributo
     */
    public int getValorAtributoInt(String nombre) {
        int n = Numeros.toEntero(nombre, 0);
        return n;
    }
    
    
    /**
     * Obtiene el valor de un atributo dado por su nombre. Añade el prefijo DATA al atributo a obtener.
     * El nombre es insensible a mayúsculas y minúsculas.
     *
     * @param nombre                            Nombre del atributo
     * 
     * @return                                  Valor
                                                'null' si no existe el atributo
     */
    public String getValorAtributoData(String nombre) {
        return getValorAtributo(PREFIJO_DATA + nombre);
    }

    
    /**
     * Elimina un atributo dado.
     *
     * @param atributo                          Atributo a eliminar
     */
    public void removeAtributo(CAtributo atributo) {
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
    public void removeAtributo(String nombre) {
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
    public void removeAtributoData(String nombre) {
        removeAtributo(PREFIJO_DATA + nombre);
    }
    
    
    /**
     * Elimina todos los atributos.
     */
    public void clearAtributos() {
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
            if (i > 0) {
                s.append(separador);
            }

            CAtributo atributo = l_atributos.get(i);
            String s_atributo = atributo.toString(simbolo_igual, caracter_encerrador);
            s.append(s_atributo);
        }

        return s.toString();
    }


    /**
     * Obtiene en una cadena la lista de atributos.
     *
     * @param separador                     Carácter (o cadena) para separar los elementos de la lista
     * 
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
     * Combina los atributos de las dos listas dadas, generando una única lista.
     * Si el mismo atributo se repite en ambas listas, sólo se toma el de la lista preferente.
     * Si en ambas lista se encuentra el atributo "style", es combinado de forma específica.
     * 
     * @param lista_atributos_preferente        Lista de los atributos preferente. Si es 'null', se ignora
     * @param lista_atributos_secundaria        Lista de los atributos secundaria. Si es 'null', se ignora
     * 
     * @return                                  Lista de atributos combinada
     */
    public static ListaAtributos combinar(ListaAtributos lista_atributos_preferente, ListaAtributos lista_atributos_secundaria) {
        
        if (lista_atributos_preferente == null && lista_atributos_secundaria == null) {
            return new ListaAtributos();
        }
        if (lista_atributos_preferente == null) {
            return new ListaAtributos(lista_atributos_secundaria);
        }
        if (lista_atributos_secundaria == null) {
            return new ListaAtributos(lista_atributos_preferente);
        }
        
        ListaAtributos lista_atributos_combinada = new ListaAtributos(lista_atributos_preferente);
        
        List <CAtributo> la_secundarios = lista_atributos_secundaria.getListaAtributos();
        for (int i = 0; i < la_secundarios.size(); i++) {
            
            CAtributo atributo_secundario = la_secundarios.get(i);

            if (!lista_atributos_combinada.existeAtributo(atributo_secundario.nombre)) {
                lista_atributos_combinada.setAtributo(atributo_secundario);
            } else {
            
                // El atributo "style" lo tratamos de forma específica
                if (atributo_secundario.nombre.equalsIgnoreCase("style")) {
                    Style style_preferente = new Style(lista_atributos_preferente.getAtributoStyle());
                    Style style_secundario = new Style(lista_atributos_secundaria.getAtributoStyle());
                    
                    ListaAtributos lista_atributos_style_combinado = ListaAtributos.combinar(style_preferente, style_secundario);
                    String cadena_style_combinado = lista_atributos_style_combinado.getCadenaAtributos();
                    lista_atributos_combinada.setAtributoStyle(cadena_style_combinado);
                
                } else {
                    lista_atributos_combinada.setAtributo(atributo_secundario);
                }
            }
        }
        
        return lista_atributos_combinada;
    }
    
    
    
    /**
     * Combina los atributos de las listas dadas, generando una única lista.
     * Si el mismo atributo se repite en más de una lista, tiene preferencia el atributo de la lista más a la izquierda (según el orden del parámetro).
     * El atributo "style" es combinado de forma específica.
     * La combinación se realiza de forma recursiva mientras haya más de dos listas a combinar.
     * 
     * @param listas_atributos                  Listas de atributos a combinar. Si algún elemento es 'null', se ignora
     * 
     * @return                                  Lista de atributos combinada
     */
    public static ListaAtributos combinar(ListaAtributos ... listas_atributos) {
        
        ListaAtributos lista_atributos_combinada;
        
        if (listas_atributos.length == 0) {
            return null;
        } else if (listas_atributos.length == 1) {
            return listas_atributos[0];
        } else if (listas_atributos.length == 2) {
            lista_atributos_combinada = combinar(listas_atributos[0], listas_atributos[1]);
        } else {
            
            ListaAtributos lista_atributos_0 = listas_atributos[0];
            ListaAtributos [] lista_atributos_resto = new ListaAtributos[listas_atributos.length - 1];
            System.arraycopy(listas_atributos, 1, lista_atributos_resto, 0, lista_atributos_resto.length);
            
            ListaAtributos lista_atributos_temp = combinar(lista_atributos_resto);
            lista_atributos_combinada = combinar(lista_atributos_0, lista_atributos_temp);
        }
        
        return lista_atributos_combinada;
    }
    
    
    
    
    /**
     * Obtiene en una cadena la lista de atributos.
     * La lista va separada por el carácter predeterminado.
     *
     * @return                                  Cadena con la lista de atributos
     */
    @Override
    public String toString() {
        return getCadenaAtributos();
    }
}
