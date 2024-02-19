package controlador.Academico;

import controlador.TDA.listas.DynamicList;
import controlador.TDA.listas.Exception.EmptyException;
import controlador.dao.AdaptadorDao;
import modelo.Ciclo;

public class CicloBD extends AdaptadorDao<Ciclo> {

    private DynamicList<Ciclo> ciclos;
    private Ciclo ciclo;

    public CicloBD() {
        super(Ciclo.class);
    }

    public DynamicList<Ciclo> getCiclos() {
        ciclos = all();
        return ciclos;
    }

    public void setCiclos(DynamicList<Ciclo> ciclos) {
        this.ciclos = ciclos;
    }

    public Ciclo getCiclo() {
        if (ciclo == null) {
            ciclo = new Ciclo();
        }
        return ciclo;
    }

    public void setCiclo(Ciclo ciclo) {
        this.ciclo = ciclo;
    }

    @Override
    public Boolean persist(Ciclo obj){
        obj.setId(all().getLength() + 1);
        return super.persist(obj);
    }

    public DynamicList<Ciclo> buscarLineal(DynamicList<Ciclo> lista, String campo, String valorBuscado) throws EmptyException {
        Ciclo ciclos[] = lista.toArray();
        DynamicList<Ciclo> listaBusqueda = new DynamicList<>();
        for (int i = 0; i < lista.getLength(); i++) {
            Ciclo ciclo = ciclos[i];
            if (ciclo.compareCampo(campo, valorBuscado) == 0) {
                listaBusqueda.add(ciclo);
            }
        }
        return listaBusqueda;
    }

    public Ciclo buscarBinaria(String campo, String valorBuscado) throws EmptyException {
        int inicio = 0;
        DynamicList<Ciclo> lista = all();
        int fin = lista.getLength() - 1;
        Ciclo ciclos[] = lista.toArray();
        while (inicio <= fin) {
            int medio = (inicio + fin) / 2;
            Ciclo ciclo = ciclos[medio];
            int comparacion = ciclo.compareCampo(campo, valorBuscado);
            if (comparacion == 0) {
                return ciclo;
            } else if (comparacion < 0) {
                inicio = medio + 1;
            } else {
                fin = medio - 1;
            }
        }
        return null;
    }
    
    public boolean buscarNombre(String text) throws EmptyException {
        ciclos = all();
        for (int i = 0; i < ciclos.getLength(); i++) {
            if (ciclos.getInfo(i).getNombre().equals(text)) {
                return true;
            }
        }
        return false;
    }
}
