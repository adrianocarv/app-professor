package br.com.yaw.spgae.controller;

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

import br.com.yaw.spgae.model.Mercadoria;

/**
 * Define um <code>DataSource</code> para entidade (<code>Model</code>) Mercadoria.
 * 
 * <p>As operações de manipulação de dados no App Engine ocorrem de forma assíncrona.
 *    Antes de efetivar a mudança em todas as instâncias uma consulta pode trazer dados "sujos" (eventually consistent),
 *    a proposta do datasource é manter uma replica dos dados na sessão web, para refletir as mudanças recentes do usuário.</p>
 * 
 * <p>Mantém as mercadorias indexadas pelo <code>id</code> (<code>Long</code>) em um <code>HashMap</code>.</p>
 * 
 * @author YaW Tecnologia
 */
public class MercadoriaDataSource implements Serializable, DataSource<Mercadoria> {
	
	private static Logger log = Logger.getLogger(MercadoriaDataSource.class);
	
	private Map<Long, Mercadoria> data = new LinkedHashMap<Long, Mercadoria>();
	
	@Override
	public void add(Mercadoria m) {
		if (m != null) {
			this.data.put(m.getId(), m);
		}
		updateSession();
	}
	
	@Override
	public void update(Mercadoria m) {
		add(m);
	}
	
	@Override
	public void remove(Mercadoria m) {
		if (m != null) {
			this.data.remove(m.getId());
		}
		updateSession();
	}
	
	@Override
	public void synch(Collection<Mercadoria> collection) {
		log.debug("Sincronizando datasource de mercadorias...");
		this.data.clear();
		if (collection == null) {
			return;
		}
		for (Mercadoria m: collection) {
			if (m != null) {
				this.data.put(m.getId(), m);
			}
		}
		updateSession();
	}
	
	@Override
	public List<Mercadoria> getAll() {
		return new ArrayList<Mercadoria>(data.values());
	}
	
	@Override
	public boolean isEmpty() {
		return data.isEmpty();
	}
	
	/**
	 * Pede ao App Engine para atualizar a sessão.
	 */
	private void updateSession() {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession session = attr.getRequest().getSession();
		session.setAttribute("forceGaeSessionSerialization", System.currentTimeMillis());
	}

}
