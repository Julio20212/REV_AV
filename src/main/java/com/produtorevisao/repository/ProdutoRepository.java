package com.produtorevisao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.produtorevisao.entity.Produto;

public interface ProdutoRepository extends JpaRepository <Produto, Long>{
	List<Produto> findByDescricao (String descricao);
	List<Produto> findByNome (String nome);
	List<Produto> findByPreco (double preco);

}
