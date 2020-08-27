package com.jornadadev.mercadolivre.config.security;

import lombok.Data;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Data
public class LoginInputDto {

	private String email;
	private String password;

	public UsernamePasswordAuthenticationToken build() {
		return new UsernamePasswordAuthenticationToken(this.email,
				this.password);
	}
}