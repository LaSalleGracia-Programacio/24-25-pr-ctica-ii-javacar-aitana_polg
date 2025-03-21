package org.JavaCar;

import java.util.ArrayList;

public abstract class Vehicle {
    //inici VARIABLES
    protected String matricula;
    protected String marca;
    protected String model;
    protected double preuBase;
    protected Motor motor;
    protected ArrayList <Roda> rodes = new ArrayList<>();
    protected String etiquetaAmbiental;
    //Fi VARIABLES


    public Vehicle(String matricula, String marca, String model, double preuBase, Motor motor, ArrayList<Roda> rodes, String etiquetaAmbiental) {
        this.matricula = matricula;
        this.marca = marca;
        this.model = model;
        this.preuBase = preuBase;
        this.motor = motor;
        this.rodes = rodes;
        this.etiquetaAmbiental = etiquetaAmbiental;
    }

    public String getMatricula() {
        return matricula;
    }

    public String getMarca() {
        return marca;
    }

    public String getModel() {
        return model;
    }

    public double getPreuBase() {
        return preuBase;
    }

    public Motor getMotor() {
        return motor;
    }

    public ArrayList<Roda> getRodes() {
        return rodes;
    }

    public String getEtiquetaAmbiental() {
        return etiquetaAmbiental;
    }


}
