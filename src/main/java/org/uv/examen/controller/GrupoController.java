/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.uv.examen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.uv.examen.entity.Alumno;
import org.uv.examen.entity.Grupo;
import org.uv.examen.entity.Materia;
import org.uv.examen.repository.AlumnoRepository;
import org.uv.examen.repository.GrupoRepository;
import org.uv.examen.repository.MateriaRepository;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/grupos")
public class GrupoController {

    private final GrupoRepository grupoRepository;
    private final AlumnoRepository alumnoRepository;
    private final MateriaRepository materiaRepository;

    @Autowired
    public GrupoController(GrupoRepository grupoRepository, 
                           AlumnoRepository alumnoRepository, 
                           MateriaRepository materiaRepository) {
        this.grupoRepository = grupoRepository;
        this.alumnoRepository = alumnoRepository;
        this.materiaRepository = materiaRepository;
    }

    @GetMapping
    public ResponseEntity<List<Grupo>> obtenerGrupos() {
        List<Grupo> grupos = grupoRepository.findAll();
        return new ResponseEntity<>(grupos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Grupo> obtenerGrupo(@PathVariable Long id) {
        Optional<Grupo> grupo = grupoRepository.findById(id);
        return new ResponseEntity(grupo, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Grupo> crearGrupo(@RequestBody Grupo grupo) {
        Grupo createdGrupo = grupoRepository.save(grupo);
        return new ResponseEntity<>(createdGrupo, HttpStatus.CREATED);
    }
    
    @PostMapping("/{grupoId}/alumnos/{alumnoId}")
    public ResponseEntity<Grupo> agregarAlumnoAGrupo(@PathVariable Long grupoId, @PathVariable String alumnoId) {
        Optional<Grupo> grupoOp = grupoRepository.findById(grupoId);
        Optional<Alumno> alumnoOp = alumnoRepository.findById(alumnoId);

        if (grupoOp.isPresent() && alumnoOp.isPresent()) {
            Grupo grupo = grupoOp.get();
            Alumno alumno = alumnoOp.get();
            grupo.getAlumnos().add(alumno);
            Grupo groupUpdated = grupoRepository.save(grupo);
            return new ResponseEntity<>(groupUpdated, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @PostMapping("/{grupoId}/materias/{materiaId}")
    public ResponseEntity<Grupo> addMateriaToGrupo(@PathVariable Long grupoId, @PathVariable String materiaId) {
        Optional<Grupo> grupoOp = grupoRepository.findById(grupoId);
        Optional<Materia> materiaOp = materiaRepository.findById(materiaId);

        if (grupoOp.isPresent() && materiaOp.isPresent()) {
            Grupo grupo = grupoOp.get();
            Materia materia = materiaOp.get();
            grupo.getMaterias().add(materia);
            Grupo groupUpdated = grupoRepository.save(grupo);
            return new ResponseEntity<>(groupUpdated, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Grupo> updateGrupo(@PathVariable Long id, @RequestBody Grupo grupo) {
        if (!grupoRepository.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        grupo.setId(id);
        Grupo updatedGrupo = grupoRepository.save(grupo);
        return new ResponseEntity<>(updatedGrupo, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGrupo(@PathVariable Long id) {
        if (!grupoRepository.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        grupoRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}