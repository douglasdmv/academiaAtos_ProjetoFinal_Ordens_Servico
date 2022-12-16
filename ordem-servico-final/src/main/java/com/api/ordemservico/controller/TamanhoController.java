package com.api.ordemservico.controller;

import com.api.ordemservico.model.TamanhoModel;
import com.api.ordemservico.repository.TamanhoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.tinylog.Logger;

import java.util.List;

@RestController
@RequestMapping("tamanho")
public class TamanhoController {

    @Autowired
    private TamanhoRepository tamanhoRepository;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping(value = "lista-tamanhos")
    @ResponseBody
    public ResponseEntity<List<TamanhoModel>> listaTamanho() {
        List<TamanhoModel> tamanhos = tamanhoRepository.findAll();
        Logger.info("Consulta realizada com sucesso!");
        return new ResponseEntity<List<TamanhoModel>>(tamanhos, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(value = "salvar-tamanho")
    @ResponseBody
    public ResponseEntity<TamanhoModel> salvarTamanho(@RequestBody TamanhoModel tamanhoModel) {
        TamanhoModel tamanho = tamanhoRepository.save(tamanhoModel);
        Logger.info("Cadastro realizado corretamente!");
        return new ResponseEntity<TamanhoModel>(tamanho, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping(value = "deletar-tamanho")
    @ResponseBody
    public ResponseEntity<String> deletarTamanho(@RequestParam Long tamanhoId) {
        tamanhoRepository.deleteById(tamanhoId);
        Logger.info("Tamanho deletado com sucesso.");
        return new ResponseEntity<String>("Tamanho deletado com sucesso!", HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping(value = "buscar-tamanho-id")
    @ResponseBody
    public ResponseEntity<TamanhoModel> buscarTamanhoId(@RequestParam(name = "tamanhoId") Long tamanhoId) {
        TamanhoModel tamanho = tamanhoRepository.findById(tamanhoId).get();
        Logger.info("Consulta realizada com sucesso!");
        return new ResponseEntity<TamanhoModel>(tamanho, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping(value = "atualizar-tamanho")
    @ResponseBody
    public ResponseEntity<?> atualizarTamanho(@RequestBody TamanhoModel tamanhoModel) {

        if (tamanhoModel.getTamanhoId() == null) {
            Logger.error("ID não informado!");
            return new ResponseEntity<String>("Informe o ID para atualização dos dados.", HttpStatus.CONFLICT);
        }

        TamanhoModel tamanho = tamanhoRepository.saveAndFlush(tamanhoModel);
        Logger.info("Tamanho atualizado com sucesso.");
        return new ResponseEntity<TamanhoModel>(tamanho,HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping(value = "buscar-tamanho")
    @ResponseBody
    public ResponseEntity<List<TamanhoModel>> buscaTamanho(@RequestParam(name = "tamanho") String tamanho) {
        List<TamanhoModel> tamanhoModel = tamanhoRepository.buscaTamanho(tamanho.trim().toUpperCase());
        Logger.info("Consulta realizada com sucesso!");
        return new ResponseEntity<List<TamanhoModel>>(tamanhoModel, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping("cadastro")
    public ModelAndView cadTamanho(){
        ModelAndView mv = new ModelAndView("cadastro-tamanho");
        return mv;
    }
}
