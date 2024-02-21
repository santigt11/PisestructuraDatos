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

    public DynamicList<Usuario> getUsuariosTodos() {
        usuarios = all();
        return usuarios;
    }
    
    public DynamicList<Usuario> getUsuarios() {
        if (usuarios == null) {
            usuarios = new DynamicList<>();
        }
        return usuarios;
    }

    public void setUsuarios(DynamicList<Usuario> usuarios) {
        this.usuarios = usuarios;
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

    public void setUsuarios(Usuario usuario) {
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

            DynamicList<Usuario> usuarios = getUsuariosTodos(); // Método para obtener la lista de usuarios
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
        DynamicList<Usuario> listaOrdenada = ordenarMerge(lista, campo, 0);
        Usuario usuarios[] = listaOrdenada.toArray();
        DynamicList<Usuario> listaBusqueda = new DynamicList<>();
        for (int i = 0; i < listaOrdenada.getLength(); i++) {
            Usuario usuario = usuarios[i];
            if (usuario.compareCampo(campo, valorBuscado) == 0) {
                listaBusqueda.add(usuario);
            }
        }
        return listaBusqueda;
    }

    public Usuario buscarBinaria(DynamicList<Usuario> lista, String campo, String valorBuscado) throws EmptyException {
        DynamicList<Usuario> listaOrdenada = ordenarMerge(lista, campo, 0);
        int inicio = 0;
        int fin = listaOrdenada.getLength() - 1;
        Usuario usuarios[] = listaOrdenada.toArray();

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
    
    //MergeSort
    public DynamicList<Usuario> ordenarMerge(DynamicList<Usuario> lista, String field, Integer tipo) throws EmptyException {
        if (lista.getLength() > 1) {
            DynamicList<Usuario> izquierda = new DynamicList<>();
            DynamicList<Usuario> derecha = new DynamicList<>();
            int mitad = lista.getLength() / 2;
            for (int i = 0; i < mitad; i++) {
                izquierda.add(lista.getInfo(i));
            }
            for (int i = mitad; i < lista.getLength(); i++) {
                derecha.add(lista.getInfo(i));
            }
            izquierda = ordenarMerge(izquierda, field, tipo);
            derecha = ordenarMerge(derecha, field, tipo);
            mezclar(lista, izquierda, derecha, field, tipo);
        }
        return lista;
    }

    private void mezclar(DynamicList<Usuario> lista, DynamicList<Usuario> list1, DynamicList<Usuario> list2, String field, Integer tipo) throws EmptyException {
        int indiceIzq = 0, indiceDer = 0, indiceLista = 0;
        Usuario[] izquierda = list1.toArray();
        Usuario[] derecha = list2.toArray();
        while (indiceIzq < izquierda.length && indiceDer < derecha.length) {
            if (izquierda[indiceIzq].compare(derecha[indiceDer], field, tipo)) {
                lista.merge(izquierda[indiceIzq], indiceLista);
                indiceIzq += 1;
            } else {
                lista.merge(derecha[indiceDer], indiceLista);
                indiceDer += 1;
            }
            indiceLista += 1;
        }

        while (indiceIzq < izquierda.length) {
            lista.merge(izquierda[indiceIzq], indiceLista);
            indiceIzq += 1;
            indiceLista += 1;
        }

        while (indiceDer < derecha.length) {
            lista.merge(derecha[indiceDer], indiceLista);
            indiceDer += 1;
            indiceLista += 1;
        }
    }
    
   public boolean update(Usuario usuario) {
    try {
        // Actualiza el usuario proporcionado como argumento
        // Utiliza this.usuario para referirte al atributo de la clase en lugar del parámetro
        update(usuario);
        return true;
    } catch (Exception e) {
        // Asegúrate de manejar cualquier excepción correctamente
        e.printStackTrace(); // Esto imprimirá la traza de la excepción en la consola para ayudarte a depurar errores
        return false; // Debes tener un retorno en el caso de que la actualización falle
    }

}

}
