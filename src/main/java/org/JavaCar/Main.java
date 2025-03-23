package org.JavaCar;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Benvingut/da a JavaCar que vol fer?");
        Menu();
    }

    public static void Menu(){
        Scanner sc = new Scanner(System.in);
        System.out.println("1. Alquilar un Vehicle \n2. Gestionar els meus vehicles alquilats");
        try {
            int opcio = sc.nextInt();
            switch (opcio) {
                case 1:
                    Client("");
                    break;
                case 2:
                    Arrendatari("");
                    break;
                default:
                    System.out.println("Si us plau, escull entre les opcions que et proporcionem");
                    Menu();
            }
        } catch (InputMismatchException e){
            System.out.println("Si us plau, escull entre les opcions que et proporcionem");
            Menu();
        }
    }

    public static void Client(String usuari){
        Scanner sc = new Scanner(System.in);
        System.out.println("Que vols fer? \n1. Buscar Vehicle per temps d'alquiler \n2. Buscar Vehicles per preu d'alquiler");
        try {
            int opcio = sc.nextInt();
            switch (opcio) {
                case 1:
                    System.out.println("1");
                    break;
                case 2:
                    System.out.println("2");
                    break;
                default:
                    System.out.println("Si us plau, escull entre les opcions que et proporcionem");
                    Menu();
            }
        } catch (InputMismatchException e){
            System.out.println("Si us plau, escull entre les opcions que et proporcionem");
            Menu();
        }
    }

    public static void Arrendatari(String usuari){
        boolean rol = true;
        Scanner sc = new Scanner(System.in);
        System.out.println("Que vols fer? \n1.Calcular ingressos totals \n2. Afegir un vehicle per alquilar");
        try {
            int opcio = sc.nextInt();
            switch (opcio) {
                case 1:
                    if (usuari == ""){
                        Identificarse(rol);
                    } else {
                        System.out.println("Que tal?" + usuari);
                    }
                    break;
                case 2:
                    System.out.println("2");
                    break;
                default:
                    System.out.println("Si us plau, escull entre les opcions que et proporcionem");
                    Menu();
            }
        } catch (InputMismatchException e){
            System.out.println("Si us plau, escull entre les opcions que et proporcionem");
            Menu();
        }
    }

    public static void Identificarse(boolean rol){
        Scanner sc = new Scanner(System.in);
        System.out.println("Abans de comencar, hem de saber qui ets\nCom et dius?");
        String usuari = sc.next();
        System.out.println("Benvingut a JavaCar "+ usuari);
        if (rol == true) {
            Arrendatari(usuari);
        } else {
            Client(usuari);
        }
    }
}