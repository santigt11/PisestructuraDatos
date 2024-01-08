package controlador.Matriculas;

import controlador.TDA.listas.DynamicList;
import controlador.TDA.listas.Exception.EmptyException;
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
        obj.setId(all().getLength() + 1);
        return super.persist(obj);
    }

    public DynamicList<PeriodoAcademico> buscarLineal(DynamicList<PeriodoAcademico> lista, String campo, String valorBuscado) throws EmptyException {
        PeriodoAcademico periodosAcademicos[] = lista.toArray();
        DynamicList<PeriodoAcademico> listaBusqueda = new DynamicList<>();
        for (int i = 0; i < lista.getLength(); i++) {
            PeriodoAcademico periodoAcademico = periodosAcademicos[i];
            if (periodoAcademico.compareCampo(campo, valorBuscado) == 0) {
                listaBusqueda.add(periodoAcademico);
            }
        }
        return listaBusqueda;
    }

    public PeriodoAcademico buscarBinaria(String campo, String valorBuscado) throws EmptyException {
        int inicio = 0;
        DynamicList<PeriodoAcademico> lista = all();
        int fin = lista.getLength() - 1;
        PeriodoAcademico periodosAcademicos[] = lista.toArray();
        while (inicio <= fin) {
            int medio = (inicio + fin) / 2;
            PeriodoAcademico periodoAcademico = periodosAcademicos[medio];
            int comparacion = periodoAcademico.compareCampo(campo, valorBuscado);
            if (comparacion == 0) {
                return periodoAcademico;
            } else if (comparacion < 0) {
                inicio = medio + 1;
            } else {
                fin = medio - 1;
            }
        }
        return null;
    }

}
