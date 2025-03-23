package org.JavaCar;

import java.util.ArrayList;

public class Moto extends Vehicle implements Llogable{
    int cilindrada;

    public Moto(String matricula, String marca, String model, double preuBase,int cilindrada, Motor motor, ArrayList<Roda> rodes) {
        super(matricula, marca, model, preuBase, motor, rodes);
    }


    public int getCilindrada() {
        return cilindrada;
    }
}
