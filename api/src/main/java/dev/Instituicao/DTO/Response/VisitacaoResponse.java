package dev.Instituicao.DTO.Response;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class VisitacaoResponse {
    private String DataVisita;
    private short Nota;    
    private String idTurista;
}