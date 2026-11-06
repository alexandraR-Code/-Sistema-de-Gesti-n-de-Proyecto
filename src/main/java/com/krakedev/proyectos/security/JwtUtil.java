package com.krakedev.proyectos.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Date;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

public class JwtUtil {
	private static final Logger logger = LoggerFactory.getLogger(JwtUtil.class);

	private static final String CLAVE_SECRETA = "EsttaEsUnaClaveSecretaMuyLarga123456789";
	// Emisor del token
	private static final String EMISOR = "KarkedevBackend";

	private static final long TIEMPO_EXPIRACION = 1800000;

	public static String creacionToken(String username, String rol) {

		Algorithm algoritmo = Algorithm.HMAC256(CLAVE_SECRETA);

		long tiempoActual = System.currentTimeMillis();
		Date fechaExpiracion = new Date(tiempoActual + TIEMPO_EXPIRACION);

		String tokenGenerado = JWT.create().withIssuer(EMISOR).withSubject(username)
				.withIssuedAt(new Date(tiempoActual)).withExpiresAt(fechaExpiracion).withClaim("rol", rol)
				.sign(algoritmo);

		return tokenGenerado;
	}

	public static DecodedJWT verificarToken(String token) {
		try {
			Algorithm algoritmo = Algorithm.HMAC256(CLAVE_SECRETA);

			JWTVerifier verificador = JWT.require(algoritmo).withIssuer(EMISOR).build();

			DecodedJWT tokenDecodificado = verificador.verify(token);

			return tokenDecodificado;

		} catch (Exception e) {

			logger.error("Error de validacion del token: " + e.getMessage());

			return null;

		}
	}

}
