package org.ifg.prototype.repositories;

import org.ifg.prototype.entities.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Um repositório para a entidade {@link Projeto}.
 * Crated by Daniel 03/06/2023.
 */
@Repository
public interface ProjetoRepository extends JpaRepository<Projeto,Long> {
}
