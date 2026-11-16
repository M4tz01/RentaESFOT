public class Vehiculo {
    private final String placa;
    private final String marca;
    private final String modelo;
    private final double tarifaBase;

    public Vehiculo(String placa, String marca, String modelo, double tarifaBase) {
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.tarifaBase = Entrada.noNegativo(tarifaBase, "tarifaBase");
    }

    public String getPlaca() { return placa; }

    public double calcularTarifaDiaria() { return tarifaBase; }

    public double calcularAlquiler(int dias) {
        if (dias <= 0) throw new IllegalArgumentException("Los días deben ser mayores a cero.");
        return calcularTarifaDiaria() * dias;
    }

    public double calcularAlquiler(int dias, double porcentajeDescuento) {
        if (!(porcentajeDescuento >= 0 && porcentajeDescuento <= 50))
            throw new IllegalArgumentException("El descuento debe estar entre 0 y 50 %.");
        return calcularAlquiler(dias) * (1 - porcentajeDescuento / 100);
    }

    @Override
    public String toString() {
        return String.format("%s %s %s | Tarifa diaria: $%.2f", placa, marca, modelo, calcularTarifaDiaria());
    }
}
