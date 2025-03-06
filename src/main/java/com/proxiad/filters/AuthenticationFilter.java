package com.proxiad.filters;

import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    public AuthenticationFilter() {
        super("/login");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (!request.getMethod().equalsIgnoreCase(HttpMethod.POST.name())) {
            return null;
        }

        // Extraire l'email et le mot de passe depuis la requête
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(email, password));
    }
}