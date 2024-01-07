package controlador.Academico;

import controlador.DAO.Conexion;
import controlador.TDA.listas.DynamicList;
import controlador.TDA.listas.Exception.EmptyException;
import controlador.dao.AdaptadorDao;
import java.time.LocalDate;
import modelo.Persona;
import modelo.Rol;


public class PersonaArchivos extends AdaptadorDao<Persona> {
    
    private DynamicList<Persona> personas;
    private Persona persona;

    public PersonaArchivos() {
        super(Persona.class);
    }

    public PersonaArchivos(DynamicList<Persona> personas, Persona persona, Class clazz) {
        super(clazz);
        this.personas = personas;
        this.persona = persona;
    }

    public DynamicList<Persona> getPersonas() {
        personas = all();
        return personas;
    }

    public void setPersonas(DynamicList<Persona> personas) {
        this.personas = personas;
    }

    public Persona getPersona() {
        if (persona == null) {
            persona = new Persona();
        }
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    @Override
    public Integer persist(Persona obj) throws Exception {
        obj.setId(all().getLength()+1);
        return super.persist(obj);
    }
    
    
    public DynamicList<Persona> buscarLineal(String campo, String valorBuscado) throws EmptyException {
        DynamicList<Persona> lista = all();
        Persona personas[] = lista.toArray();
        DynamicList<Persona> listaBusqueda = new DynamicList<>();
        for (int i = 0; i < lista.getLength(); i++) {
            Persona persona = personas[i];
            if (persona.compareCampo(campo, valorBuscado) == 0) {
                listaBusqueda.add(persona);
            }
        }
        return listaBusqueda;
    }
    
    public Persona buscarBinaria(String campo, String valorBuscado) throws EmptyException {
        int inicio = 0;
        DynamicList<Persona> lista = all();
        int fin = lista.getLength() - 1;
        Persona personas[] = lista.toArray();
        while (inicio <= fin) {
            int medio = (inicio + fin) / 2;
            Persona persona = personas[medio];
            int comparacion = persona.compareCampo(campo, valorBuscado);
            if (comparacion == 0) {
                return persona;
            } else if (comparacion < 0) {
                inicio = medio + 1;
            } else {
                fin = medio - 1;
            }
        }
        return null;
    }
//    public static void main(String[] args) {
//        Persona p = new Persona(1, "1106072588", "Estefania", "Torres", , telefono, Rol.DOCENTE)
//    }
}
