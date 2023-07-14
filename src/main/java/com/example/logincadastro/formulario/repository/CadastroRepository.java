package com.example.logincadastro.formulario.repository;

import com.example.logincadastro.formulario.models.cadastro.CadastroModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface CadastroRepository extends JpaRepository<CadastroModel, UUID> {
}
