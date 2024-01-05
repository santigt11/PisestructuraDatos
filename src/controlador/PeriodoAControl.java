package controlador;

import controlador.TDA.listas.DynamicList;
import controlador.TDA.listas.Exception.EmptyException;
import modelo.PeriodoAcademico;

public class PeriodoAControl {
    private PeriodoAcademico periodo = new PeriodoAcademico();
    private DynamicList<PeriodoAcademico> periodos;

    public PeriodoAControl(PeriodoAcademico periodo) {
        this.periodo = periodo;
    }

    public PeriodoAControl() {
        this.periodos = new DynamicList<>();
        
    }

    public Boolean guardar() {
        
        try {
            getPeriodo().setId(getPeriodos().getLength());
            getPeriodos().add(getPeriodo());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Integer posVerificar() throws EmptyException {
        
        Integer bandera = 0;

        for (Integer i = 0; i <= this.periodos.getLength(); i++) {
            
            if (this.getPeriodos().getInfo(i) == null) {
                bandera = i;
                break;
            }
        }
        return bandera;
    }

    public void imprimir() throws EmptyException {
        for (int i = 0; i < this.getPeriodos().getLength(); i++) {
            System.out.println(getPeriodos().getInfo(i));
        }
    }

    public PeriodoAcademico getPeriodo() {
        if (periodo == null) {
            periodo = new PeriodoAcademico();
        }
        return periodo;
    }

    public void setPeriodo(PeriodoAcademico malla) {
        this.periodo = malla;
    }

    public DynamicList<PeriodoAcademico> getPeriodos() {
        return periodos;
    }

    public void setPeriodos(DynamicList<PeriodoAcademico> mallas) {
        this.periodos = mallas;
    }
}
