package com.api.ordemservico.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.api.ordemservico.model.PrestadorModel;

import java.util.List;

@Repository
public interface PrestadorRepository extends JpaRepository<PrestadorModel, Long>{

    @Query(value = "select pres from PrestadorModel pres where upper(trim(pres.nome)) like %?1%")
    List<PrestadorModel> buscaPrestador(String nome);

}
