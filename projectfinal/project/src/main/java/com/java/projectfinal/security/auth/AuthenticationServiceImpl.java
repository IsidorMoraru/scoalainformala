package com.java.projectfinal.security.auth;

import com.java.projectfinal.model.entity.User;
import com.java.projectfinal.model.entity.UserStatus;
import com.java.projectfinal.repository.UserRepository;
import com.java.projectfinal.security.config.JwtProvider;
import com.java.projectfinal.security.token.Token;
import com.java.projectfinal.security.token.TokenRepository;
import com.java.projectfinal.security.token.TokenType;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final JwtProvider jwtService;
    private final UserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()
                )
        );
        var user = userRepository.findByEmail(request.email())
                .orElseThrow();
        if (UserStatus.ACTIVE.equals(user.getStatus())) {
            var userDetails = userDetailsService.loadUserByUsername(request.email());
            var jwtToken = jwtService.generateToken(List.of(user.getRole().name()), userDetails);
            revokeAllUserTokens(user);
            saveUserToken(user, jwtToken);
            return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .build();
        }
        return AuthenticationResponse.builder()
                .token("")
                .build();
    }



    private void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)

                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepository.findValidTokensByUserId(user.getId());
        if (validUserTokens.isEmpty()) {
            return;
        }
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }
}

