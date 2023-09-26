package com.specification.repo;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class ProductSpecification<T> {

    private String category;
    private int price;
    private String sort;

    public ProductSpecification (String category,int price){
        this.category = category;
        this.price = price;
    }

    public Specification<T> productWithCategory(){
        return new Specification<T>() {
            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.equal(root.get("category"), category);
            }
        };
    }

    public Specification<T> productGreaterThan(){
        return new Specification<T>() {
            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.greaterThanOrEqualTo(root.get("price"), price);
            }
        };
    }

    public Specification<T> sortByAsc(){
        return new Specification<T>() {
            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                query.orderBy(cb.asc(root.get("price")));
                return cb.greaterThanOrEqualTo(root.get("price"), price);
            }
        };
    }
}
