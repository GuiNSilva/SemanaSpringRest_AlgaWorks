package com.algaworks.algalog.domain.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@Entity
public class Cliente {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/* Anotations do Bean Validation, dependencia io validation adicionada o projeto.
	 * Anotation @NotBlank não permite que o campo esteja vazio ou nulo.
	 * @Size para limitar o tamanho do campo, evita conflito com o tamanho do campo no BD.
	 * @Email valida o formato correto para o campo email.
	 * */
	
	
	
	@NotBlank
	@Size(max = 60)
	private String nome;
	
	@NotBlank @Email @Size(max = 255)
	private String email;
	
	@NotBlank @Size(max = 20)
	private String telefone;
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return Objects.equals(id, other.id);
	}
	
	
	
}
