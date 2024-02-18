package vista.listas.tablas;


import controlador.Matriculas.PeriodoArchivos;
import controlador.Admin.PersonaArchivos;
import controlador.TDA.listas.DynamicList;
import controlador.TDA.listas.Exception.EmptyException;
import javax.swing.table.AbstractTableModel;
import modelo.Matricula;

public class TablaMatricula extends AbstractTableModel {

    private DynamicList<Matricula> matriculas;
    private PersonaArchivos filePersona = new PersonaArchivos();
    private PeriodoArchivos filePeriodo = new PeriodoArchivos();
    

    @Override
    public int getRowCount() {
        return matriculas.getLength();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        try {
            Matricula mt = matriculas.getInfo(rowIndex);
            switch (columnIndex) {
                case 0:
                    return (mt != null) ? filePersona.buscarBinaria("dni", mt.getPersona_DNI()).getApellido() + " " + filePersona.buscarBinaria("dni", mt.getPersona_DNI()).getNombre(): " ";
                case 1:
                    return (mt != null) ? mt.getFecha(): "";
                case 2:
                    return (mt != null) ? filePeriodo.getPeriodos().getInfo(mt.getPeriodoAcademico_ID()).getFechaIncio() + " " + filePeriodo.getPeriodos().getInfo(mt.getPeriodoAcademico_ID()).getFechaFin(): "";
                case 3:
                    return (mt != null) ? mt.getExpActivo()? "ACTIVO" : "INACTIVO" : "";
                default:
                    return null;
            }
        } catch (EmptyException ex) {
            return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "ESTUDIANTE";
            case 1:
                return "FECHA DE REGISTRO";
            case 2:
                return "PERIODO ACADEMICO";
            case 3:
                return "EXPEDIENTE";
            default:
                return null;
        }
    }

    public DynamicList<Matricula> getMatriculas() {
        return matriculas;
    }

    public void setMatriculas(DynamicList<Matricula> matriculas) {
        this.matriculas = matriculas;
    }   

}
