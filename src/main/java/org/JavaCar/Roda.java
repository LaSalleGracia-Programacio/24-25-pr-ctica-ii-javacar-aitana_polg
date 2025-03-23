package org.JavaCar;

public class Roda {
    private String marca;
    double diametre;

    public Roda(String marca, double diametre) {
        this.marca = marca;
        this.diametre = diametre;
    }

    public String getMarca() {
        return marca;
    }

    public double getDiametre() {
        return diametre;
    }
}
