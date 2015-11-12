/**
 * InputFile.java
 *
 * Creado el 09-nov-2015, 13:29:07
 */

package es.pmp.pmplib.html.componentes.form;

/**
 * Definición de un elemento de formulario de tipo "FileUpload".
 * Este campo cargar uno o varios ficheros.
 * 
 * @author pmpreciado
 */
public class InputFile extends FormItemBase {

    private static final String TYPE = "file";
    
    /** Atributos de uso común para los input de tipo file */
    private static final String ATR_ACCEPT      = "accept";
    private static final String ATR_MULTIPLE    = "multiple";
    
    
    
    /**
     * Crea una instancia de la clase.
     */
    public InputFile() {
        super(TYPE);
    }
    
    
    /**
     * Establece el tipo de fichero a aceptar.
     * 
     * @param accept                            Tipo de fichero a aceptar
     *                                          Ejemplo: "audio/*"
     */
    public void setAccept(String accept) {
        
        setAtributo(ATR_ACCEPT, accept);
    }
    
    
    /**
     * Obtiene el tipo de fichero a aceptar.
     * 
     * @return                                  Tipo de fichero a aceptar
     */
    public String getAccept () {
        String accept = getValorAtributo(ATR_ACCEPT);
        return accept;
    }
    
    
    /**
     * Establece si el campo aceptará más de un fichero.
     * 
     * @param multiple                          'true' para que el campo acepte más de un fichero
     *                                          'false' para que sólo acepte uno
     */
    public void setMultiple(boolean multiple) {
        if (multiple) {
            setAtributo(ATR_MULTIPLE);
        } else {
            removeAtributo(ATR_MULTIPLE);
        }
    }
    
    
    /**
     * Comprueba si este campo aceptará más de un fichero.
     * 
     * @return                                  'true' si el campo aceptará más de un fichero
     *                                          'false' en caso contrario
     */
    public boolean getMultiple() {
        boolean multiple = super.existeAtributo(ATR_MULTIPLE);
        return multiple;
    }
}
