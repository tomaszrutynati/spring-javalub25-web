package pl.sda.covidvavapp.repository;

import lombok.*;
import pl.sda.covidvavapp.api.model.Facility;

import javax.persistence.*;

@Entity
@Table(name = "facilities")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class FacilityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String street;
    private String houseNumber;
    private String zipCode;
    private String city;
    private String phoneNumber;

    public void updateFacility(Facility facility) {
        this.name = facility.getName();
        this.street = facility.getStreet();
        this.houseNumber = facility.getHouseNumber();
        this.zipCode = facility.getZipCode();
        this.city = facility.getCity();
        this.phoneNumber = facility.getPhoneNumber();
    }
}
