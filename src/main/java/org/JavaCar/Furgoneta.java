package org.JavaCar;

import java.util.ArrayList;

public class Furgoneta extends Vehicle implements Llogable{
    double capacitatCarga;

    public Furgoneta(String matricula, String marca, String model, double preuBase, Motor motor, ArrayList<Roda> rodes, EtiquetaAmbiental etiquetaAmbiental) {
        super(matricula, marca, model, preuBase, motor, rodes, etiquetaAmbiental);
    }
}
