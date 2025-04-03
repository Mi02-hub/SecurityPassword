package com.Bcript.safety.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Bcript.safety.dto.UsuarioDTO;
import com.Bcript.safety.services.UsuarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("usuario")
public class UsuarioController {

	@Autowired
	UsuarioService usuarioService;

	@PostMapping(value = "/salvar")
	public ResponseEntity<UsuarioDTO> salvar(@Valid @RequestBody UsuarioDTO dto) {
		dto = usuarioService.salvarUsuario(dto);
		return ResponseEntity.ok(dto);

	}

	@PostMapping(value = "/login")
	public ResponseEntity<?> login(@Valid @RequestBody UsuarioDTO dto) {
		boolean login = usuarioService.autenticarUsuario(dto);

		if (login) {
			return ResponseEntity.ok("Sucesso! ;)");
		} else {
			return ResponseEntity.status(401).body("Email ou senha negados! :(");
		}

	}
}
