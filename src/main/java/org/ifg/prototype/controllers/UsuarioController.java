package org.ifg.prototype.controllers;

import java.util.List;
import java.util.Optional;

import org.ifg.prototype.dto.UsuarioDTO;
import org.ifg.prototype.entities.Usuario;
import org.ifg.prototype.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/users")
public class UsuarioController {

	@Autowired
	private UsuarioService service;

	@GetMapping
	public ResponseEntity<List<UsuarioDTO>> findAll() {
		List<UsuarioDTO> list = service.findAll();
		return ResponseEntity.ok(list);
	}

	@GetMapping("/buscar/{codigo}")
	public ResponseEntity<Object> buscarUsuario(@PathVariable(value = "codigo") Long codigo) {
		Optional<UsuarioDTO> usuarioDTOOptional = service.findById(codigo);

		if (!usuarioDTOOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado.");
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(usuarioDTOOptional.get());
		}
	}

	@PatchMapping("/atualizarusuario/{codigo}")
	public ResponseEntity<Object> usuarioUpdate(@PathVariable(value = "codigo") Long codigo,
			@RequestBody UsuarioDTO usuarioDTO) {

		usuarioDTO.setCodigo(codigo);

		Optional<UsuarioDTO> usuarioDTOOptional = service.findById(codigo);

		if (usuarioDTOOptional.isPresent()) {

			Usuario usuario = new Usuario();

			usuario.setCodigo(usuarioDTOOptional.get().getCodigo());
			usuario.setNome(usuarioDTOOptional.get().getNome());
			usuario.setEmail(usuarioDTOOptional.get().getEmail());
			usuario.setSenha(usuarioDTOOptional.get().getSenha());

			service.update(usuario);

			return ResponseEntity.status(HttpStatus.OK).body("Usuário atualizado");

		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado.");
		}

	}

	@PostMapping(value = "/salvar")
	public ResponseEntity<Object> salvarUsuario(@RequestBody UsuarioDTO usuarioDTO) {
		try {

			Usuario usuario = new Usuario();

			usuario.setCodigo(service.save(usuario).getCodigo());

			return ResponseEntity.ok().body(usuario);

		} catch (Exception ex) {
			return ResponseEntity.badRequest().body(ex.getMessage());
		}
	}
}
