package com.example.logincadastro.formulario.models.cadastro;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "table_user")
public class CadastroModel {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "id")
  private UUID id;

  @Column(name = "name")
  private String name;

  @Column(name = "email")
  private String email;

  @Column(name = "senha")
  private String senha;


  public CadastroModel() {
  }

  public CadastroModel(String name, String email, String senha) {
    this.name = name;
    this.email = email;
    this.senha = senha;
  }

  public UUID getId() {
    return this.id;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return this.email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getSenha() {
    return this.senha;
  }

  public void setSenha(String senha) {
    this.senha = senha;
  }
}
