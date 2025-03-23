package org.JavaCar;

import java.util.ArrayList;
import java.util.Arrays;

public class Cotxe extends Vehicle implements Llogable{
    int nombrePlaces;

    public Cotxe(String matricula, String marca, String model, double preuBase, int nombrePlaces,Motor motor, Roda[] rodesArray) {
        super(matricula, marca, model, preuBase, motor, new ArrayList<>(Arrays.asList(rodesArray)));
        this.nombrePlaces = nombrePlaces;
    }
}
