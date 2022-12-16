package com.api.ordemservico.repository;

import com.api.ordemservico.model.TamanhoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TamanhoRepository extends JpaRepository<TamanhoModel, Long> {

    @Query(value = "select t from TamanhoModel t where upper(trim(t.tamanho)) like %?1%")
    List<TamanhoModel> buscaTamanho(String tamanho);

    //@Query(value = "select tamanhoId, tamanho from TamanhoModel")
    //List<TamanhoModel> buscaTodosTamanhos(String tamanhos);

}
