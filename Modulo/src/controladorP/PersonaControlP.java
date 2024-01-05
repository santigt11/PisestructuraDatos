/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladorP;

import controlador.DAO.DaoImplements;
import controlador.TDA.listas.DynamicList;
import modelo.Persona;


/**
 *
 * @author santi
 */
public class PersonaControlP extends DaoImplements<Persona>{
    private DynamicList<Persona> personas;
    private Persona persona;

    public PersonaControlP() {
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
    
    public Boolean persist(){
        persona.setId(all().getLength() + 1);
        return persist(persona);
    }
    
}
