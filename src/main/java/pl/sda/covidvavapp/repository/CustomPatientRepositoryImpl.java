package pl.sda.covidvavapp.repository;

import org.springframework.stereotype.Repository;
import pl.sda.covidvavapp.api.model.PatientSearchParams;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomPatientRepositoryImpl implements CustomPatientRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<PatientEntity> findWithSearchParams(PatientSearchParams searchParams) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<PatientEntity> query = cb.createQuery(PatientEntity.class);
        Root<PatientEntity> root = query.from(PatientEntity.class);
        List<Predicate> predicates = new ArrayList<>();

        if (searchParams.getFirstName() != null && !searchParams.getFirstName().isEmpty()) {
            predicates.add(cb.equal(root.get("firstName"), searchParams.getFirstName()));
        }
        if (searchParams.getLastName() != null && !searchParams.getLastName().isEmpty()) {
            predicates.add(cb.equal(root.get("lastName"), searchParams.getLastName()));
        }
        if (searchParams.getBirthDateFrom() != null) {
            predicates.add(cb.greaterThanOrEqualTo(root.get("birthDate"), searchParams.getBirthDateFrom()));
        }
        if (searchParams.getBirthDateTo() != null) {
            predicates.add(cb.lessThanOrEqualTo(root.get("birthDate"), searchParams.getBirthDateTo()));
        }

        query.where(predicates.toArray(new Predicate[predicates.size()]));

        return entityManager.createQuery(query).getResultList();
    }
}
