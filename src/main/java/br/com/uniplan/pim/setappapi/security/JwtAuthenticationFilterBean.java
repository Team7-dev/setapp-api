package br.com.uniplan.pim.setappapi.security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class JwtAuthenticationFilterBean extends GenericFilterBean {

    private static final String HEADER_STRING = "Authorization";

    private static final String TOKEN_PREFIX = "Bearer ";

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private TokenProvider jwtTokenUtil;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String header = httpServletRequest.getHeader(HEADER_STRING);
        String usuario = null;
        String authToken = null;
        if (header != null && header.startsWith(TOKEN_PREFIX)) {
            authToken = header.replace(TOKEN_PREFIX, "");
            try {
                usuario = jwtTokenUtil.getUsernameFromToken(authToken);
            } catch (IllegalArgumentException e) {
                logger.error("Ocorreu um erro ao recuperar o usuário pelo token", e);
            } catch (ExpiredJwtException e) {
                logger.warn("Token está expirado e já não é mais válido", e);
            } catch (SignatureException e) {
                logger.error("Falha na autenticação. Usuário ou Senha estão incorretos.");
            }
        } else {
            logger.warn("Não foi possível recuperar a String Bearer, o header será ignorado.");
        }
        if (usuario != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails = userDetailsService.loadUserByUsername(usuario);

            if (jwtTokenUtil.validateToken(authToken, userDetails)) {
                UsernamePasswordAuthenticationToken authentication = jwtTokenUtil.getAuthentication(authToken, SecurityContextHolder.getContext().getAuthentication(), userDetails);
                //UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN")));
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                logger.info("Usuário autenticado " + usuario + ", aplicando o contexto de segurança");
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        filterChain.doFilter(request, response);
    }

}
