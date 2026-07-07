package com.vti.department_service.service;

import com.vti.department_service.entity.Department;
import com.vti.department_service.form.DepartmentFilterForm;
import com.vti.department_service.repository.IDepartmentRepository;
import com.vti.department_service.specification.department.DepartmentSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService implements IDepartmentService {

    @Autowired
    private IDepartmentRepository _departmentRepository;

    @Override
    public Page<Department> getDepartmentList(Pageable pageable, String search, DepartmentFilterForm filterForm) {
        Specification<Department> where = DepartmentSpecification.buildWhere(search, filterForm);
        return _departmentRepository.findAll(where, pageable);
    }

    @Override
    public Department getDepartmentDetail(int id) {
        return _departmentRepository.findById(id).isPresent() ? _departmentRepository.findById(id).get() : null;
    }
}
