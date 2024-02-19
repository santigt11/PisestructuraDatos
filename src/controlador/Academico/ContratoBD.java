package controlador.Academico;

import controlador.TDA.listas.DynamicList;
import controlador.TDA.listas.Exception.EmptyException;
import controlador.dao.AdaptadorDao;
import modelo.Asignacion;

public class ContratoBD extends AdaptadorDao<Asignacion> {

    private DynamicList<Asignacion> contratos;
    private Asignacion contrato;

    public ContratoBD() {
        super(Asignacion.class);
    }

    public DynamicList<Asignacion> getContratosTodos() {
        contratos = all();
        return contratos;
    }
    
    public DynamicList<Asignacion> getContratos() {
        return contratos;
    }
    
    public void setContratos(DynamicList<Asignacion> contratos) {
        this.contratos = contratos;
    }

    public Asignacion getContrato() {
        if (contrato == null) {
            contrato = new Asignacion();
        }
        return contrato;
    }

    public void setContrato(Asignacion contrato) {
        this.contrato = contrato;
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
