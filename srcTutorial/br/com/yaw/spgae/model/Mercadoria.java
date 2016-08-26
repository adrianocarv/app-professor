package br.com.yaw.spgae.model;

import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

/**
 * Classe de modelo que representa uma mercadoria. A mercadoria é um objeto persistido, por isso utilizamos o nome entidade.
 * 
 * <p>As funcionalidades desse sistema demonstração são concentradas no cadastro (CRUD) de mercadorias.</p>
 * 
 * <p>Essa entidade é mapeada com anotações do <code>Objectify</code>, um framework para persistência alto-nível no datastore (mecanismo de persistência do <code>App Engine</code>).</p>
 * 
 * @author YaW Tecnologia
 */
@Entity
public class Mercadoria implements Serializable {

	@Id
	private Long id;
	
	@NotNull @Size(min=5, max=200)
	private String nome;
	
	private String descricao;
	
	@NotNull @Min(value=1)
	private Integer quantidade;
	
	@NotNull @Min(value=1)
	private Double preco;
	
	public Mercadoria() {
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}
	
	public Double getPreco() {
		return preco;
	}
	
	public void setPreco(Double preco) {
		this.preco = preco;
	}
	
	public Integer getQuantidade() {
		return quantidade;
	}
	
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		
		if ((obj == null) || (obj.getClass() != this.getClass())) {
			return false;
		}
		
		Mercadoria outro = (Mercadoria) obj;
		boolean equal = (id != null && id.equals(outro.id)) 
				|| (nome != null && nome.equals(outro.nome))
				|| (descricao != null && descricao.equals(outro.descricao));
		return equal;
	}
	
	@Override
	public int hashCode() {
		int hash = 17;
		
		hash = (31 * hash) + (id == null ? 0 : id.intValue());
		hash = (31 * hash) + (nome == null ? 0 : nome.hashCode());
		hash = (31 * hash) + (descricao == null ? 0 : descricao.hashCode());
		
		return hash;
	}

}
