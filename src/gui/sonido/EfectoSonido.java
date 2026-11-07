package gui.sonido;

import java.util.Arrays;

public enum EfectoSonido {

    ACIERTO(new int[]{880}, new int[]{120}),
    ERROR(new int[]{220}, new int[]{250}),
    ALERTA(new int[]{440, 0, 440}, new int[]{80, 40, 80}),
    VICTORIA(new int[]{523, 659, 784}, new int[]{140, 140, 260}),
    DERROTA(new int[]{392, 330, 262}, new int[]{180, 180, 320});

    private final int[] frecuencias;
    private final int[] duracionesMs;

    EfectoSonido(int[] frecuencias, int[] duracionesMs) {
        this.frecuencias = frecuencias;
        this.duracionesMs = duracionesMs;
    }

    public int[] getFrecuencias() {
        return Arrays.copyOf(frecuencias, frecuencias.length);
    }

    public int[] getDuracionesMs() {
        return Arrays.copyOf(duracionesMs, duracionesMs.length);
    }
}
