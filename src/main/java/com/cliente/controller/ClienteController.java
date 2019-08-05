package com.cliente.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cliente.exception.ClienteNotFoundException;
import com.cliente.model.Cliente;
import com.cliente.repository.ClienteRepository;
import com.cliente.util.Util;

@RestController
@RequestMapping("/api/v1.0")
public class ClienteController {

	@Autowired
	ClienteRepository clienteRepository;

	@Autowired
	private HttpServletRequest request;

	@GetMapping("/clientes")
	public List<Cliente> getAllClientes() {
		return clienteRepository.findAll();
	}

	@GetMapping("/clientes/{id}")
	public Cliente getClienteById(@PathVariable(value = "id") Long id) {
		return clienteRepository.findById(id).orElseThrow(() -> new ClienteNotFoundException("Cliente", "id", id));
	}

	@PostMapping("/clientes")
	public Cliente createCliente(@Valid @RequestBody Cliente cliente) {

		cliente.setIp(request.getRemoteAddr());
		new Util().GetGeolocationInfo(cliente);
		
		return clienteRepository.save(cliente);
	}

	@PutMapping("/clientes/{id}")
	public Cliente updateCliente(@PathVariable(value = "id") Long id, @Valid @RequestBody Cliente clienteDetails) {

		Cliente cliente = clienteRepository.findById(id)
				.orElseThrow(() -> new ClienteNotFoundException("Cliente", "id", id));

		clienteDetails.setIp(request.getRemoteAddr());
		new Util().GetGeolocationInfo(clienteDetails);
		
		cliente.setFirstName(clienteDetails.getFirstName());
		cliente.setLastName(clienteDetails.getLastName());
		cliente.setAddress(clienteDetails.getAddress());
		cliente.setRequestLocation(clienteDetails.getRequestLocation());
		cliente.setMaxTemp(clienteDetails.getMaxTemp());
		cliente.setMinTemp(clienteDetails.getMinTemp());

		Cliente updatedCliente = clienteRepository.save(cliente);
		return updatedCliente;
	}

	@DeleteMapping("/clientes/{id}")
	public ResponseEntity<Cliente> deleteCliente(@PathVariable(value = "id") Long id) {

		Cliente cliente = clienteRepository.findById(id)
				.orElseThrow(() -> new ClienteNotFoundException("Cliente", "id", id));

		clienteRepository.delete(cliente);
		return ResponseEntity.ok().build();
	}
}
