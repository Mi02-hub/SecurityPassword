package com.Bcript.safety.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.Bcript.safety.dto.UsuarioDTO;
import com.Bcript.safety.entities.Usuario;
import com.Bcript.safety.repositories.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepo;
	// chamando o metodo criado em config
	@Autowired
	private PasswordEncoder passwordEncoder;

	public UsuarioDTO salvarUsuario(UsuarioDTO dto) {
		Usuario user = new Usuario();
		user.setEmail(dto.getEmail());

		// usando o metodo de criptografia na senha
		user.setSenha(passwordEncoder.encode(dto.getSenha()));
		user = usuarioRepo.save(user);
		return new UsuarioDTO(user);
	}

	// aqui ele usa o boolean como um identificador (verdade ou falso) caso o
	// email/usuario existe ou nao(negado ou aprovado)
	public boolean autenticarUsuario(UsuarioDTO dto) {
		Usuario user = usuarioRepo.findByEmail(dto.getEmail());

		if (user == null) {
			return false;
		}

		// compara a senha que eu recebo da senha criptografada e a senha cadastrada no banco
		return passwordEncoder.matches(dto.getSenha(), user.getSenha());
	}
}
