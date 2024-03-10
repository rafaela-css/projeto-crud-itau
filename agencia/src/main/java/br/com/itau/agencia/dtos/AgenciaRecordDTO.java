package br.com.itau.agencia.dtos;

import java.math.BigDecimal;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record AgenciaRecordDTO(
    Integer codigoAgencia,
    Integer cep,
    BigDecimal faturamentoMedio) {

}
