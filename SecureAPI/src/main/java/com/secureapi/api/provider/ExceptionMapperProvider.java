package com.secureapi.api.provider;

import java.util.Objects;

import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.secureapi.api.dto.ExceptionDTO;
import com.secureapi.business.exception.BusinessCheckedException;
import com.secureapi.business.exception.BusinessException;

@Provider
public class ExceptionMapperProvider implements ExceptionMapper<Throwable> {

	@Override
	public Response toResponse(Throwable exception) {
		Status statusResponse = Status.INTERNAL_SERVER_ERROR;
		
		exception.printStackTrace();
		ExceptionDTO exceptionDTO = new ExceptionDTO();
		exceptionDTO.setMessage(getMessage(exception));
		
		if (exception instanceof NotAuthorizedException) {
			statusResponse = Status.UNAUTHORIZED;
		} else if (exception instanceof BusinessException || exception instanceof BusinessCheckedException) {
			statusResponse = Status.BAD_REQUEST;
		}
		
		Response response = buildResponse(statusResponse, exceptionDTO);
		
		return response;
	}

	private Response buildResponse(Status statusResponse, Object entity) {
		return Response.status(statusResponse).entity(entity).type(MediaType.APPLICATION_JSON).build();
	}
	
	private String getMessage(Throwable exception) {
		String message = exception.getMessage();
		Throwable cause = exception.getCause();
		if (Objects.isNull(message) && Objects.nonNull(cause)) {
			message = cause.getMessage();
		}
		if (Objects.isNull(message)) {
			message = exception.toString();
		}
		return message;
	}

}
