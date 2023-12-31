package controlador.Academico;

import controlador.DAO.DaoImplement;
import controlador.TDA.listas.DynamicList;
import controlador.TDA.listas.Exception.EmptyException;
import modelo.Asignatura;

public class AsignaturaArchivos extends DaoImplement<Asignatura> {
    
    private DynamicList<Asignatura> asignaturas;
    private Asignatura asignatura;

    public AsignaturaArchivos() {
        super(Asignatura.class);
    }

    public DynamicList<Asignatura> getAsignaturas() {
        asignaturas = all();
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

    public void setCarrera(Asignatura asignatura) {
        this.asignatura = asignatura;
    }

    public Boolean persist() {
        asignatura.setId(all().getLength());
        return persist(asignatura);
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
