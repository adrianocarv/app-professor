package br.com.yaw.spgae.config;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import br.com.yaw.spgae.model.Mercadoria;
import br.unifesp.ict.dao.AlunoDAO;
import br.unifesp.ict.dao.TurmaDAO;
import br.unifesp.ict.model.Aluno;
import br.unifesp.ict.model.Aula;
import br.unifesp.ict.model.Turma;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;

/**
 * Componente necess��rio para registrar no Objectify quais s��o as entidades que ele deve gerenciar.
 * 
 * <p>C��digo executado durante a inicializa����o do aplicativo web.</p> 
 * 
 * @author YaW Tecnologia
 */
public class ConfigStartup implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		ObjectifyService.register(Mercadoria.class);
		ObjectifyService.register(Aula.class);
		ObjectifyService.register(Turma.class);
		ObjectifyService.register(Aluno.class);
		populateDataBase();
	}
	
	public static void populateDataBase() {
//	  Turma t = new Turma();
//    t.setSemestre("2/2013");
//    t.setDisciplina("Laboratório de Metodologias Ágeis");
//    Aluno a = new Aluno();
//    a.setNome("Rafael");
//    Aluno b = new Aluno();
//    b.setNome("Luis");
//    Aluno c = new Aluno();
//    c.setNome("Patricia"); 
//    AlunoDAO aD = new AlunoDAO();
//    aD.save(a);    
//    aD.save(b);    
//    aD.save(c);
//    t.addAluno(Key.create(a));
//    t.addAluno(Key.create(b));
//    t.addAluno(Key.create(c));
//    TurmaDAO tD = new TurmaDAO();
//    tD.save(t);
//    System.out.println(t.getId());
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {}
	
}
