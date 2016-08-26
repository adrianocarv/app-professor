package br.unifesp.ict.controller;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * Define uma estrutura para armazenar e fornecer dados para um <code>View</code>, mantido na camada server.
 * 
 * @author YaW Tecnologia
 *
 * @param <T> Tipo da informa????o mantida no <code>DataSource</code>, normalmente um <code>Model</code>.
 */
public interface DataSource<T extends Serializable> {

	/**
	 * Adiciona elemento ao <code>DataSource</code>.
	 * @param t
	 */
	void add(T t);
	
	/**
	 * Atualiza elemento ao <code>DataSource</code>.
	 * @param t
	 */
	void update(T t);
	
	/**
	 * Remove elemento ao <code>DataSource</code>.
	 * @param t
	 */
	void remove(T t);
	
	/**
	 * Sincroniza todos os elementos do <code>DataSource</code> com as inst??ncias da cole????o.
	 * @param collection
	 */
	void synch(Collection<T> collection);
	
	/**
	 * @return <code>List</code> com todos os elementos contidos no <code>DataSource</code>.
	 */
	List<T> getAll();
	
	boolean isEmpty();
	
}
