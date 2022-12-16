package com.api.ordemservico.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.ordemservico.model.OrdemServicoModel;

@Repository
public interface OrdemServicoRepository extends JpaRepository<OrdemServicoModel, Long>{

}
