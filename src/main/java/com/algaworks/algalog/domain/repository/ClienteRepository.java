package com.algaworks.algalog.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.algaworks.algalog.domain.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	
	List<Cliente> findByNome(String nome); // metodo que interface fornece para consulta pelo nome.
	List<Cliente> findByNomeContaining(String nome);//mesmo metodo, mas utiliza o contain para pesquisar a string.
	Optional<Cliente> findByEmail(String email);
}
