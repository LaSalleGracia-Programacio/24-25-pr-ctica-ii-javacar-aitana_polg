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
        inicialitzarVehicles();
        System.out.println(benvinguda());
        mostrarMenu();
        GestioMenu();
    }


    /**
     * Metode on crea (Roda, Motor, Cotxe, Moto i furgoneta) per defecte
     */
    public static void inicialitzarVehicles() {
        // Vehicles perque l'usuari pugui alquilar sense la necessitat de que un Arrendetari afegeixi
        Roda[] rodesCotxe = {
                new Roda("Michelin", 15),
                new Roda("Michelin", 15),
                new Roda("Michelin", 15),
                new Roda("Michelin", 15)
        };
        Roda[] rodesMoto = {
                new Roda("Pirelli", 17),
                new Roda("Pirelli", 17)
        };
        Roda[] rodesFurgoneta = {
                new Roda("Goodyear", 16),
                new Roda("Goodyear", 16),
                new Roda("Goodyear", 16),
                new Roda("Goodyear", 16)
        };

        Motor motorDiesel = new Motor("diesel", 100);
        Motor motorGasolina = new Motor("gasolina", 90);

        Cotxe cotxe1 = new Cotxe("C001", "Toyota", "Corolla", 30.0, 5, motorGasolina, rodesCotxe);
        Cotxe cotxe2 = new Cotxe("C002", "Ford", "Focus", 28.0, 5, motorDiesel, rodesCotxe);
        Cotxe cotxe3 = new Cotxe("C003", "Honda", "Civic", 32.0, 5, motorGasolina, rodesCotxe);

        Moto moto1 = new Moto("M001", "Yamaha", "YZF-R3", 20.0, 321, motorGasolina, rodesMoto);
        Moto moto2 = new Moto("M002", "Kawasaki", "Ninja 400", 22.0, 399, motorGasolina, rodesMoto);

        Furgoneta furgoneta1 = new Furgoneta("F001", "Mercedes", "Sprinter", 50.0, 1500.0, motorDiesel, rodesFurgoneta);
        //Afegir aquets vehicler per poder ser alquilats
        gestorLloguers.afegirVehicle(cotxe1);
        gestorLloguers.afegirVehicle(cotxe2);
        gestorLloguers.afegirVehicle(cotxe3);
        gestorLloguers.afegirVehicle(moto1);
        gestorLloguers.afegirVehicle(moto2);
        gestorLloguers.afegirVehicle(furgoneta1);
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
        System.out.println("3. Tancar Sessió");
        System.out.println("4. Sortir");
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
                            mostrarMenu();
                            GestioMenu();
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
                            mostrarMenu();
                            GestioMenu();
                        }
                    }
                case 3:
                    tancarSessio();
                    break;
                case 4:
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

    /**
     * Metode per les opcions que pot fer el Client
     * @param usuari nom de l'usuari
     */
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
    /**
     * Metode per poder guardar la informació dels nous vehicles
     */
    public static void afegirVehiclePerLlogar(){
        //Inici VARIABLES
        String matricula;
        String marca;
        String model;
        String tipusVehicle;
        String tipusMotor;
        //Fi VARIABLES

        System.out.println("\n------ Afegir un nou vehicle ------");

        do{
            System.out.println("Introdueix la matricula:");
            matricula = sc.next();
            if (matricula.isEmpty()){
                System.out.println("Error: La matrícula no pot estar buida.");
            }

        }while(matricula.isEmpty());

        do {
            sc.nextLine();
            System.out.println("Marca del Vehicle:");
            marca = sc.nextLine();
            if (marca.isEmpty()){
                System.out.println("Error: La marca no pot estar buida.");
            }
        }while (marca.isEmpty());

        do {
            System.out.println("Model del Vehicle:");
            model = sc.nextLine();
            if (model.isEmpty()){
                System.out.println("Error: Model no pot estar buit.");
            }
        }while (model.isEmpty());

        System.out.println("Preu base per dia:");
        double preuBase = sc.nextDouble();

        do {
            System.out.println("Tipus de motor (electric, híbrid, diesel, gasolina):");
             tipusMotor = sc.next();
             if (tipusMotor.isEmpty()){
                 System.out.println("Error: El tipus de motor no pot estar buit.");
             }
        }while (tipusMotor.isEmpty());


        System.out.print("Potència del motor (CV): ");
        int potenciaMotor = sc.nextInt();


        Motor motor = new Motor(tipusMotor, potenciaMotor);

        do {
            System.out.println("Quin tipus de vehicle és? (cotxe/moto/furgoneta):");
            tipusVehicle = sc.next().toLowerCase();
            if (tipusVehicle.isEmpty()){
                System.out.println("Error: El tipus de vehicle no pot estar buit.");
            }
        }while (tipusVehicle.isEmpty());


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

    /**
     * Metode que mostra els vehicles amb el temps que vol llogar
     */
    public static void filtrarPerTempsLloguer() {
        System.out.print("Introdueix el nombre de dies mínim de lloguer: ");
        int dies = sc.nextInt();

        gestorLloguers.filtrarPerTemps(dies);

        mostrarDetallsVehicles();
    }

    /**
     * Metode que mostra els vehicles amb el preu que vol
     */
    public static void filtrarPerPreuLloguer() {
        System.out.print("Introdueix el preu màxim que vols pagar per dia: ");
        double preuMax = sc.nextDouble();

        System.out.print("Introdueix el nombre de dies del lloguer: ");
        int dies = sc.nextInt();

        gestorLloguers.filtrarPerPreu(preuMax, dies);

        mostrarDetallsVehicles();
    }

    /**
     * Metode que calcula els ingressos del arrendatari
     */
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

        double totalIngressos = gestorLloguers.calcularIngressosTotals(gestorLloguers.getVehicleList() ,dies);
        System.out.println("Ingressos totals per " + dies + " dies: " + totalIngressos + "€");
        mostrarMenu();
        GestioMenu();
    }

    /**
     * Metode que mostra els detalls dels vehicles en cas que hi hagin
     */
    public static void mostrarDetallsVehicles() {
        if (gestorLloguers.getVehicleList().isEmpty()) {
            System.out.println("No hi ha vehicles registrats.");
        } else {
            System.out.println();
            System.out.println("Etiquetes Ambientals:");
            for (Vehicle v : gestorLloguers.getVehicleList()) {
                System.out.println(v.getMarca() + " " + v.getModel() + " - Etiqueta Ambiental: " + v.getEtiquetaAmbiental());
            }
        }
        mostrarMenu();
        GestioMenu();
    }



    /**
     * Metode per la identificació de la persona
     * @param sc L'objecte Scanner per llegir l'entrada de l'usuari.
     */
    public static void registre(Scanner sc){
        boolean rol = false;
        String usuari = "";
        String pass = "";


        System.out.println("\n---------------REGISTRE--------------");
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
     * @param pass
     * @return true si és arrendatari, en cas contrari false
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

    /**
     * Tanca la sessió de l'usuari.
     */
    public static void tancarSessio() {
        nomUsuariActual = "";
        passActual = "";
        usuariAutenficat = false;
        System.out.println("Sessió tancada correctament.");
        mostrarMenu();
        GestioMenu();
    }
}