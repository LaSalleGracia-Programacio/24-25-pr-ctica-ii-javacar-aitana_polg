package org.JavaCar;

import java.util.ArrayList;

public class Cotxe extends Vehicle implements Llogable{
    int nombrePlaces;

    public Cotxe(String matricula, String marca, String model, double preuBase, Motor motor, ArrayList<Roda> rodes, String etiquetaAmbiental) {
        super(matricula, marca, model, preuBase, motor, rodes, etiquetaAmbiental);
    }
}
