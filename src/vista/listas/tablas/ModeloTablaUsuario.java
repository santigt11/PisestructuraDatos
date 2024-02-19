/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista.listas.tablas;

import controlador.TDA.listas.DynamicList;
import controlador.TDA.listas.Exception.EmptyException;
import javax.swing.table.AbstractTableModel;
import modelo.Persona;
import modelo.Usuario;

/**
 *
 * @author Usuario
 */
public class ModeloTablaUsuario extends AbstractTableModel {

    private DynamicList<Usuario> usuarios;
    private DynamicList<Persona> personas;

    public int getRowCount() {
        return usuarios.getLength();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    @SuppressWarnings("empty-statement")
    public Object getValueAt(int rowIndex, int columnIndex) {
        Usuario usuario = null;
        try {
            usuario = usuarios.getInfo(rowIndex);
            Persona p = this.personas.getInfo(usuario.getId());
            return switch (columnIndex) {
                case 0 ->
                    usuario.getCorreo();
                case 1 ->
                    p.getNombre() + "" + p.getApellido();
                case 2 ->
                    (usuario.getActivo()) ? "Activo" : "Inactivo";
                case 3 ->
                    p.getDni();
                case 4 -> {
                    yield switch (usuario.getRol()){
                        case  -> "Administrador";
                        case  -> "Docente";
                        case  -> "Estudiante";
                        default -> "";                     
                          };
            }
            default -> null;
        };
    } catch (EmptyException ex) {
        throw new RuntimeException("Error al obtener la información: " + ex.getMessage());
    }
}

    public DynamicList<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(DynamicList<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
}
