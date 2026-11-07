package modelo;

import java.text.Normalizer;
import java.util.Locale;

public final class UtilTexto {

    private static final String MARCADOR_ENYE = "\u0001";

    private UtilTexto() {
    }

    public static String normalizar(String texto) {
        if (texto == null) {
            return "";
        }
        String conMarcador = texto
                .replace('ñ', '\u0001')
                .replace('Ñ', '\u0001')
                .toUpperCase(Locale.ROOT);

        String descompuesto = Normalizer.normalize(conMarcador, Normalizer.Form.NFD);
        String sinTildes = descompuesto.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");

        return sinTildes.replace(MARCADOR_ENYE, "Ñ");
    }

    public static boolean esLetra(char c) {
        String normalizado = normalizar(String.valueOf(c));
        if (normalizado.length() != 1) {
            return false;
        }
        char ch = normalizado.charAt(0);
        return (ch >= 'A' && ch <= 'Z') || ch == 'Ñ';
    }

    public static boolean esPalabraValida(String palabra) {
        if (palabra == null || palabra.length() < 3 || palabra.length() > 15) {
            return false;
        }
        for (int i = 0; i < palabra.length(); i++) {
            if (!esLetra(palabra.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
