package com.udea.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * A Materia.
 */
@Entity
@Table(name = "materia")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Materia implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre")
    private String nombre;
    
    @Column(name = "hora")
    private Date hora;
    
    @Column(name = "nota")
    private Double nota;

    @ManyToMany(mappedBy = "materias")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Estudiante> estudiantes = new HashSet<>();

    @ManyToMany(mappedBy = "materias")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Profesor> profesors = new HashSet<>();

    @OneToOne(mappedBy = "materia")
    @JsonIgnore
    private Aula aula;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public Materia nombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getHora() {
		return hora;
	}

	public void setHora(Date hora) {
		this.hora = hora;
	}

	public Double getNota() {
		return nota;
	}

	public void setNota(Double nota) {
		this.nota = nota;
	}

	public Set<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    public Materia estudiantes(Set<Estudiante> estudiantes) {
        this.estudiantes = estudiantes;
        return this;
    }

    public Materia addEstudiante(Estudiante estudiante) {
        this.estudiantes.add(estudiante);
        estudiante.getMaterias().add(this);
        return this;
    }

    public Materia removeEstudiante(Estudiante estudiante) {
        this.estudiantes.remove(estudiante);
        estudiante.getMaterias().remove(this);
        return this;
    }

    public void setEstudiantes(Set<Estudiante> estudiantes) {
        this.estudiantes = estudiantes;
    }

    public Set<Profesor> getProfesors() {
        return profesors;
    }

    public Materia profesors(Set<Profesor> profesors) {
        this.profesors = profesors;
        return this;
    }

    public Materia addProfesor(Profesor profesor) {
        this.profesors.add(profesor);
        profesor.getMaterias().add(this);
        return this;
    }

    public Materia removeProfesor(Profesor profesor) {
        this.profesors.remove(profesor);
        profesor.getMaterias().remove(this);
        return this;
    }

    public void setProfesors(Set<Profesor> profesors) {
        this.profesors = profesors;
    }

    public Aula getAula() {
        return aula;
    }

    public Materia aula(Aula aula) {
        this.aula = aula;
        return this;
    }

    public void setAula(Aula aula) {
        this.aula = aula;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Materia materia = (Materia) o;
        if (materia.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, materia.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
	public String toString() {
		return "Materia [id=" + id + ", nombre=" + nombre + ", hora=" + hora + ", nota=" + nota + ", estudiantes="
				+ estudiantes + ", profesors=" + profesors + ", aula=" + aula + "]";
	}
}
