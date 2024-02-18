package controlador.Tutorias;

import controlador.DAO.Conexion;
import controlador.TDA.listas.DynamicList;
import controlador.TDA.listas.Exception.EmptyException;
import controlador.dao.AdaptadorDao;
import modelo.Tutoria;

public class TutoriaBD extends AdaptadorDao<Tutoria> {

    private DynamicList<Tutoria> tutorias;
    private Tutoria tutoria;

    public TutoriaBD(DynamicList<Tutoria> tutorias, Tutoria tutoria) {
        super(Tutoria.class);
        this.tutorias = tutorias;
        this.tutoria = tutoria;
    }

    public TutoriaBD() {
        super(Tutoria.class);
    }

    public DynamicList<Tutoria> getTutorias() {
        tutorias = all();
        return tutorias;
    }

    public void setTutorias(DynamicList<Tutoria> tutorias) {
        this.tutorias = tutorias;
    }

    public Tutoria getTutoria() {
        if (tutoria == null) {
            tutoria = new Tutoria();
        }
        return tutoria;
    }

    public void setTutoria(Tutoria tutoria) {
        this.tutoria = tutoria;
    }

    @Override
    public Boolean persist(Tutoria obj){
        obj.setId(all().getLength() + 1);
        return super.persist(obj);
    }

    public DynamicList<Tutoria> buscarLineal(DynamicList<Tutoria> lista, String campo, String valorBuscado) throws EmptyException {
        Tutoria tutorias[] = lista.toArray();
        DynamicList<Tutoria> listaBusqueda = new DynamicList<>();
        for (int i = 0; i < lista.getLength(); i++) {
            Tutoria tutoria = tutorias[i];
            if (tutoria.compareCampo(campo, valorBuscado) == 0) {
                listaBusqueda.add(tutoria);
            }
        }
        return listaBusqueda;
    }

    public Tutoria buscarBinaria(String campo, String valorBuscado) throws EmptyException {
        int inicio = 0;
        DynamicList<Tutoria> lista = all();
        int fin = lista.getLength() - 1;
        Tutoria tutorias[] = lista.toArray();
        while (inicio <= fin) {
            int medio = (inicio + fin) / 2;
            Tutoria tutoria = tutorias[medio];
            int comparacion = tutoria.compareCampo(campo, valorBuscado);
            if (comparacion == 0) {
                return tutoria;
            } else if (comparacion < 0) {
                inicio = medio + 1;
            } else {
                fin = medio - 1;
            }
        }
        return null;
    }
}
