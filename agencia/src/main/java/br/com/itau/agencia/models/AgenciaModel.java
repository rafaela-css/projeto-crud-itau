package br.com.itau.agencia.models;

import java.math.BigDecimal;
import java.util.UUID;

import jakarta.persistence.*;

import java.io.Serializable;

import lombok.*;

@Data
@Entity
@Table(name = "TB_AGENCIA")
public class AgenciaModel implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idAgencia;
    private Integer codigoAgencia;
    private Integer cep;
    private BigDecimal faturamentoMedio;

}
