package spacegirls;

public class ListaSimpleOrbes {
    private NodoSimpleOrbe first;
	private int size;

	public ListaSimpleOrbes() {
		first = null;
	}
	
	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public NodoSimpleOrbe getFirst(){
		return first;
	}

	public void ingresar(Orbe j) {
		NodoSimpleOrbe n = new NodoSimpleOrbe(j);
		if (first == null) {
			first = n;
			setSize(getSize() + 1);
			
		}
		else {
			n.setNext(first);
			first = n;
			setSize(getSize() + 1);
		}
	}

    public Orbe buscarOrbe (String Nombre) {
		String dato = Nombre;
		NodoSimpleOrbe current = first;
		while (!current.getOrbe().getNombre().equals(dato) && current.getNext() != null) {
			current = current.getNext();
		}
		if (current.getOrbe().getNombre().equals(dato)) {
			return current.getOrbe();
		} 
		else {
			return null;
		}
	}

	@Override
	public String toString() {
		String text = "";
		NodoSimpleOrbe current = first;
		while (current != null) {
			text += "Nombre: "+current.getOrbe().getNombre()+"\n";
			current = current.getNext();
		}
		return text;
	}

	public boolean eliminar(String Nombre) {
		String dato = Nombre;
		NodoSimpleOrbe current = first;
		NodoSimpleOrbe prev = first;
		while (!current.getOrbe().getNombre().equals(dato) && current.getNext() != null) {
			prev = current;
			current = current.getNext();
		}
		if (current.getOrbe().getNombre().equals(dato)) {
			if (first == current) {
				first = first.getNext();
				size--;
				return true;	
			} 
			else {
				prev.setNext(current.getNext());
				size--;
				return true;
			}
		} 
		else {
			return false;
		}
		
	}

}
