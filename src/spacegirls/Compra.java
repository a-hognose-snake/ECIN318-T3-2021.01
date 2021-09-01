package spacegirls;

public class Compra {
    private String fecha;
    private int valor;

    public Compra(String fecha, int valor) {
        this.fecha = fecha;
        this.valor = valor;
    }

    public int getMes(){
        int mes = Integer.parseInt(fecha.split("/")[1]);
        return mes;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

}