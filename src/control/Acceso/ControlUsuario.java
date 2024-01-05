/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control.Acceso;

import controlador.DAO.DaoImplement;
import controlador.TDA.listas.DynamicList;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import modelo.Rol;
import modelo.Usuario;
import static vista.listas.util.Utilvista.validadorDeCorreo;

/**
 *
 * @author Usuario
 */
public class ControlUsuario extends  DaoImplement<Usuario>{
    private DynamicList<Usuario> usuarios;
    private Usuario usuario;

    public ControlUsuario() {
        super(Usuario.class);
    }

    public DynamicList<Usuario> getUsuarios() {
        usuarios = all();
        return usuarios;
    }

    public void setUsuarios(DynamicList<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public Usuario getUsuario() {
         if (usuario== null) {
            usuario = new Usuario();
        }
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Boolean persist() {
        usuario.setId(all().getLength()+1);
        return persist(usuario);
    }
    
public Rol autenticarse(String correo, String clave) {
    if (!validadorDeCorreo(correo)) {
        return null; // Si el correo no cumple con el formato esperado
    }

    DynamicList<Usuario> usuarios = getUsuarios(); // Método para obtener la lista de usuarios
    
    for (int i = 0; i < usuarios.getLength(); i++) {
        //Usuario usuario = usuarios.getClass(i);
        if (usuario.getCorreo().equals(correo) && verificarClave(usuario.getClave(), clave)) {
            return usuario.getRol(); // Devuelve el rol del usuario autenticado
        }
    }

    return null; // Si no se encuentra el usuario o las credenciales son incorrectas
}

private boolean verificarClave(String claveAlmacenada, String claveIngresada) {
    // Implementa la lógica de verificación de contraseña aquí
    return claveAlmacenada.equals(claveIngresada);
}
    public void guardarUsuarios() {
        try {
            FileOutputStream fileOut = new FileOutputStream("usuarios.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(usuarios); // usuarios es tu lista de usuarios
            out.close();
            fileOut.close();
            System.out.println("Se guardaron los usuarios en usuarios.ser");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public void cargarUsuarios() {
        try {
            FileInputStream fileIn = new FileInputStream("usuarios.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            usuarios = (DynamicList<Usuario>) in.readObject();
            in.close();
            fileIn.close();
            System.out.println("Se cargaron los usuarios desde usuarios.ser");
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("Clase Usuario no encontrada");
            c.printStackTrace();
        }
    }
    public static void main(String[] args) {
    ControlUsuario controlUsuario = new ControlUsuario();
    
    // Cargar usuarios desde el archivo (si existen)
    controlUsuario.cargarUsuarios();
    
    // ... realizar operaciones con los usuarios
    
    // Guardar los usuarios de vuelta en el archivo
    controlUsuario.guardarUsuarios();
}

}







    
    

