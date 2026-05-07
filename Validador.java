import java.util.Scanner;

// Clase auxiliar — métodos de lectura y validación
// Se mantiene separada del Main para organizar responsabilidades
public class Validador {

    // ─────────────────────────────────────────────────
    // MÉTODOS PRIVADOS DE VALIDACIÓN
    // Se definen primero porque los métodos públicos los usan
    // ─────────────────────────────────────────────────

    // Verifica si un String representa un número entero
    private static boolean esNumeroEntero(String texto) {
        if (texto == null || texto.isEmpty()) return false;

        // Si el primer carácter es un signo negativo, empezamos a verificar desde el segundo
        // charAt(0) devuelve el carácter en la posición 0
        int inicio = texto.charAt(0) == '-' ? 1 : 0;

        // Si el String solo tiene el signo y nada más, no es válido
        if (inicio == texto.length()) return false;

        // Recorremos cada carácter y verificamos que sea un dígito
        // Character.isDigit() devuelve true si el carácter está entre '0' y '9'
        for (int i = inicio; i < texto.length(); i++) {
            if (!Character.isDigit(texto.charAt(i))) {
                return false;  // encontramos un carácter que no es dígito → no es entero
            }
        }
        return true;
    }

    // Verifica si un String representa un número decimal
    private static boolean esNumeroDecimal(String texto) {
        if (texto == null || texto.isEmpty()) return false;

        // Mismo control de signo negativo que en esNumeroEntero()
        int inicio = texto.charAt(0) == '-' ? 1 : 0;

        if (inicio == texto.length()) return false;

        // Usamos un flag para controlar que el punto aparezca solo una vez
        boolean tuvoPunto = false;

        for (int i = inicio; i < texto.length(); i++) {
            char c = texto.charAt(i);

            if (c == '.') {
                // Si ya encontramos un punto antes, no es válido
                // "12.5.3" no es un número decimal
                if (tuvoPunto) return false;
                tuvoPunto = true;
            } else if (!Character.isDigit(c)) {
                // Si no es punto ni dígito, no es válido
                return false;
            }
        }

        // Si termina con punto sin decimales ("12.") tampoco es válido
        if (texto.charAt(texto.length() - 1) == '.') return false;

        return true;
    }


    // ─────────────────────────────────────────────────
    // MÉTODOS PÚBLICOS DE LECTURA
    // Usan los métodos privados de arriba para validar
    // ─────────────────────────────────────────────────

    // Lee la opción del menú — retorna -1 si no es un número entero
    // El default del switch en GestorProductos captura ese caso
    // Repasa: los Strings leídos con Scanner nunca vienen del pool
    public static int leerOpcion(Scanner scanner) {
        String entrada = scanner.nextLine().trim();
        if (!esNumeroEntero(entrada)) {
            return -1;
        }
        return Integer.parseInt(entrada);
    }

    // Lee un String no vacío desde consola
    // Repasa: trim() devuelve un nuevo String
    // si no reasignamos el resultado, el original no cambia (inmutabilidad)
    public static String leerTexto(Scanner scanner, String etiqueta) {
        String valor = "";
        while (valor.isEmpty()) {
            System.out.print(etiqueta);
            valor = scanner.nextLine().trim(); // trim() → nuevo String, reasignamos
            if (valor.isEmpty()) {
                System.out.println("El campo no puede estar vacío. Intentá de nuevo.");
            }
        }
        return valor;
    }

    // Lee un entero mayor o igual a cero desde consola
    // Leemos todo con nextLine() — sin problema de buffer
    public static int leerEnteroPositivo(Scanner scanner, String etiqueta) {
        int valor = -1;
        boolean valido = false;
        while (!valido) {
            System.out.print(etiqueta);
            String entrada = scanner.nextLine().trim();

            // Validamos antes de convertir con parseInt()
            // Si no validamos y la entrada no es número, parseInt() lanzaría una excepción
            if (!esNumeroEntero(entrada)) {
                System.out.println("Error: ingresá un número entero.");
            } else {
                valor = Integer.parseInt(entrada);
                if (valor < 0) {
                    System.out.println("El valor no puede ser negativo.");
                } else {
                    valido = true;
                }
            }
        }
        return valor;
    }

    // Lee un precio válido desde consola
    // Leemos todo con nextLine() — sin problema de buffer
    public static double leerPrecio(Scanner scanner, String etiqueta) {
        double precio = 0;
        boolean valido = false;
        while (!valido) {
            System.out.print(etiqueta);
            String entrada = scanner.nextLine().trim();

            // Validamos antes de convertir con parseDouble()
            if (!esNumeroDecimal(entrada)) {
                System.out.println("Error: ingresá un número válido.");
            } else {
                precio = Double.parseDouble(entrada);
                if (precio <= 0) {
                    System.out.println("El precio debe ser mayor a cero.");
                } else {
                    valido = true;
                }
            }
        }
        return precio;
    }
}