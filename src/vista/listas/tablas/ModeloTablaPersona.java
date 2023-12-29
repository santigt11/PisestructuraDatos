package vista.listas.tablas;

import controlador.TDA.listas.DynamicList;
import controlador.TDA.listas.Exception.EmptyException;
import javax.swing.table.AbstractTableModel;
import modelo.Persona;

public class ModeloTablaPersona extends AbstractTableModel {

    private DynamicList<Persona> personas;

    @Override
    public int getRowCount() {
        return personas.getLength();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        try {
            Persona ps = personas.getInfo(rowIndex);
            switch (columnIndex) {
                case 0:
                    return (ps != null) ? ps.getDni() : " ";
                case 1:
                    return (ps != null) ? ps.getApellido() : " ";
                case 2:
                    return (ps != null) ? ps.getNombre() : "";
                case 3:
                    return (ps != null) ? ps.getTelefono() : "";
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
                return "DNI";
            case 1:
                return "APELLIDO";
            case 2:
                return "NOMBRE";
            case 3:
                return "TELEFONO";
            default:
                return null;
        }
    }

    public DynamicList<Persona> getPersonas() {
        return personas;
    }

    public void setPersonas(DynamicList<Persona> persona) {
        this.personas = persona;
    }

}
