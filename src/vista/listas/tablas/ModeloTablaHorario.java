/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista.listas.tablas;

import controlador.TDA.listas.DynamicList;
import controlador.TDA.listas.Exception.EmptyException;
import javax.swing.table.AbstractTableModel;
import modelo.Horario;

/**
 *
 * @author marian
 */
public class ModeloTablaHorario extends AbstractTableModel{
    private DynamicList<Horario> horarios;

    @Override
    public int getRowCount() {
        return horarios.getLength();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        try {
            Horario h = horarios.getInfo(rowIndex);
            switch (columnIndex) {
                case 0:
                    return (h != null) ? h.getHoraInicio(): " ";
                case 1:
                    return (h != null) ? h.getHoraFin(): " ";
                case 2:
                    return (h != null) ? h.getDisponible(): " ";
                
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
                return "HORA INICIO";
            case 1:
                return "HORA FIN";
            case 2:
                return "DISPONIBLE";
            
            default:
                return null;
        }
    }

    public DynamicList<Horario> getHorarios() {
        return horarios;
    }

    public void setHorarios(DynamicList<Horario> horarios) {
        this.horarios = horarios;
    }

}
