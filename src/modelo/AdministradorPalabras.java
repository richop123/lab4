package modelo;

import excepciones.PalabraDuplicadaException;
import excepciones.PalabraInvalidaException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class AdministradorPalabras implements Serializable {

    private static final long serialVersionUID = 1L;
    private final List<String> palabras;
    private transient Random random;

    public AdministradorPalabras() {
        this.palabras = new ArrayList<>();
        this.random = new Random();
        cargarPalabrasIniciales();
    }

    private void cargarPalabrasIniciales() {
        String[] iniciales = {
            "PROGRAMACION", "POLIMORFISMO", "HERENCIA", "INTERFAZ", "EXCEPCION",
            "RECURSIVIDAD", "ABSTRACCION", "COMPILADOR", "ALGORITMO", "MONTAÑA",
            "PAÑUELO", "MURCIELAGO"
        };
        for (String palabra : iniciales) {
            this.palabras.add(UtilTexto.normalizar(palabra));
        }
    }

    public void agregar(String palabra) throws PalabraInvalidaException, PalabraDuplicadaException {
        if (!UtilTexto.esPalabraValida(palabra)) {
            throw new PalabraInvalidaException(
                    "La palabra debe tener entre 3 y 15 letras, sin espacios ni números.");
        }
        String normalizada = UtilTexto.normalizar(palabra);
        if (existe(normalizada, 0)) {
            throw new PalabraDuplicadaException(
                    "La palabra «" + normalizada + "» ya existe en la lista.");
        }
        this.palabras.add(normalizada);
    }

    public String obtenerAleatoria() throws PalabraInvalidaException {
        if (this.palabras.isEmpty()) {
            throw new PalabraInvalidaException("No hay palabras disponibles en el administrador.");
        }
        if (this.random == null) {
            this.random = new Random();
        }
        return this.palabras.get(this.random.nextInt(this.palabras.size()));
    }

    private boolean existe(String palabra, int indice) {
        if (indice >= this.palabras.size()) {
            return false;
        }
        if (this.palabras.get(indice).equals(palabra)) {
            return true;
        }
        return existe(palabra, indice + 1);
    }

    public int cantidad() {
        return this.palabras.size();
    }

    public List<String> getPalabras() {
        return Collections.unmodifiableList(new ArrayList<>(this.palabras));
    }
}
