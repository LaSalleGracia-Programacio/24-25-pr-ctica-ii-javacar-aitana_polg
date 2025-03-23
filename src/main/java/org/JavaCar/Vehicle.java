package org.JavaCar;

import java.util.ArrayList;

public abstract class Vehicle {
    //inici VARIABLES
    protected String matricula;
    protected String marca;
    protected String model;
    protected double preuBase;
    protected Motor motor;
    protected Roda[] rodes;
    protected EtiquetaAmbiental etiquetaAmbiental;
    //Fi VARIABLES


    public Vehicle(String matricula, String marca, String model, double preuBase, Motor motor, ArrayList<Roda> rodes) {
        this.matricula = matricula;
        this.marca = marca;
        this.model = model;
        this.preuBase = preuBase;
        this.motor = motor;
        this.rodes = rodes.toArray(new Roda[0]);
    }

    public double calcularPreu(int dies){
        double base = preuBase * dies;
        double extra = 0;

        if(this instanceof Moto) {
            Moto m = (Moto) this;
            if (m.getCilindrada() > 500){
                extra = 5 * dies;
            }
        } else if (this instanceof Furgoneta) {
            Furgoneta f = (Furgoneta) this;
            if(f.getCapacitatCarga() > 1000) {
                extra = 10 * dies;
            }
        }
        return base + extra;
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

    public Roda[] getRodes() {
        return rodes;
    }

    public EtiquetaAmbiental getEtiquetaAmbiental() {
        return etiquetaAmbiental;
    }
}
