package org.JavaCar;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    //Variables globals
    static Scanner sc = new Scanner(System.in);
    private static String nomUsuariActual = ""; // Nom de l'usuari actual
    private static String passActual = ""; // Contrasenya actual de l'usuario
    private static boolean usuariAutenficat = false; // Saber si l'usuari està autentificat o no
    private static String[] passArrendataris = {"1234", "4321", "33213"}; //Contrasenyes per verificar que és arrendatari
    private static ArrayList<String[]> usuarisRegistrats = new ArrayList<>(); // Llista d'usuaris registrats (emmagatzema usuari i contrasenya)
    private static GestorLloguers gestorLloguers = new GestorLloguers();
    private static List<Vehicle> vehicles;

    public static void main(String[] args) {
        System.out.println(benvinguda());
        mostrarMenu();
        GestioMenu();
    }

    /**
     * Retorna el missatge de benvinguda
     *
     * @return String amb el text de benvinguda
     */
    public static String benvinguda() {
        return "---------------------------------------\n" +
                "               JavaCar\n" +
                "---------------------------------------";
    }

    /**
     * Mostra el menu principal amb les opcions disponibles per a l'usuari
     */
    public static void mostrarMenu() {
        System.out.println("\n-------------MENU PRINCIPAL------------");
        System.out.println("1. Llogar un vehicle");
        System.out.println("2. Gestionar els meus vehicles llogats");
        System.out.println("3. Sortir");
        System.out.println("4. Tancar Secció");
        System.out.println("---------------------------------------");
    }

    /**
     * Gestiona la opció seleccionada
     */
    public static void GestioMenu(){
        try {
            System.out.print("Escull una opció -> ");
            int opcio = sc.nextInt();
            switch (opcio) {
                case 1:
                    if (!usuariAutenficat) {
                        registre(sc);
                    } else {
                        if (esArrendatari(passActual)) {
                            System.out.println("La teva sessió està iniciada com a arrendatari. Si vols llogar un vehicle, primer t'has d'autentificar com a client.");
                            System.out.println("Tanca la sessió per canviar d'usuari.");
                        } else {
                            Client(nomUsuariActual);
                        }
                    }
                    break;
                case 2:
                    if (!usuariAutenficat) {
                        registre(sc);
                    } else {
                        if (esArrendatari(passActual)) {
                            Arrendatari(nomUsuariActual);
                        } else {
                            System.out.println("La teva sessió està iniciada com a client. Aquesta opció és per arrendataris.");
                            System.out.println("Tanca la sessió per canviar d'usuari.");
                        }
                    }
                case 3:
                    System.out.println("Gràcies per utilitzar JavaCar");
                    break;
                default:
                    System.out.println("ERROR: Opció no vàlida");
                    GestioMenu();
            }
        } catch (InputMismatchException e){
            System.out.println("Si us plau, escull entre les opcions que et proporcionem");
            GestioMenu();
        }
    }

    public static void Client(String usuari){
        mostrarMenuClient();
        try {
            int opcio = sc.nextInt();
            switch (opcio) {
                case 1:
                    filtrarPerTempsLloguer();
                case 2:
                    filtrarPerPreuLloguer();
                case 3:
                    mostrarMenu();
                    GestioMenu();
                    break;
                default:
                    System.out.println("ERROR: Opció no vàlida");
                    GestioMenu();
            }
        } catch (InputMismatchException e){
            System.out.println("Si us plau, escull entre les opcions que et proporcionem");
            GestioMenu();;
        }
    }

    /**
     * Mostra el menu client amb les opcions disponibles per a l'usuari
     */
    public static void mostrarMenuClient() {
        System.out.println("\n-------------MENU CLIENT------------");
        System.out.println("1. Filtrar vehicle per temps lloguer");
        System.out.println("2. Filtrar vehicle per preu lloguer");
        System.out.println("3. Tornar enrere");
        System.out.println("---------------------------------------");
    }

    /**
     * Metode per les opcions que pot fer l'arrendatari
     * @param usuari nom de l'usuari
     */
    public static void Arrendatari(String usuari) {
        boolean rol = true;
        mostrarMenuArrendatari();
        System.out.print("Escull una opció -> ");
        try {
            int opcio = sc.nextInt();
            switch (opcio) {
                case 1:
                    System.out.println("Has seleccionat l'opció 1");
                    calcularIngressosTotals();
                case 2:
                    afegirVehiclePerLlogar();
                case 3:
                    mostrarMenu();
                    GestioMenu();
                    break;
                default:
                    System.out.println("ERROR: Opció no vàlida");
                    GestioMenu();
            }
        } catch (InputMismatchException e){
            System.out.println("Si us plau, escull entre les opcions que et proporcionem");
            GestioMenu();
        }
    }

    /**
     * Mostra el menu arrendatari amb les opcions disponibles per a l'usuari
     */
    public static void mostrarMenuArrendatari() {
        System.out.println("\n-------------MENU ARRENDATARI------------");
        System.out.println("1. Calcular ingressos totals");
        System.out.println("2. Afegir un vehicle per llogar");
        System.out.println("3. Tornar enrere");
        System.out.println("---------------------------------------\n");
    }

    public static void afegirVehiclePerLlogar(){
        System.out.println("\n------ Afegir un nou vehicle ------");

        System.out.println("Introdueix la matricula:");
        String matricula = sc.next();

        System.out.println("Marca del Vehicle:");
        String marca = sc.next();

        System.out.println("Model del Vehicle:");
        String model = sc.next();

        System.out.println("Preu base per dia:");
        double preuBase = sc.nextDouble();

        System.out.println("Tipus de motor (electric, híbrid, diesel, gasolina):");
        String tipusMotor = sc.next();

        System.out.print("Potència del motor (CV): ");
        int potenciaMotor = sc.nextInt();

        Motor motor = new Motor(tipusMotor, potenciaMotor);

        System.out.println("Quin tipus de vehicle és? (cotxe/moto/furgoneta):");
        String tipusVehicle = sc.next().toLowerCase();

        Vehicle nouVehicle = null;

        if(tipusVehicle.equals("cotxe")) {
            System.out.println("Nombre de places: ");
            int places = sc.nextInt();
            nouVehicle = new Cotxe(matricula, marca, model, preuBase, places, motor, new Roda[4]);
        } else if (tipusVehicle.equals("moto")) {
            System.out.println("Cilindrada: ");
            int cilindrada = sc.nextInt();
            nouVehicle = new Moto(matricula, marca, model, preuBase, cilindrada, motor, new Roda[2]);
        } else if (tipusVehicle.equals("furgoneta")) {
            System.out.print("Capacitat de càrrega (kg): ");
            double capacitatCarga = sc.nextDouble();
            nouVehicle = new Furgoneta(matricula, marca, model, preuBase, capacitatCarga, motor, new Roda[4]);
        } else {
            System.out.println("Tipus de vehicle no vàlid.");
            return;
        }
        gestorLloguers.afegirVehicle(nouVehicle);

        System.out.println("Vehicle afegit correctament");

        mostrarMenu();
        GestioMenu();
    }

    public static void filtrarPerTempsLloguer() {
        System.out.print("Introdueix el nombre de dies mínim de lloguer: ");
        int dies = sc.nextInt();

        gestorLloguers.filtrarPerTemps(dies);

        mostrarMenu();
        GestioMenu();
    }

    public static void filtrarPerPreuLloguer() {
        System.out.print("Introdueix el preu màxim que vols pagar per dia: ");
        double preuMax = sc.nextDouble();

        System.out.print("Introdueix el nombre de dies del lloguer: ");
        int dies = sc.nextInt();

        gestorLloguers.filtrarPerPreu(preuMax, dies);

        mostrarMenu();
        GestioMenu();
    }

    public static void calcularIngressosTotals() {
        if (gestorLloguers.getVehicleList().isEmpty()) {
            System.out.println("No hi ha vehicles registrats per calcular ingressos.");
            mostrarMenu();
            GestioMenu();
            return;
        }

        System.out.print("Introdueix el nombre de dies per calcular els ingressos: ");
        int dies = sc.nextInt();

        if (dies <= 0) {
            System.out.println("El nombre de dies ha de ser positiu.");
            return;
        }

        double totalIngressos = gestorLloguers.calcularIngressosTotals(vehicles ,dies);
        System.out.println("Ingressos totals per " + dies + " dies: " + totalIngressos + "€");
    }


    /**
     * Metode per la identificació de la persona
     * @param sc L'objecte Scanner per llegir l'entrada de l'usuari.
     */
    public static void registre(Scanner sc){
        boolean rol = false;
        String usuari = "";
        String pass = "";


        System.out.println("\n-------INICIAR SECCIÓ/REGISTRE-------");
        System.out.println("Ara deurà de contestar a unes preguntes");

        //Demanar el nom de l'usuari
        System.out.println("Com et dius?");
        usuari = sc.next();

        boolean existeix = false;
        for (String[] u : usuarisRegistrats) {
            if (u[0].equals(usuari)) {
                existeix = true;
                break;
            }
        }
        if(existeix){
            System.out.println("L'usuari ja està registrat. Si us plau, autentifica't");
            if (autentificar(sc)){
                boolean esArrendetari = false;
                for (String passCandidate : passArrendataris) {
                    if (passCandidate.equals(passActual)) {
                        esArrendetari = true;
                        break;
                    }
                }
                if (esArrendetari) {
                    Arrendatari(usuari);
                } else {
                    Client(usuari);
                }
            }
        } else {
            //Demanar una contrasenya de mínim 4 digits
            System.out.print("Introdueix una contrasenya (mínim 4 digits): ");
            pass = sc.next();
            if (pass.length() <= 3) {
                System.out.println("La contrasenya a de tindre mínim 4 digits");
                System.out.print("Introdueix una contrasenya (mínim 4 digits): ");
                pass = sc.next();
            }

            String[] nouUsuari = {usuari, pass};
            usuarisRegistrats.add(nouUsuari);
            //Benvinguda a l'usuari
            System.out.println("Benvingut a JavaCar " + usuari);

            boolean esArrendetari = false;
            for (String passCandidate : passArrendataris) {
                if (passCandidate.equals(pass)) {
                    esArrendetari = true;
                    break;
                }
            }
            if (esArrendetari) {
                Arrendatari(usuari);
            } else {
                Client(usuari);
            }
        }
    }

    /**
     * Comprova si la contrasenya pertany al rol arrendatari.
     */
    public static boolean esArrendatari(String pass) {
        for (String passCandidate : passArrendataris) {
            if (passCandidate.equals(pass)) {
                return true;
            }
        }
        return false;
    }


    /**
     * Metode per autentificar-se
     *
     * @param sc L'objecte Scanner per llegir l'entrada de l'usuari.
     * @return true si l'autenticació es exitosa, false en cas contrari
     */
    public static boolean autentificar(Scanner sc) {
        // Declaració de variables
        String nombreIntroduit;
        String passIntroduit;

        if (usuarisRegistrats.isEmpty()) {
            System.out.println("ERROR: No estàs registrat. Per favor, regístrat primer.");
            return false;
        }
        System.out.println("\n-------------AUTENTIFICACIÓ------------");
        // Autentificació (usuari i contrasenya)
        System.out.println("Introdueix el teu nom d'usuari: ");
        nombreIntroduit = sc.next();

        System.out.println("Introdueix la teva contrasenya: ");
        passIntroduit = sc.next();

        // Busca l'usuari en la llista d'usuaris registrats
        for (String[] usuari : usuarisRegistrats) {
            if (usuari[0].equals(nombreIntroduit) && usuari[1].equals(passIntroduit)) {
                // Si trobem coincidència de nom i contrasenya
                System.out.println("La autentificació ha estat exitosa. Benvingut al sistema " + nombreIntroduit + "!");

                // Actualitzar variables globals
                usuariAutenficat = true;
                nomUsuariActual = nombreIntroduit;
                passActual = passIntroduit;

                return true; // Sessió iniciada
            }
        }

        // Si no es troba una coincidència
        System.out.println("ERROR: Nom d'usuari o contrasenya incorrectes. Intenta-ho de nou.");
        return false;
    }
}