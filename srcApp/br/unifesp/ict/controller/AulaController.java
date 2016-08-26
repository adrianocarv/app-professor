package br.unifesp.ict.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import br.com.yaw.spgae.model.Mercadoria;
import br.unifesp.ict.dao.AlunoDAO;
import br.unifesp.ict.dao.AulaDAO;
import br.unifesp.ict.dao.TurmaDAO;
import br.unifesp.ict.model.Aluno;
import br.unifesp.ict.model.Aula;
import br.unifesp.ict.model.Turma;

import com.googlecode.objectify.Key;


@RequestMapping(value = "/aula/")
@Controller
public class AulaController {
  
  @Autowired
  private TurmaDAO turmaDao;
  
  @Autowired
  private AulaDAO aulaDao;
  
  @Autowired
  private AlunoDAO alunoDao;

  @RequestMapping(value = "/{idTurma}", params = "form", method = RequestMethod.GET)
  public String criarForm(@PathVariable("idTurma") Long idTurma, Model uiModel) {
    Turma t = turmaDao.findById(idTurma);
    Aula a = new Aula();
    a.setTurma(Key.create(t));
    Map<Aluno, Boolean> alunos = new HashMap<Aluno,Boolean>();
    for(Key<Aluno> ka : t.getAlunos()) {
      Aluno al = alunoDao.findById(ka.getId());
      alunos.put(al, false);
    }
    a.setData(new Date(System.currentTimeMillis()));
    aulaDao.save(a);
    uiModel.addAttribute("aula", a);
    uiModel.addAttribute("alunos", alunos);
    uiModel.addAttribute("active", "incluir");
    t.addAula(Key.create(a));
    turmaDao.save(t);
    return "incluirAula";
  }

  @RequestMapping(method = RequestMethod.POST)
  public String criar(@Valid Aula aula, BindingResult bindingResult,
      Model uiModel, HttpServletRequest request) {
    if (bindingResult.hasErrors()) {
      uiModel.addAttribute("aula", aula);
      uiModel.addAttribute("active", "incluir");
      return "incluirAula";
    }
    Aula aBanco = aulaDao.findById(aula.getId());
    aBanco.setData(aula.getData());
    String[] presencas = request.getParameterValues("presChecks");
    Set<Key<Aluno>> presentes = new HashSet<Key<Aluno>>();
    for(String s : presencas) {
      Aluno a = alunoDao.findById(new Long(s));
      presentes.add(Key.create(a));
    }
    aBanco.setPresencas(presentes);
    aulaDao.save(aBanco);
    return listar(aBanco.getTurma().getId(), uiModel);
  }
  
  @RequestMapping(value = "/edita/{id}", params = "form", method = RequestMethod.GET)
  public String editarForm(@PathVariable("id") Long id, Model uiModel) {
    Aula a = aulaDao.findById(id);
    Turma t = turmaDao.findById(a.getTurma().getId());
    Map<Aluno, Boolean> alunos = new HashMap<Aluno, Boolean>(); 
    for(Key<Aluno> ka : t.getAlunos()) {
      Aluno al = alunoDao.findById(ka.getId());
      alunos.put(al, a.presente(al));
    }
    
    if (a != null) {
      uiModel.addAttribute("aula", a);
      uiModel.addAttribute("alunos", alunos);
    }
    return "editarAula";
  }
  
  @RequestMapping(value = "/lista/{idTurma}", params = "form", method = RequestMethod.GET)
  public String listar(@PathVariable("idTurma") Long idTurma, Model uiModel) {
    Turma t = turmaDao.findById(idTurma);
    ArrayList<Aluno> listaAlunos = new ArrayList<Aluno>();
    for(Key<Aluno> ka : t.getAlunos()) {
      Aluno a = alunoDao.findById(ka.getId());
      listaAlunos.add(a);
    }
    ArrayList<Aula> listaAulas = new ArrayList<Aula>();
    for(Key<Aula> ka : t.getAulas()) {
      Aula a = aulaDao.findById(ka.getId());
      listaAulas.add(a);
    }
    
    Map<Aluno,List<String>> tabelaPresenca = new HashMap<Aluno, List<String>>();
    
    for(Aluno a : listaAlunos) {
      List<String> presencas = new ArrayList<String>();
      for(Aula au : listaAulas) {
        boolean presente = false;
        for(Key<Aluno> ka : au.getPresencas()) {
          if(ka.getId() == a.getId()) {
            presente = true;
            break;
          }
        }
        presencas.add(presente ? "P" : "F");
      }
      tabelaPresenca.put(a, presencas);
    }
    
    uiModel.addAttribute("turma", t);
    uiModel.addAttribute("aulas", listaAulas);
    uiModel.addAttribute("tabelaPresenca", tabelaPresenca);
    
    return "listaAulas";
  }
  
  public AulaDataSource getDataSource() {
    ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
    HttpSession session = attr.getRequest().getSession();
    AulaDataSource ds = (AulaDataSource) session.getAttribute("ds");
    if (ds == null) {
      ds = new AulaDataSource();
      ds.synch(aulaDao.getAll());
      session.setAttribute("ds", ds);
    }
    return ds;
  }

}
