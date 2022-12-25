package examples.springboot.spec;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;

import examples.springboot.entity.Label;
import examples.springboot.entity.Technology;
import examples.springboot.entity.TechnologyLabel;
import examples.springboot.search.FilterParamsDTO;

public class TechnologyLabelSpec implements Specification<TechnologyLabel> {

    private static final long serialVersionUID = 8985307866747463718L;
    
	@Autowired
    FilterParamsDTO searchCriteria;
	
    public TechnologyLabelSpec(FilterParamsDTO searchCriteria) {
        this.searchCriteria = searchCriteria;
    }

    @Override
    public Predicate toPredicate(Root<TechnologyLabel> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

        List<Predicate> predicates = new ArrayList<>();

        Join<TechnologyLabel, Label> labelJoin = root.join("label", JoinType.INNER);
        Join<TechnologyLabel, Technology> technologyJoin = root.join("technology", JoinType.INNER);

        if (searchCriteria.getRating() != 0) {
            predicates.add(technologyJoin.get("rating").in(searchCriteria.getRating()));
        }

        if (searchCriteria.getFilterName() != null && !searchCriteria.getFilterName().isEmpty()) {
            predicates.add(cb.like(technologyJoin.get("technologyName"), searchCriteria.getFilterName()));
        }
        
        if (searchCriteria.getFilterLabel() != null && !searchCriteria.getFilterLabel().isEmpty()) {
        	predicates.add(cb.like(labelJoin.get("name"), searchCriteria.getFilterLabel()));
        }

        return cb.and(predicates.toArray(new Predicate[0]));
    }
}