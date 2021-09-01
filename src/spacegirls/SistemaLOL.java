package spacegirls;

/**
 * @author a_hognose_snake
 *
 */
public interface SistemaLOL {
	
	/**
	 * Enters a player to the system
	 * @param nombreJugador
	 * @param claveJugador
	 * @param saldoJugador
	 */
	public void ingresarJugador(String nombreJugador, String claveJugador, int saldoJugador);
	
	/**
	 * Enters a champion to the system
	 * @param nombreCampeon
	 * @param carrilCampeon
	 */
	public void ingresarCampeon(String nombreCampeon, String carrilCampeon);
	
	/**
	 * Enters a skin to the system
	 * @param campeon
	 * @param nombreAspecto
	 * @param tipoASpecto
	 */
	public void ingresarAspecto(String campeon,String nombreAspecto, String tipoASpecto);
	
	/**
	 * It asociates a player to an orbe
	 * @param nombreJugador
	 * @param nombreOrbe
	 */
	public void asociarJugadorOrbe(String nombreJugador, String nombreOrbe);
	
	/**
	 * It asociates a player to a champion
	 * @param nombreJugador
	 * @param nombreCampeon
	 */
	public void asociarJugadorCampeon(String nombreJugador, String nombreCampeon);

	/**
	 * It asociates a champion to a skin
	 * @param nombreCampeon
	 * @param nombreAspecto
	 */
	public void asociarAspectoCampeon(String nombreCampeon,String nombreAspecto);
	
	/**
	 * It asociates a skin to a player
	 * @param nombreJugador
	 * @param nombreAspecto
	 */
	public void asociarJugadorAspecto(String nombreJugador, String nombreAspecto);
	
	/**
	 * Enters an admin to the system
	 * @param nombreAdmin
	 * @param claveAdmin
	 */
	public void ingresarAdmin(String nombreAdmin, String claveAdmin);
	
	/**
	 * Enters a receipt to the system
	 * @param fecha
	 * @param montoCompra
	 */
	public void ingresarCompra(String fecha, int montoCompra);
	
	/**
	 * Enters an orbe to the system
	 * @param nombreOrbe
	 */
	public void ingresarOrbe(String nombreOrbe);
	
	/**
	 * Checks if the user exists in the system
	 * @param nombreUsuario
	 * 
	 */
	public boolean validarUsuario(String nombreUsuario);
	
	/**
	 * Checks if the password of the user is correct
	 * @param nombreUsuario
	 * @param clave
	 *
	 */
	public int validarAcceso(String nombreUsuario, String clave);
	
	/**
	 * Obtains data about a specific player
	 * @param nombreJugador
	 * 
	 */
	public String obtenerDatosJugador(String nombreJugador);
	
	/**
	 * Adds money to an user's account
	 * @param nombreJugador
	 * @param monto
	 * 
	 */
	public int addSaldo(String nombreJugador, int monto);

	/**
	 * Displays the money held in a user account
	 * @param nombreJugador
	 * 
	 */
	public int getSaldo(String nombreJugador);
	
	/**
	 * Obtains data about the orbes that are avaliable
	 * 
	 */
	public String obtenerDatosOrbesDisponibles();
	
	/**
	 * Allows a user to buy an orbe
	 * @param nombreJugador
	 * @param nombreOrbe
	 * 
	 */
	public boolean comprarOrbe(String nombreJugador, String nombreOrbe);
	
	/**
	 * Returns an int tha represent the tipe of object
	 * @param x
	 * 
	 */
	public int comprobarTipo(String x);

    /**
     * Allow you to obtain data about all the orbes a user has
     * @param nombreJugador
     * 
     */
    public String obtenerOrbesDelJugador(String nombreJugador);

    /**
     * Allows you to opn an orbe
     * @param nombreJugador
     * @param nombreOrbe
     * 
     */
    public String abrirOrbe(String nombreJugador, String nombreOrbe);

    /**
     * It obtains all the data about the skins a user has
     * @param nombreJugador
     * 
     */
    public String obtenerFragmentosDeAspectoDelJugador(String nombreJugador);

    /**
     * Allows you to create a full skin with 3 fragments of skin
     * @param nombreA1
     * @param nombreA2
     * @param nombreA3
     * @param nombreJugador
     * @return a full skin
     */
    public String reRoll(String nombreA1, String nombreA2, String nombreA3, String nombreJugador);

    /**
     * Obtains the month with the most sales
     *
     */
    public String obtenerMesConMasVentas();

    /**
     * Obtains the most popular skin within users
     * 
     */
    public String obtenerAspectoMasComun();

    /**
     * Obtains data about users to re-write .txt files
     * 
     */
    public String obtenerPersonasTXT();

    /**
     * Obtains data about skins to re-write .txt files
     * 
     */
    public String obtenerAspectoTXT();

    /**
     * Obtains data about sales to re-write .txt files
     * 
     */
    public String obtenerBalanceTXT();

    /**
     * Obtains data about orbes to re-write .txt files
     * 
     */
    public String obtenerOrbesTXT();

	/**
	 * Sets the date
	 * @param fecha
	 */
	public void setFecha(String fecha);

    /**
     * Sets the probability of a champion
     * @param d
     */
    public void asignarProbabilidadCampeon(double d);

	/**
	 * Sets the probability of an orbe
	 * @param d
	 */
	public void asignarProbabilidadOrbe(double d);

	/**
	 * Sets the probability of a skin
	 * @param d
	 */
	public void asignarProbabilidadAspecto(double d);

	/**
	 * Obtains data about players
	 * 
	 */
	public String obtenerDatosJugadores();
}