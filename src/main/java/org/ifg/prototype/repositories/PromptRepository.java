package org.ifg.prototype.repositories;

import org.ifg.prototype.entities.Prompt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PromptRepository extends JpaRepository<Prompt,Long> {
}