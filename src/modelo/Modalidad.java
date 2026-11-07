package modelo;

public enum Modalidad {
    PALABRA_FIJA("Jugar con Palabra Fija"),
    AL_AZAR("Jugar con Palabra al Azar");

    private final String textoBoton;

    Modalidad(String textoBoton) {
        this.textoBoton = textoBoton;
    }

    public String getTextoBoton() {
        return textoBoton;
    }

    @Override
    public String toString() {
        return textoBoton;
    }
}
