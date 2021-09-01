package spacegirls;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

public class SistemaLOLImpl implements SistemaLOL
{
    private String fecha;
    private ArrayList<Compra> compras;
    private ListaDobleAspectos aspectos;
    private ListaSimpleOrbes orbes;
    private ArrayList<Usuario> usuarios;
    private LinkedList<Campeon> campeones;

    public void setFecha(String fecha){
        this.fecha=fecha;
    }

    public SistemaLOLImpl() {
        
        compras = new ArrayList<Compra>();
        aspectos = new ListaDobleAspectos();
        orbes = new ListaSimpleOrbes();
        usuarios = new ArrayList<Usuario>();
        campeones = new LinkedList<Campeon>();
    }

    @Override
    public void ingresarJugador(String nombreJugador, String claveJugador, int saldoJugador) {
        Usuario j = new Jugador(nombreJugador, claveJugador, saldoJugador);
        usuarios.add(j);
    }

    @Override
    public void ingresarCampeon(String nombreCampeon, String carrilCampeon) {
        Campeon c = new Campeon(nombreCampeon, carrilCampeon);
        campeones.add(c);
    }

    @Override
    public void ingresarAspecto(String nombreCampeon,String nombreAspecto, String tipoASpecto) {
        Aspecto a = new Aspecto(nombreAspecto, tipoASpecto);
        Campeon c = null;
        Iterator<Campeon> it = campeones.iterator();
        while(it.hasNext()){
            c = (Campeon) it.next();
            if (c.getNombre().equals(nombreCampeon)){
                break;
            }
        }
        if (c==null){
            throw new NullPointerException("No existe el campeon.");
        }
        else{
            a.setC(c);
            aspectos.ingresar(a);
        }
    }

    @Override
    public void asociarJugadorOrbe(String nombreJugador, String nombreOrbe) {
        Orbe o = orbes.buscarOrbe(nombreOrbe);
        Jugador j = null;
        Iterator<Usuario> it = usuarios.iterator();
        while(it.hasNext()){
            Usuario u = (Usuario) it.next();
            if (u instanceof Jugador){
                j = (Jugador) u;
                if(j.getNombre().equals(nombreJugador)){
                    break;
                }
            }
            
        }
        if (o == null || j == null) {
            throw new NullPointerException("No existe orbe y/o jugador. No se pudo asociar.");
        } else {
            j.getOrbes().ingresar(o);
        }
    }

    @Override
    public void asociarJugadorCampeon(String nombreJugador, String nombreCampeon) {
        Campeon c = null;
        Iterator<Campeon> it = campeones.iterator();
        while(it.hasNext()){
            c = (Campeon) it.next();
            if (c.getNombre().equals(nombreCampeon)){
                break;
            }
        }
        Jugador j = null;
        Iterator<Usuario> it2 = usuarios.iterator();
        while(it2.hasNext()){
            Usuario u = (Usuario) it2.next();
            if (u instanceof Jugador){
                j = (Jugador) u;
                if(j.getNombre().equals(nombreJugador)){
                    break;
                }
            }
        }
        if (c == null || j == null) {
            throw new NullPointerException("No existe campeon y/o jugador. No se pudo asociar.");
        } else {
            j.getCampeones().add(c);
        }
    }

    @Override
    public void asociarJugadorAspecto(String nombreJugador, String nombreAspecto) {
        Jugador j = null;
        Iterator<Usuario> it = usuarios.iterator();
        while(it.hasNext()){
            Usuario u = (Usuario) it.next();
            if (u instanceof Jugador){
                j = (Jugador) u;
                if(j.getNombre().equals(nombreJugador)){
                    break;
                }
            }
        }
        Aspecto a = aspectos.buscar(nombreAspecto);
        if (a == null || j == null) {
            throw new NullPointerException("No existe aspecto y/o jugador. No se pudo asociar.");
        } else {
            a.aumentarUso();
            j.getAspectos().ingresar(a);
        }
    }

