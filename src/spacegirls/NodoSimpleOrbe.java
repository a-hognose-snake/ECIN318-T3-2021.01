package spacegirls;

public class NodoSimpleOrbe {

    private NodoSimpleOrbe next;
	private Orbe orbe;
	
	public NodoSimpleOrbe(Orbe o) {
		this.orbe = o;
		setNext(null);
	}
	
	public Orbe getOrbe() {
		return orbe;
	}

	public NodoSimpleOrbe getNext() {
		return next;
	}

	public void setNext(NodoSimpleOrbe next) {
		this.next = next;
	}

}