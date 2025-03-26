package org.JavaCar;

import java.util.ArrayList;

public abstract class Vehicle implements Llogable{
    //inici VARIABLES
    protected String matricula;
    protected String marca;
    protected String model;
    protected double preuBase;
    protected int anyMatriculacio;
    protected Motor motor;
    protected Roda[] rodes;
    protected EtiquetaAmbiental etiquetaAmbiental;
    //Fi VARIABLES


    public Vehicle(String matricula, String marca, String model, double preuBase, Motor motor, ArrayList<Roda> rodes) {
        this.matricula = matricula;
        this.marca = marca;
        this.model = model;
        this.preuBase = preuBase;
        this.anyMatriculacio = anyMatriculacio;
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
        String tipusCombustible = motor.getTipus().toLowerCase();

        // Lògica de càlcul de l'etiqueta ambiental basada en el tipus de motor i potència
        if (tipusCombustible.equals("electric") || tipusCombustible.equals("hidrogeno")) {
            return EtiquetaAmbiental.ZeroEmisions;
        } else if (tipusCombustible.equals("híbrido")) {
            return EtiquetaAmbiental.Eco;
        } else if (tipusCombustible.equals("gasolina")) {
            if (anyMatriculacio >= 2006) {
                return EtiquetaAmbiental.C;
            } else if (anyMatriculacio >= 2001) {
                return EtiquetaAmbiental.B;
            }
        } else if (tipusCombustible.equals("diésel")) {
            if (anyMatriculacio >= 2015) {
                return EtiquetaAmbiental.C;
            } else if (anyMatriculacio >= 2006) {
                return EtiquetaAmbiental.B;
            }
        }
        return EtiquetaAmbiental.SenseEtiqueta;
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
