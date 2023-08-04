package com.java.projectfinal.security.auth;

public interface AuthenticationService {
    AuthenticationResponse authenticate(AuthenticationRequest request);
}
