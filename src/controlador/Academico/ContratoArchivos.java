package controlador.Academico;

import controlador.TDA.listas.DynamicList;
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
        obj.setId(all().getLength()+1);
        return super.persist(obj);
    }
}