    @Override
    public void ingresarAdmin(String nombreAdmin, String claveAdmin) {
        Usuario a = new Admin(nombreAdmin, claveAdmin);
        usuarios.add(a);
    }

    @Override
    public void ingresarCompra(String fecha, int montoCompra) {
        Compra c = new Compra(fecha, montoCompra);
        compras.add(c);
    }

    @Override
    public void ingresarOrbe(String nombreOrbe) {
        Orbe o = new Orbe(nombreOrbe);
        orbes.ingresar(o);
    }

    @Override
    public boolean validarUsuario(String nombreUsuario) {
        Usuario u = null;
        Iterator<Usuario> it = usuarios.iterator();
        while(it.hasNext()){
            Usuario uu = (Usuario) it.next();
            if(uu.getNombre().equals(nombreUsuario)){
                u=uu;
                break;
            }
        }
        if (u == null) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public int validarAcceso(String nombreUsuario, String clave) {
        Usuario u = null;
        Iterator<Usuario> it = usuarios.iterator();
        while(it.hasNext()){
            u = (Usuario) it.next();
            if(u.getNombre().equals(nombreUsuario)){
                break;
            }
        }
        if (u == null) {
            return 0;
        } else {
            if (u.getClave().equals(clave)){
                if (u instanceof Admin) {
                    return 1;
                } else {
                    return 2;
                }
            } else {
                return 0;
            }
        }
    }

    @Override
    public String obtenerDatosJugador(String nombreJugador) {
        String text = "";
        Jugador j = null;
        Iterator<Usuario> it = usuarios.iterator();
        while(it.hasNext()){
            Usuario u = (Usuario) it.next();
            if (u instanceof Jugador){     
                j = (Jugador) u;
                if(j.getNombre().equals(nombreJugador)){
                    break;
                }
            }
        }
        text = j.toString();
        return text;
    }

    @Override
    public int addSaldo(String nombreJugador, int monto) {
        Jugador j = null;
        Iterator<Usuario> it = usuarios.iterator();
        while(it.hasNext()){
            Usuario u = (Usuario) it.next();
            if (u instanceof Jugador){
                j = (Jugador) u;
                if(j.getNombre().equals(nombreJugador)){
                    break;
                }
            }
        }
        if (j == null) {
            throw new NullPointerException("No existe jugador con ese nombre. No se pudo agregar el saldo.");
        } else {
            int saldo = j.getSaldoJugador();
            int saldoActualizado = saldo + monto;
            j.setSaldoJugador(saldoActualizado);
            return saldoActualizado;
        }
    }

    @Override
    public String obtenerDatosOrbesDisponibles() {
        String text = "";
        text = orbes.toString();
        return text;
    }

    @Override
    public boolean comprarOrbe(String nombreJugador, String nombreOrbe) {
        Orbe o = orbes.buscarOrbe(nombreOrbe);
        Jugador j = null;
        Iterator<Usuario> it = usuarios.iterator();
        while(it.hasNext()){
            Usuario u = (Usuario) it.next();
            if (u instanceof Jugador){
                Jugador ju = (Jugador) u;
                if (ju.getNombre().equals(nombreJugador)){
                    j = ju;
                }
            }
            
        }
        if (j == null || o == null) {
            throw new NullPointerException("No existe jugador y/o orbe. No se pudo comprar el orbe.");
        } 
        else {
            int saldoJugador = j.getSaldoJugador();
            int precioOrbe = Orbe.getValor();
            if (precioOrbe < saldoJugador) {
                j.setSaldoJugador(saldoJugador - precioOrbe);
                j.getOrbes().ingresar(o);
                Compra compra = new Compra(this.fecha, precioOrbe);
                compras.add(compra);
                return true;
            } 
            else {
                throw new NullPointerException("El jugador no tiene saldo suficiente. No se pudo comprar el orbe.");
            }
        }
    }

    @Override
    public int comprobarTipo(String x) {
        if (orbes.buscarOrbe(x)!=null){
            return 0;//is a orbe
        }
        else{//can be a aspect or a champion
            Iterator<Campeon> it = campeones.iterator();
            Campeon campeon = null;
            while(it.hasNext()){
                Campeon c = (Campeon) it.next();
                if (c.getNombre().equals(x)){
                    campeon = c;
                }
            }
            if (campeon!=null){
                return 1;//is a champion
            }
            return 2;//is a aspect
        }
    }

    @Override
    public String obtenerOrbesDelJugador(String nombreJugador) {
        String text = "";
        Jugador j = null;
        Iterator<Usuario> it = usuarios.iterator();
        while(it.hasNext()){
            Usuario u = (Usuario) it.next();
            if (u instanceof Jugador){
                j = (Jugador) u;
                if(j.getNombre().equals(nombreJugador)){
                    break;
                }
            }
            
        }
        if (j.getOrbes().getSize()==0){
            throw new NullPointerException("No hay orbes");
        }
        else{
            text = j.getOrbes().toString();
        }
        return text;
    }

    @Override
    public String abrirOrbe(String nombreJugador, String nombreOrbe) {
        Jugador j = null;
        Iterator<Usuario> it = usuarios.iterator();
        while(it.hasNext()){
            Usuario u = (Usuario) it.next();
            if (u instanceof Jugador){
                j = (Jugador) u;
                if(j.getNombre().equals(nombreJugador)){
                    break;
                }
            }
            
        }
        if (j.getOrbes().eliminar(nombreOrbe)){
            Random rand = new Random();
            int probabilidadCampeon = (int)(Campeon.getProbabilidad()*100);
            int probabilidadAspecto = (int)(Aspecto.getProbabilidad()*100);
            int probabilidadOrbe    = (int)(Orbe.getProbabilidad()*100);
            int suma = probabilidadAspecto+probabilidadCampeon+probabilidadOrbe+1;
            int probable = rand.nextInt(suma);
            if (probable>probabilidadOrbe+probabilidadCampeon && probable<=probabilidadOrbe+probabilidadAspecto+probabilidadCampeon){
                int wat =rand.nextInt(aspectos.getSize());
                NodoDobleAspecto current = aspectos.getFirst();
                int count = 0;
                Aspecto seleccionado = null;
                while (current!=aspectos.getLast()){
                    if (count==wat){
                        seleccionado = current.getAspecto();
                    }
                    current = current.getNext();
                    count++;
                }
                j.getAspectos().ingresar(seleccionado);
                if (!j.getCampeones().contains(seleccionado.getC())){
                    j.getCampeones().add(seleccionado.getC());
                }
                return "Ha salido un aspecto y se ha ingresado al inventario: "+seleccionado.getNombreReal();
            }
            if (probable>probabilidadOrbe && probable<=probabilidadOrbe+probabilidadAspecto){
                int wat = rand.nextInt(campeones.size());
                Campeon seleccionado = campeones.get(wat);
                j.getCampeones().add(seleccionado);
                return "Ha salido un campeon y se ha ingresado al inventario: "+seleccionado.getNombre();
                
            }
            else{
                int wat =rand.nextInt(orbes.getSize());
                NodoSimpleOrbe current = orbes.getFirst();
                int count = 0;
                Orbe seleccionado = null;
                while (current.getNext()!=null){
                    if (count==wat){
                        seleccionado = current.getOrbe();
                    }
                    current = current.getNext();
                    count++;
                }
                j.getOrbes().ingresar(seleccionado);
                return "Ha salido un orbe y se ha ingresado al inventario: "+seleccionado.getNombre();
            }
        }
        else{
            throw new NullPointerException("No existe aquel orbe.");
        }
        
    }

    @Override
    public String obtenerFragmentosDeAspectoDelJugador(String nombreJugador) {
        Jugador j = null;
        Iterator<Usuario> it = usuarios.iterator();
        while(it.hasNext()){
            Usuario u = (Usuario) it.next();
            if (u instanceof Jugador){
                Jugador jo = (Jugador) u;
                if(jo.getNombre().equals(nombreJugador)){
                    j = jo;
                    break;
                }
            }
        }
        String text ="";
        NodoDobleAspecto current = j.getAspectos().getFirst();
        while (current!=null){
            text+=current.getAspecto().getNombreReal()+"\n";
            current=current.getNext();
        }
        if (text.equals("")){
            return "No hay aspectos.";
        }
        return text;
    }

    @Override
    public String reRoll(String nombreA1, String nombreA2, String nombreA3, String nombreJugador) {
        Jugador j = null;
        Iterator<Usuario> it = usuarios.iterator();
        while(it.hasNext()){
            Usuario u = (Usuario) it.next();
            if (u instanceof Jugador){
                Jugador jo = (Jugador) u;
                if(jo.getNombre().equals(nombreJugador)){
                    j = jo;
                    break;
                }
            }
            
        }
        if (j.getAspectos().buscar(nombreA1)!=null && j.getAspectos().buscar(nombreA2)!=null && j.getAspectos().buscar(nombreA3)!=null) {
            j.getAspectos().eliminar(nombreA1); //nombreA1->nombreReal
            j.getAspectos().eliminar(nombreA2); //nombreA2->nombreReal
            j.getAspectos().eliminar(nombreA3); //nombreA3->nombreReal
            Random random = new Random();
            int wat = random.nextInt(j.getCampeones().size());
            //Campeon seleccionado = j.getCampeones().get(wat);
            int probabilidadMitica = 5;
            int probabilidadLegendaria = 15;
            int probabilidadEpica = 30;
            int probabilidadNormal = 50;
            int wat2 = random.nextInt(probabilidadMitica+probabilidadLegendaria+probabilidadEpica+probabilidadNormal+1);
            String tipo = "";
            if (wat2<=probabilidadMitica){
                tipo = "Mitica";
            }
            if (wat2>probabilidadMitica && wat2<=probabilidadLegendaria+probabilidadMitica){
                tipo = "Legendaria";
            }
            if (wat2>probabilidadLegendaria+probabilidadMitica && wat2<=probabilidadLegendaria+probabilidadMitica+probabilidadEpica){
                tipo = "Epica";
            }
            if (wat2>probabilidadLegendaria+probabilidadMitica+probabilidadEpica && wat2<=probabilidadLegendaria+probabilidadMitica+probabilidadEpica+probabilidadNormal){
                tipo = "Normal";
            }
            int heap = 0;
            while (aspectos.getCantByType(tipo)==0){
                heap++;
                wat2 = random.nextInt(probabilidadMitica+probabilidadLegendaria+probabilidadEpica+probabilidadNormal+1);
                if (wat2<=probabilidadMitica){
                    tipo = "Mitica";
                }
                if (wat2>probabilidadMitica && wat2<=probabilidadLegendaria+probabilidadMitica){
                    tipo = "Legendaria";
                }
                if (wat2>probabilidadLegendaria+probabilidadMitica && wat2<=probabilidadLegendaria+probabilidadMitica+probabilidadEpica){
                    tipo = "Epica";
                }
                if (wat2>probabilidadLegendaria+probabilidadMitica+probabilidadEpica && wat2<=probabilidadLegendaria+probabilidadMitica+probabilidadEpica+probabilidadNormal){
                    tipo = "Normal";
                }
                if (heap==250){
                    throw new NullPointerException("Desbordamiento en el intento de obtener una mezcla aleatoria.");
                }
            }
            int cant = aspectos.getCantByType(tipo);
            NodoDobleAspecto current = aspectos.getFirst();
            int wat3 = random.nextInt(cant);
            Aspecto Aseleccionad = null;
            while (cant!=0){
                if (current.getAspecto().getTipo().equals(tipo)){
                    if (cant == wat3){
                        j.getAspectos().ingresar(current.getAspecto());
                        if (!j.getCampeones().contains(current.getAspecto().getC())){
                            j.getCampeones().add(current.getAspecto().getC());
                        }
                        Aseleccionad = current.getAspecto();
                        break;
                    }
                    cant--;
                }
                current = current.getNext();
            }
            return "Ha salido un aspecto "+tipo+":\n"+Aseleccionad.getNombreReal();
        } 
        else{
            throw new NullPointerException("Uno o mas de los aspectos no existe en el inventario del jugador");
        }
        
    }

    @Override
    public String obtenerMesConMasVentas() {
        Iterator<Compra> it = compras.iterator();
        int[] comprasMensuales = new int[12];
        while(it.hasNext()){
            Compra compra = (Compra) it.next();
            int mes = compra.getMes();
            comprasMensuales[mes-1]+=compra.getValor();
        }
        int max = -1;
        int maxMes = -1;
        for (int i = 0; i < comprasMensuales.length; i++) {
            if (comprasMensuales[i]>max){
                max = comprasMensuales[i];
                maxMes = i+1;
            }
        }
        return "El mes con mas ventas es "+maxMes+" con $"+max;
    }

    @Override
    public String obtenerAspectoMasComun() {
        NodoDobleAspecto current = aspectos.getFirst();
        int mayor = -1;
        Aspecto masyor = null;
        while(current!=null){
            if (current.getAspecto().getUso()>mayor){
                mayor = current.getAspecto().getUso();
                masyor = current.getAspecto();
            }
            current=current.getNext();
        }
        return "El aspecto mas común es "+masyor.getNombreReal()+" con "+mayor+" usos.";
    }

    @Override
    public String obtenerPersonasTXT() {
        String text = "";
        Iterator<Usuario> it = usuarios.iterator();
        while(it.hasNext()){
            Usuario u = (Usuario) it.next();
            if (u instanceof Admin){
                text+=u.getNombre()+","+u.getClave()+",Admin";
            }
            else{
                Jugador jugador = (Jugador) u;
                text+=u.getNombre()+","+u.getClave()+",player,"+jugador.getSaldoJugador();
                NodoSimpleOrbe current = jugador.getOrbes().getFirst();
                while(current!=null){
                    Orbe orb = current.getOrbe();
                    text+=","+orb.getNombre();
                    current = current.getNext();
                }
                Iterator<Campeon> it2 = jugador.getCampeones().iterator();
                while(it2.hasNext()){
                    Campeon campeon = (Campeon) it2.next();
                    text+=","+campeon.getNombre();
                    NodoDobleAspecto current2 = jugador.getAspectos().getFirst();
                    while(current2!=null){
                        Aspecto a = current2.getAspecto();
                        if (a.getC().getNombre().equals(campeon.getNombre())){
                            text+=","+a.getNombre();
                        }
                        current2 = current2.getNext();
                    }
                }
            }
            if (it.hasNext()){
                text+="\n";
            }
        }
        return text;
    }

    @Override
    public String obtenerAspectoTXT() {
        String text = "";
        Iterator<Campeon> it = campeones.iterator();
        while(it.hasNext()){
            Campeon c = (Campeon) it.next();
            text+=c.getNombre()+","+c.getCarril();
            NodoDobleAspecto current = aspectos.getFirst();
            while(current!=null){
                Aspecto a = current.getAspecto();
                if (a.getC().getNombre().equals(c.getNombre())){
                    text+=","+a.getNombre()+","+a.getTipo();
                }
                current = current.getNext();
            }
            if (it.hasNext()){
                text+="\n";
            }
        }
        return text;
    }

    @Override
    public String obtenerBalanceTXT() {
        String text = "";
        Iterator<Compra> it = compras.iterator();
        while(it.hasNext()){
            Compra c = (Compra) it.next();
            text+=c.getFecha()+","+c.getValor();
            if (it.hasNext()){
                text+="\n";
            }
        }
        return text;
    }

    @Override
    public String obtenerOrbesTXT() {
        String text = "";
        NodoSimpleOrbe current = orbes.getFirst();
        while(current!=null){
            text+=current.getOrbe().getNombre()+",";
            if (current.getNext()!=null){
                text+="\n";
            }
            current = current.getNext();
        }
        return text;
    }

    @Override
    public void asignarProbabilidadCampeon(double d) {
        Aspecto.setProbabilidad(d);
        
    }

    @Override
    public void asociarAspectoCampeon(String nombreCampeon, String nombreAspecto) {
        String nombreRealAspecto = nombreCampeon+" "+nombreAspecto;
        Iterator<Campeon> it = campeones.iterator();
        Campeon c = null;
        while (it.hasNext()){
            Campeon campeon = (Campeon) it.next();
            if (campeon.getNombre().equals(nombreCampeon)){
                c = campeon;
            }
        }
        Aspecto a = aspectos.buscar(nombreRealAspecto);
        if (c != null && a != null){
            a.setC(c);
            c.getAspectos().ingresar(a);
        }
        else{
            throw new NullPointerException("No se pudo asociar "+nombreCampeon+" con "+nombreAspecto);
        }
    }

    @Override
    public void asignarProbabilidadOrbe(double d) {
        Orbe.setProbabilidad(d);
        
    }

    @Override
    public void asignarProbabilidadAspecto(double d) {
        Aspecto.setProbabilidad(d);
        
    }

    @Override
    public String obtenerDatosJugadores() {
        String text = "";
        String[] carriles = {"SUPPORT","ADC","MID","TOP","JUNGLER"};
        Iterator<Usuario> it = usuarios.iterator();
        while(it.hasNext()){
            Usuario u = (Usuario) it.next();
            if (u instanceof Jugador){
                Jugador j = (Jugador) u;
                Iterator<Campeon> it2 = j.getCampeones().iterator();
                int[] preferenciaCarriles = new int[5];
                while(it2.hasNext()){
                    Campeon c = (Campeon) it2.next();
                    if (c.getCarril().equals("SUPPORT")){
                        preferenciaCarriles[0]+=1;
                    }
                    if (c.getCarril().equals("ADC")){
                        preferenciaCarriles[1]+=1;
                    }
                    if (c.getCarril().equals("MID")){
                        preferenciaCarriles[2]+=1;
                    }
                    if (c.getCarril().equals("TOP")){
                        preferenciaCarriles[3]+=1;
                    }
                    if (c.getCarril().equals("JUNGLER")){
                        preferenciaCarriles[4]+=1;
                    }
                }
                int mayor = -1;
                for (int i = 0; i < preferenciaCarriles.length; i++) {
                    if (preferenciaCarriles[i]>mayor){
                        mayor = preferenciaCarriles[i];
                    }
                }
                ArrayList<Integer> veces = new ArrayList<Integer>();
                for (int ja = 0; ja < preferenciaCarriles.length; ja++) {
                    if (preferenciaCarriles[ja]==mayor){
                        veces.add(ja);
                    }
                }
                int cantidad = veces.size();
                if (cantidad>1){
                    text+=j.getNombre();
                    for (int k = 0; k < cantidad; k++) {
                        String carrilazo = carriles[veces.get(k)];
                        text+=", "+carrilazo;
                    }
                }
                else{
                    text+=j.getNombre()+" ,"+carriles[veces.get(0)];
                }
                text+="\n";
            }
        }
        return text;
    }

    @Override
    public int getSaldo(String nombreJugador) {
        Iterator<Usuario> it = usuarios.iterator();
        while(it.hasNext()){
            Usuario u = (Usuario) it.next();
            if (u instanceof Jugador){
                Jugador jo = (Jugador) u;
                if(jo.getNombre().equals(nombreJugador)){
                    return jo.getSaldoJugador();
                }
            }
            
        }
        return 0;
    }
    
}