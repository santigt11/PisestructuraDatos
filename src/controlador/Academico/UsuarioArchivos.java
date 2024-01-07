package controlador.Academico;

import controlador.TDA.listas.DynamicList;
import controlador.TDA.listas.Exception.EmptyException;
import static controlador.Utiles.Utiles.validadorDeCorreo;
import controlador.dao.AdaptadorDao;
import modelo.Rol;
import modelo.Usuario;

public class UsuarioArchivos extends AdaptadorDao<Usuario> {

    private DynamicList<Usuario> usuarios;
    private Usuario usuario;

    public UsuarioArchivos(DynamicList<Usuario> usuarios, Usuario usuario) {
        super(Usuario.class);
        this.usuarios = usuarios;
        this.usuario = usuario;
    }

    public UsuarioArchivos() {
        super(Usuario.class);
    }

    public DynamicList<Usuario> getUsuarios() {
        usuarios = all();
        return usuarios;
    }

    public void setUsuarios(DynamicList<Usuario> tutorias) {
        this.usuarios = tutorias;
    }

    public Usuario getUsuario() {
        if (usuario == null) {
            usuario = new Usuario();
        }
        return usuario;
    }

    public void setTutoria(Usuario ssuario) {
        this.usuario = usuario;
    }

    @Override
    public Integer persist(Usuario obj) throws Exception {
        obj.setId(all().getLength() + 1);
        return super.persist(obj);
    }

    public Usuario autenticarse(String correo, String clave) throws EmptyException {
        if (!validadorDeCorreo(correo)) {
            return null; // Si el correo no cumple con el formato esperado
        } else {

            DynamicList<Usuario> usuarios = getUsuarios(); // Método para obtener la lista de usuarios
            for (int i = 0; i < usuarios.getLength(); i++) {
                Usuario usuario = usuarios.getInfo(i);
                if (usuario.getCorreo().equals(correo) && verificarClave(usuario.getClave(), clave)) {
                    return usuario; // Devuelve el rol del usuario autenticado
                }
            }
            return null;
        }
    }

    private boolean verificarClave(String claveAlmacenada, String claveIngresada) {
        return claveAlmacenada.equalsIgnoreCase(claveIngresada);
    }
}
