package org.JavaCar;

public class Furgoneta extends Vehicle implements Llogable{
    double capacitatCarga;

    public Furgoneta(String matricula, String marca, String model, double preuBase, int motor, int rodes, String etiquetaAmbiental) {
        super(matricula, marca, model, preuBase, motor, rodes, etiquetaAmbiental);
    }
}
