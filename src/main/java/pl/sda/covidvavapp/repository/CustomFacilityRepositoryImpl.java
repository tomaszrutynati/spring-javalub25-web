package pl.sda.covidvavapp.repository;

import org.springframework.stereotype.Repository;
import pl.sda.covidvavapp.api.model.Facility;
import pl.sda.covidvavapp.api.model.FacilitySearchParams;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomFacilityRepositoryImpl implements CustomFacilityRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<FacilityEntity> findWithSearchParams(FacilitySearchParams searchParams) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<FacilityEntity> query = cb.createQuery(FacilityEntity.class);

        Root<FacilityEntity> root = query.from(FacilityEntity.class);

        List<Predicate> predicates = new ArrayList<>();

        if (searchParams.getCity() != null && !searchParams.getCity().isEmpty()) {
            predicates.add(cb.equal(root.get("city"), searchParams.getCity()));
        }
        if (searchParams.getStreet() != null && !searchParams.getStreet().isEmpty()) {
            predicates.add(cb.equal(root.get("street"), searchParams.getStreet()));
        }
        if (searchParams.getZipCode() != null && !searchParams.getZipCode().isEmpty()) {
            predicates.add(cb.equal(root.get("zipCode"), searchParams.getZipCode()));
        }

        query.where(predicates.toArray(new Predicate[predicates.size()]));

        return entityManager.createQuery(query).getResultList();
    }
}
