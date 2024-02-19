package vista.listas.tablas;

import controlador.Academico.AsignaturaBD;
import controlador.Admin.PersonaBD;
import controlador.TDA.listas.DynamicList;
import controlador.TDA.listas.Exception.EmptyException;
import javax.swing.table.AbstractTableModel;
import modelo.Asignacion;

public class TablaContrato extends AbstractTableModel {

    private DynamicList<Asignacion> contratos;
    private PersonaBD filePersona = new PersonaBD();
    private AsignaturaBD fileAsignatura = new AsignaturaBD();

    @Override
    public int getRowCount() {
        return contratos.getLength();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        try {
            Asignacion ct = contratos.getInfo(rowIndex);
            switch (columnIndex) {
                case 0:
                    return (ct != null) ? filePersona.buscarBinaria("dni", ct.getDniPersona()).getApellido() + " " + filePersona.buscarBinaria("dni", ct.getDniPersona()).getNombre(): " ";
                case 1:
                    return (ct != null) ? fileAsignatura.buscarBinaria("codigo", ct.getAsignatura_CODIGO()).getCodigo(): "";
                case 2:
                    return (ct != null) ? ct.getFechaRegistro(): "";
                case 3:
                    return (ct != null) ? ct.getFechaCulminacion(): "";
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
                return "FECHA DE REGISTRO";
            case 3:
                return "FECHA DE CULMINACION";
            default:
                return null;
        }
    }

    public DynamicList<Asignacion> getContratos() {
        return contratos;
    }

    public void setContratos(DynamicList<Asignacion> contrato) {
        this.contratos = contrato;
    }

}