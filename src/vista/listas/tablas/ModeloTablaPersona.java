/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista.listas.tablas;

import controlador.TDA.listas.DynamicList;
import controlador.TDA.listas.Exception.EmptyException;
import javax.swing.table.AbstractTableModel;
import modelo.Persona;

/**
 *
 * @author santi
 */
public class ModeloTablaPersona extends AbstractTableModel {

    private DynamicList<Persona> personas;

    @Override
    public int getRowCount() {
        return personas.getLength();
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        try {
            Persona p = personas.getInfo(rowIndex);
            switch (columnIndex) {
                case 0:
                    return (p != null) ? p.getDni() : " ";
                case 1:
                    return (p != null) ? p.getApellido() : " ";
                case 2:
                    return (p != null) ? p.getNombre() : " ";
                case 3:
                    return (p != null) ? p.getFechaNacimientoi() : "";
                case 4:
                    return (p != null) ? p.getTelefono() : "";
                case 5:
                    return (p != null) ? p.getRol() : "";
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
                return "NOMBRE";
            case 2:
                return "APELLIDO";
            case 3:
                return "FECHA NACIMIENTO";
            case 4:
                return "TELEFONO";
            case 5:
                return "ROL";
            default:
                return null;
        }
    }

    public DynamicList<Persona> getPersonas() {
        return personas;
    }

    public void setPersonas(DynamicList<Persona> personas) {
        this.personas = personas;
    }

}
