package controlador;

import controlador.TDA.listas.DynamicList;
import modelo.Contrato;

public class ContratoControl {
    private Contrato contrato = new Contrato();
    private DynamicList<Contrato> contratos;

    public ContratoControl(Contrato contrato) {
        this.contrato = contrato;
    }

    public ContratoControl() {
        this.contratos = new DynamicList<>();
        
    }

    public Boolean guardar() {
        
        try {
            getContrato().setId(getContratos().getLength());
            getContratos().add(getContrato());
            return true;
        } catch (Exception e) {
            return false;
        }
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

    public DynamicList<Contrato> getContratos() {
        return contratos;
    }

    public void setFacultades(DynamicList<Contrato> contratos) {
        this.contratos = contratos;
    }
}
