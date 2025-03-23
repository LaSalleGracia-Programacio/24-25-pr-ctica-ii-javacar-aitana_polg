package org.JavaCar;

import java.util.ArrayList;
import java.util.Arrays;

public class Moto extends Vehicle implements Llogable{
    int cilindrada;

    public Moto(String matricula, String marca, String model, double preuBase,int cilindrada, Motor motor, Roda[] rodesArray) {
        super(matricula, marca, model, preuBase, motor, new ArrayList<>(Arrays.asList(rodesArray)));
    }


    public int getCilindrada() {
        return cilindrada;
    }
}
