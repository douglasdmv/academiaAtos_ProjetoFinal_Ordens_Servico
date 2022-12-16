package com.api.ordemservico.model;

import java.io.Serializable;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "produtos")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoModel implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long produtoId;
	@Column(nullable = false, length = 70)
	private String descricao;
	@Column(nullable = false, length = 30)
	private String cor;
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "tamanho_id", referencedColumnName = "tamanhoId", nullable = true)
	private TamanhoModel tamanhoModel;
	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "valor_id", referencedColumnName = "valorId", nullable = true)
	private ValorModel valorModel;

}
