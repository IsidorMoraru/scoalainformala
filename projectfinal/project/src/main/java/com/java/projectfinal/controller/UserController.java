package com.java.projectfinal.controller;

import com.java.projectfinal.model.dto.UserDetailsDto;
import com.java.projectfinal.model.payload.ApiResponse;
import com.java.projectfinal.model.payload.UserUpdateRequest;
import com.java.projectfinal.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/users")

public class UserController {
    @Autowired
    private UserService userService;
    @Operation(summary = "Delete user by id", description = "Delete the user identified by the given UUID. " +
            "By using the soft delete mechanism, the user is not deleted permanently, instead its " +
            "status is marked as DELETED.")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
                    description = "OK: Operation successful",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UserDetailsDto.class)
                    )),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400",
                    description = "Bad Request: The provided id does not exist or is invalid"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401",
                    description = "Unauthorized: Mandatory authentication failed or was not provided"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404",
                    description = "Not Found: The required id parameter is missing"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "405",
                    description = "Method Not Allowed: Request method not supported or mandatory path parameter missing"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500",
                    description = "Internal Server Error")
    })
    @Parameter(
            name = "id",
            description = "The ID of the user to retrieve details for",
            required = true,
            example = "6d714f6b-54a7-4e68-a2d1-6f1c6d206cae",
            schema = @Schema(type = "string", format = "uuid")
    )
    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable UUID userId) {
        if (userId == null) {
            return ResponseEntity.ok(new ApiResponse(false, "User id is null"));
        }
        if (userService.deleteUser(userId)) {
            return ResponseEntity.ok(new ApiResponse(true, "User deleted successfully"));
        } else {
            return ResponseEntity.ok(new ApiResponse(false, "Deletion failed, no such user"));
        }
    }
    @Operation(summary = "Update user by id",
            description = "Update information relevant to the user's profile.")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
                    description = "OK: Operation successful"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400",
                    description = "Bad Request: The provided id does not exist or is invalid"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401",
                    description = "Unauthorized: Mandatory authentication failed or was not provided"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404",
                    description = "Not Found: The required id parameter is missing"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "405",
                    description = "Method Not Allowed: The mandatory path parameter is missing"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500",
                    description = "Internal Server Error")
    })
    @Parameter(
            name = "id",
            description = "The ID of the user to retrieve details for",
            required = true,
            example = "6d714f6b-54a7-4e68-a2d1-6f1c6d206cae",
            schema = @Schema(type = "string", format = "uuid")
    )
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateUser(@PathVariable("id") UUID id, @Valid @RequestBody UserUpdateRequest user,
                                                  BindingResult br) {
        if (br.hasErrors()) {
            return ResponseEntity.ok(new ApiResponse(false,
                    br.getAllErrors().get(0).getDefaultMessage()));
        }
        if (user == null || id == null) {
            return ResponseEntity.ok(new ApiResponse(false, "The user or the user id cannot be null"));
        }
        return userService.updateUser(user, id) ?
                ResponseEntity.ok(new ApiResponse(true, "Update successful")) :
                ResponseEntity.ok(new ApiResponse(false, "Update failed"));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDetailsDto> getUserDetails(@PathVariable UUID userId) {
        Optional<UserDetailsDto> userDetailsDto = userService.getUserDetails(userId);
        if (userDetailsDto.isPresent()) {
            return ResponseEntity.ok(userDetailsDto.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

