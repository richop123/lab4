package excepciones;

public class LetraRepetidaException extends AhorcadoException {
    private static final long serialVersionUID = 1L;
    private final char letra;

    public LetraRepetidaException(char letra, String mensaje) {
        super(mensaje);
        this.letra = letra;
    }

    public LetraRepetidaException(char letra) {
        this(letra, "La letra " + letra + " ya fue ingresada. Pruebe con otra.");
    }

    public LetraRepetidaException(String mensaje) {
        super(mensaje);
        this.letra = '\0';
    }

    public char getLetra() {
        return letra;
    }
}
