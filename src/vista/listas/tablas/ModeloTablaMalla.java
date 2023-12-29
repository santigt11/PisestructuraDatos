package vista.listas.tablas;

import controlador.TDA.listas.DynamicList;
import controlador.TDA.listas.Exception.EmptyException;
import javax.swing.table.AbstractTableModel;
import modelo.MallaCurricular;

public class ModeloTablaMalla extends AbstractTableModel {

    private DynamicList<MallaCurricular> mallas;

    @Override
    public int getRowCount() {
        return mallas.getLength();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        try {
            MallaCurricular ml = mallas.getInfo(rowIndex);
            switch (columnIndex) {
                case 0:
                    return (ml != null) ? ml.getPensum() : " ";
                case 1:
                    return (ml != null) ? ml.getDescripcion() : "";
                case 2:
                    return (ml != null) ? ((ml.isVigente()) ? "Vigente" : "No Vigente") : "";
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
                return "PENSUM";
            case 1:
                return "DESCRIPCION";
            case 2:
                return "VIGENCIA";
            default:
                return null;
        }
    }

    public DynamicList<MallaCurricular> getMallas() {
        return mallas;
    }

    public void setMallas(DynamicList<MallaCurricular> malla) {
        this.mallas = malla;
    }

}
