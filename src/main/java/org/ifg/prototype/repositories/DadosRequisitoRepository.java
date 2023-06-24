package org.ifg.prototype.repositories;

import org.ifg.prototype.entities.DadosRequisito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DadosRequisitoRepository
        extends JpaRepository<DadosRequisito, Long>, JpaSpecificationExecutor<DadosRequisito> {
}