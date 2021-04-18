package pl.sda.covidvavapp.repository;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class FacilityEntity {
    @Setter
    private Long id;
    private String name;
    private String street;
    private String houseNumber;
    private String zipCode;
    private String city;
    private String phoneNumber;
}
