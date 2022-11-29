package com.boasaude.planodesaude.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.boasaude.planodesaude.model.PlanoDeSaude;
import com.boasaude.planodesaude.repository.PlanoDeSaudeRepository;

@Service
public class UserDetailsServiceImpl {
	@Autowired
	private PlanoDeSaudeRepository associadoRepository;
	
	public UserDetailsImpl loadUserByUsername(String userName) throws UsernameNotFoundException {
		Optional<PlanoDeSaude> user = associadoRepository.findByNome(userName);
		user.orElseThrow(() -> new UsernameNotFoundException(userName + " not found."));
		
		return user.map(UserDetailsImpl::new).get();
	}

}
