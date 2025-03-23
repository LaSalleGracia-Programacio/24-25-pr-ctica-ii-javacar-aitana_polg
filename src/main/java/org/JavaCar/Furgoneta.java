package org.JavaCar;

import java.util.ArrayList;
import java.util.Arrays;

public class Furgoneta extends Vehicle implements Llogable{
    private double capacitatCarga;

    public Furgoneta(String matricula, String marca, String model, double preuBase, double capacitatCarga, Motor motor, Roda[] rodesArray) {
        super(matricula, marca, model, preuBase, motor, new ArrayList<>(Arrays.asList(rodesArray)));

        this.capacitatCarga = capacitatCarga;
    }

    public double getCapacitatCarga() {
        return capacitatCarga;
    }

    @Override
    public double calcularPreu(int dies) {
        double extra = (capacitatCarga > 1000) ? 10 * dies : 0;
        return super.calcularPreu(dies);
    }
}
