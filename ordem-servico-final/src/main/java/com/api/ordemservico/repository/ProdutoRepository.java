package com.api.ordemservico.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.api.ordemservico.model.ProdutoModel;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoModel, Long>{

    @Query(value = "select prod from ProdutoModel prod where upper(trim(prod.descricao)) like %?1%")
    List<ProdutoModel> buscaProduto(String descricao);

}
