package com.api.ordemservico.model;

import java.io.Serializable;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "processos")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProcessoModel implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long processoId;
	@Column(nullable = false, unique = true, length = 50)
	private String processo;
	
}
