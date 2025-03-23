package org.JavaCar;

import java.util.ArrayList;
import java.util.Arrays;

public class Furgoneta extends Vehicle implements Llogable{
    private double capacitatCarga;

    public Furgoneta(String matricula, String marca, String model, double preuBase, double capacitatCarga, Motor motor, Roda[] rodesArray) {
        super(matricula, marca, model, preuBase, motor, new ArrayList<>(Arrays.asList(rodesArray !=null ? rodesArray: new Roda[0])));

        this.capacitatCarga = capacitatCarga;
    }

    public double getCapacitatCarga() {
        return capacitatCarga;
    }
}
