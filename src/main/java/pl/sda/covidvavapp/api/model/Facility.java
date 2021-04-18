package pl.sda.covidvavapp.api.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Facility {
    private Long id;
    private String name;
    private String street;
    private String houseNumber;
    private String zipCode;
    private String city;
    private String phoneNumber;
}
