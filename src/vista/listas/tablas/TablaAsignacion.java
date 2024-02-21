package vista.listas.tablas;

import controlador.Academico.AsignaturaBD;
import controlador.Admin.PersonaBD;
import controlador.Login.UsuarioBD;
import controlador.Matriculas.PeriodoBD;
import controlador.TDA.listas.DynamicList;
import controlador.TDA.listas.Exception.EmptyException;
import javax.swing.table.AbstractTableModel;
import modelo.Asignacion;
import modelo.Persona;
import modelo.Usuario;

public class TablaAsignacion extends AbstractTableModel {

    private DynamicList<Asignacion> asignaciones;
    private PersonaBD filePersona = new PersonaBD();
    private AsignaturaBD fileAsignatura = new AsignaturaBD();
    private UsuarioBD fileUsuario = new UsuarioBD();
    private PeriodoBD filePeriodo = new PeriodoBD();

    @Override
    public int getRowCount() {
        return asignaciones.getLength();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        try {
            Asignacion asignacion = asignaciones.getInfo(rowIndex);
            Usuario usuario = fileUsuario.getUsuariosTodos().getInfo(asignacion.getUsuario_ID());
            Persona docente = filePersona.buscarBinariaUnico("dni", usuario.getPersona_DNI());
            switch (columnIndex) {
                case 0:
                    return (asignacion != null) ? docente.getApellido() + " " + docente.getNombre(): " ";
                case 1:
                    return (asignacion != null) ? fileAsignatura.buscarBinaria("codigo", asignacion.getAsignatura_CODIGO()).getCodigo(): "";
                case 2:
                    return (asignacion != null) ? filePeriodo.getPeriodos().getInfo(asignacion.getPeriodoAcademico_ID()): "";
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
                return "DOCENTE";
            case 1:
                return "ASIGNATURA";
            case 2:
                return "PERIODO ACADEMICO";
            default:
                return null;
        }
    }

    public DynamicList<Asignacion> getAsignaciones() {
        return asignaciones;
    }

    public void setAsignaciones(DynamicList<Asignacion> asignaciones) {
        this.asignaciones = asignaciones;
    }

}
