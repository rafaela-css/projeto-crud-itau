package br.com.itau.agencia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.itau.agencia.models.AgenciaModel;

import java.util.UUID;

@Repository
public interface AgenciaRepository extends JpaRepository<AgenciaModel, UUID>{

}
