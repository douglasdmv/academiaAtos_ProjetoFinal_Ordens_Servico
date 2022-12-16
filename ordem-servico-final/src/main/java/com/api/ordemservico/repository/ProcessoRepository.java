package com.api.ordemservico.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.api.ordemservico.model.ProcessoModel;

import java.util.List;

@Repository
public interface ProcessoRepository extends JpaRepository<ProcessoModel, Long>{

    @Query(value = "select proc from ProcessoModel proc where upper(trim(proc.processo)) like %?1%")
    List<ProcessoModel> buscaProcesso(String processo);

}
