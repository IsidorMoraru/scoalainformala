package com.java.projectfinal.service;


import com.java.projectfinal.model.mapper.TokenMapper;
import com.java.projectfinal.security.token.Token;
import com.java.projectfinal.security.token.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private TokenMapper tokenMapper;

    @Override
    public void revokeToken(final String token) {
        Optional<Token> optionalToken = tokenRepository.findByTokenAndNotExpiredAndNotRevoked(token);
        optionalToken.ifPresent(t -> {
            t.setExpired(true);
            t.setRevoked(true);
            tokenRepository.save(t);
        });
    }
}
