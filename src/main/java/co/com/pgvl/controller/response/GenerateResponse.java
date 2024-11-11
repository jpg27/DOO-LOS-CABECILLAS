package co.com.pgvl.controller.response;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import co.com.pgvl.controller.response.concrete.GenericResponse;

public final class GenerateResponse<T> {
	
	public static ResponseEntity<GenericResponse> generateSucessResponse(final List<String> messages){
		var genericResponse = new GenericResponse();
		genericResponse.setMessages(messages);
		
		return new ResponseEntity<>(genericResponse, HttpStatus.OK);
	}
	
	public static ResponseEntity<GenericResponse> generateFailesResponse(final List<String> messages){
		var genericResponse = new GenericResponse();
		genericResponse.setMessages(messages);
		
		return new ResponseEntity<>(genericResponse, HttpStatus.BAD_REQUEST);
	}
	
	public ResponseEntity<T> generateSucessResponseWithData(final T responseWithData){	
		return new ResponseEntity<>(responseWithData, HttpStatus.OK);
	}
	
	public ResponseEntity<ResponseWithData<T>> generateFailedResponseWithData(final ResponseWithData<T> responseWithData){	
		return new ResponseEntity<>(responseWithData, HttpStatus.BAD_REQUEST);
	}

}
