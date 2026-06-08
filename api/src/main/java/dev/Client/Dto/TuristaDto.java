package dev.Client.Dto;

import dev.Client.Entity.TuristaEntity;
import dev.Client.Entity.VisitacoesEntity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

public class TuristaDto {

    public static class Request {
        @NotBlank(message = "Nome é obrigatório")
        private String nome;

        @NotBlank(message = "CPF é obrigatório")
        @CPF(message = "CPF inválido")
        private String cpf;

        @NotBlank(message = "E-mail é obrigatório")
        @Email(message = "E-mail inválido")
        private String email;

        @NotBlank(message = "Senha é obrigatória")
        @Size(min = 8, message = "Senha deve ter no mínimo 8 caracteres")
        private String senha;

        private String telefone;
        private String passaporte;
        private LocalDate dataNascimento;

        public Request() {}

        public String getNome()                        { return nome; }
        public void setNome(String nome)               { this.nome = nome; }
        public String getCpf()                         { return cpf; }
        public void setCpf(String cpf)                 { this.cpf = cpf; }
        public String getEmail()                       { return email; }
        public void setEmail(String email)             { this.email = email; }
        public String getSenha()                       { return senha; }
        public void setSenha(String senha)             { this.senha = senha; }
        public String getTelefone()                    { return telefone; }
        public void setTelefone(String telefone)       { this.telefone = telefone; }
        public String getPassaporte()                  { return passaporte; }
        public void setPassaporte(String passaporte)   { this.passaporte = passaporte; }
        public LocalDate getDataNascimento()           { return dataNascimento; }
        public void setDataNascimento(LocalDate d)     { this.dataNascimento = d; }
    }

    public static class Response {
        private Long id;
        private String nome;
        private String cpf;
        private String email;
        private String telefone;

        private LocalDate dataNascimento;

        public Response() {}

        public Response(TuristaEntity turista) {
            this.id             = turista.getId(); 
            this.nome           = turista.getNome();
            this.cpf            = turista.getCpf();
            this.email          = turista.getEmail();
            this.telefone       = turista.getTelefone();
            this.dataNascimento = turista.getDataNascimento();
        }

        public Long getId()                  { return id; }
        public String getNome()              { return nome; }
        public String getCpf()              { return cpf; }
        public String getEmail()             { return email; }
        public String getTelefone()          { return telefone; }
        public LocalDate getDataNascimento() { return dataNascimento; }
    }

    public static class HistoricoResponse {
        private Long visitacaoId;
        private String localVisitado;
        private LocalDate dataVisita;

        public HistoricoResponse() {}

        public HistoricoResponse(VisitacoesEntityByTurista v) {
            this.visitacaoId = v.getId();
            this.localVisitado = v.getLocalVisitado();
            this.dataVisita    = v.getDataVisita().toLocalDate();
        }

        public Long getVisitacaoId()          { return visitacaoId; }
        public String getLocalVisitado()      { return localVisitado; }
        public LocalDate getDataVisita()      { return dataVisita; }
    }
}