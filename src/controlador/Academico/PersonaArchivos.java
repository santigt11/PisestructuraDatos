package controlador.Academico;

import controlador.DAO.Conexion;
import controlador.TDA.listas.DynamicList;
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
    
//    public static void main(String[] args) {
//        Persona p = new Persona(1, "1106072588", "Estefania", "Torres", , telefono, Rol.DOCENTE)
//    }
}
