package com.produtorevisao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.produtorevisao.entity.Produto;
import com.produtorevisao.service.ProdutoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;



@Tag(name = "Módulos Produtos - ", description = "API REST - GERENCIAMENTO DE PRODUTOS")
@RestController
@RequestMapping(value = "/produto")
public class ProdutoController {

	private final ProdutoService produtoService;

	@Autowired
	public ProdutoController(ProdutoService produtoService) {
		this.produtoService = produtoService;
	}

	@Operation(summary = "Localizar produto por ID")
	@GetMapping("/{id}")
	public ResponseEntity<Produto> getProductById(@PathVariable Long id) {
		Produto produto = produtoService.getProdutoById(id);
		if (produto != null) {
			return ResponseEntity.ok(produto);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@Operation(summary = "Localizar todos produtos")
	@GetMapping
	public ResponseEntity<List<Produto>> getAllProdutos() {
		List<Produto> produtos = produtoService.getAllProdutos();
		return ResponseEntity.ok(produtos);
	}

	// Query Method
	@Operation(summary = "Localizar descrição do produto")
	@GetMapping("/descricao/{descricao}")
	public ResponseEntity<List<Produto>> buscarProdutosPorDescricao(@PathVariable String descricao) {
		List<Produto> produtos = produtoService.buscarProdutosPorDescricao(descricao);
		return ResponseEntity.ok(produtos);
	}

	@Operation(summary = "Localizar nome do produto")
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Produto>> buscarProdutosPorNome(@PathVariable String nome) {
		List<Produto> produtos = produtoService.buscarProdutosPorNome(nome);
		return ResponseEntity.ok(produtos);
	}

	@Operation(summary = "Localizar preço do produto")
	@GetMapping("/preco/{preco}")
	public ResponseEntity<List<Produto>> buscarProdutosPorPreco(@PathVariable double preco) {
		List<Produto> produtos = produtoService.buscarProdutosPorPreco(preco);
		return ResponseEntity.ok(produtos);
	}

	@Operation(summary = "Criar produto")
	@PostMapping("/")
	public ResponseEntity<Produto> criarProduto(@RequestBody @Valid Produto produto) {
		Produto criarProduto = produtoService.salvarProduto(produto);
		return ResponseEntity.status(HttpStatus.CREATED).body(criarProduto);
	}

	@PutMapping("/{id}")
	@Operation(summary = "Alterar produto pelo ID")
	public ResponseEntity<Produto> updateProduto(@PathVariable Long id, @RequestBody @Valid Produto produto) {
		Produto updatedProduto = produtoService.updateProduto(id, produto);
		if (updatedProduto != null) {
			return ResponseEntity.ok(updatedProduto);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@Operation(summary = "Deletar produto pelo ID")
	@DeleteMapping("/{id}")
	public ResponseEntity<Produto> deleteProduto(@PathVariable Long id) {
		boolean deleted = produtoService.deleteProduto(id);
		if (deleted) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}

}
