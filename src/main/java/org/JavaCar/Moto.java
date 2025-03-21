package org.JavaCar;

import java.util.ArrayList;

public class Moto extends Vehicle implements Llogable{
    int cilindrada;

    public Moto(String matricula, String marca, String model, double preuBase, Motor motor, ArrayList<Roda> rodes, EtiquetaAmbiental etiquetaAmbiental) {
        super(matricula, marca, model, preuBase, motor, rodes, etiquetaAmbiental);
    }


    public int getCilindrada() {
        return cilindrada;
    }
}
