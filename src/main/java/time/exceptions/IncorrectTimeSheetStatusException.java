package time.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(value=HttpStatus.NOT_ACCEPTABLE, reason="Time Sheet has the wrong status for this action")
public class IncorrectTimeSheetStatusException extends Exception {

}
