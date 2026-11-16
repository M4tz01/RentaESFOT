import java.util.Scanner;

public class Entrada {
    private static final Scanner teclado = new Scanner(System.in);

    public static String linea(String pregunta) {
        System.out.print(pregunta + ": ");
        return teclado.nextLine().trim();
    }

    public static String texto(String pregunta) {
        String valor = linea(pregunta);
        if (valor.isEmpty()) throw new IllegalArgumentException("Escriba un valor para " + pregunta + ".");
        return valor;
    }

    public static int entero(String pregunta) {
        try {
            return Integer.parseInt(texto(pregunta));
        } catch (NumberFormatException error) {
            throw new IllegalArgumentException(pregunta + " requiere un entero.");
        }
    }

    public static double decimal(String pregunta) {
        try {
            double valor = Double.parseDouble(texto(pregunta).replace(',', '.'));
            if (Double.isFinite(valor)) return valor;
        } catch (NumberFormatException error) {
            // El mismo mensaje sirve para texto y valores fuera de rango.
        }
        throw new IllegalArgumentException(pregunta + " requiere un número válido.");
    }

    public static boolean si(String pregunta) {
        String respuesta = texto(pregunta + " (s/n)");
        if (respuesta.equalsIgnoreCase("s")) return true;
        if (respuesta.equalsIgnoreCase("n")) return false;
        throw new IllegalArgumentException("Responda s o n.");
    }

    public static double noNegativo(double valor, String campo) {
        if (!Double.isFinite(valor) || valor < 0) throw new IllegalArgumentException("Valor inválido para " + campo + ".");
        return valor;
    }

    public static int noNegativo(int valor, String campo) {
        if (valor < 0) throw new IllegalArgumentException("Valor negativo para " + campo + ".");
        return valor;
    }
}
