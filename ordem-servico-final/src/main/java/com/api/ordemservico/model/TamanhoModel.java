package com.api.ordemservico.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tamanhos")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TamanhoModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tamanhoId;
    @Column(nullable = true, length = 30)
    private String tamanho;

}
