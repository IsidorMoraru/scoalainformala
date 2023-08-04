package com.java.projectfinal.controller;

import com.java.projectfinal.component.ErrorResponse;
import com.java.projectfinal.model.payload.UserRegistrationRequest;
import com.java.projectfinal.security.auth.AuthenticationRequest;
import com.java.projectfinal.security.auth.AuthenticationResponse;
import com.java.projectfinal.security.auth.AuthenticationService;
import com.java.projectfinal.service.TokenService;
import com.java.projectfinal.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

import static com.java.projectfinal.security.config.JwtAuthenticationFilter.extractToken;

@Controller
@RequestMapping("/auth")
@Tag(name = "Auth", description = "Endpoints related to authentication and authorization")
public class AuthController {

    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationService service;
    @Autowired
    private TokenService tokenService;

    @Operation(summary = "Register user", description = "Register a new user")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
                    description = "Operation successful"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400",
                    description = "Bad Request: The provided input payload is invalid"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500",
                    description = "Internal Server Error")
    })

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new UserRegistrationRequest());
        return "user-register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("user") UserRegistrationRequest user,
                               BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("errorMessage", result.getAllErrors().get(0).getDefaultMessage());
            return "user-register";
        }

        boolean registrationSuccess = userService.createUser(user);
        model.addAttribute("registrationSuccess", registrationSuccess);
        return "registration-result";
    }


    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("user", new AuthenticationRequest("", ""));
        return "user-login";
    }

    @PostMapping("/login")
    public String authenticate(@ModelAttribute("user") AuthenticationRequest authenticationRequest, Model model) {
        AuthenticationResponse response = service.authenticate(authenticationRequest);

        if (response.getToken() != null && !response.getToken().isEmpty()) {

            model.addAttribute("token", response.getToken());
            return "greetings";
        } else {

            model.addAttribute("error", "Invalid credentials");
            return "user-login";
        }
    }

    @Operation(summary = "Logout user",
            description = "Logout an authenticated user by revoking their authentication token.")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
                    description = "OK: Logout successful"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401",
                    description = "Unauthorized: Required authentication failed or was not provided"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404",
                    description = "Not Found: The requested resource could not be found"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "405",
                    description = "Method Not Allowed: Request method not supported"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500",
                    description = "Internal Server Error",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    ))
    })
    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request, HttpServletResponse response) {
        Optional<String> tokenString = extractToken(request);
        return tokenString.map(token -> {
                    tokenService.revokeToken(token);
                    return ResponseEntity.ok("Logout successful");
                })
                .orElseGet(() -> ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }

}

