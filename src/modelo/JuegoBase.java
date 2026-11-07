package modelo;

import excepciones.LetraInvalidaException;
import excepciones.LetraRepetidaException;
import excepciones.PalabraInvalidaException;
import excepciones.PartidaTerminadaException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public abstract class JuegoBase implements JuegoAhorcado, Serializable {

    private static final long serialVersionUID = 1L;
    public static final int MAX_INTENTOS = 6;

    protected String palabraSecreta;
    protected char[] palabraMostrada;
    protected int intentosRestantes;
    protected List<Character> letrasAcertadas;
    protected List<Character> letrasErradas;
    protected EtapaAhorcado etapa;
    protected EstadoPartida estado;

    protected JuegoBase() {
        this.letrasAcertadas = new ArrayList<>();
        this.letrasErradas = new ArrayList<>();
        this.etapa = EtapaAhorcado.VACIO;
        this.estado = EstadoPartida.EN_CURSO;
        this.intentosRestantes = MAX_INTENTOS;
    }

    @Override
    public void establecerPalabraSecreta(String palabra) throws PalabraInvalidaException {
        if (!UtilTexto.esPalabraValida(palabra)) {
            throw new PalabraInvalidaException(
                    "La palabra debe tener entre 3 y 15 letras, sin espacios ni números.");
        }
        this.palabraSecreta = UtilTexto.normalizar(palabra);
        this.palabraMostrada = new char[this.palabraSecreta.length()];
        Arrays.fill(this.palabraMostrada, '_');
        this.intentosRestantes = MAX_INTENTOS;
        this.letrasAcertadas.clear();
        this.letrasErradas.clear();
        this.etapa = EtapaAhorcado.VACIO;
        this.estado = EstadoPartida.EN_CURSO;
    }

    @Override
    public ResultadoLetra ingresarLetra(String entrada)
            throws LetraInvalidaException, LetraRepetidaException, PartidaTerminadaException {

        if (this.estado != EstadoPartida.EN_CURSO) {
            throw new PartidaTerminadaException(
                    "La partida ya terminó. Presione «Jugar de nuevo».");
        }

        if (entrada == null || entrada.length() != 1 || !UtilTexto.esLetra(entrada.charAt(0))) {
            throw new LetraInvalidaException("Ingrese una única letra (A–Z o Ñ).");
        }

        char letra = UtilTexto.normalizar(entrada).charAt(0);

        if (this.letrasAcertadas.contains(letra) || this.letrasErradas.contains(letra)) {
            throw new LetraRepetidaException(
                    letra, "La letra " + letra + " ya fue ingresada. Pruebe con otra.");
        }

        ResultadoLetra resultado;
        if (this.palabraSecreta.indexOf(letra) >= 0) {
            this.letrasAcertadas.add(letra);
            revelarLetra(letra, 0);
            resultado = ResultadoLetra.ACIERTO;
        } else {
            this.letrasErradas.add(letra);
            this.intentosRestantes--;
            this.etapa = EtapaAhorcado.dePorErrores(this.letrasErradas.size());
            resultado = ResultadoLetra.ERROR;
        }

        if (haGanado(0)) {
            this.estado = EstadoPartida.GANADA;
        } else if (haPerdido()) {
            this.estado = EstadoPartida.PERDIDA;
        }

        return resultado;
    }

    @Override
    public void reiniciar() throws PalabraInvalidaException {
        establecerPalabraSecreta(elegirPalabra());
    }

    protected void revelarLetra(char letra, int posicion) {
        if (posicion >= this.palabraSecreta.length()) {
            return;
        }
        if (this.palabraSecreta.charAt(posicion) == letra) {
            this.palabraMostrada[posicion] = letra;
        }
        revelarLetra(letra, posicion + 1);
    }

    protected boolean haGanado(int posicion) {
        if (posicion >= this.palabraSecreta.length()) {
            return true;
        }
        if (this.palabraMostrada[posicion] != this.palabraSecreta.charAt(posicion)) {
            return false;
        }
        return haGanado(posicion + 1);
    }

    protected boolean haPerdido() {
        return this.intentosRestantes <= 0;
    }

    protected abstract String elegirPalabra() throws PalabraInvalidaException;

    @Override
    public abstract Modalidad getModalidad();

    @Override
    public String getPalabraMostrada() {
        if (this.palabraMostrada == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.palabraMostrada.length; i++) {
            if (i > 0) {
                sb.append(' ');
            }
            sb.append(this.palabraMostrada[i]);
        }
        return sb.toString();
    }

    @Override
    public int getIntentosRestantes() {
        return this.intentosRestantes;
    }

    @Override
    public List<Character> getLetrasAcertadas() {
        return Collections.unmodifiableList(new ArrayList<>(this.letrasAcertadas));
    }

    @Override
    public List<Character> getLetrasErradas() {
        return Collections.unmodifiableList(new ArrayList<>(this.letrasErradas));
    }

    @Override
    public EtapaAhorcado getEtapa() {
        return this.etapa;
    }

    @Override
    public EstadoPartida getEstado() {
        return this.estado;
    }

    @Override
    public String getPalabraSecreta() {
        return this.palabraSecreta;
    }
}
