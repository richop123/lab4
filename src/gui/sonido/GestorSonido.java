package gui.sonido;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.swing.SwingUtilities;

public class GestorSonido {

    private static final float SAMPLE_RATE = 44100f;
    private static final double VOLUMEN = 0.35;

    private boolean silenciado;
    private boolean audioDisponible;
    private Runnable callbackSinAudio;

    public GestorSonido() {
        this.silenciado = false;
        this.audioDisponible = verificarDisponibilidadInicial();
    }

    private boolean verificarDisponibilidadInicial() {
        AudioFormat formato = crearFormatoAudio();
        DataLine.Info info = new DataLine.Info(SourceDataLine.class, formato);
        return AudioSystem.isLineSupported(info);
    }

    private AudioFormat crearFormatoAudio() {
        return new AudioFormat(SAMPLE_RATE, 16, 1, true, false);
    }

    public void setCallbackSinAudio(Runnable callbackSinAudio) {
        this.callbackSinAudio = callbackSinAudio;
    }

    public boolean isSilenciado() {
        return silenciado;
    }

    public void setSilenciado(boolean silenciado) {
        this.silenciado = silenciado;
    }

    public boolean isAudioDisponible() {
        return audioDisponible;
    }

    public void reproducir(EfectoSonido efecto) {
        if (efecto == null || silenciado || !audioDisponible) {
            return;
        }
        Thread hiloAudio = new Thread(() -> reproducirEnHilo(efecto), "Hilo-Sonido-Ahorcado");
        hiloAudio.setDaemon(true);
        hiloAudio.start();
    }

    private void reproducirEnHilo(EfectoSonido efecto) {
        AudioFormat formato = crearFormatoAudio();
        DataLine.Info info = new DataLine.Info(SourceDataLine.class, formato);

        try (SourceDataLine linea = (SourceDataLine) AudioSystem.getLine(info)) {
            linea.open(formato);
            linea.start();

            int[] frecuencias = efecto.getFrecuencias();
            int[] duraciones = efecto.getDuracionesMs();

            for (int i = 0; i < frecuencias.length; i++) {
                if (silenciado) {
                    break;
                }
                byte[] bufferNota = generarOndaSenoidal(frecuencias[i], duraciones[i]);
                linea.write(bufferNota, 0, bufferNota.length);
            }

            linea.drain();
            linea.stop();
        } catch (LineUnavailableException | IllegalArgumentException ex) {
            audioDisponible = false;
            if (callbackSinAudio != null) {
                SwingUtilities.invokeLater(callbackSinAudio);
            }
        }
    }

    private byte[] generarOndaSenoidal(int frecuenciaHz, int duracionMs) {
        int totalMuestras = Math.max(1, (int) ((duracionMs / 1000.0) * SAMPLE_RATE));
        byte[] pcm = new byte[totalMuestras * 2];

        if (frecuenciaHz <= 0) {
            return pcm;
        }

        int muestrasFade = Math.min(totalMuestras / 5, (int) (SAMPLE_RATE * 0.012));

        for (int i = 0; i < totalMuestras; i++) {
            double angulo = 2.0 * Math.PI * i * frecuenciaHz / SAMPLE_RATE;
            double envolvente = 1.0;

            if (muestrasFade > 0) {
                if (i < muestrasFade) {
                    envolvente = (double) i / muestrasFade;
                } else if (i > totalMuestras - muestrasFade) {
                    envolvente = (double) (totalMuestras - i) / muestrasFade;
                }
            }

            short muestra = (short) (Math.sin(angulo) * Short.MAX_VALUE * VOLUMEN * envolvente);
            pcm[2 * i] = (byte) (muestra & 0xFF);
            pcm[2 * i + 1] = (byte) ((muestra >> 8) & 0xFF);
        }

        return pcm;
    }
}
