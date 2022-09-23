package com.algaworks.algalog.api.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algalog.domain.model.Cliente;

@RestController
public class ClienteController {
	
	@GetMapping("/clientes")
	public List<Cliente>listar() {
		Cliente cliente1 = new Cliente();
		cliente1.setId(1L);
		cliente1.setNome("Gerardo Matusquela");
		cliente1.setTelefone("31 99876 6543");
		cliente1.setEmail("geramastuquela@gmail.com");
		
		Cliente cliente2 = new Cliente();
		cliente2.setId(2L);
		cliente2.setNome("Tony Lambreta");
		cliente2.setTelefone("31 99876 3456");
		cliente2.setEmail("tonylambida@gmail.com");
		
		return Arrays.asList(cliente1,cliente2);
	}

}
