package com.api.ordemservico.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ordens_servicos")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrdemServicoModel implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idOrdem;
	@Column(name = "data_emissao")
	private Date dataEmissao;
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "processo_id", referencedColumnName = "processoId", nullable = true)
	private ProcessoModel processoModel;
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "prestador_id", referencedColumnName = "prestadorId", nullable = true)
	private PrestadorModel prestadorModel;
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "produto_id", referencedColumnName = "produtoId", nullable = true)
	private ProdutoModel produtoModel;
	@Column(name = "quantidade_pecas")
	private float quantidade;
	@Column(name = "valor_total")
	private float valortotal;
	@Column(name = "situacao_os")
	private String situacao;
	
}