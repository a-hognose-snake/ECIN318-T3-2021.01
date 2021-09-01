package spacegirls;


public class Campeon{

    private String nombre;
    private String carril;
    private static double probabilidad;
    private ListaDobleAspectos aspectos;

    public Campeon(String nombreCampeon, String carril) {
        this.setNombre(nombreCampeon);
        this.carril = carril;
        aspectos = new ListaDobleAspectos();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCarril() {
        return carril;
    }

    public void setCarril(String carril) {
        this.carril = carril;
    }

    public static double getProbabilidad() {
        return probabilidad;
    }

    public static void setProbabilidad(double probabilidad) {
        Campeon.probabilidad = probabilidad;
    }

    public ListaDobleAspectos getAspectos() {
        return aspectos;
    }

    public void setAspectos(ListaDobleAspectos aspectos) {
        this.aspectos = aspectos;
    }
}
