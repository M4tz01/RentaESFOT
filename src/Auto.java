public class Auto extends Vehiculo {
    private final int puertas;
    private final boolean aire;

    public Auto(String placa, String marca, String modelo, double tarifaBase, int puertas, boolean aire) {
        super(placa, marca, modelo, tarifaBase);
        this.puertas = Entrada.noNegativo(puertas, "puertas");
        this.aire = aire;
    }

    @Override
    public double calcularTarifaDiaria() {
        return super.calcularTarifaDiaria() + (aire ? 10 : 0);
    }

    @Override
    public String toString() {
        return "[Auto] " + super.toString() + " | " + puertas + " puertas, " + (aire ? "con A/C" : "sin A/C");
    }
}
