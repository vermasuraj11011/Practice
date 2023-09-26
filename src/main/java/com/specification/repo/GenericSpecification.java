package com.specification.repo;

import lombok.Data;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Data
public class GenericSpecification<T> implements Specification<T> {
    public String column;
    public String queryVal;

    public GenericSpecification(String colName, String queryVal) {
        this.column = colName;
        this.queryVal = queryVal;
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        return criteriaBuilder.equal(root.get(column), queryVal);
    }
}
