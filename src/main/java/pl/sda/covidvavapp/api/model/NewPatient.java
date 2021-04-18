package pl.sda.covidvavapp.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.pl.PESEL;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewPatient {
    @NotNull(message = "First name should not be null")
    private String firstName;
    @NotNull(message = "Last name should not be null")
    private String lastName;
    private LocalDate birthDate;
    @PESEL(message = "PESEL should have proper value")
    private String pesel;

    @AssertTrue(message = "Birth date should be in past")
    public boolean isBirthDateInPast() {
        return birthDate.isBefore(LocalDate.now());
    }
}
