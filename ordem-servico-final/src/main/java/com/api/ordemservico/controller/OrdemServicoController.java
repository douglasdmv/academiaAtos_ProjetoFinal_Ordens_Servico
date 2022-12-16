package com.api.ordemservico.controller;

import java.util.List;

import com.api.ordemservico.repository.OrdemServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.tinylog.Logger;

import com.api.ordemservico.model.OrdemServicoModel;

@RestController
@RequestMapping("ordem-servico")
public class OrdemServicoController {

	@Autowired
	private OrdemServicoRepository ordemServicoRepository;

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@GetMapping(value = "lista-ordens-servico")
	@ResponseBody
	public ResponseEntity<List<OrdemServicoModel>> listaOrdensServico() {
		List<OrdemServicoModel> ordens = ordemServicoRepository.findAll();
		Logger.info("Consulta realizada com sucesso!");
		return new ResponseEntity<List<OrdemServicoModel>>(ordens, HttpStatus.OK);
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@PostMapping(value = "salvar-ordem-servico")
	@ResponseBody
	public ResponseEntity<OrdemServicoModel> salvarOrdemServico(@RequestBody OrdemServicoModel ordemServicoModel) {
		ordemServicoModel.setSituacao("EM ABERTA");
		OrdemServicoModel ordem = ordemServicoRepository.save(ordemServicoModel);
		Logger.info("Cadastro realizado corretamente!");
		return new ResponseEntity<OrdemServicoModel>(ordem, HttpStatus.CREATED);
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@GetMapping("deletar-ordem-servico/{id}")
	public ModelAndView deletarOrdemServico(@PathVariable("id") Long idOrdem) {
		ordemServicoRepository.deleteById(idOrdem);
		Logger.info("Ordem de serviço deletada com sucesso!");
		return pesqOrdemServico();
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@GetMapping("buscar-ordem-servico/{id}")
	public ModelAndView buscareOrdemServicoId(@PathVariable("id") Long idOrdem) {
		ordemServicoRepository.findById(idOrdem).get().setSituacao("FINALIZADA");
		Logger.info("Consulta realizada com sucesso!");
		return pesqOrdemServico();
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@PutMapping("atualizar-ordem-servico")
	@ResponseBody
	public ResponseEntity<?> atualizarOrdemServico(@RequestBody OrdemServicoModel ordemServicoModel) {

		if(ordemServicoModel.getIdOrdem() == null) {
			Logger.error("ID não informado!");
			return new ResponseEntity<String>("Informe o ID para atualização dos dados.", HttpStatus.CONFLICT);
		}
		OrdemServicoModel ordem = ordemServicoRepository.saveAndFlush(ordemServicoModel);
		Logger.info("Ordem de serviço atualizada com sucesso.");
		return new ResponseEntity<OrdemServicoModel>(ordem, HttpStatus.OK);
	}

	@GetMapping("cadastro")
	public ModelAndView cadOrdemServico(){
		ModelAndView mv = new ModelAndView("cadastro-ordem-servico");
		return mv;
	}

	@GetMapping("pesquisa")
	public ModelAndView pesqOrdemServico(){
		ModelAndView mv = new ModelAndView("pesquisa-ordem-servico");
		mv.addObject("ordensList", ordemServicoRepository.findAll());
		return mv;
	}
}
