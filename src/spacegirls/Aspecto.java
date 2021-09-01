package spacegirls;


public class Aspecto {

    private String nombre;
    private String tipo;
    private int valorRareza;
    private double probabilidadTipoAspecto;
    private static double probabilidad;
    private Campeon c;
    private int uso;

    public Aspecto(String nombre, String tipo) {
        uso = 0;
        this.nombre = nombre;
        this.tipo = tipo;
        if (tipo.equals("Mitica")) {
            this.valorRareza = 5000; 
            setProbabilidadTipoAspecto(0.05);
        } else {
            if (tipo.equals("Legendaria")) {
                this.valorRareza = 3200;
                setProbabilidadTipoAspecto(0.15);
            } else {
                if (tipo.equals("Epica")) {
                    this.valorRareza = 1350;
                    setProbabilidadTipoAspecto(0.30);
                } else {
                    if (tipo.equals("Normal")) {
                        this.valorRareza = 950;
                        setProbabilidadTipoAspecto(0.50);
                    }
                    
                }
                
            }
            
        }
    }
    
    public int getUso(){
        return uso;
    }

    public void aumentarUso(){
        uso+=1;
    }

    public double getProbabilidadTipoAspecto() {
        return probabilidadTipoAspecto;
    }

    public void setProbabilidadTipoAspecto(double probabilidadTipoAspecto) {
        this.probabilidadTipoAspecto = probabilidadTipoAspecto;
    }

    public String getNombre() {
        return nombre;
    }

    public String getNombreReal(){
        return c.getNombre()+" "+nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getValorRareza() {
        return valorRareza;
    }

    public void setValorRareza(int valorRareza) {
        this.valorRareza = valorRareza;
    }

    public static double getProbabilidad() {
        return probabilidad;
    }

    public static void setProbabilidad(double probabilidad) {
        Aspecto.probabilidad = probabilidad;
    }

    public Campeon getC() {
        return c;
    }

    public void setC(Campeon c) {
        this.c = c;
    }

    @Override
	public String toString() {
		String text = "";
		
		text += "Nombre: "+this.getNombreReal()+"\n";
		text += "Tipo: "+this.getTipo()+"\n\n";

		return text;
	}
}
