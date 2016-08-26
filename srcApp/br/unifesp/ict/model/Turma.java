package br.unifesp.ict.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class Turma implements Serializable {

  private static final long serialVersionUID = 1L;
  
  @Id
  private Long id;
  private String semestre;
  private String disciplina;
  Set<Key<Aluno>> alunos = new HashSet<Key<Aluno>>();
  Set<Key<Aula>> aulas = new HashSet<Key<Aula>>();
  
  public void addAluno(Key<Aluno> a) {
    alunos.add(a);
  }
  
  public void addAula(Key<Aula> a) {
    aulas.add(a);
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getSemestre() {
    return semestre;
  }

  public void setSemestre(String semestre) {
    this.semestre = semestre;
  }

  public String getDisciplina() {
    return disciplina;
  }

  public void setDisciplina(String disciplina) {
    this.disciplina = disciplina;
  }

  public Set<Key<Aluno>> getAlunos() {
    return alunos;
  }
  
  public Set<Key<Aula>> getAulas() {
    return aulas;
  }

  public void setAlunos(Set<Key<Aluno>> alunos) {
    this.alunos = alunos;
  }
}
