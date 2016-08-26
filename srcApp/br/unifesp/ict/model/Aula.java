package br.unifesp.ict.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class Aula implements Serializable {
  
  @Id
  private Long id;
  
  @DateTimeFormat(pattern = "dd/MM/yyyy")
  private Date data;
  private Key<Turma> turma;
  private Set<Key<Aluno>> presencas = new HashSet<Key<Aluno>>();
  

  public Date getData() {
    return data;
  }
  public void setData(Date data) {
    this.data = data;
  }
  
  public String getDataFormatada() {
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM");
    return sdf.format(data);
  }
  
  public Key<Turma> getTurma() {
    return turma;
  }
  
  public void setTurma(Key<Turma> turma) {
    this.turma = turma;
  }
  
  public Long getId() {
    return id;
  }
  
  public void setId(Long id) {
    this.id = id;
  }
  
  public void addPresenca(Key<Aluno> presenca) {
    presencas.add(presenca);
  }
  
  public Set<Key<Aluno>> getPresencas() {
    return presencas;
  }
  
  public void setPresencas(Set<Key<Aluno>> presencas) {
    this.presencas = presencas;
  }
  
  public boolean presente(Aluno a) {
    for(Key<Aluno> ka : presencas) {
      if(a.getId() == ka.getId())
        return true;
    }
    return false;
  }
  
}
