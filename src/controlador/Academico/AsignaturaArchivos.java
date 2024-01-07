package controlador.Academico;

import controlador.DAO.Conexion;
import controlador.TDA.listas.DynamicList;
import controlador.TDA.listas.Exception.EmptyException;
import controlador.dao.AdaptadorDao;
import modelo.Asignatura;

public class AsignaturaArchivos extends AdaptadorDao<Asignatura> {

    private DynamicList<Asignatura> asignaturas;
    private Asignatura asignatura;

    public AsignaturaArchivos() {
        super(Asignatura.class);
    }

    public AsignaturaArchivos(DynamicList<Asignatura> asignaturas, Asignatura asignatura, Class clazz) {
        super(clazz);
        this.asignaturas = asignaturas;
        this.asignatura = asignatura;
    }

    public DynamicList<Asignatura> getAsignaturasTodas() {
        asignaturas = all();
        return asignaturas;
    }

    public DynamicList<Asignatura> getAsignaturas() {
        return asignaturas;
    }

    public void setAsignaturas(DynamicList<Asignatura> asignaturas) {
        this.asignaturas = asignaturas;
    }

    public Asignatura getAsignatura() {
        if (asignatura == null) {
            asignatura = new Asignatura();
        }
        return asignatura;
    }

    public void setAsignatura(Asignatura asignatura) {
        this.asignatura = asignatura;
    }

    @Override
    public Integer persist(Asignatura obj) throws Exception {
        obj.setId(all().getLength() + 1);
        return super.persist(obj);
    }

    public DynamicList<Asignatura> buscarLineal(String campo, String valorBuscado) throws EmptyException {
        DynamicList<Asignatura> lista = all();
        Asignatura asignaturas[] = lista.toArray();
        DynamicList<Asignatura> listaBusqueda = new DynamicList<>();
        for (int i = 0; i < lista.getLength(); i++) {
            Asignatura asignatura = asignaturas[i];
            if (asignatura.compareCampo(campo, valorBuscado) == 0) {
                listaBusqueda.add(asignatura);
            }
        }
        return listaBusqueda;
    }

    public Asignatura buscarBinaria(String campo, String valorBuscado) throws EmptyException {
        int inicio = 0;
        DynamicList<Asignatura> lista = all();
        int fin = lista.getLength() - 1;
        Asignatura asignaturas[] = lista.toArray();
        while (inicio <= fin) {
            int medio = (inicio + fin) / 2;
            Asignatura asignatura = asignaturas[medio];
            int comparacion = asignatura.compareCampo(campo, valorBuscado);
            if (comparacion == 0) {
                return asignatura;
            } else if (comparacion < 0) {
                inicio = medio + 1;
            } else {
                fin = medio - 1;
            }
        }
        return null;
    }

    public boolean buscarCodigo(String text) throws EmptyException {
        asignaturas = all();
        for (int i = 0; i < asignaturas.getLength(); i++) {
            if (asignaturas.getInfo(i).getCodigo().equals(text)) {
                return true;
            }
        }
        return false;
    }
}
