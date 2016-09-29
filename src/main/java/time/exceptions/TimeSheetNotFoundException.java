package time.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="This Time Sheet is not found in the system")
public class TimeSheetNotFoundException extends Exception {

}
