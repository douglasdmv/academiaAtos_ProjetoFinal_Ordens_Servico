package com.api.ordemservico.controller;

import java.util.List;

import com.api.ordemservico.repository.ProcessoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.tinylog.Logger;

import com.api.ordemservico.model.ProcessoModel;

@RestController
@RequestMapping("processo")
public class ProcessoController {

	@Autowired
	private ProcessoRepository processoRepository;

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@GetMapping(value = "lista-processos")
	@ResponseBody
	public ResponseEntity<List<ProcessoModel>> listaProcesso() {
		List<ProcessoModel> processos = processoRepository.findAll();
		Logger.info("Consulta realizada com sucesso!");
		return new ResponseEntity<List<ProcessoModel>>(processos, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping(value = "salvar-processo")
	@ResponseBody
	public ResponseEntity<ProcessoModel> salvarProcesso(@RequestBody ProcessoModel processoModel) {
		ProcessoModel processo = processoRepository.save(processoModel);
		Logger.info("Cadastro realizado corretamente!");
		return new ResponseEntity<ProcessoModel>(processo, HttpStatus.CREATED);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping(value = "deletar-processo")
	@ResponseBody
	public ResponseEntity<String> deletarProcesso(@RequestParam Long processoId) {
		processoRepository.deleteById(processoId);
		Logger.info("Processo deletado com sucesso.");
		return new ResponseEntity<String>("Processo deletado com ducesso!", HttpStatus.OK);
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@GetMapping(value = "buscar-processo-id")
	@ResponseBody
	public ResponseEntity<ProcessoModel> buscarProcessoId(@RequestParam(name = "processoId") Long processoId) {
		ProcessoModel processo = processoRepository.findById(processoId).get();
		Logger.info("Consulta realizada com sucesso!");
		return new ResponseEntity<ProcessoModel>(processo, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PutMapping(value = "atualizar-processo")
	@ResponseBody
	public ResponseEntity<?> atualizarProcesso(@RequestBody ProcessoModel processoModel) {

		if(processoModel.getProcessoId() == null) {
			Logger.error("ID não informado!");
			return new ResponseEntity<String>("Informe o ID para atualização dos dados.", HttpStatus.CONFLICT);
		}
		
		ProcessoModel processo = processoRepository.saveAndFlush(processoModel);
		Logger.info("Processo atualizado com sucesso.");
		return new ResponseEntity<ProcessoModel>(processo, HttpStatus.OK);
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@GetMapping(value = "buscar-processo")
	@ResponseBody
	public ResponseEntity<List<ProcessoModel>> buscaProcesso(@RequestParam(name = "processo") String processo) {
		List<ProcessoModel> processoModel = processoRepository.buscaProcesso(processo.trim().toUpperCase());
		Logger.info("Consulta realizada com sucesso!");
		return new ResponseEntity<List<ProcessoModel>>(processoModel, HttpStatus.OK);
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@GetMapping("cadastro")
	public ModelAndView cadProcesso(){
		ModelAndView mv = new ModelAndView("cadastro-processo");
		return mv;
	}
}
