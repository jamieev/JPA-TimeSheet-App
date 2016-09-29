package time.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(value=HttpStatus.NOT_ACCEPTABLE, reason="This Time Sheet is incomplete")
public class TimeSheetIncompleteException extends Exception {

}
