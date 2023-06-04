package org.ifg.prototype.repositories;

import org.ifg.prototype.entities.Requisito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequisitoRepository extends JpaRepository<Requisito, Long> {

}
