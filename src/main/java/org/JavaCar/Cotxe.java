package org.JavaCar;

public class Cotxe extends Vehicle implements Llogable{
    int nombrePlaces;

    public Cotxe(String matricula, String marca, String model, double preuBase, String motor, int rodes, String etiquetaAmbiental) {
        super(matricula, marca, model, preuBase, motor, rodes, etiquetaAmbiental);
    }
}
