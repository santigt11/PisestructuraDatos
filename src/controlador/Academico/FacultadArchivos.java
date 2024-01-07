package controlador.Academico;

import controlador.TDA.listas.DynamicList;
import controlador.dao.AdaptadorDao;
import modelo.Facultad;

public class FacultadArchivos extends AdaptadorDao<Facultad> {

    private DynamicList<Facultad> facultades;
    private Facultad facultad;

    public FacultadArchivos() {
        super(Facultad.class);
    }

    public DynamicList<Facultad> getFacultades() {
        facultades = all();
        return facultades;
    }

    public void setFacultades(DynamicList<Facultad> facultades) {
        this.facultades = facultades;
    }

    public Facultad getFacultad() {
        if (facultad == null) {
            facultad = new Facultad();
        }
        return facultad;
    }

    public void setFacultad(Facultad facultad) {
        this.facultad = facultad;
    }

    @Override
    public Integer persist(Facultad obj) throws Exception {
        obj.setId(all().getLength()+1);
        return super.persist(obj);
    }
}
