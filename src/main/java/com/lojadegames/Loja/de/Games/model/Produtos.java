package com.lojadegames.Loja.de.Games.model;

import org.hibernate.annotations.ManyToAny;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name ="tb_produtos")
public class Produtos {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long idProdutos;
	
	@NotBlank
	@Size(min=4, max =255, message = "O atributo nome deve conter no minimo 4 caracteres e no maximo 255")
	private String nomeDoJogo;
	
	private Float preco;
	
	private Integer quantidadeEstoque;
	
	
	@ManyToAny
	@JsonIgnoreProperties("categoria")
	private Long categoria;
	
	public Long getCategoria() {
		return categoria;
	}

	public void setCategoria(Long idCategoria) {
		this.categoria = idCategoria;
	}

	public Long getIdProdutos() {
		return idProdutos;
	}

	public void setIdProdutos(Long idProdutos) {
		this.idProdutos = idProdutos;
	}

	public String getNomeDoJogo() {
		return nomeDoJogo;
	}

	public void setNomeDoJogo(String nomeDoJogo) {
		this.nomeDoJogo = nomeDoJogo;
	}

	public Float getPreco() {
		return preco;
	}

	public void setPreco(Float preco) {
		this.preco = preco;
	}

	public Integer getQuantidadeEstoque() {
		return quantidadeEstoque;
	}

	public void setQuantidadeEstoque(Integer quantidadeEstoque) {
		this.quantidadeEstoque = quantidadeEstoque;
	}


}
