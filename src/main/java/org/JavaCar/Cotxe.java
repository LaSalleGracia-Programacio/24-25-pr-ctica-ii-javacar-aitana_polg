package org.JavaCar;

import java.util.ArrayList;

public class Cotxe extends Vehicle implements Llogable{
    int nombrePlaces;

    public Cotxe(String matricula, String marca, String model, double preuBase, int nombrePlaces,Motor motor, ArrayList<Roda> rodes, EtiquetaAmbiental etiquetaAmbiental) {
        super(matricula, marca, model, preuBase, motor, rodes, String.valueOf(etiquetaAmbiental));
        this.nombrePlaces = nombrePlaces;
    }
}
