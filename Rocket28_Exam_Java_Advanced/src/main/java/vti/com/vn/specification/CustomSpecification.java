package vti.com.vn.specification;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.NonNull;
import vti.com.vn.entity.Group;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@SuppressWarnings("serial")
@RequiredArgsConstructor
public class CustomSpecification implements Specification<Group> {

    @NonNull
    private String field;

    @NonNull
    private Object value;

    @Override
    public Predicate toPredicate(
            Root<Group> root,
            CriteriaQuery<?> query,
            CriteriaBuilder criteriaBuilder
    ) {
        if (field.equalsIgnoreCase("groupName")) {
            return criteriaBuilder.like(root.get("groupName"), "%" + value.toString() + "%");
        }
        return null;
    }
}
