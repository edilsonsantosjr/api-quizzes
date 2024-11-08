package com.quizzes.quizzes.controllers;

import com.quizzes.quizzes.dtos.UserRequestDTO;
import com.quizzes.quizzes.dtos.UserResponseDTO;
import com.quizzes.quizzes.exceptions.UserException;
import com.quizzes.quizzes.models.User;
import com.quizzes.quizzes.services.UserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @Operation(summary = "Obtém informações do usuário autenticado", description = "Recupera as informações do usuário autenticado no sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Informações do usuário recuperadas com sucesso.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class)))
    })
    @GetMapping
    public ResponseEntity<Object> getUser(Authentication authentication) {
        User authenticatedUser = (User) authentication.getPrincipal();
        UserResponseDTO userModelObj;
        try {
            userModelObj = userService.getOneUser(authenticatedUser.getId());
        } catch (UserException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.OK).body(userModelObj);

    }

    @Operation(summary = "Atualiza as informações do usuário autenticado", description = "Atualiza os dados de um usuário com base no email autenticado, como nome e outros atributos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Informações do usuário atualizadas com sucesso.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "400", description = "Dados de entrada inválidos.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class)))
    })
    @PutMapping
    public ResponseEntity<Object> updateUser(Authentication authentication, @RequestBody UserRequestDTO userRequestDTO){
        User authenticatedUser = (User) authentication.getPrincipal();
        String email = authenticatedUser.getEmail();
        UserResponseDTO userResponseDTO;
        try {
            userResponseDTO = userService.updateUser(email, userRequestDTO);
        } catch (UserException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.OK).body(userResponseDTO);
    }

    @Operation(summary = "Deleta o usuário autenticado", description = "Remove o usuário autenticado do sistema com base no seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário deletado com sucesso.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class)))
    })
    @DeleteMapping
    public ResponseEntity<Object> deleteOneUser(Authentication authentication){
        User authenticatedUser = (User) authentication.getPrincipal();
        String id = authenticatedUser.getId();
        User existinUser;
        try {
            userService.deleteUser(id);
        } catch (UserException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.OK).body("User successfully deleted");
    }

}
