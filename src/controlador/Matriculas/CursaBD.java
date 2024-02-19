package controlador.Matriculas;

import controlador.TDA.listas.DynamicList;
import controlador.TDA.listas.Exception.EmptyException;
import controlador.dao.AdaptadorDao;
import modelo.Cursa;

public class CursaBD extends AdaptadorDao<Cursa> {

    private DynamicList<Cursa> cursas;
    private Cursa cursa;

    public CursaBD() {
        super(Cursa.class);
        cursas = new DynamicList<>();
    }

    public DynamicList<Cursa> getCursasTodas() {
        cursas = all();
        return cursas;
    }
    
    public DynamicList<Cursa> getCursas() {
        return cursas;
    }

    public void setCursas(DynamicList<Cursa> cursas) {
        this.cursas = cursas;
    }

    public Cursa getCursa() {
        if (cursa == null) {
            cursa = new Cursa();
        }
        return cursa;
    }

    public void setCursa(Cursa cursa) {
        this.cursa = cursa;
    }

    @Override
    public Boolean persist(Cursa obj){
        obj.setId(all().getLength() + 1);
        return super.persist(obj);
    }

    public DynamicList<Cursa> buscarLineal(DynamicList<Cursa> lista, String campo, String valorBuscado) throws EmptyException {
        Cursa cursas[] = lista.toArray();
        DynamicList<Cursa> listaBusqueda = new DynamicList<>();
        for (int i = 0; i < lista.getLength(); i++) {
            Cursa cursa = cursas[i];
            if (cursa.compareCampo(campo, valorBuscado) == 0) {
                listaBusqueda.add(cursa);
            }
        }
        return listaBusqueda;
    }

    public Cursa buscarBinaria(String campo, String valorBuscado) throws EmptyException {
        int inicio = 0;
        DynamicList<Cursa> lista = all();
        int fin = lista.getLength() - 1;
        Cursa cursas[] = lista.toArray();
        while (inicio <= fin) {
            int medio = (inicio + fin) / 2;
            Cursa cursa = cursas[medio];
            int comparacion = cursa.compareCampo(campo, valorBuscado);
            if (comparacion == 0) {
                return cursa;
            } else if (comparacion < 0) {
                inicio = medio + 1;
            } else {
                fin = medio - 1;
            }
        }
        return null;
    }

}
