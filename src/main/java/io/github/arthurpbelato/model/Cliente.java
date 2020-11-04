package io.github.arthurpbelato.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.time.LocalDate;


@Entity
@Table(name = "cliente")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger codCliente;

    @NotNull
    private String nome;

    @NotNull
    private String cpf;
    private String telefone;
    private String email;
    private LocalDate dataNascimento;


}
