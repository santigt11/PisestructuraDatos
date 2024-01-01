package controlador.Academico;

import controlador.DAO.DaoImplement;
import controlador.TDA.listas.DynamicList;
import modelo.Contrato;

public class ContratoArchivos extends DaoImplement<Contrato> {
    
    private DynamicList<Contrato> contratos;
    private Contrato contrato;

    public ContratoArchivos() {
        super(Contrato.class);
    }

    public DynamicList<Contrato> getContratos() {
        contratos = all();
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

    public Boolean persist() {
        contrato.setId(all().getLength() + 1);
        return persist(contrato);
    }
}
