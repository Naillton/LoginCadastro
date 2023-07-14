package com.example.logincadastro.formulario.controllers.cadastro;

import com.example.logincadastro.formulario.models.cadastro.CadastroModel;
import com.example.logincadastro.formulario.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

  @Autowired
  private UserService userService;

  @PostMapping( value = "/cadastro",
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
  public Object insertUser(@RequestBody CadastroModel user) {
    Object isValid = validationCamps(user.getName(), user.getEmail(), user.getSenha());
    try {
      if (isValid == null) {
        userService.insertUser(user);
        return ResponseEntity.status(HttpStatus.OK).body(user);
      } else {
        return isValid;
      }
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  @GetMapping("/userlist")
  public ResponseEntity<List<CadastroModel>> getUsers() {
    try {
      List<CadastroModel> users = userService.getUsers();
      return ResponseEntity.status(HttpStatus.OK).body(users);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  @GetMapping("/userId/{id}")
  public ResponseEntity<CadastroModel> getuserId(@PathVariable UUID id) {
    try {
      CadastroModel user = userService.getUserById(id);
      return ResponseEntity.status(HttpStatus.OK).body(user);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  @PutMapping("/updateUser/{id}")
  public  ResponseEntity<CadastroModel> updateUser(@PathVariable UUID id,@RequestBody CadastroModel user) {
    int userExist = userService.getUserById(id).getName().length();
    try {
      if (userExist == 0) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
      }
      CadastroModel updated = userService.getUserById(id);
      updated.setName(user.getName());
      updated.setEmail(user.getEmail());
      updated.setSenha(user.getSenha());
      userService.updateUser(updated);
      return ResponseEntity.status(HttpStatus.OK).body(updated);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  @DeleteMapping(value = "/userDel/{id}")
  public ResponseEntity<String> delUser(@PathVariable UUID id) {
    try {
      userService.delUserById(id);
      return ResponseEntity.status(HttpStatus.OK).build();
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  @PostMapping(value = "/login",
          consumes = MediaType.APPLICATION_JSON_VALUE,
          produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<CadastroModel> login(@RequestBody CadastroModel user) {
    List<CadastroModel> users = userService.getUsers();
    CadastroModel userFind = users.stream().filter(userF ->
                    userF.getEmail().equals(user.getEmail()) && userF.getSenha().equals(user.getSenha()))
            .findFirst().orElse(null);
    try {
      if (userFind == null) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
      }
      return ResponseEntity.status(HttpStatus.OK).body(userFind);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  private Object validationCamps(String name, String email, String senha) {
    if (email.isBlank() || name.isBlank() || senha.isBlank()) {
      return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("campos/invalidos");
    }

    if (senha.length() < 8) {
      return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("senha/invalida");
    }
    return null;
  }

}
