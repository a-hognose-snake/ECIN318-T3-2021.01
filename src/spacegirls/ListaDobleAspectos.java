package spacegirls;

public class ListaDobleAspectos {
    private NodoDobleAspecto first;
	private NodoDobleAspecto last;
	private int size;
    
	
	public ListaDobleAspectos() {
        first = null;
        last = null;
    }

	
	public boolean isEmpty() {
		if (first == null) {
			return true;
		} 
		else {
			return false;
		}
	}
	public boolean eliminar(String rut) {
		if (first!=null){
			if (first.getNext()==null){
				if (first.getAspecto().getNombreReal().equals(rut)){
					first=null;
					return true;
				}
				return false;
			}
			else{
				String dato = rut;
				NodoDobleAspecto current = first;
				while (!current.getAspecto().getNombreReal().equals(dato) && current != last) {
					current = current.getNext();	
				}
				if (current.getAspecto().getNombreReal().equals(dato)) {
					if (current == first) {
						first = first.getNext();
						first.setPrev(null);
						return true;
					} 
					else {
						if (current == last) {
							last = current.getPrev();
							last.setNext(null);
							return true;
						} 
						else {
							NodoDobleAspecto aux = current;
							current = aux.getPrev();
							current.setNext(aux.getNext());
							current.getNext().setPrev(current);
							aux = null;
							return true;
						}
					}
				} 
				else {
					return false;
				}

			}
		}
		else{
			return false;
		}
	}

    public void ingresar(Aspecto a) {
		NodoDobleAspecto n = new NodoDobleAspecto(a);
		if (isEmpty()) {
			first = n;
			last = n;
			size++;
		} 
		else {
			n.setNext(first);
			first.setPrev(n);
			first = n;
			size++;
		}
	}


    public NodoDobleAspecto getFirst() {
        return first;
    }


    public void setFirst(NodoDobleAspecto first) {
        this.first = first;
    }


    public NodoDobleAspecto getLast() {
        return last;
    }


    public void setLast(NodoDobleAspecto last) {
        this.last = last;
    }

	public int getSize(){
		return size;
	}

	public int getCantByType(String tipo){
		int cant = 0;
		NodoDobleAspecto current = first;
		while ( current != last) {
			if (current.getAspecto().getTipo().equals(tipo)){
				cant++;
			}
			current = current.getNext();
		}
		return cant;
	}
	
    public Aspecto buscar(String Nombre) {
		String dato = Nombre;
		NodoDobleAspecto current = first;
		while (!current.getAspecto().getNombreReal().equals(dato) && current != last) {
			current = current.getNext();	
		}
		if (current.getAspecto().getNombreReal().equals(dato)) {
			return current.getAspecto();
		} 
		else {
			return null;
		}
	}

	@Override
	public String toString() {
		String text = "\n";
		NodoDobleAspecto current = last;
		while (current != null) {
			text += "Nombre: "+current.getAspecto().getNombreReal()+"\n";
			text += "Tipo: "+current.getAspecto().getTipo()+"\n\n";
			current = current.getPrev();
		}
		return text+"\n";
	}

}