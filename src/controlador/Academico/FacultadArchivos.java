package controlador.Academico;

import controlador.DAO.DaoImplement;
import controlador.TDA.listas.DynamicList;
import modelo.Facultad;

public class FacultadArchivos extends DaoImplement<Facultad> {

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

    public Boolean persist() {
        facultad.setId(all().getLength());
        return persist(facultad);
    }
}
