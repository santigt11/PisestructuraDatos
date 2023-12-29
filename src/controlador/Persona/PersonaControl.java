package controlador.Persona;

import controlador.DAO.DaoImplement;
import controlador.TDA.listas.DynamicList;
import controlador.TDA.listas.Exception.EmptyException;
import controlador.Utiles.Utiles;
import java.lang.reflect.Field;
import modelo.Persona;

public class PersonaControl extends DaoImplement<Persona> {

    private DynamicList<Persona> personas;
    private Persona persona;

    public PersonaControl() {
        super(Persona.class);
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

    public Boolean persist() {
        persona.setId(all().getLength() + 1);
        return persist(persona);
    }

    public DynamicList<Persona> ordenar(DynamicList<Persona> lista, Integer tipo, String field) throws EmptyException, Exception {
        Field attribute = Utiles.getField(Persona.class, field);
        Integer n = lista.getLength();
        Persona[] personas = lista.toArray();
        if (attribute != null) {
            for (int i = 0; i < n; i++) {
                int k = i;
                Persona t = personas[i];
                for (int j = i + 1; j < n; j++) {
//                    if (personas[j].getApellidos().compareTo(t.getApellidos()) < 0) {
                    if (personas[j].compare(t, field, tipo)) {
                        t = personas[j];
                        k = j;
                    }
                }
                personas[k] = personas[i];
                personas[i] = t;
            }
        } else {
            throw new Exception("No existe el criterio de busqueda");
        }
        return lista.toList(personas);
    }
    
    public DynamicList<Persona> buscarApellidos(String texto, DynamicList<Persona> personas, String criterio){
        DynamicList<Persona> lista = new DynamicList<>();
        try {
            Persona[] aux = ordenar(personas, 0, criterio).toArray();
            for (Persona p : aux) {
                if (p.getApellido().toLowerCase().contains(texto.toLowerCase())){
                    lista.add(p);
                }
            }
        } catch (Exception e) {
        }
        return lista;
    }

//    public static void main(String[] args) {
//        try {
//            PersonaControl pc = new PersonaControl();
//            System.out.println(pc.all().toString());
//            System.out.println("------aa-----");
//            System.out.println(pc.ordenar(pc.all(), 0, "nombres").toString());
//        } catch (Exception e) {
//        }
//    }
}
