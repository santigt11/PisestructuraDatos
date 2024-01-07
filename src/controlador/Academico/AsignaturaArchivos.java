package controlador.Academico;

import controlador.DAO.Conexion;
import controlador.TDA.listas.DynamicList;
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

    public void setCarrera(Asignatura asignatura) {
        this.asignatura = asignatura;
    }

    @Override
    public Integer persist(Asignatura obj) throws Exception {
        obj.setId(all().getLength()+1);
        return super.persist(obj);
    }
}
