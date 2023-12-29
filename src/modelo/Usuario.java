/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Santiago
 */
public class Usuario {
    private Integer id;
    private String cooreo;
    private String clave;

    public Usuario() {
    }

    public Usuario(Integer id, String cooreo, String clave) {
        this.id = id;
        this.cooreo = cooreo;
        this.clave = clave;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCooreo() {
        return cooreo;
    }

    public void setCooreo(String cooreo) {
        this.cooreo = cooreo;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
}
