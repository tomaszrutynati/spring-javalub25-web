package pl.sda.covidvavapp.api.model;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Facility {
    private Long id;
    @NotEmpty
    @Length(max = 30)
    private String name;
    @NotEmpty
    private String street;
    @NotEmpty
    @Length(max = 10)
    private String houseNumber;
    @Length(max = 6, min = 5)
    private String zipCode;
    @NotEmpty
    private String city;
    private String phoneNumber;

    @AssertTrue
    public boolean isPhoneNumberCorrect() {
        return phoneNumber.startsWith("+48");
    }
}
