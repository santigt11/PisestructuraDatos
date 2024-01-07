
package controlador.Academico;

import controlador.TDA.listas.DynamicList;
import controlador.dao.AdaptadorDao;
import modelo.PeriodoAcademico;

public class PeriodoArchivos extends AdaptadorDao<PeriodoAcademico> {
    
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

    @Override
    public Integer persist(PeriodoAcademico obj) throws Exception {
        obj.setId(all().getLength()+1);
        return super.persist(obj);
    }
}
