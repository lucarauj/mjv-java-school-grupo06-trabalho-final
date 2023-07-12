package com.jobby.g6.api.dto;

import com.jobby.g6.domain.model.*;
import com.jobby.g6.domain.model.enums.SexoEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDateTime;
import java.util.List;

public record CadastroDTO(
        @NotBlank(message = "{nome.obrigatorio}")
        String nome,
        @NotBlank(message = "{cpf.obrigatorio}")
        @CPF(message = "{cpf.invalido}")
        String cpf,
        @NotNull LocalDateTime dataNascimento,
        @NotNull Long telefone,
        @NotBlank(message = "{email.obrigatorio}")
        @Email(message = "{email.invalido}")
        String email,
        @NotBlank List<String> habilidades,
        @NotBlank Profissao profissao,
        @NotNull PretensaoSalarial pretensaoSalarial,
        @NotNull SexoEnum sexoEnum,
        @NotNull(message = "{celular.obrigatorio}") Celular celular,
        @NotNull(message = "{endereco.obrigatorio}") Endereco endereco,
        @NotNull CadastroExperiencia cadastroExperiencia
) {
}