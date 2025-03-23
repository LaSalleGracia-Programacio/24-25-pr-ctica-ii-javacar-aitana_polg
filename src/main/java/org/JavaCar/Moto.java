package org.JavaCar;

import java.util.ArrayList;
import java.util.Arrays;

public class Moto extends Vehicle implements Llogable{
    private int cilindrada;

    public Moto(String matricula, String marca, String model, double preuBase,int cilindrada, Motor motor, Roda[] rodesArray) {
        super(matricula, marca, model, preuBase, motor, new ArrayList<>(Arrays.asList(rodesArray)));
    }

    public int getCilindrada() {
        return cilindrada;
    }

    @Override
    public double calcularPreu(int dies) {
        double extra = (cilindrada > 500) ? 5 * dies: 0;
        return super.calcularPreu(dies);
    }
}
