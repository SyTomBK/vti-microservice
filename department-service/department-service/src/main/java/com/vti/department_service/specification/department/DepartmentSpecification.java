package com.vti.department_service.specification.department;

import com.vti.department_service.entity.Department;
import com.vti.department_service.form.DepartmentFilterForm;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class DepartmentSpecification {

    public static Specification<Department> buildWhere(
            String search,
            DepartmentFilterForm filterForm) {

        return (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (search != null && !search.isBlank()) {
                predicates.add(
                        criteriaBuilder.like(
                                root.get("name"),
                                "%" + search + "%"
                        )
                );
            }

            if (filterForm != null) {

                if (filterForm.getType() != null) {
                    predicates.add(
                            criteriaBuilder.equal(
                                    root.get("type"),
                                    filterForm.getType()
                            )
                    );
                }

                if (filterForm.getMinCreateDate() != null) {
                    predicates.add(
                            criteriaBuilder.greaterThanOrEqualTo(
                                    root.get("createdAt"),
                                    filterForm.getMinCreateDate()
                            )
                    );
                }

                if (filterForm.getMaxCreateDate() != null) {
                    predicates.add(
                        criteriaBuilder.lessThanOrEqualTo(
                                root.get("createdAt"),
                                filterForm.getMaxCreateDate()
                        )
                    );
                }
            }

            return criteriaBuilder.and(
                    predicates.toArray(new Predicate[0])
            );
        };
    }
}