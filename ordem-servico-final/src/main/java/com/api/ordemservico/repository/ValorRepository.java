package com.api.ordemservico.repository;

import com.api.ordemservico.model.ValorModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ValorRepository extends JpaRepository<ValorModel, Long> {
}
