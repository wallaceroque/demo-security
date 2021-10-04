package br.wallace.security.demo.domain;

import java.io.Serializable;

public class WelcomeTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9065801384885325078L;
	private String message;
	
	public WelcomeTO() {}
	
	public WelcomeTO(String username) {
		this.message = "Hello " + username + "!";
	}
	
	public String getMessage() {
		return message;
	}

}
