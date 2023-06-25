package org.ifg.prototype.repositories;

import org.ifg.prototype.entities.Prompt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Um repositório para a entidade {@link Prompt}.
 * Crated by Daniel 04/06/2023.
 */
@Repository
public interface PromptRepository extends JpaRepository<Prompt,Long> {
}