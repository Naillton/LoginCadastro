package com.example.logincadastro.formulario.services;

import com.example.logincadastro.formulario.models.cadastro.CadastroModel;
import com.example.logincadastro.formulario.repository.CadastroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

  @Autowired
  private CadastroRepository cadRepository;

  public void insertUser(CadastroModel user) {
    this.cadRepository.save(user);
  }

  public List<CadastroModel> getUsers() {
    return this.cadRepository.findAll();
  }

  public CadastroModel getUserById(UUID id) {
    return this.cadRepository.findById(id).get();
  }

  public void updateUser(CadastroModel user) {
    this.cadRepository.save(user);
  }

  public void delUserById(UUID id) {
    CadastroModel user = this.cadRepository.findById(id).get();
    this.cadRepository.delete(user);
  }
}
