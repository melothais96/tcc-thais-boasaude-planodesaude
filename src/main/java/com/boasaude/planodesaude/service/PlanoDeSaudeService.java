package com.boasaude.planodesaude.service;

import java.nio.charset.Charset;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.boasaude.planodesaude.model.PlanoDeSaude;
import com.boasaude.planodesaude.model.PlanoDeSaudeLogin;
import com.boasaude.planodesaude.repository.PlanoDeSaudeRepository;

@Service
public class PlanoDeSaudeService {
	@Autowired
	private PlanoDeSaudeRepository repository;

	private static final Logger LOGGER = Logger.getLogger(PlanoDeSaudeService.class.getName());

	public PlanoDeSaudeService(PlanoDeSaudeRepository repository) {
		this.repository = repository;
	}

	public PlanoDeSaude cadastrarUsuario(PlanoDeSaude planodesaude) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		String senhaEncoder = encoder.encode(planodesaude.getSenha());
		planodesaude.setSenha(senhaEncoder);

		return repository.save(planodesaude);
	}

	public Optional<PlanoDeSaudeLogin> logar(Optional<PlanoDeSaudeLogin> user) {

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		Optional<PlanoDeSaude> usuario = repository.findByUsuario(user.get().getUsuario());

		if (usuario.isPresent()) {
			if (encoder.matches(user.get().getSenha(), usuario.get().getSenha())) {

				String auth = user.get().getUsuario() + ":" + user.get().getSenha();
				byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
				String authHeader = "Basic " + new String(encodedAuth);

				user.get().setToken(authHeader);
				user.get().setUsuario(authHeader);
				user.get().setId(usuario.get().getId());
				user.get().setAdmin(usuario.get().isAdmin());

				return user;

			}
		}
		return null;
	}

	public void run(ApplicationArguments args) throws Exception {
		PlanoDeSaude associado = new PlanoDeSaude("Gaspar Barancelli Junior");

		LOGGER.log(Level.INFO, "Persist");
		repository.save(associado);
		LOGGER.log(Level.INFO, associado.toString());

		LOGGER.log(Level.INFO, "Find");
		repository.findById(associado.getId()).ifPresent(it -> {
			LOGGER.log(Level.INFO, associado.toString());
		});

		PlanoDeSaude associado2 = new PlanoDeSaude("Rodrigo Barancelli");

		LOGGER.log(Level.INFO, "Persist");
		repository.save(associado2);
		LOGGER.log(Level.INFO, associado2.toString());

		associado2.setNome("Rodrigo Dalla Valle Barancelli");
		LOGGER.log(Level.INFO, "Update");
		repository.save(associado2);
		LOGGER.log(Level.INFO, associado2.toString());

		LOGGER.log(Level.INFO, "FindAll");
		repository.findAll().forEach(it -> LOGGER.log(Level.INFO, it.toString()));

		LOGGER.log(Level.INFO, "Delete");
		repository.delete(associado2);
		LOGGER.log(Level.INFO, associado2.toString());
	}
}
