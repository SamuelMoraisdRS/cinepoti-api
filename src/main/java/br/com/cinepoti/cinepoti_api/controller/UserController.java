package br.com.cinepoti.cinepoti_api.controller;

import br.com.cinepoti.cinepoti_api.model.user.UserRequestDTO;
import br.com.cinepoti.cinepoti_api.model.user.UserResponseDTO;
import br.com.cinepoti.cinepoti_api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")  // Define o caminho base para os endpoints relacionados ao usuário
public class UserController {


    private final UserService userService;

    @Autowired
    public  UserController(UserService userService){
        this.userService = userService;
    }

    // Endpoint para criar um novo usuário
    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserRequestDTO userRequestDTO) {
        // Chama o serviço para salvar o usuário
        UserResponseDTO userResponseDTO = userService.saveUser(userRequestDTO);

        // Retorna a resposta com o status HTTP 201 (Created)
        return new ResponseEntity<>(userResponseDTO, HttpStatus.CREATED);
    }

    // Endpoint para listar todos os usuários
    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        List<UserResponseDTO> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    // Endpoint para buscar um usuário por ID
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable("id") UUID id) {
        UserResponseDTO userResponseDTO = userService.getUserById(id);
        if (userResponseDTO != null) {
            return new ResponseEntity<>(userResponseDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Endpoint para atualizar um usuário
    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(
            @PathVariable("id") UUID id,
            @RequestBody UserRequestDTO userRequestDTO) {

        UserResponseDTO userResponseDTO = userService.updateUser(id, userRequestDTO);
        if (userResponseDTO != null) {
            return new ResponseEntity<>(userResponseDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
