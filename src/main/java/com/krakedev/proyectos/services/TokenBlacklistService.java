package com.krakedev.proyectos.services;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

@Service
public class TokenBlacklistService {

	private final Set<String> blacklist = ConcurrentHashMap.newKeySet();

	//Metood par invalidadr un token 
	public void invalidarToken(String token) {
		blacklist.add(token);
	}

	//Metodo para vefificar si existe este token
	public boolean estaInvalidado(String token) {
		return blacklist.contains(token);
	}

}
