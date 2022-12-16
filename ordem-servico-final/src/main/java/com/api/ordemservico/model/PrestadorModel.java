package com.api.ordemservico.model;

import java.io.Serializable;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "prestadores")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PrestadorModel implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long prestadorId;
	@Column(nullable = false, unique = true, length = 15)
	private String cpf;
	@Column(nullable = false, length = 70)
	private String nome;
	@Column(nullable = false, length = 70)
	private String endereco;
	@Column(nullable = false, length = 40)
	private String cidade;
	@Column(nullable = false, length = 40)
	private String servico;
	
}
