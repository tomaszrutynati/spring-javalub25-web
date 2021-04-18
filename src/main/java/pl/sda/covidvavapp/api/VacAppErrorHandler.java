package pl.sda.covidvavapp.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.sda.covidvavapp.api.model.Errors;
import pl.sda.covidvavapp.exception.PatientAlreadyVaccinatedException;

import java.util.Collections;

@RestControllerAdvice
public class VacAppErrorHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(VacAppErrorHandler.class);

    @ExceptionHandler(IllegalStateException.class)
    public Errors handleIllegalState(IllegalStateException ex) {
        LOGGER.error("Error", ex);
        return Errors.builder().errors(Collections.singletonList(ex.getMessage())).build();
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(PatientAlreadyVaccinatedException.class)
    public Errors handleVacAlreadyExists(PatientAlreadyVaccinatedException ex) {
        LOGGER.error("Error", ex);
        return Errors.builder().errors(Collections.singletonList(ex.getMessage())).build();
    }

}
