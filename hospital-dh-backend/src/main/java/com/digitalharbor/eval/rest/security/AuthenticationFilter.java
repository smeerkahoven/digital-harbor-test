package com.digitalharbor.eval.rest.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.authentication.UserServiceBeanDefinitionParser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.digitalharbor.eval.rest.SpringApplicationContext;
import com.digitalharbor.eval.rest.exception.HospitalException;
import com.digitalharbor.eval.rest.service.imp.UsuarioService;
import com.digitalharbor.eval.rest.ui.model.dto.UsuarioDto;
import com.digitalharbor.eval.rest.ui.model.request.UsuarioRequest;
import com.digitalharbor.eval.rest.ui.model.response.ModelResponse;
import com.digitalharbor.eval.rest.ui.model.response.UsuarioResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private final AuthenticationManager manager;

	public AuthenticationFilter(AuthenticationManager manager) {
		this.manager = manager;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {

		try {
			UsuarioRequest usuarioRequest = new ObjectMapper().readValue(request.getInputStream(),
					UsuarioRequest.class);

			return manager.authenticate(new UsernamePasswordAuthenticationToken(usuarioRequest.getUsername(),
					usuarioRequest.getPassword(), new ArrayList<>()));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication auth) throws IOException, ServletException {

		String username = ((User) auth.getPrincipal()).getUsername();

		String token = Jwts.builder().setSubject(username)
				.setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SecurityConstants.getTokenSecret()).compact();

		UsuarioService service = (UsuarioService) SpringApplicationContext.getBean("usuarioService") ;
		UsuarioResponse usuarioResponse = new UsuarioResponse();
		try {
			UsuarioDto dtoFromDb = service.getUsuarioByUsername(username);
			usuarioResponse.setPublicId(dtoFromDb.getPublicId());
		} catch (HospitalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		usuarioResponse.setToken(token);
		usuarioResponse.setCodigo(ModelResponse.RESPONSE_OK);
		usuarioResponse.setUsername(username);
		
		Gson gson = new Gson();

		response.getWriter().write(gson.toJson(usuarioResponse));
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		response.addHeader(SecurityConstants.HEADER_AUTH, SecurityConstants.TOKEN_PREFIX + token);

	}

}
