package com.lojadegames.Loja.de.Games.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import com.lojadegames.Loja.de.Games.model.Produtos;
import com.lojadegames.Loja.de.Games.repository.CategoriaRepository;
import com.lojadegames.Loja.de.Games.repository.ProdutosRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/produtos")
@CrossOrigin(origins = "*", allowedHeaders="*")
public class ProdutosController {
	
	@Autowired
	private ProdutosRepository produtosRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@GetMapping()
	public ResponseEntity<List<Produtos>> getAll(){
		return ResponseEntity.ok(produtosRepository.findAll());
	}
	
	@GetMapping("/{idProdutos}")
	public ResponseEntity<Produtos> getById(@PathVariable Long idProdutos){
		return produtosRepository.findById(idProdutos)
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	@GetMapping("/nome-do-jogo/{nomeDoJogo}")
	public ResponseEntity<List<Produtos>> getByNome(@PathVariable String nomeDoJogo){
		return ResponseEntity.ok(produtosRepository.findAllByNomeDoJogoContainingIgnoreCase(nomeDoJogo));
	}
	
	@PostMapping
	public ResponseEntity<Produtos> post(@Valid @RequestBody Produtos produtos){
		return ResponseEntity.status(HttpStatus.CREATED).body(produtosRepository.save(produtos));
	}
	
	@PutMapping()
	public ResponseEntity<Produtos> put(@Valid @RequestBody Produtos produtos){
			if(categoriaRepository.existsById(produtos.getCategoria().getId())) {
				return produtosRepository.findById(produtos.getIdProdutos()).map(resposta -> ResponseEntity.status(HttpStatus.OK).body(produtosRepository.save(produtos)))
						.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());}
			
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Categoria não existe", null);
		
	}

	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{idProdutos}")
	public void  delete(@PathVariable  Long idProdutos) {
		Optional<Produtos> produtos = produtosRepository.findById(idProdutos);
		if(produtos.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
			
		}else {
			produtosRepository.deleteById(idProdutos);
		}

	}

}