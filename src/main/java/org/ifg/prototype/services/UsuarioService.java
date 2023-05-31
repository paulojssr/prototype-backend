/**
 * 
 */
package org.ifg.prototype.services;

import java.util.List;
import java.util.stream.Collectors;

import org.ifg.prototype.dto.UsuarioDTO;
import org.ifg.prototype.entities.Usuario;
import org.ifg.prototype.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author paulojorge
 *
 */
@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;
	
	@Transactional(readOnly = true)
	public List<UsuarioDTO> findAll(){
		List<Usuario> result = repository.findAll();
		return result.stream().map(x -> new UsuarioDTO(x))
				.collect(Collectors.toList());
	}
}
