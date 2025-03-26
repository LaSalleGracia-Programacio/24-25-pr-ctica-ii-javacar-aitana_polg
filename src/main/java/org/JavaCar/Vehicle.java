package org.JavaCar;

import java.util.ArrayList;

public abstract class Vehicle implements Llogable{
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
        Roda[] temp = rodes.toArray(new Roda[0]);
        this.rodes = (temp.length == 0) ? null : temp;
        this.etiquetaAmbiental = calcularEtiquetaAmbiental();
    }

    @Override
    public double calcularPreu(int dies){
        double base = preuBase * dies;
        double extra = 0;

        if(this instanceof Moto) {
            Moto m = (Moto) this;
            if (m.getCilindrada() >= 500){
                extra = 5 * dies;
            }
        } else if (this instanceof Furgoneta) {
            Furgoneta f = (Furgoneta) this;
            if(f.getCapacitatCarga() >= 1000) {
                extra = 10 * dies;
            }
        }
        return base + extra;
    }


    private EtiquetaAmbiental calcularEtiquetaAmbiental() {
        if (motor.getTipus().equalsIgnoreCase("electric")) {
            return EtiquetaAmbiental.ZeroEmisions;// Vehicle elèctric
        } else if (motor.getTipus().equalsIgnoreCase("híbrid")) {
            return EtiquetaAmbiental.Eco;   // Vehicle híbrid
        } else if (motor.getTipus().equalsIgnoreCase("diesel") && motor.getPotencia() <= 120) {
            return EtiquetaAmbiental.B;     // Dièsel moderat
        } else if (motor.getTipus().equalsIgnoreCase("diesel") || motor.getTipus().equalsIgnoreCase("gasolina")) {
            return EtiquetaAmbiental.C;     //  Vehicles més antics
        } else {
            return EtiquetaAmbiental.SenseEtiqueta; // Vehicles sense distintiu
        }
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
