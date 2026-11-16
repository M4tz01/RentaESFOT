import java.util.*;

public class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        ArrayList<Vehiculo> vehiculos = new ArrayList<>();
        HashSet<String> placas = new HashSet<>();
        while (true) {
            System.out.println("\n--- MENÚ RENTAESFOT ---");
            System.out.println("1. Registrar auto");
            System.out.println("2. Registrar camioneta");
            System.out.println("3. Mostrar vehículos");
            System.out.println("4. Cotizar alquiler");
            System.out.println("5. Mostrar estadísticas");
            System.out.println("6. Salir");
            try {
                switch (Entrada.entero("Opción")) {
                    case 1 -> {
                        registrar(vehiculos, placas, true);
                    }
                    case 2 -> {
                        registrar(vehiculos, placas, false);
                    }
                    case 3 -> {
                        comprobarLista(vehiculos);
                        for (Vehiculo vehiculo : vehiculos) System.out.println(vehiculo);
                    }
                    case 4 -> {
                        comprobarLista(vehiculos);
                        Vehiculo vehiculo = buscar(vehiculos, Entrada.texto("Placa"));
                        int dias = Entrada.entero("Días");
                        double total = Entrada.si("¿Aplica descuento?")
                                ? vehiculo.calcularAlquiler(dias, Entrada.decimal("Descuento (%)"))
                                : vehiculo.calcularAlquiler(dias);
                        System.out.printf("Total a pagar: $%.2f%n", total);
                    }
                    case 5 -> {
                        comprobarLista(vehiculos);
                        Vehiculo mayor = vehiculos.get(0);
                        double suma = 0;
                        for (Vehiculo v : vehiculos) {
                            suma += v.calcularTarifaDiaria();
                            if (v.calcularTarifaDiaria() > mayor.calcularTarifaDiaria()) mayor = v;
                        }
                        System.out.println("Vehículo con mayor tarifa: " + mayor);
                        System.out.printf("Promedio de tarifas: $%.2f%n", suma / vehiculos.size());
                    }
                    case 6 -> { return; }
                    default -> System.out.println("Opción no válida.");
                }
            } catch (IllegalArgumentException error) {
                System.out.println("Error: " + error.getMessage());
            } catch (NoSuchElementException fin) {
                return;
            }
        }
    }
    private static void registrar(ArrayList<Vehiculo> vehiculos, HashSet<String> placas, boolean esAuto) {
        String placa = Entrada.texto("Placa").toUpperCase(Locale.ROOT);
        if (placas.contains(placa)) throw new IllegalArgumentException("La placa ya está registrada.");
        String marca = Entrada.texto("Marca");
        String modelo = Entrada.texto("Modelo");
        double base = Entrada.decimal("Tarifa diaria base");
        Vehiculo nuevo;
        if (esAuto) {
            nuevo = new Auto(placa, marca, modelo, base, Entrada.entero("Puertas"), Entrada.si("¿Tiene aire acondicionado?"));
        } else {
            nuevo = new Camioneta(placa, marca, modelo, base, Entrada.decimal("Carga (t)"));
        }
        if (placas.add(placa)) {
            vehiculos.add(nuevo);
            System.out.println("Vehículo registrado.");
        } else {
            throw new IllegalArgumentException("La placa ya está registrada.");
        }
    }

    private static Vehiculo buscar(ArrayList<Vehiculo> vehiculos, String placa) {
        for (Vehiculo v : vehiculos) {
            if (v.getPlaca().equalsIgnoreCase(placa)) return v;
        }
        throw new IllegalArgumentException("La placa no existe.");
    }

    private static void comprobarLista(ArrayList<Vehiculo> vehiculos) {
        if (vehiculos.isEmpty()) throw new IllegalArgumentException("No hay vehículos registrados.");
    }
}
