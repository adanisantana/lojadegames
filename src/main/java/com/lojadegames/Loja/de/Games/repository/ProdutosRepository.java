package com.lojadegames.Loja.de.Games.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.lojadegames.Loja.de.Games.model.Produtos;

public interface ProdutosRepository extends JpaRepository<Produtos,Long> {

	public List<Produtos> findAllByNomeDoJogoContainingIgnoreCase(@Param("nomeDoJogo")String nomeDoJogo);



}
