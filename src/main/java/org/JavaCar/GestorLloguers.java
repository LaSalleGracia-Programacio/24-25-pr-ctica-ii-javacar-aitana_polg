package org.JavaCar;

import java.util.ArrayList;
import java.util.List;

public class GestorLloguers {
    int dies; // Nombre de dies que està llogat (Llogable)
    double preuMax;

    private List<Vehicle> vehicleList;

    public GestorLloguers(){
        this.vehicleList = new ArrayList<>();
    }

    public void afegirVehicle(Vehicle vehicle){
        vehicleList.add(vehicle);
    }

    public static double calcularIngressosTotals(List<Vehicle> vehicles, int dies){
        double total = 0;

        for (Vehicle v : vehicles){
            total += v.calcularPreu(dies);
        }
        return total;
    }

    public void filtrarPerPreu(double preuMax) {
        System.out.println("Vehicles amb preu inferior a " + preuMax + ":");

        for (Vehicle v : vehicleList) {
            if (v instanceof Llogable) {
                double preu = ((Llogable) v).calcularPreu(dies);
                if (preu <= preuMax) {
                    System.out.println(v.getMarca() + " " + v.getModel() + " - Preu: " + preu);
                }
            }
        }
    }
}
