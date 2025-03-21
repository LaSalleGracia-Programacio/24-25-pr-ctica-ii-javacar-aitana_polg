package org.JavaCar;

import java.util.ArrayList;

public class Furgoneta extends Vehicle implements Llogable{
    double capacitatCarga;

    public Furgoneta(String matricula, String marca, String model, double preuBase, double capacitatCarga, Motor motor, ArrayList<Roda> rodes) {
        super(matricula, marca, model, preuBase, motor, rodes);

        this.capacitatCarga = capacitatCarga;
    }

    public double getCapacitatCarga() {
        return capacitatCarga;
    }
}
