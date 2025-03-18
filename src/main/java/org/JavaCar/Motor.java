package org.JavaCar;

public class Motor {
    //inici VARIABLES
    String tipus;
    int potencia;
    //Fi VARIABLES

    //Constructor
    public Motor(String tipus, int potencia) {
        this.tipus = tipus;
        this.potencia = potencia;
    }

    //GETTERS
    public String getTipus() {
        return tipus;
    }

    public int getPotencia() {
        return potencia;
    }
}
