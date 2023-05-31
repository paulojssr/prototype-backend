/**
 * 
 */
package org.ifg.prototype.repositories;

import org.ifg.prototype.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author paulojorge
 *
 */
public interface UsuarioRepository extends JpaRepository<Usuario,Long>{

}
