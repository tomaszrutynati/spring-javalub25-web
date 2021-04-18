package pl.sda.covidvavapp.exception;

public class PatientAlreadyVaccinatedException extends RuntimeException {

    public PatientAlreadyVaccinatedException(String message) {
        super(message);
    }
}
