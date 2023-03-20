package com.example.Challenge2.security.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
//every request should be authenticated
    private final JwtService jwtService;

    private final UserDetailsService userDetailsService;
//JWT validation process
    @Override
    protected void doFilterInternal
            (@NonNull HttpServletRequest request,
             @NonNull HttpServletResponse response,
             @NonNull FilterChain filterChain)
            throws ServletException, IOException {
            final String authHeader = request.getHeader("Authorization");//get the "Authorization" header
            final String jwt;
            final String userEmail;
            if(authHeader == null || !authHeader.startsWith("Bearer ")){
                filterChain.doFilter(request, response);
                return;
            }
            jwt = authHeader.substring(7);//get the token from the request header
            userEmail = jwtService.extractUserEmail(jwt);

        //if user is valid we need to update our security context and send a request to our dispacher servlet

        if(userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null){
                //get userDetails from database using a simple function in the AppConfig class using the repository
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
                if(jwtService.isTokenValid(jwt, userDetails)){
                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken(
                                    userDetails,
                                    null,
                                    userDetails.getAuthorities()
                            );
                    //we set the new token's details to a WebAuthenticationDetailsSource() object and it contains information
                    // about the user's authentication request such as the IP address, the user agent, etc that he gets from the request
                    authenticationToken.setDetails(
                            new WebAuthenticationDetailsSource().buildDetails(request)
                    );
                    //SecurityContextHolder is a class provided by Spring Security that we have to update with the new token
                    //and send the authentication request to the dispacher servlet
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);

                }
            }
        //let the next filter get executed
        filterChain.doFilter(request, response);
    }
}
