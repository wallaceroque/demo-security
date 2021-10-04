package br.wallace.security.demo.controllers;

import java.security.Principal;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.wallace.security.demo.domain.WelcomeTO;

@CrossOrigin(origins = "*", maxAge = 3600, allowedHeaders = "*")
@RestController
@RequestMapping("/")
public class Auth {
	
	@GetMapping
	public ResponseEntity<WelcomeTO> welcome() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = "";
		
		if (principal instanceof UserDetails)
			username = ((UserDetails)principal).getUsername();
		else if (principal instanceof Principal)
			username = ((Principal)principal).getName();
		else if (principal instanceof Authentication) 
			username = ((Authentication)principal).getName();
		else if (principal instanceof OAuth2AuthenticatedPrincipal)
			username = ((OAuth2AuthenticatedPrincipal)principal).getAttribute("given_name");
		else {
			username = principal.toString();
		}
		
		return ResponseEntity.ok(new WelcomeTO(username));
	}

}
