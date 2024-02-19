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
        Asignacion contratos[] = lista.toArray();
        DynamicList<Asignacion> listaBusqueda = new DynamicList<>();
        for (int i = 0; i < lista.getLength(); i++) {
            Asignacion contrato = contratos[i];
            if (contrato.compareCampo(campo, valorBuscado) == 0) {
                listaBusqueda.add(contrato);
            }
        }
        return listaBusqueda;
    }

    public Asignacion buscarBinaria(String campo, String valorBuscado) throws EmptyException {
        int inicio = 0;
        DynamicList<Asignacion> lista = all();
        int fin = lista.getLength() - 1;
        Asignacion contratos[] = lista.toArray();
        while (inicio <= fin) {
            int medio = (inicio + fin) / 2;
            Asignacion contrato = contratos[medio];
            int comparacion = contrato.compareCampo(campo, valorBuscado);
            if (comparacion == 0) {
                return contrato;
            } else if (comparacion < 0) {
                inicio = medio + 1;
            } else {
                fin = medio - 1;
            }
        }
        return null;
    }

}
