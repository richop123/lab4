package modelo;

import excepciones.PalabraInvalidaException;

public final class JuegoPalabraFija extends JuegoBase {

    private static final long serialVersionUID = 1L;
    private String palabraConfigurada;

    public JuegoPalabraFija(String palabra) throws PalabraInvalidaException {
        super();
        this.palabraConfigurada = palabra;
        establecerPalabraSecreta(palabra);
    }

    public void cambiarPalabra(String nuevaPalabra) {
        this.palabraConfigurada = nuevaPalabra;
    }

    public String getPalabraConfigurada() {
        return this.palabraConfigurada;
    }

    @Override
    protected String elegirPalabra() {
        return this.palabraConfigurada;
    }

    @Override
    public Modalidad getModalidad() {
        return Modalidad.PALABRA_FIJA;
    }
}
