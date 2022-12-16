package com.api.ordemservico.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "valores")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ValorModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long valorId;
    @Column(name = "valor_corte")
    private float valorcorte;
    @Column(name = "valor_costura")
    private float valorcostura;
    @Column(name = "valor_lavagem")
    private float valorlavagem;

}
