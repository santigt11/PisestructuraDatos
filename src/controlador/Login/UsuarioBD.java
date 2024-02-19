package controlador.Login;

import controlador.TDA.listas.DynamicList;
import controlador.TDA.listas.Exception.EmptyException;
import controlador.Utiles.Utiles;
import controlador.dao.AdaptadorDao;
import modelo.Usuario;

public class UsuarioBD extends AdaptadorDao<Usuario> {

    private DynamicList<Usuario> usuarios;
    private Usuario usuario;

    public UsuarioBD(DynamicList<Usuario> usuarios, Usuario usuario) {
        super(Usuario.class);
        this.usuarios = usuarios;
        this.usuario = usuario;
    }

    public UsuarioBD() {
        super(Usuario.class);
    }

    public DynamicList<Usuario> getUsuarios() {
        usuarios = all();
        return usuarios;
    }

    public void setUsuarios(DynamicList<Usuario> tutorias) {
        this.usuarios = tutorias;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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
    public Boolean persist(Usuario obj) {
        obj.setId(all().getLength() + 1);
        return super.persist(obj);
    }

    public Usuario autenticarse(String correo, String clave) throws EmptyException {
        if (!Utiles.validadorDeCorreo(correo)) {
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

    public DynamicList<Usuario> buscarLineal(DynamicList<Usuario> lista, String campo, String valorBuscado) throws EmptyException {
        Usuario usuarios[] = lista.toArray();
        DynamicList<Usuario> listaBusqueda = new DynamicList<>();
        for (int i = 0; i < lista.getLength(); i++) {
            Usuario usuario = usuarios[i];
            if (usuario.compareCampo(campo, valorBuscado) == 0) {
                listaBusqueda.add(usuario);
            }
        }
        return listaBusqueda;
    }

    public Usuario buscarBinaria(String campo, String valorBuscado) throws EmptyException {
        int inicio = 0;
        DynamicList<Usuario> lista = all();
        int fin = lista.getLength() - 1;
        Usuario usuarios[] = lista.toArray();
        while (inicio <= fin) {
            int medio = (inicio + fin) / 2;
            Usuario usuario = usuarios[medio];
            int comparacion = usuario.compareCampo(campo, valorBuscado);
            if (comparacion == 0) {
                return usuario;
            } else if (comparacion < 0) {
                inicio = medio + 1;
            } else {
                fin = medio - 1;
            }
        }
        return null;
    }

}
