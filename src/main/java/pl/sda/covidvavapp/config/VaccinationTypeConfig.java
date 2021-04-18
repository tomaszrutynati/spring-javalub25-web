package pl.sda.covidvavapp.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.LocalDate;
import java.util.Map;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ConfigurationProperties(prefix = "vaccination")
public class VaccinationTypeConfig {
    private Map<String, DateRange> types;

    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DateRange {
        private String start;
        private String end;

        public LocalDate getStart() {
            return LocalDate.parse(start);
        }

        public LocalDate getEnd() {
            return LocalDate.parse(end);
        }
    }
}
