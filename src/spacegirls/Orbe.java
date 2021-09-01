package spacegirls;

public class Orbe {

    private String nombre;
    private static int valor = 1350;
    private static double probabilidad;

    public Orbe(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public static int getValor() {
        return valor;
    }

    public static void setValor(int valor) {
        Orbe.valor = valor;
    }

    public static double getProbabilidad() {
        return probabilidad;
    }

    public static void setProbabilidad(double probabilidad) {
        Orbe.probabilidad = probabilidad;
    }

    @Override
	public String toString() {
		String text = "";
		
		text += "\n"+this.getNombre();

		return text;
	}

}