package br.com.yaw.spgae.dao;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.yaw.spgae.model.Mercadoria;

import com.googlecode.objectify.Key;

/**
 * Implementa o contrato de persist��ncia da entidade <code>Mercadoria</code>.
 * 
 * <p>
 *   Nessa aplica����o resolvemos a persist��ncia utilizando o Objectify, um framework de persist��ncia para o App Engine.<br/>
 *   A proposta do Objetify �� denifir uma API mais alto-n��vel para manipular dados no <code>DataStore</code> do App Engine.
 * </p>
 * 
 * <p><code>MercadoriaDAOObjectify</code> �� anotado com <code>@Repository</code>, dessa forma o Spring Framework 
 * gerencia o ciclo de vida dos objetos dessa classe, al��m de permitir a inje����o de depend��ncia em outros componentes. 
 * 
 * @see br.com.yaw.sjpac.dao.MercadoriaDAO
 * @see com.googlecode.objectify.ObjectifyService.
 * 
 * @author YaW Tecnologia
 */
@Repository
public class MercadoriaDAOObjectify implements Serializable, MercadoriaDAO {

	@Override
	public Mercadoria save(Mercadoria mercadoria) {
		ofy().save().entity(mercadoria).now();
		return mercadoria;
	}
	
	@Override
	public List<Mercadoria> getAll() {
		return ofy().load().type(Mercadoria.class).list();
	}
	
	@Override
	public Boolean remove(Mercadoria mercadoria) {
		ofy().delete().entity(mercadoria).now();
		return true;
	}
	
	@Override
	public Mercadoria findById(Long id) {
		Key<Mercadoria> k = Key.create(Mercadoria.class, id);
		return ofy().load().key(k).get();
	}
	
}
