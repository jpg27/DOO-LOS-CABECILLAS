package co.com.pgvl.controller;

import java.util.ArrayList;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.pgvl.businesslogic.facade.cliente.impl.RegisterNewClienteFacadeImpl;
import co.com.pgvl.controller.response.GenerateResponse;
import co.com.pgvl.controller.response.concrete.ClienteResponse;
import co.com.pgvl.controller.response.concrete.GenericResponse;
import co.com.pgvl.crosscutting.exceptions.PGVLApplicationException;
import co.com.pgvl.crosscutting.exceptions.PGVLException;
import co.com.pgvl.dto.ClienteDTO;

@RestController
@RequestMapping("/api/v1/clientes")
public final class ClienteController {
	
	@GetMapping("/dummy")
	public ClienteDTO getDummy() {
		return ClienteDTO.create();
	}
	
	@PostMapping
	public ResponseEntity<GenericResponse> create(@RequestBody ClienteDTO cliente) {
		var messages = new ArrayList<String>();
		
		try {
			var registerNewClienteFacade = new RegisterNewClienteFacadeImpl();
			registerNewClienteFacade.execute(cliente);
			
			messages.add("El cliente se registro de forma satisfactoria");
			
			return GenerateResponse.generateSucessResponse(messages);
		} catch (final PGVLException exception) {
			messages.add(exception.getUserMessage());
			exception.printStackTrace();
			
			return GenerateResponse.generateFailesResponse(messages);
		} catch (final PGVLApplicationException exception) {
			messages.add(exception.getUserMessage());
			exception.printStackTrace();
			
			return GenerateResponse.generateFailesResponse(messages);
		} catch(final Exception exception) {
			messages.add("Se ha presentado un problema tratando de llevar a cabo el registro del cliente de forma satisfactoria");
			exception.printStackTrace();
			
			return GenerateResponse.generateFailesResponse(messages);
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<GenericResponse> update(@PathVariable String id, @RequestBody ClienteDTO cliente) {
		var messages = new ArrayList<String>();
		messages.add("El cliente se actualizo de forma satisfactoria");
		
		return GenerateResponse.generateSucessResponse(messages);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<GenericResponse> delete(@PathVariable String id) {
		var messages = new ArrayList<String>();
		
		messages.add("El cliente se elimino de forma satisfactoria");
		return GenerateResponse.generateSucessResponse(messages);
	}
	
	@GetMapping
	public ResponseEntity<ClienteResponse> retrieveAll() {
		ClienteResponse responseWithData = new ClienteResponse();
		
		var messages = new ArrayList<String>();
		messages.add("Los clientes se consultaron de forma satisfactoria");
		
		var list = new ArrayList<ClienteDTO>();
		list.add(getDummy());
		list.add(getDummy());
		list.add(getDummy());
		list.add(getDummy());
		
		responseWithData.setData(list);
		responseWithData.setMessages(messages);
		
		return (new GenerateResponse<ClienteResponse>()).generateSucessResponseWithData(responseWithData);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ClienteResponse> retrieveById(@PathVariable String id) {
		ClienteResponse responseWithData = new ClienteResponse();
		
		var messages = new ArrayList<String>();
		messages.add("El cliente se consulto de forma satisfactoria");
		
		var list = new ArrayList<ClienteDTO>();
		list.add(getDummy());
		
		responseWithData.setData(list);
		responseWithData.setMessages(messages);
		
		return (new GenerateResponse<ClienteResponse>()).generateSucessResponseWithData(responseWithData);

	}

}
