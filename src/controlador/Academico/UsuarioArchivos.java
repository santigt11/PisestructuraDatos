package controlador.Academico;

import controlador.TDA.listas.DynamicList;
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
        obj.setId(all().getLength()+1);
        return super.persist(obj);
    }
}
