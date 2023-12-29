package vista.listas.tablas;

import controlador.TDA.listas.DynamicList;
import controlador.TDA.listas.Exception.EmptyException;
import javax.swing.table.AbstractTableModel;
import modelo.Asignatura;

public class ModeloTablaAsignaturas extends AbstractTableModel {

    private DynamicList<Asignatura> asignaturas;

    @Override
    public int getRowCount() {
        return asignaturas.getLength();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        try {
            Asignatura as = asignaturas.getInfo(rowIndex);
            switch (columnIndex) {
                case 0:
                    return (as != null) ? as.getCodigo() : " ";
                case 1:
                    return (as != null) ? as.getNombre() : "";
                case 2:
                    return (as != null) ? as.getTotalHoras() : "";
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
                return "CODIGO";
            case 1:
                return "NOMBRE";
            case 2:
                return "TOTAL DE HORAS";
            default:
                return null;
        }
    }

    public DynamicList<Asignatura> getAsignaturas() {
        return asignaturas;
    }

    public void setAsignaturas(DynamicList<Asignatura> asignatura) {
        this.asignaturas = asignatura;
    }

}
