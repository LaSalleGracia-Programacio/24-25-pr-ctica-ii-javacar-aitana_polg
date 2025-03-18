package org.JavaCar;

public class Moto extends Vehicle implements Llogable{
    int cilindrada;

    public Moto(String matricula, String marca, String model, double preuBase, String motor, int rodes, String etiquetaAmbiental) {
        super(matricula, marca, model, preuBase, motor, rodes, etiquetaAmbiental);
    }
}
