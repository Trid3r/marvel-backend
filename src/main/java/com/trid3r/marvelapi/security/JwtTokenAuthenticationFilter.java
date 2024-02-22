package com.trid3r.marvelapi.security;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.crypto.SecretKey;
import java.io.IOException;
import java.util.Base64;

@Slf4j
@Service
public class JwtTokenAuthenticationFilter extends OncePerRequestFilter {

    private static final String HEADER = "Authorization";
    private static final String PREFIX = "Bearer ";
    private static final String SECRET = "watchmen_is_better_than_marvel_and_dc";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String token = extractToken(request.getHeader(HEADER));
            if (token != null) {
                if(isTokenValid(token)){
                    String username = decodeJWTToken(token);

                    UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(username,null, null );
                    SecurityContextHolder.getContext().setAuthentication(auth);
                } else {
                    SecurityContextHolder.clearContext();
                }
            } else {
                SecurityContextHolder.clearContext();
            }
            filterChain.doFilter(request, response);
        }  catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException e) {
            log.error(e.getMessage());
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Error con el JWT");
        }
    }

    private String extractToken(String header) {
        if (header != null && header.startsWith(PREFIX)) {
            return header.substring(PREFIX.length());
        }
        return null;
    }

    private boolean isTokenValid(String token){
        SecretKey key = Keys.hmacShaKeyFor(SECRET.getBytes());

        JwtParser jwtParser = Jwts.parser()
                .verifyWith(key)
                .build();
        try{
            jwtParser.parse(token);
            return true;
        } catch (Exception e){
            return false;
        }

    }

    public static String decodeJWTToken(String token) {
        Base64.Decoder decoder = Base64.getUrlDecoder();

        String[] chunks = token.split("\\.");
        String payloadJson = new String(decoder.decode(chunks[1]));
        JsonObject payloadObj = JsonParser.parseString(payloadJson).getAsJsonObject();

        return payloadObj.get("name").getAsString();
    }
}
