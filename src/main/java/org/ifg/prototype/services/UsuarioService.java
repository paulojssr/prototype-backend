/**
 * 
 */
package org.ifg.prototype.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.ifg.prototype.dto.UsuarioDTO;
import org.ifg.prototype.entities.Usuario;
import org.ifg.prototype.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioService {

	@Autowired
	private final UsuarioRepository usuarioRepository;

	public UsuarioService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	@Transactional(readOnly = true)
	public List<UsuarioDTO> findAll() {
		List<Usuario> result = usuarioRepository.findAll();

		return result.stream().map(UsuarioDTO::new).collect(Collectors.toList());
	}

	@Transactional
	public Optional<UsuarioDTO> findById(Long id) {
		Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);

		return usuarioOptional.map(UsuarioDTO::new);
	}

	@Transactional
	public Usuario save(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}

	@Transactional
	public void update(Usuario usuario) {
		usuarioRepository.save(usuario);
	}

	public void deleteUsuario(Long id) {
		usuarioRepository.deleteById(id);
	}

	public void exist(Long id) {
		usuarioRepository.existsById(id);
	}
}
