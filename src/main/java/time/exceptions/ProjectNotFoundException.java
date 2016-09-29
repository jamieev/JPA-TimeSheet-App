package time.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


	@SuppressWarnings("serial")
	@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="This project is not found in the system")
	public class ProjectNotFoundException extends Exception {
}
