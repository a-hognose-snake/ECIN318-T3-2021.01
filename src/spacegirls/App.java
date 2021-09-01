package spacegirls;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * 
 * @author a_hognose_snake
 *
 */
public class App{
    /**
     * Executes the program
     * @param args
     * @throws FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
        SistemaLOL system = new SistemaLOLImpl();
        Lectura(system);
        Sesion(system);
    }

    /**
     * Allows the user to log in
     * @param system
     */
    public static void Sesion(SistemaLOL system) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Ingrese fecha (DD/MM/AAAA): ");
        String fecha = scan.nextLine();
        while(!comprobarFecha(fecha,system)){
            System.out.println("Reingrese fecha (DD/MM/AAAA): ");
            fecha = scan.nextLine();
        }
        System.out.println("INICIO DE SESION");
        System.out.println("Ingrese nombre de usuario: ");
        String nombreU = scan.nextLine();
        System.out.println("Ingrese clave de usuario: ");
        String clave = scan.nextLine();
        int opcion = validarAcceso(nombreU, clave, system);
        while(opcion==0){
            System.out.println("Error en el ingreso de datos.");
            System.out.println("OPCIONES:\n[0] Volver a intentar\n[1] Registrarse\n[2] Salir del sistema");
            String opcion2 = scan.nextLine();
            if (opcion2.equals("0")){
                System.out.println("INICIO DE SESION");
                System.out.println("Ingrese nombre de usuario: ");
                nombreU = scan.nextLine();
                System.out.println("Ingrese clave de usuario: ");
                clave = scan.nextLine();
                opcion = validarAcceso(nombreU, clave, system);
            }
            else{
                if (opcion2.equals("1")){
                    opcion = Registro(system);
                    System.out.println("Reinicie el sistema.");
                    try{
                        salir(system);
                    }
                    catch(Exception e){
                        System.exit(0);
                    }
                }
                else{
                    if (opcion2.equals("2")){
                        System.out.println("Adios.");
                        System.exit(0);
                    }
                    else{
                        opcion = 0;
                    }
                }
            }
        }
        if (opcion==1){
            MenuAdmin(nombreU,system);
        }
        else{
            MenuUsuario(nombreU,system);
        }
    }

    /**
     * Allows the user to obtain information according to its role or to try again or close the system
     * @param nombreU
     * @param system
     */
    public static void MenuUsuario(String nombreU, SistemaLOL system) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Bievenido "+nombreU);
        desplegarMenuUsuario();
        System.out.println("Ingresa una opcion: ");
        String opcion = scan.nextLine();
        while(true){
            try {
                int opcionInt = Integer.parseInt(opcion);
                if (opcionInt == 0){
                    salir(system);
                }
                if (opcionInt == 1){
                    System.out.println(system.obtenerDatosJugador(nombreU));
                }
                if (opcionInt == 2){
                    System.out.println("Saldo disponible: "+system.getSaldo(nombreU));
                    System.out.println("Ingrese saldo: ");
                    int saldo = Integer.parseInt(scan.nextLine());
                    System.out.println("Nuevo saldo: "+system.addSaldo(nombreU, saldo));
                    System.out.println(saldo+" RP ingresados a su cuenta.");
                }
                if (opcionInt == 3){
                    System.out.println(system.obtenerDatosOrbesDisponibles());
                    System.out.println("Saldo disponible: "+system.getSaldo(nombreU));
                    System.out.println("Ingrese nombre del orbe: ");
                    String nombreOrbe = scan.nextLine();
                    if (system.comprarOrbe(nombreU, nombreOrbe)){
                        System.out.println(nombreOrbe+" comprado.");
                    }
                    else{
                        System.out.println(nombreOrbe+" no comprado.");
                    }
                }
                if (opcionInt == 4){
                    System.out.println(system.obtenerFragmentosDeAspectoDelJugador(nombreU));
                    System.out.println("Ingrese nombre de aspecto 1: ");
                    String nombreA1 = scan.nextLine();
                    System.out.println("Ingrese nombre de aspecto 2: ");
                    String nombreA2 = scan.nextLine();
                    System.out.println("Ingrese nombre de aspecto 3: ");
                    String nombreA3 = scan.nextLine();
                    System.out.println(system.reRoll(nombreA1, nombreA2, nombreA3, nombreU));
                }
                if (opcionInt == 5){
                    System.out.println(system.obtenerOrbesDelJugador(nombreU));
                    String nombreOrbe = scan.nextLine();
                    System.out.println(system.abrirOrbe(nombreU, nombreOrbe));
                }
                desplegarMenuUsuario();
                System.out.println("Ingresa una opcion: ");
                opcion = scan.nextLine();
                
            }
            catch (Exception e) {
                System.out.println("[Error]");
                if (e instanceof NullPointerException){
                    System.out.println(e.getMessage());
                }
                else{
                    System.out.println("Ingrese una opcion valida.");
                }
                desplegarMenuUsuario();
                System.out.println("Ingresa una opcion: ");
                opcion = scan.nextLine();
            }
        }
    }

    /**
     * Displays the admin's menu
     */
    public static void desplegarMenuAdmin() {
        System.out.println("Menu: ");
        System.out.println("[0] Salir del sistema.");
        System.out.println("[1] Desplegar mes de mas ventas.");
        System.out.println("[2] Informacion de jugadores.");
        System.out.println("[3] Mejor aspecto.");
    }

    /**
     * Displays the user menu
     */
    public static void desplegarMenuUsuario() {
        System.out.println("Menu: ");
        System.out.println("[0] Salir del sistema.");
        System.out.println("[1] Informacion de usuario.");
        System.out.println("[2] Annadir saldo.");
        System.out.println("[3] Comprar orbe.");
        System.out.println("[4] Re-Roll.");
        System.out.println("[5] Abrir orbe.");
    }

    /**
     * Allows the admin to obtain information according to its role or to try again or close the system
     * @param nombreU
     * @param system
     */
    public static void MenuAdmin(String nombreU, SistemaLOL system) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Bievenido Admin "+nombreU);
        desplegarMenuAdmin();
        System.out.println("Ingresa una opcion: ");
        String opcion = scan.nextLine();
        while(true){
            try {
                int opcionInt = Integer.parseInt(opcion);
                if (opcionInt == 0){
                    salir(system);
                }
                if (opcionInt == 1){
                    System.out.println(system.obtenerMesConMasVentas());
                }
                if (opcionInt == 2){
                    System.out.println(system.obtenerDatosJugadores());
                }
                if (opcionInt == 3){
                    System.out.println(system.obtenerAspectoMasComun());
                }
                desplegarMenuAdmin();
                System.out.println("Ingresa una opcion: ");
                opcion = scan.nextLine();
                
            }
            catch (Exception e) {
                System.out.println("[Error]");
                if (e instanceof NullPointerException){
                    System.out.println(e.getMessage());
                }
                else{
                    System.out.println("Ingrese una opcion valida.");
                }
                desplegarMenuUsuario();
                System.out.println("Ingresa una opcion: ");
                opcion = scan.nextLine();
            }
        }
    }

    /**
     * Re-writes .txt files and closes the system
     * @param system
     * @throws IOException
     */
    public static void salir(SistemaLOL system) throws IOException {
        FileWriter file_orbes = new FileWriter("orbes.txt");
        PrintWriter escritura_orbes = new PrintWriter(file_orbes);
        escritura_orbes.print(system.obtenerOrbesTXT());
        escritura_orbes.close();
        FileWriter file_balances = new FileWriter("balances.txt");
        PrintWriter escritura_balances = new PrintWriter(file_balances);
        escritura_balances.print(system.obtenerBalanceTXT());
        escritura_balances.close();
        FileWriter file_personas = new FileWriter("personas.txt");
        PrintWriter escritura_personas = new PrintWriter(file_personas);
        escritura_personas.print(system.obtenerPersonasTXT());
        escritura_personas.close();
        FileWriter file_aspectos = new FileWriter("aspectosPersonajes.txt");
        PrintWriter escritura_aspectos = new PrintWriter(file_aspectos);
        escritura_aspectos.print(system.obtenerAspectoTXT());
        escritura_aspectos.close();
        System.exit(0);
    }

    /**
     * Allows you to register a new player or admin
     * @param system
     * @return
     */
    public static int Registro(SistemaLOL system) {
        Scanner scan = new Scanner(System.in);
        System.out.println("REGISTRO:");
        System.out.println("Ingrese nombre de NUEVO usuario: ");
        String nombreU = scan.nextLine();
        while (system.validarUsuario(nombreU)){
            System.out.println("[Error] Nombre de usuario ya se encuentra en el sistema.");
            System.out.println("Reingrese nombre de NUEVO usuario: ");
            nombreU = scan.nextLine();
        }
        System.out.println("Ingrese clave de NUEVO usuario: ");
        String clave = scan.nextLine();
        System.out.println("Ingrese tipo del NUEVO usuario (Admin o Jugador): ");
        String tipo = scan.nextLine();
        while (!tipo.equals("Admin") && !tipo.equals("Jugador")){
            System.out.println("Reingrese tipo del NUEVO usuario (Admin o Jugador): ");
            tipo = scan.nextLine();
        }
        if (tipo.equals("Admin")){
            system.ingresarAdmin(nombreU, clave);
            return 1;
        }
        else{   
            System.out.println("Ingrese saldo del NUEVO jugador: ");
            int saldo = 0;
            try{
                saldo = Integer.parseInt(scan.nextLine());
                system.ingresarJugador(nombreU, clave, saldo);
                return 2;
            }
            catch(Exception ex){
                System.out.println("Error en el ingreso del saldo. \nRedirigiendo...");
                return 0;
            }
        }
    }

    /**
     * Checks if the combination of username and password is correct and returns an it wit the type of user (admin or player)
     * @param nombreU
     * @param clave
     * @param system
     * @return
     */
    public static int validarAcceso(String nombreU, String clave, SistemaLOL system) {
        if(system.validarUsuario(nombreU)){
            /**
             * if option equals 1 is an Admin, 2 is a Player, 0 is null
             */
            int opcion = system.validarAcceso(nombreU,clave);
            return opcion;
        }
        else{
            /**
             * if option equals 0 is a invalid user, we need throw the registration or the re-login case
             */
            return 0;
        }
    }

    /**
     * Checks the format of the date
     * @param fecha
     * @param sy
     * @return
     */
    public static boolean comprobarFecha(String fecha,SistemaLOL sy) {
        String [] apartado = fecha.split("/");
        if (apartado.length==3){
            try {
                int dia, mes, anno;
                dia = Integer.parseInt(apartado[0]);
                mes = Integer.parseInt(apartado[1]);
                anno = Integer.parseInt(apartado[2]);
                if (dia>0 && dia<=31){
                    if (mes>0 && mes<=12){
                        if (anno>=1990 && anno<=2100){
                            sy.setFecha(fecha);
                            return true;
                        }
                    }
                }
                System.out.println("Dia, mes o año invalidos.");
                return false;
            } catch (Exception e) {
                System.out.println("Formato erroneo.");
                return false;
            }
        }
        else{
            System.out.println("Formato erroneo.");
            return false;
        }
    }

    /**
     * Reads and saves the info in .txt files
     * @param system
     * @throws FileNotFoundException
     */
    public static void Lectura(SistemaLOL system) throws FileNotFoundException {
        /**
         * Section abstract >/
         *  Champion skins and champions data entry into the system.
         *  For each champion skin, a association with his respective champion. (On system: aspectoX.setCampeon(campeonX) on system)
         * /<
         */
        Scanner scan = new Scanner(new File("aspectosPersonajes.txt"));
        system.asignarProbabilidadCampeon(0.45);
        system.asignarProbabilidadAspecto(0.45);
        while(scan.hasNextLine()){
            String banana = scan.nextLine();
            String[] partes = banana.split(",");
            String nombreCampeon = partes[0];
            String carril = partes[1];
            /**
             * champion entry into the system
             */
            system.ingresarCampeon(nombreCampeon,carril);
            for (int i = 2; i < partes.length; i+=2) {
                String aspecto = partes[i];
                String tipo = partes[i+1];
                
                try {
                    /**
                     * skin entry into the system
                     */
                    
                    /**
                     * champion skin association with his champion
                     */
                    system.ingresarAspecto(nombreCampeon, aspecto, tipo);
                } catch (NullPointerException e) {
                    System.out.println(e.getMessage());
                    System.exit(0);
                }
            }
        }
        /**
         * Section abstract >/
         *  Orbes data entry.
         * /<
         */
        scan = new Scanner(new File("orbes.txt"));
        system.asignarProbabilidadOrbe(0.1);
        while(scan.hasNextLine()){
            String banana = scan.nextLine();
            String [] partes = banana.split(",");
            String orbe = partes[0];
            /**
             * orbe entry into the system
             */
            system.ingresarOrbe(orbe);
        }

        /**
         * Section abstract >/
         *  User (can be an administrator or a player); his champions, orbes and his champion skins; data entry into the system.
         *  For each champion and champion skin a association with his respective player (User son).
         *  On system:
         *      user.championlist.add <- champion
         *      user.championskinlist.add <- champion skin
         *      user.orbelist.add <- orbe
         * /<
         */
        scan = new Scanner(new File("personas.txt"));
        while(scan.hasNextLine()){
            String banana = scan.nextLine();
            String[] partes2 = banana.split(",");
            String nombreU = partes2[0];
            String pass = partes2[1];
            String tipoU = partes2[2];
            if (tipoU.equalsIgnoreCase("Admin")){/** means that the inspected user is a administrator */
                /**
                 * user(admin) entry into the system
                 */
                system.ingresarAdmin(nombreU,pass);
            }
            else{ /** meas that the inspected user is a player */
                int saldo = Integer.parseInt(partes2[3]);
                /**
                 * user(player) entry into the system
                 */
                system.ingresarJugador(nombreU, pass, saldo);
                int cantidadALeer = partes2.length;
                String ultimoCampeon = "";
                for(int i=4 ; i < cantidadALeer ; i++){
                    /**
                     * Exception control (system throws on associations)
                     */
                    try{
                        int tipo = system.comprobarTipo(partes2[i]);
                        if (tipo==0){/** means that the inspected item is a orbe */
                            /**
                             * orbe association with his owner player
                             */
                            system.asociarJugadorOrbe(nombreU, partes2[i]);
                        }
                        else{
                            if (tipo==1){/** means that the inspected item is a champion */
                                /**
                                 * champion association with his owner player
                                 */
                                
                                ultimoCampeon = partes2[i];
                                system.asociarJugadorCampeon(nombreU, partes2[i]);
                            }
                            else{/** means that the inspected item is a champion skin */
                                /**
                                 * champion skin association with his owner player
                                 */
                                system.asociarJugadorAspecto(nombreU, ultimoCampeon+" "+partes2[i]);

                            }
                        }
                        //System.out.println(nombreU+" "+partes2[i]+" tipo: "+2);
                    }
                    catch(NullPointerException ex){
                        System.out.println("[Error] -> Asociación de datos: "+partes2[i]);
                        System.out.println(ex.getMessage());
                    }
                }
            }
        }

        /**
         * Section abstract >/
         *  Balance data entry.
         * /<
         */
        scan = new Scanner(new File("balances.txt"));
        while(scan.hasNextLine()){
            String banana = scan.nextLine();
            String[] partes = banana.split(",");
            String fecha = partes[0];
            int monto = Integer.parseInt(partes[1]);
            system.ingresarCompra(fecha, monto);
        }
    }

}