package bg.softuni.ut.web.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
//@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such Id.")
public class EntityNotExistException extends RuntimeException {

	private Long id;
	private String attractionName;

	public EntityNotExistException(String message, Long id) {
		super(message);
		this.id = id;
	}

	public EntityNotExistException(String message, String attractionName) {
		super(message);
		this.attractionName = attractionName;
	}

	public Long getId() {
		return id;
	}

	public String getAttractionName() {
		return attractionName;
	}

}
