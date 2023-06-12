/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.uv.examen.entity;

import java.util.ArrayList;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import java.util.List;

@Entity
@Table(name = "grupos")
public class Grupo {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nombreGrupo")
    private String nombreGrupo;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "alumnos", referencedColumnName = "id")
    List <Alumno> alumnos = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "alumnos", referencedColumnName = "id")
    List <Materia> materias = new ArrayList<>();

    public Grupo() {}

    public Grupo(Long id, String nombreGrupo) {
        this.id = id;
        this.nombreGrupo = nombreGrupo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreGrupo() {
        return nombreGrupo;
    }

    public void setNombreGrupo(String nombreGrupo) {
        this.nombreGrupo = nombreGrupo;
    }

    public List <Alumno> getAlumnos() {
        return alumnos;
    }

    public void setAlumnos(List <Alumno> alumnos) {
        this.alumnos = alumnos;
    }

    public List<Materia> getMaterias() {
        return materias;
    }

    public void setMaterias(List<Materia> materias) {
        this.materias = materias;
    }
    
}
