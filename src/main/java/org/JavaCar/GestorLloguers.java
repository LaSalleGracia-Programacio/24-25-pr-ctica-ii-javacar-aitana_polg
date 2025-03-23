package org.JavaCar;

import java.util.List;

public class GestorLloguers {
    int dies; // Nombre de dies que està llogat (Llogable)
    double preuMax;

    public static double calcularIngressosTotals(List<Vehicle> vehicles, int dies){
        double total = 0;

        for (Vehicle v : vehicles){
            total += v.calcularPreu(dies);
        }
        return total;
    }
    public void filtrarPerPreu(double preuMax){

    }
}
