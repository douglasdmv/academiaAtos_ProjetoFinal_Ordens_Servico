package com.api.ordemservico.controller;

import java.util.List;

import com.api.ordemservico.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.tinylog.Logger;

import com.api.ordemservico.model.ProdutoModel;

@RestController
@RequestMapping("produto")
public class ProdutoController {

	@Autowired
	private ProdutoRepository produtoRepository;

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@GetMapping(value = "lista-produtos")
	@ResponseBody
	public ResponseEntity<List<ProdutoModel>> listaProdutos() {
		List<ProdutoModel> produtos = produtoRepository.findAll();
		Logger.info("Consulta realizada com sucesso!");
		return new ResponseEntity<List<ProdutoModel>>(produtos, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping(value = "salvar-produto")
	@ResponseBody
	public ResponseEntity<ProdutoModel> salvarProduto(@RequestBody ProdutoModel produtoModel) {
		ProdutoModel produto = produtoRepository.save(produtoModel);
		Logger.info("Cadastro realizado corretamente!");
		return new ResponseEntity<ProdutoModel>(produto, HttpStatus.CREATED);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping(value = "deletar-produto")
	@ResponseBody
	public ResponseEntity<String> deletarProduto(@RequestParam Long produtoId) {
		produtoRepository.deleteById(produtoId);
		Logger.info("Produto deletado com sucesso.");
		return new ResponseEntity<String>("Produto deletado com sucesso.", HttpStatus.OK);
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@GetMapping(value = "buscar-produto-id")
	@ResponseBody
	public ResponseEntity<ProdutoModel> buscarProdutoId(@RequestParam(name = "produtoId")Long produtoId) {
		ProdutoModel produto = produtoRepository.findById(produtoId).get();
		Logger.info("Consulta realizada com sucesso!");
		return new ResponseEntity<ProdutoModel>(produto, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PutMapping(value = "atualizar-produto")
	@ResponseBody
	public ResponseEntity<?> atualizarProduto(@RequestBody ProdutoModel produtoModel) {

		if(produtoModel.getProdutoId() == null) {
			Logger.error("ID não informado!");
			return new ResponseEntity<String>("Informe o ID para atualização dos dados.", HttpStatus.CONFLICT);
		}
		
		ProdutoModel produto = produtoRepository.saveAndFlush(produtoModel);
		Logger.info("Produto atualizado com sucesso.");
		return new ResponseEntity<ProdutoModel>(produto, HttpStatus.OK);
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@GetMapping(value = "buscar-produto")
	@ResponseBody
	public ResponseEntity<List<ProdutoModel>> buscaProduto(@RequestParam(name = "descricao") String descricao) {
		List<ProdutoModel> produtoModel = produtoRepository.buscaProduto(descricao.trim().toUpperCase());
		Logger.info("Consulta realizada com sucesso!");
		return new ResponseEntity<List<ProdutoModel>>(produtoModel, HttpStatus.OK);
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@GetMapping("cadastro")
	public ModelAndView cadProduto(){
		ModelAndView mv = new ModelAndView("cadastro-produto");
		return mv;
	}
}
