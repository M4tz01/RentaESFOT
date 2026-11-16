public class Camioneta extends Vehiculo {
    private final double carga;

    public Camioneta(String placa, String marca, String modelo, double tarifaBase, double carga) {
        super(placa, marca, modelo, tarifaBase);
        this.carga = Entrada.noNegativo(carga, "carga");
    }

    @Override
    public double calcularTarifaDiaria() {
        return super.calcularTarifaDiaria() * (carga > 1.5 ? 1.15 : 1);
    }

    @Override
    public String toString() {
        return "[Camioneta] " + super.toString() + " | " + carga + " t";
    }
}
