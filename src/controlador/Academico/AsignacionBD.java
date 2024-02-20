package controlador.Academico;

import controlador.TDA.listas.DynamicList;
import controlador.TDA.listas.Exception.EmptyException;
import controlador.dao.AdaptadorDao;
import modelo.Asignacion;

public class AsignacionBD extends AdaptadorDao<Asignacion> {

    private DynamicList<Asignacion> asignaciones;
    private Asignacion asignacion;

    public AsignacionBD() {
        super(Asignacion.class);
    }

    public DynamicList<Asignacion> getAsignacionesTodos() {
        asignaciones = all();
        return asignaciones;
    }
    
    public DynamicList<Asignacion> getAsignaciones() {
        if (asignaciones == null) {
            asignaciones = new DynamicList<>();
        }
        return asignaciones;
    }
    
    public void setAsignaciones(DynamicList<Asignacion> asignaciones) {
        this.asignaciones = asignaciones;
    }

    public Asignacion getAsignacion() {
        if (asignacion == null) {
            asignacion = new Asignacion();
        }
        return asignacion;
    }

    public void setAsignacion(Asignacion asignacion) {
        this.asignacion = asignacion;
    }

    @Override
    public Boolean persist(Asignacion obj){
        obj.setId(all().getLength() + 1);
        return super.persist(obj);
    }

    public DynamicList<Asignacion> buscarLineal(DynamicList<Asignacion> lista, String campo, String valorBuscado) throws EmptyException {
        Asignacion asignaciones[] = lista.toArray();
        DynamicList<Asignacion> listaBusqueda = new DynamicList<>();
        for (int i = 0; i < lista.getLength(); i++) {
            Asignacion asignacion = asignaciones[i];
            if (asignacion.compareCampo(campo, valorBuscado) == 0) {
                listaBusqueda.add(asignacion);
            }
        }
        return listaBusqueda;
    }

    public Asignacion buscarBinaria(DynamicList<Asignacion> lista, String campo, String valorBuscado) throws EmptyException {
        int inicio = 0;
        int fin = lista.getLength() - 1;
        Asignacion asignaciones[] = lista.toArray();
        while (inicio <= fin) {
            int medio = (inicio + fin) / 2;
            Asignacion asignacion = asignaciones[medio];
            int comparacion = asignacion.compareCampo(campo, valorBuscado);
            if (comparacion == 0) {
                return asignacion;
            } else if (comparacion < 0) {
                inicio = medio + 1;
            } else {
                fin = medio - 1;
            }
        }
        return null;
    }

}
