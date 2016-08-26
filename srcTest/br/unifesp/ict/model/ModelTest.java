package br.unifesp.ict.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.unifesp.ict.dao.AlunoDAO;
import br.unifesp.ict.dao.AulaDAO;
import br.unifesp.ict.dao.TurmaDAO;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;

public class ModelTest {
  
  
  private final LocalServiceTestHelper helper =
      new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());
  
  @Before
  public void setUp() {
    helper.setUp();
    ObjectifyService.register(Aula.class);
    ObjectifyService.register(Turma.class);
    ObjectifyService.register(Aluno.class);
  }
  
  @After
  public void tearDown() {
    helper.tearDown();
  }
  
  @Test 
  public void criacaoTurma() {
    Turma t = new Turma();
    t.setSemestre("2/2013");
    t.setDisciplina("Laboratório de Metodologias Ágeis");
    TurmaDAO tD = new TurmaDAO();
    assertNull(t.getId());
    t = tD.save(t);
    assertNotNull(t.getId());
  }
  
  @Test
  public void criacaoAluno() {
    Aluno a = new Aluno();
    a.setNome("Jose");
    AlunoDAO aD = new AlunoDAO();
    Aluno aR = aD.save(a);
    assertEquals(aR.getNome(), aD.findById(aR.getId()).getNome());
  }

  @Test
  public void turmaTresAlunos() {
    Turma t = new Turma();
    t.setSemestre("2/2013");
    t.setDisciplina("Laboratório de Metodologias Ágeis");
    Aluno a = new Aluno();
    a.setNome("Rafael");
    Aluno b = new Aluno();
    b.setNome("Luis");
    Aluno c = new Aluno();
    c.setNome("Patricia"); 
    AlunoDAO aD = new AlunoDAO();
    aD.save(a);    
    aD.save(b);    
    aD.save(c);
    t.addAluno(Key.create(a));
    t.addAluno(Key.create(b));
    t.addAluno(Key.create(c));
    TurmaDAO tD = new TurmaDAO();
    tD.save(t);
    for(Key<Aluno> ak : t.alunos) {
      Aluno a1 = aD.findById(ak.getId());
      assertEquals(a1.getId(), ak.getId(), .0);
    }
  }
  
  @Test
  public void criacaoAula() throws ParseException {
    Aula a = new Aula();
    SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
    a.setData(sdf.parse("23/10/2013"));
    AulaDAO aD = new AulaDAO();
    Aula a1 = aD.save(a);
    assertEquals(a1.getData(), aD.findById(a.getId()).getData());
  }
  
  @Test
  public void turmaComAulas() throws ParseException {
    Turma t = new Turma();
    t.setSemestre("2/2013");
    t.setDisciplina("Laboratório de Metodologias Ágeis");
    Aula a = new Aula();
    SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
    a.setData(sdf.parse("23/10/2013"));
    Aula b = new Aula();
    b.setData(sdf.parse("04/11/2013"));
    Aula c = new Aula();
    c.setData(sdf.parse("06/11/2013"));
    AulaDAO aD = new AulaDAO();
    aD.save(a);
    aD.save(b);
    aD.save(c);
    t.addAula(Key.create(a));
    t.addAula(Key.create(b));
    t.addAula(Key.create(c));
    TurmaDAO tD = new TurmaDAO();
    tD.save(t);
    
    for(Key<Aula> ak : t.aulas) {
      Aula a1 = aD.findById(ak.getId());
      assertEquals(a1.getId(), ak.getId(), .0);
    }
  }
  
  @Test
  public void aulaComPresencas() throws ParseException {
    Turma t = new Turma();
    t.setSemestre("2/2013");
    t.setDisciplina("Laboratório de Metodologias Ágeis");
    Aula au = new Aula();
    SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
    au.setData(sdf.parse("23/10/2013"));
    AulaDAO aD = new AulaDAO();
    aD.save(au);
    t.addAula(Key.create(au));
    Aluno a = new Aluno();
    a.setNome("Rafael");
    Aluno b = new Aluno();
    b.setNome("Luis"); 
    AlunoDAO alD = new AlunoDAO();
    alD.save(a);    
    alD.save(b);    
    t.addAluno(Key.create(a));
    t.addAluno(Key.create(b));
    TurmaDAO tD = new TurmaDAO();
    tD.save(t);
    au.setTurma(Key.create(t));
    aD.save(au);
    
    au.addPresenca(Key.create(a));
    
    Aula aR = aD.save(au);
    assertEquals(1, aR.getPresencas().size());
    
    for(Key<Aluno> ak : aR.getPresencas()) {
      Aluno aRec = alD.findById(ak.getId());
      assertEquals("Rafael", aRec.getNome());
    }
    
  }

}
