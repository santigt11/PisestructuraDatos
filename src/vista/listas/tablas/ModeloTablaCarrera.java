package vista.listas.tablas;

import controlador.TDA.listas.DynamicList;
import controlador.TDA.listas.Exception.EmptyException;
import javax.swing.table.AbstractTableModel;
import modelo.Carrera;

public class ModeloTablaCarrera extends AbstractTableModel {

    private DynamicList<Carrera> carreras;

    @Override
    public int getRowCount() {
        return carreras.getLength();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        try {
            Carrera cr = carreras.getInfo(rowIndex);
            switch (columnIndex) {
                case 0:
                    return (cr != null) ? cr.getNombre() : "";
                case 2:
                    return (cr != null) ? cr.getNumCiclos() : "";
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
                return "NOMBRE";
            case 1:
                return "NUMERO DE CICLOS";
            default:
                return null;
        }
    }

    public DynamicList<Carrera> getCarreras() {
        return carreras;
    }

    public void setCarreras(DynamicList<Carrera> carrera) {
        this.carreras = carrera;
    }

}
