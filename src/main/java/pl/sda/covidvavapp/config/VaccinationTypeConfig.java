package pl.sda.covidvavapp.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Map;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class VaccinationTypeConfig {
    private Map<String, DateRange> types;

    @Setter
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DateRange {
        private LocalDate start;
        private LocalDate end;
    }
}
