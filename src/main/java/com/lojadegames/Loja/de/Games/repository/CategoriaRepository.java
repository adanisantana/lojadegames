package com.lojadegames.Loja.de.Games.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.lojadegames.Loja.de.Games.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>{
	public List<Categoria> findAllByNomeCategoriaContainingIgnoreCase(@Param("nomeCategoria") String nomeCategoria);

	

}
