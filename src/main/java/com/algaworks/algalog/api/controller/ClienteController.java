package com.algaworks.algalog.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algalog.domain.model.Cliente;
import com.algaworks.algalog.domain.repository.ClienteRepository;
import com.algaworks.algalog.domain.service.CatalogoClienteService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/clientes")//mapeando o get a nivel de classe
public class ClienteController {
	@Autowired
	private ClienteRepository clienteRepository;
	private CatalogoClienteService catalogoClienteService; 
	
	@GetMapping
	public List<Cliente>listar() {
		return clienteRepository.findAll();
		
	}
	
	
	//Metodo de busca por id
	
	@GetMapping("/{clienteId}")//caminho para passar na consulta
	
	public ResponseEntity<Cliente> buscar(@PathVariable Long clienteId) { //personalizar o resultado na consulta
		return clienteRepository.findById(clienteId)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}
	
	 //implementação do metodo post
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)//Mudando o retorno 200 para 201 created
	public Cliente adicionar( @Valid @ RequestBody Cliente cliente) {
		//return clienteRepository.save(cliente);
		return catalogoClienteService.salvar(cliente);
		
	}
	
	//implementação do metodo Atualizar @valid para fazer a validacao na entrada do metodo.
	@PutMapping("/{clienteId}")
	public ResponseEntity<Cliente>atualizar( @Valid @PathVariable Long clienteId,
			@RequestBody Cliente cliente){
		if(!clienteRepository.existsById(clienteId)) {// verificando se o cliente não existe
			return ResponseEntity.notFound().build();
		}
		
		cliente.setId(clienteId);
		//cliente = clienteRepository.save(cliente);
		cliente = catalogoClienteService.salvar(cliente);
		return ResponseEntity.ok(cliente);
		 
		
	}
	
	@DeleteMapping("/{clienteId}")
	//metodo para apagar registros pelo id
	public ResponseEntity<Void> remover (@PathVariable Long clienteId){
		if(!clienteRepository.existsById(clienteId)) {// verificando se o cliente não existe
			return ResponseEntity.notFound().build();
		}
		
		//clienteRepository.deleteById(clienteId);
		catalogoClienteService.excluir(clienteId);
		return ResponseEntity.noContent().build();
		
	}
	

}
