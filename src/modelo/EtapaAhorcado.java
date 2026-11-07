package modelo;

public enum EtapaAhorcado {
    VACIO,
    CABEZA,
    TRONCO,
    BRAZO_IZQ,
    BRAZO_DER,
    PIERNA_IZQ,
    PIERNA_DER;

    public static EtapaAhorcado dePorErrores(int errores) {
        EtapaAhorcado[] etapas = values();
        if (errores <= 0) {
            return VACIO;
        }
        if (errores >= etapas.length - 1) {
            return PIERNA_DER;
        }
        return etapas[errores];
    }
}
