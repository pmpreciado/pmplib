///////////////////////////////////////////////////////////////
// FUNCIONES JAVASCRIPT DE PROPÓSITO GENERAL
////////////////////////////////////////////////////////////////



/**
 * Establece el foco en el campo dado
 *
 * @param id        Id. del campo que recibir el foco
 */
function setFocus(id) {
    fld = document.getElementById(id);
    if (fld === null) {
        return;
    }
    fld.focus();
}