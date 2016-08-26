package br.unifesp.ict.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.unifesp.ict.dao.TurmaDAO;
import br.unifesp.ict.model.Turma;


@RequestMapping(value = "/turma/")
@Controller
public class TurmaController {
  
  @Autowired
  private TurmaDAO turmaDao;
  
  @RequestMapping(value = "/lista", method = RequestMethod.GET)
  public String listar(Model uiModel) {
    List<Turma> listaTurmas = turmaDao.getAll();
    
    uiModel.addAttribute("turmas", listaTurmas);
    
    return "listaTurmas";
  }

}
