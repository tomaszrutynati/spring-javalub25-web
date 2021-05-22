package pl.sda.covidvavapp.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.pl.PESEL;
import org.springframework.format.annotation.DateTimeFormat;
import pl.sda.covidvavapp.api.validator.BirthDateAndPesel;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@BirthDateAndPesel
public class NewPatient {
    @NotBlank(message = "First name should be present")
    private String firstName;
    @NotBlank(message = "Last name should be present")
    private String lastName;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;
    @PESEL(message = "PESEL should have proper value")
    private String pesel;

    @AssertTrue(message = "Birth date should be in past")
    public boolean isBirthDateInPast() {
        if (birthDate == null) {
            return false;
        }
        return birthDate.isBefore(LocalDate.now());
    }
}
