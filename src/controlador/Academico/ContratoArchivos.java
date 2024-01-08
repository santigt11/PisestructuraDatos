package controlador.Academico;

import controlador.TDA.listas.DynamicList;
import controlador.TDA.listas.Exception.EmptyException;
import controlador.dao.AdaptadorDao;
import modelo.Contrato;

public class ContratoArchivos extends AdaptadorDao<Contrato> {

    private DynamicList<Contrato> contratos;
    private Contrato contrato;

    public ContratoArchivos() {
        super(Contrato.class);
    }

    public DynamicList<Contrato> getContratosTodos() {
        contratos = all();
        return contratos;
    }
    
    public DynamicList<Contrato> getContratos() {
        return contratos;
    }
    
    public void setAsignaturas(DynamicList<Contrato> contratos) {
        this.contratos = contratos;
    }

    public Contrato getContrato() {
        if (contrato == null) {
            contrato = new Contrato();
        }
        return contrato;
    }

    public void setContrato(Contrato contrato) {
        this.contrato = contrato;
    }

    @Override
    public Integer persist(Contrato obj) throws Exception {
        obj.setId(all().getLength() + 1);
        return super.persist(obj);
    }

    public DynamicList<Contrato> buscarLineal(DynamicList<Contrato> lista, String campo, String valorBuscado) throws EmptyException {
        Contrato contratos[] = lista.toArray();
        DynamicList<Contrato> listaBusqueda = new DynamicList<>();
        for (int i = 0; i < lista.getLength(); i++) {
            Contrato contrato = contratos[i];
            if (contrato.compareCampo(campo, valorBuscado) == 0) {
                listaBusqueda.add(contrato);
            }
        }
        return listaBusqueda;
    }

    public Contrato buscarBinaria(String campo, String valorBuscado) throws EmptyException {
        int inicio = 0;
        DynamicList<Contrato> lista = all();
        int fin = lista.getLength() - 1;
        Contrato contratos[] = lista.toArray();
        while (inicio <= fin) {
            int medio = (inicio + fin) / 2;
            Contrato contrato = contratos[medio];
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
