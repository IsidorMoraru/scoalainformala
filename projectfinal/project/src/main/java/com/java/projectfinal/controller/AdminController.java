package com.java.projectfinal.controller;

import com.java.projectfinal.component.ErrorResponse;
import com.java.projectfinal.model.payload.ApiResponse;
import com.java.projectfinal.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/manage")
@Tag(name = "Admin", description = "Endpoints for managing users")
@CrossOrigin
public class AdminController {
    @Autowired
    private UserService userService;

    @Operation(summary = "Activate user",
            description = "Activate the user by changing their status to ACTIVE for the user identified " +
                    "by the given UUID.")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
                    description = "OK: Operation successful"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400",
                    description = "Bad Request: The provided id does not exist or is invalid"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401",
                    description = "Unauthorized: Required authentication failed or was not provided"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404",
                    description = "Not Found: The required id parameter is missing"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "405",
                    description = "Method Not Allowed: Request method not supported or mandatory path parameter missing"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500",
                    description = "Internal Server Error",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    ))
    })
    @Parameter(
            name = "userId",
            description = "The ID of the user to be activated",
            required = true,
            example = "6d714f6b-54a7-4e68-a2d1-6f1c6d206cae",
            schema = @Schema(type = "string", format = "uuid")
    )
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{userId}/activate")
    public ResponseEntity<ApiResponse> activateUser(@PathVariable UUID userId) {
        if (userService.activateUser(userId)) {
            return ResponseEntity.ok(new ApiResponse(true, "User activated"));
        }
        return ResponseEntity.ok(new ApiResponse(false, "User activation failed"));
    }
}
