/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import controlador.TDA.listas.DynamicList;
import java.util.Date;

/**
 *
 * @author Santiago
 */
public class Matricula {
    private Integer id;
    private Date fecha;
    private Boolean expActivo;
    private DynamicList<MatriculaAsignatura> matriculasAs;
    private PeriodoAcademico periodoA;
    private DynamicList<Tutoria> tutorias;

    public Matricula(Integer id, Date fecha, Boolean expActivo, DynamicList<MatriculaAsignatura> matriculasAs, PeriodoAcademico periodoA, DynamicList<Tutoria> tutorias) {
        this.id = id;
        this.fecha = fecha;
        this.expActivo = expActivo;
        this.matriculasAs = matriculasAs;
        this.periodoA = periodoA;
        this.tutorias = tutorias;
    }

    public Matricula() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Boolean getExpActivo() {
        return expActivo;
    }

    public void setExpActivo(Boolean expActivo) {
        this.expActivo = expActivo;
    }

    public DynamicList<MatriculaAsignatura> getMatriculasAs() {
        return matriculasAs;
    }

    public void setMatriculasAs(DynamicList<MatriculaAsignatura> matriculasAs) {
        this.matriculasAs = matriculasAs;
    }

    public PeriodoAcademico getPeriodoA() {
        return periodoA;
    }

    public void setPeriodoA(PeriodoAcademico periodoA) {
        this.periodoA = periodoA;
    }

    public DynamicList<Tutoria> getTutorias() {
        return tutorias;
    }

    public void setTutorias(DynamicList<Tutoria> tutorias) {
        this.tutorias = tutorias;
    }
}