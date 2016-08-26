package br.unifesp.ict.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import br.unifesp.ict.model.Aula;

/**
 * Define um <code>DataSource</code> para entidade (<code>Model</code>) Aula.
 * 
 * <p>As opera????es de manipula????o de dados no App Engine ocorrem de forma ass??ncrona.
 *    Antes de efetivar a mudan??a em todas as inst??ncias uma consulta pode trazer dados "sujos" (eventually consistent),
 *    a proposta do datasource ?? manter uma replica dos dados na sess??o web, para refletir as mudan??as recentes do usu??rio.</p>
 * 
 * <p>Mant??m as aulas indexadas pelo <code>id</code> (<code>Long</code>) em um <code>HashMap</code>.</p>
 * 
 * @author YaW Tecnologia
 */
public class AulaDataSource implements Serializable, DataSource<Aula> {
	
	private static Logger log = Logger.getLogger(AulaDataSource.class);
	
	private Map<Long, Aula> data = new LinkedHashMap<Long, Aula>();
	
	@Override
	public void add(Aula m) {
		if (m != null) {
			this.data.put(m.getId(), m);
		}
		updateSession();
	}
	
	@Override
	public void update(Aula m) {
		add(m);
	}
	
	@Override
	public void remove(Aula m) {
		if (m != null) {
			this.data.remove(m.getId());
		}
		updateSession();
	}
	
	@Override
	public void synch(Collection<Aula> collection) {
		log.debug("Sincronizando datasource de aulas...");
		this.data.clear();
		if (collection == null) {
			return;
		}
		for (Aula m: collection) {
			if (m != null) {
				this.data.put(m.getId(), m);
			}
		}
		updateSession();
	}
	
	@Override
	public List<Aula> getAll() {
		return new ArrayList<Aula>(data.values());
	}
	
	@Override
	public boolean isEmpty() {
		return data.isEmpty();
	}
	
	/**
	 * Pede ao App Engine para atualizar a sess??o.
	 */
	private void updateSession() {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession session = attr.getRequest().getSession();
		session.setAttribute("forceGaeSessionSerialization", System.currentTimeMillis());
	}

}
