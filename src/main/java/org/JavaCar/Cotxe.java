package org.JavaCar;

import java.util.ArrayList;

public class Cotxe extends Vehicle implements Llogable{
    int nombrePlaces;

    public Cotxe(String matricula, String marca, String model, double preuBase, int nombrePlaces,Motor motor, ArrayList<Roda> rodes) {
        super(matricula, marca, model, preuBase, motor, rodes);
        this.nombrePlaces = nombrePlaces;
    }
}
