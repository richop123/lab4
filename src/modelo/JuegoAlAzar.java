package modelo;

import excepciones.PalabraInvalidaException;

public final class JuegoAlAzar extends JuegoBase {

    private static final long serialVersionUID = 1L;
    private final AdministradorPalabras administrador;

    public JuegoAlAzar(AdministradorPalabras administrador) throws PalabraInvalidaException {
        super();
        if (administrador == null) {
            throw new PalabraInvalidaException("El administrador de palabras no puede ser nulo.");
        }
        this.administrador = administrador;
        establecerPalabraSecreta(elegirPalabra());
    }

    public AdministradorPalabras getAdministrador() {
        return this.administrador;
    }

    @Override
    protected String elegirPalabra() throws PalabraInvalidaException {
        return this.administrador.obtenerAleatoria();
    }

    @Override
    public Modalidad getModalidad() {
        return Modalidad.AL_AZAR;
    }
}
