package vista.listas.tablas;

import controlador.TDA.listas.DynamicList;
import controlador.TDA.listas.Exception.EmptyException;
import javax.swing.table.AbstractTableModel;
import modelo.Facultad;

public class TablaFacultad extends AbstractTableModel {

    private DynamicList<Facultad> facultades;

    @Override
    public int getRowCount() {
        return facultades.getLength();
    }

    @Override
    public int getColumnCount() {
        return 1;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        try {
            Facultad fc = facultades.getInfo(rowIndex);
            switch (columnIndex) {
                case 0:
                    return (fc != null) ? fc.getNombre() : " ";
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
                return null;
            default:
                return null;
        }
    }

    public DynamicList<Facultad> getFacultades() {
        return facultades;
    }

    public void setFacultades(DynamicList<Facultad> facultad) {
        this.facultades = facultad;
    }

}
