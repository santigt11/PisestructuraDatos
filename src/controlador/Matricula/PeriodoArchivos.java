package controlador.Matricula;

import controlador.Academico.*;
import controlador.DAO.DaoImplement;
import controlador.TDA.listas.DynamicList;
import modelo.PeriodoAcademico;

public class PeriodoArchivos extends DaoImplement<PeriodoAcademico> {
    
    private DynamicList<PeriodoAcademico> periodos;
    private PeriodoAcademico periodo;

    public PeriodoArchivos() {
        super(PeriodoAcademico.class);
    }

    public DynamicList<PeriodoAcademico> getPeriodos() {
        periodos = all();
        return periodos;
    }

    public void setPeriodos(DynamicList<PeriodoAcademico> periodos) {
        this.periodos = periodos;
    }

    public PeriodoAcademico getPeriodo() {
        if (periodo == null) {
            periodo = new PeriodoAcademico();
        }
        return periodo;
    }

    public void setPeriodo(PeriodoAcademico periodo) {
        this.periodo = periodo;
    }

    public Boolean persist() {
        periodo.setId(all().getLength());
        return persist(periodo);
    }
}
