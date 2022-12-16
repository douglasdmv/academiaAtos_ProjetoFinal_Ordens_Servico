package com.api.ordemservico.controller;

import java.util.List;

import com.api.ordemservico.repository.PrestadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.tinylog.Logger;

import com.api.ordemservico.model.PrestadorModel;

@RestController
@RequestMapping("prestador")
public class PrestadorController {

	@Autowired
	private PrestadorRepository prestadorRepository;

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@GetMapping(value = "lista-prestadores")
	@ResponseBody
	public ResponseEntity<List<PrestadorModel>> listaPrestador() {
		List<PrestadorModel> prestadores = prestadorRepository.findAll();
		Logger.info("Consulta realizada com sucesso!");
		return new ResponseEntity<List<PrestadorModel>>(prestadores, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping(value = "salvar-prestador")
	@ResponseBody
	public ResponseEntity<PrestadorModel> salvarPrestador(@RequestBody PrestadorModel prestadorModel) {
		PrestadorModel prestador = prestadorRepository.save(prestadorModel);
		Logger.info("Cadastro realizado corretamente!");
		return new ResponseEntity<PrestadorModel>(prestador, HttpStatus.CREATED);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping(value = "deletar-prestador")
	@ResponseBody
	public ResponseEntity<String> deletarPrestador(@RequestParam Long prestadorId) {
		prestadorRepository.deleteById(prestadorId);
		Logger.info("Prestador deletado com sucesso.");
		return new ResponseEntity<String>("Prestador deletado com sucesso!", HttpStatus.OK);
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@GetMapping(value = "buscar-prestador-id")
	@ResponseBody
	public ResponseEntity<PrestadorModel> buscarPrestadorId(@RequestParam(name = "prestadorId") Long prestadorId) {
		PrestadorModel prestador = prestadorRepository.findById(prestadorId).get();
		Logger.info("Consulta realizada com sucesso!");
		return new ResponseEntity<PrestadorModel>(prestador, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PutMapping(value = "atualizar-prestador")
	@ResponseBody
	public ResponseEntity<?> atualizarPrestador(@RequestBody PrestadorModel prestadorModel) {

		if(prestadorModel.getPrestadorId() == null) {
			Logger.error("ID não informado!");
			return new ResponseEntity<String>("Informe o ID para atualização dos dados.", HttpStatus.CONFLICT);
		}
		
		PrestadorModel prestador = prestadorRepository.saveAndFlush(prestadorModel);
		Logger.info("Prestador atualizado com sucesso.");
		return new ResponseEntity<PrestadorModel>(prestador, HttpStatus.OK);
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@GetMapping(value = "buscar-prestador")
	@ResponseBody
	public ResponseEntity<List<PrestadorModel>> buscaPrestador(@RequestParam(name = "nome") String nome) {
		List<PrestadorModel> prestadorModel = prestadorRepository.buscaPrestador(nome.trim().toUpperCase());
		Logger.info("Consulta realizada com sucesso!");
		return new ResponseEntity<List<PrestadorModel>>(prestadorModel, HttpStatus.OK);
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@GetMapping("cadastro")
	public ModelAndView cadPrestador(){
		ModelAndView mv = new ModelAndView("cadastro-prestador");
		return mv;
	}
}
