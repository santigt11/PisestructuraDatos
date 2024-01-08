package controlador.login;

import controlador.TDA.listas.DynamicList;
import controlador.TDA.listas.Exception.EmptyException;
import controlador.dao.AdaptadorDao;
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
