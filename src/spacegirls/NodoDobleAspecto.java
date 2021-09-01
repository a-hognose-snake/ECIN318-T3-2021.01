package spacegirls;

public class NodoDobleAspecto {

    private NodoDobleAspecto prev;
	private Aspecto aspecto;
	private NodoDobleAspecto next;
	
	public NodoDobleAspecto(Aspecto a) {
		this.aspecto = a;
		this.next = null;
		this.prev =  null;
	}

	public NodoDobleAspecto getPrev() {
		return prev;
	}

	public void setPrev(NodoDobleAspecto prev) {
		this.prev = prev;
	}

	public Aspecto getAspecto() {
		return aspecto;
	}

	public NodoDobleAspecto getNext() {
		return next;
	}

	public void setNext(NodoDobleAspecto next) {
		this.next = next;
	}
}