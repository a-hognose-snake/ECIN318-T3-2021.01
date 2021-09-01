package spacegirls;

import java.util.Iterator;
import java.util.LinkedList;

public class Jugador extends Usuario{

    private int saldoJugador;
    private ListaSimpleOrbes orbes;
    private LinkedList<Campeon> campeones;
    private ListaDobleAspectos aspectos;
    

    public Jugador(String nombreJugador, String claveJugador, int saldoJugador) {
        super(nombreJugador, claveJugador);
        orbes = new ListaSimpleOrbes();
        campeones = new LinkedList<Campeon>();
        aspectos = new ListaDobleAspectos();
        this.saldoJugador = saldoJugador;
    }


    public ListaDobleAspectos getAspectos() {
        return aspectos;
    }


    public void setAspectos(ListaDobleAspectos aspectos) {
        this.aspectos = aspectos;
    }


    public LinkedList<Campeon> getCampeones() {
        return campeones;
    }


    public void setCampeones(LinkedList<Campeon> campeones) {
        this.campeones = campeones;
    }


    public int getSaldoJugador() {
        return saldoJugador;
    }


    public void setSaldoJugador(int saldoJugador) {
        this.saldoJugador = saldoJugador;
    }


    public ListaSimpleOrbes getOrbes() {
        return orbes;
    }


    public void setOrbes(ListaSimpleOrbes orbes) {
        this.orbes = orbes;
    }

    @Override
	public String toString() {
		String text = "";
		
		text += this.getNombre()+"\n";
        if (this.getOrbes().getSize()==0){
            text+= "Orbes: No tiene.\n";
        }
        else{
            text += "Orbes: "+this.getOrbes().toString()+"\n";
        }
        if (this.getCampeones().size()==0){
            text+= "Campeones: No tiene.\n";
        }
        else{
            text += "Campeones:\n";
            Iterator<Campeon> it = campeones.iterator();
            while(it.hasNext()){
                Campeon c = (Campeon) it.next();
                text+=c.getNombre()+" ";
            }
            text+="\n";
        }
		if (this.getAspectos().getSize()==0){
            text+= "Aspectos: No tiene";
        }
        else{
            text += "Aspectos: "+this.getAspectos().toString()+"";
        }
		return text;
	}
}
